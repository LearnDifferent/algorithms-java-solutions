import java.util.ArrayList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II:
 *
 * 不同的二叉搜索树 II
 *
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 *
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 */
class Solution {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {

        List<TreeNode> results = new ArrayList<>();

        if (start > end) {
            // 此时节点为 null，也属于一种 BST，所以存储进去
            results.add(null);
            return results;
        }

        // 从 start 开始到 end 结束，遍历所有可能的 root 节点的情况
        for (int rootValue = start; rootValue <= end; rootValue++) {
            // 递归以 rootValue 的值为 root 的左子树和右子树的情况
            List<TreeNode> leftResults = generateTrees(start, rootValue - 1);
            List<TreeNode> rightResults = generateTrees(rootValue + 1, end);

            // 以当前 rootValue 为 root 的值，连接左右子树，组合出所有 BST 的情况
            for (TreeNode leftNode : leftResults) {
                for (TreeNode rightNode : rightResults) {
                    TreeNode root = new TreeNode(rootValue);
                    root.left = leftNode;
                    root.right = rightNode;
                    results.add(root);
                }
            }
        }

        return results;
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

