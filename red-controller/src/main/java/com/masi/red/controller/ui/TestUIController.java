package com.masi.red.controller.ui;


import com.masi.red.IJobTitleService;
import com.masi.red.IQuestionService;
import com.masi.red.ITestService;
import com.masi.red.dto.TestWithQuestionsDTO;
import com.masi.red.entity.JobTitle;
import com.masi.red.entity.User;
import com.masi.red.exception.NoTestsAvailableException;
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
        try {
            TestWithQuestionsDTO randomTest = testService.getRandomTest(jobId, user.getId());
            model.addAttribute("test", randomTest);

            JobTitle jobTitle = jobService.getJobTitleById(jobId);

            model.addAttribute("candidate", user.getUsername());
            model.addAttribute("jobTitle", jobTitle);

            return "testpage";
        } catch (NoTestsAvailableException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
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
    public String getTestDetailsPage(@PathVariable Integer testId, Model model) {
        TestWithQuestionsDTO test = testService.getTestById(testId);
        model.addAttribute("test", test);
        model.addAttribute("existingQuestions",
                questionService.findNotAttachedQuestionsWithLanguage(testId, test.getLanguage()));
        return "test-details";
    }

}
