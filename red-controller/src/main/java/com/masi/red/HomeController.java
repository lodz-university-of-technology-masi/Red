package com.masi.red;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        //Jeśli rola to KANDYDAT to zwróć:
        // return "candidate";

        //Jeśli rola to REDAKTOR to zwróć:
        // return "editor";

        //Jeśli rola to MODERATOR to zwróć:
        // return "moderator";

        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/kandydat")
    public String candidateHome() {
        return "candidate";
    }

}
