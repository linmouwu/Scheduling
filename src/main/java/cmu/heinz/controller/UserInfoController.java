package cmu.heinz.controller;

import cmu.heinz.model.Officer;
import cmu.heinz.model.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
@Controller
public class UserInfoController {

    @Autowired
    private OfficerRepository officerRepository;

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public String getUserInfo(@RequestParam(value = "username") String username,
                              Model model) {

        System.out.println(username);
        Officer officer = officerRepository.findOne(username);

        if (officer != null) {
            model.addAttribute("officer", officer);
        }

        return "userinfo";

    }
}
