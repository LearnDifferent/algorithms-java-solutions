/**
 * 344. Reverse String
 * 反转字符串
 *
 * Write a function that reverses a string. The input string is given as an array of characters s.
 *
 * 链接：https://leetcode-cn.com/problems/reverse-string
 */
class Solution {

    public void reverseString(char[] s) {

        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}