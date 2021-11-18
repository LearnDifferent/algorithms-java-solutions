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
    public boolean findTarget(TreeNode root, int k) {
        // 获取 bst 所有数值
        List<Integer> list = getSortedList(root, new ArrayList<>());

        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum > k) {
                right--;
            } else if (sum < k) {
                left++;
            } else {
                return true;
            }
        }

        return false;
    }

    private List<Integer> getSortedList(TreeNode root, List<Integer> result) {
        if (root == null) return result;

        getSortedList(root.left, result);
        result.add(root.val);
        return getSortedList(root.right, result);
    }
}
