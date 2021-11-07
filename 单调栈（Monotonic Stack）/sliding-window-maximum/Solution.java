import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 239. Sliding Window Maximum:
 * 滑动窗口最大值
 *
 * 解决方案：单调队列
 *
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of
 * the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
 * by one position.
 *
 * Return the max sliding window.
 *
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */

class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 使用单调队列作为窗口
        MonotonicQueue window = new MonotonicQueue();
        // 存储结果的列表
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // 当前 index 对应的数值
            int currentIndexValue = nums[i];

            if (i < k - 1) {
                // 如果没有形成 k 个一组，就将当前 index 的数值填入窗口中
                window.push(currentIndexValue);
            } else {
                // 窗口向前滑动，加入新数值
                window.push(currentIndexValue);

                // 记录当前窗口中，最大的数值（因为肯定有最大的数组，所以不会为 null）
                Integer max = window.getMax();
                result.add(max);

                // 移出即将离开窗口的数值
                // 该数值的 index 为当前的下一个 index（index + 1），往前数 k 个
                int removed = nums[i + 1 - k];
                window.pop(removed);
            }
        }

        // 答案需要返回数组，所以将列表转化为数组并返回结果
        int size = result.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = result.get(i);
        }
        return res;
    }
}

/**
 * 单调队列：保证在一个区间大小内，头部的值最小，尾部的值最大。
 * <p>
 * push 进来的新元素，需要保证队列中没有比其更小的。
 * 如果新元素比队列中所有元素都大，那么其他元素都会被删除，只保留这个最大的新元素。
 * <p>
 * 因为在 push 的时候会删除队列中的元素，所以 pop 出去一个元素的时候，
 * 被 popped 的元素可能已经不存在于队列之中了。
 * <p>
 * 所以，pop 之前需要先判断被 popped 的元素是否为底部那个最大的元素，
 * 如果不是的话，说明早已经不存在了。
 * <p>
 * 获取最大的元素，就直接返回底部元素即可。
 */
class MonotonicQueue {

    // 链表结构支持在头部和尾部快速增删元素
    LinkedList<Integer> queue = new LinkedList<>();

    public void push(int value) {
        while (!queue.isEmpty() && queue.getFirst() < value) {
            // 如果头部的元素，小于新加入的元素，就将头部的元素弹出
            // 保持新加入的元素一定大于头部的元素
            queue.pollFirst();
        }
        // 保证最上头的元素，一定当前队列中最小的（或者并列最小）
        // 所以：最尾部的元素，一定是最大的
        queue.addFirst(value);
    }

    public Integer getMax() {
        // 底部的元素，一定是队列中最大的
        return queue.getLast();
    }

    public void pop(int value) {
        // 如果底部的值，就是传入的值，就将其弹出
        // 因为底部的值是最大的，所以下一次判断的时候，最大值没有了
        // 所以，当前第二大的值，就变成了最大值
        if (queue.getLast().equals(value)) {
            queue.pollLast();
        }
    }
}