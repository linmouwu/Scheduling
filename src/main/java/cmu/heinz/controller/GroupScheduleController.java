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
 * This is the controller for creating new event.
 */
@Controller
public class GroupScheduleController {


    /**
     * JPA repository instance for officer.
     */
    @Autowired
    private OfficerRepository officerRepository;


    /**
     * Group schedule repository
     */
    @Autowired
    private GroupScheduleRepository group_scheduleRepository;

    /**
     * Schedule officer repository
     */
    @Autowired
    private ScheduleOfficerRepository schedule_officerRepository;

    /**
     * Shift type repository
     */
    @Autowired
    private ShiftTypeRepository shiftTypeRepository;


    /**
     * Create group schedule
     *
     * @param type             schedule type
     * @param description      schedule description
     * @param selectedOfficers list of selected officers
     * @param startTime        schedule start time
     * @param endTime          end time
     * @return 200 OK if success
     */
    @RequestMapping(value = "/createGroupEvent", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity createGroupEvent(
            @RequestParam(value = "shift_type") String type,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "selectedOfficers[]") List<String> selectedOfficers,
            @RequestParam(value = "startTime[]") @DateTimeFormat(pattern = "MM/dd/yyyy") List<Date> startTime,
            @RequestParam(value = "endTime[]") @DateTimeFormat(pattern = "MM/dd/yyyy") List<Date> endTime) {

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
        ShiftType shiftType = shiftTypeRepository.findByName(type);
        int recruitId = Integer.valueOf(officer.getRecruitId());
        List<GroupSchedule> results = new ArrayList<GroupSchedule>();
        for (int i = 0; i < startTime.size(); i++) {
            GroupSchedule schedule = new GroupSchedule(recruitId, description, startTime.get(i), endTime.get(i), officer, union, shiftType, "appoved", selectedOfficers.size());

            schedule = group_scheduleRepository.save(schedule);
            for (String selectedOfficer : selectedOfficers) {
                ScheduleOfficer record = new ScheduleOfficer();
                record.setGroupSchedule(schedule);
                record.setOfficer(officerRepository.findByUID(selectedOfficer));
                schedule_officerRepository.save(record);
            }
            results.add(schedule);
        }


        return ResponseEntity.ok(results);
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
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endTime) {

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
        GroupSchedule schedule = group_scheduleRepository.findById(myId);


        //change schedule_officer records
        boolean[] visited = new boolean[selectedOfficers.size()];
        List<ScheduleOfficer> officers = schedule_officerRepository.findByScheduleId(myId);
        for (ScheduleOfficer each : officers) {
            int thisId = each.getOfficer().getId();
            String thisUid = each.getOfficer().getUid();

            if (!selectedOfficers.contains(thisUid)) {
                System.out.println("delete officer" + thisUid);
                schedule_officerRepository.delete(each);
            } else {
                visited[selectedOfficers.indexOf(thisUid)] = true;
            }
        }
        System.out.println("delete extra officers");
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                ScheduleOfficer record = new ScheduleOfficer();
                record.setOfficer(officerRepository.findByUID(selectedOfficers.get(i)));
                record.setGroupSchedule(schedule);
                schedule_officerRepository.save(record);

            }
        }
        System.out.println("create extra officers");
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        ShiftType shiftType = shiftTypeRepository.findByName(type);
        schedule.setShiftType(shiftType);
        System.out.println("what???");
        schedule.setSelectedOfficer(selectedOfficers.size());
        schedule.setDescription(description);
        System.out.println("update schedule");
        GroupSchedule updated_schedule = group_scheduleRepository.save(schedule);

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
        GroupSchedule schedule = group_scheduleRepository.findById(thisId);

        return ResponseEntity.ok(schedule);
    }

    /**
     * Get all events of one union.
     *
     * @param model session model of the event
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
        List<GroupSchedule> schedules = group_scheduleRepository.findByUnion(unionId);
        System.out.println("group schedules");
        System.out.println(schedules.toString());

        model.addAttribute("groupScheduleList", schedules);

        return ResponseEntity.ok("ok");
    }

    /**
     * Get all events of one union.
     *
     * @param model session model of the event
     * @return corresponding http response
     */
    @RequestMapping(value = "/deleteGroupSchedule", method = RequestMethod.POST)
    public ResponseEntity deleteGroupSchedule(@RequestParam(value = "id") Integer id,
                                              Model model) {
        int myId = Integer.valueOf(id);
        GroupSchedule schedule = group_scheduleRepository.findById(myId);


        //change schedule_officer records

        List<ScheduleOfficer> officers = schedule_officerRepository.findByScheduleId(myId);
        for (ScheduleOfficer each : officers) schedule_officerRepository.delete(each);
        group_scheduleRepository.delete(schedule);
        return ResponseEntity.ok("ok");
    }

}

