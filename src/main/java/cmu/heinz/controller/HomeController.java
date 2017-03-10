package cmu.heinz.controller;

import cmu.heinz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Controller
public class HomeController {

    @Autowired
    private OfficerRepository officerRepository;

    @Autowired
    private UnionRepository unionRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @RequestMapping(value = "/home", method = {RequestMethod.POST, RequestMethod.GET})
    public String home(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        Officer officer = officerRepository.findOne(username);

        List<Officer> officerList = (List<Officer>) officerRepository.findAll();

        List<Union> unionList = (List<Union>) unionRepository.findAll();

        model.addAttribute("officer", officer);

        model.addAttribute("officerList", officerList);

        model.addAttribute("unionList", unionList);

        return "home";
    }


}