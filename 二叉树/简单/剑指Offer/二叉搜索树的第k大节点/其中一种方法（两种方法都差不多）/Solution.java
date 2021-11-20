import java.util.ArrayList;
import java.util.List;

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

        result = new ArrayList<>();
        updateList(root, k);
        return result.get(k - 1).val;
    }

    private List<TreeNode> result;

    private void updateList(TreeNode root, int targetSize) {
        // base case
        if (root == null) {
            return;
        }

        updateList(root.right, targetSize);

        // 如果已经达成目标，直接返回即可
        if (result.size() == targetSize) {
            return;
        }

        // 存入当前节点
        result.add(root);

        updateList(root.left, targetSize);
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
