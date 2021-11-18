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
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            // 都为 null，视为相同
            return true;
        }
        
        if (p == null || q == null) {
            // 在只有其中一个为 null 的情况下，视为不同
            return false;
        }

        if (p.val != q.val) {
            // 如果子节点不同，就不相同
            return false;
        }

        // 递归
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
