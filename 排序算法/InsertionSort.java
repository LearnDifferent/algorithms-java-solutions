public class InsertionSort {

    // 换一种遍历方式
    public void insertionSort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            // 当前位置的值
            int cur = nums[i];

            // index 初始化为 前一个位置的 index
            // 最后的 index + 1 就是 当前数值 应该被插入的位置
            int index = i - 1;

            // 查找插入的位置：比较 当前数值 和 前面位置的所有数值
            for (; index >= 0; index--) {
                // 只要前面位置的数值 大于 当前数值
                if (nums[index] > cur) {
                    // 移动数据，目的是腾出位置，用于存放当前数值
                    nums[index + 1] = nums[index];
                } else {
                    // 如果前面位置的数值 小于等于 当前数值，
                    // 说明 index + 1 是需要插入的 当前数值 的位置，所以停止查找
                    break;
                }
            }

            // 在该位置，插入当前的数值
            nums[index + 1] = cur;
        }
    }

    public void insertionSorting(int[] arr) {

        /*
        插入算法的核心思想是取未排序区间中的元素，
        在已排序区间中找到合适的插入位置将其插入，
        并保证已排序区间数据一直有序。
        重复这个过程，直到未排序区间中元素为空，算法结束
        */
        for (int i = 1; i < arr.length; i++) {

            // 抽取当前的数
            int cur = arr[i];
            // 前一个数的 index
            int preIndex = i - 1;

            // 抽取出来的数值（当前数值）和前面的数值相比较
            while (preIndex >= 0 && cur < arr[preIndex]) {
                // 只要前面的数值比当前的数值小，就将前面的 index 往后移动，留出一个位置用于插入
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            // 当 arr[preIndex] 大于等于 cur 的时候，说明可以在 preIndex + 1 的位置插入 cur 了
            arr[preIndex + 1] = cur;
        }
    }
}
