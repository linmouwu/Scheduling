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
 * Holiday controller.
 */
@Controller
public class HolidayController {

    /**
     * Union holiday repository
     */
    @Autowired
    private UnionHolidayRepository unionHolidayRepository;

    /**
     * Officer repository
     */
    @Autowired
    private OfficerRepository officerRepository;

    /**
     * Holiday repository
     */
    @Autowired
    private HolidayRepository holidayRepository;

    /**
     * Union Repository
     */
    @Autowired
    private UnionRepository unionRepository;

    /**
     * Get all events of one union.
     *
     * @param union_id union to visit
     * @param model    session model of the event
     * @return corresponding http response
     */
    @RequestMapping(value = "/allHoliday", method = RequestMethod.GET)
    public ResponseEntity getAllHoliday(@RequestParam(value = "union_id") int union_id,
                                        Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String username = userDetails.getUsername();

        // Fetch user details by username(UID).
        Officer officer = officerRepository.findByUID(username);

        List<UnionHoliday> allHoliday = unionHolidayRepository.findByUnionID(union_id);

        List<CurrentEvent> allCurrentHoliday = new ArrayList<CurrentEvent>();

        if (allHoliday != null) {
            for (UnionHoliday e : allHoliday) {
                CurrentEvent cur = new CurrentEvent(e.getId(), e.getHoliday().getDescription(), e.getHoliday().getDate(), e.getHoliday().getDate());
                allCurrentHoliday.add(cur);
            }
            model.addAttribute("allHoliday", allCurrentHoliday);
        }

        return ResponseEntity.ok(allCurrentHoliday);
    }

    /**
     * Assign new holidays to union
     *
     * @param union_id        union id
     * @param selectedHoliday selected holiday
     * @param model           model
     * @return 200
     */
    @RequestMapping(value = "/assignHoliday", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity addHoliday(@RequestParam(value = "unionId") int union_id, @RequestParam(value = "selectedHoliday[]") List<Integer> selectedHoliday,
                                     Model model) {
        for (Integer holidayId : selectedHoliday) {
            UnionHoliday uh = new UnionHoliday();
            Union union = unionRepository.findOne(union_id);
            Holiday holiday = holidayRepository.findOne(holidayId);
            uh.setHoliday(holiday);
            uh.setUnion(union);
            unionHolidayRepository.save(uh);
        }
        return ResponseEntity.ok(200);
    }

    /**
     * Update holiday
     *
     * @param dateList date list for the holiday
     * @param model    model
     * @return 200
     */
    @RequestMapping(value = "/updateHolidayDate", method = {RequestMethod.POST})
    public ResponseEntity updateHolidayDate(@RequestParam(value = "dateList[]") @DateTimeFormat(pattern = "yyyy-MM-dd") List<Date> dateList,
                                            Model model) {
        List<Holiday> holidayList = (List<Holiday>) holidayRepository.findAll();
        for (int i = 0; i < dateList.size(); i++) {
            holidayList.get(i).setDate(dateList.get(i));
            System.out.println(dateList.get(i));
            holidayRepository.save(holidayList.get(i));
        }
        List<Holiday> updateHolidayList = (List<Holiday>) holidayRepository.findAll();
        return ResponseEntity.ok(updateHolidayList);
    }
}
