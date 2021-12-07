/*
  793. Preimage Size of Factorial Zeroes Function:

  阶乘函数后 k 个零：
  给定一个非负整数 k，问有多少个 n，使得 n!（n 的阶乘）结果末尾有 k 个 0。

  Let f(x) be the number of zeroes at the end of x!.
  Recall that x! = 1 * 2 * 3 * ... * x and by convention, 0! = 1.

  - For example, f(3) = 0 because 3! = 6 has no zeroes at the end,
  while f(11) = 2 because 11! = 39916800 has two zeroes at the end.

  Given an integer k, return the number of non-negative integers x have the property that f(x) = k.

  链接：https://leetcode-cn.com/problems/preimage-size-of-factorial-zeroes-function
  */
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        long countZeros = solution.trailingZeroes(Long.MAX_VALUE / Integer.MAX_VALUE);
        // 经过实验，Long.MAX_VALUE / Integer.MAX_VALUE 的情况下，依旧满足尾数有 Math.pow(10, 9) 个 0
        System.out.println(countZeros > Math.pow(10, 9));
    }

    /*
     解题思路：
     获取满足条件的 n 最小是多少，最大是多少，最大值和最小值相减，就可以算出来有多少个 n 满足条件。
     所以需要 二分查找 中「搜索左侧边界」和「搜索右侧边界」的技巧。

     注意：
     题目中规定，0 <= k <= 10^9，也就是尾数可能出现 10^9 个 0，
     所以使用 Long.MAX_VALUE / Integer.MAX_VALUE
     （Integer.MAX_VALUE 不够大，不采用；而 Long.MAX_VALUE 又太大了，会影响效率）
      */
    public int preimageSizeFZF(int k) {
        // 满足条件的右侧边界 - 满足条件的左侧边界 + 1 = 满足条件的数量
        return (int) (rightBound(k) - leftBound(k) + 1L);
    }

    private long leftBound(int k) {
        long left = 0L;
        // 查找左侧边界时，可以在最大值前面 1 个数就停下
        // 其实 Long.MAX_VALUE 已经超过最大值了，
        // 所以 -1 也无关紧要，这里只是套用左侧边界的公式而已
        long right = Long.MAX_VALUE / Integer.MAX_VALUE - 1L;

        while (left < right) {
            long mid = left + (right - left) / 2L;
            if (trailingZeroes(mid) < k) {
                // 如果尾数的 0 不够多，就往右边查找：[mid + 1, right)
                left = mid + 1L;
            } else if (trailingZeroes(mid) > k) {
                // 如果尾数的 0 太多了，就往左查找：[left, mid)
                right = mid;
            } else {
                // 如果找到了就继续向左，看看左侧有没有其他符合的
                right = mid;
            }
        }

        // 最后 left、right 和 mid 都停留在同一个地方，返回 right 或 left
        return left;
    }

    private long rightBound(int k) {
        // 寻找右侧边界的时候，可以从最小值 +1 位置开始
        long left = 1L;
        long right = Long.MAX_VALUE / Integer.MAX_VALUE;

        while (left < right) {
            long mid = left + (right - left) / 2L;
            if (trailingZeroes(mid) > k) {
                // 当尾数的 0 过多时，向左寻找：[left, mid)
                right = mid;
            } else if (trailingZeroes(mid) < k) {
                // 当尾部的 0 过少时，向右寻找：[mid + 1, right)
                left = mid + 1;
            } else {
                // 当找到时，继续向右寻找，探寻右侧边界
                left = mid + 1;
            }
        }

        // 因为 left = mid + 1，所以 mid = left - 1，返回 left - 1
        return left - 1L;
    }

    // 计算 n 的阶乘后面有几个 0 的方法。Leetcode 也有相应的题目：
    // https://leetcode-cn.com/problems/preimage-size-of-factorial-zeroes-function/
    // 这里使用 long 是为了避免整型溢出
    private long trailingZeroes(long n) {
        long res = 0L;
        for (long i = n; i / 5L > 0L; i = i / 5L) {
            res += i / 5L;
        }
        return res;
    }
}
