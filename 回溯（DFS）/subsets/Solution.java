import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets:
 * 子集
 *
 * Given an integer array nums of unique elements, return all possible subsets (the power set)（包括空集）.
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * 链接：https://leetcode-cn.com/problems/subsets
 */
class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        // 题目中规定：1 <= nums.length
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    // 递归，类似于中序遍历
    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // 终止条件
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 选择：跳过当前 index，从 index + 1 开始的情况
        backtrack(nums, index + 1, current, result);

        // 选择：添加当前 index，然后再递归进入 index + 1 的情况
        current.add(nums[index]);
        backtrack(nums, index + 1, current, result);

        // 撤销选择：移除最后一个元素
        current.remove(current.size() - 1);
    }


    // 回溯算法
    private void backtrack0(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // 因为需要的是全部结果，所以不用终止，直接用每次的结果，生成新的 list 并放入即可
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            // 选择当前 index
            current.add(nums[i]);

            // 递归，处理 index + 1 的情况
            backtrack(nums, i + 1, current, result);

            // 撤销选择：移除最后一个元素
            current.remove(current.size() - 1);
        }
    }
}
