/**
 * 64. Minimum Path Sum:
 * 最小路径和
 * 
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 *
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 */
class Solution {

    public int minPathSum(int[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;

        int[][] dp = new int[rowLength][colLength];

        // base case（主要防止索引出界）：
        // 初始位置
        dp[0][0] = grid[0][0];
        // 一直向下走的话
        for (int i = 1; i < rowLength; i++) {
            // 当前 grip 的距离 + dp table 中上面那个的距离
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        // 一直向右走的话
        for (int j = 1; j < colLength; j++) {
            // 当前 grip 的距离 + dp table 中左边那个的距离
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        for (int i = 1; i < rowLength; i++) {
            for (int j = 1; j < colLength; j++) {
//                // 当前 grip 的距离 + dp table 中上面那个的距离
//                int down = grid[i][j] + dp[i - 1][j];
//                // 当前 grip 的距离 + dp table 中左边那个的距离
//                int right = grid[i][j] + dp[i][j - 1];
//                // 这两个中，最短的距离，就被采纳
//                dp[i][j] = Math.min(down, right);
                // 上面注释中的代码，可以直接写成：
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[rowLength - 1][colLength - 1];
    }
}
