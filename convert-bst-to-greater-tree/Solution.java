/**
 * 538. Convert BST to Greater Tree:
 * 把二叉搜索树转换为累加树
 *
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree
 * such that every key of the original BST is changed to
 * the original key plus the sum of all keys greater than the original key in BST.
 *
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 *
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 *
 * BST 转为累加树的意思是，假设 BST 中，其中一个节点的值是 5，最大的节点的值是 8，
 * 那么比 5 这个节点大的节点就有 6、7、8。在转化为累加树的时候，
 * 这个 5 的节点就要转化为 5+6+7+8=26，6 的节点就要转化为 6+7+8=21，以此类推。
 *
 * 解决方案：使用变种的中序遍历。中序遍历 BST，能以从小到大的顺序排序，
 * 这道题的要点，是知道比当前节点大的所有节点的值，所以需要的是从大到小的排序，
 * 只需要将中序遍历的 root.left, root, root.right 的遍历顺序，
 * 改为 root.right, root, root.left 的遍历顺序，即可实现从大到小的排序。
 */
class Solution {

    public TreeNode convertBST(TreeNode root) {
        // 第二个参数填 0，因为 root 就是最大的节点，所以没有比它更大的节点，所以 biggerNodesSum 为 0
        traverseAndGetSum(root, 0);
        return root;
    }

    /**
     * 累加 {@code biggerNodesSum} 的值，获取当前节点的累加的值
     *
     * @param root           当前节点
     * @param biggerNodesSum 比当前节点，更大的所有节点的值的总和
     * @return 当前节点的值 + 比当前节点，更大的所有节点的值的总和
     */
    private int traverseAndGetSum(TreeNode root, int biggerNodesSum) {
        if (root == null) {
            return biggerNodesSum;
        }

        // 变种的中序遍历：root.right, root, root.left
        // 遍历获取右子树的所有节点的值的总和
        int rightSum = traverseAndGetSum(root.right, biggerNodesSum);
        // 获取当前节点的值，并累加为新的值：root.val = root.val + rightSum
        root.val += rightSum;
        // 现在，root.val 对于左子树而言，就是 biggerNodesSum，继续递归完成变种的中序遍历
        return traverseAndGetSum(root.left, root.val);
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