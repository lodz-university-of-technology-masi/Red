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

    private final TestService testService;
    private final QuestionService questionService;

    @GetMapping(value = "/kandydat/stanowisko/{jobID}")
    public String getJobTestPage(@PathVariable Integer jobID, @RequestParam String language, Model model){

        model.addAttribute("job", jobID);

        return "testpage";
    }

    @GetMapping(value = {"/moderator/tests/{testId}", "/redaktor/tests/{testId}"})
    public String redirect(@PathVariable Integer testId, Model model) {
        model.addAttribute("test", testService.getTestById(testId));
        model.addAttribute("existingQuestions", questionService.findNotAttachedQuestions(testId));
        return "test-details";
    }
}
