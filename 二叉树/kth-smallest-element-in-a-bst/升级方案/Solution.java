/**
 * 230. Kth Smallest Element in a BST:
 *
 * 二叉搜索树中第K小的元素
 *
 * 解决方案：中序遍历 BST（二叉搜索树，root 的值 > left 的值 > right 的值），
 * 得到的结果是按照值，从小到大排序。
 *
 * 新的解决方案的升级点：获取以当前的根节点为基准的 这棵二叉树的 左子树的所有节点总数，
 * 将左子树的节点总数 + 1，就是当前节点的排名。如果当前节点的排名符合需要的排名，就完成了查找。
 * 如果排名比需要的排名大，说明需要的在左子树；如果排名小于需要的排名，说明需要的在右子树。
 *
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 */
class Solution {

    public int kthSmallest(TreeNode root, int k) {
        TreeNode target = findTarget(root, k);
        return target.val;
    }

    /**
     * 获取以 {@code root} 为根的二叉树的 所有节点的 总数
     *
     * @param root root
     * @return 节点总数
     */
    private int getSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 递归获取左右子树的节点
        int leftSize = getSize(root.left);
        int rightSize = getSize(root.right);
        // 返回左子树的总数 + 右子树的总数 + 当前节点的 1 个
        return leftSize + rightSize + 1;
    }

    private TreeNode findTarget(TreeNode root, int targetRank) {
        // 获取左子树的节点总数
        int leftChildCount = getSize(root.left);
        // 当前节点的排名
        int currentRank = leftChildCount + 1;

        if (currentRank > targetRank) {
            // 如果当前的排名，比需要的排名大，说明需要的排名在左子树中
            // 去搜索左子树的排名为 targetRank 的节点
            return findTarget(root.left, targetRank);
        } else if (currentRank < targetRank) {
            // 如果当前的排名，小于需要的排名，就在右子树中搜索
            // 因为右子树的值大于当前节点的值，所以需要的排名...
            // 在右子树中，是 targetRank - currentRank
            return findTarget(root.right, targetRank - currentRank);
        } else {
            // 如果排名相等，说明当前的即为需要的
            return root;
        }
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