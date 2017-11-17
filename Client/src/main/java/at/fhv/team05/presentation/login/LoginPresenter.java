package at.fhv.team05.presentation.login;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.dtos.UserAccountDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LoginPresenter extends Presenter implements Initializable {
    @FXML
    public AnchorPane loginAnchorPane;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginAnchorPane.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.S) {
                parent.changeNavigationBarToLoggedIn();
                parent.openSearchView();
            }
        });
    }

    public void loginUser() {
        try {
            String key = ClientRun.controller.getKey();
            String cryptedPassword = "";
            try {
                cryptedPassword = encryptCredentials(key, getPw());
            } catch (NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            UserAccountDTO accountDTO = new UserAccountDTO();
            accountDTO.setEmail(getUsr());
            accountDTO.setPassword(cryptedPassword);

            ResultDTO<Boolean> resultBoolean = ClientRun.controller.authenticateUser(accountDTO);
            if (resultBoolean.getDto()) {
                parent.changeNavigationBarToLoggedIn();
                parent.openSearchView();
            } else {
                errorAlert(resultBoolean.getException().getMessage());
                username.clear();
                password.clear();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private String encryptCredentials(String key, String pw) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] bytekey = hexStringToByteArray(key);
        SecretKeySpec sks = new SecretKeySpec(bytekey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
        byte[] encrypted = cipher.doFinal(pw.getBytes());
        return byteArrayToHexString(encrypted);
    }


    private String getUsr() {
        return username.getText();
    }

    private String getPw() {
        return password.getText();
    }

    private String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
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
}
