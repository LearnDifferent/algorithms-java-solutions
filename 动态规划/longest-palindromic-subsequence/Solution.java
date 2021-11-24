/**
 * 516. Longest Palindromic Subsequence:
 * 最长回文子序列
 * 
 * Given a string s, find the longest palindromic subsequence's length in s.
 * 
 * A subsequence is a sequence that can be derived from another sequence by deleting some 
 * or no elements without changing the order of the remaining elements.
 * 
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 */
class Solution {

    public int longestPalindromeSubseq(String s) {
        // 对于一个子序列而言，如果它是回文子序列，并且长度大于 2，那么将它首尾的两个字符去除之后，它仍然是个回文子序列
        char[] word = s.toCharArray();
        int len = word.length;

        // 在 word[i] 到 word[j] 中，最长的回文子序列为 dp[i][j]
        int[][] dp = new int[len][len];

        // base case 1: i > j 时，不存在回文，保持默认值即可
        // base case 2: i = j 时，长度为 1，任何长度为 1 的子序列都是回文子序列

        for (int i = len - 1; i >= 0; i--) {
            // base case 2 写在这里
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (word[i] == word[j]) {
                    // 如果相等，说明对于 word[i + 1] 到 word[j - 1] 的回文子序列，
                    // 向外各走 1 个字符可以组成新的回文子序列
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    // 如果不相等，说明没办法组成新的回文子序列，此时比较向 内 走的回文子序列中，也就是
                    // 从 word[i + 1] 到 word[j] 和 word[i] 到 word[j - 1] 的回文子序列哪个比较长
                    // 注意：同时向 内 走 1 个单位的 word[i+1] 到 word[j-1] 肯定不比另外两种长，所以忽略
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    // 和 longestPalindromeSubseq0 类似，这个速度快一点
    public int longestPalindromeSubseq1(String s) {
        int len = s.length();
        // 正序的字符串
        char[] asc = s.toCharArray();
        // 倒序的字符串
        char[] desc = new char[len];
        for (int i = 0; i < len; i++) {
            desc[i] = asc[len - 1 - i];
        }

        int[][] dp = new int[len + 1][len + 1];

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (asc[i - 1] == desc[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len][len];
    }

    public int longestPalindromeSubseq0(String s) {
        // 题目中规定：1 <= s.length
        int len = s.length();

        // 这里要判断回文，所以使用的是左右指针的方式，左指针从左往右，右指针从右往左
        // 这样的话，相当于一个是正序的字符串，另一个是倒序的字符串，求两个的公共最长子序
        // 对于 dp[i][j]，i 设定为正序的 index，j 设定为倒序的 index
        int[][] dp = new int[len + 1][len + 1];

        // base case: dp[0][0] 表示空字符串

        // 正序遍历字符串
        for (int i = 1; i <= len; i++) {
            // 倒序遍历字符串
            for (int j = 1; j <= len; j++) {
                // 正序时候的字符
                char asc = s.charAt(i - 1);
                // 倒序时候的字符
                char desc = s.charAt(len - j);
                if (desc == asc) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len][len];
    }
}
