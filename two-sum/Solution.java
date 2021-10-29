import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum:
 * 两数之和
 * 解决方案：哈希表。
 * 思路：一般情况下，面对无序数组，应该先排序，后使用双指针的技巧。
 * 然而，HashMap 或 HashSet 也可以用来处理无序的数组。
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * 链接：https://leetcode-cn.com/problems/two-sum
 */
class Solution {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // key 是用于相加的数字，value 是其索引
            map.put(nums[i], i);
        }

        for (int i = 0; i < len; i++) {
            // needNum 表示当前 index 的数字，距离目标的数字相差的那个数字
            int needNum = target - nums[i];
            // 如果 needNum 存在，且不是 needNum 的位置不是当前的位置（即，不是当前数字）
            if (map.containsKey(needNum) && !map.get(needNum).equals(i)) {
                return new int[]{i, map.get(needNum)};
            }
        }
        // 根据题意，绝对会有存在，所以不存在的情况返回任意数组即可
        return new int[]{-1, -1};
    }
}