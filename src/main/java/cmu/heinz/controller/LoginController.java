package cmu.heinz.controller;

import cmu.heinz.model.User;
import cmu.heinz.model.UserRepository;
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
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

        User user = userRepository.findOne(name);

        System.out.println(user.getId());
        System.out.println(name);

        String lastName = user.getLastName();
        String firstName = user.getFirstName();

        model.addAttribute("lastName", lastName);
        model.addAttribute("firstNa me", firstName);

        System.out.println(lastName);
        return "greeting";

    }
}