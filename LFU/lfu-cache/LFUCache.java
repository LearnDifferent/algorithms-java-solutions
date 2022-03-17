import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/*
460. LFU Cache:
Design and implement a data structure for a Least Frequently Used (LFU) cache.

题意：
1. 只要使用 get 或 put，该 key 的 freq（频率）就 +1
2. 如果到达了 capacity，此时 put 之前，需要将 freq 最低的 key 删除。如果有多个 key，就删除最久没用的那个 key
<p>
分析：
1. 需要两个 HashMap：一个存储 key 和 key 的 value，另一个存储 key 和 key 的 freq
2. 需要一个 minFreq：为了在 O(1) 时间内，找到 freq 最小的 key，需要变量 minFreq 记录当前 freq 最小的 key
--------
3.1) 一个 freq 可以对应多个 key（多个 key 可以用列表的形式存储）
3.2) 为了快速删除最旧的 key，freq 对应的 key 是有时间顺序的
3.3) 因为 freq 会发生改变，而且一个 freq 有多个 key，所以将 key 在 freq 间移动的时候，需要有快速删除 key 的能力
--------
3. 综上，需要 HashMap<Integer, LinkedHashSet<Integer>> freqToKeys，key 为 freq，value 是 key 的列表。
使用 LinkedHashSet 是因为，它除了可以像 LinkedList 一样，满足 3.1（可以作为列表）和 3.2（有插入顺序）之外，
因为它有 Hash 的特性，所以还可以满足 3.3（快速访问和删除 key）的功能。

链接：https://leetcode-cn.com/problems/lfu-cache/
 */
class LFUCache {

    // key 和 value（只要 key 有新增或减少，keyToVal、keyToFreq 和 freqToKeys 需要同时发生改变）
    private final Map<Integer, Integer> keyToVal;

    // key 和 freq（只要 freq 发生改变 keyToFreq 和 freqToKeys 需要同时发生改变）
    private final Map<Integer, Integer> keyToFreq;
    // freq 和 key 列表（只要 freq 发生改变 keyToFreq 和 freqToKeys 需要同时发生改变）
    private final Map<Integer, LinkedHashSet<Integer>> freqToKeys;

    // 统计最小的 freq
    private int minFreq;
    // LFU 缓存的最大容量
    private final int capacity;

    public LFUCache(int capacity) {
        this.keyToVal = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKeys = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 增加 key 对应的 freq
        increaseFreq(key);
        return keyToVal.get(key);
    }

    private void increaseFreq(int key) {
        // 获取该 key 的 freq（肯定是存在的，所以这里直接 get，并转化为 int）
        int freq = keyToFreq.get(key);

        // 只要 freq 发生改变 keyToFreq 和 freqToKeys 需要同时发生改变：

        // 在 keyToFreq 中，freq + 1
        keyToFreq.put(key, freq + 1);

        // 在 freqToKeys 中，从该 freq 中的 keys 列表中，将该 key 删除，
        // 并将该 key 放入 freq + 1 的 keys 的列表中

        // 因为当前的 key 的 freq 已经更新了，所以需要把之前的 freq -> key 对照中的 key 删除后才能更新
        LinkedHashSet<Integer> curFreqKeys = freqToKeys.get(freq);
        curFreqKeys.remove(key);

        // 在将 key 放入 freq + 1 中之前，先判断 freq + 1 的 key 是否存在，如果不存在就创建
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());

        // 获取 freq + 1 的 keysSet（这里叫做 updatedFreqKeys）
        LinkedHashSet<Integer> updatedFreqKeys = freqToKeys.get(freq + 1);
        // 将 key 放入其中
        updatedFreqKeys.add(key);

        // 此时，如果原 freq（没有删除操作之前的 freq）对应的列表空了，就删除该 freq
        if (curFreqKeys.isEmpty()) {
            freqToKeys.remove(freq);
            if (freq == minFreq) {
                // 如果这个 freq 恰好是 minFreq，更新 minFreq
                // 因为 freq 是从小到大紧挨着的，所以只要 +1 即可
                minFreq++;
            }
        }
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            // 如果 capacity 定义为无容量，则无法存储数据（一般不会，不过要保险起见）
            return;
        }

        // 如果 key 已经存在，就修改该 key 对应的 value
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            // 该 key 对应的 freq 需要 +1
            increaseFreq(key);
            return;
        }

        // 如果 key 不存在，需要插入

        // 插入之前，需要判断容量是否已满
        if (capacity == keyToVal.size()) {
            // 删除 freq 最小的 key。如果有多个，删除最旧的那个
            removeMinFreqKey();
        }

        // 插入新的 key
        keyToVal.put(key, value);
        // 新的 key，freq 为 1
        keyToFreq.put(key, 1);
        // 将该 key，放入 freq 为 1 的列表中：
        // 1. 如果 freq 为 1 的列表没有创建，就创建新的 LinkedHashSet
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        // 2. 放入到该列表中：
        // 2.1) 获取 freq 为 1 的 LinkedHashSet
        LinkedHashSet<Integer> freqIsOne = freqToKeys.get(1);
        // 2.2) 将新加入的 key，放入其中
        freqIsOne.add(key);

        // 最后，更新 freq 的最小值（freq 为 1 肯定是最小的 freq）
        minFreq = 1;
    }

    private void removeMinFreqKey() {
        // 获取 freq 最小的 keysSet
        LinkedHashSet<Integer> keysSet = freqToKeys.get(minFreq);
        // key 列表中，第一个 key（最早被插入的 key，也就是最旧的 key），就是需要被删除的
        Integer oldestKey = keysSet.iterator().next();

        // 同步删除该 key：
        // 在 freqToKeys 中，对应的 freq 的 key 列表中，删除该 key
        keysSet.remove(oldestKey);
        // 在 keyToVal 中删除
        keyToVal.remove(oldestKey);
        // 在 keyToFreq 中删除
        keyToFreq.remove(oldestKey);

        if (keysSet.isEmpty()) {
            // 如果 keysSet 此时为空的话，可以在 freqToKeys 中，将该最小的 freq 删除
            freqToKeys.remove(minFreq);
        }
        // 这里不需要更新 minFreq 的值：
        // 因为要获取 minFreq，需要遍历有 freq 数据的列表，时间上不划算
        // 也因为，在 put 方法存放新的值的时候，才会调用这个 removeMinFreqKey 方法，
        // 所以，只要有 removeMinFreqKey 方法，在执行完 removeMinFreqKey 后，
        // 新 put 的值，肯定 freq 为 1，所以 minFreq 在 removeMinFreqKey 之后，肯定会被重新设置为 1
    }
}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
