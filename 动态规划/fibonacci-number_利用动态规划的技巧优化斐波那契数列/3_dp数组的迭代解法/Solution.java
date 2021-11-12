class Solution {
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        // dp 数组其实和备忘录是一个功能，这里主要是把递归换成了迭代
        int[] dp = new int[n + 1];

        // base case
        dp[0] = 0;
        dp[1] = 1;

        // 状态转移：遍历从 2 到 n（0 和 1 已经定义好了）的数值
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 然后，返回计算完成的那个位置的值即可
        return dp[n];
    }
}
