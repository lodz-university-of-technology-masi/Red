package com.masi.red.controller.api;

import com.masi.red.IAnswerService;
import com.masi.red.dto.CandidateAnswerDTO;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/kandydat")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateController {

    private final IAnswerService answerService;

    @PostMapping(value = "/stanowisko/{jobId}")
    @ResponseBody
    public ResponseEntity<Object> getPostJobTestPage(@PathVariable Integer jobId, @Valid @RequestBody CandidateAnswerDTO answerDto, @AuthenticationPrincipal User candidate){
        boolean response = answerService.addAnswers(answerDto, candidate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
