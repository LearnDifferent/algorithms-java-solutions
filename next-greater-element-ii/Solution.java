import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 503. Next Greater Element II:
 * 解决方案：利用循环数组的技巧来模拟数组长度翻倍 + Monotonic Stack（单调栈）求下一个更大的元素
 *
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array, which
 * means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 *
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 */
class Solution {

    public int[] nextGreaterElements(int[] nums) {

        // 数组的长度
        int len = nums.length;

        // 结果的数组，默认全部为 -1，表示后面没有更大的数
        // 如果有更大的数，再赋值过去
        int[] result = new int[len];
        Arrays.fill(result, -1);

        // Monotonic Stack（单调栈）
        Deque<Integer> stack = new ArrayDeque<>();

        // 利用循环数组来模拟翻倍的数组长度
        // 这里判断的条件是 2 * len - 1，而不是 2 * len，
        // 是因为：假设长度为 3，两倍的长度就是 6，
        // 在 2 * len (len = 3)，根据长度取模的情况下，index 的顺序就是：0,1,2,0,1,2
        // 这样的话，最后一个 index（这里是 2）的数值，肯定和中间的 index（这里是 2）的值相等，
        // 所以，没有必要这样处理，字节使用 2 * len - 1，也就是不考虑中间的 index 必定数值相等的情况
        for (int i = 0; i < 2 * len - 1; i++) {

            // i % len：取模（余数）来获取 circular integer array（环形数组）的 index
            int index = i % len;

            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                Integer popped = stack.pop();
                result[popped] = nums[index];
            }

            stack.push(index);
        }

        return result;
    }
}