/*
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
            return head;
        }

        ListNode previousNode = null;
        ListNode currentNode = head;

        while (currentNode != null) {

            // 保存当前节点为 temp
            ListNode temp = currentNode;

            // 让当前节点的下一个节点，变为当前节点
            // 注意，此时 temp 和 currentNode 都是指向相同的实例
            // 所以这里写 currentNode = currentNode.next 也是可以的
            // 不过，在完成这一步之后，currentNode 就发生了改变
            currentNode = temp.next;

            // 然后，再操作 temp 的下一个节点，变为当前节点的上一个节点
            temp.next = previousNode;
            // 最后，把 temp 的值，作为新的当前的前一个节点
            previousNode = temp;
        }

        // 当 currentNode 为 null 的时候，表示其为最后一个节点的下一个节点
        // 所以返回前一个节点
        return previousNode;
    }
}
