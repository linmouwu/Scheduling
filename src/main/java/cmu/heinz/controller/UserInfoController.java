package cmu.heinz.controller;

import cmu.heinz.model.*;
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
 * Controller to manage user information.
 *
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
@Controller
public class UserInfoController {

    /**
     * Officer repository interface.
     */
    @Autowired
    private OfficerRepository officerRepository;

    /**
     * Union repository interface.
     */
    @Autowired
    private UnionRepository unionRepository;

    /**
     * Permission Group repository interface.
     */
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    /**
     * Retrieve user information based on user id.
     *
     * @param id    user id (NOT UID/username)
     * @param model view model
     * @return render to 'userInfo' page
     */
    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public String getUserInfo(@RequestParam(value = "id") int id,
                              Model model) {

        Officer officer = officerRepository.findOne(id);

        if (officer != null) {
            model.addAttribute("officer", officer);
        }

        return "userinfo";

    }

    /**
     * Add new user.
     *
     * @param uid              username/UID
     * @param lastName         last name
     * @param firstName        first name
     * @param badgeNum         badge number
     * @param title            title
     * @param gender           gender
     * @param seniority        seniority
     * @param permissionGroup  permission group
     * @param union            union
     * @param recruit          recruit
     * @param contractEmployee contract indicator
     * @param hireDate         hire date
     * @param promoteDate      promote date
     * @param trainer          trainer if existed
     * @return new user object
     */
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ResponseEntity addUserInfo(@RequestParam(value = "uid") String uid,
                                      @RequestParam(value = "lastName") String lastName,
                                      @RequestParam(value = "firstName") String firstName,
                                      @RequestParam(value = "badgeNum") String badgeNum,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "gender") String gender,
                                      @RequestParam(value = "seniority") int seniority,
                                      @RequestParam(value = "permissionGroup") PermissionGroup permissionGroup,
                                      @RequestParam(value = "union") String union,
                                      @RequestParam(value = "recruit") String recruit,
                                      @RequestParam(value = "contractEmployee") String contractEmployee,
                                      @RequestParam(value = "hireDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate,
                                      @RequestParam(value = "promoteDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date promoteDate,
                                      @RequestParam(value = "trainer") String trainer) {

        // Create a new officer object.
        Officer officer = new Officer();

        // Update corresponding properties.
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

//        System.out.println("uid: " + uid + " union " + union + " recruit " + officer.getRecruitId());
//        System.out.println(officerRepository.findByUID(uid));

        if (officerRepository.findByUID(uid) != null) {

            // New user already existed.
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<String>("Already existed", responseHeaders, HttpStatus.CREATED);

        }

        if (trainer != null && !trainer.isEmpty() && !trainer.equals("")) {

            // If the new user has a training, update the field.
            officer.setTrainer(officerRepository.findByUID(trainer));

        }

        Officer newOfficer = officerRepository.save(officer);
        newOfficer.setTrainer(null);

        return ResponseEntity.ok(newOfficer);
    }


    @RequestMapping(value = "/update_permission_group", method = RequestMethod.POST)
    public ResponseEntity updatePermissionGroup(@RequestParam(value = "uid") String uid,
                                                @RequestParam(value = "permissionGroup") String permissionGroup) {

        Officer officer = officerRepository.findByUID(uid);

        System.out.println("Permission Group: " + permissionGroup);

        PermissionGroup newPG = permissionGroupRepository.findByRole(permissionGroup);

        if (officer == null || newPG == null) {

            System.out.println("No user found: " + uid);
            return ResponseEntity.badRequest().build();

        }

        officer.setPermissionGroup(newPG);

        officerRepository.save(officer);

        return ResponseEntity.ok().build();
    }
}
