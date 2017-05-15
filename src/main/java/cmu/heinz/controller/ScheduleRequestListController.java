package cmu.heinz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Schedule request list controller.
 * List the schedule requests of the user.
 *
 */

@Controller
public class ScheduleRequestListController {

    /**
     * Get the schedule request list
     * @return schedule request page
     */
    @RequestMapping(value = "/schedule_request_list", method = RequestMethod.GET)
    public String scheduleRequestList() {
        return "schedule_request_list";
    }

}
