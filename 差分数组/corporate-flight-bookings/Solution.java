/**
 * 1109. Corporate Flight Bookings / 航班预订统计
 * https://leetcode-cn.com/problems/corporate-flight-bookings/submissions/
 */
public class Solution {

    /**
     * 使用差分数组解决
     *
     * @param bookings bookings[i] = [first, last, seats]，
     *                 表示在 [first, last] 区间内的航班，每个航班都有 seats 个座位被预定了。
     *                 first 和 last 表示飞机的编号，是从 1 开始的（而不是从 0 开始）
     * @param n        n 表示一共有 n 个编号的航班，比如 n = 2 时，有编号 1 航班和编号 2 航班
     * @return 返回数组，统计每个航班被预定座位的总和。
     * 比如 [7, 8, 5] 表示 1 号被预定了 7 个座位，2 号被预订了 8 个座位，3 号被预定了 5 个座位。
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 差分数组，每个元素为 0
        int[] diff = new int[n];

        for (int[] b : bookings) {
            // [left, right] 区间内每个元素 +val
            int left = b[0] - 1;
            int right = b[1] - 1;
            int val = b[2];
            // 让 [left, ...] 全部 +val
            diff[left] += val;
            // 如果 right + 1 还在范围内（数组最后一个位置为 n - 1）
            if (right + 1 < n) {
                diff[right + 1] -= val;
            }
        }

        // 根据差分数组，还原 原数组，这里不创建新的数组，直接用 diff 完成还原的步骤
        // diff[i] = nums[i] - nums[i - 1]，所以 nums[i] = diff[i] + nums[i - 1]，
        // 这里的 diff[i] 就是 nums[i]，最终就是 diff[i] = diff[i] + diff[i - 1]
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }
        return diff;
    }

    // 也可以使用 Difference 类的解法：
    public int[] corpFlightBookings0(int[][] bookings, int n) {
        // 初始化一个全部为 0 的数组，nums[i] 表示编号为 i + 1 的航班预定的座位数量
        int[] ordered = new int[n];

        // 构造差分数组的工具类
        Difference diff = new Difference(ordered);

        for (int[] b : bookings) {
            int left = b[0] - 1;
            int right = b[1] - 1;
            int val = b[2];
            // 在 [left, right] 区间，增加 val 数量的座位
            diff.increase(left, right, val);
        }

        // 返回更新了座位的结果
        return diff.getResult();
    }
}

/**
 * 差分数组的工具类
 */
class Difference {

    // 维护一个差分数组
    private final int[] diff;

    public Difference(int[] nums) {
        int n = nums.length;
        assert n > 0;

        // 初始化差分数组
        diff = new int[n];

        // 根据传入的 nums，构造差分数组
        // base case
        diff[0] = nums[0];
        // 当前差分 = 当前元素 - 前一个元素
        for (int i = 1; i < n; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    /**
     * 让 [left, right] 区间内所有元素都增加 val。
     * 如果 val 为负数，表示该区间内所有元素都减少相应数值。
     * 因为还原的时候，是将 diff 数组从左往右累加，
     * 所以 diff[i] 加了 val 之后，以后从 [i] 开始的所有元素都会跟着 +val。
     *
     * @param left  [left
     * @param right right]
     * @param val   [left, right] 区间内，增加的值（可以为负数）
     */
    public void increase(int left, int right, int val) {
        // 从 [left, ...] 全部 +val
        diff[left] += val;
        // 只要 right + 1 还在索引范围内：
        if (right + 1 < diff.length) {
            // 从 [right + 1, ...] 全部 -val
            diff[right + 1] -= val;
        }
        // 这样的话，就可以保证 [left, right] 全部 +val
    }

    /**
     * 返回构造 Difference 类时 传入的 nums 数组，在 increase 方法更新后的 结果。
     *
     * @return 假如 [1,2] 数组整个区间内的元素都 +1，就返回 [2,3]
     */
    public int[] getResult() {
        // numsAfterIncr 就是更新过后的 nums（nums 在构造时已经传入）
        int[] numsAfterIncr = new int[diff.length];
        // base case
        numsAfterIncr[0] = diff[0];
        // 因为 diff[i] = nums[i] - nums[i = 1]，所以 nums[i] = diff[i] + nums[i]
        for (int i = 1; i < diff.length; i++) {
            numsAfterIncr[i] = diff[i] + numsAfterIncr[i - 1];
        }
        return numsAfterIncr;
    }
}
