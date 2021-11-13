/**
 * 72. Edit Distance:
 * 编辑距离
 *
 * Given two strings word1 and word2,
 * return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 * - Insert a character
 * - Delete a character
 * - Replace a character
 *
 * 链接：https://leetcode-cn.com/problems/edit-distance
 */
class Solution {

    /*
        |" "| a | p | p | l | e |
     " "| 0 | 1 | 2 | 3 | 4 | 5 |
      a | 1 |   |   |   |   |   |
      p | 2 |   |   |   |   |   |
      e | 3 |   |   |   |   |   |
      =================================
      行表示：word1 的长度
      列表示：word2 的长度

      如上例子：
      dp[0][0] 相当于空字符串。
      dp[1][0] 到 dp[5][0] 表示 apple 从 a 到 e 的默认操作数（其实就是长度）；
      dp[0][1] 到 dp[0][3] 表示 ape 从 a 到 e 的默认操作数；
      也就是说，dp[0][4] 从什么都没有，到出现 appl，默认需要的操作数为 4（增加 4 个字符）。

      那么，dp[1][1] 表示的是从 ape 中的 a，转变为 apple 中的 a，所需要的最少的操作数，
      （其实理解成 apple 的 a 到 ape 的 a 转变的操作数也是一样的，反正最后求的是两个单词最后一个字符的最小操作数）
      这里 a 和 a 是相等的，所以操作数为 0，所以使用前一个操作的操作数 + 0（前一个操作数是空字符串的 0）。

      dp[1][2] 就表示从 ape 的 a 到 apple 的 ap 需要的操作数，这里就需要从 a 增加一个字母 p，
      这里就需要从 dp[1][1] 增加 1 个操作数。同理，dp[1][3] 就是 dp[1][2] 增加 1 个操作数，
      dp[1][4] 就是 dp[1][3] 增加一个操作数……

      同理，对于列来说，dp[2][1] 就是 dp[1][1] 加 1 个操作数，dp[3][1] 就是 dp[2][1] 加 1 个操作数。

      这里要明白的是，"插入"和"删除"操作是相对的，比如 a 到 ap 是"插入"，ap 到 a 就是"删除"，
      只要是"插入"或"删除"的操作，新的操作数 dp[i][j] 要不就是 dp[i - 1][j] + 1，要不就是 dp[i][j - 1] + 1。

      因为动态规划需要递归穷举所有可能来找到最优解，所以获取 dp[i][j] 的最优解，肯定需要穷举"插入"和"删除"，
      以及"替换"的最优解，这里已经知道"插入"和"删除"的状态转移方式了，现在就差"替换"了。

      同样是上面的例子，dp[3][3] 表示从（ape 的）ape，到（apple 的）app 需要的最少操作数。
      这里是使用将 e "替换"为 p 的操作。因为"替换"不像"删除"和"插入"那样，会改变字符串的长度，
      所以计算操作数的时候，因为 e 是 ape 和 apple 的第 3 个字母，所以用第 2 个字母时的最少操作数 + 1 即可。
      也就是 dp[i][j] 在"替换"的时候，就是 dp[i - 1][j - 1] + 1。

      最终的 dp[i][j] 就是在这些操作中的最优解。
     */
    public int minDistance(String word1, String word2) {

        char[] origin = word1.toCharArray();
        char[] target = word2.toCharArray();

        int originLength = origin.length;
        int targetLength = target.length;

        // index 为 0 表示第 1 个字符，index 为 originLength 表示 origin 的最后一个字符……
        int[][] dp = new int[originLength + 1][targetLength + 1];

        // base case：
        // 第一行和第一列，最短操作是前一个的最短操作 + 1（因为第一行只有"插入"和"删减"，不可能替换或跳过）
        // dp[0][0] 表示空字符串，所以操作数为 0（这里写成 i = 0 开始遍历也可以，不过没什么必要）
        for (int i = 1; i <= originLength; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= targetLength; j++) {
            dp[0][j] = j;
        }

        // 自底向上求解：
        // 遍历的过程中，填写 dp table，最后获取 dp table 的最后一个值
        for (int i = 1; i <= originLength; i++) {
            for (int j = 1; j <= targetLength; j++) {
                if (origin[i - 1] == target[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int a = dp[i - 1][j] + 1;
                    int b = dp[i][j - 1] + 1;
                    int c = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(a, Math.min(b, c));
                }
            }
        }
        // dp table 右下角的最后一个值，就是需要的最小操作数
        return dp[originLength][targetLength];
    }
}
