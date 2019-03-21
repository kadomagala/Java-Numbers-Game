package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;

import java.time.Duration;

public class NumberLabel extends Label {
    private int row;
    private int col;
    public NumberLabel(int row, int col){
        this.row= row;
        this.col = col;
        bindToNumberBean();
    }

    private void bindToNumberBean() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ofMillis(250),
                event -> if(Strip.strip.containsValue(new Cordinates(row,col)))
                    setText(Strip.strip.);
                            ))
    }
}
