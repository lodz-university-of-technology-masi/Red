package com.masi.red.controller.api;

import com.masi.red.ICsvService;
import com.masi.red.ITestService;
import com.masi.red.dto.*;
import com.masi.red.entity.User;
import com.masi.red.exception.ResourceAccessForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final ITestService testService;
    private final ICsvService csvService;

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @GetMapping
    public ResponseEntity<List<TestDTO>> getUsersTests(@AuthenticationPrincipal User user) {
        List<TestDTO> tests = testService.getTestsByUserId(user);
        return ResponseEntity.ok(tests);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/all")
    public ResponseEntity<List<TestDTO>> getAllTests() {
        List<TestDTO> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{testId}")
    public ResponseEntity<TestWithQuestionsDTO> getTestById(@PathVariable Integer testId) {
        TestWithQuestionsDTO test = testService.getTestById(testId);
        return ResponseEntity.ok(test);
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @PutMapping("/{testId}")
    public ResponseEntity updateTest(@PathVariable Integer testId,
                                     @RequestBody EditedTestDTO test,
                                     @AuthenticationPrincipal User user) {
        try {
            TestDTO testObject = testService.updateTest(testId, test, user);
            return ResponseEntity.ok(testObject);
        } catch (ResourceAccessForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @PostMapping
    public ResponseEntity<String> addTest(@Valid @RequestBody NewTestDTO testDTO, @AuthenticationPrincipal User user) {
        TestDTO test = testService.addTest(testDTO, user);
        return new ResponseEntity<>("Test " + test.getId() + " został dodany", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @DeleteMapping("/{testId}")
    public ResponseEntity deleteTest(@PathVariable Integer testId,
                                     @AuthenticationPrincipal User user) {
        try {
            testService.deleteTest(testId, user);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ResourceAccessForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @DeleteMapping("/{testId}/questions/{questionId}")
    public ResponseEntity detachQuestionFromTest(@PathVariable Integer testId,
                                                 @PathVariable Integer questionId,
                                                 @AuthenticationPrincipal User user) {
        try {
            testService.detachQuestionFromTest(testId, questionId, user);
            return ResponseEntity.noContent().build();
        } catch (ResourceAccessForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @PostMapping("/{testId}/questions")
    public ResponseEntity attachQuestionToTest(@PathVariable Integer testId,
                                               @Valid @RequestBody QuestionDTO question,
                                               @AuthenticationPrincipal User user) {
        try {
            testService.attachQuestionToTest(question, testId, user);
            return ResponseEntity.noContent().build();
        } catch (ResourceAccessForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @PostMapping("/import")
    public ResponseEntity importTest(@RequestParam("file") MultipartFile file) {
        TestDTO testDTO = testService.importTest(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(testDTO);
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @GetMapping("/{testId}/export")
    public void exportTest(@PathVariable Integer testId, HttpServletResponse response) throws IOException {
        csvService.exportTestCsv(testId, response);
    }


}
