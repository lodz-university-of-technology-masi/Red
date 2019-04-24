package com.masi.red.controller;


import com.masi.red.JobTitleService;
import com.masi.red.entity.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestUIController {

    @Autowired
    JobTitleService jobService;

    @GetMapping(value = "/kandydat/stanowisko/{jobId}")
//    public String getJobTestPage(@PathVariable Integer jobId, @RequestParam String language, Model model){
        public String getJobTestPage(@PathVariable Integer jobId, Model model){
        JobTitle jobTitle = jobService.getJobTitleById(jobId);

        model.addAttribute("jobTitle", jobTitle);

        return "testpage";
    }
}
