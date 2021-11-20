/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点:
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 */
class Solution {
    public int kthLargest(TreeNode root, int k) {
        // 等于查找右边的节点数
        // left < root < right
        // 遍历顺序:right -> root -> left
        targetValue = -1;
        currentRank = 0;
        updateRank(root, k);
        return targetValue;
    }

    int targetValue;
    int currentRank;

    private void updateRank(TreeNode root, int targetRank) {
        if (root == null) return;

        updateRank(root.right, targetRank);
        // 当前排名 +1
        currentRank++;
        if (currentRank == targetRank) {
            targetValue = root.val;
            return;
        }
        updateRank(root.left, targetRank);
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
