package cmu.heinz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Schedule controller.
 * Routing the schedule pages.
 * Created by nan xia on 3/5/17.
 * Andrew ID :nxia
 */
@Controller
public class ScheduleController {

    /**
     * @param model session model
     * @return schedule page
     */
    @RequestMapping(value = "/calendar", method = {RequestMethod.POST, RequestMethod.GET})
    public String calendar(Model model) {
        return "calendar";
    }
}
