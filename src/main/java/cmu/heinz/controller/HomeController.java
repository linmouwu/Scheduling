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
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "/home", method = {RequestMethod.POST, RequestMethod.GET})
    public String home(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        Officer officer = officerRepository.findByUID(username);

        int id = officer.getId();
        System.out.println(id);

        String uid = officer.getUid();
        String unionID = "1";
        String permissionGroup = officer.getPermissionGroup();
        System.out.println("the id is " + uid);
        List<Officer> officerList = (List<Officer>) officerRepository.findAll();

        List<Union> unionList = (List<Union>) unionRepository.findAll();
        List<Event> pendingEventList = new ArrayList<Event>();
        List<Event> previousEventList = new ArrayList<Event>();

        if (permissionGroup.equals("User")) {
            pendingEventList = (List<Event>)eventRepository.findByPendingUID(uid);
            previousEventList = (List<Event>) eventRepository.findByPreviousUID(uid);
        } else if (permissionGroup.equals("Administrator")) {
            pendingEventList = (List<Event>)eventRepository.findByPendingUnionID(unionID);
            previousEventList = (List<Event>) eventRepository.findByPreviousUnionID(unionID);
        }
//        List<Event> pendingEventList = (List<Event>)eventRepository.findByPendingUID(uid);

        model.addAttribute("officer", officer);

        model.addAttribute("officerList", officerList);

        model.addAttribute("unionList", unionList);

        model.addAttribute("pendingEventList", pendingEventList);

        model.addAttribute("previousEventList", previousEventList);

        return "home";
    }


}