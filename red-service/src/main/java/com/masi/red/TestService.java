package com.masi.red;

import com.masi.red.dto.NewTestDTO;
import com.masi.red.dto.TestDTO;
import com.masi.red.entity.JobTitle;
import com.masi.red.entity.Test;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestService implements ITestService {

    private final TestRepository testRepository;
    private final JobTitleRepository jobTitleRepository;
    private final UserRepository userRepository;

    @Override
    public Test addTest(NewTestDTO testDTO) {
        JobTitle jobTitle = jobTitleRepository.findById(testDTO.getJobTitleId())
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono stanowiska o id " + testDTO.getJobTitleId()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipalName)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono użytkownika o loginie " + currentPrincipalName));
        Test test = Test.builder()
                .jobTitle(jobTitle)
                .creationTime(OffsetDateTime.now())
                //.questionsList(questionRepository...) TODO: implement
                .user(user)
                .build();
        jobTitle.attachTest(test);
        return testRepository.save(test);
    }

    @Override
    public List<TestDTO> getAllTests() {

        return testRepository.findAll()
                .stream()
                .map(test -> TestDTO.builder().id(test.getId())
                        .editorName((test.getUser() != null) ? (test.getUser().getFirstName() + " " + test.getUser().getLastName()) : null)
                        .creationDate(test.getCreationTime())
                        .jobTitleName(test.getJobTitle().getName())
                        .questionsNumber(test.getQuestionsList().size())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Test getTestById(Integer id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie ma testu o id " + id));
    }

    @Override
    public Test updateTest(Integer id, Test test) {
        Test testToEdit = testRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie ma testu o id " + id));
        if(jobTitleChanged(test, testToEdit)) {
            testToEdit.getJobTitle().detachTest(testToEdit);
            testToEdit.setJobTitle(test.getJobTitle());
            testToEdit.getJobTitle().attachTest(testToEdit);
        }
        testToEdit.setQuestionsList(test.getQuestionsList());
        return testToEdit;
    }

    private boolean jobTitleChanged(Test test, Test testToEdit) {
        return test.getJobTitle().getId() != testToEdit.getJobTitle().getId();
    }

    @Override
    public void deleteTest(Integer id) {
        testRepository.deleteById(id);
    }
}
