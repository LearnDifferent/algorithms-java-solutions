import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 739. Daily Temperatures:
 *
 * 解决方案：求下一个更大的元素时，使用 Monotonic Stack（单调栈）
 *
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days
 * you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 */
class Solution {

    public int[] dailyTemperatures(int[] temperatures) {
        // 使用 Deque，比 Stack 类快一点
        Deque<Integer> stack = new ArrayDeque<>();
        // 要求返回一个 answer 数组
        int[] answer = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {

            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer popped = stack.pop();
                answer[popped] = i - popped;
            }

            stack.push(i);
        }

        return answer;
    }
}