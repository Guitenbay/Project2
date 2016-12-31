package JungleChess;

import java.util.Objects;

/**
 * Created bx Tenbax on 2016/11/28.
 */
public class Animal {
    private int power;
    private int x;
    private int y;
    private String side;
    private char home;
    private char trap;

    public Animal() {
    }

    public Animal(int power, String side) {
        this.power = power;
        this.setSide(side);
    }

    public boolean isMove(char direction, Animal[][] animals, char[][] tiles) {
        boolean movable = true;
        int i = 0;
        int j = 0;
        switch (direction) {
            case 'w':
                i = this.y - 1;
                j = this.x;
                break;
            case 'a':
                i = this.y;
                j = this.x - 1;
                break;
            case 's':
                i = this.y + 1;
                j = this.x;
                break;
            case 'd':
                i = this.y;
                j = this.x + 1;
                break;
            default:
                break;
        }
        if (i < 0 || j < 0 || i > 6 || j > 8) {
            System.out.println("动物不能超出边界");
            movable = false;
        } else if (this.getSide().equals(animals[i][j].getSide())) {
            System.out.print("与己方动物冲突，请再次输入：\n");
            movable = false;
        } else if (this.getHome() == tiles[i][j]) {
            System.out.println("己方动物不能进家！");
            movable = false;
        } else if ((this.getPower() == 6 || this.getPower() == 7) && tiles[i][j] == '1') {
            switch (direction) {
                case 'w':
                    if (this.getSide().equals(animals[y - 3][x].getSide())) {
                        System.out.print("与己方动物冲突，请再次输入：\n");
                        movable = false;
                    } else if (this.power < animals[y - 3][x].getPower()) {
                        System.out.println("你吃不了它，快跑！");
                        movable = false;
                    } else if (animals[y - 1][x].getPower() == 0 && animals[y - 2][x].getPower() == 0) {
                        movable = true;
                    } else {
                        System.out.println("有鼠阻挡，不能过河");
                        movable = false;
                    }
                    break;
                case 'a':
                    if (this.getSide().equals(animals[y][x - 4].getSide())) {
                        System.out.print("与己方动物冲突，请再次输入：\n");
                        movable = false;
                    } else if (this.power < animals[y][x - 4].getPower()) {
                        System.out.println("你吃不了它，快跑！");
                        movable = false;
                    } else if (animals[y][x - 1].getPower() == 0 && animals[y][x -2].getPower() == 0 && animals[y][x - 3].getPower() == 0) {
                        movable = true;
                    } else {
                        System.out.println("有鼠阻挡，不能过河");
                        movable = false;
                    }
                    break;
                case 's':
                    if (this.getSide().equals(animals[y + 3][x].getSide())) {
                        System.out.print("与己方动物冲突，请再次输入：\n");
                        movable = false;
                    } else if (this.power < animals[y + 3][x].getPower()) {
                        System.out.println("你吃不了它，快跑！");
                        movable = false;
                    } else if (animals[y + 1][x].getPower() == 0 && animals[y + 2][x].getPower() == 0) {
                        movable = true;
                    } else {
                        System.out.println("有鼠阻挡，不能过河");
                        movable = false;
                    }
                    break;
                case 'd':
                    if (this.getSide().equals(animals[y][x + 4].getSide())) {
                        System.out.print("与己方动物冲突，请再次输入：\n");
                        movable = false;
                    } else if (this.power < animals[y][x + 4].getPower()) {
                        System.out.println("你吃不了它，快跑！");
                        movable = false;
                    } else if (animals[y][x + 1].getPower() == 0 && animals[y][x + 2].getPower() == 0 && animals[y][x + 3].getPower() == 0) {
                        movable = true;
                    } else {
                        System.out.println("有鼠阻挡，不能过河");
                        movable = false;
                    }
                    break;
                default:
                    break;
            }
        } else if (this.getPower() != 1 && tiles[i][j] == '1') {
            System.out.println("该动物不能进入水中！");
            movable = false;
        } else if (this.getTrap() == tiles[i][j]) {
            movable = true;
        } else if ((animals[i][j].getPower() == 8 && this.getPower() == 1) && tiles[y][x] != '1') {
            movable = true;
        } else if (animals[i][j].getPower() == 1 && this.getPower() == 8) {
            System.out.println("你吃不了它，快跑！");
            movable = false;
        } else if (animals[i][j].getPower() <= this.getPower()) {
            movable = true;
        } else if (animals[i][j].getPower() > this.getPower()) {
            System.out.println("你吃不了它，快跑！");
            movable = false;
        }
        return movable;
    }

