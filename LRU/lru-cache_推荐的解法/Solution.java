import java.util.HashMap;

/*
146. LRU Cache:
LRU 缓存机制

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

链接：https://leetcode-cn.com/problems/lru-cache
 */
class LRUCache {

    // key 是最近的 key
    // value 是最近的任务（key 和 value）
    private final HashMap<Integer, Node> map;
    // 最近任务的缓存
    private final DoubleLinkedList cache;
    // 缓存容量
    private final int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.cache = new DoubleLinkedList();
        this.capacity = capacity;
    }

    public void put(int key, int value) {
        // 初始化新任务
        Node node = new Node(key, value);

        if (map.containsKey(key)) {
            // 如果已经在缓存中存在，那么就先删除该缓存中的内容
            Node n = map.get(key);
            cache.delete(n);
        } else {
            // 如果在缓存中不存在，就检查一下缓存是否到了限制
            if (map.size() == capacity) {
                // 如果到了限制，就删除最后一个缓存（在 cache 和 map 中都需要删除）
                int k = cache.deleteLast();
                map.remove(k);
            }
        }

        // 现在，开始添加任务到头部（在 cache 和 map 中都需要添加）
        cache.addFirst(node);
        map.put(key, node);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        // 获取该 key 的任务
        Node node = map.get(key);
        // 任务的 value 就是需要返回的值
        int val = node.val;
        // 因为"最近获取过了"，所以更新一下：删除后再重新放入
        this.put(key, val);

        // 返回需要的值
        return val;
    }
}

class DoubleLinkedList {

    // 头节点，不存数据。也就是说，第一个有数据的节点是 head.next，表示最近使用的数据
    private final Node head;
    // 尾节点，不存数据。也就是说，最后一个有数据的节点是 tail.prev，表示使用过的最久的数据
    private final Node tail;

    public DoubleLinkedList() {
        // 初始化（key 和 value 随便一个数据即可，因为 head 和 tail 的数据会被忽略）
        head = new Node(0, 0);
        tail = new Node(0, 0);

        // 形成链表：head <-> tail
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 添加到第一个（实际上 head 不存数据，所以第一个是 head.next）
     *
     * @param node 节点：存储新的 key 和 value
     */
    public void addFirst(Node node) {

        // head <- node
        node.prev = head;
        // head <- node -> head.next
        node.next = head.next;
        // head <- node <-> head.next
        head.next.prev = node;
        // 注意：上面的顺序可以打乱，
        // 但是 head -> node 一定要放在最后，
        // 因为 head 如果提前连接了 node，也就是 head.next 就变为了 node，
        // 前面的 head.next.prev 和 "node.next = head.next" 就无法使用了
        // head <-> node <-> head.next
        head.next = node;
    }

    public int delete(Node node) {
        int key = node.key;
        // 为了能任意删除位置的节点，所以需要前驱节点的指针
        // 所以使用了双向链表，以保证时间复杂度为 O(1)
        node.prev.next = node.next;
        node.next.prev = node.prev;

        return key;
    }

    public int deleteLast() {
        if (head.next.equals(tail)) {
            // 当 head -> tail 时，即没有数据的情况（head 和 tail 不存储数据）
            return -1;
        }

        // 删除最后一个数据（tail 没有数据，所以是 tail.prev）
        return delete(tail.prev);
    }
}

class Node {

    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */