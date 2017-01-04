package JungleChessFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by Tenbay on 2017/1/3.
 */
public class PromptPane extends VBox{
    private Background backGray = new Background(new BackgroundFill(Color.grayRgb(120, 0.6), new CornerRadii(5), new Insets(10, 10, 10, 10)));
    private Button button;
    PromptPane(String string) {
        setBackground(backGray);
        ImageView imageView = new ImageView("image/叉.png");
        imageView.fitWidthProperty().bind(this.widthProperty().divide(15));
        imageView.fitHeightProperty().bind(this.widthProperty().divide(15));
        imageView.setEffect(new DropShadow(5, Color.web("0x66ccff")));
        button = new Button("", imageView);
        button.setStyle("-fx-background-color:transparent;");
        Text text = new Text(string);
        text.setFill(Color.WHITE);
        text.setStroke(Color.grayRgb(200));
        button.setOnAction(e -> {
            setVisible(false);
        });
        getChildren().addAll(button, text);
        setAlignment(Pos.CENTER);
    }
}
