package cmu.heinz.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//import cmu.heinz.util.ActiveDirectory.*;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 *
 * @author Akashravindranath
 */
public class Authenticate {

    /**
     * @param args the command line arguments
     */
    private static final String MASTER_USER_DN=  "cmuldap";
    private static final String MASTER_PASSWORD = "P@ss4CMULDAP#1";
    private static String ldapUrl = "";
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            LdapContext ctx = ActiveDirectory.getConnection(MASTER_USER_DN, MASTER_PASSWORD);
            System.out.println(ctx.toString());
            
            ActiveDirectory.User[] users = ActiveDirectory.getUsers(ctx);
//            for(ActiveDirectory.User u : users){
//                System.out.println(u.getCommonName()+"-"+u.toString());
//            }
            System.out.println(args[0]+args[1]);
            authenticateUser(args[0],args[1]);
            ctx.close();
        }
        catch(Exception e){
            //Failed to authenticate user!
            e.printStackTrace();
        }
    }
    
    public static DirContext authenticateUser(String userName, String pwd){
        try {
            return authenticate(userName, pwd);
        } catch (NamingException ex) {
            Logger.getLogger(Authenticate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
//    private DirContext authenticate() throws NamingException {
//        return authenticate(null, null);
//    }
 
    private static DirContext authenticate(String username, String password)
            throws NamingException {
        String initialContextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
        String securityAuthentication = "simple";
 
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
        env.put(Context.SECURITY_AUTHENTICATION, securityAuthentication);
//        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_PRINCIPAL,
                username != null ? username : MASTER_USER_DN);
        env.put(Context.SECURITY_CREDENTIALS,
                password != null ? password : MASTER_PASSWORD);
        System.out.println(username);
        System.out.println(password);
        
        
//        LdapContext ctx = new InitialLdapContext(env, null);
        LdapContext ctx = ActiveDirectory.getConnection(username, password);
        ActiveDirectory.User u = ActiveDirectory.getUser(username, ctx);
//            for(ActiveDirectory.User u : users){
                System.out.println(u.toString()+":"+u.getCommonName()+"-"+u.toString());
//            }
        return ctx;
    }
    
}
