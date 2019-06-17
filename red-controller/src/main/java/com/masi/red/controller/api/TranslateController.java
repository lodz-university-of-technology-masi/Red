package com.masi.red.controller.api;

import com.masi.red.TestService;
import com.masi.red.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TranslateController {

    private final TestService testService;

    @GetMapping("/{testId}")
    public ResponseEntity<NewTestDTO> getTestByIdNewTestDTO(@PathVariable Integer testId) {
        NewTestDTO test = testService.getTestByIdNewTestDTO(testId);
        return ResponseEntity.ok(test);
    }
}
