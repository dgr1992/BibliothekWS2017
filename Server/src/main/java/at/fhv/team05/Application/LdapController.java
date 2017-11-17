package at.fhv.team05.Application;

import at.fhv.team05.ResultDTO;
import at.fhv.team05.domain.UserAccount;
import at.fhv.team05.dtos.UserAccountDTO;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Random;

public class LdapController extends BaseController<UserAccount, UserAccountDTO> {
    private static LdapController mInstance;


    private LdapController(Class<UserAccount> userAccountClass) {
        super(userAccountClass);
    }

    public static LdapController getInstance() {
        if (mInstance == null) {
            mInstance = new LdapController(UserAccount.class);
        }
        return mInstance;
    }

    public ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO, String key) {
        UserAccount account = _mapDtoToDomain.get(accountDTO);
        String pw = "";
        try {
            pw = decryptCredentials(key, accountDTO.getPassword());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        if (account != null) {

            String[] splitUserName = account.getUsername().split("@");
            String userName = splitUserName[0];

            Hashtable<String, String> env = new Hashtable<>();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldaps://openldap.fhv.at:636");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + userName + ",ou=" + account.getOu() + ",o=fhv.at");
            env.put(Context.SECURITY_CREDENTIALS, pw);

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

    @Override
    protected UserAccountDTO createDTO(UserAccount object) {
        return new UserAccountDTO(object);
    }

    @Override
    protected boolean compareInput(UserAccount object, UserAccountDTO userAccountDTO) {
        return false;
    }

    private byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public String decryptCredentials(String tempkey, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] bytekey = hexStringToByteArray(tempkey);
        SecretKeySpec sks = new SecretKeySpec(bytekey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sks);
        byte[] decrypted = cipher.doFinal(hexStringToByteArray(password));
        return new String(decrypted);
    }

    public static String getRandomHexString(int numchars) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }
}
