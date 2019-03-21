package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class NumberLabel extends Label {
    private int row;
    private int col;
    public NumberLabel(int row, int col){
        this.row= row;
        this.col = col;
        bindToNumberBean();
    }

    private void bindToNumberBean() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(250),
                event -> setText((Strip.strip[row][col] == -1) ? "" : String.valueOf(Strip.strip[row][col]))),
                new KeyFrame(Duration.millis(250)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
