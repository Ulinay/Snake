package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SizeAskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField rows;

    @FXML
    private TextField columns;

    @FXML
    private Button enter;

    @FXML
    void initialize() {
enter.setOnAction(actionEvent -> {
    if(((Integer.parseInt(columns.getText()) > 30) || (Integer.parseInt(columns.getText()) < 10))||((Integer.parseInt(rows.getText())) > 17) || (Integer.parseInt(rows.getText())) < 5){
        try {
            throw new Exception("Unreachable size");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    else {
        Snake snake = new Snake(Integer.parseInt(columns.getText()) * 40,Integer.parseInt(rows.getText()) * 40);


    }
    enter.getScene().getWindow().hide();

    });

    }
}
