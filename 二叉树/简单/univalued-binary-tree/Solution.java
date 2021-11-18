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
    public boolean isUnivalTree(TreeNode root) {
        // nodes in the tree is in the range [1, 100]
        // 所以 root 不为 null，且全部的值都要和 root.val 相等
        int target = root.val;
        return equalsTargetValue(root, target);
    }

    private boolean equalsTargetValue(TreeNode root, int target) {
        if (root == null) return true;
        if (root.val != target) return false;
        return equalsTargetValue(root.left, target) && equalsTargetValue(root.right, target);
    }
}
