/**
 * 19. Remove Nth Node From End of List:
 * 删除链表的倒数第 N 个结点
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 */
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 假设有 0 1 2 3 4，要选倒数第 2 个，也就是 3
        // 让快指针先走 2 步，也就是走到 2 的位置
        // 此时，让两个指针同步走，当快指针为 null 的时候
        // 慢指针刚好在倒数第二个的位置上
        // 快指针同步后的路径：2 3 4 null
        // 慢指针同步后的路径：0 1 2 3

        // 注意一下，这里是删除倒数第 n 个节点
        // 所以要获取倒数第 n - 1 个节点后，再删除倒数第 n 个
        ListNode fast = head;
        ListNode slow = head;
        // 让快指针先走 n 次
        for (int i = 0; i < n; i++) {
            fast = fast.next;
            if (fast == null) {
                // 如果此时快指针已经为 null
                // 倒数第 n 个节点就是第一个节点
                // 删除第一个节点，就是返回第二个节点
                return head.next;
            }
        }

        // 两个指针一起走，当快指针的下一个节点到结尾，也就是为 null 的时候
        // 说明此时慢指针所在位置就是倒数第 n - 1 个节点
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow 为倒数第 n - 1 个节点，删除该节点即可
        slow.next = slow.next.next;
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