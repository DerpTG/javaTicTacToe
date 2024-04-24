import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private static char[][] board;
    private static char currentPlayer = 'X';
    private static int boardSize;
    private static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the Tic Tac Toe board: ");
        boardSize = scanner.nextInt();
        board = new char[boardSize][boardSize];

        initializeBoard();
        char winner = '\0';

        while (winner == '\0') {
            displayBoard();
            if (currentPlayer == 'X') {
                getPlayerMove(scanner);
            } else {
                getComputerMove();
            }
            winner = checkWinner();
            switchPlayer();
        }

        displayBoard();
        if (winner == 'D') {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Player " + winner + " wins!");
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        System.out.print(" ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < boardSize; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
                if (j < boardSize - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.print("  ");
            if (i < boardSize - 1) {
                for (int j = 0; j < boardSize; j++) {
                    System.out.print("-");
                    if (j < boardSize - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    private static void getPlayerMove(Scanner scanner) {
        int row, col;
        do {
            System.out.print("Player " + currentPlayer + ", enter row (0 to " + (boardSize - 1) + "): ");
            row = scanner.nextInt();
            System.out.print("Player " + currentPlayer + ", enter column (0 to " + (boardSize - 1) + "): ");
            col = scanner.nextInt();
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
    }

    private static void getComputerMove() {
        int row, col;
        do {
            row = random.nextInt(boardSize);
            col = random.nextInt(boardSize);
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
        System.out.println("Computer placed " + currentPlayer + " on (" + row + ", " + col + ")");
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize && board[row][col] == ' ';
    }

    private static char checkWinner() {
        // Check all rows and columns
        for (int i = 0; i < boardSize; i++) {
            if (checkLine(0, i, 1, 0) || checkLine(i, 0, 0, 1)) {
                return currentPlayer;
            }
        }

        // Check diagonals
        if (checkLine(0, 0, 1, 1) || checkLine(0, boardSize - 1, 1, -1)) {
            return currentPlayer;
        }

        // Check for draw
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return '\0'; // Continue playing
                }
            }
        }
        return 'D'; // Draw
    }

    private static boolean checkLine(int startRow, int startCol, int rowStep, int colStep) {
        char first = board[startRow][startCol];
        if (first == ' ') return false;

        for (int i = 1; i < boardSize; i++) {
            if (board[startRow + i * rowStep][startCol + i * colStep] != first) {
                return false;
            }
        }
        return true;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
