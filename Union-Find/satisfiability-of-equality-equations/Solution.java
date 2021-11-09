/**
 * 990. Satisfiability of Equality Equations:
 * 等式方程的可满足性
 *
 * You are given an array of strings equations that represent relationships between variables where each string
 * equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".（这里的 xi 和 yi 中的 i 是下标）
 * Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
 *
 * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations,
 * or false otherwise.
 *
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
 * 并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 *
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
 *
 * 题目含义：数组 equations 存放着字符串表示的算式，每个算式 equations[i] 长度都是 4，
 * 而且只有这两种情况：a==b 或者 a!=b，其中 a,b 可以是任意小写字母。
 * 如果 equations 中所有算式都不会互相冲突，返回 true，否则返回 false。
 * 比如，输入 ["a==b","b!=c","c==a"]，算法返回 false，因为这三个算式不可能同时正确。
 * 比如，输入 ["c==c","b==d","x!=z"]，算法返回 true，因为这三个算式并不会造成逻辑冲突。
 *
 * 解决方案：因为 == 关系是一种等价关系，所以可以使用 Union-Find 算法。
 * 首先，将所有 == 的算式互相连通，然后检查 != 算式是否破坏了之前连通的节点。
 * 只要不会破坏之前连通的节点，就返回 true。
 *
 * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 */
class Solution {

    public boolean equationsPossible(String[] equations) {
        // 26 个小写字母
        UF uf = new UF(26);
        // 连通所有 == 算式的字母
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                // x==y 算式，在 index 为 0 的位置是第一个字母
                // index 为 1 和 2 的位置是 = 符号
                // index 为 3 的位置是第二个字母
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                // 连接小写字母的序号，从 0 到 26
                uf.union(getRelativeToA(x), getRelativeToA(y));
            }
        }

        // 检查 != 算式的两侧的字母，是否已经连通了。如果已经连通了，说明不等式不成立，返回 false
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                if (uf.connected(getRelativeToA(x), getRelativeToA(y))) {
                    // 如果已经连通，说明当前不等式是错误的
                    return false;
                }
            }
        }
        // 经过检查后，如果没有问题就返回 true
        return true;
    }

    private int getRelativeToA(char letter) {
        // 假设 letter 是字母 a，则返回 0；如果是 b，则返回 1……
        return letter - 'a';
    }
}

class UF {

    /**
     * 连通分量的个数：
     * 初始化的时候，count 就是节点数
     */
    private int count;
    /**
     * 该节点对应的父节点。
     * 比如 parent[1] = 2 表示，节点 1 的父节点是节点 2。
     * 如果没有指向其他节点，它就是根节点，需要指向它自己。
     */
    private int[] parent;

    /**
     * 统计树的"重量"，也就是某个根节点下面连接了多少子节点
     */
    private int[] size;

    /**
     * 构造器
     *
     * @param totalNodes 节点的总数
     */
    public UF(int totalNodes) {
        this.count = totalNodes;
        parent = new int[totalNodes];
        size = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            size[i] = 1;
            parent[i] = i;
        }
    }

    /**
     * 查找节点 i 的根节点
     *
     * @param i 节点 i
     * @return 节点 i 的根节点
     */
    private int find(int i) {
        // 查找之前，先压缩，让父节点变为根节点
        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }
        // 此时父节点就是根节点
        return parent[i];
    }

    /**
     * 连通 a 节点和 b 节点
     *
     * @param a 节点
     * @param b 节点
     */
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            // 如果根节点相等，说明它们本来就是相互连接的
            return;
        }

        if (size[rootA] > size[rootB]) {
            // 让小的树，接到大的树下面，可以更加平衡
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        } else {
            parent[rootA] = rootB;
            size[rootB] += rootA;
        }

        // 当前的连通分量就减少一
        count--;
    }

    public boolean connected(int a, int b) {
        // 如果两个节点的根节点相等，说明它们连通
        return find(a) == find(b);
    }

    public int count() {
        // 返回连通分量个数：
        // 比如有 [0,1,2] 节点，这 3 个都是独立节点，就是 3
        // 如果其中 0 和 1 相连，就只有 [0,1] 和 [2]，也就是 2
        return count;
    }
}
