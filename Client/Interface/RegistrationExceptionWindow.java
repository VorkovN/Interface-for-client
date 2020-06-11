package Client.Interface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.w3c.dom.Text;

public class RegistrationExceptionWindow {

    @FXML
    private Text title;

    @FXML
    private Button okButton;

    @FXML
    void initialize() {
        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });
    }
}
