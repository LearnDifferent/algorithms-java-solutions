import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K:
 *
 * 和为 K 的子数组
 *
 * Given an array of integers nums and an integer k,
 * return the total number of continuous subarrays whose sum equals to k.
 *
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 */
class Solution {

    public int subarraySum(int[] nums, int k) {
        // 在另一种解法中 if (preSum[i] - preSum[j] == k) 可以写为 (preSum[i] - k == preSum[j])
        // 所以，在知道了 preSum[i] 的情况下，查看有没有 value 为 preSum[i] - k 的 preSum[j]

        // key：前缀和，val：该前缀和出现的次数
        Map<Integer, Integer> preSum = new HashMap<>();
        // base case
        preSum.put(0, 1);

        // 记录最终结果
        int result = 0;
        // 当前循环到的位置的 前缀和
        int sum = 0;

        for (int num : nums) {
            // 更新当前位置的 前缀和
            sum += num;
            // 当前的 前缀和 - 目标k，就是 需要的另外一个 前缀和
            int need = sum - k;
            // 如果之前出现过符合条件的 前缀和，就更新 result
            if (preSum.containsKey(need)) {
                // 该 前缀和 出现的次数，
                // 也就是有多少个 subarrays 可以和当前的 subarray 组成目标 k
                int subCount = preSum.get(need);
                result += subCount;
            }
            // 更新当前的 前缀和 出现的次数
            int freq = preSum.getOrDefault(sum, 0) + 1;
            preSum.put(sum, freq);
        }

        return result;
    }

    // 比较慢的解法：利用前缀和数组，双层遍历求解
    public int subarraySum0(int[] nums, int k) {
        // 前缀和
        int[] preSum = new int[nums.length + 1];

        // preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }

        int result = 0;
        for (int i = 1; i < preSum.length; i++) {
            for (int j = 0; j < i; j++) {
                // nums[j] 到 nums[i - 1] 的和为 k 时，result + 1
                if (preSum[i] - preSum[j] == k) {
                    result++;
                }
            }
        }
        return result;
    }
}
