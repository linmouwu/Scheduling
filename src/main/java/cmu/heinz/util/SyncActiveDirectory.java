/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmu.heinz.util;

import cmu.heinz.model.*;
import cmu.heinz.util.ActiveDirectory.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class to synchronize the Active Directory.
 */
public class SyncActiveDirectory {

    /**
     * Officer repository interface
     */
    @Autowired
    private OfficerRepository officerRepository;

    /**
     * Union repository
     */
    @Autowired
    private UnionRepository unionRepository;

    /**
     * Permission Group repository
     */
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;


    /**
     * @param args the command line arguments
     */
    @Value("${activedirectory.username}")
    private static String adusername;

    @Value("${activedirectory.password}")
    private static String adpassword;

    @Value("${activedirectory.server}")
    private static String adserver;

    public static void main(String[] args) {
        initializeSync();
    }


    public static void initializeSync() {
        SyncActiveDirectory ad;

        ArrayList<Officer> userNames = new ArrayList<Officer>();
        try {
            LdapContext ctx = ActiveDirectory.getConnection(adusername, adpassword, adserver);
            // ctx.search(name, matchingAttributes)
            System.out.println(ctx.toString());

            User[] users = ActiveDirectory.getUsers(ctx);
            for (User u : users) {
                // if(u.getCommonName())
                if (u.getDistinguishedName().contains("OU=Departments") || u.getDistinguishedName().contains("OU=Fire")) {
                    System.out.println(u.getUserPrincipal() + "::" + u.getDistinguishedName());
                    Officer o = new Officer();
                    o.setUid(u.getUserPrincipal().split("@")[0]);
                    o.setFirstName(u.getCommonName().split("\\s")[0]);
                    o.setLastName(u.getCommonName().split("\\s")[1]);
                    o.setTitle("temp");

                    userNames.add(o);
                }
            }
            ctx.close();
            ad = new SyncActiveDirectory();
            ad.syncOfficerTable(userNames);

        } catch (Exception e) {
            // Failed to authenticate user!
            e.printStackTrace();
        }
    }

    private void syncOfficerTable(List<Officer> usernames) {
        for (Officer officer : usernames) {
            if (officerRepository.findByUID(officer.getUid()) == null) {

                // Create a new officer object.
                // Officer officer = new Officer();

                // Get permission group by name.
                PermissionGroup permissionGroup = permissionGroupRepository.findByRole("Staff Member");

                // Update corresponding properties.
                // officer.setUid(uid);
                // officer.setLastName(lastName);
                // officer.setFirstName(firstName);
                // officer.setBadgeNum("000");
                // officer.setTitle(title);
                // officer.setGender(gender);
                officer.setPermissionGroup(permissionGroup);
                officer.setUnion(unionRepository.findByName("New User"));
                officer.setRecruitId("1");
                // officer.setSeniority("");
                // officer.setContractEmployee(contractEmployee);
                // officer.setHireDate(hireDate);
                // officer.setPromotionDate(promoteDate);

                try {
                    officerRepository.save(officer);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        }

    }

}
