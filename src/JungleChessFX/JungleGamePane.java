package JungleChessFX;/**
 * Created by Tenbay on 2016/12/26.
 */

import JungleChess.Animal;
import JungleChess.GameHistory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static JungleChess.Animal.getDirection;
import static JungleChess.Jungle.*;

public class JungleFX extends Application {
    private static final char MOUSE = '1';
    private static final char CAT = '2';
    private static final char WOLF = '3';
    private static final char DOG = '4';
    private static final char LEOPARD = '5';
    private static final char TIGER = '6';
    private static final char LION = '7';
    private static final char ELEPHANT = '8';
    char[][] animalsChar = readFile(new Scanner(new File("animal.txt")));
    char[][] tiles = readFile(new Scanner(new File("tile.txt")));
    private static final Color blueLight = Color.rgb(102, 204, 255, 1.0);

    public JungleFX() throws FileNotFoundException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    static Pane pane = new Pane();
    private boolean player;
    private String boardSide;
    private GameHistory gameHistory = new GameHistory();
    private Rectangle[][] backRectangle = new Rectangle[7][9];
    private ImageView[][] animalView = new ImageView[7][9];
    private Rectangle[][] upperRectangle = new Rectangle[7][9];
    private Animal[][] animals = connectAnimalWithBoard(animalsChar);

    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("image/pic/Map3.png", true);
        ImageView imageViewBackground = new ImageView(image);

        imageViewBackground.fitWidthProperty().bind(pane.widthProperty());
        imageViewBackground.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(imageViewBackground);

        gameHistory.addRecord(animalsChar);

        createBackRectangle(backRectangle);

        createAnimalView(animals);

        createUpperRectangle(upperRectangle);

