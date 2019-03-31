package com.masi.red.controller;

import com.masi.red.TestService;
import com.masi.red.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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

@EnableJpaAuditing(setDates = false)
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final TestService testService;

    @GetMapping("/tests")
    public ResponseEntity<Object> getAllTests(){
        List<Test> tests = testService.getAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/tests/{testId}")
    public ResponseEntity<Object> getTestById(@PathVariable Integer testId){
        Test test = testService.getTestById(testId);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PutMapping("/tests/{testId}")
    public ResponseEntity<Object> updateTest(@PathVariable Integer testId,
                                             @RequestBody Test test){
        Test testObject = testService.updateTest(testId, test);
        return new ResponseEntity<>(testObject,HttpStatus.OK);
    }

    @PostMapping("/tests")
    public ResponseEntity<Object> addTest(@Valid @RequestBody Test test){
        testService.addTest(test);
        return new ResponseEntity<>("Test "+test.getId()+" został dodany",HttpStatus.CREATED);
    }

    @DeleteMapping("/tests/{testId}")
    public ResponseEntity<Object> deleteTest(@PathVariable Integer testId) {
        testService.deleteTest(testId);
        return new ResponseEntity<>("Test " + testId + " został usunięty", HttpStatus.OK);
    }
}
