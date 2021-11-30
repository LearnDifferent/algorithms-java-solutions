import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 773. Sliding Puzzle:
 * 滑动谜题
 *
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0.
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given the puzzle board board, return the least number of moves required so that the state of the board is solved.
 * If it is impossible for the state of the board to be solved, return -1.
 *
 * 链接：https://leetcode-cn.com/problems/sliding-puzzle
 */
class Solution {

    public int slidingPuzzle(int[][] board) {

        // 压缩二维数组
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        // 获取起始的格子图
        String start = sb.toString();
        // 终点需要的图
        String target = "123450";

        // 如果相等，直接返回
        if (start.equals(target)) {
            return 0;
        }

        /*
         对于 2 * 3 的二维数组，我们把它压缩到一维数组的话，index 如下：
         | 0 | 1 | 2 |
         | 3 | 4 | 5 |
         也就是说，如果需要移动的话 index 为 0 位置的元素，只能往 index  为 1 和 3 的位置移动，
         可以得到 neighbour[0] 为 {1, 3}，以此类推：
         */
        int[][] neighborMap = new int[][]{{1, 3}, {0, 4, 2}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        // 添加起点
        q.offer(start);
        visited.add(start);
        // 步数
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            // 遍历当前这一层
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                assert cur != null;
                if (cur.equals(target)) {
                    return step;
                }

                // 找到字符为 0 的 index，从这个 index 的位置扩散
                int index0 = cur.indexOf("0");
                // 遍历该 index 在 2 * 3 的二维数组中，相邻的 index，并交换相邻位置的字符
                for (int neighbor : neighborMap[index0]) {
                    // 交换相邻位置，并生成新的字符串
                    String next = swap(cur, neighbor, index0);
                    // 只要新的字符串没有出现过，就将其加入
                    if (!visited.contains(next)) {
                        q.offer(next);
                        visited.add(next);
                    }
                }
            }
            // 遍历完成当前这层后，步数 +1
            step++;
        }
        // 如果没有结果，返回 -1
        return -1;
    }

    private String swap(String str, int a, int b) {
        // 将 str 中 a 位置和 b 位置的字符互相交换
        char[] c = str.toCharArray();
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
        return new String(c);
    }
}
