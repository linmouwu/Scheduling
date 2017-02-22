package cmu.heinz.controller;

import cmu.heinz.model.Officer;
import cmu.heinz.model.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Controller
public class LoginController {

    @Autowired
    private OfficerRepository officerRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

        System.out.println(name);

        Officer officer = officerRepository.findByLastName(name);

        String lastName = "defaultLastName";
        String firstName = "defaultFirstName";
        String gender = "defaultGender";

        if (officer != null) {

            System.out.println(officer);
            System.out.println(officer.getLastName());
            System.out.println(officer.getFirstName());
            System.out.println(officer.getGender());
            System.out.println(officer.getContractEmployee());
            System.out.println(officer.getHireDate());
            System.out.println(officer.getPromotionDate());
            System.out.println(officer.getSeniority());
            System.out.println(officer.getTrainerId());
            System.out.println(officer.getTitle());

            lastName = officer.getLastName();
            firstName = officer.getFirstName();
            gender = officer.getGender();

        }

        model.addAttribute("lastName", lastName);
        model.addAttribute("firstName", firstName);
        model.addAttribute("gender", gender);
        return "greeting";

    }
}