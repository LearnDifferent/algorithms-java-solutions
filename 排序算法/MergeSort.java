public class MergeSort {

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (right <= left) {
            // 只有一个数的时候，没必要排序，直接 return（而且 left 必须小于 right）
            return;
        }

        int mid = left + (right - left) / 2;

        // 向左递归进行分解：left -> mid
        mergeSort(arr, left, mid);
        // 向右递归进行分解：(mid + 1) -> right
        mergeSort(arr, mid + 1, right);

        // 合并 left -> mid 和 (mid + 1) -> right
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // arr 的 index 从 left -> mid 是有序的，(mid + 1) -> right 也是有序的
        // 相当于合并 left -> mid 数组和 (mid + 1) -> right 数组

        // 申请额外内存空间
        int[] temp = new int[right - left + 1];

        // left -> mid 数组的起始位置
        int start1 = left;
        // (mid + 1) -> right 数组的起始位置
        int start2 = mid + 1;

        // temp 数组需要填入新值的位置
        int tempIndex = 0;

        // 只要 index（start1 和 start2）在 left -> mid 和 (mid + 1) -> right 之间，
        // 就一直遍历，直到其中一个 index 到达了该数组的末尾，就停下（该 index 所在的数组遍历完成）
        while (start1 <= mid && start2 <= right) {
            // temp 按照从左到右的顺序，放入值
            // 放入的值为 两个数组中比较小的那个
            if (arr[start1] < arr[start2]) {
                temp[tempIndex] = arr[start1];
                // index 要后移
                tempIndex++;
                start1++;
            } else {
                temp[tempIndex] = arr[start2];
                tempIndex++;
                start2++;
            }
        }

        // 肯定有一个数组是走完的，而另一个（大概率）没有走完
        // 如果是 start1 所在的 left -> mid 数组没有走完，
        // 就将数组剩余的内容放入到 temp 中（因为是有序数组，所以直接放入）
        while (start1 <= mid) {
            temp[tempIndex] = arr[start1];
            start1++;
            tempIndex++;
        }

        // 如果是 start2 所在的 (mid + 1) -> right 数组没有走完，也是一样
        while (start2 <= right) {
            temp[tempIndex] = arr[start2];
            start2++;
            tempIndex++;
        }

        // 最后，将 temp 数组的内容，放入到 arr[left] 到 arr[temp.length - 1] 中
        for (int i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }
}
