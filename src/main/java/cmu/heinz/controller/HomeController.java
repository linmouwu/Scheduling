package cmu.heinz.controller;

import cmu.heinz.model.Officer;
import cmu.heinz.model.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Controller
public class HomeController {

    @Autowired
    private OfficerRepository officerRepository;

    @RequestMapping(value = "/home", method = {RequestMethod.POST, RequestMethod.GET})
    public String home(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        Officer officer = officerRepository.findOne(username);

        model.addAttribute("officer", officer);

        return "home";
    }


}