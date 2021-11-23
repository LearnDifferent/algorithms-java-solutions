/**
 * 712. Minimum ASCII Delete Sum for Two Strings:
 * 两个字符串的最小ASCII删除和
 * 
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 * 
 * 链接：https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings
 */
class Solution {

    public int minimumDeleteSum(String s1, String s2) {

        // 题目中规定了：1 <= s1.length, s2.length
        int len1 = s1.length();
        int len2 = s2.length();

        // dp[3][2] 表示 s1 的前 3 个字符到 s2 的前 2 个字符，需要删除的 lowest ASCII sum
        int[][] dp = new int[len1 + 1][len2 + 1];

        // base case：dp[0][0] 表示空字符串，
        // dp[1][0] 表示从 s1 的第 1 个字符到空字符，需要删除的 lowest ASCII sum 是第 1 个字符的 ASCII
        // dp[2][0] 则是从 s1 的前 2 个字符到空字符需要删除的 ASCII sum，所以要用第 1 个字符的 + 第 2 个字符的 ASCII
        for (int i = 1; i < dp.length; i++) {
            // s1 中第 i 个字符的 ASCII（注意第 i 个字符的 index 是 i - 1）
            int c = s1.charAt(i - 1);
            // dp[i - 1][0] 是前 i - 1 个字符的 ASCII sum，加上当前第 i 个字符就是前 i 个字符的 ASCII sum
            dp[i][0] = c + dp[i - 1][0];
        }
        for (int j = 1; j < dp[0].length; j++) {
            // 同理可得：
            dp[0][j] = s2.charAt(j - 1) + dp[0][j - 1];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // s1 和 s2 该位置的字符，然后转化为 int (ASCII)
                int c1 = s1.charAt(i - 1);
                int c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    // 如果字符相等，就不用删除，沿用前一个位置字符的结果
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 如果不相等，就有 3 种情况：1. 需要删除 s1 的字符 2. 需要删除 s2 的字符
                    // 3. 删除 s1 和 s2 的字符（因为是求最小值，而同时删除肯定不是最小值，所以忽略这种情况）

                    // 删除 s1 的字符的话：
                    // sum 为当前 s1 位置字符 的 ASCII + s1 前一个位置，s2 当前位置的最优解
                    int delS1 = c1 + dp[i - 1][j];
                    // 删除 s2 的字符同理
                    int delS2 = c2 + dp[i][j - 1];
                    // 最优解，取这两种的最小值
                    dp[i][j] = Math.min(delS1, delS2);
                }
            }
        }
        return dp[len1][len2];
    }
}
