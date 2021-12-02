/**
 * 303. Range Sum Query - Immutable / 区域和检索 - 数组不可变
 *
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 */
class NumArray {
    // 前缀和数组：preSum[i] 表示 nums[0] 到 nums[i - 1] 的总和
    // 可以理解为 preSum[2] 表示 nums 中前 2 个元素（nums[0] 和 nums[1]）的总和
    int[] preSum;

    public NumArray(int[] nums) {
        // preSum[0] = 0，便于计算累加和
        preSum = new int[nums.length + 1];
        // 计算 nums 的累加和
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    // 计算 [left, right] 区间内元素的和
    public int sumRange(int left, int right) {
        // index 为 left 表示第 left + 1 个
        // index 为 right 表示第 right + 1 个元素
        // 所以，按照个数来划分区间：[1, left] [left + 1, right + 1] [right + 2, ...]
        // 所以， [left, right] 区间内元素的和，就是 "right + 1" - "left"
        return preSum[right + 1] - preSum[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
