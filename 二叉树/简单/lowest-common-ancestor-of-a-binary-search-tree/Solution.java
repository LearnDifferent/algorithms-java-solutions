/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null) return null;

        // 因为是 BST，所以只要 root 的值在 p 和 q 之间即可
       if (root.val < p.val && root.val < q.val) {
           // 当前 root 值小，所以去右边
           return lowestCommonAncestor(root.right, p, q);
       }

       if (root.val > p.val && root.val > q.val) {
           return lowestCommonAncestor(root.left, p ,q);
       }

       return root;
    }
}
