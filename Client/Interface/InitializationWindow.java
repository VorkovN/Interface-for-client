package Client.Interface;

import Client.CommandExecutor;
import Client.User;
import Exceptions.UnacceptableNumberException;
import Route.Route;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.time.LocalDate;

public class InitializationWindow {

    CommandExecutor commandExecutor = CommandExecutor.getCommandExecutor();
    User user = commandExecutor.getUser();
    Route newRoute = new Route();

    @FXML
    private Text title;

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

    @FXML
    void initialize() {

        okButton.setText(CommandExecutor.lang.getString("init_button_okText"));

        okButton.setOnAction(actionEvent -> {
            String name = nameField.getText();
            String x = xField.getText();
            String y = yField.getText();
            String xl1 = xl1Field.getText();
            String yl1 = yl1Field.getText();
            String zl1 = zl1Field.getText();
            String xl2 = xl2Field.getText();
            String yl2 = yl2Field.getText();
            String namel2 = namel2Field.getText();
            String distance = distanceField.getText();

            date();
            name(name);
            x(x);
            y(y);
            xl1(xl1);
            yl1(yl1);
            zl1(zl1);
            xl2(xl2);
            yl2(yl2);
            namel2(namel2);
            distance(distance);
            System.out.println("User = " + user.getName());
            newRoute.setUser(user.getName());
            commandExecutor.setNewRoute(newRoute);
            okButton.getScene().getWindow().hide();
        });
    }


    public void name(String name) {
        try {
            newRoute.setName(name);
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void x(String xstr) {
        try{
            float x = Float.parseFloat(xstr);
            if (!((Float.MIN_VALUE < Math.abs(x)) && (Math.abs(x) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setX(x);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void y(String ystr){
        try{
            double y = Double.parseDouble(ystr);
            if (!((Double.MIN_VALUE < Math.abs(y)) && (Math.abs(y) < Double.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setY(y);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void date() {
        LocalDate date = LocalDate.now();
        newRoute.setDate(date.toString());
    }

    public void xl1(String xl1str) {
        try{
            long xl1 = Long.parseLong(xl1str);
            if (!((Long.MIN_VALUE < xl1) && (xl1 < Long.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setXl1(xl1);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void yl1(String yl1str) {
        try{
            double yl1 = Double.parseDouble(yl1str);
            if (!((Double.MIN_VALUE < Math.abs(yl1)) && (Math.abs(yl1) < Double.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setYl1(yl1);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void zl1(String zl1str) {
        try{
            long zl1 = Long.parseLong(zl1str);
            if (!((Long.MIN_VALUE < zl1) && (zl1 < Long.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setZl1(zl1);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void xl2(String xl2str) {
        try{
            int xl2 = Integer.parseInt(xl2str);
            if (!((Integer.MIN_VALUE < xl2) && (xl2 < Integer.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setXl2(xl2);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void yl2(String yl2str) {
        try{
            float yl2 = Float.parseFloat(yl2str);
            if (!((Float.MIN_VALUE < Math.abs(yl2)) && (Math.abs(yl2) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setYl2(yl2);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void namel2(String namel2) {
        try{
            if (namel2.length() > 968) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setNamel2(namel2);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

    public void distance(String distancestr) {
        try{
            float dist = (Float.parseFloat(distancestr));
            if (!((1 < Math.abs(dist)) && (Math.abs(dist) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setDistance(dist);
            }
        } catch (NumberFormatException e) {
            //TODO
        }
    }

}

