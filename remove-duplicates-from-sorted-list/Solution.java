/**
 * 83. Remove Duplicates from Sorted List:
 * 删除排序链表中的重复元素
 * 解决方案：双指针
 * 【双指针技巧秒杀四道数组/链表题目 https://mp.weixin.qq.com/s/55UPwGL0-Vgdh8wUEPXpMQ】
 *
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 *
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 */
class Solution {

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            if (fast.val != slow.val) {
                // 操作指针，让 slow 指针和下一个不重复元素相连
                slow.next = fast;
                // 移动慢指针
                slow = slow.next;
            }
            // 如果指针指向的值相等，就继续移动快指针
            fast = fast.next;
        }

        // slow 后面的都是全部元素，所以要断开
        slow.next = null;
        return head;
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