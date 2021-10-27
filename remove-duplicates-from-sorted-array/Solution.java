/**
 * 26. Remove Duplicates from Sorted Array:
 * 删除有序数组中的重复项
 * 解决方案：双指针
 * 【双指针技巧秒杀四道数组/链表题目 https://mp.weixin.qq.com/s/55UPwGL0-Vgdh8wUEPXpMQ】
 *
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 */
class Solution {

    /**
     * 使用快慢双指针，剔除重复元素：
     * 让快指针去探路，只要出现了重复元素，就往前走，直到没有重复元素为止。
     * 当没有重复元素的时候，再让慢指针移动一步。
     * <p>
     * 此时，慢指针所在位置 index，及其前一个位置 index - 1 是有重复元素的，
     * 而 index - 2 及其之前的所有元素，都是不重复的。
     * 所以，只需要让 index 位置的元素替换为快指针所指向的最新的那个不重复的元素即可。
     * <p>
     * 这样循环，就可以保存一个从 nums[0] 到 nums[slow] 都是不重复元素的 nums 数组。
     *
     * @param nums 需要被剔除重复元素的数组。
     *             注意，要直接操作 {@code nums} 数组，而不是新建数组。
     * @return 返回没有重复元素的最后一个位置 + 1，用来表示剔除了重复元素的数组的长度
     */
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;

        while (fast < length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}