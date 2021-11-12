import java.util.Arrays;

/**
 * 931. Minimum Falling Path Sum:
 * 下降路径最小和
 *
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly
 * below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1),
 * (row + 1, col), or (row + 1, col + 1).
 *
 * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
 */
class Solution {

    /**
     * 暴力递归
     */
    public int minFallingPathSum0(int[][] matrix) {
        // 最后一行的 index + 1
        int rowLength = matrix.length;
        // 最后一列的 index + 1
        int colLength = matrix[0].length;
        // 最短路径
        int minRoad = Integer.MAX_VALUE;

        // 终点会出现在最后一行的某一列上，所以，遍历最后一行的每一列
        for (int col = 0; col < colLength; col++) {
            // 获取在最后一行中，到这一列的最短路径
            int currentColMinRoad = minFallingPathSum0(matrix, rowLength - 1, col);
            // 查看是否为最短路径
            minRoad = Math.min(minRoad, currentColMinRoad);
        }
        return minRoad;
    }

    private int minFallingPathSum0(int[][] matrix, int row, int col) {
        // 对于 matrix[row][col]，只会从三个方向过来：
        // 左上方：matrix[row - 1][col - 1]
        // 正上方：matrix[row - 1][col]
        // 右上方：matrix[row - 1][col + 1]
        // 最短路径，就是那三个方向的最短路径，加上当前 matrix[row][col] 的这段距离

        // 检查索引
        if (row < 0 || col < 0
                || row > matrix.length - 1
                || col > matrix[0].length - 1) {
            // 返回一个最大值，比最大的结果还大就行，表示此路不通
            // 题目中：-100 <= matrix[i][j] <= 100，所以最大的值是 100 * 100
            return 20_000;
        }

        // base case
        if (row == 0) {
            // 当在第一行的时候，直接当前格子的距离
            return matrix[row][col];
        }

        // 递归获取左上方的最短路径
        int a = minFallingPathSum0(matrix, row - 1, col - 1);
        // 正上方的最短路径
        int b = minFallingPathSum0(matrix, row - 1, col);
        // 右上方
        int c = minFallingPathSum0(matrix, row - 1, col + 1);

        // 获取当前位置之前的最短的那个路径
        int minB4 = Math.min(a, Math.min(b, c));

        // 状态转移：当前距离，加上之前的最短距离
        return minB4 + matrix[row][col];
    }

    /**
     * 备忘录
     */
    public int minFallingPathSum(int[][] matrix) {

        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        // 初始化备忘录
        int[][] memo = new int[rowLength][colLength];
        for (int[] memoEveryRow : memo) {
            // 题目中：-100 <= matrix[i][j] <= 100，所以最大的值是 100 * 100
            // 初始值需要比最大值还大，表示这条路还没走过，这里设定为 3 万
            Arrays.fill(memoEveryRow, 30_000);
        }

        // 最短路径
        int minRoad = Integer.MAX_VALUE;

        // 最后一行的每一列，都有可能会得到最小路径
        for (int col = 0; col < colLength; col++) {
            // rowLength - 1 表示在最后一行，在最后一行的当前列，获取最短的路径
            int currentMinRoad = minFallingPathSum(memo, matrix, rowLength - 1, col);
            // 看看是否为最短路径
            minRoad = Math.min(currentMinRoad, minRoad);
        }

        return minRoad;
    }

    private int minFallingPathSum(int[][] memo, int[][] matrix, int row, int col) {
        // 检查索引
        if (row < 0 || col < 0 || row > matrix.length - 1 || col > matrix[0].length - 1) {
            // 索引不存在的情况，返回一个比最大值还大的值即可（而且要跟 memo 中的默认值不同）
            return 40_000;
        }

        // base case：第一行的情况下，返回格子的距离
        if (row == 0) {
            return matrix[row][col];
        }

        // 其他情况，返回上一行的最短路径 + 当前格子的距离

        // 这里先查询一下备忘录，看看是否已经被计算过
        if (memo[row][col] != 30_000) {
            return memo[row][col];
        }

        // 获取左上、正上和右上方的最短距离，然后获得上一行的最短距离
        int a = minFallingPathSum(memo, matrix, row - 1, col - 1);
        int b = minFallingPathSum(memo, matrix, row - 1, col);
        int c = minFallingPathSum(memo, matrix, row - 1, col + 1);
        int previousRowMin = Math.min(a, Math.min(b, c));

        // 状态转移：获取当前位置的距离 + 上一行的最短距离，然后存入备忘录中
        memo[row][col] = previousRowMin + matrix[row][col];
        return memo[row][col];
    }

}
