package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application {

    public static void main(String[] args){
        launch(args);
    }

    public MainClient() {
        CommandExecutor.getCommandExecutor();
    }


    public void newWindow(Stage primaryStage, String window) throws Exception{
        Parent scene = FXMLLoader.load(getClass().getResource(window));
        primaryStage.setScene(new Scene(scene, 600, 450));
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        newWindow(primaryStage, "Interface/start.fxml");
    }

    //TODO неверный ввод координат
    //TODO фикс execute_script
    //TODO визуализация, добавить ууу-ууу-ууу
    //TODO исправить индексацию

}
