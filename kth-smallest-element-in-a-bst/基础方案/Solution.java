/**
 * 230. Kth Smallest Element in a BST:
 *
 * 二叉搜索树中第K小的元素
 *
 * 解决方案：中序遍历 BST（二叉搜索树，root 的值 > left 的值 > right 的值），
 * 得到的结果是按照值，从小到大排序。
 *
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 */
class Solution {

    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历后，返回遍历过程中，获取到的结果
        traverse(root, k);
        return result;
    }

    // 节点的排名（因为会一直递归，所以一开始会从最小的节点开始往大的排）
    int rank = 0;
    // 结果：需要的元素
    int result = 0;

    /**
     * 中序遍历（In-Order Traversal），然后统计该 root 节点的是在从小到大的排序中，排名第几。
     * 如果符合 {@code targetRank}，就记录该结果。
     *
     * @param root       BST
     * @param targetRank 在从小到大的排序中，需要该排名的节点
     */
    private void traverse(TreeNode root, int targetRank) {

        if (root == null) {
            return;
        }

        // 中序遍历左子树
        traverse(root.left, targetRank);

        // 中序遍历到当前的节点
        // 先让排名 +1
        rank++;
        // 比对该排名，是否符合需求
        if (targetRank == rank) {
            // 找到了，就记录
            result = root.val;
            // 直接 return，不用再继续了
            return;
        }

        // 中序遍历右子树
        traverse(root.right, targetRank);
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
