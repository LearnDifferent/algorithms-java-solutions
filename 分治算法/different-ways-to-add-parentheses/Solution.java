import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
241. Different Ways to Add Parentheses:
为运算表达式设计优先级

运算符只包含：+ - *

Given a string expression of numbers and operators,
return all possible results from computing all the different possible ways
to group numbers and operators. You may return the answer in any order.

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10

解决方案：Divide and conquer / 分治算法

链接：https://leetcode-cn.com/problems/different-ways-to-add-parentheses
 */
class Solution {

    // 备忘录：如果 expression 相同的情况下，可以直接返回结果
    // 加不加这个备忘录，总体时间复杂度都是一样的，只是稍微增加一点效率
    Map<String, List<Integer>> memo = new HashMap<>();

    /**
     * 方法定义：计算所有可能的运算结果
     *
     * @param expression 运算表达式，比如 "1 * 2 + 3"
     * @return 在表达式的不同位置添加括号后，所有的结果
     */
    public List<Integer> diffWaysToCompute(String expression) {

        // 查看备忘录（这个只是优化技巧，并非分治算法，而且加不加备忘录，差别不是非常大）
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        // 存储结果。在递归的过程中，存储该过程的结果。
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            // 当前位置字符
            char cur = expression.charAt(i);
            // 查找表达式中的运算符
            if (cur == '-' || cur == '*' || cur == '+') {
                // divide：以运算符为中心，分割成左右两部分的字符串，分别递归计算运算的结果
                // 分为 [0, i) 和 [i + 1, 最后一个] 两部分
                String leftExp = expression.substring(0, i);
                String rightExp = expression.substring(i + 1);
                // 递归计算左右部分，所有可能的结果（这个方法的定义）
                List<Integer> leftResults = diffWaysToCompute(leftExp);
                List<Integer> rightResults = diffWaysToCompute(rightExp);

                // conquer：通过子问题的结果，合成原问题的结果
                for (int left : leftResults) {
                    for (int right : rightResults) {
                        if (cur == '+') {
                            result.add(left + right);
                        } else if (cur == '-') {
                            result.add(left - right);
                        } else {
                            // cur == '*'
                            result.add(left * right);
                        }
                    }
                }
            }
        }

        // base case
        if (result.isEmpty()) {
            // 如果 result 为空，说明没有运算符，只是单纯的数字
            int num = Integer.parseInt(expression);
            // 将该数字放入结果集中
            result.add(num);
        }

        // 将结果添加进备忘录（如果不想使用备忘录，也是可以的，备忘录只是优化）
        memo.put(expression, result);
        return result;
    }
}
