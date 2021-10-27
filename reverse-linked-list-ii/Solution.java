/**
 * 92. Reverse Linked List II:
 * 反转链表 II
 * 解决方案：递归
 *
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 */
class Solution {

    public ListNode reverseBetween(ListNode head, int left, int right) {

        // base case
        if (left == 1) {
            // 此时，说明反转是从第一个开始
            return reverseFirstToN(head, right);
        }

        // 反转当前节点之后的节点:
		// 因为是从第二个节点开始反转，所以缩小的范围应该整体向前移动
		// 可以理解为起始点向后移动了，所以保存原来的范围就需要让范围向前移动
        ListNode nextNodeAfterReversed = reverseBetween(head.next, left - 1, right - 1);
        // 重新设置当前节点的下一个节点
        head.next = nextNodeAfterReversed;
        // 一直反转下一个，直到触发 base case
        return head;
    }

    // 获取反转之后，最后一个节点后面的节点 successor
	// 因为只是反转区间内的节点，反转了之后，
	// 其最后一个节点还需要和 successor 相连接
    ListNode succ = null;

    // 反转第一个到第 N 个
    public ListNode reverseFirstToN(ListNode head, int n) {
        if (n == 1) {
            // 为 1 时，表示只取这一个
            // 它的后面还有节点，所以把它的下一个存起来
            succ = head.next;
            return head;
        }

        // 递归下一个并获取反转后的返回值（注意是 n - 1）
        ListNode result = reverseFirstToN(head.next, n - 1);

        // 因为下一个已经递归完成了，所以只要反转当前的节点的指针即可
        head.next.next = head;
        // 注意，这里表示指向最后一个节点的下一个节点（指向的 succ 节点不参与反转）
        head.next = succ;

        return result;
    }
}

/*
 * Definition for singly-linked list.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
