/**
 * 面试题 04.02. Minimum Height Tree LCCI:
 *
 * Given a sorted (increasing order) array with unique integer elements, write an algorithm to
 * create a binary search tree with minimal height.
 *
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 *
 * 链接：https://leetcode-cn.com/problems/minimum-height-tree-lcci
 */
class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {

        if (nums == null || nums.length == 0) {
            return null;
        }

        int start = 0;
        int end = nums.length - 1;
        return sortedArrayToBST(nums, start, end);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        // 在 [start, end] 中递归（这里使用 while 也可以）
        if (start <= end) {

            int mid = start + (end - start) / 2;

            TreeNode root = new TreeNode(nums[mid]);

            // [start, mid - 1]
            root.left = sortedArrayToBST(nums, start, mid - 1);
            // [mid + 1, end]
            root.right = sortedArrayToBST(nums, mid + 1, end);

            return root;
        }

        // 注意，这里要返回 null
        return null;
    }
}
/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
