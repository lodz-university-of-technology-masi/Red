package com.masi.red;

import com.masi.red.dto.EditedTestDTO;
import com.masi.red.dto.NewTestDTO;
import com.masi.red.dto.TestDTO;
import com.masi.red.dto.TestWithQuestionsDTO;
import com.masi.red.entity.JobTitle;
import com.masi.red.entity.Question;
import com.masi.red.entity.Test;
import com.masi.red.entity.User;
import com.masi.red.helper.EntityFinder;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestService implements ITestService {

    private final TestRepository testRepository;
    private final EntityFinder entityFinder;
    private final MapperFacade mapper;

    @Override
    public TestDTO addTest(NewTestDTO testDTO, User user) {
        JobTitle jobTitle = entityFinder.findJobTitleById(testDTO.getJobTitleId());
        Test test = Test.builder()
                .user(user)
                .jobTitle(jobTitle)
                .build();
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
    public TestDTO updateTest(Integer id, EditedTestDTO editedTest) {
        Test testToEdit = entityFinder.findTestById(id);
        mapper.map(editedTest, testToEdit);
        if(jobTitleChanged(editedTest, testToEdit)) {
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
}
