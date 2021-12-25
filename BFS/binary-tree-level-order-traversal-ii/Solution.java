import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
107. Binary Tree Level Order Traversal II:
二叉树的层序遍历 II

Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
(i.e., from left to right, level by level from leaf to root).

解决方案：参考 102. Binary Tree Level Order Traversal
(该题目的链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal)

链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 */
class Solution {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 这里使用 LinkedList，因为结果需要自下往上存储，所以需要使用 addFirst()
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                assert cur != null;

                level.add(cur.val);

                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            // 这里使用 addFirst()，实现从下往上的顺序
            result.addFirst(level);
        }
        return result;
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
