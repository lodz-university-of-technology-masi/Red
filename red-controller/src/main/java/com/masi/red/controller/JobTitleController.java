package com.masi.red.controller;

import com.masi.red.JobTitleRepository;
import com.masi.red.entity.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class JobTitleController {
    @Autowired
    private JobTitleRepository jobTitleRepository;

    @GetMapping("/jobTitles")
    public ResponseEntity<Object> getAllJobTitles(){
        List<JobTitle> jobTitles = jobTitleRepository.findAll();
        return new ResponseEntity<>(jobTitles, HttpStatus.OK);
    }

    @GetMapping("/jobTitles/{jobTitleId}")
    public ResponseEntity<Object> getJobTitleById(@PathVariable Integer jobTitleId){
        JobTitle jobTitle = jobTitleRepository.findById(jobTitleId).get();
        return new ResponseEntity<>(jobTitle, HttpStatus.OK);
    }

    @PutMapping("/jobTitles/{jobTitleId}")
    public ResponseEntity<Object> updateJobTitle(@PathVariable  Integer jobTitleId,
                                                 @Valid @RequestBody JobTitle jobTitle){
        JobTitle title = jobTitleRepository.findById(jobTitleId).get();
        title.setName(jobTitle.getName());
        title.setActive(jobTitle.isActive());
        jobTitleRepository.save(title);
        return new ResponseEntity<>(title,HttpStatus.OK);
    }

    @PostMapping("/jobTitles")
    public ResponseEntity<Object> addJobTitle(@Valid @RequestBody JobTitle jobTitle){
        jobTitleRepository.save(jobTitle);
        return new ResponseEntity<>("Job title "+jobTitle.getName()+" was added",HttpStatus.CREATED);
    }
}
