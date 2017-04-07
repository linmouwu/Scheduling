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
 * Home controller.
 * When user visit the home page, we needs to retrieve the basic user info
 * and all listing events.
 *
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Controller
public class HomeController {

    /**
     * Officer repository interface.
     */
    @Autowired
    private OfficerRepository officerRepository;

    /**
     * Union repository interface.
     */
    @Autowired
    private UnionRepository unionRepository;

    /**
     * Event repository interface.
     */
    @Autowired
    private EventRepository eventRepository;

    /**
     * Time Cycle interface.
     */
    @Autowired
    private TimeCycleRepository timeCycleRepository;

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

            // If the current user is a regular user.
            pendingEventList = (List<Event>) eventRepository.findByPendingUID(uid);
            previousEventList = (List<Event>) eventRepository.findByPreviousUID(uid);
        } else if (permissionGroup.equals("Administrator")) {

            // If the current user  is a administrator.
            pendingEventList = (List<Event>) eventRepository.findByPendingUnionID(unionID);
            previousEventList = (List<Event>) eventRepository.findByPreviousUnionID(unionID);
        } else if (permissionGroup.equals("Master Administrator")) {

            TimeCycle timeCycleActivated = timeCycleRepository.findActivate();

            model.addAttribute("activatedTimeCycle", timeCycleActivated);

            System.out.println("Current activated time cycle: " + timeCycleActivated.getId());

        } else if (permissionGroup.equals("Technical Administrator")) {

        } else {
            // Other users.
            pendingEventList = new ArrayList<Event>();
            previousEventList = new ArrayList<Event>();
        }

        model.addAttribute("officer", officer);

        System.out.println("PG: " + officer.getPermissionGroup());

        model.addAttribute("officerList", officerList);

        model.addAttribute("unionList", unionList);

        model.addAttribute("pendingEventList", pendingEventList);

        model.addAttribute("previousEventList", previousEventList);

        return "home";
    }


}