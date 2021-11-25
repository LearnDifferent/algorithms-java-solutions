import java.util.LinkedList;
import java.util.List;

/**
 * 46. Permutations:
 * 全排列
 * 
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 链接：https://leetcode-cn.com/problems/permutations
 */
class Solution {

    // 存储最后的结果
    List<List<Integer>> result;

    List<List<Integer>> permute(int[] nums) {
        result = new LinkedList<>();
        // 因为要穷举所有可能的结果，所以使用回溯算法
        backtrack(nums, new LinkedList<>());
        return result;
    }

    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 结束条件：nums 中的元素全都在 track 中出现过
        if (track.size() == nums.length) {
            // nums 中元素不重复，所以使用大小来判断
            // 注意，这里需要将 track 的内容，移动到新的列表中，所以需要新 new 一个列表，
            // 否则以后 track 被修改的时候，已经存入的 track 的内容也会一起被修改
            result.add(new LinkedList<>(track));
            // 完成其中一条路径，return
            return;
        }

        for (int num : nums) {
            // 只要该元素还没出现在 track 中，就继续走下去
            if (!track.contains(num)) {
                // 添加选择，当前循环到的元素，作为分歧点
                track.add(num);
                // 递归，进入下一层决策树，直到触发结束条件，
                // 也就是 nums 中全部元素都被放入 track 中，触发 return
                backtrack(nums, track);
                // 取消选择：因为上面进行过递归了，已经将其中一条路径的所有可能性都找到...
                // 并存储到 result 列表中了，此时该条路径的 track 列表已经完成使命了，
                // 所以在结束递归往回走的过程中，逐步将 track 中的元素清空，为下一条路径做准备
                track.removeLast();
            }
        }
    }
}
