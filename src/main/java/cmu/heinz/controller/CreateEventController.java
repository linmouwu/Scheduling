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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This is the controller for creating new event.
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

    /**
     * Group schedule repository.
     */
    @Autowired
    private GroupScheduleRepository group_scheduleRepository;

    /**
     * Schedule officer repository.
     */
    @Autowired
    private ScheduleOfficerRepository schedule_officerRepository;

    /**
     * Shift type repository.
     */
    @Autowired
    private ShiftTypeRepository shiftTypeRepository;

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
            @RequestParam(value = "shift_type") int shiftTypeId,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endTime) {

        // Retrieve the current log-in user.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Retrieve user name.
        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Find officer.
        Officer officer = officerRepository.findByUID(username);

        // Officer infos.
        PermissionGroup permissionGroup = officer.getPermissionGroup();

        //Get union ID
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
            range = "Single";
            status = "approved";
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
        event.setShiftType(shiftTypeRepository.findOne(shiftTypeId));
        Event newEvent = eventRepository.save(event);

        return ResponseEntity.ok(newEvent);
    }

    /**
     * Update the existing event.
     *
     * @param id          event id to be updated
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
            @RequestParam(value = "event_type") String type,
            @RequestParam(value = "event_status") String status,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endTime) {

        // User evidence.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Find user.
        Officer officer = officerRepository.findByUID(username);

        // Get event infos.
        Event event = eventRepository.findByID(Integer.valueOf(id));

        // Update event based on user permission group.
        if (officer.getPermissionGroup().getId() <= 6) {
            event.setEventStatus(status);
        } else if (officer.getPermissionGroup().getId() >= 7) {
            System.out.println(status);
            System.out.println(event.getEventStatus());
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            event.setEventType(type);
            event.setEventStatus(status);
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
     * Get all events of one union by current shift type.
     *
     * @param shiftType union to visit
     * @param model     session model of the event
     * @return corresponding http response
     */
    @RequestMapping(value = "/allShiftTypeEvent", method = RequestMethod.GET)
    public ResponseEntity getAllShiftEvent(@RequestParam(value = "shiftType") int shiftType,
                                           Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Fetch user details by username(UID).
        Officer officer = officerRepository.findByUID(username);

        // Find special events by officer id.
        int officerid = officer.getId();

        int union_id = officer.getUnion().getId();

        // Get the current permission group.

        String permissionGroup = officer.getPermissionGroup().getRole();

        List<Event> allEvent = new ArrayList<Event>();

        //get all events from current shift type and union.
        allEvent = eventRepository.findByAllUnionIDAndShift(union_id, shiftType);

        List<GroupSchedule> groupSchedule = group_scheduleRepository.findByAllDateUnionShift(union_id, shiftType);

        List<CurrentEvent> allCurrentEvent = new ArrayList<CurrentEvent>();

        if (allEvent != null) {
            for (Event e : allEvent) {
                String description = e.getUid() + " : " + e.getDescription();
                CurrentEvent cur = new CurrentEvent(e.getId(), description, e.getStartTime(), e.getEndTime());
                allCurrentEvent.add(cur);
            }
            model.addAttribute("allShiftEvent", allCurrentEvent);
        }
        if (groupSchedule != null) {
            for (GroupSchedule s : groupSchedule) {
                CurrentEvent cur = new CurrentEvent(s.getId(), s.getDescription(), s.getStartTime(), s.getEndTime());
                allCurrentEvent.add(cur);
            }
        }
        return ResponseEntity.ok(allCurrentEvent);
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

        String permissionGroup = officer.getPermissionGroup().getRole();

        List<Event> allEvent = new ArrayList<Event>();

        // find all events in the current group.
        allEvent = eventRepository.findByAllUnionID(union_id);

        // find all group schedule.
        List<ScheduleOfficer> groupSchedule = schedule_officerRepository.findByOfficer(officerid);

        List<CurrentEvent> allCurrentEvent = new ArrayList<CurrentEvent>();

        if (allEvent != null) {
            for (Event e : allEvent) {
                String description = e.getDescription();
                CurrentEvent cur = new CurrentEvent(e.getId(), description, e.getStartTime(), e.getEndTime());
                allCurrentEvent.add(cur);
            }
            model.addAttribute("allEvent", allCurrentEvent);
        }
        if (groupSchedule != null) {
            for (ScheduleOfficer s : groupSchedule) {
                CurrentEvent cur = new CurrentEvent(s.getId(), s.getGroupSchedule().getDescription(), s.getGroupSchedule().getStartTime(), s.getGroupSchedule().getEndTime());
                allCurrentEvent.add(cur);
            }
        }
        return ResponseEntity.ok(allCurrentEvent);
    }

    /**
     * Get all events for current union.
     *
     * @param union_id the union id
     * @param model    the data model
     * @return 200 success
     */
    @RequestMapping(value = "/allUnionEvent", method = RequestMethod.GET)
    public ResponseEntity getAllUnionEvent(@RequestParam(value = "union_id") int union_id,
                                           Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Fetch user details by username(UID).
        Officer officer = officerRepository.findByUID(username);

        // Find special events by officer id.
        int officerid = officer.getId();

        // get the permission group of the current officer.
        String permissionGroup = officer.getPermissionGroup().getRole();

        // get all individual events for current officer.
        List<Event> allEvent = eventRepository.findByOfficerID(officerid);

        // get all group schedule event for the current officer.
        List<ScheduleOfficer> groupSchedule = schedule_officerRepository.findByOfficer(officerid);

        // add all events into one list.
        List<CurrentEvent> allCurrentEvent = new ArrayList<CurrentEvent>();


        if (allEvent != null) {
            for (Event e : allEvent) {
                //add description for the event.
                String description = officer.getUid() + " : " + e.getDescription();
                CurrentEvent cur = new CurrentEvent(e.getId(), description, e.getStartTime(), e.getEndTime());
                allCurrentEvent.add(cur);
            }

        }
        if (groupSchedule != null) {
            for (ScheduleOfficer s : groupSchedule) {
                String description = s.getGroupSchedule().getSelectedOfficer() + " Officers : " + s.getGroupSchedule().getDescription();
                CurrentEvent cur = new CurrentEvent(s.getId(), description, s.getGroupSchedule().getStartTime(), s.getGroupSchedule().getEndTime());
                allCurrentEvent.add(cur);
            }
        }
        model.addAttribute("allIndividualEvent", allCurrentEvent);
        return ResponseEntity.ok(allCurrentEvent);
    }

    /**
     * Get the number of officer for current shift in this union
     *
     * @param start_date start date
     * @param union_id   union id
     * @param shiftType  shift type
     * @param model      data model
     * @return the successful information
     */
    @RequestMapping(value = "/getOfficerNumber", method = RequestMethod.GET)
    public ResponseEntity getOfficeNumber(@RequestParam(value = "date") Date start_date, @RequestParam(value = "union_id") int union_id, @RequestParam(value = "shiftType") int shiftType, Model model) {
        //get the date.
        Calendar c = Calendar.getInstance();
        c.setTime(start_date);
        c.add(Calendar.DATE, 1);
        Date dt = c.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sd = df.format(dt);
        String rst = "ShiftType:" + shiftType + "\n";
        // get how many officers currently have schedule events.
        int off = eventRepository.findAllByDate(union_id, sd, shiftType);
        Integer groupOff = group_scheduleRepository.findAllByDate(union_id, sd, shiftType);
        if (groupOff != null) {
            off += groupOff;
        }
        rst += sd + " Leave Officer Number: " + off;
        return ResponseEntity.ok(rst);
    }

    /**
     * add days to the group schedule
     *
     * @param date the date to add
     * @param days numbers of days
     * @return Date
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}

