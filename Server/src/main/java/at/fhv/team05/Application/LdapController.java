package at.fhv.team05.Application;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapController {
    private static LdapController mInstance;

    private LdapController() {
    }

    public static LdapController getInstance() {
        if (mInstance == null) {
            mInstance = new LdapController();
        }
        return mInstance;
    }

    public boolean authenticateUser(String uname, String pw) {


        Hashtable<String, String> env = new Hashtable<>();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldaps://openldap.fhv.at:636/o=fhv.at");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=" + (uname.equals("") ? " " : uname) + ",ou=people,o=fhv.at");
        env.put(Context.SECURITY_CREDENTIALS, (pw.equals("") ? " " : pw));

        try {
            DirContext ctx = new InitialDirContext(env);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return true;

    }

}
