/**
 * 96. Unique Binary Search Trees:
 *
 * 不同的二叉搜索树
 *
 * Given an integer n, return the number of structurally unique BST's (binary search trees)
 * which has exactly n nodes of unique values from 1 to n.
 *
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 *
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 */
class Solution {

    public int numTrees(int n) {

        // 要存入 [start][end] 的结果，所以初始化二维数组，默认值为 0
        int[][] reminder = new int[n][n];
        // 统计使用 [1, n]，可以组成多少个不同的 BST
        return count(1, n, reminder);
    }

    /**
     * 统计使用 [start, end] 中的数值，可以组成多少个不同的 BST
     *
     * @param start    起始的数值
     * @param end      结束的数值
     * @param reminder 存入 [start][end] 出现的结果，以避免重复的情况
     * @return 统计结果
     */
    int count(int start, int end, int[][] reminder) {
        // base case
        if (start > end) {
            // 空节点也属于一种情况，所以返回 1
            return 1;
        }

        // 如果 start 和 end 已经被统计过，直接返回已经存入过的结果
        // 因为 start 和 end 是从 1 开始的，所以要 -1 来计算它们在二维数组中的 index
        int startIndex = start - 1;
        int endIndex = end - 1;
        if (reminder[startIndex][endIndex] != 0) {
            return reminder[startIndex][endIndex];
        }

        // 最终结果
        int result = 0;

        // 遍历 start 到 end 的数值，被遍历到的数值，就作为 root 的值
        for (int rootValue = start; rootValue <= end; rootValue++) {
            // 统计以 rootValue 为 root 的值的左子树的情况总数
            int leftSum = count(start, rootValue - 1, reminder);

            // 统计以 rootValue 为 root 的值的右子树的情况总数
            int rightSum = count(rootValue + 1, end, reminder);

            // 左右子树的组合数相乘，就是可以组成的总数
            int sum = leftSum * rightSum;
            // 与之前的统计相加即可
            result += sum;
        }

        // 将结果存入
        reminder[startIndex][endIndex] = result;

        // 返回统计后的总数
        return result;
    }
}
