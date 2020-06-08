package Client.Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartWindow {


    @FXML
    private Button toAutButton;

    @FXML
    private Button toRegButton;

    @FXML
    void initialize() {
        toAutButton.setOnAction(actionEvent -> {
            toAutButton.getScene().getWindow().hide();
            newWindow("in.fxml");
        });
        toRegButton.setOnAction(actionEvent -> {
            toRegButton.getScene().getWindow().hide();
            newWindow("reg.fxml");
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

}

