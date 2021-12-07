/*
172. Factorial Trailing Zeroes:

阶乘后的零：求阶乘的结果，尾数有几个零

Given an integer n, return the number of trailing zeroes in n!.
Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

解题思路：尾数出现 0，说明有阶乘中 2 * 5，
而只要是偶数，就能分离出 2，所以 2 出现的次数肯定大于 5 出现的次数，
也就是说，只要计算 n 能分离出多少个 5，就能知道结果的尾数有多少个 0

https://leetcode-cn.com/problems/factorial-trailing-zeroes
 */
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        for (int i = n; i / 5 > 0; i = i / 5) {
            res += i / 5;
        }
        return res;
    }
}
