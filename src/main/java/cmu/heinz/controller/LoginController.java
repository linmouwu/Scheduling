package cmu.heinz.controller;

import cmu.heinz.model.Officer;
import cmu.heinz.model.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Controller
public class LoginController {

    @Autowired
    private OfficerRepository officerRepository;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        Model model) {

        Officer officer = officerRepository.findOne(username);

        if (officer != null) {
            model.addAttribute("officer", officer);
        }

        return "userinfo";

    }


}