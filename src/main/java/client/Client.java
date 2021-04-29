package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ServerDTO;

public class Client extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ServerDTO server = SelectServer.selectServer();
        TcpRequests.createPolling(server);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("window.fxml"));
        primaryStage.setTitle("Камень-ножницы-бумага");
        primaryStage.setScene(new Scene(root, 592, 666));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
