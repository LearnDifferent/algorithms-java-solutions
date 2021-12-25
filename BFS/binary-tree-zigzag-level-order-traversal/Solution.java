import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
103. Binary Tree Zigzag Level Order Traversal:
二叉树的锯齿形层序遍历

Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
(i.e., from left to right, then right to left for the next level and alternate between).

给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

解决方案：参考 102. Binary Tree Level Order Traversal
(该题目的链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal)

链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 */
class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        // 是否从左往右
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            // 因为有双向的顺序要求，所以使用 LinkedList
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                assert cur != null;

                if (leftToRight) {
                    // 从左往右的话，就是正常顺序，每次都加到尾部
                    level.addLast(cur.val);
                } else {
                    level.addFirst(cur.val);
                }

                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }

            result.add(level);
            leftToRight = !leftToRight;
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
