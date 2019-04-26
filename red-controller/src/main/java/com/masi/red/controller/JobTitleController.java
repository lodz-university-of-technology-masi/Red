package com.masi.red.controller;

import com.masi.red.IJobTitleService;
import com.masi.red.common.Language;
import com.masi.red.entity.JobTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobTitleController {

    private final IJobTitleService jobTitleService;

    @GetMapping("/jobTitles")
    public ResponseEntity<Object> getAllJobTitles(){
        List<JobTitle> jobTitles = jobTitleService.getAllJobTitles();
        return new ResponseEntity<>(jobTitles, HttpStatus.OK);
    }

    @GetMapping("/jobTitles/{jobTitleId}")
    public ResponseEntity<Object> getJobTitleById(@PathVariable Integer jobTitleId){
        JobTitle jobTitle = jobTitleService.getJobTitleById(jobTitleId);
        return new ResponseEntity<>(jobTitle, HttpStatus.OK);
    }

    @PutMapping("/jobTitles/{jobTitleId}")
    public ResponseEntity<Object> updateJobTitle(@PathVariable Integer jobTitleId,
                                                 @Valid @RequestBody JobTitle jobTitle){
        JobTitle title = jobTitleService.updateJobTitle(jobTitleId, jobTitle);
        return new ResponseEntity<>(title,HttpStatus.OK);
    }

    @PostMapping("/jobTitles")
    public ResponseEntity<Object> addJobTitle(@Valid @RequestBody JobTitle jobTitle){
        jobTitleService.addJobTitle(jobTitle);
        return new ResponseEntity<>("Stanowisko "+jobTitle.getName()+" zostało dodane",HttpStatus.CREATED);
    }

    @PutMapping("/jobTitles/{jobTitleId}/tests/{testId}")
    public ResponseEntity<Object> attachTestToJobTitle(@PathVariable Integer testId, @PathVariable Integer jobTitleId) {
        jobTitleService.attachTestToJobTitle(testId, jobTitleId);
        return new ResponseEntity<>("Test " + testId
                + " został przypisany do stanowiska " + jobTitleId, HttpStatus.OK);
    }

    @DeleteMapping("/jobTitles/{jobTitleId}/tests/{testId}")
    public ResponseEntity<Object> detachTestFromJobTitle(@PathVariable Integer testId, @PathVariable Integer jobTitleId) {
        jobTitleService.detachTestFromJobTitle(testId, jobTitleId);
        return new ResponseEntity<>("Test " + testId
                + " został odpięty od stanowiska " + jobTitleId, HttpStatus.OK);
    }

    @GetMapping("/jobTitles/language/{languageName}")
    public ResponseEntity<Object> findByTestLanguage(@PathVariable Language languageName){
        List<JobTitle> jobTitles = jobTitleService.findByTestLanguage(languageName);
        return new ResponseEntity<>(jobTitles, HttpStatus.OK);
    }
}
