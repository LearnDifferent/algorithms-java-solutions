/**
 * 114. Flatten Binary Tree to Linked List:
 *
 *  二叉树展开为链表
 *
 *  解决方案：递归 https://labuladong.gitee.io/algo/2/18/21/
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
 * list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 */
class Solution {

    public void flatten(TreeNode root) {

        // base case
        if (root == null) {
            return;
        }

        // 递归，拉平左子节点和右子节点
        flatten(root.left);
        flatten(root.right);

        // 递归完成后，左右两个子节点已经被拉平了
        // 接下来，就要拉平当前节点

        // 将拉平后的结果，先保存起来
        TreeNode leftAfterFlatten = root.left;
        TreeNode rightAfterFlatten = root.right;

        // 拉平的时候，左节点就变为 null 了
        root.left = null;
        // 因为拉平了，所以只剩下右子节点了
        // 首先，把拉平后的左子节点，转变为新的右子节点
        root.right = leftAfterFlatten;

        // 然后，要将拉平后的右子节点，连接到新的右子节点的尾部
        // 需要先遍历，得到当前右子节点的尾部
        TreeNode tmp = root;
        while (tmp.right != null) {
            // 当 tmp 的下一个右子节点为 null 的时候，
            // 说明 tmp 到了尾部
            tmp = tmp.right;
        }

        // 当 tmp 到达尾部后，连接拉平后的旧的右子节点
        tmp.right = rightAfterFlatten;
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