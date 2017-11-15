package at.fhv.team05.Application;

import at.fhv.team05.ResultDTO;
import at.fhv.team05.domain.UserAccount;
import at.fhv.team05.dtos.UserAccountDTO;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapController extends BaseController<UserAccount, UserAccountDTO> {
    private static LdapController mInstance;


    private LdapController(Class<UserAccount> userAccountClass) {
        super(userAccountClass);
    }

    private LdapController() {
        super(UserAccount.class);
    }

    public static LdapController getInstance() {
        if (mInstance == null) {
            mInstance = new LdapController();
        }
        return mInstance;
    }

    public ResultDTO<Boolean> authenticateUser(String uname, String pw) {

        if (mailAuthentication(uname)) {

            String[] splitUserName = uname.split("@");
            String userName = splitUserName[0];

            Hashtable<String, String> env = new Hashtable<>();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldaps://openldap.fhv.at:636/o=fhv.at");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + (uname.equals("") ? " " : userName) + ",ou=people,o=fhv.at");
            env.put(Context.SECURITY_CREDENTIALS, (pw.equals("") ? " " : pw));

            try {
                DirContext ctx = new InitialDirContext(env);
                return new ResultDTO<>(true, null);
            } catch (NamingException e) {
                e.printStackTrace();
                return new ResultDTO<>(false, new Exception(e.getMessage()));
            }
        } else {
            return new ResultDTO<>(false, new Exception("Email address not found."));
        }

    }

    private boolean mailAuthentication(String mname) {
        for (UserAccount acc : _mapDomainToDto.keySet()) {
            if (acc.getEmail().equalsIgnoreCase(mname)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected UserAccountDTO createDTO(UserAccount object) {
        return null;
    }

    @Override
    protected boolean compareInput(UserAccount object, UserAccountDTO userAccountDTO) {
        return false;
    }
}
