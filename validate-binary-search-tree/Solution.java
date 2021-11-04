/**
 * 98. Validate Binary Search Tree:
 * 验证二叉搜索树
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
class Solution {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * 因为要约束的不是当前节点的左子节点和右子节点的值，
     * 而是整个左子树的值小于当前节点的值，整个右子树的值大于当前节点的值，
     * 所以，需要的是 {@code minNode} 和 {@code maxNode} 作为约束的参数。
     * 在不知道 {@code minNode} 和 {@code maxNode} 的情况下，
     * 传入 null，表示需要递归去查找最大或最小的节点。
     *
     * @param root    当前节点
     * @param minNode 最小的值的节点：
     *                在递归的过程中，对于 root.right 来说，最小的节点会变为 root，
     *                也就是缩小约束的条件，来满足整个左子树的节点都小于当前节点
     * @param maxNode 最大的值的节点：在递归的过程中，对于 root.left 来说，最大的节点会变为 root，
     *                和 {@code minNode} 同理
     * @return 是否为 BST
     */
    private boolean isValidBST(TreeNode root, TreeNode minNode, TreeNode maxNode) {

        // base case
        if (root == null) {
            return true;
        }

        // 满足条件：maxNode.val > root.val > minNode.val 才符合要求
        // 假如出现 [2,2,2] 的情况，那么，它是不符合要求的，所以等于的情况，也要认定为 false
        if (minNode != null && root.val <= minNode.val) {
            return false;
        }
        if (maxNode != null && root.val >= maxNode.val) {
            return false;
        }

        return isValidBST(root.left, minNode, root)
                && isValidBST(root.right, root, maxNode);
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
