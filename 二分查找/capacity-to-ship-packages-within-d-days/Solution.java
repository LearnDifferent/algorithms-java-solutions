/**
 * 1011. Capacity To Ship Packages Within D Days:
 *
 * 解决方案：二分搜索左侧边界。将载重量从低到高当作有序数组，来查找符合条件的最低的值（最左侧的边界）
 *
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The ith package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will
 * result in all the packages on the conveyor belt being shipped within days days.
 *
 * 在 D 天内送达包裹的能力：
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * 传送带上的第 i 个包裹的重量为 weights[i]。
 * 每一天，我们都会按给出重量的顺序往传送带上装载包裹。
 * 我们装载的重量不会超过船的最大运载重量。
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 */
class Solution {

    public int shipWithinDays(int[] weights, int days) {

        // left 表示最小载重，即 weights 数组中最大的那个重量。
        // 因为如果小于最大的重量，最大的重量就因为超重而不能运了
        int left = getMaxWeight(weights);

        // right 表示最高的载重，就是一次性运完所有的内容，也就是 weights 数组中所有元素的总和
        // 这里使用左侧边界搜索，区间为 [left, right)，理论上，应该是 [left, right + 1)，
        // 才能完成从 left 到 right 的查找，但是因为 right 就是最高的载重，
        // 肯定是能一次性运完的，所以可以当循环到了那个地方的时候，可以不用检验了。
        int right = getTotalWeight(weights);

        // 在 [left, right) 中循环
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean canFinish = canFinish(weights, days, mid);
            if (canFinish) {
                // 继续向左寻找，看看有没有更小的载重：[left, mid)
                right = mid;
            } else {
                // 如果不能按时完成，就提高载重，也就是往右移动:[mid + 1, right)
                left = mid + 1;
            }
        }
        // return right; 也是一样的
        return left;
    }

    private int getMaxWeight(int[] weights) {
        // 获取 weights 数组中最大的数
        int maxWeight = 0;
        for (int w : weights) {
            if (maxWeight < w) {
                maxWeight = w;
            }
        }
        return maxWeight;
    }

    private int getTotalWeight(int[] weights) {
        // 获取 weights 数组中，所有数的总和
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        return sum;
    }

    /**
     * 能否在 {@code withinDays} 天数内，以 {@code weightPerDay} 的速度，
     * 搬运完 {@code weights} 数组内，所有重量的物品
     *
     * @param weights      货物对应的重量
     * @param withinDays   在这期间完成搬运
     * @param weightPerDay 每天搬运的最大重量
     * @return 能否在 {@code withinDays} 天数内，以 {@code weightPerDay} 的速度，
     * 搬运完 {@code weights} 数组内，所有重量的物品
     */
    private boolean canFinish(int[] weights, int withinDays, int weightPerDay) {
        // 消耗的天数
        int spentDay = 0;
        // 已经搬运了的货物的重量（每天结束后，会重新计算）
        int transferredWeight = 0;
        for (int weight : weights) {
            if (transferredWeight + weight > weightPerDay) {
                // 只要接下来需要搬运的重量 + 已经搬运的重量，大于每天最多的重量
                // 那么，今天就已经搬运完成了，接下来的重量留到明天再搬运
                spentDay++;
                transferredWeight = weight;
            } else {
                // 否则，继续搬运今天的份
                transferredWeight += weight;
            }
        }
		if (transferredWeight != 0) {
			// 如果还有重量没运完，就再花 1 天把它运往
			spentDay++;
		}
        // 只要消耗的天数，小于或等于规定的天数即可
        return spentDay <= withinDays;
    }
}
