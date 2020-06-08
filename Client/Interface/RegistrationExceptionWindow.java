package Client.Interface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegistrationExceptionWindow {

    @FXML
    private Button okButton;

    @FXML
    void initialize() {
        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });
    }
}
