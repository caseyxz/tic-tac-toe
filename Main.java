package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static String[][] grid = new String[3][3];
    static int moveRow;
    static int moveColumn;
    static int player = 0;
    static boolean moveCorrect;
    static boolean xWon = false;
    static boolean oWon = false;
    static boolean impossible = false;
    static boolean draw = false;

    public static void main(String[] args) {

        startGame();

    }

    public static void startGame() {
        System.out.println("Let's start the game!");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "_";

            }
        }
        getGrid();

        while (!impossible && !xWon && !oWon && !draw){
            makeMove();
            gameState();
        }
    }
    public static void makeMove(){

        if (player % 2 == 0){
            System.out.println("It's player X turn.");
        } else {
            System.out.println("It's player O turn.");
        }

        checkMove();
        if (moveCorrect) {
            if (player % 2 == 0){
                grid[moveRow - 1][moveColumn - 1] = "X";
            } else {
                grid[moveRow - 1][moveColumn - 1] = "O";
            }
            getGrid();
            player++;
        } else {
            makeMove();
        }
    }
    public static void checkMove() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Which cell do you choose?");
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
                System.out.println("You should enter numbers!"); // prevents incorrect input type
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
                    line = "" + grid[0][0] + grid[0][1] + grid[0][2];
                    break;
                case 1:
                    line = "" + grid[0][0] + grid[1][0] + grid[2][0];
                    break;
                case 2:
                    line = "" + grid[0][0] + grid[1][1] + grid[2][2];
                    break;
                case 3:
                    line = "" + grid[0][1] + grid[1][1] + grid[2][1];
                    break;
                case 4:
                    line = "" + grid[0][2] + grid[1][2] + grid[2][2];
                    break;
                case 5:
                    line = "" + grid[1][0] + grid[1][1] + grid[1][2];
                    break;
                case 6:
                    line = "" + grid[2][0] + grid[2][1] + grid[2][2];
                    break;
                case 7:
                    line = "" + grid[0][2] + grid[1][1] + grid[2][0];
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
            System.out.println("The game became impossible to continue.");
        }
        else if (draw) {
            System.out.println("It's a draw.");
        }
        else if (xWon) {
            System.out.println("Player X wins.\nCongratulations!");
        }
        else if (oWon) {
            System.out.println("Player O wins.\nCongratulations!");
        }
        else {
            System.out.println("Game is not finished yet.");
        }
    }
}


