
/**
 * 206. Reverse Linked List: Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            // head == null 是判空操作
            // 主要是 head.next == null，表示这个节点只有一个，就不用反转了
            return head;
        }

        // 递归下一个节点，获取下一个节点递归完成的结果
        ListNode result = reverseList(head.next);

        // 因为下一个节点已经完成了反转，所以只需对当前的节点进行指针进行反转即可
        // 让之前的下一个节点的指针 next，指向当前的节点
        head.next.next = head;
        // 当前节点的下一个就是 null
        head.next = null;

        return result;
    }
}