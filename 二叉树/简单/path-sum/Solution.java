/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            // 此时没有子节点，就判断目标值是否和当前节点的值相等
            return targetSum == root.val;
        }

        // 计算除去当前节点值，还需要的目标值
        int newTargetSum = targetSum - root.val;

        // 递归计算，看看有没有符合条件的
        return hasPathSum(root.left, newTargetSum) || hasPathSum(root.right, newTargetSum);
    }
}
