/*
109. Convert Sorted List to Binary Search Tree:
有序链表转换二叉搜索树

Given the head of a singly linked list where elements are sorted in ascending order,
convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree
in which the depth of the two subtrees of every node never differ by more than 1.

链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 */
class Solution {

    private ListNode cur;

    public TreeNode sortedListToBST(ListNode head) {
        // 获取链表长度
        int length = 0;
        for (ListNode p = head; p != null; p = p.next) {
            length++;
        }
        // 初始化当前节点
        cur = head;
        // 根据左右指针和 BST 的性质，利用中序遍历构造 BST
        return inorder(0, length - 1);
    }

    private TreeNode inorder(int left, int right) {
        if (left > right) {
            // 下面的代码符合 left <= right，位于 [left, right] 区间
            return null;
        }

        // 中间位置
        int mid = (left + right) / 2;

        // 递归构造左子树：区间为 [left, mid - 1]
        TreeNode leftTree = inorder(left, mid - 1);

        // 构造根节点：
        // 因为之前的递归，所以会不断压缩区间，而 cur 会不停地移动，
        // 一直到 cur 走了半个区间的长度，到达中间位置，才会 return，
        // 此时 cur 的值就是中间节点值
        TreeNode root = new TreeNode(cur.val);
        cur = cur.next;

        // 递归构造右子树：区间为 [mid + 1, right]
        TreeNode rightTree = inorder(mid + 1, right);

        // 最后，将左右子树接到根节点上
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }

    //===========================第二种解法===========================
    public TreeNode sortedListToBSTAnother(ListNode head) {
        return build(head, null);
    }

    // 将链表左闭右开区间 [start, end) 的节点构造成 BST
    private TreeNode build(ListNode start, ListNode end) {
        if (start == end) {
            // 因为是左闭右开区间，此时为空集，返回 null
            return null;
        }
        // 获取中间节点
        ListNode mid = getMid(start, end);
        // 将中间节点设为当前二叉树的根节点
        TreeNode root = new TreeNode(mid.val);
        // 递归构造左右子树
        root.left = build(start, mid);
        root.right = build(mid.next, end);
        return root;
    }

    // 使用快慢指针，获取链表左闭右开区间 [start, end) 的中心节点
    ListNode getMid(ListNode start, ListNode end) {
        ListNode slow = start;
        ListNode fast = start;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
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