    public void battle(char direction, Animal[][] animals, char[][] tiles)  {
        int i = 0;
        int j = 0;
        switch (direction) {
            case 'w':
                i = this.y - 1;
                j = this.x;
                break;
            case 'a':
                i = this.y;
                j = this.x - 1;
                break;
            case 's':
                i = this.y + 1;
                j = this.x;
                break;
            case 'd':
                i = this.y;
                j = this.x + 1;
                break;
            default:
                break;
        }
        if (i < 0 || j < 0 || i > 6 || j > 8) {
            System.out.println("动物不能超出边界");
        } else if (this.getSide().equals(animals[i][j].getSide())) {
            System.out.print("与己方动物冲突，请再次输入：\n");
        } else if (this.getHome() == tiles[i][j]) {
            System.out.println("己方动物不能进家！");
        } else if ((this.getPower() == 6 || this.getPower() == 7) && tiles[i][j] == '1') {
            switch (direction) {
                case 'w':
                    if (this.getSide().equals(animals[y - 3][x].getSide())) {
                        System.out.print("与己方动物冲突，请再次输入：\n");
                    } else if (this.power < animals[y - 3][x].getPower()) {
                        System.out.println("你吃不了它，快跑！");
                    } else if (animals[y - 1][x].getPower() == 0 && animals[y - 2][x].getPower() == 0) {
                        animals[y - 3][x].setSide(this.getSide());
                        animals[y - 3][x].setPower(this.getPower());
                        animals[y - 3][x].setTrap(this.getTrap());
                        animals[y - 3][x].setHome(this.getHome());
                        this.setPower(0);
                        this.setHome('0');
                        this.setSide(null);
                        this.setTrap('0');
                    } else {
                        System.out.println("有鼠阻挡，不能过河");
                    }
                    break;
                case 'a':
                    if (this.getSide().equals(animals[y][x - 4].getSide())) {
                        System.out.print("与己方动物冲突，请再次输入：\n");
                    } else if (this.power < animals[y][x - 4].getPower()) {
                        System.out.println("你吃不了它，快跑！");
                    } else if (animals[y][x - 1].getPower() == 0 && animals[y][x -2].getPower() == 0 && animals[y][x - 3].getPower() == 0) {
                        animals[y][x - 4].setSide(this.getSide());
                        animals[y][x - 4].setPower(this.getPower());
                        animals[y][x - 4].setTrap(this.getTrap());
                        animals[y][x - 4].setHome(this.getHome());
                        this.setPower(0);
                        this.setHome('0');
                        this.setSide(null);
                        this.setTrap('0');
                    } else {
                        System.out.println("有鼠阻挡，不能过河");
                    }
                    break;
                case 's':
                    if (this.getSide().equals(animals[y + 3][x].getSide())) {
                        System.out.print("与己方动物冲突，请再次输入：\n");
                    } else if (this.power < animals[y + 3][x].getPower()) {
                        System.out.println("你吃不了它，快跑！");
                    } else if (animals[y + 1][x].getPower() == 0 && animals[y + 2][x].getPower() == 0) {
                        animals[y + 3][x].setSide(this.getSide());
                        animals[y + 3][x].setPower(this.getPower());
                        animals[y + 3][x].setTrap(this.getTrap());
                        animals[y + 3][x].setHome(this.getHome());
                        this.setPower(0);
                        this.setHome('0');
                        this.setSide(null);
                        this.setTrap('0');
                    } else {
                        System.out.println("有鼠阻挡，不能过河");
                    }
                    break;
                case 'd':
                    if (this.getSide().equals(animals[y][x + 4].getSide())) {
                        System.out.print("与己方动物冲突，请再次输入：\n");
                    } else if (this.power < animals[y][x + 4].getPower()) {
                        System.out.println("你吃不了它，快跑！");
                    } else if (animals[y][x + 1].getPower() == 0 && animals[y][x + 2].getPower() == 0 && animals[y][x + 3].getPower() == 0) {
                        animals[y][x + 4].setSide(this.getSide());
                        animals[y][x + 4].setPower(this.getPower());
                        animals[y][x + 4].setTrap(this.getTrap());
                        animals[y][x + 4].setHome(this.getHome());
                        this.setPower(0);
                        this.setHome('0');
                        this.setSide(null);
                        this.setTrap('0');
                    } else {
                        System.out.println("有鼠阻挡，不能过河");
                    }
                    break;
                default:
                    break;
            }
        } else if (this.getPower() != 1 && tiles[i][j] == '1') {
            System.out.println("该动物不能进入水中！");
        } else if (this.getTrap() == tiles[i][j]) {

            animals[i][j].setSide(this.getSide());
            animals[i][j].setPower(this.getPower());
            animals[i][j].setTrap(this.getTrap());
            animals[i][j].setHome(this.getHome());
            this.setPower(0);
            this.setHome('0');
            this.setSide(null);
            this.setTrap('0');
        } else if ((animals[i][j].getPower() == 8 && this.getPower() == 1) && tiles[y][x] != '1') {

            animals[i][j].setSide(this.getSide());
            animals[i][j].setPower(this.getPower());
            animals[i][j].setTrap(this.getTrap());
            animals[i][j].setHome(this.getHome());
            this.setPower(0);
            this.setHome('0');
            this.setSide(null);
            this.setTrap('0');
        } else if (animals[i][j].getPower() == 1 && this.getPower() == 8) {
            System.out.println("你吃不了它，快跑！");
        } else if (animals[i][j].getPower() <= this.getPower()) {

            animals[i][j].setSide(this.getSide());
            animals[i][j].setPower(this.getPower());
            animals[i][j].setTrap(this.getTrap());
            animals[i][j].setHome(this.getHome());
            this.setPower(0);
            this.setHome('0');
            this.setSide(null);
            this.setTrap('0');
        } else if (animals[i][j].getPower() > this.getPower()) {
            System.out.println("你吃不了它，快跑！");
        }
    }

