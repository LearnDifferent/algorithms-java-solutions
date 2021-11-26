import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N-Queens / N 皇后问题：
 * 空棋盘填入 '.'，皇后位置填入 'Q'。皇后可以走直线和斜对角。
 */
class Solution {

    List<List<String>> result;

    /**
     * 在 n * n 的棋盘中，求皇后所有可能的位置
     *
     * @param n 棋盘的边长
     * @return 皇后所有可能的位置
     */
    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();

        // 创建并初始化棋盘，空棋盘为 '.'
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // 行数从第 0 行开始，列数使用 n，正斜线和反斜线都是 2n - 1
        backtrack(board, 0, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1]);
        return result;
    }

    /**
     * 在 {@code board} 中，以每一行（row）放置一个皇后为路径，
     * 每一行的所有列（column）都可以作为摆放皇后的选择，
     * 当 row 为 {@code board} 最后一行时，记录结果，并结束该条路径。
     * ======================================================
     * 假设是 3 * 3 的棋盘，那么就有从 0 到 4，共 2 * 3 - 1 = 5 (2n - 1)条正斜线，对应的 index 为：
     * | 0 | 1 | 2 |
     * | 1 | 2 | 3 |
     * | 2 | 3 | 4 |
     * 反斜线同理：
     * | 2 | 1 | 0 |
     * | 3 | 2 | 1 |
     * | 4 | 3 | 2 |
     * =============================================================================
     * 所以，正斜线在棋盘中的 index 为 row + col，比如上面的 1，在棋盘中分别为 [1,0] 和 [0,1]。
     * -----------------------------------------------------------------------------
     * 反斜线也是可以理解为 row + "col"，只是这个"col" 是相反位置的 "col"，
     * 需要先使用 usedCol.length（或 board[0].length，也就是 列的长度）- 1，获取该列最后一个 index，
     * 然后用该列最后一个减去当前 col，就是相反位置的 "col" 了。
     * 总结一下，就是：row + ((usedCol.length - 1) - col)，即 row + usedCol.length - 1 - col
     *
     * @param board         棋盘：'.'表示空，'Q'表示皇后
     * @param row           存放当前皇后的行号（也就是路径选择到了第几行）
     * @param usedCol       皇后已经占用的 Column（说明该 列 已经无法存放其他皇后）
     * @param usedSlash     从左上角到右下角排列，已经存放了皇后的正斜线（也就是：/）
     * @param usedBackSlash 从右上角到左下角排列，已经存放皇后的反斜线：（也就是：\）
     */
    private void backtrack(char[][] board,
                           int row,
                           boolean[] usedCol,
                           boolean[] usedSlash,
                           boolean[] usedBackSlash) {
        // 触发结束条件：到达最后一行
        if (row == board.length) {
            List<String> currentBoard = new ArrayList<>();
            for (char[] r : board) {
                String everyRow = new String(r);
                currentBoard.add(everyRow);
            }
            result.add(currentBoard);
            return;
        }

        // 在当前 row 中，存放皇后，皇后可选择的位置为所有 column，这里遍历所有可能的 column
        for (int col = 0; col < board[0].length; col++) {
            // 判断合法性：
            // 1. 该 column（列）中是否有其他皇后
            boolean checkColumn = !usedCol[col];

            // 2. 确定了 column 和 row 之后，看看该正斜线的位置是否可以使用
            int slashIndex = row + col;
            boolean checkSlash = !usedSlash[slashIndex];

            // 3. 同理，检查反斜线的位置
            int backSlashIndex = row + usedCol.length - 1 - col;
            boolean checkBackSlash = !usedBackSlash[backSlashIndex];

            if (checkColumn && checkSlash && checkBackSlash) {
                // 选择当前位置并标记
                board[row][col] = 'Q';
                usedCol[col] = true;
                usedSlash[slashIndex] = true;
                usedBackSlash[backSlashIndex] = true;

                // 递归进入下一行的判断
                backtrack(board, row + 1, usedCol, usedSlash, usedBackSlash);

                // 存储完一个路径的结果后，开始撤销该路径的选择
                board[row][col] = '.';
                usedCol[col] = false;
                usedSlash[slashIndex] = false;
                usedBackSlash[backSlashIndex] = false;
            }
        }
    }
}
