package Client.Interface;

import Client.CommandExecutor;
import Client.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.io.IOException;

public class ControllerWindow {

    CommandExecutor commandExecutor = CommandExecutor.getCommandExecutor();
    User user = commandExecutor.getUser();

    @FXML
    private Button helpButton;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button removeFirstButton;

    @FXML
    private Button removeGreaterButton;

    @FXML
    private Button removeByIdButton;

    @FXML
    private Button removeAllByDistanceButton;

    @FXML
    private Button countLessThanDistanceButton;

    @FXML
    private Button filterGreaterThanDistanceButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button executeScriptButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button showButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField argumentField;

    @FXML
    private TextArea console;



    @FXML
    void initialize() {

        helpButton.setOnAction(actionEvent -> {
            commandExecutor.execute("help");
            console.setText(CommandExecutor.forPrint);
        });

        addButton.setOnAction(actionEvent -> {
            newWindow("init.fxml");
            commandExecutor.execute("add");
            console.setText(CommandExecutor.forPrint);
        });

        updateButton.setOnAction(actionEvent -> {
            try {
                if (checkId(argumentField.getText())) {
                    newWindow("init.fxml");
                    commandExecutor.execute("update " + argumentField.getText());
                    console.setText(CommandExecutor.forPrint);
                }
            } catch (NumberFormatException ignored){
                console.setText("You doesn't have route with id=" + argumentField.getText());
            }
        });

        historyButton.setOnAction(actionEvent -> {
            commandExecutor.execute("history");
            console.setText(CommandExecutor.forPrint);
        });

        removeFirstButton.setOnAction(actionEvent -> {
            commandExecutor.execute("remove_first");
            console.setText(CommandExecutor.forPrint);
        });

        removeGreaterButton.setOnAction(actionEvent -> {
            commandExecutor.execute("remove_greater " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
        });

        removeByIdButton.setOnAction(actionEvent -> {
            commandExecutor.execute("remove_by_id " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
        });

        removeAllByDistanceButton.setOnAction(actionEvent -> {
            commandExecutor.execute("remove_all_by_distance " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
        });

        countLessThanDistanceButton.setOnAction(actionEvent -> {
            commandExecutor.execute("count_less_than_distance " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
        });

        filterGreaterThanDistanceButton.setOnAction(actionEvent -> {
            commandExecutor.execute("filter_greater_than_distance " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
        });

        saveButton.setOnAction(actionEvent -> {
            commandExecutor.execute("save");
            console.setText(CommandExecutor.forPrint);
        });

        executeScriptButton.setOnAction(actionEvent -> {
            commandExecutor.execute("execute_script " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
        });

        infoButton.setOnAction(actionEvent -> {
            commandExecutor.execute("info");
            console.setText(CommandExecutor.forPrint);
        });

        showButton.setOnAction(actionEvent -> {
            commandExecutor.execute("show");
            console.setText(CommandExecutor.forPrint);
        });

        clearButton.setOnAction(actionEvent -> {
            commandExecutor.execute("clear");
            console.setText(CommandExecutor.forPrint);
        });

        exitButton.setOnAction(actionEvent -> {
            commandExecutor.execute("exit");
            console.setText(CommandExecutor.forPrint);
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
        stage.showAndWait();
    }

    public boolean checkId(String arg) throws NumberFormatException{
        System.out.println(user.getIds());
        int a = Integer.parseInt(arg);
        for (int i: user.getIds()) {
            if (i == a){
                return true;
            }
        }
        return false;
    }
}


