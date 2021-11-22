/**
 * 583. Delete Operation for Two Strings:
 * 两个字符串的删除操作
 *
 * Given two strings word1 and word2, return the minimum number of steps
 * required to make word1 and word2 the same.
 *
 * In one step, you can delete exactly one character in either string.
 *
 * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
 */
class Solution {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // dp[2][3] 表示从 word1 前 2 个字符到 word2 前三个字符，需要删除多少次
        int[][] dp = new int[len1 + 1][len2 + 1];

        // base case：
        // dp[0][0] 表示 word1 和 word2 为空字符串的时候
        // 每个字符串变为到空字符，都需要删除该字符串本身的长度
        // 因为 i = 0 的时候已经是默认值 0 了，所以这里从 i = 1 开始
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 如果该位置字符相同，就不用删除，最优解就是前一个位置的最优解
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 如果该位置的字符不相同，就需要删除（删除操作 +1）
                    // 这里要考虑删除的是 word1 还是 word2 的该位置的字符，所以看看哪个比较小
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    // 另外一种方法：求出最长公共子序列，然后查看两个字符串需要删除多少个字符才能达到最长公共子序列
    // https://leetcode-cn.com/problems/longest-common-subsequence
    public int minDistance0(String word1, String word2) {
        // 题目中规定了 1 <= word1.length, word2.length
        int len1 = word1.length();
        int len2 = word2.length();

        // 求最长公共子序列
        int[][] dp = new int[len1 + 1][len2 + 1];

        // base case
        dp[0][0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // 最长公共子序列的长度
        int commonLength = dp[len1][len2];
        // 返回两个单词与最长公共子序列的差值的和
        return (len1 - commonLength) + (len2 - commonLength);
    }
}
