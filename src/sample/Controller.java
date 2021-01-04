package sample;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button score;

    @FXML
    private Button exit;

    @FXML
    private Button start;

    @FXML
    void initialize() {
start.setOnAction(actionEvent -> {
start.getScene().getWindow().hide();
FXMLLoader fxmlLoader = new FXMLLoader();
fxmlLoader.setLocation(getClass().getResource("/sample/askForSize.fxml"));
    try {
        fxmlLoader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
Parent parent = fxmlLoader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(parent));
    stage.showAndWait();

});
exit.setOnAction(actionEvent -> {
    exit.getScene().getWindow().hide();
});
score.setOnAction(actionEvent -> {
    new Score();
});
    }
}
