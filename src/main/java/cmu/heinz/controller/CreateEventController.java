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
 * Created by nan xia on 3/5/17.
 * Andrew ID :nxia
 */
@Controller
public class CreateEventController {

    @Autowired
    private OfficerRepository officerRepository;

    @RequestMapping(value = "/createEvent", method = {RequestMethod.POST,RequestMethod.GET})
    public String createEvent(Model model) {
        return "createEvent";
    }
}
