package com.masi.red.controller.api;

import com.masi.red.TestService;
import com.masi.red.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TranslateController {

    private final TestService testService;

    @GetMapping("/{testId}")
    public ResponseEntity<NewTestDTO> getTestById1(@PathVariable Integer testId) {
        NewTestDTO test = testService.getTestById1(testId);
        return ResponseEntity.ok(test);
    }
}
