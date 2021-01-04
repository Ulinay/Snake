package sample;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EndGameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nickname;


    @FXML
    private Button save;

    @FXML
    void initialize() throws Exception {
save.setOnAction(actionEvent -> {
    File file = new File("results.txt");
    try {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(nickname.getText() + "\n");
        fileWriter.flush();
        fileWriter.close();
        save.getScene().getWindow().hide();
    } catch (IOException e) {
        e.printStackTrace();
    }
    save.getScene().getWindow().hide();
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/sample/start.fxml"));
    try {
        fxmlLoader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
    Parent parent = fxmlLoader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(parent));
    stage.show();

});

    }
}
