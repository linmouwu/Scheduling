package cmu.heinz.controller;

import cmu.heinz.model.TimeCycle;
import cmu.heinz.model.TimeCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
@Controller
public class TimeCycleController {


    @Autowired
    public TimeCycleRepository timeCycleRepository;

    @RequestMapping(value = "/deactivate_event", method = {RequestMethod.PUT, RequestMethod.GET})
    public ResponseEntity deactivateEvent(
            @RequestParam(value = "timeCycleId") Integer id) {

        try {

            timeCycleRepository.deactivateById(id);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.badRequest().build();

        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/add_time_cycle", method = {RequestMethod.POST})
    public ResponseEntity addTimeCycle(
            @RequestParam(value = "startDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate) {

        TimeCycle timeCycle = new TimeCycle();

        timeCycle.setStartDate(startDate);

        timeCycle.setEndDate(endDate);

        timeCycle.setActivated(true);

        timeCycleRepository.save(timeCycle);

        return ResponseEntity.ok().build();
    }

}
