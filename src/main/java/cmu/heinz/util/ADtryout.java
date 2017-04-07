/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmu.heinz.util;

import cmu.heinz.util.ActiveDirectory.User;

import javax.naming.ldap.LdapContext;

/**
 * @author Akashravindranath
 */
public class ADtryout {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            LdapContext ctx = ActiveDirectory.getConnection("cmuldap", "P@ss4CMULDAP#1");
            System.out.println(ctx.toString());

            User[] users = ActiveDirectory.getUsers(ctx);
            for (User u : users) {
                System.out.println(u.getCommonName() + "-" + u.toString());
            }
            ctx.close();
        } catch (Exception e) {
            //Failed to authenticate user!
            e.printStackTrace();
        }
    }

}
