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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return traverse(root, result);
    }
    private List<Integer> traverse(TreeNode root, List<Integer> result) {
        if (root == null) {
            // base case: 如果为 null，直接返回
            return result;
        }
        
        traverse(root.left, result);
        result.add(root.val);
        traverse(root.right, result);
        
        return result;
    }
}
