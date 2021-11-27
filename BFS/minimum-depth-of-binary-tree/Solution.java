import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. Minimum Depth of Binary Tree:
 * 二叉树的最小深度
 */
class Solution {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 核心数据结构
        Queue<TreeNode> queue = new LinkedList<>();
        // 二叉树不会走回头路，所以不需要记录走过的路：Set<Node> visited

        // 将起点加入队列
        queue.offer(root);
        // 记录步数：root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!queue.isEmpty()) {
            // 在遍历之前，先保留当前 queue 的大小，表示当前这一层节点的数量
            // 因为 queue 是 FIFO，只要记录了当前这一层的节点数，下面遍历的时候就能保证这一层完成遍历
            // 这一层节点的子节点只会加入到 queue 的末尾，所以不会影响这一层的遍历
            int currentLevelCount = queue.size();

            // 将当前队列中的所有节点向四周扩散（遍历当前这一层节点，并保持它们的子节点）
            for (int i = 0; i < currentLevelCount; i++) {
                // 弹出一个节点
                TreeNode cur = queue.poll();
                // 因为左右子节点一定是不为 null 的情况下才加入 queue 中的，所以肯定不为 null
                assert cur != null;

                // 判断是否到达终点（是否为叶子节点）
                if (cur.left == null && cur.right == null) {
                    return depth;
                }

                // 将 cur 的相邻节点加入队列
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            // 更新步数：遍历完成当前这一层后，深度 + 1
            depth++;
        }
        return depth;
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
