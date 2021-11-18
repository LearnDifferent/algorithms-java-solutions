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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        traverse(root, "", result);
        return result;
    }

    private void traverse(TreeNode root, String path, List<String> result) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            // 如果 root 是最后一个节点了，就将前面的路径加起来
            result.add(path + root.val);
        }

        // 递归：前面的路径 + 当前节点 + "->"
        traverse(root.left, path + root.val + "->", result);
        traverse(root.right, path + root.val + "->", result);
    }
}
