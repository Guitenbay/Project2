package JungleChessFX;/**
 * Created by Tenbay on 2016/12/31.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class JunglePanes extends Application {
    private Pane bigPane;
    private Pane[] pages;
    private EasingProperty animation;
    private Scene scene;
    private int currentIndex = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
//        pages[0] = new AboutPane();
//        pages[1] = new WelcomePane();
//        pages[2] = new JungleGamePane();
        JungleGamePane jungleGamePane = new JungleGamePane();
       // animation = new EasingProperty(bigPane.layoutXProperty());
       // animation.setEaseIn(true);
//        for(int i = 0;i < pages.length;i++) {
//            bigPane.getChildren().add(pages[i]);
//            pages[i].layoutXProperty().bind(scene.widthProperty().multiply(i));
//            pages[i].prefWidthProperty().bind(scene.widthProperty());
//            pages[i].prefHeightProperty().bind(scene.heightProperty());
//        }
//        bigPane.widthProperty().addListener(ov -> {
//            animation.setValueImmediately(-currentIndex * scene.getWidth());
//        });


        Scene scene = new Scene(jungleGamePane, 1200, 800);
        primaryStage.setTitle("Jungle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void switchToPage(int index){
        animation.setToValue(-index * scene.getWidth());
        currentIndex = index;
    }
}
