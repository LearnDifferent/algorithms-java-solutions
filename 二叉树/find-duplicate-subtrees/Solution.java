import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652. Find Duplicate Subtrees:
 * 寻找重复的子树
 *
 * 解决方案：使用后序遍历：首先要弄清左子树和右子树的具体情况，然后再根据根节点的情况，来判断
 *
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * 链接：https://leetcode-cn.com/problems/find-duplicate-subtrees
 */
class Solution {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> countSubtrees = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();

        serializeWithPostorderTraversal(root, countSubtrees, result);
        return result;
    }

    /**
     * Serialize Binary Tree with Post-order Traversal
     *
     * @param root          需要被序列化的根节点
     * @param countSubtrees 所有序列化后的子树，以及出现的次数
     * @param result        需要返回的答案：重复的子树的根节点
     * @return 根据后续遍历，完成来序列化的二叉树
     */
    private String serializeWithPostorderTraversal(TreeNode root,
                                                   Map<String, Integer> countSubtrees,
                                                   List<TreeNode> result) {
        if (root == null) {
            // 如果 root 为 null，序列化为：#
            return "#";
        }

        // 序列化左右子树
        String left = serializeWithPostorderTraversal(root.left, countSubtrees, result);
        String right = serializeWithPostorderTraversal(root.right, countSubtrees, result);

        // 序列化当前这个树，格式为：根节点的值,左子树,右子树
        String subtree = root.val + "," + left + "," + right;

        // 重复的树，也只需要返回一个即可:
        // 获取当前这颗树出现的次数（转化为 int 比较方便）
        int count = countSubtrees.getOrDefault(subtree, 0);

        // 如果已经出现一次，当前就是第二次出现，也就是重复出现，所以要加入（超过 2 次的话，就没必要加入）
        if (count == 1) {
            result.add(root);
        }

        // 发现这颗树的次数，需要加一
        countSubtrees.put(subtree, count + 1);

        // 返回序列化的结果
        return subtree;
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}