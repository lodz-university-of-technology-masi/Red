package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user) {
        Set<@NotNull RoleName> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        if (roles.contains(RoleName.MODERATOR)) {
            return "moderator";
        } else if (roles.contains(RoleName.EDITOR)) {
            return "editor";
        } else {
            return "candidate";
        }
    }


    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/kandydat")
    public String candidateHome() {
        return "candidate";
    }

}
