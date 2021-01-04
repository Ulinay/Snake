package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Score {
ArrayList<String> result = new ArrayList<>();


    public Score(){

        try {
            File file = new File("results.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null){
                result.add(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        ObservableList<String> langs = FXCollections.observableArrayList(result);
        ListView<String> langsListView = new ListView<String>(langs);
langsListView.setPrefSize(450,200);
        FlowPane root = new FlowPane(langsListView);
        Scene scene = new Scene(root, 450, 200);
stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Score");
        stage.show();



    }
}
