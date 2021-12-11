/*
236. Lowest Common Ancestor of a Binary Tree：
二叉树的最近公共祖先

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 */
class Solution {

    /**
     * 获取 {@code root} 中，{@code p} 节点和 {@code q} 节点之间，最近的公共祖先。
     *
     * @param root 根节点
     * @param p    某个节点
     * @param q    某个节点
     * @return 1. 如果 {@code root} 不是 {@code p} 和 {@code q} 的公共祖先，返回 null；
     * 2. 如果 {@code p} 或 {@code q} 中的其中一个的节点，公共祖先是 {@code root}，返回该节点；
     * 3. 如果 {@code root} 是 {@code p} 和 {@code q} 的公共祖先，返回 {@code root}
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // base case
        if (root == null || root == p || root == q) {
            // 等价于：if (root == null) return null;
            // 和 if (root == p || root == q) return root;
            return root;
        }

        // 递归，看看左子节点和右子节点，是否为 p 和 q 的公共祖先
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        // 如果左右子节点都是 p 和 q 的公共祖先，说明当前节点也是公共祖先
        if (leftNode != null && rightNode != null) {
            return root;
        }

        // 如果左右子节点都不为 p 和 q 的公共祖先，说明当前节点也不是公共祖先
        // 不过，下面这个 if 判断的代码可以不写，因为最后一行的代码，也包括了这种情况
//        if (leftNode == null && rightNode == null) {
//            return null;
//        }

        // 剩下的情况，就是左子节点 或 右子节点当中，有且只有一个为 p 和 q 的公共祖先。
        // 不过，实际上可以理解为：不是左子节点为公共祖先，就是右子节点为公共祖先；
        // 如果都不是公共祖先，这个代码也会返回 null
        return leftNode != null ? leftNode : rightNode;
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}
