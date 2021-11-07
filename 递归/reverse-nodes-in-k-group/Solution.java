/**
 * 25. Reverse Nodes in k-Group:
 * K 个一组翻转链表
 * 解决方案：递归
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

        // kPlus1Node 表示 k 个一组之后的那个节点
        ListNode kPlus1Node = head;
        ListNode firstNode = head;

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
        // 反转以 firstNode 开头，区间内的 k 个一组的节点
        ListNode reversedNode = reverseBetween(firstNode, kPlus1Node);
        // 递归当前方法，反转下一个 k 个一组
        ListNode reversedAfterFirstKGroup = reverseKGroup(kPlus1Node, k);

        // reversedNode 中最后一个节点 firstNode...
        // （因为反转了，所以 firstNode 是最后一个节点，而 reversedNode 是反转后的第一个节点）...
        // 的下一个节点，和递归反转的 reversedAfterFirstKGroup 相连接
        firstNode.next = reversedAfterFirstKGroup;
        // 最后，返回反转后的第一个节点
        return reversedNode;
    }

    /**
     * 反转两个 ListNode 之间的节点（参考：206. Reverse Linked List）
     * 注意，tail 不会被反转，也就是说，反转的是：[head, tail)
     */
    ListNode reverseBetween(ListNode head, ListNode tail) {
        ListNode previous = null;
        ListNode current = head;

        while (current != tail) {
            ListNode temp = current;
            // current.next 可以换成 temp.next
            current = current.next;
            temp.next = previous;
            previous = temp;
        }
        // 当 current 为 tail 的时候，退出循环
        // 此时，tail 节点之前的节点全部反转了
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
