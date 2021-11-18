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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 只要 subRoot 为 null，肯定是包含的
        if (subRoot == null) return true;

        // 在 subRoot 不为 null，但 root 为 null 的时候，肯定是不包含的
        if (root == null) return false;

       if (sameTree(root, subRoot)) {
            // 如果当前树和需要的树相等，就返回 true，否则，递归执行
            return true;
        }

        // 递归检查左子树和右子树
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;

        if (a == null || b == null) return false;

        if (a.val != b.val) return false;

        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
