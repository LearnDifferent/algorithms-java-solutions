import java.util.PriorityQueue;

/*
295. Find Median from Data Stream:
数据流的中位数

The median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value
and the median is the mean of the two middle values.

- For example, for arr = [2,3,4], the median is 3.
- For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

Implement the MedianFinder class:

- MedianFinder() initializes the MedianFinder object.
- void addNum(int num) adds the integer num from the data stream to the data structure.
- double findMedian() returns the median of all elements so far.

链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 */
class MedianFinder {

    /**
     * 使用 Max Heap 和 Min Heap 来存放数据。
     * --------------------------------------------
     * Min Heap（小顶堆）：其顶部的元素是 Heap 中的最小值
     * Max Heap（大顶堆）：其顶部元素为最大值
     * 注：Java 默认的 PriorityQueue 是 Min Heap，
     * --------------------------------------------
     * 新加入的数据，均匀存到 Max Heap 和 Min Heap 中，
     * 使得两个 Heap 存储的数量差不超过 1，
     * 就能保持其中一个 Heap 的顶部元素为中位数
     * （或者两个 Heap 顶部元素的平均数为中位数）。
     * --------------------------------------------
     * 除此之外，难点在于怎么维持 Max Heap 和 Min Heap，
     * 让它们的顶部始终为中位数。
     * 如果将所有数据当作一个从小到大的有序数组 [a][b][c][d]，
     * 中间的 [b] 和 [c] 就是中位数，将它们当作 Heap 的顶部，
     * [b]->[a] 可以组成一个从大到小的数组，[b] 为 Heap 最大值，
     * [c]->[d] 组成从小到大的数组，[c] 为最小值，
     * [b]->[a] 可以看作是 Max Heap，[c] -> [d] 就是 Min Heap。
     * --------------------------------------------
     * 如果要确保 [b] 和 [c] 始终处于中位数位置，
     * 那么 [b]->[a]（Max Heap）整体就必须小于 [c]->[d]（Min Heap），
     * 所以如果想在 Max Heap 中加入新的元素，就要先将新的元素放入 Min Heap 中，
     * 然后将 Min Heap 中最小的元素，也就是顶部元素，放入到 Max Heap 中，
     * 这样就能保证 Max Heap 中所有元素都小于 Min Heap 中的所有元素。
     * 反过来说，如果现在 Min Heap 中放入新元素，也是同样的道理。
     */
    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {

        if (maxHeap.size() > minHeap.size()) {
            // 当 Max Heap 元素多的时候，就要加入新的元素到 Min Heap 中，
            // 在加入新到元素到 Min Heap 之前，需要将 num 放到 Max Heap 中，
            // 从 Max Heap 中获取其加入 num 后的最大值（也就是顶部元素），
            // 再将这个最大值放入到 Min Heap 中，保持 Min Heap 所有元素大于 Max Heap
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        // 如果存储的数据量不相等，那么，数据多的那个 Heap 的顶部就是中位数
        if (minHeap.size() < maxHeap.size()) {
            return maxHeap.peek();
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            // 如果元素一样多，两个堆堆顶元素的平均数是中位数
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
