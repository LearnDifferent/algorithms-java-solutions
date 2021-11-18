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
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;

        List<Integer> list = getSortedList(root, new ArrayList<>());

        // nodes in the given tree will be in the range [1, 100]，所以肯定有一个以上
        // 创建新的节点
        TreeNode newRoot = new TreeNode(list.get(0));

        // 用于存储遍“上一个节点”
        TreeNode tmp = newRoot;

        // 从第二个开始遍历，让每个节点都成为“上一个节点”的右节点
        for (int i = 1; i < list.size(); i++) {
            // 创建当前节点
            TreeNode cur = new TreeNode(list.get(i));
            // 让当前节点成为“上一个节点”的右节点
            tmp.right = cur;
            // 更新“上一个节点”
            tmp = cur;
        }

        return newRoot;
    }

    private List<Integer> getSortedList(TreeNode root, List<Integer> list) {
        if (root == null) return list;

        getSortedList(root.left, list);
        list.add(root.val);
        return getSortedList(root.right, list);
    }
}
