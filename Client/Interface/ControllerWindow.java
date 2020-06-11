package Client.Interface;

import Client.CommandExecutor;
import Client.User;
import Route.Route;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.xml.crypto.Data;
import java.io.IOException;

public class ControllerWindow {

    private ObservableList<Route> routes = FXCollections.observableArrayList();
    private CommandExecutor commandExecutor = CommandExecutor.getCommandExecutor();
    private User user = commandExecutor.getUser();
    private int ID;

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
    private TableColumn<Route, Route> deleteColumn;

    @FXML
    private Button exitButton;

    @FXML
    private TextField argumentField;

    @FXML
    private TextArea console;

    @FXML
    private LineChart<Number, Number> xyChart;

    @FXML
    void initialize(){

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> System.out.println(newValue.getId()));




        // устанавливаем тип и значение которое должно хранится в колонке
        updateTable();
        toBuildChart();

        helpButton.setOnAction(actionEvent -> {
            commandExecutor.execute("help");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        addButton.setOnAction(actionEvent -> {
            newWindow("init.fxml");
            commandExecutor.execute("add");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
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
            updateTable();
            toBuildChart();
        });

        historyButton.setOnAction(actionEvent -> {
            commandExecutor.execute("history");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        removeFirstButton.setOnAction(actionEvent -> {
            commandExecutor.execute("remove_first");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        removeGreaterButton.setOnAction(actionEvent -> {
            commandExecutor.execute("remove_greater " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        removeByIdButton.setOnAction(actionEvent -> {
            commandExecutor.execute("remove_by_id " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        removeAllByDistanceButton.setOnAction(actionEvent -> {
            commandExecutor.execute("remove_all_by_distance " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        countLessThanDistanceButton.setOnAction(actionEvent -> {
            commandExecutor.execute("count_less_than_distance " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        filterGreaterThanDistanceButton.setOnAction(actionEvent -> {
            commandExecutor.execute("filter_greater_than_distance " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        saveButton.setOnAction(actionEvent -> {
            commandExecutor.execute("save");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        executeScriptButton.setOnAction(actionEvent -> {
            commandExecutor.execute("execute_script " + argumentField.getText());
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        infoButton.setOnAction(actionEvent -> {
            commandExecutor.execute("info");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        showButton.setOnAction(actionEvent -> {
            commandExecutor.execute("show");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        clearButton.setOnAction(actionEvent -> {
            commandExecutor.execute("clear");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
        });

        exitButton.setOnAction(actionEvent -> {
            commandExecutor.execute("exit");
            console.setText(CommandExecutor.forPrint);
            updateTable();
            toBuildChart();
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

    public void updateTable(){
        commandExecutor = CommandExecutor.getCommandExecutor();
        user = commandExecutor.getUser();
        routes.removeAll(routes);

        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteColumn.setCellFactory(param -> new TableCell<Route, Route>() {
            private final Button deleteButton = new Button("delete");

            @Override
            protected void updateItem(Route route, boolean empty) {
                super.updateItem(route, empty);

                if (route == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> routes.remove(route));
            }
        });

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



    }
    public void toBuildChart(){
        xyChart.getData().removeAll(xyChart.getData());

        for (Route route: routes){
            XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
            ObservableList<XYChart.Data<Number, Number>> datas1 = FXCollections.observableArrayList();
            datas1.removeAll(datas1);
            datas1.add(new XYChart.Data<Number, Number>((Number)route.getXl1(), (Number)route.getYl1()));
            System.out.println(route.getXl1()+ " " + route.getYl1());
            datas1.add(new XYChart.Data<Number, Number>((Number)route.getXl2(), (Number)route.getYl2()));
            System.out.println( route.getXl2()+ " " + route.getYl2());
            series1.setData(datas1);
            xyChart.getData().add(series1);
        }

    }

    Button btn = new Button("delete");
    //@FXML
    //private void handleDeletePerson() {
    //    int selectedIndex = table.getSelectionModel().getSelectedIndex();
    //    table.getItems().remove(selectedIndex);
    //}
}


