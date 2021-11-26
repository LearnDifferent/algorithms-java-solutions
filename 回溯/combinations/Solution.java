import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations:
 * 组合
 *
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * You may return the answer in any order.
 * 
 * 链接：https://leetcode-cn.com/problems/combinations
 */
class Solution {

    public List<List<Integer>> combine(int n, int k) {
        // 题目规定：1 <= n 和 1 <= k
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, new ArrayList<>(), k, result);
        return result;
    }

    /**
     * @param start   开始数字
     * @param end     结束数字（不能大于这个数值）
     * @param current 当前可以组成的情况
     * @param size    每一个 {@code current} 的最终大小
     * @param result  存储最终结果
     */
    private void backtrack(int start, int end, List<Integer> current, int size, List<List<Integer>> result) {

        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= end; i++) {
            // 选择当前数值
            current.add(i);
            // 递归
            backtrack(i + 1, end, current, size, result);
            // 撤销选择
            current.remove(current.size() - 1);
        }
    }
}
