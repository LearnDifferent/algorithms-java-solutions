/**
 * 410. Split Array Largest Sum:
 * 分割数组的最大值
 *
 * Given an array nums which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 题目含义：假设 nums = [7,3,5], m = 2，也就是将数组切一刀，分为 2 个子数组。
 * 此时可以分为 [7] [3,5] 和 [7,3] [5] 两种情况。
 * 如果是 [7] [3,5] 的情况，那么 [7] 的总和为 7，[3,5] 的总和为 8，所以这种情况，最大值就是 8。
 * 如果是 [7,3] [5] 的情况，那么 [7,3] 的总和为 10，[5] 的总和为 5，所以最大值为 10。
 * 所以，符合题目要求的"最大值 最小"的条件的，就是 [7] [3,5] 的情况，此时最大值为 8，返回 8 作为结果。
 *
 * 解决方案：二分搜索左侧边界。
 * 题目的意思是将数组切分为固定数量的子数组，然后求各个数组中的最大值，找到最小的那个最大值并返回。
 * 我们可以反向思维，将各种排列可能出现的最大值，当作一个有序数组，在这个"最大值集合"的有序数组中，
 * 查找在"固定最大值"的情况下，在每个"固定的最大值"约束下，该数组"至少可以切分为多少个子数组"，
 * （也就是说，每个子数组都尽可能接近"固定的最大值"，看看能分为多少个子数组）。
 *
 * 使用"至少可以切分为多少个子数组"，是因为以"最接近固定的最大值"为限制，对每个子数组进行约束，
 * 可以获取*固定的切分数量*，用这个切分数量去和，需要的切分数量进行对比，就能够利用到二分搜索的技巧，
 * 就能够在不计算完所有结果的情况下，反推出符合条件的的结果。
 *
 * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
 */
class Solution {

    public int splitArray(int[] nums, int m) {
        // 因为子数组不能为空，所以在所有子数组可能出现的最大值中，最小的情况，
        // 就是 nums 中最大的那个元素单独为一组（且其他组的总和都小于该组）的情况，
        // 其他的情况下，最大的那个元素肯定会和别的元素相加，所以只有最大的元素单独为一组才是最小的"最大值"
        int left = getMaxInNums(nums);

        // 而最大的情况，就是只需要分为 1 组，也就是全部 nums 中元素的总和
        int right = getNumsSum(nums);

        // 使用二分搜索的左侧边界查找，在 [left, right) 中查找（可以查看其他左侧边界的相关笔记）
        while (left < right) {
            int mid = left + (right - left) / 2;
            // mid 为最大值，获取在最大值的限制下，最少可以分为几个连续子数组
            int count = countMinimumSubarray(nums, mid);
            if (count < m) {
                // 如果小于需要的 m 个子数组，说明 mid 设置的"最大值"太大了，
                // 此时，需要去更小的"最大值"中寻找，也就是左侧的 [left, mid)
                right = mid;
            } else if (count > m) {
                // 如果这里的子数组太多了，说明"最大值"太小了，所以去右侧寻找 [mid + 1, right)
                left = mid + 1;
            } else {
                // 如果找到了符合条件的其中一个"最大值"，就去看看比这个"最大值"还小的情况下，
                // 会不会也有更小的符合情况的"最大值"，所以要去左侧继续寻找
                right = mid;
            }
        }
        // 此时，left 和 right（以及 mid）停留的位置，就是左侧边界，也就是符合条件的最小的"最大值"
        return left;
    }

    private int getMaxInNums(int[] nums) {
        // 题目中的 nums 的元素全部都是大于等于 0 的，所以先初始化最大值为 0
        int max = 0;
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
        }
        // 返回 nums 中最大的元素
        return max;
    }

    private int getNumsSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public int countMinimumSubarray(int[] nums, int maxEachSubarray) {
        // 每个 nums 的子数组的总和，必须小于等于 maxEachSubarray

        // 统计至少可以分成几个子数组
        int count = 1;
        // 每个子数组的元素的总和
        int sumEachSubarray = 0;

        for (int num : nums) {
            if (sumEachSubarray + num <= maxEachSubarray) {
                // 如果加了当前的 num 之后，还符合最大的要求，就可以添加到总和中
                sumEachSubarray += num;
            } else {
                // 如果加上当前的 num 会超过限制，就不要添加
                // 让统计的子数组数量 +1
                count++;
                // 然后，将总和清零，然后加上当前的 num，表示进入了下一轮的统计
                sumEachSubarray = num;
            }
        }
        // 返回统计结果
        return count;
    }
}
