package com.masi.red;

import com.masi.red.common.Language;
import com.masi.red.entity.JobTitle;

import java.util.List;

public interface IJobTitleService {

    JobTitle addJobTitle(JobTitle jobTitle);
    List<JobTitle> getAllJobTitles();
    JobTitle getJobTitleById(Integer id);
    JobTitle updateJobTitle(Integer id, JobTitle jobTitle);
    JobTitle attachTestToJobTitle(Integer testId, Integer jobTitleId);
    JobTitle detachTestFromJobTitle(Integer testId, Integer jobTitleId);
    List<JobTitle> findByTestLanguage(Language language);
}
