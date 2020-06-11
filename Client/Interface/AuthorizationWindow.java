package Client.Interface;

import Client.CommandExecutor;
import Client.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthorizationWindow {

    @FXML
    private Text title;

    @FXML
    private TextField loginAutField;

    @FXML
    private PasswordField passwordAutField;

    @FXML
    private Button autButton;

    @FXML
    private Button backButton;



    @FXML
    void initialize() {
        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
            newWindow("start.fxml");
        });

        autButton.setOnAction(actionEvent -> {
            String login = loginAutField.getText().trim();
            String password = md5Custom(passwordAutField.getText().trim());

            if (!login.equals("") && !(password.equals(""))){
                CommandExecutor commandExecutor = CommandExecutor.getCommandExecutor();
                User user = new User(login, password);
                user.setAction("authorization");
                commandExecutor.setUser(user);
                if (commandExecutor.registrationAuthorization().getStatus()){
                    System.out.println("Successful authorization");
                    autButton.getScene().getWindow().hide();
                    newWindow("sample.fxml");
                }
                else{
                    System.out.println("Wrong Login or password");
                    autButton.getScene().getWindow().hide();
                    newWindow("start.fxml");
                    newWindow("login_exception.fxml");
                }
            }
            else{
                newWindow("start.fxml");
                newWindow("login_exception.fxml");
            }
        });
    }

    public void newWindow(String window){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent scene = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(scene));
        stage.show();
    }


    public String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while( md5Hex.length() < 32 ){
            md5Hex.insert(0, "0");
        }

        return md5Hex.toString();
    }

}
