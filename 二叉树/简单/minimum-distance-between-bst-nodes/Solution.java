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
    public int minDiffInBST(TreeNode root) {
        List<Integer> result = getSortedList(root, new ArrayList<>());

        // 最小差值
        int min = Integer.MAX_VALUE;

        // The number of nodes in the tree is in the range [2, 100]，所以肯定至少有 2 个
        for (int i = 1; i < result.size(); i++) {
            int cur = result.get(i) - result.get(i - 1);
            min = Math.min(cur, min);
        }
        return min;
    }

    private List<Integer> getSortedList(TreeNode root, List<Integer> result) {
        if (root == null) return result;

        getSortedList(root.left, result);
        result.add(root.val);
        return getSortedList(root.right, result);
    }
}
