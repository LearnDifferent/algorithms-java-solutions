import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    private static final int INSERTION_SORT_THRESHOLD = 7;

    public void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void insertionSort(int[] arr, int start, int end) {

        // 在 [start, end] 区间内排序：从 start + 1 开始，是为了第一次和 start 位置做对比
        for (int i = start + 1; i <= end; i++) {

            // 当前位置的值
            int cur = arr[i];
            // 前一个位置的 index
            int preIndex = i - 1;

            // 查找插入的位置：比较 当前数值 和 前面位置的所有数值
            for (; preIndex >= 0; preIndex--) {
                if (cur < arr[preIndex]) {
                    // 移动数据：目的是腾出位置，用于存放 cur（当前数值）
                    // 因为插入排序会让 当前位置 的左侧成为一个有序数组，
                    // 所以腾出位置的时候，让左边的元素往右移动就行了
                    arr[preIndex + 1] = arr[preIndex];
                } else {
                    // 因为循环中有 preIndex--，所以之前 preIndex 腾出的位置被 -1 了，
                    // 所以现在是 preIndex + 1 才是之前被腾出来的 preIndex。
                    // 此时的 preIndex 位置的数值比 cur 小（或等于），
                    // 也说明 preIndex + 1 位置就是 cur 应该插入的位置。
                    break;
                }
            }

            // 在该位置，插入当前的数值
            arr[preIndex + 1] = cur;
        }
    }

    private void quickSort(int[] arr, int start, int end) {

        // 小区间使用插入排序（在小区间的时候，插入排序比较快）
        if (end - start <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, start, end);
            return;
        }

        // 如果 start == end，说明区间内只有一个元素，就没必要进行排序了
        while (start < end) {

            // 获取 pivot 的位置
            int pivot = partition(arr, start, end);

            /*
             Tail Recursion:

             To make sure at most O(log(n)) space is used,
             recur first into the partition’s smaller side,
             then use a tail call to recur into the other.

             As such, we successfully sort the array in a way that minimizes the recursive depth.

             In worst case, it is possible that array is divided in a way
             that the first part always has n-1 elements.

             For example, this may happen when last element is chose
             as pivot and array is sorted in decreasing order.

             We can optimize the code to make a recursive call
             only for the smaller part after partition.
             */
            if (pivot - start < end - pivot) {
                // If left part is smaller, then recur for left part
                // and handle right part iteratively
                quickSort(arr, start, pivot - 1);
                start = pivot + 1;
            } else {
                // Else recur for right part
                quickSort(arr, pivot + 1, end);
                end = pivot - 1;
            }
        }
    }

    private int partition(int[] arr, int start, int end) {
        // choose a random index between `start` and `end`
        // 下面的代码等同于：(int) start + (int) (Math.random() * (end - start));
        // 注：RANDOM.nextInt(int n) 表示随机生成在 [0,n) 左闭右开区间内的整数
        int randomIndex = start + RANDOM.nextInt(end - start + 1);

        // swap the end element with the element present at random index
        swap(arr, randomIndex, end);

        // 将 pivot 定义为 end 位置
        // end 位置在前面被替换为了随机元素，可以避免最坏的情况
        // 下面的代码，可以专门创建一个变量 pivot 来替代 end，不过都是一样的
//        int pivot = end;

        // 小于 pivot 的元素的位置
        int smaller = start;

        // i 指针寻找小于 pivot 的数，然后放到 smaller 指针的位置
		// 注意：到 end - 1 停止，因为 end 位置的元素就是 pivot
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                swap(arr, i, smaller);
                smaller++;
            }
        }

        // 最后一个小于 pivot 的元素被 smaller++ 了，
        // 所以现在这个 smaller 的位置刚好是 pivot 应该处于的位置
        swap(arr, end, smaller);
        return smaller;
    }

    private void swap(int[] a, int b, int c) {
        int tmp = a[b];
        a[b] = a[c];
        a[c] = tmp;
    }

}
