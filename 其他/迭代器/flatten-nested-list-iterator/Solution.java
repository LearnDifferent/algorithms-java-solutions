import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 341. Flatten Nested List Iterator:
 * 扁平化嵌套列表迭代器
 *
 * You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *
 * - NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * - int next() Returns the next integer in the nested list.
 * - boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 *
 * 链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
 */
// ===============下面是题目中的接口===============
// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}

// ===============下面是答案===============

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
public class NestedIterator implements Iterator<Integer> {

    // LinkedList 可以使用高效的 addFirst、getFirst 和 removeFirst 方法
    private final LinkedList<NestedInteger> list;

    // 题目中规定：1 <= nestedList.length
    public NestedIterator(List<NestedInteger> nestedList) {
        // 将 nestedList 转化为 LinkedList，并初始化属性 list
        list = new LinkedList<>(nestedList);
    }

    @Override
    public boolean hasNext() {
        // 当 list 第一个元素是列表时，进入循环（也就是 "不是 Integer 的情况"）
        while (!list.isEmpty() && !list.getFirst().isInteger()) {
            // 弹出第一个元素（removeFirst 或 pop 都是一样的）
            NestedInteger f = list.removeFirst();
            // 获取被弹出的列表
            List<NestedInteger> l = f.getList();

            // 将该列表中的元素全部取出来，放到 list 属性的头部，方便以后 next 方法的调用。
            // 这里要注意，遍历 被弹出的列表 的时候，要从后往前，
            // 只有这样，才能在放到 list 头部的时候，保持正常的列表顺序。
            for (int i = l.size() - 1; i >= 0; i--) {
                // 从后往前，获取 被弹出列表的元素
                NestedInteger e = l.get(i);
                // 假如 list 属性的头部
                list.addFirst(e);
            }
        }
        // 这个时候，再返回 hasNext 与否
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        // 注意，因为迭代器无法逆转，所以这里使用 removeFirst
        NestedInteger nxt = list.removeFirst();
        return nxt.getInteger();
    }
}

// ===============下面为另一个答案及其分析===============

/**
 * 根据题意，如果要实现一个 NestedInteger 的话，就会是这样
 */
class NestedIntegerDemo {

    private final Integer val;
    private final List<NestedInteger> list;

    public NestedIntegerDemo(Integer val) {
        this.val = val;
        this.list = null;
    }

    public NestedIntegerDemo(List<NestedInteger> list) {
        this.list = list;
        this.val = null;
    }

    // 如果其中存的是一个整数，则返回 true，否则返回 false
    public boolean isInteger() {
        return val != null;
    }

    // 如果其中存的是一个整数，则返回这个整数，否则返回 null
    public Integer getInteger() {
        return this.val;
    }

    // 如果其中存的是一个列表，则返回这个列表，否则返回 null
    public List<NestedInteger> getList() {
        return this.list;
    }
}

/**
 * 类似于 N 叉树
 */
class TreeNode {

    int val;
    TreeNode[] children;

    /**
     * N 叉树是这样遍历的
     *
     * @param root N 叉树
     */
    void traverse(TreeNode root) {
        for (TreeNode child : root.children) {
            traverse(child);
        }
    }
}

/**
 * 所以，类比 N 叉树，可以将答案可以写成这样。
 * 不过这样会在构造的时候，就将所有数据铺平，会比较慢，也不符合迭代器的"惰性获取值"的思路。
 */
class NestedIterator0 implements Iterator<Integer> {

    private final Iterator<Integer> iterator;

    public NestedIterator0(List<NestedInteger> nestedList) {
        // 存放将 nestedList 铺平的结果
        List<Integer> result = new LinkedList<>();

        for (NestedInteger node : nestedList) {
            // 以每个节点为根遍历
            traverse(node, result);
        }
        // 得到 result 列表的迭代器
        this.iterator = result.iterator();
    }

    // 遍历以 root 为根的多叉树，将叶子节点的值加入 result 列表
    private void traverse(NestedInteger root, List<Integer> result) {
        if (root.isInteger()) {
            // 到达叶子节点
            result.add(root.getInteger());
            return;
        }
        // 遍历
        for (NestedInteger child : root.getList()) {
            traverse(child, result);
        }
    }

    public Integer next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
}
