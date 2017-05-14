package cmu.heinz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Login controller.
 */

@Controller
public class LoginController {

    /**
     * Defining login path and error path.
     * @param error
     * @return
     */
    @RequestMapping(value = {"/login", "/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("login", "error", error);
    }
}