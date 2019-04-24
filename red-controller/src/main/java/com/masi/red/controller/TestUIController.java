package com.masi.red.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestUIController {

    @GetMapping(value = "/kandydat/stanowisko/{jobID}")
    public String getJobTestPage(@PathVariable Integer jobID, @RequestParam String language, Model model){

        model.addAttribute("job", jobID);

        return "testpage";
    }

}
