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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // base case
        if (root1 == null && root2 == null) {
            return null;
        }

        // 其中一个为 null，就返回另一个（同时为 null 被 base case 排除了）
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        // 递归，获取左右的结果
        TreeNode leftNode = mergeTrees(root1.left, root2.left);
        TreeNode rightNode = mergeTrees(root1.right, root2.right);

        // 当前的结果
        int rootVal = root1.val + root2.val;
        TreeNode root = new TreeNode(rootVal);
        root.left = leftNode;
        root.right = rightNode;

        return root;
    }
}
