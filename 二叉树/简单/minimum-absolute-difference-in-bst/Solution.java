/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    // 最小的差值
    int min;
    // 前一个节点
    TreeNode pre;

    public int getMinimumDifference(TreeNode root) {
        // 初始化
        min = Integer.MAX_VALUE;
        pre = null;

        // 中序遍历
        dfs(root);
        return min;
    }

    private void dfs(TreeNode root){
        if(root == null) return;

        dfs(root.left);

        if(pre != null){
            // 当前节点的值 - 前一个节点的值
            min = Math.min(min, root.val - pre.val);
        }

        // 设置当前节点为前一个节点
        pre = root;

        dfs(root.right);    
    }
}
