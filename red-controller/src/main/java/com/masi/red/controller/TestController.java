package com.masi.red.controller;

import com.masi.red.TestRepository;
import com.masi.red.entity.JobTitle;
import com.masi.red.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@EnableJpaAuditing(setDates = false)
@RestController
public class TestController {
    @Autowired
    private TestRepository testRepository;

    @GetMapping("/tests")
    public ResponseEntity<Object> getAllTests(){
        List<Test> tests = testRepository.findAll();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/tests/{testId}")
    public ResponseEntity<Object> getTestById(@PathVariable Integer testId){
        Test test = testRepository.findById(testId).get();
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PutMapping("/tests/{testId}/jobTitle")
    public ResponseEntity<Object> updateTest(@PathVariable Integer testId,
                                             @RequestBody JobTitle jobtitle){
        Test testObject = testRepository.findById(testId).get();
        JobTitle job = jobtitle;
        testObject.setJobTitle(job);
        testRepository.save(testObject);
        return new ResponseEntity<>(testObject,HttpStatus.OK);
    }

    @PostMapping("/tests")
    public ResponseEntity<Object> addTest(@Valid @RequestBody Test test){
        testRepository.save(test);
        return new ResponseEntity<>("Test "+test.getId()+" was added",HttpStatus.CREATED);
    }

    @DeleteMapping("/tests/{testId}")
    public ResponseEntity<Object> deleteTest(@PathVariable Integer testId) {
        Test test = testRepository.findById(testId).get();
        testRepository.delete(test);
        return new ResponseEntity<>("Test " + testId + " was deleted", HttpStatus.OK);
    }
}
