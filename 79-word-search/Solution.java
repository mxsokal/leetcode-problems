import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a 2D board and a word, find if the word exists in the grid.
        The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
        horizontally or vertically neighboring. The same letter cell may not be used more than once.
            board and word consists only of lowercase and uppercase English letters.
            1 <= board.length <= 200
            1 <= board[i].length <= 200
            1 <= word.length <= 10^3

        Examples:

            Input: board =
                    [
                      ['A','B','C','E'],
                      ['S','F','C','S'],
                      ['A','D','E','E']
                    ]
            Output: Given word = "ABCCED", return true.
                    Given word = "SEE", return true.
                    Given word = "ABCB", return false.

    Time  complexity: O(n*m*(4^w))
    Space complexity: O(n*m)

*/
public final class Solution {

    public boolean solve(char[][] board, String word) {
        char[] characters;
        int[][] visited;
        boolean result = false;

        requireNonNull(board, "board is null");
        requireNonNull(word, "word is null");
        if (board.length > 0) {
            visited = new int[board.length][board[0].length];
            characters = word.toCharArray();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (find(board, i, j, visited, characters, 0)) {
                        result = true;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    private boolean find(char[][] board, int rowIndex, int columnIndex, int[][] visited, char[] characters, int beginIndex) {
        boolean result = (beginIndex == characters.length);

        if ((rowIndex >= 0)
          && (columnIndex >= 0)
          && (rowIndex < board.length)
          && (columnIndex < board[rowIndex].length)
          && (beginIndex < characters.length)
          && (visited[rowIndex][columnIndex] == 0)
          && (characters[beginIndex] == board[rowIndex][columnIndex])) {
            visited[rowIndex][columnIndex] = 1;
            result = find(board, rowIndex - 1, columnIndex, visited, characters, beginIndex + 1)
                || find(board, rowIndex + 1, columnIndex, visited, characters, beginIndex + 1)
                || find(board, rowIndex, columnIndex - 1, visited, characters, beginIndex + 1)
                || find(board, rowIndex, columnIndex + 1, visited, characters, beginIndex + 1);
            visited[rowIndex][columnIndex] = 0;
        }
        return result;
    }

}