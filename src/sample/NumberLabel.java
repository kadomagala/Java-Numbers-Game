package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class NumberLabel extends Label {
    private int row;
    private int col;
    public NumberLabel(int row, int col){
        this.row= row;
        this.col = col;
        bindToNumberBean();
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

    }

    private void bindToNumberBean() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(250),
                event -> setText((Strip.strip[row][col] == -1) ? "  " : String.valueOf(Strip.strip[row][col]))),
                new KeyFrame(Duration.millis(2500)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
