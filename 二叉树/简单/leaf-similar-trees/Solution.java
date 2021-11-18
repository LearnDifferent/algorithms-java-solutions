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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // 获取存储叶子节点的列表
        List<Integer> list1 = getList(root1, new ArrayList<>());
        List<Integer> list2 = getList(root2, new ArrayList<>());
        
        if (list1.size() != list2.size()) {
            // 如果节点数量不同，那么肯定不同
            return false;
        }

        // 遍历所有元素（这里也可以写 i < list2.size()）
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                // 只要对应的元素不相等，就那么就不相同，返回 false
                return false;
            }
        }
        return true;
    }

    private List<Integer> getList(TreeNode root, List<Integer> list) {
        if (root == null) return list;

        if (root.left == null && root.right == null) {
            list.add(root.val);
        }

        getList(root.left, list);
        return getList(root.right, list);
    }
}
