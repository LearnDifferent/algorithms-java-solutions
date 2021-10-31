import java.util.Stack;

/**
 * 316. Remove Duplicate Letters:
 * 去除重复字母
 * 解决方案：Monotonic Stack 单调栈
 * 注意点：smallest in lexicographical order among all possible results（字典序最小）的意思，
 * 不是按照 a,b,c,d... 这样排序，而是说，abc 比 acc 更小。
 * 所以，假设是 a,b,a,c 的话，就要把第二个 a 去掉，变成 a,b,c。如果是 b,a,c 的话，就保存不变。
 * 如果是 b,a,b,c 的话，就去除第一个 b，变为 a,b,c。
 * 如果是 b,c,a,d,c 的话，就去掉第一个 c，变为 b,a,d,c，因为第一个 c 后面是 a，a 比 c 小，
 * 这样出来的字典序就比较小；如果去掉的是最后一个 c，变为 b,c,a,d 的话，就比正确的结果 b,a,d,c 大了。
 *
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 *
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 */
class Solution {

    public String removeDuplicateLetters(String s) {

        // Monotonic Stack（单调栈）
        Stack<Character> stack = new Stack<>();

        // 每个字符都可以转化为对应的数字，比如，获取字节 'a' 对应的数字：
        // char charA = 'a';int intA = charA; 然后打印 intA 就可以得到 'a' 的数字。
        // 全部 ASCII 字符需要 0 到 255，也就是总共 256 个字节。
        // 注：题目中是只有小写字母，如果只想要 26 个字节来存储的话，
        // 可以使用基本类型为 char 的小写字母减去 'a' 来计算，
        // 比如说，在 int content = new int[26]; 中，计算字母 c 和 z 的 index，
        // 可以使用 int cIndex = 'c' - 'a'; int zIndex = 'z' - 'a';
        // 这里为了方便，字节申请了 256 个字节

        char[] letters = s.toCharArray();

        // countLetter 用于计算该位置的字符 出现的次数
        // 比如，char a 转化为数字是 97，那么 countLetter[97] 中记录就要加一
        int[] countLetter = new int[256];
        for (char letter : letters) {
            // char 会被转化为 int
            countLetter[letter]++;
        }

        // 该位置的 letter，在遍历的过程中，是否已经在 stack 中
        boolean[] letterInStack = new boolean[256];

        // 开始遍历
        for (char letter : letters) {
            // 遍历的时候，等于从 countLetter 中取出来操作
            // 所以，将 (char) letter 转化为 int 后，该位置的数量减少一
            countLetter[letter]--;

            // 该 letter 是否已经在 stack 中
            boolean letterNoInStack = !letterInStack[letter];
            // 如果该 letter 不在 stack 中，再进行下面的操作；否则略过此次循环
            if (letterNoInStack) {
                // 在 stack 不为空，且……
                // 当"当前"顶部的字母，在字典中大于当前循环到的这个字母，
                // 比如字母 b > a 的时候，再进行循环操作
                while (!stack.isEmpty() && stack.peek() > letter) {
                    if (countLetter[stack.peek()] == 0) {
                        // 当前的 stack 刚刚判断过不为空，所以可以 peek 顶部元素
                        // 如果大于当前字母的这个"栈顶的字母"，在后面已经不会再出现了，就退出循环
                        break;
                    }
                    // 弹出顶部的字母（顶部的字母比当前循环到的字母大）
                    Character poppedLetter = stack.pop();
                    // 记录该字母已被弹出
                    letterInStack[poppedLetter] = false;
                }

                // 现在，当前循环中的 letter 就是字典中最前面的字母了
                // 将其放入 stack，并标记为已经放入
                stack.push(letter);
                letterInStack[letter] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        // 只要 stack 不为空
        while (!stack.isEmpty()) {
            // 弹出最上面的字母，然后拼接为字符串
            Character poppedLetter = stack.pop();
            sb.append(poppedLetter);
        }

        return sb.reverse().toString();
    }
}