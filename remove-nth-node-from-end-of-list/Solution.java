/**
 * 19. Remove Nth Node From End of List:
 * 删除链表的倒数第 N 个结点
 * 解决方案：快慢双指针
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 */
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 假设有 0, 1, 2, 3, null
        // 要获取倒数第 1 个，也就是 3
        // 只需要让快指针先走 1 步，到 1 的位置，
        // 然后，快慢指针一起走，如下：
        // fast: 1, 2, 3, null
        // slow: 0, 1, 2, 3
        // 此时，慢指针就指向倒数第 1 个，也就是 3 了

        ListNode fast = head;
        ListNode slow = head;
        // 让快指针先走 n 次
        for (int i = 0; i < n; i++) {
            fast = fast.next;
            if (fast == null) {
                // 假设有 0, 1, 2, null。要获取倒数第 3 个，
                // 让 fast 先走 3 步，在第三步的时候，到了 null，
                // 此时，说明要剔除的是第 1 个，所以返回 head.next 即可
                // 即便是倒数第 4 个，也当作要剔除倒数第 3 个：
                return head.next;
            }
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        // 剔除了 slow 的下一个后，当前 head 就是需要的节点
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