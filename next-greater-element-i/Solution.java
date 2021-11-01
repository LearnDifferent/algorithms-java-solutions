import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. Next Greater Element I:
 *
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
 * If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 *
 * 解决方案：求下一个更大的元素时，使用 Monotonic Stack（单调栈）。这里还需要使用哈希表。
 * 题目的意思：对于 nums1 的元素，需要现在 nums2 中，找到其对应的元素。
 * 找到的这个对应的元素，在 nums2 中所在的位置的后面 的所有元素中，
 * 如果有比其大的元素，就填入那个更大的元素到 nums1 中（只填入第一个）；如果没有更大的元素，就填入 -1。
 * 以 nums1 = [4,1,2], nums2 = [1,3,4,2] 为例：
 * num1[0] = nums2[2] 都是 4，然后在 nums2 的索引 2 后面找，没有比 nums2[2] 大的数字了，因此填 -1；
 * nums1[1] = nums2[0] 都是 1，在 nums2 的索引 0 后面，第一个比它的的数字是 3，所以填 3；
 * nums1[2] = nums2[3] 都是 2，nums2 的索引 3 后面没有比 2 更大的数字，所以填 -1。
 * 所以答案是 [-1,3,-1]
 */
class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Monotonic Stack，按照 index 从小到大，存储 num2 的 index
        Stack<Integer> stack = new Stack<>();
        // key 表示 nums2 中的值
        // value 存储在 nums2 中，比 key 的值大的第一个值（index 要在 key 的值的后面）
        Map<Integer, Integer> map = new HashMap<>();

        // 遍历 nums2 的 index
        for (int index = 0; index < nums2.length; index++) {
            // 循环：在不为空的前提下，如果当前 index 所在的值，大于 stack 顶部存储的 index 所在的值
            while (!stack.isEmpty() && nums2[index] > nums2[stack.peek()]) {
                // 弹出 stack 顶部的 index
                Integer poppedIndex = stack.pop();
                // key 记录顶部的 index 对应的值
                // value 记录比顶部的 index 对应的值，还要大的，最近的一个 index 对应的值
                map.put(nums2[poppedIndex], nums2[index]);
            }
            // 在 stack 中放入当前遍历到的 index
            stack.push(index);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}