package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class EndGame implements Serializable {
    int score;
    int columns;
    int rows;

    public EndGame(int score,int columns,int rows) throws IOException {
        this.score = score;
        this.rows = rows;
        this.columns = columns;
        String result = "Score: " + score + " Rows: " + this.rows + " Columns: " + columns + " Nick: ";
        File file = new File("results.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        if(file.exists() && !file.isDirectory()) {
            fileWriter.write(result);
            fileWriter.flush();
            fileWriter.close();
        }else {
            file.createNewFile();
            fileWriter.write(result);
            fileWriter.flush();
            fileWriter.close();
        }
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("EndGame.fxml"));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(new Scene(root, 375, 202));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
