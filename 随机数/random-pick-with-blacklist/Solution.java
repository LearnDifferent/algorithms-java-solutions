import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
710. Random Pick with Blacklist:
黑名单中的随机数

You are given an integer n and an array of unique integers blacklist.
Design an algorithm to pick a random integer in the range [0, n - 1] that is not in blacklist.
Any integer that is in the mentioned range and not in blacklist should be equally likely to be returned.

Optimize your algorithm such that it minimizes the number of calls
to the built-in random function of your language.

Implement the Solution class:

- Solution(int n, int[] blacklist) :
    - Initializes the object with the integer n and the blacklisted integers blacklist.
- int pick() :
    - Returns a random integer in the range [0, n - 1] and not in blacklist.

链接：https://leetcode-cn.com/problems/random-pick-with-blacklist
 */
class Solution {

    /**
     * 合法数值的数组的长度（除黑名单外，元素的个数）
     */
    private final int size;
    /**
     * key 是黑名单的数值，value 是替换该黑名单的合法数值
     */
    private final Map<Integer, Integer> blackToWhite;
    private final Random ran;

    /**
     * 将区间 [0,n) 当作一个数组，
     * 然后将 blacklist 中的元素移到数组的最末尾，
     * 同时用一个哈希表进行映射。
     * 也就是说，最后在 [0, size) 区间内是合法的数值，
     * 而 [size, n) 区间内的是非法的数值，
     * 如果 [0, size) 区间内有非法的数值，
     * 就将其映射到 [size, n) 中的合法数值上。
     */
    public Solution(int n, int[] blacklist) {

        size = n - blacklist.length;
        blackToWhite = new HashMap<>();
        ran = new Random();

        for (int b : blacklist) {
            // key 是黑名单中的值，这里的 value 任意数值都行，后面会重新设置
            blackToWhite.put(b, 0);
        }

        // 替换黑名单的合法数值的 index（该 index 同时也是合法数值）
        // 初始为 [0, n) 中最后一个元素（的位置）
        int white = n - 1;

        for (int b : blacklist) {
            if (b >= size) {
                // 如果已经在 [size, n) 之中，就可以跳过，
                // 因为后面只会生成在 [0, size) 之间的随机数
                continue;
            }
            while (blackToWhite.containsKey(white)) {
                // 只要是黑名单中的数值，就跳过，获取 [size, n) 中合法的数值
                white--;
            }
            // 将黑名单该位置的索引，映射到合法的数值上
            blackToWhite.put(b, white);
            white--;
        }
    }

    /**
     * 在 [0, n) 中，随机选择一个非 blacklist 的数
     */
    public int pick() {
        // 现在数组可以看作 [0, size) 和 [size, n) 两个部分
        // 获取 [0, size) 部分的随机数
        int num = this.ran.nextInt(size);
        // 如果黑名单中，有该随机数，就返回映射的值；如果黑名单中没有，就直接返回该值
        return blackToWhite.getOrDefault(num, num);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
