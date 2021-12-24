import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
102. Binary Tree Level Order Traversal:
二叉树的层序遍历

Given the root of a binary tree, return the level order traversal of its nodes' values.
(i.e., from left to right, level by level).

解决方案：BFS

链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 */
class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // 二叉树不会走回头路，所以不需要记录走过的路：Set<Node> visited

        // 将起点加入队列
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 保存当前层的节点数量
            int levelCount = queue.size();
            // 用于记录这一层的节点值
            List<Integer> level = new ArrayList<>();
            // 遍历当前这一层节点
            for (int i = 0; i < levelCount; i++) {
                // 弹出当前节点
                TreeNode cur = queue.poll();
                assert cur != null;

                // 记录当前节点值
                level.add(cur.val);

                // 将 cur 的相邻节点加入队列
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 将刚刚完成遍历的那一层的结果，加入进来
            result.add(level);
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