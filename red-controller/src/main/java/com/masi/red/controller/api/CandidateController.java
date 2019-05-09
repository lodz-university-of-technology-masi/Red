package com.masi.red.controller.api;


import com.masi.red.UserService;
import com.masi.red.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> addCandidate(@Valid @RequestBody UserDto candidate) {
        userService.createUser(candidate);
        return ResponseEntity.ok("Successfully created account");
    }

}
