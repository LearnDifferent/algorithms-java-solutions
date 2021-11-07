/**
 * 116. Populating Next Right Pointers in Each Node:
 *
 * 解决方案：递归
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 *
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 *
 * 把"完美二叉树"的每一层的节点，都用 next 指针连接起来，如下所示：
 * ____________[1]--->null
 * ______[2]-------->[3]-->null
 * ___[4]-->[5]-->[6]--->[7]-->null
 */
class Solution {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        connectTwoNodes(root.left, root.right);
        return root;
    }

    private void connectTwoNodes(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }

        // 连接传入的两个节点：相当于图示中的：[2]-->[3]
        node1.next = node2;

        // 连接相同父节点的左右两个子节点
        // 相当于图示中的：[4]-->[5]
        connectTwoNodes(node1.left, node1.right);
        // 相当于图示中的：[6]-->[7]
        connectTwoNodes(node2.left, node2.right);

        // 连接同一层级，但是父节点不同的两个子节点
        // 相当于图示中的：[5]-->[6]
        connectTwoNodes(node1.right, node2.left);
    }
}

// Definition for a Node.
class Node {

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}