class Solution {
    public int fib(int n) {
        // 求 n 为 4 的值，只需要 n 为 2 和 n 为 3 的值即可，
        // 而 n 为 2 和 3 可以通过计算得出，所以说，
        // 实际上不需要完整的 dp，而是最终结果的前两个位置的值
        
        if (n == 0 || n == 1) {
            return n;
        }

		// f(1) = 1; f(2) = 1; 这里的 pre 表示 f(1)，cur 表示 f(2)
        int pre = 1;
        int cur = 1;
        
		// 现在从获取 f(3) 开始，一直获取到 f(n)
        for (int i = 3; i < n + 1; i++) {
            int tmp = cur;
            cur += pre;
            pre = tmp;
        }
		// 结束循环后返回
        return cur;
    }
}
