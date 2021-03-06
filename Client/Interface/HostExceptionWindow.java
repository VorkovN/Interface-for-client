package Client.Interface;


import Client.CommandExecutor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.w3c.dom.Text;


public class HostExceptionWindow {

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

        titleText.setText(CommandExecutor.lang.getString("host_exception_titleText"));
        loginText.setText(CommandExecutor.lang.getString("host_exception_host_portText"));
        tryText.setText(CommandExecutor.lang.getString("host_exception_tryText"));
        okButton.setText(CommandExecutor.lang.getString("host_exception_button_okText"));


        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });
    }
}