        player = true;
        boardSide = "Right";
        // The left side animal will have action when player is true
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (!boardSide.equals(animals[i][j].getSide()))
                    animalView[i][j].setEffect(new DropShadow(30, Color.web("0xffff00")));
                upperRectangle[i][j].setOnMouseClicked(new AnimalHandler(i, j));
            }
        }

        Scene scene = new Scene(pane, 1200, 800);
        primaryStage.setTitle("JungleFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createBackRectangle(Rectangle[][] rectangles) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                rectangles[i][j] = new Rectangle();
                rectangles[i][j].setFill(blueLight);
                rectangles[i][j].setStroke(blueLight);
                rectangles[i][j].heightProperty().bind(pane.heightProperty().divide(10.1 * 7 / 8.6));
                rectangles[i][j].widthProperty().bind(pane.widthProperty().divide(13.6));
                rectangles[i][j].layoutYProperty().bind(pane.heightProperty().divide(7 * 10.1 / (12 + 8.07 * i)));
                rectangles[i][j].layoutXProperty().bind(pane.widthProperty().divide(13.8 / (2.05 + 1.1 * j)));
                rectangles[i][j].setEffect(new GaussianBlur(500));
                rectangles[i][j].setVisible(false);
                pane.getChildren().add(rectangles[i][j]);
            }
        }
    }

    private void createAnimalView(Animal[][] animals) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                String side = animals[i][j].getSide();
                int power = animals[i][j].getPower();
                switch (String.valueOf(power).charAt(0)) {
                    case MOUSE:
                        animalView[i][j] = new ImageView("image/pic/animals/" + side + "/1Mouse" + side + ".png");
                        break;
                    case CAT:
                        animalView[i][j] = new ImageView("image/pic/animals/" + side + "/2Cat" + side + ".png");
                        break;
                    case WOLF:
                        animalView[i][j] = new ImageView("image/pic/animals/" + side + "/3Wolf" + side + ".png");
                        break;
                    case DOG:
                        animalView[i][j] = new ImageView("image/pic/animals/" + side + "/4Dog" + side + ".png");
                        break;
                    case LEOPARD:
                        animalView[i][j] = new ImageView("image/pic/animals/" + side + "/5Leopard" + side + ".png");
                        break;
                    case TIGER:
                        animalView[i][j] = new ImageView("image/pic/animals/" + side + "/6Tiger" + side + ".png");
                        break;
                    case LION:
                        animalView[i][j] = new ImageView("image/pic/animals/" + side + "/7Lion" + side + ".png");
                        break;
                    case ELEPHANT:
                        animalView[i][j] = new ImageView("image/pic/animals/" + side + "/8Elephant" + side + ".png");
                        break;
                    default:
                        animalView[i][j] = new ImageView();
                        break;
                }
                animalView[i][j].fitHeightProperty().bind(pane.heightProperty().divide(10));
                animalView[i][j].fitWidthProperty().bind(pane.widthProperty().divide(10));
                animalView[i][j].layoutYProperty().bind(pane.heightProperty().divide(7 * 10.1 / (12 + 8.07 * i)));
                animalView[i][j].layoutXProperty().bind(pane.widthProperty().divide(13.8 / (1.85 + 1.1 * j)));
                animalView[i][j].setEffect(new DropShadow(20, Color.BLACK));
                pane.getChildren().add(animalView[i][j]);
            }
        }
    }

    private void createUpperRectangle(Rectangle[][] rectangles) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                rectangles[i][j] = new Rectangle();
                rectangles[i][j].setFill(Color.color(1.0, 1.0, 1.0, 0.0));
                rectangles[i][j].setStroke(Color.color(1.0, 1.0, 1.0, 0.0));
                rectangles[i][j].heightProperty().bind(pane.heightProperty().divide(10.1 * 7 / 8.6));
                rectangles[i][j].widthProperty().bind(pane.widthProperty().divide(13.6));
                rectangles[i][j].layoutYProperty().bind(pane.heightProperty().divide(7 * 10.1 / (12 + 8.07 * i)));
                rectangles[i][j].layoutXProperty().bind(pane.widthProperty().divide(13.8 / (2.05 + 1.1 * j)));
                pane.getChildren().add(rectangles[i][j]);
            }
        }
    }

    //inner class
    class AnimalHandler implements EventHandler<MouseEvent> {
        private int i;
        private int j;

        AnimalHandler(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void handle(MouseEvent e) {
            if ((!boardSide.equals(animals[i][j].getSide()) && !backRectangle[i][j].isVisible()) || backRectangle[i][j].isVisible()) {

                if (backRectangle[i][j].isVisible())
                    battleAnimalView(i, j);
                else
                    lightBackground(i, j);
            }
        }
    }

    private void lightBackground(int i, int j) {
        if (animals[i][j].getPower() != 0 && !backRectangle[i][j].isVisible()) {
            for (int k = 0; k < 7; k++) {
                for (int l = 0; l < 9; l++) {
                    backRectangle[k][l].setVisible(false);
                }
            }
            backRectangle[i][j].setVisible(true);
            boolean up = animals[i][j].isMove('w', animals, tiles);
            boolean left = animals[i][j].isMove('a', animals, tiles);
            boolean right = animals[i][j].isMove('d', animals, tiles);
            boolean down = animals[i][j].isMove('s', animals, tiles);

//            printBoard(tiles, animals);
//            System.out.println("up:" + up + "\nleft:" + left + "\nright:" + right + "\ndown:" + down);

            if (up) {
                if ((tiles[i - 1][j] == '1' && animals[i][j].getPower() == 6) || (tiles[i - 1][j] == '1' && animals[i][j].getPower() == 7))
                    backRectangle[i - 3][j].setVisible(true);
                else
                    backRectangle[i - 1][j].setVisible(true);
            }
            if (left) {
                if ((tiles[i][j - 1] == '1' && animals[i][j].getPower() == 6) || (tiles[i][j - 1] == '1' && animals[i][j].getPower() == 7))
                    backRectangle[i][j - 4].setVisible(true);
                else
                    backRectangle[i][j - 1].setVisible(true);
            }
            if (right) {
                if ((tiles[i][j + 1] == '1' && animals[i][j].getPower() == 6) || (tiles[i][j + 1] == '1' && animals[i][j].getPower() == 7))
                    backRectangle[i][j + 4].setVisible(true);
                else
                    backRectangle[i][j + 1].setVisible(true);
            }
            if (down) {
                if ((tiles[i + 1][j] == '1' && animals[i][j].getPower() == 6) || (tiles[i + 1][j] == '1' && animals[i][j].getPower() == 7))
                    backRectangle[i + 3][j].setVisible(true);
                else
                    backRectangle[i + 1][j].setVisible(true);
            }
        } else if (!backRectangle[i][j].isVisible()
                || animals[i][j].getPower() != 0 && backRectangle[i][j].isVisible()) {
            for (int k = 0; k < 7; k++) {
                for (int l = 0; l < 9; l++) {
                    backRectangle[k][l].setVisible(false);
                }
            }
        }
    }

    private void battleAnimalView(int i, int j) {
        first:
        for (int k = 0; k < 7; k++) {
            for (int l = 0; l < 9; l++) {
                if (k != i || l != j) {
                    if (backRectangle[k][l].isVisible() && animals[k][l].getPower() != 0) {
                        animalView[k][l].layoutXProperty().unbind();
                        animalView[k][l].layoutYProperty().unbind();
                        animalView[i][j].layoutXProperty().unbind();
                        animalView[i][j].layoutYProperty().unbind();

//                        EasingProperty animationX = new EasingProperty(animalView[k][l].layoutXProperty());
//                        animationX.setEaseIn(false);
//                        animalView[k][l].setEffect(animationX.getBlur());
//                        animationX.setToValue(animalView[i][j].getLayoutX());
//                        EasingProperty animationY = new EasingProperty(animalView[k][l].layoutYProperty());
//                        animationY.setEaseIn(false);
//                        animalView[k][l].setEffect(animationY.getBlur());
//                        animationY.setToValue(animalView[i][j].getLayoutY());

                        animalView[i][j].setImage(animalView[k][l].getImage());
                        animalView[k][l].setImage(new ImageView().getImage());

                        animalView[k][l].layoutYProperty().bind(pane.heightProperty().divide(7 * 10.1 / (12 + 8.07 * k)));
                        animalView[k][l].layoutXProperty().bind(pane.widthProperty().divide(13.8 / (1.85 + 1.1 * l)));
                        animalView[i][j].layoutYProperty().bind(pane.heightProperty().divide(7 * 10.1 / (12 + 8.07 * i)));
                        animalView[i][j].layoutXProperty().bind(pane.widthProperty().divide(13.8 / (1.85 + 1.1 * j)));

                        animals[k][l].battle(getDirection(i, j, k, l), animals, tiles);
                        player = !player;
                        if (player)
                            boardSide = "Right";
                        else
                            boardSide = "Left";
                        for (int x = 0; x < 7; x++) {
                            for (int y = 0; y < 9; y++) {
                                animalView[x][y].setEffect(new DropShadow(20, Color.BLACK));
                                if (!boardSide.equals(animals[x][y].getSide()))
                                    animalView[x][y].setEffect(new DropShadow(30, Color.web("0xffff00")));
                            }
                        }
                        break first;
                    }
                }
            }
        }
        printBoard(tiles, animals);
        gameHistory.addRecord(makeAnimalsChar(animals));
        for (int k = 0; k < 7; k++) {
            for (int l = 0; l < 9; l++) {
                backRectangle[k][l].setVisible(false);
            }
        }
    }

}
