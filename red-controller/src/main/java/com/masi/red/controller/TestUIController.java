package com.masi.red.controller;


import com.masi.red.*;
import com.masi.red.dto.CandidateAnswerDTO;
import com.masi.red.entity.JobTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestUIController {


    @Autowired
    JobTitleService jobService;

    private final ITestService testService;
    private final IQuestionService questionService;
    private final IAnswerService answerService;


    @GetMapping(value = "/kandydat/stanowisko/{jobId}")
        public String getJobTestPage(@PathVariable Integer jobId, Model model){
        JobTitle jobTitle = jobService.getJobTitleById(jobId);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("candidate",username);
        model.addAttribute("jobTitle", jobTitle);
        model.addAttribute("test", testService.getTestById(1));

        return "testpage";
    }

    @PostMapping(value = "/kandydat/stanowisko/{jobId}")
    @ResponseBody
    public ResponseEntity<Object> getPostJobTestPage(@PathVariable Integer jobId, @Valid @RequestBody CandidateAnswerDTO answerDto){
           boolean response = answerService.addAnswers(answerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/kandydat/stanowisko/{jobId}/wynik")
    public String getJobTestResultPage(@PathVariable Integer jobId, Model model){
        JobTitle jobTitle = jobService.getJobTitleById(jobId);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("candidate",username);
        model.addAttribute("jobTitle", jobTitle);
        //model.addAttribute("test", testService.getTestById(1));

        return "test-page-result";
    }

    @GetMapping(value = {"/moderator/tests/{testId}", "/redaktor/tests/{testId}"})
    public String redirect(@PathVariable Integer testId, Model model) {
        model.addAttribute("test", testService.getTestById(testId));
        model.addAttribute("existingQuestions", questionService.findNotAttachedQuestions(testId));
        return "test-details";
    }

}
