package at.fhv.team05.server.Application;

import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.server.domain.UserAccount;
import at.fhv.team05.library.dtos.UserAccountDTO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        //get the domain-object
        UserAccount account = _mapDtoToDomain.get(accountDTO);
        String pw = "";
        try {
            //decrypt the password
            pw = decryptCredentials(key, accountDTO.getPassword());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        if (account != null) {
            //Masteraccount which skips the ldap server
            if (account.getUsername().equalsIgnoreCase("admin") && pw.equalsIgnoreCase("admin")) {
                return new ResultDTO<>(true, null);
            }

            //HashTable for the Ldap-Authentification
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldaps://openldap.fhv.at:636");
            //Authenticationtype = simple (plaintext) but we encrypt the password anyway
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + account.getUsername() + ",ou=" + account.getOu() + ",o=fhv.at");
            env.put(Context.SECURITY_CREDENTIALS, pw);

            try {
                //Connects to the Ldap-Server
                DirContext ctx = new InitialDirContext(env);
                return new ResultDTO<>(true, null);
            } catch (NamingException e) {
                //if the connection was not successful, a exception gets thrown
                return new ResultDTO<>(false, new Exception(e.getMessage()));
            }
        } else {
            //if the emailaddress is not found in our database
            return new ResultDTO<>(false, new Exception("Email address not found."));
        }
    }

    @Override
    protected UserAccountDTO createDTO(UserAccount object) {
        return new UserAccountDTO(object);
    }

    /**
     * This method is not implemented yet
     *
     * @param object         domain-object
     * @param userAccountDTO
     * @return
     */
    @Override
    protected boolean compareInput(UserAccount object, UserAccountDTO userAccountDTO) {
        throw new NotImplementedException();
    }

    /**
     * Decrypts the password, the tempkey is used for decryption and encryption
     *
     * @param tempkey  Key used for the encryption/decryption
     * @param password String which gets decrypted
     * @return decrypted String
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public String decryptCredentials(String tempkey, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] bytekey = hexStringToByteArray(tempkey);
        SecretKeySpec sks = new SecretKeySpec(bytekey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sks);
        byte[] decrypted = cipher.doFinal(hexStringToByteArray(password));
        return new String(decrypted);
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

    /**
     * generates a random hex string
     *
     * @param numchars length of characters
     * @return
     */
    public static String getRandomHexString(int numchars) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }
}
