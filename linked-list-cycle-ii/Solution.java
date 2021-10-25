/**
 * 142. Linked List Cycle II
 * 环形链表 II
 *
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 *
 * Do not modify the linked list.
 *
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 */
public class Solution {

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        boolean hasNoCycles = true;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                // 获取相遇点
                hasNoCycles = false;
                break;
            }
        }
        // 如果没有相遇点，返回 null
        if (hasNoCycles) {
            return null;
        }

        // 让 slow 指针回到起点
        slow = head;
        // 以同样的速度继续前进，此时，再次相遇的点就是回环的起始位置
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // 返回 fast 或 slow
        return slow;
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}