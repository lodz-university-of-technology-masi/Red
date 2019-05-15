package com.masi.red.controller.api;

import com.masi.red.IAnswerService;
import com.masi.red.IUserService;
import com.masi.red.dto.CandidateAnswerDTO;
import com.masi.red.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateController {

    private final IUserService userService;
    private final IAnswerService answerService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> addCandidate(@Valid @RequestBody UserDto candidate) {
        UserDto savedUser = userService.createUser(candidate);
        return ResponseEntity.ok(savedUser);
    }


    @PostMapping(value = "/kandydat/stanowisko/{jobId}")
    @ResponseBody
    public ResponseEntity<Object> getPostJobTestPage(@PathVariable Integer jobId, @Valid @RequestBody CandidateAnswerDTO answerDto) {
        boolean response = answerService.addAnswers(answerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
