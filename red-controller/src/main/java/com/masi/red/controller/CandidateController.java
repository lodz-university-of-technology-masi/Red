package com.masi.red.controller;


import com.masi.red.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CandidateController {

    @PostMapping(value="/candidate")
    public ResponseEntity<String> addCandidate(@Valid @RequestBody User candidate){

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
