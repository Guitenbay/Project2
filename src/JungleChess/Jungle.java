package JungleChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Tenbay on 2016/11/28.
 */
public class Jungle {
    private static final char MOUSE = '1';
    private static final char CAT = '2';
    private static final char WOLF = '3';
    private static final char DOG = '4';
    private static final char LEOPARD = '5';
    private static final char TIGER = '6';
    private static final char LION = '7';
    private static final char ELEPHANT = '8';

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        char[][] animalsChar = readFile(new Scanner(new File("animal.txt")));
        char[][] tiles = readFile(new Scanner(new File("tile.txt")));
        Animal[][] animals = connectAnimalWithBoard(animalsChar);

        while (true) {
            help();
            printBoard(tiles, animals);
            System.out.println("请左方玩家输入：");
            String input = scanner.nextLine();
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 3; j++) {
                    if (animals[i][j].getPower() == Integer.parseInt(String.valueOf(input.charAt(0)))) {
                        char dir1 = input.charAt(1);
                        char dir2 = input.charAt(2);
                        char dir3 = input.charAt(3);
                        char dir4 = input.charAt(4);
                        System.out.println(animals[i][j].isMove(dir1, animals, tiles));
                        System.out.println(animals[i][j].isMove(dir2, animals, tiles));
                        System.out.println(animals[i][j].isMove(dir3, animals, tiles));
                        System.out.println(animals[i][j].isMove(dir4, animals, tiles));
                    }
                }
            }
        }
    }

    public static void help() {
        System.out.println("指令介绍:\n" +
                "\n" +
                "1. 移动指令\n" +
                "        移动指令由两个部分组成。\n" +
                "        第一个部分是数字1-8,根据战斗力分别对应鼠(1),猫(2),狼(3),狗(4),豹(5),虎(6),狮(7),象(8)\n" +
                "        第二个部分是字母wasd中的一个,w对应上方向,a对应左方向,s对应下方向,d对应右方向\n" +
                "        比如指令 \"1d\" 表示鼠向右走, \"4w\" 表示狗向左走\n" +
                "\n" +
                "2. 游戏指令\n" +
                "        输入 restart 重新开始游戏\n" +
                "        输入 help 查看帮助\n" +
                "        输入 undo 悔棋\n" +
                "        输入 redo 取消悔棋\n" +
                "        输入 exit 退出游戏\n");
    }

    public static void exit() {
        System.exit(0);
    }

    public static void printBoard(char[][] tiles, Animal[][] animals) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (animals[i][j].getPower() != 0 && Objects.equals(animals[i][j].getSide(), "Left")) {
                    switch (animals[i][j].getPower()) {
                        case 1:
                            System.out.print("1鼠 ");
                            break;
                        case 2:
                            System.out.print("2猫 ");
                            break;
                        case 3:
                            System.out.print("3狼 ");
                            break;
                        case 4:
                            System.out.print("4狗 ");
                            break;
                        case 5:
                            System.out.print("5豹 ");
                            break;
                        case 6:
                            System.out.print("6虎 ");
                            break;
                        case 7:
                            System.out.print("7狮 ");
                            break;
                        case 8:
                            System.out.print("8象 ");
                            break;
                    }
                } else if (animals[i][j].getPower() != 0 && Objects.equals(animals[i][j].getSide(), "Right")) {
                    switch (animals[i][j].getPower()) {
                        case 1:
                            System.out.print(" 鼠1");
                            break;
                        case 2:
                            System.out.print(" 猫2");
                            break;
                        case 3:
                            System.out.print(" 狼3");
                            break;
                        case 4:
                            System.out.print(" 狗4");
                            break;
                        case 5:
                            System.out.print(" 豹5");
                            break;
                        case 6:
                            System.out.print(" 虎6");
                            break;
                        case 7:
                            System.out.print(" 狮7");
                            break;
                        case 8:
                            System.out.print(" 象8");
                            break;
                    }
                } else {
                    switch (tiles[i][j]){
                        case '0':
                            System.out.print(" 　 ");
                            break;
                        case '1':
                            System.out.print(" 水 ");
                            break;
                        case '2':
                            System.out.print(" 陷 ");
                            break;
                        case '3':
                            System.out.print(" 家 ");
                            break;
                        case '4':
                            System.out.print(" 陷 ");
                            break;
                        case '5':
                            System.out.print(" 家 ");
                            break;
                    }
                }
            }
            System.out.println();
        }
    }

    public static char[][] readFile(Scanner input) {
        char[][] array = new char[7][9];
        String str = "";
        for (int i = 0; i < 7; i++) {
            str += input.nextLine();
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                array[i][j] = str.charAt(i * 9 + j);
            }
        }
        return array;
    }

    public static char[][] makeAnimalsChar(Animal[][] animals) {
        char[][] animalsChar = new char[7][9];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                animalsChar[i][j] = ("" + animals[i][j].getPower()).charAt(0);
            }
        }
        return animalsChar;
    }

    public static Animal[][] connectAnimalWithBoard(char[][] animalsChar) {
        Animal[][] array = new Animal[7][9];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (j < 3) {
                    makeArrayOfAnimals(i, j, animalsChar, "Left", array);
                } else if (j > 5) {
                    makeArrayOfAnimals(i, j, animalsChar, "Right", array);
                } else {
                    Animal animal = new Animal();
                    animal.setX(j);
                    animal.setY(i);
                    array[i][j] = animal;
                }
            }
        }
        return array;
    }

    public static void makeArrayOfAnimals(int i, int j, char[][] animalsChar, String side, Animal[][] array) {
        switch (animalsChar[i][j]) {
            case MOUSE:
                Animal mouse = new Animal(1, side);
                mouse.setX(j);
                mouse.setY(i);
                array[i][j] = mouse;
                break;
            case CAT:
                Animal cat = new Animal(2, side);
                cat.setX(j);
                cat.setY(i);
                array[i][j] = cat;
                break;
            case WOLF:
                Animal wolf = new Animal(3, side);
                wolf.setX(j);
                wolf.setY(i);
                array[i][j] = wolf;
                break;
            case DOG:
                Animal dog = new Animal(4, side);
                dog.setX(j);
                dog.setY(i);
                array[i][j] = dog;
                break;
            case LEOPARD:
                Animal leopard = new Animal(5, side);
                leopard.setX(j);
                leopard.setY(i);
                array[i][j] = leopard;
                break;
            case TIGER:
                Animal tiger = new Animal(6, side);
                tiger.setX(j);
                tiger.setY(i);
                array[i][j] = tiger;
                break;
            case LION:
                Animal lion = new Animal(7, side);
                lion.setX(j);
                lion.setY(i);
                array[i][j] = lion;
                break;
            case ELEPHANT:
                Animal elephant = new Animal(8, side);
                elephant.setX(j);
                elephant.setY(i);
                array[i][j] = elephant;
                break;
            default:
                Animal animal = new Animal();
                animal.setX(j);
                animal.setY(i);
                array[i][j] = animal;
                break;
        }
    }
}
