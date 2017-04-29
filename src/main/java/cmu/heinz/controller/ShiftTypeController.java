package cmu.heinz.controller;

import cmu.heinz.model.Officer;
import cmu.heinz.model.OfficerRepository;
import cmu.heinz.model.ShiftType;
import cmu.heinz.model.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Controller
public class ShiftTypeController {

    @Autowired
    OfficerRepository officerRepository;

    @Autowired
    ShiftTypeRepository shiftTypeRepository;

    @RequestMapping(value = "/add_shift_type", method = RequestMethod.POST)
    public ResponseEntity addShiftType(@RequestParam(value = "shiftTypeName") String shiftTypeName,
                                       @RequestParam(value = "startTime") String startTime,
                                       @RequestParam(value = "endTime") String endTime,
                                       @RequestParam(value = "description") String description) {

        ShiftType shiftType = new ShiftType();

        try {

            // User log in evidence.
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            UserDetails userDetails = (UserDetails) principal;

            String username = userDetails.getUsername();

            // Fetch user details by username(UID).
            Officer officer = officerRepository.findByUID(username);

            shiftType = new ShiftType();

            DateFormat formatter = new SimpleDateFormat("HH:mm");

            shiftType.setShiftName(shiftTypeName);
            shiftType.setStartTime(new Time(formatter.parse(startTime).getTime()));
            shiftType.setEndTime(new Time(formatter.parse(endTime).getTime()));
            shiftType.setDescription(description);
            shiftType.setUnion(officer.getUnion());

            shiftTypeRepository.save(shiftType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(shiftType);
    }

    @RequestMapping(value = "/remove_shift_type", method = {RequestMethod.GET})
    public ResponseEntity removeShiftType(
            @RequestParam(value = "shiftTypeId") Integer id) {

        try {

            // Try to deactivate time cycle.
            shiftTypeRepository.removeShiftType(id);

        } catch (Exception e) {

            // If failed, return 400.
            e.printStackTrace();
            return ResponseEntity.badRequest().build();

        }

        return ResponseEntity.ok().build();
    }

}
