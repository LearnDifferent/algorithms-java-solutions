import java.util.Arrays;

/**
 * 322. Coin Change:
 * 零钱兑换
 *
 * You are given an integer array coins representing coins of different denominations and
 * an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 *
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * 链接：https://leetcode-cn.com/problems/coin-change
 */
class Solution {

    /**
     * 暴力递归解法：
     * 状态：amount 目标金额
     * 选择：coins 数组中，所有的面额
     * 函数的定义：凑出 amount 金额，至少需要 coinChange(int[] coins, int amount) 枚硬币
     * <p>
     * 假设面额为 1,2,5，需要凑出 11，可以倒推：
     * 1. 假设最后一个需要的面额为 1，则需要凑出 10，所以求凑出 10 的最小数量，最后加上当前这一枚即可
     * 2. 同理，假设最后一个需要的为 2，则需要凑出 9，所以求凑出 9 的最小数量……
     * 3. 假设最后一个需要的为 5，则需要凑出 6，所以求凑出 6 的最小数量……
     * 这样就可以列出一个个的子问题，然后递归求解了
     */
    public int coinChange0(int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        // 最小的硬币个数
        int minCount = Integer.MAX_VALUE;

        // 这里是暴力递归，所以遍历所有的面额
        for (int coin : coins) {
            // 子问题：出去当前的面额，最小的面额为？
            int subProblem = coinChange0(coins, amount - coin);
            if (subProblem != -1) {
                // 如果 subProblem 有解，则当前的总数为子问题求解后的结果 + 当前这一枚硬币
                int currentCount = subProblem + 1;
                // 查看当前结果，是否是最小的结果
                minCount = Math.min(currentCount, minCount);
            }
        }
        // 最后，如果更新了最小的硬币个数，就返回
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    /**
     * 使用备忘录优化
     */
    public int coinChange1(int[] coins, int amount) {
        // 初始化备忘录，初始值为 -2（因为 -1 表示没有，所以要用除了 -1 以外的负数）
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        return coinChange1(memo, coins, amount);
    }

    private int coinChange1(int[] memo, int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        if (memo[amount] != -2) {
            // 只要不为 -2，说明之前取过这种情况的最小值
            return memo[amount];
        }

        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = coinChange1(memo, coins, amount - coin);
            if (subProblem != -1) {
                minCount = Math.min(subProblem + 1, minCount);
            }
        }
        memo[amount] = minCount == Integer.MAX_VALUE ? -1 : minCount;
        return memo[amount];
    }

    /**
     * 最终：迭代优化
     */
    public int coinChange(int[] coins, int amount) {
        // 函数的定义：凑出 amount 金额，至少需要 coinChange(int[] coins, int amount) 枚硬币
        // 可以推导出 dp 的定义：凑出金额 x，至少需要 dp[x] 枚硬币
        int[] dp = new int[amount + 1];

        // 初始化的时候，选择一个比最大的可能性还多的数值：
        // 这里选择 amount + 1 是因为：假设每个硬币都是 1，最多也只需要 amount 个硬币，所以 amount + 1 是最大的
        Arrays.fill(dp, amount + 1);

        // base case
        dp[0] = 0;

        // 外部的 for 循环，遍历所有状态（这里的状态，指目标金额）的所有取值
        // 所有可能的目标金额是从 0 到 amount（这里的 amount 可以换成 dp.length - 1）
        for (int i = 0; i <= amount; i++) {
            // 内部的 for 循环，求该状态下，所有选择的最小值
            for (int coin : coins) {
                // 只要当前子问题（需要凑齐的金额 - 当前的面额的最小值）有解就能继续
                if (i - coin >= 0) {
                    // 当前数量 = 子问题的解 + 当前这一枚硬币
                    int currentCount = dp[i - coin] + 1;
                    // 状态转移：获取当前情况的最小值
                    dp[i] = Math.min(dp[i], currentCount);
                }
            }
        }

        if (dp[amount] == amount + 1) {
            // 如果需要的 amount 无法凑齐，也就是 dp[amount] 为那个取不到的最大值
            return -1;
        }
        // 如果能凑出来，就返回
        return dp[amount];
    }
}
