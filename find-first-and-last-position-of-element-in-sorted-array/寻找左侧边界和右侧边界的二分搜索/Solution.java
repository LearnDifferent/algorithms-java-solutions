/**
 * 寻找左侧边界的二分搜索：
 * 在"排序数组"中，找到某个给定数值的 index；在有多个的情况下，返回最左边符合条件的。
 * 注意，找不到该特定值的时候，返回 -1
 * <p>
 * 寻找右侧边界的二分搜索：
 * 和寻找左侧边界类似，寻找右侧边界，就是找到给定数值的 index（如果有多个，返回最右边的）。
 * <p>
 * 参考资料：
 * https://segmentfault.com/a/1190000016825704
 * https://labuladong.gitee.io/algo/2/21/55/
 */
class Solution {

    public static void main(String[] args) {
        // 写个 main 方法测试一下
        int[] nums = new int[]{1, 6};

        System.out.println("=========打印左侧边界搜索的结果=========");
        int target = 6;
        System.out.println(test.Solution.leftBoundNormal(nums, target));
        System.out.println("======================================");
        System.out.println("=========打印右侧边界搜索的结果=========");
        System.out.println(test.Solution.rightBound(nums, target));
        System.out.println("======================================");
    }

    /**
     * 找寻左侧边界
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
        // 注意，这里按照 [left, right) 左闭右开区间的逻辑，
        // 是要写成 nums.length，也就是形成 [0, nums.length) 的区间比较符合遍历的逻辑。
        // 但是，假设 target 就是 nums.length - 1 位置的元素，因为这是**向左寻找**，
        // 那么只要在 [0, nums.length - 1) 都找不到 target，所以就不用再向左寻找了，
        // 这种情况下，target 要不就是 nums.length - 1，要不就不存在。
        // 还有一种情况，假设在 [0, nums.length - 1) 中找到了 target，
        // 比如数组为 [1,2,3,3,3]，target 为 3，那么它**向左寻找**，也和最后一个 3 没有关系
        int right = nums.length - 1;

        // while (left < right) 表示在 [left, right) 左闭右开区间内循环搜索。
        // 当 left == right 的时候，会终止循环（此时 mid 也等于 left 和 right）。
        // 终止循环之前，right 会被移动到 left 的位置（代码为 right = mid），
        // 也就是说，在 [left, right) 中的最后一次循环之前，left 就是需要被找到的值，
        // 此时，在最后一次循环退出之前，right 会被推动到 left 的位置，
        // 形成 left、right 和 mid 处于相同位置的情况
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
                // 因为是寻找左侧边界，所以要继续在 [left, mid) 中寻找
                right = mid;
            }
        }

        // 补充一下：如果这里使用的不是 right = nums.length - 1，而是 nums.length
        // 那么，right 或者说 left 是有可能停留在 nums.length 的位置上的，
        // 那么在那种情况下，如果 right(left) 在 nums.length 上，就超过了数组，需要返回 -1

        // 结束循环的时候，left、right 和 mid 都在同一个位置，所以下面的代码中写 left 和 right 都可以
        // 如果边界所在位置的不是 target 的话，也就是 nums 中不存在 target 这个值，就返回 -1
        return nums[left] == target ? left : -1;
    }

    /**
     * 寻找右侧边界
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
        // 这里是 nums.length，在 [0, nums.length) 的区间内，
        // 循环查找 0 到 nums.length - 1 的数值是否符合条件
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
            // 补充一下：假设 left 或 right 停留在了 nums.length 上，
            // 这里的 targetIndex 会 -1，所以就是 nums.length - 1，
            // 还会在数组的范围内，所以不需要考虑那种情况
            return -1;
        }
        // 最后，要判断一下是否存在 target
        return nums[targetIndex] == target ? targetIndex : -1;
    }
}