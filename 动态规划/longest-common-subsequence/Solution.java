/**
 * 1143. Longest Common Subsequence:
 * 最长公共子序列
 * 
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 */
class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        // 题目中规定 1 <= text1.length, text2.length
        int len1 = text1.length();
        int len2 = text2.length();

        // 假设 text1 为"abc"，text2 为"bcd"，dp[0][0] 表示 text1 和 text2 为空字符串的时候。
        // dp[1][2] 表示 "a" 和 "bc" 之间的最长公共子序列（Longest Common Subsequence）
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 注意：i - 1 和 j - 1 才是字符串中相应的 index
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 在字符相等的情况下，这两个位置的字符肯定属于最长公共子序列的一部分
                    // 所以在前一个字符的最长公共子序列的前提下 +1
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // 如果字符不相等，有三种情况，需要获取其中的最大值：
                    // 1. dp[i - 1][j]：text1 中，当前位置的字母不属于最长公共子序列的一部分
                    // 2. dp[i][j - 1]：text2 中，当前位置的字母不属于最长公共子序列的一部分
                    // 3. dp[i - 1][j - 1]：两个字符都不属于最长公共子序列的一部分
                    // 因为这里需要获取的是最大值，而两个字符都不属于的时候，肯定不大于其中一个字符符合的情况，
                    // 所以第 3 种情况，绝对不大于前 2 种情况，所以情况 3 可以被忽略
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[len1][len2];
    }
}
