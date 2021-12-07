/**
 * 204. Count Primes:
 * 计数质数
 *
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 * 定义：如果一个数如果只能被 1 和它本身整除，那么这个数就是质数（素数 / prime numbers）
 * 因为 0 和 1 不是 prime numbers，所以相当于求在 [2, n) 区间内，有多少个 prime numbers。
 *
 * 可以使用 Sieve of Eratosthenes 算法解决。
 *
 * 链接：https://leetcode-cn.com/problems/count-primes
 */
class Solution {

    public int countPrimes(int n) {

        // 因为是返回小于 n 的质数，所以存储从 0 到 n - 1 的质数
        boolean[] notPrime = new boolean[n];

        /*
         从 i = 2 开始，是因为 0 和 1 肯定不是质数。

         i * i < n 就跳出循环的原因，可以看下面的例子：
         ===============================
         12 = 2 × 6
         12 = 3 × 4
         12 = sqrt(12) × sqrt(12)
         12 = 4 × 3
         12 = 6 × 2
         ===============================
         在例子中，sqrt(12) 前后的乘积是一样的，只是调换了位置，
         所以只是看乘积的话，到「根号 n」就够了。
          */
        for (int i = 2; i * i < n; i++) {
            // 只要 i 是质数，那么 i 的倍数 肯定不是质数
            // 这里使用 i 的平方的方式，来避免冗余
            if (!notPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    notPrime[j] = true;
                }
            }
        }

        // 最后，统计有几个质数
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
            }
        }

        return count;
    }
}
