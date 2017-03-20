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

import java.util.ArrayList;
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
        Officer officer = officerRepository.findByUID(username);
        String permissionGroup = officer.getPermissionGroup();
        String range = "";
        String status = "pending";
        if (permissionGroup.equals("User")) {
            range = "Single";
        } else if (permissionGroup.equals("Administrator")) {
            range = "Union";
            status = "unionevent";
        }

        int unionID = officer.getUnion().getId();
        int officerID = officer.getId();
        String uid = officer.getUid();
        int recruitId = Integer.valueOf(officer.getRecruitId());

        Event event = new Event();

        event.setOfficerId(officerID);
        event.setRecruitId(recruitId);
        event.setUnionId(unionID);
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

    @RequestMapping(value = "/update_Event", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity updateEvent(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "total") int totalDays,
            @RequestParam(value = "event_type") String type,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();
        Officer officer = officerRepository.findByUID(username);
        String permissionGroup = officer.getPermissionGroup();

        Event event = eventRepository.findByID(Integer.valueOf(id));

        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setEventType(type);
        event.setDescription(description);
//        System.out.println("the type" + " " + recruitID);
        Event newEvent = eventRepository.save(event);
        return ResponseEntity.ok(newEvent);
    }

    @RequestMapping(value = "/getEditEvent", method =RequestMethod.GET)
    public ResponseEntity getEvent(@RequestParam(value = "id") String id,
                              Model model) {

        Event event = eventRepository.findByID(Integer.valueOf(id));

        if (event != null) {
            model.addAttribute("currentEditEvent", event);
        }
        return ResponseEntity.ok(event);
    }

    @RequestMapping(value = "/allEvent", method = RequestMethod.GET)
    public ResponseEntity getAllEvent(@RequestParam(value = "union_id") int union_id,
                                      Model model) {

        List<Event> allEvent = eventRepository.findByAllUnionID(union_id);
        List<CurrentEvent> allCurrentEvent = new ArrayList<CurrentEvent>();
        if (allEvent != null) {
            for (Event e : allEvent) {
                CurrentEvent cur = new CurrentEvent(e.getId(), e.getDescription(), e.getStartTime(),e.getEndTime());
                allCurrentEvent.add(cur);
            }
            model.addAttribute("allEvent", allCurrentEvent);
        }
        return ResponseEntity.ok(allCurrentEvent);
    }
}

