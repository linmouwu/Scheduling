package cmu.heinz.controller;

import cmu.heinz.model.Officer;
import cmu.heinz.model.OfficerRepository;
import cmu.heinz.model.UnionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public String getUserInfo(@RequestParam(value = "id") int id,
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
                                      @RequestParam(value = "hireDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate,
                                      @RequestParam(value = "promoteDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date promoteDate,
                                      @RequestParam(value = "trainer") String trainer) {

        Officer officer = new Officer();
        officer.setUid(uid);
        officer.setLastName(lastName);
        officer.setFirstName(firstName);
        officer.setBadgeNum(badgeNum);
        officer.setTitle(title);
        officer.setGender(gender);
        officer.setPermissionGroup(permissionGroup);
        officer.setUnion(unionRepository.findByName(union));
        officer.setRecruitId(recruit);
        officer.setSeniority(seniority);
        officer.setContractEmployee(contractEmployee);
        officer.setHireDate(hireDate);
        officer.setPromotionDate(promoteDate);
        System.out.println("uid: " + uid + " union "+ union +  " recruit " + officer.getRecruitId());
        System.out.println("what h");
        System.out.println(officerRepository.findByUID(uid));
        if(officerRepository.findByUID(uid) != null) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<String>("Already existed", responseHeaders, HttpStatus.CREATED);
        }
        if (trainer != null && !trainer.isEmpty() && !trainer.equals("")) {
            officer.setTrainer(officerRepository.findByUID(trainer));
        }

        Officer newOfficer = officerRepository.save(officer);
        newOfficer.setTrainer(null);
        return ResponseEntity.ok(newOfficer);
    }
}
