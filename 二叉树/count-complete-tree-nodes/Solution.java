/*
222. Count Complete Tree Nodes / 完全二叉树的节点个数

完全二叉树（Complete Binary Tree）：

- 中文解释：除了最底层可能没有填满，其余都是满的。最底层优先填满左边。
- Wiki: every level, except possibly the last, is completely filled in a complete binary tree,
and all nodes in the last level are as far left as possible

链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 */
class Solution {

    // 题目中要求的遍历 Complete Binary Tree
    public int countNodes(TreeNode root) {
        TreeNode leftTree = root;
        TreeNode rightTree = root;

        // 计算左、右子树的深度
        int leftDepth = 0;
        int rightDepth = 0;

        while (leftTree != null) {
            leftTree = leftTree.left;
            leftDepth++;
        }

        while (rightTree != null) {
            rightTree = rightTree.right;
            rightDepth++;
        }

        // 如果左右子树的深度相同，则是一棵 Perfect Binary Tree
        if (leftDepth == rightDepth) {
            return (int) Math.pow(2, leftDepth) - 1;
        }

        /*
        如果左右深度不同，则递归计算。
        因为 Complete Binary Tree 的左、右子树中，
        肯定有一颗子树是 Perfect Binary Tree，
        所以那颗子树不会继续递归，而是触发 return，只消耗 O(logN) 时间。

        总结：
        递归深度就是获取树的深度 O(logN)，
        每次递归所花费的时间就是 while 循环，需要 O(logN)，
        总体的时间复杂度是 O(logN * logN)
         */
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // 统计 Perfect Binary Tree 的节点，节点总数和树的深度呈指数关系
    public int countNodesPerfect(TreeNode root) {
        int depth = 0;
        // 计算树的深度
        while (root != null) {
            root = root.left;
            depth++;
        }
        /*
         节点总数就是 2^depth - 1。
         比如 Perfect Binary Tree 为：
            [ ]
            / \
          [ ] [ ]
         那么，深度是 2，节点数就为 2 的平方 - 1
         */
        return (int) Math.pow(2, depth) - 1;
    }

    // 获取普通二叉树的节点总数
    public int countNodesNormal(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesNormal(root.left) + countNodesNormal(root.right);
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
