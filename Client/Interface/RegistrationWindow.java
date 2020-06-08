package Client.Interface;

import Client.CommandExecutor;
import Client.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationWindow {

    @FXML
    private TextField loginRegField;

    @FXML
    private TextField passwordRegField;

    @FXML
    private Button regButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {

        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
            newWindow("start.fxml");
        });

        regButton.setOnAction(actionEvent -> {
            String login = loginRegField.getText().trim();
            String password = md5Custom(passwordRegField.getText().trim());
            if (!login.equals("") && !(password.equals(""))) {
                CommandExecutor commandExecutor = CommandExecutor.getCommandExecutor();
                User user = new User(login, password);
                user.setAction("registration");
                commandExecutor.setUser(user);
                if (commandExecutor.registrationAuthorization().getStatus()) {
                    System.out.println("Successful registration");
                    regButton.getScene().getWindow().hide();
                    newWindow("start.fxml");
                } else {
                    System.out.println("User already exist");
                    regButton.getScene().getWindow().hide();
                    newWindow("start.fxml");
                    newWindow("reg_exception.fxml");
                }
            }
            else{
                newWindow("start.fxml");
                newWindow("reg_exception.fxml");
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

