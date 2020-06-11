package Client.Interface;

import Client.CommandExecutor;
import Client.User;
import Exceptions.UnacceptableNumberException;
import Route.Route;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class InitializationWindow {

    CommandExecutor commandExecutor = CommandExecutor.getCommandExecutor();
    User user = commandExecutor.getUser();
    Route newRoute = new Route();


    @FXML
    private Button okButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private TextField xl1Field;

    @FXML
    private TextField yl1Field;

    @FXML
    private TextField zl1Field;

    @FXML
    private TextField xl2Field;

    @FXML
    private TextField yl2Field;

    @FXML
    private TextField namel2Field;

    @FXML
    private TextField distanceField;

    String name;
    Float x;
    Double y;
    Long xl1;
    Double yl1;
    Long zl1;
    int xl2;
    Float yl2;
    String namel2;
    Float distance;

    @FXML
    void initialize() {

        okButton.setText(CommandExecutor.lang.getString("init_button_okText"));

        okButton.setOnAction(actionEvent -> {
            try {
                LocalDate date = LocalDate.now();
                name = nameField.getText();
                String x = xField.getText();
                String y = yField.getText();
                String xl1 = xl1Field.getText();
                String yl1 = yl1Field.getText();
                String zl1 = zl1Field.getText();
                String xl2 = xl2Field.getText();
                String yl2 = yl2Field.getText();
                namel2 = namel2Field.getText();
                String distance = distanceField.getText();

                x(x);
                y(y);
                xl1(xl1);
                yl1(yl1);
                zl1(zl1);
                xl2(xl2);
                yl2(yl2);
                distance(distance);

                newRoute.setName(name);
                newRoute.setX(this.x);
                newRoute.setY(this.y);
                newRoute.setDate(date.toString());
                newRoute.setXl1(this.xl1);
                newRoute.setYl1(this.yl1);
                newRoute.setZl1(this.zl1);
                newRoute.setXl2(this.xl2);
                newRoute.setYl2(this.yl2);
                newRoute.setNamel2(this.namel2);
                newRoute.setDistance(this.distance);

                System.out.println("User = " + user.getName());
                newRoute.setUser(user.getName());
                commandExecutor.setNewRoute(newRoute);
                okButton.getScene().getWindow().hide();
            }
            catch(Exception e){
                newWindow("number_exception.fxml");
            }
        });
    }


    public void x(String xstr) throws NumberFormatException {
            x = Float.parseFloat(xstr);
            if (!((Float.MIN_VALUE < Math.abs(x)) && (Math.abs(x) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            }
    }

    public void y(String ystr) throws NumberFormatException{
            y = Double.parseDouble(ystr);
            if (!((Double.MIN_VALUE < Math.abs(y)) && (Math.abs(y) < Double.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            }
    }


    public void xl1(String xl1str) throws NumberFormatException{
            xl1 = Long.parseLong(xl1str);
            if (!((Long.MIN_VALUE < xl1) && (xl1 < Long.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            }
    }

    public void yl1(String yl1str) throws NumberFormatException{
            yl1 = Double.parseDouble(yl1str);
            if (!((Double.MIN_VALUE < Math.abs(yl1)) && (Math.abs(yl1) < Double.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            }
    }

    public void zl1(String zl1str) throws NumberFormatException{
            zl1 = Long.parseLong(zl1str);
            if (!((Long.MIN_VALUE < zl1) && (zl1 < Long.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            }
    }

    public void xl2(String xl2str)throws NumberFormatException {
            xl2 = Integer.parseInt(xl2str);
            if (!((Integer.MIN_VALUE < xl2) && (xl2 < Integer.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            }
    }

    public void yl2(String yl2str) throws NumberFormatException{
            yl2 = Float.parseFloat(yl2str);
            if (!((Float.MIN_VALUE < Math.abs(yl2)) && (Math.abs(yl2) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            }
    }


    public void distance(String distancestr)throws NumberFormatException {
            distance = (Float.parseFloat(distancestr));
            if (!((1 < Math.abs(distance)) && (Math.abs(distance) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            }
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
        stage.show();
    }

}

