import java.util.LinkedHashMap;

/**
 * 146. LRU Cache:
 * LRU 缓存机制
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * 链接：https://leetcode-cn.com/problems/lru-cache
 */
class LRUCache {

    private final int capacity;
    private final LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将这个 key 设置为最近使用的 key
        makeRecently(key);
        return cache.get(key);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // 修改该 key 的 value
            cache.put(key, value);
            // 将 key 变为最近使用
            makeRecently(key);
            return;
        }

        // 如果添加了新的 key 之后，会超过 capacity，那么移除最久的那个没有使用的 key
        if (cache.size() >= this.capacity) {
            // 链表头部就是最久未使用的 key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的 key 添加到链表尾部
        cache.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
