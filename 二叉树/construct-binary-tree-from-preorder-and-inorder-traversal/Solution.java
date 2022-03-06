/*
105. Construct Binary Tree from Preorder and Inorder Traversal:

从前序与中序遍历序列构造二叉树:
给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。

解决方案：根据前序与中序遍历的特性，递归构建

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
is the inorder traversal of the same tree, construct and return the binary tree.

链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 */
class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    /**
     * 对于"前序遍历后的数组"，其抽象的构成为 [root][root.left][root.right]：
     * 其中 [root] 起始于：起始 index 的位置（也就是 {@code preStart}）
     * <p>
     * ----------------------------------------
     * </p>
     * [root.left] 是从起始的 index + 1 的位置开始，
     * 结束的位置在：(起始的 index + 1) +（[root.left] 本身的长度 - 1） = 起始的 index + [root.left] 本身的长度
     * <p>
     * 关于 [root.left] 本身的长度，需要根据另一个 "中序遍历后的数组"（{@code inorder}）来计算。
     * 因为 {@code inorder} 的 [root] 是位于中间的，所以它的左边的长度，就是 [root.left] 的长度。
     * ----------------------------------------
     * </p>
     * [root.right] 则是从"起始的 index + [root.left] 本身的长度 + 1"的位置开始
     * 一直到结尾的 index 结束（也就是 {@code preEnd}）。
     * <p>
     * =======================================
     * </p>
     * 对于"中序遍历后的数组"，其抽象的构成为 [root.left][root][root.right]：
     * 首先，需要根据另一个"前序遍历后的数组"（{@code preorder}），获取 root 的值。
     * 然后，遍历这个"中序遍历后的数组"（{@code inorder}），
     * 在其中，找到和 root 的值相等的数值，该数值所在的 index，就是"中序遍历后的数组"的 [root] 的 index。
     * <p>
     * ----------------------------------------
     * </p>
     * 在知道 [root] 的 index 后，[root.left] 的起点就是 {@code inStart}，终点就是 [root] 的 index - 1
     * <p>
     * ----------------------------------------
     * </p>
     * [root.right] 就是 [root] 的 index + 1，一直到 {@code inEnd}
     *
     * @param preorder 前序遍历后的数组
     * @param preStart 前序遍历后的数组的起始 index
     * @param preEnd   前序遍历后的数组的尾部 index
     * @param inorder  中序遍历后的数组
     * @param inStart  中序遍历后的数组的起始 index
     * @param inEnd    中序遍历后的数组的尾部 index
     * @return {@link TreeNode}
     */
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }

        // root 的值
        int rootValue = preorder[preStart];

        // 根据 root 的值，找到 inorder 数组中的 root 的 index
        int inorderRootIndex = -1;
        for (int i = inStart; i <= inEnd; i++)  {
            if (inorder[i] == rootValue) {
                inorderRootIndex = i;
                break;
            }
        }

        // 获取左子树的大小：inorder 的 root 往左，就是左子树的大小
        // preorder 的左子树大小和 inorder 的左子树大小是一致的
        int leftSize = inorderRootIndex - inStart;

        // 创建二叉树
        TreeNode root = new TreeNode(rootValue);
        // 递归，获取左右子节点（左右子树）
        root.left = buildTree(preorder, preStart + 1, leftSize + preStart,
                inorder, inStart, inorderRootIndex - 1);

        root.right = buildTree(preorder, leftSize + preStart + 1, preEnd,
                inorder, inorderRootIndex + 1, inEnd);

        return root;
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