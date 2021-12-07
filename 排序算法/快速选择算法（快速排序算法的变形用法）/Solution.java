import java.util.Random;

/*
215. Kth Largest Element in an Array

数组中的第K个最大元素

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 */
class Solution {
    /** 生成随机数 */
    private final Random RANDOM = new Random();

    public int findKthLargest(int[] nums, int k) {
        // 题目中规定：1 <= k <= nums.length
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    /**
     * 尾递归版（tail recursion），理论上这个更快
     *
     * @param arr    数组
     * @param start  起始 index
     * @param end    结束 index
     * @param target 目标 index，也就是 k 在排序数组中的索引
     * @return 第 k 大的元素
     */
    private int quickSelect(int[] arr, int start, int end, int target) {
        while (start < end) {
            int pivot = partition(arr, start, end);
            if (pivot > target) {
                // 在左侧寻找：[start, pivot - 1]
                end = pivot - 1;
            } else if (pivot < target) {
                // 在右侧寻找：[pivot + 1, end]
                start = pivot + 1;
            } else {
                // 找到了，返回该元素
                return arr[target];
            }
        }
        // 如果 start == end，说明区间内只有 1 个元素，
        // 此时，该元素的两边分别大于和小于 target，那么该元素所处位置就是 target
        return arr[start];
    }

    // 快速选择算法（递归版），类似于快速排序算法 Quick sort
    private int quickSelect0(int[] arr, int start, int end, int target) {
        // find the pivot
        int pivot = partition(arr, start, end);

        if (pivot < target) {
            // if pivot value is less than kth position,
            // search right side of the array.
            return quickSelect0(arr, pivot + 1, end, target);
        } else if (pivot > target) {
            // if pivot value is more than kth position,
            // search left side of the array.
            return quickSelect0(arr, start, pivot - 1, target);
        } else {
            // if pivot value is equal to the kth position,
            // return value at k.
            return arr[pivot];
        }
    }

    private int partition(int[] arr, int start, int end) {
        // 使用 end 作为 pivot，这里用随机数，调换 end
        int randomIndex = start + RANDOM.nextInt(end - start + 1);
        swap(arr, randomIndex, end);

        // 从左到右的指针，放置比 pivot 小的数
        int smaller = start;

        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                // 只要比 pivot（这里是 end）所在元素小，
                // 就依次放到 smaller 指针的位置
                swap(arr, i, smaller);
                smaller++;
            }
        }

        // 在最后一个 smaller++ 过后，smaller 左边的元素比 pivot 小，
        // 所以 pivot 就在 smaller 的位置
        swap(arr, end, smaller);
        return smaller;
    }

    private void swap(int[] a, int b, int c) {
        int tmp = a[b];
        a[b] = a[c];
        a[c] = tmp;
    }
}
