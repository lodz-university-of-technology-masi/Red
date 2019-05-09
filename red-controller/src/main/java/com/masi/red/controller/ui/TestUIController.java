package com.masi.red.controller.ui;


import com.masi.red.IJobTitleService;
import com.masi.red.IQuestionService;
import com.masi.red.ITestService;
import com.masi.red.entity.JobTitle;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestUIController {

    private final IJobTitleService jobService;
    private final ITestService testService;
    private final IQuestionService questionService;


    @GetMapping(value = "/kandydat/stanowisko/{jobId}")
    public String getJobTestPage(@PathVariable Integer jobId, Model model, @AuthenticationPrincipal User user) {
        JobTitle jobTitle = jobService.getJobTitleById(jobId);

        String username = user.getUsername();

        model.addAttribute("candidate", username);
        model.addAttribute("jobTitle", jobTitle);
        model.addAttribute("test", testService.getTestById(1)); //TODO randomize test picker

        return "testpage";
    }

    @GetMapping(value = "/kandydat/stanowisko/{jobId}/wynik")
    public String getJobTestResultPage(@PathVariable Integer jobId, Model model, @AuthenticationPrincipal User user) {
        JobTitle jobTitle = jobService.getJobTitleById(jobId);

        String username = user.getUsername();

        model.addAttribute("candidate", username);
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
