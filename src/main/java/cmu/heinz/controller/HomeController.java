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

    @Autowired
    private GroupScheduleRepository groupScheduleRepository;

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
     * Time cycle repository interface.
     */
    @Autowired
    private TimeCycleRepository timeCycleRepository;
    /**
     * Holiday Cycle interface.
     */
    @Autowired
    private HolidayRepository holidayRepository;

    /**
     * Shift type repository interface.
     */
    @Autowired
    private ShiftTypeRepository shiftTypeRepository;

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

        PermissionGroup permissionGroup = officer.getPermissionGroup();

        System.out.println("the id is " + uid);

        // List all users.
        List<Officer> officerList = new ArrayList<Officer>();

        // List all unions.
        List<Union> unionList = (List<Union>) unionRepository.findAll();

        // List all pending and previous events.
        List<Event> pendingEventList = new ArrayList<Event>();
        List<Event> previousEventList = new ArrayList<Event>();

        //List all holidays
        List<Holiday> holidayList = (List<Holiday>) holidayRepository.findAll();

        //List all shift type
        List<ShiftType> shiftTypeList = (List<ShiftType>) shiftTypeRepository.findAll();

        if (permissionGroup.getId() == 7) {

            // If the current user is a regular user.
            pendingEventList = eventRepository.findByPendingUID(uid);
            previousEventList = eventRepository.findByPreviousUID(uid);

        } else if (permissionGroup.getId() == 6) {

            // If the current user  is a administrator.
            int unionId = officer.getUnion().getId();
            List<GroupSchedule> schedules = groupScheduleRepository.findByUnion(unionId);
            officerList = (List<Officer>) officerRepository.findByUnion(unionId);

            model.addAttribute("groupScheduleList", schedules);
            pendingEventList = eventRepository.findByPendingUnionID(unionID);
            previousEventList = eventRepository.findByPreviousUnionID(unionID);

            shiftTypeList = shiftTypeRepository.getShiftTypeByUnionId(unionId);
        } else if (permissionGroup.getId() == 1) {

            // If the current user is a master administrator.
            TimeCycle timeCycleActivated = timeCycleRepository.findActivate();

            officerList = (List<Officer>) officerRepository.findAll();

            if (timeCycleActivated != null) {
                model.addAttribute("activatedTimeCycle", timeCycleActivated);
            } else {
                model.addAttribute("activatedTimeCycle");
            }

        } else if (permissionGroup.getId() == 2) {

            // TODO: if a current user is a master technician
            officerList = (List<Officer>) officerRepository.findAll();

        } else {
            // Other users.
            pendingEventList = new ArrayList<Event>();
            previousEventList = new ArrayList<Event>();
        }

        // Model attributes.
        model.addAttribute("officer", officer);

        model.addAttribute("officerList", officerList);

        model.addAttribute("unionList", unionList);

        model.addAttribute("holidayList", holidayList);

        model.addAttribute("pendingEventList", pendingEventList);

        model.addAttribute("previousEventList", previousEventList);

        model.addAttribute("shiftTypeList", shiftTypeList);

        System.out.println(shiftTypeList);

        return "home";
    }


}