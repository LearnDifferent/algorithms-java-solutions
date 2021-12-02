public class BubbleSort {

	public void bubbleSort(int[] nums) {

        int n = nums.length;

        if (n <= 1) return;

        // 注意，这里是 i < n - 1（因为最后一个位置会在后面的 j + 1 的代码中被比较）
        for (int i = 0; i < n - 1; i++) {

            // 每一轮都创建一个 boolean，表示没有交换
            boolean notSwapped = true;

            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    // 交换了
                    notSwapped = false;
                } 
            }

            // 如果没有交换，可以提前退出循环
            if (notSwapped) break;
        }
    }
}
