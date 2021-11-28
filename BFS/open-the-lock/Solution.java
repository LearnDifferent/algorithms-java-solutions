import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 752. Open the Lock:
 * 打开转盘锁
 *
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6',
 * '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock
 * will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of
 * turns required to open the lock, or -1 if it is impossible.
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 */
class Solution {

    // ==============两种解法都需要下面的这两个方法==============
    private String passwordPlusOne(String password, int index) {
        char[] pwd = password.toCharArray();
        if (pwd[index] == '9') {
            // 如果该位置为 9，就转化为 0
            pwd[index] = '0';
        } else {
            // 如果不为 9，就直接 +1
            pwd[index] += 1;
        }
        return new String(pwd);
    }

    private String passwordMinusOne(String password, int index) {
        char[] pwd = password.toCharArray();
        if (pwd[index] == '0') {
            // 如果该位置为 0，就转化为 9
            pwd[index] = '9';
        } else {
            // 如果不为 0，就直接 -1
            pwd[index] -= 1;
        }
        return new String(pwd);
    }
    //========================================================

    // 在知道终点的情况下，使用双向 BFS 会更快：
    // 从起点和终点同时开始扩散，当两边有交集的时候停止
    public int openLock(String[] deadends, String target) {
        // 使用 Set，存放从起点开始扩散的节点 和 从终点开始扩散的节点（不用 Queue）
        Set<String> start = new HashSet<>();
        Set<String> end = new HashSet<>();

        // 用于记录走过的节点
        Set<String> visited = new HashSet<>();
        // 将 deadends 中的内容放入 visited，来避免走这些节点
        for (String deadend : deadends) {
            visited.add(deadend);
        }

        // 如果 deadends 中有 0000，那刚开始开锁就会结束
        if (visited.contains("0000")) {
            return -1;
        }

        // 添加起点和终点（这里不需要加入 visited）
        start.add("0000");
        end.add(target);

        // 记录步数
        int step = 0;

        while (!start.isEmpty() && !end.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，所以用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();

            // 从起点的节点开始扩散，也就是遍历这一层
            for (String cur : start) {
                // 判断 从起点 开始扩散的 当前节点 与 从终点开始扩散的 节点是否 相遇
                if (end.contains(cur)) {
                    // 如果相遇了，代表到达了终点
                    return step;
                }
                // 注意：在这里将当前节点设为"已访问"
                visited.add(cur);

                // 将 从起点开始扩散的 当前节点的 相邻节点 加入到集合中
                for (int i = 0; i < 4; i++) {
                    String plus = passwordPlusOne(cur, i);
                    if (!visited.contains(plus)) {
                        // 注意，这里使用 temp 来存储扩散的结果，而且不用加入 visited
                        temp.add(plus);
                    }
                    String minus = passwordMinusOne(cur, i);
                    if (!visited.contains(minus)) {
                        temp.add(minus);
                    }
                }
            }
            // 遍历完这一层后，增加步数
            step++;
            // temp 一开始是 start（准确来说，是从 start 扩散出去的下一层）
            // 这里交换 start 和 end，下一轮 while 就是扩散 end
            start = end;
            end = temp;
        }
        // 如果找不到，就返回 -1
        return -1;
    }

    // 传统的 BFS：
    public int openLock0(String[] deadends, String target) {

        // 核心数据结构
        Queue<String> queue = new LinkedList<>();

        // visited 用于记录访问过的位置，初始化的时候，把"死亡密码"直接加进去
        // 这里把"死亡密码"当作访问过的位置，就能阻止触发这些密码
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));

        // 如果 deadends 中有 0000，那刚开始开锁就会结束
        if (visited.contains("0000")) {
            return -1;
        }

        // 将起点加入队列：The lock initially starts at '0000'
        queue.offer("0000");
        visited.add("0000");

        // 记录步数
        int step = 0;

        while (!queue.isEmpty()) {
            // 获取当前这一层的大小
            int currentLevel = queue.size();
            // 遍历当前这一层所有节点，并向四周扩散
            for (int i = 0; i < currentLevel; i++) {
                // 弹出一个节点
                String cur = queue.poll();
                assert cur != null;

                // 如果已经到达终点，直接返回结果：也就是判断是否命中密码
                if (cur.equals(target)) {
                    // 如果找到密码，返回需要的步数
                    return step;
                }
                // 将相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    // 将 cur 其中一个位置 +1 后，再存入队列，并设为已访问
                    String plusOne = passwordPlusOne(cur, j);
                    if (!visited.contains(plusOne)) {
                        queue.offer(plusOne);
                        visited.add(plusOne);
                    }
                    // 将 cur 其中一个位置 -1 后，再存入队列，并设为已访问
                    String minusOne = passwordMinusOne(cur, j);
                    if (!visited.contains(minusOne)) {
                        queue.offer(minusOne);
                        visited.add(minusOne);
                    }
                }
            }

            // 遍历完一层后，将步数 +1
            step++;
        }
        // 如果找不到密码，返回 -1
        return -1;
    }
}
