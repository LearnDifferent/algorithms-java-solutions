/*
99. Recover Binary Search Tree:
恢复二叉搜索树

You are given the root of a binary search tree (BST),
where the values of exactly two nodes of the tree were swapped by mistake.
Recover the tree without changing its structure.

链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 */
class Solution {

    private TreeNode a;
    private TreeNode b;
    private TreeNode pre;

    public void recoverTree(TreeNode root) {
        // 中序遍历，找出需要交换的 a 和 b 节点
        inorder(root);
        // 交换 a 和 b 节点的值
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);

        // 如果前一个节点 > 当前节点，说明不符合 BST
        // 题目中说了，有 2 个节点的出错，按照 BST 的中序遍历的规则，
        // 从小到大排序的话，第一个出错的节点 a，会比 a 后面位置的节点大；
        // 而第二个出错的节点 b，会比 b 前面位置的节点小。
        if (pre != null && pre.val > root.val) {
            if (a == null) {
                // 第一个出错的是 a 节点，它比下一个节点大，所以是 pre
                a = pre;
            }
            // 第二个出错的是 b 节点，它比前一个节点小，所以是 root
            b = root;
        }

        // 在遍历 右节点 之前，将"前一个节点"更新为"当前节点"
        pre = root;
        inorder(root.right);
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
