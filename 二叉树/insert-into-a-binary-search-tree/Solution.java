/**
 * 701. Insert into a Binary Search Tree:
 * 二叉搜索树中的插入操作
 *
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 * Return the root node of the BST after the insertion.
 * It is guaranteed that the new value does not exist in the original BST.
 *
 * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
 */
class Solution {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            // 将 null 转化为节点，也就是插入节点
            return new TreeNode(val);
        }

        if (root.val > val) {
            // 如果比当前节点的值小，说明要往左子树中插入，所以递归获取插入后的新的左子树
            root.left = insertIntoBST(root.left, val);
        }
        if (root.val < val) {
            // 如果比当前节点的值大，说明要往右子树中插入，所以递归获取插入后的新的右子树
            root.right = insertIntoBST(root.right, val);
        }
        // It is guaranteed that the new value does not exist in the original BST
        // 所以不需要判断 if (root.val == val) 的情况

        // 返回完成插入后的根节点即可
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
