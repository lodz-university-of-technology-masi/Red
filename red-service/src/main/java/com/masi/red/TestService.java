package com.masi.red;

import com.masi.red.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestService implements ITestService {

    private final TestRepository testRepository;

    @Override
    public Test addTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
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
