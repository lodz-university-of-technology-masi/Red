package com.masi.red.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/moderator")
public class ModeratorController {

    @GetMapping(value = {"/",""} )
    public String showHome(){

        return "moderator";
    }

}
