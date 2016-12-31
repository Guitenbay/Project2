package JungleChess;

import java.util.ArrayList;

import static JungleChess.Jungle.*;

/**
 * Created by Tenbay on 2016/11/28.
 */
public class GameHistory {
    private ArrayList<String> boardHistory;
    private int currentStep;
    private int finalStep;

    public GameHistory() {
        this.boardHistory = new ArrayList<>();
    }

    public void addRecord(char[][] board) {
        String str = "";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                str += board[i][j];
            }
        }
        boardHistory.add(currentStep++, str);
        finalStep++;
    }

    public Animal[][] undo() {
        Animal[][] animals;
        char[][] board = getChars(boardHistory.get(--currentStep));
        animals = connectAnimalWithBoard(board);
        finalStep--;
        return animals;
    }

    public Animal[][] redo() {
        Animal[][] animals;
        char[][] board = getChars(boardHistory.get(++currentStep));
        animals = connectAnimalWithBoard(board);
        finalStep++;
        return animals;
    }

    public Animal[][] restart() {
        Animal[][] animals;
        char[][] board = getChars(boardHistory.get(0));
        animals = connectAnimalWithBoard(board);
        this.currentStep = this.finalStep = 0;
        return animals;
    }

    public char[][] getChars(String string) {
        char[][] array = new char[7][9];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                array[i][j] = string.charAt(i * 9 + j);
            }
        }
        return array;
    }

}
