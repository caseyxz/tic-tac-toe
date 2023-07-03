package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static String[][] grid = new String[3][3];
    static String input;
    static int moveRow;
    static int moveColumn;
    static boolean moveCorrect;
    static boolean xWon = false;
    static boolean oWon = false;
    static boolean impossible = false;
    static boolean draw = false;

    public static void main(String[] args) {

        inputGameState();
        //gameState();
        makeMove();

    }

    public static void inputGameState() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        int index = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = String.valueOf(input.charAt(index));
                index++;
            }
        }
        getGrid();
    }
    public static void makeMove(){

        checkMove();
        if (moveCorrect) {
            grid[moveRow - 1][moveColumn - 1] = "X";
            getGrid();
        } else {
            makeMove();
        }
    }
    public static void checkMove() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                moveRow = scanner.nextInt();
                moveColumn = scanner.nextInt();

                if (moveRow < 1 || moveRow > 3 || moveColumn < 1 || moveColumn > 3) {
                    System.out.println("Coordinates should be from 1 to 3!"); // prevents index out of bounds
                } else if (!grid[moveRow - 1][moveColumn - 1].equals("_")) {
                    System.out.println("This cell is occupied! Choose another one!"); // prevents overriding moves
                } else {
                    moveCorrect = true;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!"); //prevents incorrect input type
                scanner.nextLine(); // consumes the remaining input
            }
        }
    }

    public static void getGrid() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {

                System.out.print(grid[i][j] + " ");

            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public static void checkImpossible() {
        int totalX = 0;
        int totalO = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].equals("X")) {
                    totalX++;
                } else if (grid[i][j].equals("O")) {
                    totalO++;
                }
            }
        }

        int difference = totalO - totalX;
        if (difference < -1 || difference > 1 || (xWon && oWon)) {
            impossible = true;

        }

    }
    public static void checkWin() {


        for (int a = 0; a < 8; a++) {

            String line = null;

            switch (a) {
                case 0:
                    line = "" + input.charAt(0) + input.charAt(1) + input.charAt(2);
                    break;
                case 1:
                    line = "" + input.charAt(3) + input.charAt(4) + input.charAt(5);
                    break;
                case 2:
                    line = "" + input.charAt(6) + input.charAt(7) + input.charAt(8);
                    break;
                case 3:
                    line = "" + input.charAt(0) + input.charAt(3) + input.charAt(6);
                    break;
                case 4:
                    line = "" + input.charAt(1) + input.charAt(4) + input.charAt(7);
                    break;
                case 5:
                    line = "" + input.charAt(2) + input.charAt(5) + input.charAt(8);
                    break;
                case 6:
                    line = "" + input.charAt(0) + input.charAt(4) + input.charAt(8);
                    break;
                case 7:
                    line = "" + input.charAt(2) + input.charAt(4) + input.charAt(6);
                    break;
            }

            if (line.equals("XXX")) {
                xWon = true;
            } else if (line.equals("OOO")) {
                oWon = true;
            }

        }
    }

    public static void checkDraw() {
        int emptySpaces = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j].equals("_")) {
                    emptySpaces++;

                }
            }
        }
        if (emptySpaces == 0 && !(xWon || oWon) && !impossible){
            draw = true;
        }
    }

    public static void gameState(){
        checkWin();
        checkImpossible();
        checkDraw();

        if (impossible) {
            System.out.println("Impossible");
        }
        else if (draw) {
            System.out.println("Draw");
        }
        else if (xWon) {
            System.out.println("X wins");
        }
        else if (oWon) {
            System.out.println("O wins");
        }
        else {
            System.out.println("Game not finished");
        }
    }
}


