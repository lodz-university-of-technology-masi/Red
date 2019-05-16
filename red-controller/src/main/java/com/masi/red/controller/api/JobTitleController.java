package com.masi.red.controller.api;

import com.masi.red.IJobTitleService;
import com.masi.red.common.Language;
import com.masi.red.entity.JobTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobTitles")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobTitleController {

    private final IJobTitleService jobTitleService;

    @GetMapping
    public ResponseEntity<Object> findJobTitles(@RequestParam(name = "language", required = false) Language languageName) {
        List<JobTitle> jobTitles;
        if (languageName != null) {
            jobTitles = jobTitleService.findByTestLanguage(languageName);
        } else {
            jobTitles = jobTitleService.getAllJobTitles();
        }
        return new ResponseEntity<>(jobTitles, HttpStatus.OK);
    }

    @GetMapping("/{jobTitleId}")
    public ResponseEntity<Object> getJobTitleById(@PathVariable Integer jobTitleId) {
        JobTitle jobTitle = jobTitleService.getJobTitleById(jobTitleId);
        return new ResponseEntity<>(jobTitle, HttpStatus.OK);
    }

    @PutMapping("/{jobTitleId}")
    public ResponseEntity<Object> updateJobTitle(@PathVariable Integer jobTitleId,
                                                 @Valid @RequestBody JobTitle jobTitle) {
        JobTitle title = jobTitleService.updateJobTitle(jobTitleId, jobTitle);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addJobTitle(@Valid @RequestBody JobTitle jobTitle) {
        jobTitleService.addJobTitle(jobTitle);
        return new ResponseEntity<>("Stanowisko " + jobTitle.getName() + " zostało dodane", HttpStatus.CREATED);
    }

    @PutMapping("/{jobTitleId}/tests/{testId}")
    public ResponseEntity<Object> attachTestToJobTitle(@PathVariable Integer testId, @PathVariable Integer jobTitleId) {
        jobTitleService.attachTestToJobTitle(testId, jobTitleId);
        return new ResponseEntity<>("Test " + testId
                + " został przypisany do stanowiska " + jobTitleId, HttpStatus.OK);
    }

    @DeleteMapping("/{jobTitleId}/tests/{testId}")
    public ResponseEntity<Object> detachTestFromJobTitle(@PathVariable Integer testId, @PathVariable Integer jobTitleId) {
        jobTitleService.detachTestFromJobTitle(testId, jobTitleId);
        return new ResponseEntity<>("Test " + testId
                + " został odpięty od stanowiska " + jobTitleId, HttpStatus.OK);
    }
}
