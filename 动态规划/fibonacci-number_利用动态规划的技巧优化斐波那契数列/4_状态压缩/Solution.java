class Solution {
    public int fib(int n) {
        // 求 n 为 4 的值，只需要 n 为 2 和 n 为 3 的值即可，
        // 而 n 为 2 和 3 可以通过计算得出，所以说，
        // 实际上不需要完整的 dp，而是最终结果的前两个位置的值
        
        if (n == 0 || n == 1) {
            return n;
        }

        // 现在从 n == 2 开始，前一个位置的 Fibonacci number 为 1
        int pre = 1;
        // 当前位置的 Fibonacci number 为 1
        int cur = 1;
        
        // 从 n 为 3 开始，一直遍历到 n
        for (int i = 3; i < n + 1; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        // 直到遍历到 n，结束循环
        return cur;
    }
}
