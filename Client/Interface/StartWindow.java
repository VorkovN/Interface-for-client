package Client.Interface;

import Client.CommandExecutor;
import Client.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StartWindow {

    private boolean check = false;

    @FXML
    private Label title;

    @FXML
    private MenuItem ru;

    @FXML
    private MenuItem fr;

    @FXML
    private MenuItem fin;

    @FXML
    private MenuItem spa;

    @FXML
    private Button toAutButton;

    @FXML
    private Button toRegButton;

    @FXML
    private TextField hostField;

    @FXML
    private TextField portField;

    @FXML
    private MenuButton langBox;

    @FXML
    void initialize() {

        title.setText(CommandExecutor.lang.getString("start_titleText"));
        toAutButton.setText(CommandExecutor.lang.getString("start_button_inText"));
        toRegButton.setText(CommandExecutor.lang.getString("start_button_regText"));
        hostField.setText(CommandExecutor.lang.getString("start_field_hostText"));
        portField.setText(CommandExecutor.lang.getString("start_field_portText"));
        langBox.setText(CommandExecutor.lang.getString("start_button_switchText"));

        ru.setOnAction(actionEvent -> {
            CommandExecutor.lang = ResourceBundle.getBundle("all", new Locale("ru"));
            langBox.getScene().getWindow().hide();
            newWindow("start.fxml");
        });
        fr.setOnAction(actionEvent -> {
            CommandExecutor.lang = ResourceBundle.getBundle("all", new Locale("fr"));
            langBox.getScene().getWindow().hide();
            newWindow("start.fxml");
        });
        fin.setOnAction(actionEvent -> {
            CommandExecutor.lang = ResourceBundle.getBundle("all", new Locale("fin"));
            langBox.getScene().getWindow().hide();
            newWindow("start.fxml");
        });
        spa.setOnAction(actionEvent -> {
            CommandExecutor.lang = ResourceBundle.getBundle("all", new Locale("spa"));
            langBox.getScene().getWindow().hide();
            newWindow("start.fxml");
        });


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
            System.out.println(CommandExecutor.lang.getLocale());
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

