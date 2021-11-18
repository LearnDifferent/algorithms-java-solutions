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

    public int findSecondMinimumValue(TreeNode root) {
        /* 
        题目中有 3 个设定：
            1. each node in this tree has exactly two or zero sub-node
            2. root.val = min(root.left.val, root.right.val)
            3. nodes in the tree is in the range [1, 25]
        由此可知：
            1. 子节点永远大于根节点
            2. root.val 是全局的最小数
            3. 那么，root 的“左子树中最小的节点”和“右子树中最小的节点” 之中最小的那个，就是第二小的
            4. root 不为 null
        */

        // 左子树和右子树中最小的值（也就是倒数第二小的值）
        // 默认值设置为 -1，当有倒数第二小的值时，再更新
        minVal = -1;

        updateMin(root.left, root.val);
        updateMin(root.right, root.val);

        // 如果倒数第二小的值没有更新，说明只有最小值，没有倒数第二小值
        return minVal;
    }

    private int minVal;

    private void updateMin(TreeNode root, int excludeValue) {
        if (root == null) return;

        updateMin(root.left, excludeValue);
        updateMin(root.right, excludeValue);

        // 更新倒数第二小的值的条件：不等于 excludeValue（也就是不等于最小值）且比当前“倒数第二小的值”还小
        boolean update = root.val != excludeValue && root.val < minVal;
        // 如果在当前 root 不为 excludeValue 的情况下，minVal 还是默认值 -1，那么就修改默认值
        boolean setUp = minVal == -1 && root.val != excludeValue;
        if (update || setUp) {
            minVal = root.val;
        }
    }
}
