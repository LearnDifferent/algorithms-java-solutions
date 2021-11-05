/**
 * 寻找左侧边界的二分搜索：
 * 在"排序数组"中，找到某个给定数值的 index；在有多个的情况下，返回最左边符合条件的。
 * 注意，找不到该特定值的时候，返回 -1
 * <p>
 * 寻找右侧边界的二分搜索：
 * 和寻找左侧边界类似，寻找右侧边界，就是找到给定数值的 index（如果有多个，返回最右边的）。
 * <p>
 * 资料：https://labuladong.gitee.io/algo/2/21/55/
 */
class Solution {

    public static void main(String[] args) {
        // 写个 main 方法测试一下
        int[] nums = new int[]{1, 2, 2, 3, 5, 5, 6};

        System.out.println("=========打印左侧边界搜索的结果=========");
        int target = 5;
        System.out.println(Solution.leftBoundNormal(nums, target));
        System.out.println(Solution.leftBoundAnother(nums, target));
        System.out.println("=======两个搜索的结果相等时表示正确=======");
        System.out.println("======================================");
        System.out.println("=========打印右侧边界搜索的结果=========");
        System.out.println(Solution.rightBound(nums, target));
        System.out.println("======================================");
    }

    /**
     * 找寻左侧边界，就是找到 {@code target} 的 index，如果有多个，就返回最左边的那个。
     * <p>
     * 可以转化为，在 {@code nums} 数组（排序数组）中，找到小于 {@code target} 数值的数总共有多少个。
     * 比如说，在 [1,2,2,3] 中，找到 3 的值的 index，就相当于统计比 3 小的有多少个，
     * 这里的答案是有 3 个，分别为 1,2 和 2，而 3 的 index 就是 3，所以可以这样转化。
     * 这样转化，可以方便理解代码中，将 right 赋值为 nums.length 的原因。
     *
     * @param nums   an array of integers nums sorted in non-decreasing order
     * @param target target value
     * @return {@code target} 的 index（有多个的情况下，返回最左边的那个 index），
     * 如果数组中没有该值，就返回 -1
     */
    public static int leftBoundNormal(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        // 这里使用 nums.length：
        // 假设 target 是 nums 中最后一个元素，比如在 [1,2,3] 中，
        // target 为 3 的话，返回的值就是 index 为 3，就是整个 nums 的长度 3，
        // 也就是 nums.length。这样寻找边界的话，就会方便一点
        int right = nums.length;

        // while (left < right) 表示在 [left, right) 左闭右开区间内循环搜索。
        // 所以，当 left == right 的时候，会终止循环
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 因为是 [left, right) 区间，所以检查过 mid 之后，就要去掉 mid，
            // 也就是隔离 mid，所以会分为 [left, mid) 和 [mid + 1, right)
            if (nums[mid] < target) {
                // 比 target 小的时候，就要到右边去寻找：[mid + 1, right)
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 大于 target 的时候，就去左边寻找：[left, mid)
                right = mid;
            } else {
                // if (nums[mid] == target) 的情况，说明找到了其中一个符合 target 的数值
                // 因为是寻找左侧边界，所以要继续压缩左边的区域，也就是在 [left, mid) 中继续寻找
                // 通过不断向左收缩，直到 left == right，
                // 也就是从 left 向右搜索到的第一个符合 target 的值，
                // 与 right 向左搜索到的最后一个符合 target 的值重合的地方，
                // （因为是左闭右开）就是需要的左侧长度
                right = mid;
            }
        }

        // 因为是在 left == right 的时候终止循环，所以下面的代码写 left 或 right 都是一样的
        if (right == nums.length) {
            // 如果在查找左侧边界的时候，指针停留在 nums.length
            // 说明没有找到，直接返回 -1 即可（停留在 0 的时候，说明 index 为 0 的元素可能符合条件）
            return -1;
        }
        // 如果边界所在位置的不是 target 的话，也就是 nums 中不存在 target 这个值，就返回 -1
        return nums[right] == target ? right : -1;
    }

    // 寻找左侧边界，另外一种不常用的方法
    public static int leftBoundAnother(int[] nums, int target) {
        int left = 0;
        // 这里以 nums 数组的最后一个 index 为 right
        int right = nums.length - 1;
        // 因为 right 是 nums.length - 1，所以需要在 [left, right] 的闭合区间中循环
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                // 在左侧的 [left, mid - 1] 中寻找（隔离了 mid）
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 在左侧边界继续寻找
                right = mid - 1;
            }
        }

        // 下面的代码中，只能写 left，因为上面循环中的退出条件是 left == right + 1
        if (left >= nums.length || nums[left] != target) {
            // 当 target 比 nums 中所有元素都大时，就会发生越界
            return -1;
        }
        return left;
    }

    /**
     * 这里演示的是 right = nums.length 的方案，也就是在 [left, right) 中查找。
     *
     * @param nums   排序数组
     * @param target 特定的数值
     * @return 特定的数值在数组中的 index；有多个的情况下，返回最右边的 index；
     * 如果数组中没有该特定值，就返回 -1
     */
    public static int rightBound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length;

        // 在 [left, right) 区间内循环
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                // 大于 target，就去左侧寻找：[left, mid)
                right = mid;
            } else if (nums[mid] < target) {
                // 在右侧寻找 [mid + 1, right)
                left = mid + 1;
            } else {
                // if (nums[mid] == target) 时，从右缩小右侧范围。
                // 我们要的其实最后的 mid，而这个 mid 被 +1 了，
                // 相当于 mid = left - 1，所以在下面返回的时候，
                // 返回的应该是 left - 1
                // （因为 left 和 right 在终止的时候相等，所以返回 right - 1 也行）
                left = mid + 1;
            }
        }

        // 下面的代码，left 或 right 都是一样的
        int targetIndex = left - 1;
        if (targetIndex < 0) {
            // 如果最后的结果不在数组的 index 范围内，说明不存在
            return -1;
        }
        // 最后，要判断一下是否存在 target
        return nums[targetIndex] == target ? targetIndex : -1;
    }
}