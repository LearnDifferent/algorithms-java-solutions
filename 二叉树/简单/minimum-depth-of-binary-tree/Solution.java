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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);

        if (leftMinDepth == 0 || rightMinDepth == 0) {
            // 当左边的深度为 0 时，说明左边没有节点，所以当前深度就是右边深度 + 1，
            // 又因为左边的深度是 0，所以也可以直接左边深度 + 右边深度 + 1
            // 当右边的深度为 0 时同理……
            return leftMinDepth + rightMinDepth + 1;
        }

        // 当左右节点同时存在时，返回最小的那个深度 + 1
        return 1 + Math.min(leftMinDepth, rightMinDepth);
    }
}
