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
import javafx.scene.text.Font;
import javafx.util.Duration;

public class NumberLabel extends Label  {
    private int y;
    private int x;
    public NumberLabel(int row, int col){
        this.x = row;
        this.y = col;
        this.setMinSize(60,60);
        this.setFont(new Font("Arial", 20));
        bindToNumberBean();
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
    }

    private void bindToNumberBean() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(250),
                event -> {
                    setText((Strip.strip[x][y] == -1) ? "   " : String.valueOf(Strip.strip[x][y]));
                    if(Strip.strip[x][y] == 0){
                        setTextFill(Color.RED);
                    }else if(NumberBean.primeNumber(Strip.strip[x][y])){
                        setTextFill(Color.GREEN);
                    }
                    else
                        setTextFill(Color.BLACK);
                }),
                new KeyFrame(Duration.millis(250)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }



}
