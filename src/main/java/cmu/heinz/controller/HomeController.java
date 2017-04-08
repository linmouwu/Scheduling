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

        // User log in evidence.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Fetch user details by username(UID).
        Officer officer = officerRepository.findByUID(username);

        int id = officer.getId();
        System.out.println(id);

        // User details.
        String uid = officer.getUid();

        String unionID = "1";

        String permissionGroup = officer.getPermissionGroup();

        System.out.println("the id is " + uid);

        // List all users.
        List<Officer> officerList = (List<Officer>) officerRepository.findAll();

        // List all unions.
        List<Union> unionList = (List<Union>) unionRepository.findAll();

        // List all pending and previous events.
        List<Event> pendingEventList = new ArrayList<Event>();
        List<Event> previousEventList = new ArrayList<Event>();

        if (permissionGroup.equals("User")) {

            // If the current user is a regular user.
            pendingEventList = eventRepository.findByPendingUID(uid);
            previousEventList = eventRepository.findByPreviousUID(uid);

        } else if (permissionGroup.equals("Administrator")) {

            // If the current user  is a administrator.
            pendingEventList = eventRepository.findByPendingUnionID(unionID);
            previousEventList = eventRepository.findByPreviousUnionID(unionID);

        } else if (permissionGroup.equals("Master Administrator")) {

            // If the current user is a master administrator.
            TimeCycle timeCycleActivated = timeCycleRepository.findActivate();

            if (timeCycleActivated != null) {

                model.addAttribute("activatedTimeCycle", timeCycleActivated);

            } else {

                model.addAttribute("activatedTimeCycle");

            }

        } else if (permissionGroup.equals("Master Technician")) {

            // TODO: if a current user is a master technician
        } else {
            // Other users.
            pendingEventList = new ArrayList<Event>();
            previousEventList = new ArrayList<Event>();
        }

        // Model attributes.
        model.addAttribute("officer", officer);

        model.addAttribute("officerList", officerList);

        model.addAttribute("unionList", unionList);

        model.addAttribute("pendingEventList", pendingEventList);

        model.addAttribute("previousEventList", previousEventList);

        return "home";
    }


}