/**
 * 704. Binary Search 二分查找
 */
class Solution {

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        // start <= end：因为 end 是 nums.length - 1
        // 使用 start <= end 就表示在 [start, end] 的闭区间中循环查找
        // 因为每次都是 +1 的操作，所以这个 while 在 start = end + 1 的时候终止，
        // 假设 end 是 3，那么最后终止的区间就是 [4,3]，这个区间为空，所以应该被终止。
        // 如果假设是 start < end 的话，start = end 的时候就会被终止，
        // 假设 end 是 3 的话，终止的区间就是 [3,3]，此时 index 为 3 的元素还存在着，
        // 所以不应该在这个时候终止循环，所以终止的判断条件不能为 start < end
        while (start <= end) {
            // 不能直接 (start + end) / 2 是因为 start + end 有可能太大而导致溢出
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                // 因为这里是 [start, end] 的闭区间中查找，
                // 如果 mid 不符合要求，那么就应该排出 mid，在 mid 的左边或右边查找（+1 或 -1）
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                // nums[mid] == target 的时候，表示找到了
                return mid;
            }
        }
        return -1;
    }
}