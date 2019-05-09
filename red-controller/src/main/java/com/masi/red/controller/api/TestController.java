package com.masi.red.controller.api;

import com.masi.red.TestService;
import com.masi.red.dto.*;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<TestWithQuestionsDTO> getTestById(@PathVariable Integer testId){
        TestWithQuestionsDTO test = testService.getTestById(testId);
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

    @DeleteMapping("/tests/{testId}/questions/{questionId}")
    public ResponseEntity<String> detachQuestionFromTest(@PathVariable Integer testId, @PathVariable Integer questionId) {
        testService.detachQuestionFromTest(testId, questionId);
        return ResponseEntity.ok("Pytanie " + questionId + " zostało usunięte z testu " + testId);
    }

    @PostMapping("/tests/{testId}/questions")
    public ResponseEntity<String> attachQuestionToTest(@PathVariable Integer testId, @Valid @RequestBody QuestionDTO question) {
        testService.attachQuestionToTest(question, testId);
        return ResponseEntity.ok("Pytanie zostało dodane do testu " + testId);
    }
}
