/**
 * 698. Partition to K Equal Sum Subsets:
 * 划分为k个相等的子集
 *
 * Given an integer array nums and an integer k, return true if it is possible to
 * divide this array into k non-empty subsets whose sums are all equal.
 *
 * 链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
 */
class Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 题目中规定了：k <= nums.length
        // 排除情况：如果总数没办法被 k 整除，说明无法组成 k 个总数相等的子集
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) {
            return false;
        }

        return backtrack(k, 0, nums, 0, new boolean[nums.length], sum / k);
    }

    /**
     * nums 中元素的总和 / k = target，也就是每组需要恰好等于这个目标总数值。
     * 以分完 k 组为结束条件，每组完成了 target 数值后，就进入下一组。
     * 穷举 nums[numsIndex] 的所有可能情况，并记录已经出现过的 nums[usedIndex]，
     * 最后得出是否可以完成这样的分组。
     *
     * @param k          分为 k 组
     * @param currentSum 当前组内元素的总值
     * @param nums       数组，该数组内的元素用于分组
     * @param numsIndex  轮到数组中的 index
     * @param usedIndex  已经用过的 index
     * @param target     每组的目标总数值
     * @return 是否可以分为 k 组，k 组内的每个元素相加为 target
     */
    private boolean backtrack(int k, int currentSum, int[] nums, int numsIndex, boolean[] usedIndex, int target) {
        // base case
        if (k == 0) {
            // 分完了 k 个组，说明可以完成
            return true;
        }
        if (currentSum == target) {
            // 当前分组已经完成，递归穷举下一个分组
            // 注意，currentSum 要清零，且下一个分组也要从 nums[0] 开始选数字
            // （被选择过的元素，会被 usedIndex 记录，所以不用怕重复选择）
            return backtrack(k - 1, 0, nums, 0, usedIndex, target);
        }

        // 从 numsIndex 开始，查看是否 nums[i] 是否可以适合当前分组
        for (int i = numsIndex; i < nums.length; i++) {
            // nums[i] 没被使用，且 currentSum 在加入了 nums[i] 后，仍然符合每个组目标总值的情况下：
            if (!usedIndex[i] && nums[i] + currentSum <= target) {
                // 做选择：将 nums[i] 放入当前分组中
                usedIndex[i] = true;
                currentSum += nums[i];

                // 递归穷举 nums[i + 1] 是否适合当前分组
                boolean ok = backtrack(k, currentSum, nums, i + 1, usedIndex, target);
                if (ok) {
                    // 如果已经完成了，就直接返回 true
                    return true;
                }

                // 如果还没有完成，就在回退的时候，撤销选择
                usedIndex[i] = false;
                currentSum -= nums[i];
            }
        }

        // 所有情况都无法完成的情况下，返回 false
        return false;
    }

}
