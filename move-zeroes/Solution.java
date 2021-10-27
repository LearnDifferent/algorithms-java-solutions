/**
 * 283. Move Zeroes:
 * 移动零
 * 解决方案：【双指针技巧秒杀四道数组/链表题目 https://mp.weixin.qq.com/s/55UPwGL0-Vgdh8wUEPXpMQ】
 *
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 */
class Solution {

    public void moveZeroes(int[] nums) {

        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        // 结束循环后，slow 指针后面的数组全部设置为 0（包括 slow 所在的位置）
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}