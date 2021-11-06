/**
 * 875. Koko Eating Bananas:
 * 爱吃香蕉的珂珂
 *
 * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
 *
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and
 * will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k
 * bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more
 * bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * 解决方案：二分查找。比较的是，从最小的速度，到最大的速度，能够完全吃完香蕉的最低的速度。
 * 所以，要按照速度从小到大，当作一个有序数组，使用二分搜索的左侧边界搜索，查找符合要求的最低速度。
 */
class Solution {

    public int minEatingSpeed(int[] piles, int h) {

        // left 设置为 1，表示最低的速度
        int left = 1;

        // 获取 piles 数组中，最大的元素，也就是最高的速度
        int maxInPiles = getMaxInPiles(piles);
        // 这里使用 [left, right)，所以让最高速度 +1
        int right = maxInPiles + 1;

        // 在 [left, right) 中寻找，在 left == right 时，退出循环
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 计算是否能在以 mid 的速度，在 h 小时内，吃完 piles 中的全部香蕉
            boolean canFinish = canFinish(mid, h, piles);
            if (canFinish) {
                // 如果在 mid 的速度下，可以吃完，就继续往左侧移动，
                // 看看有没有更小的速度，能够完成需求：[left, mid)
                right = mid;
            } else {
                // 如果不能完成，就去右侧，也就是更大的速度中，
                // 继续寻找可以完全消耗完香蕉的速度：[mid + 1, right)
                left = mid + 1;
            }
        }

        // 因为在 left == right 的时候退出，所以下面这个 return right; 也行
        return left;
    }

    private int getMaxInPiles(int[] piles) {
        int max = 0;
        for (int value : piles) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private boolean canFinish(int eatEveryHour, int withinHours, int[] piles) {
        // 计算耗时
        int spentTime = 0;
        // 遍历 piles 中的每组香蕉
        for (int numInGroup : piles) {
            // 计算当前这组香蕉，吃完所需的时间
            int currentTime = calculateTime(numInGroup, eatEveryHour);
            // 累加上之前的耗时，就是总时间
            spentTime += currentTime;
        }
        // 返回总共的耗时，是否小于等于规定的时间
        return spentTime <= withinHours;
    }

    private int calculateTime(int numInGroup, int eatEveryHours) {
        if (numInGroup % eatEveryHours == 0) {
            // 每次吃 eatEveryHours 的数量，如果能完整地吃完 numInGroup 的数量（没有余数）
            // 那么，这组香蕉的数量 / 每次吃多少个，就是消耗的时间
            return numInGroup / eatEveryHours;
        } else {
            // 如果每小时吃 eatEveryHours 的数量，还是不能完整地消费完 numInGroup 的数量
            // 说明还需要在多吃一次，也就是多吃一小时才能完全吃完
            return numInGroup / eatEveryHours + 1;
        }
    }

}
