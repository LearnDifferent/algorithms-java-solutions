/**
 * 1094. Car Pooling / 拼车
 * 
 * 这道题可以使用差分数组解决
 * 
 * https://leetcode-cn.com/problems/car-pooling/
 */
class Solution {

    /**
     * =========================================
     * 注意：题目中规定了：0 <= from < to <= 1000，
     * 也就是可以从 0 号车站上车，从 1000 号车站下车。
     * =========================================
     *
     * @param trips    trip[i] = [numPassengers, from, to]，
     *                 表示 numPassengers 个乘客会从 from 上车，到 to 下车
     * @param capacity 载客量
     * @return 一辆 capacity 载客量的货车，能否让乘客完成上下车
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // 0 号车站到 1000 号车站
        int totalStops = 1001;
        // 先构建一个数组，每个元素为 0，表示在该车站时，车上有 0 个乘客
        int[] diff = new int[totalStops];

        for (int[] t : trips) {
            int num = t[0];
            int from = t[1];
            // last 表示乘客最后停留的车站，下一个车站这些乘客已经离开来
            int last = t[2] - 1;

            // [from, ...] 区间增加 num
            diff[from] += num;
            // 如果 last + 1 在区间内，就从 [last + 1, ...] 减少 num
            if (last + 1 < totalStops) {
                diff[last + 1] -= num;
            }
        }

        if (diff[0] > capacity) {
            // 如果起始站已经超过载客量，直接返回 false
            return false;
        }
        // 计算每一站的乘客人数，并判断是否超过载客量，这里直接通过 diff 数组来还原乘客人数：
        // diff[0] 表示第 0 站的乘客人数，diff[1] 表示第 0 车站到第 1 车站增加的人数，
        // 所以 1 号车站的乘客人数 = diff[0] + diff[1]，这里用 diff[1] 来存储结果，
        // 也就是 diff[1] = diff[0] + diff[1]，写为 diff[1] += diff[0]，以此类推……
        for (int i = 1; i < totalStops; i++) {
            diff[i] += diff[i - 1];
            if (diff[i] > capacity) {
                // 如果超过载客量，返回 false
                return false;
            }
        }
        return true;
    }
}
