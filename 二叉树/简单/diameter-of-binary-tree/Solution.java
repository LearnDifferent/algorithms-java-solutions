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

    // 节点的长度（注意，题目要求的是路径的长度）
    private int maxLength;

    public int diameterOfBinaryTree(TreeNode root) {
        maxLength = 0;
        traverse(root);
        // 因为定义的 diameter 是路径的长度，所以要用节点的长度 - 1
        return maxLength - 1;
    }

    private int traverse(TreeNode root) {
        // 左边的最大长度 + 右边的最大长度 + 当前节点 1 个单位

        // base case
        if (root == null) return 0;

        int leftLength = traverse(root.left);
        int rightLength = traverse(root.right);

        // 当前长度
        int current = 1 + leftLength + rightLength;
        // 更新最大长度
        maxLength = Math.max(maxLength, current);

        // 返回当前节点最大长度：左边或右边最长的那个 + 当前节点 1 个单位
        return 1 + Math.max(leftLength, rightLength);
    }
}
