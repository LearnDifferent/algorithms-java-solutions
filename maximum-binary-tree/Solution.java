/**
 * 654. Maximum Binary Tree:
 * 最大二叉树
 *
 * You are given an integer array nums with no duplicates.
 * A maximum binary tree can be built recursively from nums using the following algorithm:
 *
 * 1. Create a root node whose value is the maximum value in nums.
 * 2. Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * 3. Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 *
 * Return the maximum binary tree built from nums.
 *
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 */
class Solution {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    /**
     * 构造最大二叉树：
     * 找到每个 {@code nums} 数组中，最大的数值及其 index。
     * 以最大数值为根节点，递归获取左右子节点中最大的跟节点，并构造完成。
     *
     * @param nums  数组
     * @param start 起始 index
     * @param end   结束 index
     * @return {@link TreeNode} 最大二叉树
     */
    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        // 数组中的最大值
        int maxValue = Integer.MIN_VALUE;
        // 数组中的最大值的 index
        int maxValueIndex = -1;

        // 遍历从 start 到 end 各个 index
        for (int i = start; i <= end; i++) {
            if (maxValue < nums[i]) {
                maxValue = nums[i];
                maxValueIndex = i;
            }
        }

        // 构造最大二叉树：root 节点为最大值
        TreeNode root = new TreeNode(maxValue);
        // 递归获取左右节点
        root.left = constructMaximumBinaryTree(nums, start, maxValueIndex - 1);
        root.right = constructMaximumBinaryTree(nums, maxValueIndex + 1, end);

        return root;
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}