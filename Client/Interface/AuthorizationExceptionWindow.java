package Client.Interface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AuthorizationExceptionWindow {

    @FXML
    private Button okButton;

    @FXML
    void initialize() {
        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });
    }
}
