/**
 * 234. Palindrome Linked List:
 * 回文链表
 * 解决方案：1. 获取中间的节点 2. 反转从中间开始的节点 3. 用反转后的中间节点，和正常的节点相对比
 *
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/
 */
class Solution {

    public boolean isPalindrome(ListNode head) {

        // 获取中间的节点（如果是偶数，就是中间两个节点中，右边的那个）
        ListNode middle = findMiddle(head);

        // 反转中间开始的节点，保存为 right
        ListNode right = reverse(middle);
        // 从头开始的节点保存为 left
        ListNode left = head;

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

    ListNode getMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 奇数时表示链表正中间，偶数时表示为中间两个节点的右边那个
        return slow;
    }

    // 使用迭代法反转
    ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur;
            cur = tmp.next;
            tmp.next = pre;
            pre = tmp;
        }
        return pre;
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