import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses:
 * 括号生成
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 */
class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack("", n, n, result);
        return result;
    }

    /**
     * 左括号和右括号只能刚刚好有 n 个。
     * <p>
     * 因为每次加上新的括号都是从左往右加上去的，所以每次新加右括号的时候，都要保证右括号的总数量要小于左括号。
     * <p>
     * 如果左右括号相等，那么新加入的右括号就更多了，明显是不合法的（右括号数量更多的情况更是不允许）。
     * <p>
     * 对于左括号而言，只要数量不超标，就能随时加入新的左括号。
     * <p>
     * 需要注意的是，这里参数的 left 和 right 表示的不是数量，而是剩余的数量。
     * <p>
     * 根据这些限制条件，不断递归，就能获取最终结果。
     *
     * @param current 括号字符串
     * @param left    还剩余多少左括号可以使用
     * @param right   还剩余多少右括号可以使用
     * @param result  用于存储字符串结果
     */
    private void backtrack(String current, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(current);
            return;
        }

        if (left > 0) {
            // 只要还有剩余的左括号，就可以加上左括号（肯定是先加左括号）
            // current + "(" 这一步会产生新的局部变量，后续它会自己清除，所以不需要管它（下同）
            backtrack(current + "(", left - 1, right, result);
        }

        if (right > left) {
            // 因为是 从左往右 加上括号的，所以 左边括号的数量 大于 右边括号的数量 的时候，
            // 当前的右括号才能加上去（假设左右括号相等，那么当前新加的右括号肯定多余，所以右边数量只能少，不能多）
            // 所以，右括号剩余的数量，一定要大于左括号的剩余数量（而且，此时右括号剩余数量肯定也大于 0）
            backtrack(current + ")", left, right - 1, result);
        }
    }
}
