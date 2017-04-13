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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

/**
 * This is the controller for creating new event.
 * Created by nan xia on 3/5/17.
 * Andrew ID :nxia
 */
@Controller
public class CreateEventController {

    /**
     * JPA repository instance for event.
     */
    @Autowired
    private EventRepository eventRepository;

    /**
     * JPA repository instance for officer.
     */
    @Autowired
    private OfficerRepository officerRepository;
    @Autowired
    private Group_ScheduleRepository group_scheduleRepository;
    @Autowired
    private Schedule_OfficerRepository schedule_officerRepository;
    /**
     * Create event methods.
     *
     * @param totalDays   total days of this event
     * @param type        type of this event
     * @param description detailed description
     * @param startTime   start time
     * @param endTime     end time
     * @return corresponding http response
     */
    @RequestMapping(value = "/create_Event", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity createEvent(
            @RequestParam(value = "total") int totalDays,
            @RequestParam(value = "event_type") String type,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {

        // Retrieve the current log-in user.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Retrieve user name.
        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Find officer.
        Officer officer = officerRepository.findByUID(username);

        // Officer infos.
        PermissionGroup permissionGroup = officer.getPermissionGroup();

        int unionID = officer.getUnion().getId();

        int officerID = officer.getId();

        String uid = officer.getUid();

        int recruitId = Integer.valueOf(officer.getRecruitId());

        // Range of the request event.
        String range = "";

        String status = "pending";

        // Verify user permission group.
        if (permissionGroup.getId() == 7) {
            range = "Single";
        } else if (permissionGroup.getId() == 6) {
            range = "Union";
            status = "unionevent";
        }

        // New event instance.
        Event event = new Event();

        // Save the new event.
        event.setOfficerId(officerID);
        event.setRecruitId(recruitId);
        event.setUnionId(unionID);
        event.setUid(uid);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setEventStatus(status);
        event.setEventType(type);
        event.setDescription(description);
        Event newEvent = eventRepository.save(event);


        return ResponseEntity.ok(newEvent);
    }

    /**
     * Update the existing event.
     *
     * @param id          event id to be updated
     * @param totalDays   totaldays to be updated
     * @param type        new event type
     * @param status      new event status
     * @param description new description
     * @param startTime   new start time
     * @param endTime     new end time
     * @return corresponding http response
     */
    @RequestMapping(value = "/update_Event", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity updateEvent(
            @RequestParam(value = "edit_id") String id,
            @RequestParam(value = "total") int totalDays,
            @RequestParam(value = "event_type") String type,
            @RequestParam(value = "event_status") String status,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {

        // User evidence.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Find user.
        Officer officer = officerRepository.findByUID(username);

        // User infos.
        PermissionGroup permissionGroup = officer.getPermissionGroup();

        // Get event infos.
        Event event = eventRepository.findByID(Integer.valueOf(id));

        // Update event based on user permission group.
        if (officer.getPermissionGroup().getId() == 6) {
            event.setEventStatus(status);
        } else if (officer.getPermissionGroup().getId() == 7) {
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            event.setEventType(type);
            event.setDescription(description);
        }
        Event newEvent = eventRepository.save(event);

        return ResponseEntity.ok(newEvent);
    }

    /**
     * Get infos of the specific event.
     *
     * @param id    id of the event
     * @param model session model of the event
     * @return corresponding http response
     */
    @RequestMapping(value = "/getEditEvent", method = RequestMethod.GET)
    public ResponseEntity getEvent(@RequestParam(value = "id") String id,
                                   Model model) {

        // Find event by given id.
        Event event = eventRepository.findByID(Integer.valueOf(id));

        // Set session value.
        if (event != null) {
            model.addAttribute("currentEditEvent", event);
        }

        return ResponseEntity.ok(event);
    }

    /**
     * Get all events of one union.
     *
     * @param union_id union to visit
     * @param model    session model of the event
     * @return corresponding http response
     */
    @RequestMapping(value = "/allEvent", method = RequestMethod.GET)
    public ResponseEntity getAllEvent(@RequestParam(value = "union_id") int union_id,
                                      Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Fetch user details by username(UID).
        Officer officer = officerRepository.findByUID(username);
        // Find special events by officer id.
        int officerid = officer.getId();
        List<Event> allEvent = eventRepository.findByOfficerID(officerid);

        List<Schedule_Officer> groupSchedule = schedule_officerRepository.findByOfficer(officerid);

        List<CurrentEvent> allCurrentEvent = new ArrayList<CurrentEvent>();

        if (allEvent != null) {
            for (Event e : allEvent) {
                CurrentEvent cur = new CurrentEvent(e.getId(), e.getDescription(), e.getStartTime(), e.getEndTime());
                allCurrentEvent.add(cur);
            }
            model.addAttribute("allEvent", allCurrentEvent);
        }
        if (groupSchedule != null) {
            for (Schedule_Officer s : groupSchedule) {
                CurrentEvent cur = new CurrentEvent(s.getId(),s.getGroupSchedule().getDescription(),s.getGroupSchedule().getStartTime(),s.getGroupSchedule().getEndTime());
                allCurrentEvent.add(cur);
            }
        }
        return ResponseEntity.ok(allCurrentEvent);
    }

@RequestMapping(value = "/getOfficerNumber", method = RequestMethod.GET)
    public ResponseEntity getOfficeNumber(@RequestParam(value = "date") Date start_date, @RequestParam(value = "union_id") int union_id, @RequestParam(value="shiftType") String shiftType, Model model) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sd = df.format(start_date);
        String rst = "ShiftType:" + shiftType + "\n";
        for (int i = 0; i < 7; i++) {
            Date curDate = addDays(start_date, i);
            String cd = df.format(curDate);
            System.out.println(cd);
            Integer next = eventRepository.findByAllDate(union_id, cd, shiftType);
            Integer groupNext = group_scheduleRepository.findByAllDate(union_id,cd,shiftType);
            if (groupNext != null) {
                next += groupNext;
            }
            System.out.println(next);
            rst += cd + " " + next + "\n";
        }
        return ResponseEntity.ok(rst);
    }
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}

