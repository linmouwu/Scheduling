package cmu.heinz.controller;

import cmu.heinz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by nan xia on 3/5/17.
 * Andrew ID :nxia
 */
@Controller
public class CreateEventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OfficerRepository officerRepository;

    @RequestMapping(value = "/create_Event", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity createEvent(
            @RequestParam(value = "total") int totalDays,
            @RequestParam(value = "event_type") String type,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();
        Officer officer = officerRepository.findOne(username);
        String permissionGroup = officer.getPermissionGroup();
        String range = "";
        String status = "pending";
        if (permissionGroup.equals("User")) {
            range = "Single";
        } else if (permissionGroup.equals("Administrator")) {
            range = "Union";
            status = "unionevent";
        }

        String unionID = "1";

        String uid = officer.getUid();
        int recruitId = Integer.valueOf(officer.getRecruitId());

        Event event = new Event();

        event.setOfficerId(2);
        event.setRecruitId(recruitId);
        event.setUnionId(1);
        event.setUid(uid);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setEventStatus(status);
        event.setEventType(type);
        event.setDescription(description);
//        System.out.println("the type" + " " + recruitID);
        Event newEvent = eventRepository.save(event);
        return ResponseEntity.ok(newEvent);
    }

    @RequestMapping(value = "/editEvent", method = RequestMethod.GET)
    public ResponseEntity getEvent(@RequestParam(value = "id") int id,
                              Model model) {

        Event event = eventRepository.findByID(id);

        if (event != null) {
            model.addAttribute("currentEvent", event);
        }
        return ResponseEntity.ok(event);

    }
}

