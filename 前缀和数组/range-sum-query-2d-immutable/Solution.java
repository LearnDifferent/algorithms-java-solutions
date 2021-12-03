/**
 * 304. Range Sum Query 2D - Immutable
 * 二维区域和检索 - 矩阵不可变
 * https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 */
class NumMatrix {

    // preSum[i][j] 表示 matrix[0][0] 到 matrix[i - 1][j - 1] 的总和
    int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            // 如果是 {} 的情况，row 就为 0
            // 这个判断完了之后，才能使用 matrix[0].length
            return;
        }

        int col = matrix[0].length;
        if (col == 0) {
            // 如果是 {{}} 的情况，col 为 0
            return;
        }

        // 构造前缀和矩阵：preSum[0][0] = 0，便于累加
        preSum = new int[row + 1][col + 1];
        for (int i = 1; i < preSum.length; i++) {
            for (int j = 1; j < preSum[0].length; j++) {
                /*
                如果要求 a 到 d 的总和：
                | a | b |
                | c | d |
                就使用当前的
                ----------------
                | d |
                ----------------
                加上
                ----------------
                | a | b |
                ----------------
                再加上
                ----------------
                | a |
                | c |
                ----------------
                因为 | a | 会重复累加，所以要减去一个。

                注意：因为 preSum 是从 1 开始的，而 matrix 是从 0 开始的，
                所以要考虑索引偏移，也就是说 preSum[i-1][j] 是到 matrix[i-2][j-1] 的总和。

                也可以使用动态规划（DP）来理解。
                 */
                preSum[i][j] = matrix[i - 1][j - 1]
                        + preSum[i - 1][j]
                        + preSum[i][j - 1]
                        - preSum[i - 1][j - 1];
            }
        }
    }

    // 矩阵的右上角为 [row1, col1]，左下角为 [row2, col2]，求矩阵内元素的和
    public int sumRegion(int row1, int col1, int row2, int col2) {
        /*
        | .......... | ...... | r1 - 1, c2 |
        | .......... | r1, c1 | r1, c2     |
        | r2, c1 - 1 | r2, c1 | r2, c2     |
        | .......... | ...... | .......... |
        ====================================
        「到 matrix[r2, c2] 的总和」
        -「到 matrix[r1 - 1, c2] 的总和」
        -「到 matrix[r2, c1 - 1] 的总和」
        +「被多剪了一次的重复的 到 [r1 - 1, c1 - 1] 的总和」
        ====================================
        因为 preSum 是从 1 开始的，matrix 是从 0 开始的，
        所以从 matrix 到 preSum 的时候要 +1
         */
        return preSum[row2 + 1][col2 + 1]
                - preSum[row1][col2 + 1]
                - preSum[row2 + 1][col1]
                + preSum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
