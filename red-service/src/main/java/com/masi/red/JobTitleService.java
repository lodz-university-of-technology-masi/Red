package com.masi.red;

import com.masi.red.common.Language;
import com.masi.red.entity.JobTitle;
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
public class JobTitleService implements IJobTitleService {

    private final JobTitleRepository jobTitleRepository;
    private final TestRepository testRepository;

    @Override
    public JobTitle addJobTitle(JobTitle jobTitle) {
        return jobTitleRepository.save(jobTitle);
    }

    @Override
    public List<JobTitle> getAllJobTitles() {
        return jobTitleRepository.findAll();
    }

    @Override
    public JobTitle getJobTitleById(Integer id) {
        return jobTitleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono stanowiska o id " + id));
    }

    @Override
    public JobTitle updateJobTitle(Integer id, JobTitle jobTitle) {
        JobTitle jobTitleToEdit = jobTitleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono stanowiska o id" + id));
        jobTitleToEdit.setName(jobTitle.getName());
        jobTitleToEdit.setActive(jobTitle.isActive());
        jobTitleToEdit.getTestList().forEach(test -> test.setJobTitle(null));
        jobTitleToEdit.setTestList(jobTitle.getTestList());
        jobTitleToEdit.getTestList().forEach(test -> test.setJobTitle(jobTitleToEdit));
        return jobTitleToEdit;
    }

    @Override
    public JobTitle attachTestToJobTitle(Integer testId, Integer jobTitleId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono testu o id" + testId));
        JobTitle jobTitle = jobTitleRepository.findById(jobTitleId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono stanowiska o id" + jobTitleId));
        test.setJobTitle(jobTitle);
        jobTitle.attachTest(test);
        return jobTitle;
    }

    @Override
    public JobTitle detachTestFromJobTitle(Integer testId, Integer jobTitleId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono testu o id" + testId));
        JobTitle jobTitle = jobTitleRepository.findById(jobTitleId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono stanowiska o id" + jobTitleId));
        test.setJobTitle(null);
        jobTitle.detachTest(test);
        return jobTitle;
    }

    @Override
    public List<JobTitle> findByTestLanguage(Language language) {
        return jobTitleRepository.findByTestLanguage(language);
    }
}
