package JungleChessFX;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Tenbay on 2016/12/31.
 */
public class FunctionPane extends Pane implements HelpBox {
    private Background backGray = new Background(new BackgroundFill(Color.grayRgb(200, 0.6), new CornerRadii(5), new Insets(10, 10, 10, 10)));
    private Background buttonGray = new Background(new BackgroundFill(Color.grayRgb(100, 0.6), new CornerRadii(20), new Insets(10, 30, 10, 30)));
    private Background backBtGray = new Background(new BackgroundFill(Color.grayRgb(100, 0.4), new CornerRadii(20), new Insets(10, 30, 10, 30)));
    private Button undoBt;
    private Button redoBt;
    private Button saveBt;
    private Button readBt;
    private Button helpBt;
    private Button restartBt;
    private Button exitBt;
    private Button addBt;
    VBox vBox;

    FunctionPane() {
        vBox = new VBox();
        vBox.setBackground(backGray);
        vBox.prefWidthProperty().bind(this.widthProperty());
        vBox.prefHeightProperty().bind(this.heightProperty());
        ImageView imageView = new ImageView("image/叉.png");
        imageView.fitWidthProperty().bind(this.widthProperty().divide(15));
        imageView.fitHeightProperty().bind(this.widthProperty().divide(15));
        imageView.setEffect(new DropShadow(20, Color.BLACK));
        addBt = new Button("", imageView);
        addBt.setStyle("-fx-background-color:transparent;");
        undoBt = makeButton("U N D O");
        redoBt = makeButton("R E D O");
        saveBt = makeButton("S A V E");
        readBt = makeButton("R E A D");
        helpBt = makeButton("H E L P !");
        restartBt = makeButton("R E S T A R T ! !");
        exitBt = makeButton("E X I T ! ! !");
        VBox helpBox = createHelpBox();
        helpBox.prefWidthProperty().bind(this.widthProperty());
        helpBox.prefHeightProperty().bind(this.heightProperty());
        helpBox.setPadding(new Insets(23));
        helpBox.setVisible(false);
        EnsurePane ensureExit = new EnsurePane("Do you really wanna E X I T ?\n\n");
        ensureExit.setVisible(false);
        ensureExit.prefWidthProperty().bind(this.widthProperty());
        ensureExit.prefHeightProperty().bind(this.heightProperty());

        vBox.getChildren().addAll(addBt, undoBt, redoBt, saveBt, readBt, helpBt, restartBt, exitBt);
        vBox.setAlignment(Pos.CENTER);
        getChildren().addAll(vBox, helpBox, ensureExit);
        helpBt.setOnAction(e -> {
            vBox.setVisible(false);
            helpBox.setVisible(true);
        });
        exitBt.setOnAction(e -> {
            ensureExit.setVisible(true);
            ensureExit.getSureBt().setOnAction(e1 -> {
                System.exit(0);
            });
            ensureExit.getBackBt().setOnAction(e2 -> {
                ensureExit.setVisible(false);
            });
        });
    }

    @Override
    public VBox createHelpBox() {
        VBox helpBox = new VBox();
        helpBox.setBackground(backGray);
        ImageView imageView = new ImageView("image/叉.png");
        imageView.fitWidthProperty().bind(this.widthProperty().divide(15));
        imageView.fitHeightProperty().bind(this.widthProperty().divide(15));
        imageView.setEffect(new DropShadow(20, Color.BLACK));
        Button addBt = new Button("", imageView);
        addBt.setStyle("-fx-background-color:transparent;");
        Text text1 = makeText("等级大小 ：");
        Text text2 = makeText("特殊吃法 ：");
        Text text3 = makeText("玩法 ：");
        Text text4 = makeText("胜负 ：");
        ImageView imageView1 = new ImageView("image/pic/斗兽棋1.PNG");
        imageView1.setOpacity(0.6);
        ImageView imageView2 = new ImageView("image/pic/斗兽棋2.PNG");
        imageView2.setOpacity(0.6);
        ImageView imageView3 = new ImageView("image/pic/斗兽棋3.PNG");
        imageView3.setOpacity(0.6);
        ImageView imageView4 = new ImageView("image/pic/斗兽棋4.PNG");
        imageView4.setOpacity(0.6);
        helpBox.getChildren().addAll(addBt, text1, imageView1, text2, imageView2, text3, imageView3, text4, imageView4);
        helpBox.setAlignment(Pos.TOP_CENTER);
        addBt.setOnAction(e -> {
            helpBox.setVisible(false);
            vBox.setVisible(true);
        });
        return helpBox;
    }
    @Override
    public Text makeText(String string) {
        Text text = new Text(string);
        text.setFill(Color.grayRgb(250));
        text.setStroke(Color.grayRgb(250));
        text.setFont(Font.font("Times New Roman", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 40));
        return text;
    }

    private Button makeButton(String string) {
        Button bt = new Button(string);
        bt.setTextFill(Color.WHITE);
        bt.prefHeightProperty().bind(this.heightProperty().divide(8.5));
        bt.prefWidthProperty().bind(this.widthProperty().divide(0.8));
        bt.setBackground(backBtGray);
        //bt.setEffect(new DropShadow(10, Color.gray(0.9)));

        bt.addEventFilter(
                MouseEvent.MOUSE_ENTERED_TARGET,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        bt.setBackground(buttonGray);
                    }
                }
        );

        bt.addEventFilter(
                MouseEvent.MOUSE_EXITED_TARGET,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        bt.setBackground(backBtGray);
                    }
                }
        );

        bt.addEventFilter(
                MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        bt.setBackground(backBtGray);
                    }
                }
        );
        return bt;
    }

    public Button getAddBt() {
        return this.addBt;
    }

    public Button getUndoBt() {
        return this.undoBt;
    }

    public Button getRedoBt() {
        return this.redoBt;
    }

    public Button getSaveBt() {
        return this.saveBt;
    }

    public Button getReadBt() {
        return this.readBt;
    }

    public Button getRestartBt() {
        return this.restartBt;
    }

}

