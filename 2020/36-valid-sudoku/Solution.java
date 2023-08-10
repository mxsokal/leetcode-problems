import java.util.Arrays;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following
        rules:
        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        The Sudoku board could be partially filled, where empty cells are filled with the character '.'.        
        A Sudoku board (partially filled) could be valid but is not necessarily solvable.
        Only the filled cells need to be validated according to the mentioned rules.
        The given board contain only digits 1-9 and the character '.'.
        The given board size is always 9x9.

        Examples:

            Input: [
                      ["5","3",".",".","7",".",".",".","."],
                      ["6",".",".","1","9","5",".",".","."],
                      [".","9","8",".",".",".",".","6","."],
                      ["8",".",".",".","6",".",".",".","3"],
                      ["4",".",".","8",".","3",".",".","1"],
                      ["7",".",".",".","2",".",".",".","6"],
                      [".","6",".",".",".",".","2","8","."],
                      [".",".",".","4","1","9",".",".","5"],
                      [".",".",".",".","8",".",".","7","9"]
                    ]
            Output: true

    Time  complexity: O(1), or O(n*m) if board is not sudoku 9*9
    Space complexity: O(1),- or O(n + m) if board is not sudoku 9*9
*/
public final class Solution {

    private static final int BOARD_SIZE = 9;
    private static final int DIGIT_COUNT = 9;
    private static final int SUBBOARD_COUNT = 9;
    private static final int SUBBOARD_PER_ROW_COUNT = 3;
    private static final int SUBBOARD_PER_COLUMN_COUNT = 3;
    private static final char EMPTY_CELL = '.';
    private static final int NUMBER_BASE = 10;

    public boolean solve(char[][] board) {
        int[][] rowDigits = createMatrix(BOARD_SIZE, DIGIT_COUNT);
        int[][] columnDigits = createMatrix(BOARD_SIZE, DIGIT_COUNT);
        int[][] subboardDigits = createMatrix(SUBBOARD_COUNT, DIGIT_COUNT);
        boolean result = true;
        int subboardIndex;
        char character;
        int digit;

        requireNonNull(board, "board is null");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                character = board[i][j];
                if (character != EMPTY_CELL) {
                    if (!Character.isDigit(character)) {
                        result = false;
                        break;
                    }
                    digit = Character.digit(character, NUMBER_BASE);
                    subboardIndex = (j / SUBBOARD_PER_ROW_COUNT)
                        + ((i / SUBBOARD_PER_COLUMN_COUNT) * SUBBOARD_PER_ROW_COUNT);
                    if ((digit == 0)
                        || (rowDigits[i][digit - 1] == 1)
                        || (columnDigits[j][digit - 1] == 1)
                        || (subboardDigits[subboardIndex][digit - 1] == 1)) {
                            result = false;
                            break;
                        }
                        rowDigits[i][digit - 1] = 1;
                        columnDigits[j][digit - 1] = 1;
                        subboardDigits[subboardIndex][digit - 1] = 1;
                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    private int[][] createMatrix(int rowCount, int columnCount) {
        int[][] result = new int[rowCount][];

        for (int i = 0; i < rowCount; i++) {
            result[i] = new int[columnCount];
        }
        return result;
    }

/*
    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    private void print(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] board = new char[9][];
        char[] defaultChars = new char[]{'.','.','.','.','.','.','.','.','.'};
        char[] rowChars;

        for (int i = 0; i < 9; i++) {
            board[i] = new char[9];
            rowChars = (args.length > i) ? args[i].toCharArray() : defaultChars;
            for (int j = 0; j < 9; j++) {
                board[i][j] = (j < rowChars.length) ? rowChars[j] : '.';
            }
        }

        System.out.println(s.solve(board));

    }
*/
}