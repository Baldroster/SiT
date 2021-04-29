package client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    Label label;

    @FXML
    private void initialize(){

    }
    @FXML
    private void but1Clicked() {
        String response = TcpRequests.createRequest("0");
        switch (response) {
            case "0":
                response = "оппонент выбрал бумагу, вы проиграли";
                break;
            case "1":
                response = "оппонент выбрал камень, ничья";
                break;
            case "2":
                response = "оппонент выбрал ножницы, вы выиграли";
                break;
        }
        label.setText(response);
    }

    @FXML
    private void but2Clicked() {
        String response = TcpRequests.createRequest("1");
        switch (response) {
            case "0":
                response = "оппонент выбрал камень, вы проиграли";
                break;
            case "1":
                response = "оппонент выбрал ножницы, ничья";
                break;
            case "2":
                response = "оппонент выбрал бумагу, вы выиграли";
                break;
        }
        label.setText(response);
    }

    @FXML
    private void but3Clicked() {
        String response = TcpRequests.createRequest("2");
        switch (response) {
            case "0":
                response = "оппонент выбрал ножницы, вы проиграли";
                break;
            case "1":
                response = "оппонент выбрал бумагу, ничья";
                break;
            case "2":
                response = "оппонент выбрал камень, вы выиграли";
                break;
        }
        label.setText(response);
    }
}
