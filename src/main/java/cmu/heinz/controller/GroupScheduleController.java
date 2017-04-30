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
 * Created by rui tian on 4/19/17.
 * Andrew ID :rtian
 */
@Controller
public class GroupScheduleController {


    /**
     * JPA repository instance for officer.
     */
    @Autowired
    private OfficerRepository officerRepository;
    @Autowired
    private Group_ScheduleRepository group_scheduleRepository;
    @Autowired
    private Schedule_OfficerRepository schedule_officerRepository;



    @RequestMapping(value = "/createGroupEvent", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity createGroupEvent(
            @RequestParam(value = "shift_type") String type,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "selectedOfficers[]") List<String> selectedOfficers,
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

        Union union = officer.getUnion();

        String uid = officer.getUid();

        int recruitId = Integer.valueOf(officer.getRecruitId());

//
//
//        // Verify user permission group.
//        if (permissionGroup.getId() == 7) {
//            range = "Single";
//        } else if (permissionGroup.getId() == 6) {
//            range = "Union";
//            status = "unionevent";
//        }

        // New event instance.
        Group_Schedule schedule = new Group_Schedule(recruitId, description, startTime, endTime, officer, union, type, "pending", selectedOfficers.size());
        schedule = group_scheduleRepository.save(schedule);
        for(String selectedOfficer : selectedOfficers) {
            Schedule_Officer record = new Schedule_Officer();
            record.setGroupSchedule(schedule);
            record.setOfficer(officerRepository.findByUID(selectedOfficer));
            schedule_officerRepository.save(record);
        }

        return ResponseEntity.ok(schedule);
    }

    /**
     * Update the existing event.
     *
     * @param id          event id to be updated
     * @param type        new event type
     * @param description new description
     * @param startTime   new start time
     * @param endTime     new end time
     * @return corresponding http response
     */
    @RequestMapping(value = "/update_group_schedule", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity updateGroupSchedule(
            @RequestParam(value = "scheduleId") String id,
            @RequestParam(value = "shiftType") String type,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "selectedOfficers[]") List<String> selectedOfficers,
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {

        // User evidence.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Find user.
        Officer admin = officerRepository.findByUID(username);

        // User infos.
        PermissionGroup permissionGroup = admin.getPermissionGroup();

        //save new schedule
        int myId = Integer.valueOf(id);
        Group_Schedule schedule = group_scheduleRepository.findById(myId);


        //change schedule_officer records
        boolean[] visited = new boolean[selectedOfficers.size()];
        List<Schedule_Officer> officers = schedule_officerRepository.findByScheduleId(myId);
        for(Schedule_Officer each : officers) {
            int thisId = each.getOfficer().getId();
            String thisUid = each.getOfficer().getUid();

            if(!selectedOfficers.contains(thisUid)) {
                System.out.println("delete officer" + thisUid);
                schedule_officerRepository.delete(each);
            } else {
                visited[selectedOfficers.indexOf(thisUid)] = true;
            }
        }
        System.out.println("delete extra officers");
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i]) {
                Schedule_Officer record = new Schedule_Officer();
                record.setOfficer(officerRepository.findByUID(selectedOfficers.get(i)));
                record.setGroupSchedule(schedule);
                schedule_officerRepository.save(record);

            }
        }
        System.out.println("create extra officers");
//        if (admin.getPermissionGroup().getId() == 6) {
//            //schedule.setStatus(status);
//        } else if (admin.getPermissionGroup().getId() == 7) {
//            schedule.setStartTime(startTime);
//            schedule.setEndTime(endTime);
//            schedule.setShiftType(type);
//            System.out.println("what???");
//            schedule.setSelected_Officer(selectedOfficers.size());
//            schedule.setDescription(description);
//        }
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setShiftType(type);
        System.out.println("what???");
        schedule.setSelected_Officer(selectedOfficers.size());
        schedule.setDescription(description);
        System.out.println("update schedule");
        Group_Schedule updated_schedule = group_scheduleRepository.save(schedule);

        return ResponseEntity.ok(updated_schedule);
    }

    /**
     * Get infos of the specific event.
     *
     * @param id    id of the event
     * @param model session model of the event
     * @return corresponding http response
     */
    @RequestMapping(value = "/getEditSchedule", method = RequestMethod.GET)
    public ResponseEntity getSchedule(@RequestParam(value = "id") Integer id,
                                   Model model) {

        // Find event by given id.
        int thisId = id;
        Group_Schedule schedule = group_scheduleRepository.findById(thisId);

        // Set session value.
//        if (schedule != null) {
//            model.addAttribute("currentEditSchedule", schedule);
//        }


        return ResponseEntity.ok(schedule);
    }

    /**
     * Get all events of one union.
     *
     * @param model    session model of the event
     * @return corresponding http response
     */
    @RequestMapping(value = "/allGroupSchedule", method = RequestMethod.GET)
    public ResponseEntity getGroupSchedule(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Fetch user details by username(UID).
        Officer officer = officerRepository.findByUID(username);
        int unionId = officer.getUnion().getId();
        List<Group_Schedule> schedules = group_scheduleRepository.findByUnion(unionId);
        System.out.println("group schedules");
        System.out.println(schedules.toString());

        model.addAttribute("groupScheduleList",schedules);

        return ResponseEntity.ok("ok" );
    }

}

