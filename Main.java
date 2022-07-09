package tictactoe;

import java.util.Scanner;

public class Main {

    final static int FIELD_HEIGHT = 3;
    final static int FIELD_WIDTH = 3;
    static char[][] field = new char[FIELD_HEIGHT][FIELD_WIDTH];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                field[i][j] = line.charAt(i * 3 + j);
            }
        }
        // print field state
        printField();

        // Check wins and impossible
        int threeXInHRow = checkThreeInHRow('X');
        int threeXInWRow = checkThreeInWRow('X');
        int threeOInHRow = checkThreeInHRow('O');
        int threeOInWRow = checkThreeInWRow('O');
        int threeXInDiagonal = checkThreeInDiagonal('X');
        int threeOInDiagonal = checkThreeInDiagonal('O');
        int numberOfX = calculateSymbol('X');
        int numberOfO = calculateSymbol('O');
        int numberOfEmpty = calculateSymbol('_');
        String state = "";
        // Check states
        if (numberOfX - numberOfO >= 2 || numberOfO - numberOfX >= 2 || (threeXInHRow > 0 && threeOInHRow >0 ) || (threeXInWRow > 0 && threeOInWRow > 0)) {
            state = "Impossible";
        }
        if ((threeXInHRow == 1 || threeXInWRow == 1 || threeXInDiagonal == 1) && state == "")
        {
            state = "X wins";
        }
        if ((threeOInHRow == 1 || threeOInWRow == 1 || threeOInDiagonal == 1) && state == "")
        {
            state = "O wins";
        }
        if (numberOfEmpty > 0 && state == "")
        {
            state = "Game not finished";
        }
        if ((threeXInHRow == 0 || threeXInWRow == 0) && numberOfEmpty == 0 && state == "")
        {
            state = "Draw";
        }
        System.out.println(state);

    }

    public static void printField() {
        System.out.println("---------");
        for (int i = 0; i < FIELD_HEIGHT; i++) {
            System.out.print("| ");
            for (int j = 0; j < FIELD_WIDTH; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static int checkThreeInHRow(char symbol) {
        int totalCounter = 0;
        for (int i = 0; i < FIELD_HEIGHT; i++) {
            int counter = 0;
            for (int j = 1; j < FIELD_WIDTH; j++) {
                if (field[i][j] == field[i][j - 1] && field[i][j] == symbol) {
                    counter++;
                }
            }
            if (counter == 2) {
                totalCounter++;
            }
        }
        return totalCounter;
    }

    public static int checkThreeInWRow(char symbol) {
        int totalCounter = 0;
        for (int i = 0; i < FIELD_HEIGHT; i++) {
            int counter = 0;
            for (int j = 1; j < FIELD_WIDTH; j++) {
                if (field[j][i] == field[j - 1][i] && field[j][i] == symbol) {
                    counter++;
                }
            }
            if (counter == 2) {
                totalCounter++;
            }
        }
        return totalCounter;
    }

    public static int checkThreeInDiagonal(char symbol) {
        if ((field[0][0] == field[1][1] && field[1][1] == field [2][2] && field[2][2] == symbol) || (field[2][0] == field[1][1] && field[1][1] == field [0][2] && field[0][2] == symbol)) {
            return 1;
        }
        return 0;
    }

    public static int calculateSymbol(char symbol) {
        int counter = 0;
        for (char[] ar: field) {
            for (char el: ar) {
                if (el == symbol) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
