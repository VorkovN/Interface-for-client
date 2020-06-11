package Client.Interface;

import Client.CommandExecutor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class NumberException {

    @FXML
    private Label titleText;

    @FXML
    private Button okButton;

    @FXML
    private Label numberText;

    @FXML
    private Label tryText;

    @FXML
    void initialize() {
        titleText.setText(CommandExecutor.lang.getString("titleText"));
        okButton.setText(CommandExecutor.lang.getString("okButton"));
        numberText.setText(CommandExecutor.lang.getString("numberText"));
        tryText.setText(CommandExecutor.lang.getString("tryText"));


        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });
    }

}