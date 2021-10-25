/**
 * 25. Reverse Nodes in k-Group:
 * K 个一组翻转链表
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a
 * multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode firstNode = head;
        ListNode kPlus1Node = head;

        // 循环 k 次，获取第 k + 1 个节点的值
        for (int i = 0; i < k; i++) {
            // 循环 k 次，如果其中一次为 null
            // 说明没办法形成 k 个一组，直接返回之前的 head
            if (kPlus1Node == null) {
                return head;
            }
            // 然后获取第 k + 1 个节点的值
            kPlus1Node = kPlus1Node.next;
        }
        // 反转区间内的 k 个一组的节点
        ListNode reversedKGroupNode = reverseBetween(firstNode, kPlus1Node);
        // 递归，反转下一个 k 个一组
        ListNode reversedAfterFirstKGroup = reverseKGroup(kPlus1Node, k);
        // 然后，让反转后的区间内的第一个节点，和这个递归后的结果相连接
        firstNode.next = reversedAfterFirstKGroup;
        // 最后，返回反转后的结果
        return reversedKGroupNode;
    }

    /**
     * 反转两个 ListNode 之间的节点。
     * 注意1：这里第二个参数表示倒数第二个节点。
     * 注意2: 根据题意，first 不会为 null
     * 参考：206. Reverse Linked List
     */
    ListNode reverseBetween(ListNode first, ListNode secondToLast) {
        ListNode previous = null;
        ListNode current = first;

        while (current != secondToLast) {
            ListNode temp = current;
            // current.next 可以换成 temp.next
            current = current.next;
            temp.next = previous;
            previous = temp;
        }
        return previous;
    }
}

/**
 * Definition for singly-linked list.
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
