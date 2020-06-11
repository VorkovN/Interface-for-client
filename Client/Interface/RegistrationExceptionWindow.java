package Client.Interface;

import Client.CommandExecutor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.w3c.dom.Text;

import java.awt.*;

public class RegistrationExceptionWindow {

    @FXML
    private Label titleText;

    @FXML
    private Button okButton;

    @FXML
    private Label loginText;

    @FXML
    private Label tryText;

    @FXML
    void initialize() {

        titleText.setText(CommandExecutor.lang.getString("reg_exception_titleText"));
        loginText.setText(CommandExecutor.lang.getString("reg_exception_errorText"));
        tryText.setText(CommandExecutor.lang.getString("reg_exception_tryText"));
        okButton.setText(CommandExecutor.lang.getString("reg_exception_button_okText"));

        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });
    }
}
