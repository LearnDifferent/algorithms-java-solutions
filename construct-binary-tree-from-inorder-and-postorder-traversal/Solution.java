/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal:
 *
 * 从中序与后序遍历序列构造二叉树
 *
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 *
 * 参考 105. Construct Binary Tree from Preorder and Inorder Traversal：
 * - 题目链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * - 自己之前那题的解法：https://github.com/LearnDifferent/algorithms-java-solutions/blob/master/construct-binary-tree-from-preorder-and-inorder-traversal/Solution.java
 */
class Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                               int[] postorder, int postStart, int postEnd) {
        if (postStart > postEnd) {
            // inStart > inEnd 也是同样的效果，写哪个都行
            return null;
        }

        int rootValue = postorder[postEnd];

        int inorderRootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                inorderRootIndex = i;
                break;
            }
        }

        int leftSize = inorderRootIndex - inStart;

        TreeNode root = new TreeNode(rootValue);

        root.left = buildTree(inorder, inStart, inorderRootIndex - 1,
                postorder, postStart, postStart + leftSize - 1);

        root.right = buildTree(inorder, inorderRootIndex + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);

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
