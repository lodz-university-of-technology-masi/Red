package com.masi.red;

import com.masi.red.common.QuestionTypeMapper;
import com.masi.red.common.RoleName;
import com.masi.red.dto.*;
import com.masi.red.entity.*;
import com.masi.red.exception.NoTestsAvailableException;
import com.masi.red.exception.ResourceAccessForbiddenException;
import com.masi.red.helper.EntityFinder;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestService implements ITestService {

    private final TestRepository testRepository;
    private final EntityFinder entityFinder;
    private final MapperFacade mapper;
    private final QuestionRepository questionRepository;
    private final CandidateAnswerRepository answerRepository;

    private Random rand = new Random();

    @Override
    public TestDTO addTest(NewTestDTO testDTO, User user) {
        JobTitle jobTitle = entityFinder.findJobTitleById(testDTO.getJobTitleId());
        Set<@NotNull RoleName> roles = mapRoleSetToRoleNameSet(user);
        User testOwner;
        if (roles.contains(RoleName.MODERATOR) && testDTO.getEditorId() != null) {
            testOwner = entityFinder.findUserById(testDTO.getEditorId());
        } else {
            testOwner = user;
        }
        Test test = mapper.map(testDTO, Test.class);
        test.setUser(testOwner);
        test.setJobTitle(jobTitle);
        //TODO: implement set questions
        jobTitle.attachTest(test);

        return mapper.map(testRepository.save(test), TestDTO.class);
    }

    @Override
    public List<TestDTO> getAllTests() {

        return testRepository.findAll()
                .stream()
                .map(test -> mapper.map(test, TestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TestWithQuestionsDTO getTestById(Integer id) {
        Test test = entityFinder.findTestById(id);
        return mapper.map(test, TestWithQuestionsDTO.class);
    }

    @Override
    public TestWithQuestionsDTO getRandomTest(Integer jobTitleId, Integer userId) throws NoTestsAvailableException {
        List<Test> allTests = testRepository.findAllByJobTitleId(jobTitleId);
        List<Integer> userTestIds = answerRepository
                .findAllByUserId(userId).stream()
                .map(CandidateAnswer::getTest)
                .map(Test::getId).collect(Collectors.toList());

        allTests.removeIf(test -> userTestIds.contains(test.getId()));

        if (allTests.isEmpty()) {
            throw new NoTestsAvailableException("All tests were completed!");
        }

        Test test = allTests.get(rand.nextInt(allTests.size()));
        return mapper.map(test, TestWithQuestionsDTO.class);
    }

    @Override
    public TestDTO updateTest(Integer id, EditedTestDTO editedTest, User user) throws ResourceAccessForbiddenException {
        Test testToEdit = entityFinder.findTestById(id);
        if (isTestAccessForbidden(user, testToEdit)) {
            throw new ResourceAccessForbiddenException("Edycja testu dozwolona dla moderatorów i właścicieli testu");
        }
        Set<@NotNull RoleName> roles = mapRoleSetToRoleNameSet(user);
        User testOwner;
        if (roles.contains(RoleName.MODERATOR) && editedTest.getEditorId() != null) {
            testOwner = entityFinder.findUserById(editedTest.getEditorId());
        } else {
            testOwner = user;
        }

        mapper.map(editedTest, testToEdit);
        if (testToEdit.getJobTitle() == null) {
            JobTitle jobTitle = entityFinder.findJobTitleById(editedTest.getJobTitleId());
            testToEdit.setJobTitle(jobTitle);
        } else if (jobTitleChanged(editedTest, testToEdit)) {
            JobTitle jobTitle = entityFinder.findJobTitleById(editedTest.getJobTitleId());
            testToEdit.getJobTitle().detachTest(testToEdit);
            testToEdit.setJobTitle(jobTitle);
            testToEdit.getJobTitle().attachTest(testToEdit);
        }

        testToEdit.setUser(testOwner);
        //testToEdit.setQuestions(editedTest.getQuestions()); TODO implement
        return mapper.map(testToEdit, TestDTO.class);
    }

    private boolean jobTitleChanged(EditedTestDTO editedTest, Test testToEdit) {
        JobTitle jobTitle = testToEdit.getJobTitle();
        if (jobTitle == null) return true;
        return !Objects.equals(editedTest.getJobTitleId(), jobTitle.getId());
    }

    @Override
    public void deleteTest(Integer testId, User user) throws ResourceAccessForbiddenException {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new EntityNotFoundException("Test o id: " + testId + " nie istnieje"));
        if (isTestAccessForbidden(user, test)) {
            throw new ResourceAccessForbiddenException("Usunięcie testu dozwolone dla moderatorów i właścicieli testu");
        }
        testRepository.deleteById(testId);
    }

    @Override
    public void detachQuestionFromTest(Integer testId, Integer questionId, User user) throws ResourceAccessForbiddenException {
        Test test = entityFinder.findTestById(testId);
        if (isTestAccessForbidden(user, test)) {
            throw new ResourceAccessForbiddenException("Odpięcie pytania od testu dozwolone dla moderatorów i właścicieli testu");
        }
        test.getQuestions().stream()
                .filter(question -> question.getId() == questionId)
                .findAny()
                .ifPresent(question -> question.detachTest(testId));
        test.detachQuestion(questionId);
    }

    @Override
    public void attachQuestionToTest(QuestionDTO questionDTO, Integer testId, User user) throws ResourceAccessForbiddenException {
        Test test = entityFinder.findTestById(testId);
        if (isTestAccessForbidden(user, test)) {
            throw new ResourceAccessForbiddenException("Przypięcie pytania do testu dozwolone dla moderatorów i właścicieli testu");
        }
        Question question;
        if (questionDTO.getId() == null) {
            question = mapper.map(questionDTO, QuestionTypeMapper.getEntityClass(questionDTO));
            if (question.getOriginalQuestion() == null || Objects.equals(question.getOriginalQuestion().getId(), questionDTO.getId())) {
                question.setOriginalQuestion(question);
            }
            question = questionRepository.save(question);
        } else {
            question = entityFinder.findQuestionById(questionDTO.getId());
        }
        test.attachQuestion(question);
        question.attachToTest(test);
    }

    @Override
    public List<TestDTO> getTestsByUserId(User user) {
        List<Test> tests = testRepository.findAllByUserId(user.getId());
        return tests.stream()
                .map(test -> mapper.map(test, TestDTO.class))
                .collect(Collectors.toList());
    }

    private boolean isTestAccessForbidden(User user, Test test) {
        Set<@NotNull RoleName> roles = mapRoleSetToRoleNameSet(user);
        boolean isModerator = roles.contains(RoleName.MODERATOR);
        boolean isEditorOwner = roles.contains(RoleName.EDITOR)
                && test.getUser().getId().equals(user.getId());
        return !isModerator && !isEditorOwner;
    }

    private Set<@NotNull RoleName> mapRoleSetToRoleNameSet(User user) {
        return user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    }
}
