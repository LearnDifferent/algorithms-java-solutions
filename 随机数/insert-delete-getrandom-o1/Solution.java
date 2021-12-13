/*
380. Insert Delete GetRandom O(1):
O(1) 时间插入、删除和获取随机元素

解题思路（https://labuladong.gitee.io/algo/2/21/61/）：

1. 哈希集合的底层是一个大数组，把元素通过哈希函数映射到一个索引上；
如果用拉链法解决哈希冲突，那么这个索引可能连着一个链表或者红黑树。

2.使用 HashSet 的话，插入，删除，查找是 O(1)，然而 getRandom() 方法肯定不是。
根据底层实现，元素是被哈希函数「分散」到整个数组里面的，
还有拉链法等等解决哈希冲突的机制，基本做不到 O(1) 时间等概率随机获取元素

3. 如果使用 LinkedHashSet（哈希链表）也只是给 HashSet 增加了有序性，
依然无法无法在 O(1) 的时间内随机访问某一个元素的，因为底层用链表结构存储元素。

4. 如果想「等概率」且「在 O(1) 的时间」取出元素，底层需用数组实现，且数组必须是紧凑的。
这样就可以直接生成随机数作为索引，从数组中取出该随机索引对应的元素，作为随机元素。

5. 如果用数组存储元素的话，插入和删除的时间复杂度怎么做到 O(1)？
只对数组尾部进行插入和删除操作，不会涉及数据搬移，所以时间复杂度是 O(1)

6. 所以，如果想在 O(1) 的时间删除数组中的某一个元素 val，
可以先把这个元素交换到数组的尾部，然后再 pop 掉

7. 交换两个元素必须通过索引进行交换，所以需要一个哈希表用于记录每个元素值对应的索引

链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class RandomizedSet {

    // 存储元素值的列表
    private final List<Integer> valueList;
    // key 是元素值，value 是该元素值在 valueList 中对应的索引
    private final Map<Integer, Integer> elemsAndTheirIndices;
    private final Random random;

    public RandomizedSet() {
        valueList = new ArrayList<>();
        elemsAndTheirIndices = new HashMap<>();
        random = new Random();
    }

    /**
     * 如果 val 不存在集合中，则插入并返回 true，否则直接返回 false
     */
    public boolean insert(int val) {
        if (elemsAndTheirIndices.containsKey(val)) {
            // 该 val 已经存在
            return false;
        }

        // 将新的 val，存储在列表的尾部，并在 Map 中加入该索引
        int index = valueList.size();
        valueList.add(index, val);
        elemsAndTheirIndices.put(val, index);
        return true;
    }

    /**
     * 如果 val 在集合中，则删除并返回 true，否则直接返回 false
     */
    public boolean remove(int val) {
        if (!elemsAndTheirIndices.containsKey(val)) {
            // 如果不存在，直接返回 false
            return false;
        }
        // 删除最后一个元素的时间复杂度才是 O(0)，所以需要：
        // 1. 获取 val 在集合中的 index
        Integer valIndex = elemsAndTheirIndices.get(val);
        // 2. 获取最后一个元素的值
        Integer lastVal = valueList.get(valueList.size() - 1);
        // 3. 将 valIndex 位置的值，设置为最后一个元素的值
        valueList.set(valIndex, lastVal);
        // 4. 此时 val 的值已经被覆盖为最后一个元素的值了，再把最后一个位置的元素剔除
        valueList.remove(valueList.size() - 1);
        // 剩下的，就是更新索引：
        elemsAndTheirIndices.put(lastVal, valIndex);
        elemsAndTheirIndices.remove(val);
        return true;
    }

    /**
     * 从集合中随机获得一个元素
     */
    public int getRandom() {
        // 在 [0, 列表长度) 范围内随机获取一个 index，并返回该 index 元素
        int randomIndex = random.nextInt(valueList.size());
        return valueList.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
