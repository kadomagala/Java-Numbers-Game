package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Game");
        //primaryStage.setScene(new Scene(root, 500, 500));
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for(int i = 0; i < Strip.cols; i++){
            for(int j = 0; j < Strip.rows; j++){
                NumberLabel numberLabel = new NumberLabel(i,j);
                grid.add(numberLabel, i, j);
            }
        }
        Scene scene = new Scene(grid,700,700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        int numOfBeans = 50;
        int width = 10;
        int height = 10;

        Strip.Init(numOfBeans,width, height);
        Thread [] threads = new Thread[Strip.numOfBeans];
        Runnable[] runnables = new Runnable[Strip.numOfBeans];

        runnables[0] = Strip.zeroBean;
        for(int i = 1; i < Strip.numOfBeans-1; i++){
            runnables[i] = Strip.beans[i-1];
        }

        for(int i = 0; i < Strip.numOfBeans; i++){
            threads[i] = new Thread(runnables[i]);
        }

        for(int i = 0; i < Strip.numOfBeans; i++){
            threads[i].start();
        }

        launch(args);
    }
}
