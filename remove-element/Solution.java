/**
 * 27. Remove Element:
 * 移除元素
 * 解决方案：【双指针技巧秒杀四道数组/链表题目 https://mp.weixin.qq.com/s/55UPwGL0-Vgdh8wUEPXpMQ】
 *
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * 链接：https://leetcode-cn.com/problems/remove-element
 */
class Solution {

    public int removeElement(int[] nums, int val) {

        int fast = 0;
        int slow = 0;
        while (fast < nums.length) {

            // 如果 nums[fast] 和 val 相等，说明 fast 位置需要删除
            if (nums[fast] != val) {
                // 而如果 nums[fast] 和 val 并不相等
                // 说明可以保留 fast 位置的数
                nums[slow] = nums[fast];
                // 保留完成后，再移动慢指针
                // 以此保证 nums[0] 到 nums[slow - 1] 都没有 val
                slow++;
            }
            fast++;
        }
        // 返回 slow 作为数组的长度
        return slow;
    }
}