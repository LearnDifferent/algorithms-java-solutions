class Solution {
    public int fib(int n) {
        // 备忘录
        int[] memo = new int[n + 1];
        return fib(memo, n);
    }

    private int fib(int[] memo, int n) {
        // base case
        if (n == 0 || n == 1) {
            // F(0) = 0, F(1) = 1
            return n;
        }
        // 如果已经在备忘录中存在，就直接返回结果
        if (memo[n] != 0) {
            return memo[n];
        }
        // 递归计算并存储值
        memo[n] = fib(memo, n - 1) + fib(memo, n - 2);
        return memo[n];
    }
}
