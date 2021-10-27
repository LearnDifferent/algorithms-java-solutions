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
		// 因为 left != 1，所以当前第 1 个位置的 head 节点不需要反转
		// 直接让 head 的 next 连接刚刚反转好的下一个节点即可
        head.next = nextNodeAfterReversed;
		// 在 left != 1 的时候，将 head 之后的节点反转，反转后连接 head
		// 最后返回 head 就可以了
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

		// 当 n != 1 的时候，反转 head.next
        ListNode result = reverseFirstToN(head.next, n - 1);
		// 在反转了 head.next 之后，head.next 就是目前最后一个节点
		// 所以，需要让 head 变为“需要反转的最后一个节点”
		// 也就是 head.next 的 next（下一个）节点指向 head
        head.next.next = head;
		// 最后，让反转后的最后一个节点 head 的 next 指向后躯节点 succ 即可
        head.next = succ;

		// 返回的是 result，因为 head 节点已经反转到 result 的最后了
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
