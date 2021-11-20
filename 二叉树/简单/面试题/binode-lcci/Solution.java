/**
 * 面试题 17.12. BiNode LCCI:
 *
 * The data structure TreeNode is used for binary tree, but it can also used to represent a single linked list (where
 * left is null, and right is the next node in the list). Implement a method to convert a binary search tree
 * (implemented with TreeNode) into a single linked list. The values should be kept in order and the operation should be
 * performed in place (that is, on the original data structure).
 *
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，
 * 也就是在原始的二叉搜索树上直接修改。返回转换后的单向链表的头节点。
 *
 * 链接：https://leetcode-cn.com/problems/binode-lcci
 */
class Solution {

    // 因为是对 BST 按照 right -> root -> left 顺序遍历的，
    // 所以最终的 head 是 BST 中最小值的节点，
    // 所以它也是新 TreeNode 的头节点
    private TreeNode head;

    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 对 BST 进行逆转顺序的中序遍历：right -> root -> left
        convertBiNode(root.right);

        // 递归完右节点后，让右节点设为 head
        // 因为逆向顺序递归后，是从大到小排序，
        // 所以在这之前的 head 的值，会比 root 的值大，所以设为右节点
        root.right = head;

        // 更新头节点，让头节点保持为最小的节点
        head = root;

        convertBiNode(root.left);

        // 递归完左节点后，让左节点置空
        root.left = null;

        return head;
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
