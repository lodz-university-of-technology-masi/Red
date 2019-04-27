package com.masi.red.controller;


import com.masi.red.QuestionService;
import com.masi.red.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestUIController {


    @Autowired
    JobTitleService jobService;

    private final TestService testService;
    private final QuestionService questionService;


    @GetMapping(value = "/kandydat/stanowisko/{jobId}")
    public String getJobTestPage(@PathVariable Integer jobId, @RequestParam String language, Model model){
        public String getJobTestPage(@PathVariable Integer jobId, Model model){
        JobTitle jobTitle = jobService.getJobTitleById(jobId);

        model.addAttribute("jobTitle", jobTitle);

        return "testpage";
    }

    @GetMapping(value = {"/moderator/tests/{testId}", "/redaktor/tests/{testId}"})
    public String redirect(@PathVariable Integer testId, Model model) {
        model.addAttribute("test", testService.getTestById(testId));
        model.addAttribute("existingQuestions", questionService.findNotAttachedQuestions(testId));
        return "test-details";
    }

}
