/*
25. Reverse Nodes in k-Group:
K 个一组翻转链表
解决方案：递归

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a
multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // tail 表示 k 个一组之后的那个节点
        ListNode tail = head;

        // 循环 k 次，获取 tail
        for (int i = 0; i < k; i++) {
            // 如果 tail 为 null，说明没办法形成 k 个一组，直接返回之前的 head
            if (tail == null) {
                return head;
            }
            // 在循环中，更新 tail
            tail = tail.next;
        }
        // 反转 [head, tail) 区间内的节点（也就是反转从 head 开始的 k 个一组的节点）
        ListNode result = reverseUntil(head, tail);
        // 在反转了之后，head 就是 k 个一组中的最后一个了
        // 接下来，就是递归反转从 tail 开始的节点，然后让 head 与反转后的结果相连
        head.next = reverseKGroup(tail, k);

        // 最后，返回 result
        return result;
    }

    /**
     * 反转两个 ListNode 之间的节点（参考：206. Reverse Linked List）
     * 注意，tail 不会被反转，也就是说，反转的是：[head, tail)
     */
    private ListNode reverseUntil(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != tail) {
            ListNode temp = cur;
            // cur.next 可以换成 temp.next
            cur = cur.next;
            temp.next = pre;
            pre = temp;
        }
        // 当 cur 为 tail 的时候，退出循环
        // 此时，tail 节点之前的节点全部反转了
        return pre;
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
