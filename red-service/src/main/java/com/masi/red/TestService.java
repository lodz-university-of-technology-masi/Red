package com.masi.red;

import com.masi.red.common.Language;
import com.masi.red.common.QuestionTypeMapper;
import com.masi.red.dto.*;
import com.masi.red.entity.*;
import com.masi.red.exception.NoTestsAvailableException;
import com.masi.red.helper.EntityFinder;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
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
    public TestDTO addTest(NewTestDTO testDTO) {
        JobTitle jobTitle = entityFinder.findJobTitleById(testDTO.getJobTitleId());
        Test test = mapper.map(testDTO, Test.class);
        if(testDTO.getEditorId() != null) {
            User editor = entityFinder.findUserById(testDTO.getEditorId());
            test.setUser(editor);
        }

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
        List<Test> allTests = testRepository.findAllByJobTitle_Id(jobTitleId);
        List<Integer> userTestIds = answerRepository
                .findAllByUser_Id(userId).stream()
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
    public TestDTO updateTest(Integer id, EditedTestDTO editedTest) {
        Test testToEdit = entityFinder.findTestById(id);
        mapper.map(editedTest, testToEdit);
        if (jobTitleChanged(editedTest, testToEdit)) {
            JobTitle jobTitle = entityFinder.findJobTitleById(editedTest.getJobTitleId());
            testToEdit.getJobTitle().detachTest(testToEdit);
            testToEdit.setJobTitle(jobTitle);
            testToEdit.getJobTitle().attachTest(testToEdit);
        }

        //testToEdit.setQuestions(editedTest.getQuestions()); TODO implement
        return mapper.map(testToEdit, TestDTO.class);
    }

    private boolean jobTitleChanged(EditedTestDTO editedTest, Test testToEdit) {
        return !editedTest.getJobTitleId().equals(testToEdit.getJobTitle().getId());
    }

    @Override
    public void deleteTest(Integer id) {
        testRepository.deleteById(id);
    }

    @Override
    public void detachQuestionFromTest(Integer testId, Integer questionId) {
        Test test = entityFinder.findTestById(testId);
        test.getQuestions().stream()
                .filter(question -> question.getId() == questionId)
                .findAny()
                .get().detachTest(testId);
        test.detachQuestion(questionId);
    }

    @Override
    public void attachQuestionToTest(QuestionDTO questionDTO, Integer testId) {
        Test test = entityFinder.findTestById(testId);
        Question question;
        if (questionDTO.getId() == null) {
            question = mapper.map(questionDTO, QuestionTypeMapper.getEntityClass(questionDTO));
            if (question.getOriginalQuestion() == null || question.getOriginalQuestion().getId() == questionDTO.getId().intValue()) {
                question.setOriginalQuestion(question);
            }
            question = questionRepository.save(question);
        } else {
            question = entityFinder.findQuestionById(questionDTO.getId());
        }
        test.attachQuestion(question);
        question.attachtoTest(test);
    }
}
