package com.mtv.erp.controller;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.DayOfWeek;
import com.mtv.erp.request.HoursGetUserDtoRequest;
import com.mtv.erp.request.UserGetFromDateDtoRequest;
import com.mtv.erp.request.UserSaveDtoRequest;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "*")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HoursService hoursService;

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String getAll(Model model) throws ServerException {
        model.addAttribute("user", new UserSaveDtoRequest(1, "Имя", "Фамилия", "email@mail.ru"));
        model.addAttribute("users", userService.getAll());
        model.addAttribute("month", LocalDate.now().getMonthValue());
        model.addAttribute("year", LocalDate.now().getYear());
        return "getAllUser";
    }

    @RequestMapping(value = {"/user/id/{monthYear}"}, method = RequestMethod.GET)
    String getFromDateById(@PathVariable("id") int id, @PathVariable("monthYear") String monthYear, Model model) throws ServerException {
        List<Integer> daysOfMonth = new ArrayList<>();
        List<String> daysOfWeek = new ArrayList<>();
        for (int i = 0; i < LocalDate.now().lengthOfMonth(); i++) {
            daysOfMonth.add(i + 1);
            daysOfWeek.add(DayOfWeek.getDay(LocalDate.of(MonthYearConverter.getYear(monthYear), MonthYearConverter.getMonth(monthYear), i+1).getDayOfWeek()).getDay());
        }
        model.addAttribute("user", userService.getFromDateById(id, monthYear));
        model.addAttribute("days", daysOfMonth);
        model.addAttribute("weeks", daysOfWeek);
        model.addAttribute("month", MonthYearConverter.getMonth(monthYear));
        model.addAttribute("year", MonthYearConverter.getYear(monthYear));
        return "userFromDate";
    }

    @RequestMapping(value = {"/user/update"}, method = RequestMethod.GET)
    String update(Model model) throws ServerException {
        model.addAttribute("users", userService.update());
        return "users";
    }

    @RequestMapping(value ={"/user"}, method = RequestMethod.POST)
    String addUser(Model model, @ModelAttribute("SpringWeb") UserSaveDtoRequest user) throws ServerException {
        userService.save(user);
        model.addAttribute("users", userService.getAll());
        model.addAttribute("month", LocalDate.now().getMonthValue());
        model.addAttribute("year", LocalDate.now().getYear());
        return "getAllUser";
    }

    @RequestMapping(value = {"/user/{monthYear}"}, method = RequestMethod.POST)
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
