package com.mtv.erp.controller;

import com.mtv.erp.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        User user = new User();
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        model.addAttribute("user", user);
        model.addAttribute("month", LocalDate.now().getMonthValue());
        model.addAttribute("year", LocalDate.now().getYear());
        return "index";
    }

}
