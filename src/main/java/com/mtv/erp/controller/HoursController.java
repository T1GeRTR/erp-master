package com.mtv.erp.controller;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.DayOfWeek;
import com.mtv.erp.request.UsersGetFromDateDtoRequest;
import com.mtv.erp.response.HoursGetUserDtoResponse;
import com.mtv.erp.response.UserGetFromDateDtoResponse;
import com.mtv.erp.response.UsersGetFromDateDtoResponse;
import com.mtv.erp.service.HoursService;
import com.mtv.erp.service.UserService;
import com.mtv.erp.utils.MonthYearConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HoursController {

    @Autowired
    private HoursService hoursService;

    @Autowired
    private UserService userService;

    @PostConstruct
    @PostMapping(value = {"/hours/update"})
    String update() throws ServerException {
        userService.update();
        hoursService.update();
        return "index";
    }

    @RequestMapping(value = {"/hours/{monthYear}"}, method = RequestMethod.GET)
    String getFromDate(@PathVariable("monthYear") String monthYear, Model model) throws ServerException {
        int month = MonthYearConverter.getMonth(monthYear);
        int year = MonthYearConverter.getYear(monthYear);
        List<Integer> daysOfMonth = new ArrayList<>();
        List<String> daysOfWeek = new ArrayList<>();
        for (int i = 0; i < LocalDate.of(year, month, 1).lengthOfMonth(); i++) {
            daysOfMonth.add(i + 1);
            daysOfWeek.add(DayOfWeek.getDay(LocalDate.of(year, month, i+1).getDayOfWeek()).getDay());
        }
        model.addAttribute("users", new UsersGetFromDateDtoResponse(userService.getFromDate(monthYear)));
        model.addAttribute("days", daysOfMonth);
        model.addAttribute("weeks", daysOfWeek);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "usersFromDate";
    }

    @RequestMapping(value = {"/hours/{monthYear}"}, method = RequestMethod.POST)
    String saveUsersHour(Model model, @ModelAttribute("SpringWeb") UsersGetFromDateDtoRequest users, @PathVariable("monthYear") String monthYear) throws ServerException {
        userService.getFromDate(monthYear);
        List<HoursGetUserDtoResponse> oldHours = new ArrayList<>();
        for (UserGetFromDateDtoResponse user : userService.getFromDate(monthYear)) {
            oldHours.addAll(user.getHours());
        }
        hoursService.saveChanges(users, oldHours);
        List<Integer> daysOfMonth = new ArrayList<>();
        List<String> daysOfWeek = new ArrayList<>();
        for (int i = 0; i < LocalDate.now().lengthOfMonth(); i++) {
            daysOfMonth.add(i + 1);
            daysOfWeek.add(DayOfWeek.getDay(LocalDate.of(MonthYearConverter.getYear(monthYear), MonthYearConverter.getMonth(monthYear), i+1).getDayOfWeek()).getDay());
        }
        model.addAttribute("users", new UsersGetFromDateDtoResponse(userService.getFromDate(monthYear)));
        model.addAttribute("days", daysOfMonth);
        model.addAttribute("weeks", daysOfWeek);
        model.addAttribute("month", MonthYearConverter.getMonth(monthYear));
        model.addAttribute("year", MonthYearConverter.getYear(monthYear));
        return "usersFromDate";
    }
}
