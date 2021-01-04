package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class Snake {

     int widthWindow;
     int heightWindow;
     int widthSnake = 26;
     int heightSnake = 26;
     int maxSpeed = 25;
     List<PartsOfSnake> snake = new ArrayList<>();
     side getSide = side.RIGHT;
     Apple apple;
     int score = 0;
     boolean alive = true;
     Thread thread;
 boolean animstop = false;

    public enum side {
        LEFT, RIGHT, UP, DOWN
    }

    public Snake(int width, int height) {
        this.widthWindow = width;
        this.heightWindow = height;
        apple = new Apple(widthWindow, heightWindow);
        snake.add(new PartsOfSnake(0, 0));
      snake.add(new PartsOfSnake(0, 0));
      snake.add(new PartsOfSnake(0, 0));


        BorderPane root = new BorderPane();
        Canvas c = new Canvas(width, height);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);
        thread = new Thread(apple);
        Scene scene = new Scene(root, width, height);
        thread.start();
        Stage primaryStage = new Stage();

      new AnimationTimer() {
            public void handle(long l) {
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                check(gc);
                if (!alive) {
                    stop();
                    thread.stop();
                    try {
                        EndGame endGame = new EndGame(score, width / 40, height / 40);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    scene.getWindow().hide();

                }else if(animstop){
                    stop();
                    thread.stop();
primaryStage.close();
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

                }
            }

        }.start();



        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.show();


        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.D) {
                getSide = side.RIGHT;
            } else if (keyEvent.getCode() == KeyCode.W) {
                getSide = side.UP;
            } else if (keyEvent.getCode() == KeyCode.A) {
                getSide = side.LEFT;
            } else if (keyEvent.getCode() == KeyCode.S) {
                getSide = side.DOWN;
            }else if(keyEvent.getCode() == KeyCode.ESCAPE){
animstop=true;
            }
        });
    }


    public  void check(GraphicsContext gc) {

        gc.setFill(Color.BROWN);
        gc.fillRect(0, 0, widthWindow, heightWindow);

        switch (getSide) {
            case RIGHT:
                snake.get(0).x++;
                break;
            case UP:
                snake.get(0).y--;
                break;
            case LEFT:
                snake.get(0).x--;
                break;
            case DOWN:
                snake.get(0).y++;
                break;
        }


        for (PartsOfSnake c : snake) {
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * maxSpeed, c.y * maxSpeed, widthSnake, heightSnake);
           gc.setFill(Color.YELLOW);
           gc.fillRect(snake.get(0).x * maxSpeed,snake.get(0).y*maxSpeed,widthSnake,heightSnake);
        }
        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        gc.fillOval(apple.x, apple.y, widthSnake - 10, heightSnake - 10);
        gc.setFill(Color.RED);
        gc.fillOval(apple.xPoison, apple.yPoison, widthSnake - 10, heightSnake - 10);

        if ((snake.get(0).x == apple.x / maxSpeed) && (snake.get(0).y == apple.y / maxSpeed)) {
            apple.eaten = true;
            snake.add(new PartsOfSnake(-5, -5));
            score++;
        } else if ((snake.get(0).x == apple.xPoison / maxSpeed) && (snake.get(0).y == apple.yPoison / maxSpeed)) {
            apple.eatenPoison = true;
            snake.remove(snake.size() - 1);
            score--;
        }
//check
        if (snake.get(0).x < 0 || snake.get(0).y < 0 || snake.get(0).x * maxSpeed + 20 > widthWindow || snake.get(0).y * maxSpeed + 20 > heightWindow) {
            alive = false;
        }
        if(score > 3){
            for (int i = 4; i < snake.size(); i++) {
                if((snake.get(0).x == snake.get(i).x) && (snake.get(0).y == snake.get(i).y)){
                    alive = false;
                }
            }

        }
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("Italic", 20));
            gc.fillText("Score: " + score, widthWindow - 90, heightWindow - 20);

    }
}

