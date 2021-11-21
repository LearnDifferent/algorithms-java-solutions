/**
 * 53. Maximum Subarray:
 * 最大子序和
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
class Solution {

    // 这是最优的方案
    public int maxSubArray(int[] nums) {
        // 最后的结果，默认值为第一个元素
        int result = nums[0];

        // 在 for 循环中，计算总和
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                // 只要 sum 大于 0（这里写"大于等于"也是一样的）
                // 说明加上新的数值后，有变大的可能
                sum += num;
            } else {
                // 如果 sum 是负数，说明加上新的数值后，就相当于"减操作"
                // 比如现在 sum 是 -5，那么 num + sum 实际上就是 num - 5，
                // 肯定比直接使用 num 还要小，所以这里直接让 sum 等于 num
                sum = num;
            }
            // 看看当前的 sum 是否比之前最大的 result 还要大，然后更新
            result = Math.max(result, sum);

        }
        return result;
    }

    // 从使用 dp 数组的方案可以知道：dp[i] = max(nums[i], nums[i] + dp[i-1])，
    // 所以 dp[i] 仅和 dp[i-1] 的状态有关，所以使用 dpiMinus1 来表示 dp[i-1]，dpi 表示 dp[i]
    public int maxSubArray1(int[] nums) {
        // base case
        int dpiMinus1 = nums[0];
        int dpi = 0;

        int res = dpiMinus1;

        for (int i = 1; i < nums.length; i++) {
            // 等价于 dp[i] = max(nums[i], nums[i] + dp[i-1])
            dpi = Math.max(nums[i], nums[i] + dpiMinus1);
            // 当前的 dp[i] 就变为了 dp[i - 1]
            dpiMinus1 = dpi;
            // 更新最大的结果
            res = Math.max(res, dpi);
        }

        return res;
    }

    // 这是使用 dp 数组的方案：
    public int maxSubArray0(int[] nums) {
        // 题目约束：nums.length >= 1

        // dp[2] 表示到 nums[2] 元素的连续的最大值
        int[] dp = new int[nums.length];

        // base case
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 一种可能的情况，就是当前元素 + 前一个位置的最优解
            int cur = nums[i] + dp[i - 1];
            // 另外一种情况，就是"当前元素"比"当前元素 + 前一个位置的最优解"还大，
            // 这种情况，说明前一个位置的最优解为负数
            dp[i] = Math.max(nums[i], cur);
        }

        // 遍历 dp，看看哪个是最大的
        int result = Integer.MIN_VALUE;
        for (int d : dp) {
            result = Math.max(d, result);
        }
        return result;
    }
}
