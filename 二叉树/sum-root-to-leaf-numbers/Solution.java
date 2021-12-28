/*
129. Sum Root to Leaf Numbers:
求根节点到叶节点数字之和

You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers.
Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.

链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 */
class Solution {

    /**
     * 记录 root to leaf 经过的节点的数字（字符串）
     */
    private StringBuilder path;
    /**
     * 在遍历的过程中，计算最终的结果
     */
    private int result;

    public int sumNumbers(TreeNode root) {
        path = new StringBuilder();
        result = 0;

        traverse(root);
        return result;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序遍历位置，记录节点值
        path.append(root.val);

        if (root.left == null && root.right == null) {
            // 到达 leaf 后，获取该路径的数值，并累加到最终结果中
            result += Integer.parseInt(path.toString());
        }

        // 递归遍历左右子树
        traverse(root.left);
        traverse(root.right);

        // 完成一个 root to leaf 的路径后，撤销经过的节点
        // 因为是 0 到 9 的数字的字符串，所以可以用 deleteCharAt 来完成撤销
        path.deleteCharAt(path.length() - 1);
    }
}


/**
 * Definition for a binary tree node.
 */
class TreeNode {

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