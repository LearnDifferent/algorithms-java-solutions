/**
 * 226. Invert Binary Tree:
 *
 * 解决方案：递归
 *
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
class Solution {

	// 两种思路都可以
    // public TreeNode invertTree(TreeNode root) {
    //     if (root == null) return null;

    //     TreeNode invertedLeft = invertTree(root.left);
    //     TreeNode invertedRight = invertTree(root.right);

    //     root.left = invertedRight;
    //     root.right = invertedLeft;

    //     return root;
    // }

    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

/**
 * Definition for a binary tree node.
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
