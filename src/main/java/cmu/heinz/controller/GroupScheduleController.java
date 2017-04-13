package cmu.heinz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Controller
public class GroupScheduleController {

    @RequestMapping(value = "/add_group_schedule", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity scheduleRequestList(@RequestParam(value = "startDate") Date startDate,
                                              @RequestParam(value = "endDate") Date endDate,
                                              @RequestParam(value = "shiftType") String shiftType,
                                              @RequestParam(value = "userList") List<String> userList) {



        return ResponseEntity.ok().build();

    }
}
