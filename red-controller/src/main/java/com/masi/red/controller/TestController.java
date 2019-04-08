package com.masi.red.controller;

import com.masi.red.TestService;
import com.masi.red.dto.EditedTestDTO;
import com.masi.red.dto.NewTestDTO;
import com.masi.red.dto.TestDTO;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<TestDTO>> getAllTests(){
        List<TestDTO> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/tests/{testId}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Integer testId){
        TestDTO test = testService.getTestById(testId);
        return ResponseEntity.ok(test);
    }

    @PutMapping("/tests/{testId}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable Integer testId,
                                             @RequestBody EditedTestDTO test){
        TestDTO testObject = testService.updateTest(testId, test);
        return ResponseEntity.ok(testObject);
    }

    @PostMapping("/tests")
    public ResponseEntity<String> addTest(@Valid @RequestBody NewTestDTO testDTO, @AuthenticationPrincipal User editor){
        TestDTO test = testService.addTest(testDTO, editor);
        return new ResponseEntity<>("Test "+test.getId()+" został dodany",HttpStatus.CREATED);
    }

    @DeleteMapping("/tests/{testId}")
    public ResponseEntity<String> deleteTest(@PathVariable Integer testId) {
        testService.deleteTest(testId);
        return ResponseEntity.ok("Test " + testId + " został usunięty");
    }
}
