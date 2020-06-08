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
import java.util.Scanner;

public class StartWindow {

    private boolean check = false;

    @FXML
    private Button toAutButton;

    @FXML
    private Button toRegButton;

    @FXML
    private TextField hostField;

    @FXML
    private TextField portField;

    @FXML
    void initialize() {

        toAutButton.setOnAction(actionEvent -> {
            init();
            toAutButton.getScene().getWindow().hide();
            if (check){
                newWindow("in.fxml");
            }
            else{
                newWindow("start.fxml");
                newWindow("host_exception.fxml");
            }

        });
        toRegButton.setOnAction(actionEvent -> {
            init();
            toRegButton.getScene().getWindow().hide();
            if (check){
                newWindow("reg.fxml");
            }
            else{
                newWindow("start.fxml");
                newWindow("host_exception.fxml");
            }
        });
    }

    void init(){
        CommandExecutor commandExecutor = CommandExecutor.getCommandExecutor();
        String host = hostField.getText().trim();
        String port = portField.getText().trim();
        if (!host.equals("") && !(port.equals(""))) {
            try {
                commandExecutor.setAddress(host);
                commandExecutor.setPort(Integer.parseInt(port));
                commandExecutor.checkAddreess();
                check = true;
            }
            catch (IllegalArgumentException | IOException e){
                check = false;
            }
        }
        else{
            check = false;
        }
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
}

