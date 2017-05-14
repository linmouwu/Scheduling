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
 * Controller to manage the time cycle.
 *
 */
@Controller
public class TimeCycleController {


    /**
     * Time cycle repository interface.
     */
    @Autowired
    public TimeCycleRepository timeCycleRepository;

    /**
     * Deactivate the current time cycle. Usually there is only one time cycle activated.
     *
     * @param id the time cycle to be deactivated.
     * @return 200 OK.
     */
    @RequestMapping(value = "/deactivate_event", method = {RequestMethod.PUT, RequestMethod.GET})
    public ResponseEntity deactivateEvent(
            @RequestParam(value = "timeCycleId") Integer id) {

        try {

            // Try to deactivate time cycle.
            timeCycleRepository.deactivateById(id);

        } catch (Exception e) {

            // If failed, return 400.
            e.printStackTrace();
            return ResponseEntity.badRequest().build();

        }

        return ResponseEntity.ok().build();
    }

    /**
     * Add new time cycle which can be populated to the whole application.
     *
     * @param startDate start date of current time cycle
     * @param endDate   end date of current time cycle
     * @return 200 OK
     */
    @RequestMapping(value = "/add_time_cycle", method = {RequestMethod.POST})
    public ResponseEntity addTimeCycle(
            @RequestParam(value = "startDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate) {

        // New time cycle object.
        TimeCycle timeCycle = new TimeCycle();

        timeCycle.setStartDate(startDate);

        timeCycle.setEndDate(endDate);

        // A new time cycle can be created only when no time cycle is activated,
        // thus the new time cycle is automatically activated.
        timeCycle.setActivated(true);

        timeCycleRepository.save(timeCycle);

        // 200 OK.
        return ResponseEntity.ok().build();
    }

}
