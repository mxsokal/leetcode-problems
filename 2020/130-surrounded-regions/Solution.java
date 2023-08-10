import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
        A region is captured by flipping all 'O's into 'X's in that surrounded region.
        Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped
        to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
        Two cells are connected if they are adjacent cells connected horizontally or vertically.

        Examples:

            Input:
                    X X X X
                    X O O X
                    X X O X
                    X O X X

            Output:
                    X X X X
                    X X X X
                    X X X X
                    X O X X

    Time  complexity: O(n*m)
    Space complexity: O(n*m)
*/
public final class Solution {

    public void solve(char[][] board) {
        requireNonNull(board, "board is null");
        if ((board.length > 0) && (board[0].length > 0)) {
            // mark all "bad" cells
            for (int i = 1; i < board.length - 1; i++) {
                if (board[i][0] == 'O') {
                    markPath(board, i, 1);
                }
                if (board[i][board[0].length - 1] == 'O') {
                    markPath(board, i, board[0].length - 2);
                }
            }
            for (int j = 1; j < board[0].length - 1; j++) {
                if (board[0][j] == 'O') {
                    markPath(board, 1, j);
                }
                if (board[board.length - 1][j] == 'O') {
                    markPath(board, board.length - 2, j);
                }
            }
            // restore "bad" cells and fill "surrounded" cells
            for (int i = 1; i < board.length - 1; i++) {
                for (int j = 1; j < board[0].length - 1; j++) {
                    if (board[i][j] == '1') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    private void markPath(char[][] board, int i, int j) {
        if ((i > 0) && (i < board.length - 1) && (j > 0) && (j < board[0].length - 1) && (board[i][j] == 'O')) {
            board[i][j] = '1';
            markPath(board, i - 1, j);
            markPath(board, i + 1, j);
            markPath(board, i, j - 1);
            markPath(board, i, j + 1);
        }
    }

}