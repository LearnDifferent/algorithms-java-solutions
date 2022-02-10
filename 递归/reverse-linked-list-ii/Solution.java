/*
92. Reverse Linked List II:
反转链表 II
解决方案：递归

Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the
list from position left to position right, and return the reversed list.

链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 */
public class Solution {

    public ListNode reverseBetween(ListNode head, int left, int right) {

        // base case
        if (left == 1) {
            // 此时，说明反转是从第一个开始
            return reverseFromFirst(head, right);
        }

        // 如果不是从第一个开始反转，说明被反转的只有 head.next 开始的部分，
        // 所以递归反转，更新 head.next 即可（更新了 head.next 后，用 head 连接新的 head.next）
        // 假设是 [1][2][3][4]，要反转 [2][3][4]（left 为 2，right 为 4），
        // 那么递归到 [2][3][4] 的时候，触发 base case（此时 left 为 1，right 为 3）
        head.next = reverseBetween(head.next, left - 1, right - 1);

        return head;
    }

    /**
     * succ 表示 successor，
     * 假设有 [1][2][3]，需要反转为 [2][1][3]，
     * 那么 [1][2] 反转为 [2][1]，然后 [1] 连接 [3]，
     * 这个 [3] 就是 successor
     */
    private ListNode succ = null;

    // 反转第一个到第 N 个
    private ListNode reverseFromFirst(ListNode head, int n) {
        if (n == 1) {
            // n 为 1 时，表示只取这一个，所以直接返回 head 就行
            // 在返回之前，需要把 successor 存起来
            succ = head.next;
            return head;
        }

        // 当 n != 1 的时候，先递归反转 head.next
        // 假设是 [1][2][3][4][5]，反转 [1][2][3][4]（不反转 [5]）
        // 那就先递归反转 [2][3][4] 为 [4][3][2]，
        // result 递归反转后，即为 [4][3][2]
        ListNode result = reverseFromFirst(head.next, n - 1);

        // 此时，head.next 为 [4][3][2] 中的 [2]
        // 让其与 head，也就是 [1] 相连，此时 result 为 [4][3][2][1]
        head.next.next = head;
        // 最后，让 head，也就是 [4][3][2][1] 中的 [1]，
        // 与不需要反转的 [5] 相连，此时 result 为 [4][3][2][1][5]
        head.next = succ;

        // 此时，返回 result 即为最终结果
        return result;
    }
}

/*
 * Definition for singly-linked list.
 */
class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