    public static boolean isfindAnimal(Animal[][] animals,int power, String side) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (animals[i][j].getPower() == power && Objects.equals(animals[i][j].getSide(), side)) {
                    return true;
                }
            }
        }
        return false;
    }

//    public static char[][] readFile(Scanner input) {
//        char[][] array = new char[7][9];
//        String str = "";
//        for (int i = 0; i < 7; i++) {
//            str += input.nextLine();
//        }
//
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 9; j++) {
//                array[i][j] = str.charAt(i * 9 + j);
//            }
//        }
//        return array;
//    }

    public static char getDirection(int i, int j, int k, int l) {
        if (i == k) {
            if (l < j)
                return  'd';
            else
                return  'a';
        } else {
            if (k < i)
                return  's';
            else
                return  'w';
        }
    }

    public static char[][] getAnimalsChar(Animal[][] animals) {
        char[][] animalsChar = new char[7][9];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                animalsChar[i][j] = String.valueOf(animals[i][j].getPower()).charAt(0);
            }
        }
        return animalsChar;
    }

    public int getPower() {
        return power;
    }

    protected void setPower(int power) {
        this.power = power;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
        if (Objects.equals(side, "Left")) {
            this.home = '3';
            this.trap = '2';
        } else if (Objects.equals(side, "Right")) {
            this.home = '5';
            this.trap = '4';
        }
    }

    protected char getHome() {
        return home;
    }

    protected void setHome(char home) {
        this.home = home;
    }

    protected char getTrap() {
        return trap;
    }

    protected void setTrap(char trap) {
        this.trap = trap;
    }
}