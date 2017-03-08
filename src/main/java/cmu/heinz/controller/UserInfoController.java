package cmu.heinz.controller;

import cmu.heinz.model.Officer;
import cmu.heinz.model.OfficerRepository;
import cmu.heinz.model.UnionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
@Controller
public class UserInfoController {

    @Autowired
    private OfficerRepository officerRepository;

    @Autowired
    private UnionRepository unionRepository;

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public String getUserInfo(@RequestParam(value = "id") String id,
                              Model model) {

        Officer officer = officerRepository.findOne(id);

        if (officer != null) {
            model.addAttribute("officer", officer);
        }

        return "userinfo";

    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ResponseEntity addUserInfo(@RequestParam(value = "uid") String uid,
                                      @RequestParam(value = "lastName") String lastName,
                                      @RequestParam(value = "firstName") String firstName,
                                      @RequestParam(value = "badgeNum") String badgeNum,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "gender") String gender,
                                      @RequestParam(value = "seniority") int seniority,
                                      @RequestParam(value = "permissionGroup") String permissionGroup,
                                      @RequestParam(value = "union") String union,
                                      @RequestParam(value = "recruit") String recruit,
                                      @RequestParam(value = "contractEmployee") String contractEmployee,
                                      @RequestParam(value = "hireDate") Date hireDate,
                                      @RequestParam(value = "promoteDate") Date promoteDate,
                                      @RequestParam(value = "trainer") String trainer) {

        Officer officer = new Officer();
        officer.setUid(uid);
        officer.setLastName(lastName);
        officer.setFirstName(firstName);
        officer.setBadgeNum(badgeNum);
        officer.setTitle(title);
        officer.setGender(gender);
        officer.setPermissionGroup(permissionGroup);
        officer.setUnion(unionRepository.findOne(union));
        officer.setRecruitId(recruit);
        officer.setSeniority(seniority);
        officer.setContractEmployee(contractEmployee);
        officer.setHireDate(hireDate);
        officer.setPromotionDate(promoteDate);
        if (trainer != null && !trainer.isEmpty() && !trainer.equals("")) {
            officer.setTrainer(officerRepository.findByUID(trainer));
        }

        Officer newOfficer = officerRepository.save(officer);

        return ResponseEntity.ok(newOfficer);
    }
}
