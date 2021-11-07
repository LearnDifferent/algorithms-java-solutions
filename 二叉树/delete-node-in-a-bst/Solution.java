/**
 * 450. Delete Node in a BST:
 *
 *  删除二叉搜索树中的节点
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 */
class Solution {

    public TreeNode deleteNode(TreeNode root, int key) {

        // base case
        if (root == null) {
            return null;
        }

        if (root.val > key) {
            // 说明需要删除的节点在左子树中，递归删除该节点，并获取新的左子树
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            // 如果删除的就是当前节点，需要分 3 中情况判断：
            // 情况 1：当前节点，没有子节点，那么可以直接删除当前节点
            // 情况 2：当前节点有一个子节点，另一个节点为 null，
            // 假设是 root.left 存在，root.right 为 null，
            // 那么删除 root 就相当于让 root.left 取代 root；反之亦然。
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 根据上面的分析，应该还有个 root.right 和 root.left 都为 null 的情况，
            // 然而情况 2 的解法，实际上已经包括了这种情况。
            // 因为，假设有一个 root.right 和 root.left 都为 null 的节点，
            // 在符合 if (root.left == null) 的判断后，进入了 return root.right;
            // 此时，因为 root.right 也是 null，所以会触发 base case，也就是返回 null，
            // 然后继续返回，因为还是 null，所以 root 变为了 null，表示 root 被删除了

            // 情况 3：如果需要删除的节点 root，存在两个子节点，那么，
            // 必须找到 root.left 中的最大值，或是 root.right 中的最小值成为新的 root
            // 注意：这里理论上应该通过链表操作，不断交换"旧的 root 节点"和
            // "新的 root"的节点（root.left 中的最大节点，或是 root.right 中的最小节点）的位置。
            // 不过为了方便，这里先将"新的节点的值"赋给"旧的节点"，然后根据"新的节点的值"，
            // 递归删除"除了当前的新的节点"之后，另一个拥有"新的节点的值"的那个节点。
            // 这样就相当于完成了删除当前节点，并维持 BST 的操作

            // 这里选择找到 root.left 中最大的节点，让其成为新的 root 节点的方案
            TreeNode maxNode = getMaxNode(root.left);
            root.val = maxNode.val;
            root.left = deleteNode(root.left, maxNode.val);
        }

        return root;
    }

    private TreeNode getMaxNode(TreeNode node) {
        // BST 中最右边的节点，就是最大的节点
        while (node.right != null) {
            node = node.right;
        }
        return node;
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
