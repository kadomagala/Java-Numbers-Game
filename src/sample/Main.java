package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, 500, 500));
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for(int i = 0; i < Strip.rowSize; i++){
            for(int j = 0; j < Strip.colSize; j++){
                NumberLabel numberLabel = new NumberLabel(i,j);
                grid.add(numberLabel, i, j);
            }
        }
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {

        Strip.Init(10,10, 10);
        Thread [] threads = new Thread[Strip.numOfBeans];
        Runnable[] runnables = new Runnable[Strip.numOfBeans];

        for(int i = 0; i < Strip.numOfBeans; i++){
            runnables[i] = Strip.beans[i];
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
