package Client.Interface;

import Client.CommandExecutor;
import Client.User;
import Route.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerWindow {

    private ObservableList<Route> routes = FXCollections.observableArrayList();
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
    private TableView<Route> table;

    @FXML
    private TableColumn<Route, Integer> idColumn;

    @FXML
    private TableColumn<Route, String> nameColumn;

    @FXML
    private TableColumn<Route, Float> xColumn;

    @FXML
    private TableColumn<Route, Double> yColumn;

    @FXML
    private TableColumn<Route, Long> xl1Column;

    @FXML
    private TableColumn<Route, Double> yl1Column;

    @FXML
    private TableColumn<Route, Long> zl1Column;

    @FXML
    private TableColumn<Route, Integer> xl2Column;

    @FXML
    private TableColumn<Route, Float> yl2Column;

    @FXML
    private TableColumn<Route, String> namel2Column;

    @FXML
    private TableColumn<Route, Float> distColumn;

    @FXML
    private TableColumn<Route, String> dateColumn;

    @FXML
    private Button exitButton;

    @FXML
    private TextField argumentField;

    @FXML
    private TextArea console;

    @FXML
    void initialize() throws InterruptedException {

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        xl1Column.setCellValueFactory(new PropertyValueFactory<>("xl1"));
        yl1Column.setCellValueFactory(new PropertyValueFactory<>("yl1"));
        zl1Column.setCellValueFactory(new PropertyValueFactory<>("zl1"));
        xl2Column.setCellValueFactory(new PropertyValueFactory<>("xl2"));
        yl2Column.setCellValueFactory(new PropertyValueFactory<>("yl2"));
        namel2Column.setCellValueFactory(new PropertyValueFactory<>("namel2"));
        distColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // заполняем таблицу данными
        routes.addAll(user.getArr());
        table.setItems(routes);

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


