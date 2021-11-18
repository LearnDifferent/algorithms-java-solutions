/**
 * 563. Binary Tree Tilt:
 *
 * 二叉树的坡度
 *
 * 给定一个二叉树，计算 整个树 的坡度 。
 *
 * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。
 * 如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
 *
 * 整个树 的坡度就是其所有节点的坡度之和。
 *
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 *
 * The tilt of a tree node is the absolute difference between the sum of all left subtree node values and all right
 * subtree node values. If a node does not have a left child, then the sum of the left subtree node values is treated as
 * 0. The rule is similar if there the node does not have a right child.
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-tilt
 */
class Solution {

    private int result;

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        getSumAndUpdateResult(root);

        return result;
    }

    private int getSumAndUpdateResult(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 获取左右子树各自的总和
        int leftSum = getSumAndUpdateResult(root.left);
        int rightSum = getSumAndUpdateResult(root.right);

        // 计算当前 root 的结果
        int rootResult = Math.abs(leftSum - rightSum);
        // 更新 result
        result += rootResult;

        // 返回当前树所有节点的值的总和
        return leftSum + rightSum + root.val;
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
