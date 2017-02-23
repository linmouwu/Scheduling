package cmu.heinz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/login"})
    public String home() {
        return "login";
    }
}