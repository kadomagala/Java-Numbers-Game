package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StripView extends GridPane {
    private final int width, height;
    private Label[][] number;

    public StripView(int width, int height){

        this.width = width;
        this.height = height;
        number = new Label[height][width];
        setHgap(10);
        setVgap(10);

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                number[i][j] = new Label();
                number[i][j].setMinSize(60,60);
                add(number[i][j], i , j);
            }
        }
    }

    public void updateView(int strip[][]){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int value = strip[i][j];
                number[i][j].setText(value == -1 ? "" : String.valueOf(value));
            }
        }
    }

}
