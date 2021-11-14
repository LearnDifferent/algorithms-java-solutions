/**
 * 416. Partition Equal Subset Sum:
 * 分割等和子集
 *
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets
 * such that the sum of elements in both subsets is equal.
 *
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 *
 * 转化为 0-1 背包问题：
 * 求出数组中，所有元素的和 sum，那么 sum / 2 就是 2 个子数组目标的大小，类似于背包可容纳的重量。
 * 而数组中的第 i 个元素，类似于背包问题中的第 i 个物品。第 i 个元素的大小 nums[i]，类似于每个物品的重量。
 * 所以，需要求是否有一种方案，可以让背包刚好装满。
 *
 * 解题思路：
 * 1. 确定"状态"和"选择"。状态就是"背包的容量（这里指 sum / 2）"和"可选择的物品（这里指 nums 数组中的元素）"。
 * 选择就是是否将该物品放入背包（这里指的是 是否要将 nums 数组中的该元素计入）
 *
 * 2. 明确 dp 的定义：假设 dp[4][10] = true，那么当 sum / 2 为 10 时，前 4 个元素相加刚好等于 10
 *
 * 3. 根据"选择"，获取状态转移的逻辑。如果选择不将第 i 个元素计入其中，那么结果就和 dp[i - 1][j] 是一样的。
 * 如果选择将第 i 个元素计入其中，那么 dp[i][j] 实际上和第 i - 1 个元素，
 * 在抵消掉第 i 个元素的大小（j - nums[i]）的限制下是否等于 sum / 2 的结果是一样的。
 * (注意，因为 nums 数组中的元素是从 0 开始的，所以第 i 个元素的大小其实是 nums[i - 1]，而不是上面写的 nums[i])。
 *
 * 4. base case 就是，当 j 为 0 时，就不需要凑了，相当于直接达成结果，类似于，背包满了，所以 dp[..][0] = true
 * 而当 i 为 0 时，表示没有元素，类似没有物品可以选择，就肯定没有办法装满背包，所以 dp[0][..] = false
 */
class Solution {

    public boolean canPartition(int[] nums) {
        // 数组中的元素个数
        int n = nums.length;
        // 如果元素个数少于 2 个，肯定不行
        if (n < 2) {
            return false;
        }

        // 数组内元素的总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            // 如果 sum 不是偶数的话，就没办法分为 2 个和相等的子数组，所以直接返回 false
            return false;
        }

        // 分为 2 个子数组，每个子数组的和都应该为 target
        int target = sum / 2;

        // dp[i][j] 表示前 i 个元素相加，是否等于 j
        boolean[][] dp = new boolean[n + 1][target + 1];

        // base case：
        // dp[...][0] 为 true
        // dp[0][...] 为 false（因为默认值就是 false，所以这里可以不写）
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j - nums[i - 1] < 0) {
                    // 检查边界：类似于背包没有容量，不能放入第 i 个物品，所以等于第 i - 1 个的值
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果不将第 i 个元素计入，那么就和第 i - 1 个元素的时候相同
                    boolean noCount = dp[i - 1][j];
                    // 如果将第 i 个元素计入，那么就和第 i - 1 个元素在（j - 第 i 个元素的大小）的时候相同
                    int offset = 1;
                    boolean count = dp[i - 1][j - nums[i - offset]];
                    // 在两种情况之中，选择可以成功的那个，作为当前的值
                    dp[i][j] = noCount || count;
                }
            }
        }
        return dp[n][target];
    }
}
