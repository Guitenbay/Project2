package JungleChessFX;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * Created by Tenbay on 2017/1/1.
 */
public class TimeLabel extends Label{
    private Timeline tl;
    private KeyFrame keyFrame;
    private int[] time = {0, 0, 0, 0, 0, 0};

    TimeLabel() {
        this("");
    }

    TimeLabel(String string) {
        super();
        super.setTextFill(Color.WHITE);
        super.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 23));
        this.setPadding(new Insets(25));
        if (string.length() == 6) {
            for (int i = 0; i < 6; i++) {
                time[i] = string.charAt(i);
            }
        }
        setAlignment(Pos.CENTER);
        Timeline tl = new Timeline(makeKeyFrame());
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }

    private KeyFrame makeKeyFrame() {
        keyFrame = new KeyFrame(new Duration(1000), e -> {
            if (time[5] != 9) {
                time[5]++;
            } else {
                if (time[4] != 5) {
                    time[4]++;
                    time[5] = 0;
                } else {
                    if (time[3] != 9) {
                        time[3]++;
                        time[4] = time[5] = 0;
                    } else {
                        if (time[2] != 5) {
                            time[2]++;
                            time[3] = time[4] = time[5] = 0;
                        } else {
                            if (time[0] == 2) {
                                if (time[1] != 3) {
                                    time[1]++;
                                    time[2] = time[3] = time[4] = time[5] = 0;
                                } else {
                                    time[0] = time[1] = time[2] = time[3] = time[4] = time[5] = 0;
                                }
                            } else {
                                if (time[1] != 9) {
                                    time[1]++;
                                    time[2] = time[3] = time[4] = time[5] = 0;
                                } else {
                                    time[0]++;
                                    time[1] = time[2] = time[3] = time[4] = time[5] = 0;
                                }
                            }
                        }
                    }
                }
            }
            super.setText("" + time[0] + time[1] + ":" + time[2] + time[3] + ":" + time[4] + time[5]);
        });
        return keyFrame;
    }
}
