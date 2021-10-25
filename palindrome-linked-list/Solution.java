/**
 * 234. Palindrome Linked List:
 * 回文链表
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/
 */
class Solution {

    public boolean isPalindrome(ListNode head) {

        // 从头开始的节点
        ListNode left = head;

        // 中间的节点（如果是偶数，就是中间两个节点中，右边的那个）
        ListNode middle = findMiddle(head);
        // 从中间开始反转的节点
        ListNode right = reverse(middle);

        // while 循环到 right 的结尾
        while (right != null) {
            if (left.val != right.val) {
                // 只要对应的值有一处不相等，就不是 palindrome
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }

    ListNode findMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 奇数时表示链表正中间，偶数时表示为中间两个节点的右边那个
        return slow;
    }

    ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return result;
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