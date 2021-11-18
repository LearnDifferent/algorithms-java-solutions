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
    public String tree2str(TreeNode root) {
        /*
        [root,left,right] 转换为 root(left)(right)：
        1. 如果只有 left 为空节点，则输出 root()(right)
        2. 如果只有 right 为空节点，则可以忽略右节点的 ()，输出为root(left)
        3. 如果左右都为空，则输出 root
        */

        // base case
        if (root == null) return "";

        if (root.left == null && root.right == null) {
            // 左右都为空
            return String.valueOf(root.val);
        }

        // 只有右为空
        if (root.right == null) {
            return root.val + "(" + tree2str(root.left) + ")";
        }

        // 只有左为空和其他情况
        return root.val + "(" + tree2str(root.left) + ")" + "(" + tree2str(root.right) + ")";
    }
}
