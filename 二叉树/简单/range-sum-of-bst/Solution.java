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
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        if (root.val < low) {
            // 小于 [low, high]，去右边
            return rangeSumBST(root.right, low, high);
        }

        if (root.val > high) {
            // 大于 [low, high]，去右边
            return rangeSumBST(root.left, low, high);
        }

        // 当前的值 + 左边符合条件的值 + 右边符合条件的值
        return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);
    }
}
