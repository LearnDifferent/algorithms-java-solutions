import java.util.HashMap;
import java.util.Map;

/**
 * 567. Permutation in String
 * 字符串的排列
 *
 * 解决方法：滑动窗口
 *
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 */
class Solution {

    public boolean checkInclusion(String s1, String s2) {

        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // s1 表示需要查找的字符串，需要存入 needs 中
        char[] charsToBeFound = s1.toCharArray();
        // s2 表示从该字符串中查找
        char[] charsPool = s2.toCharArray();

        for (char c : charsToBeFound) {
            Integer count = needs.getOrDefault(c, 0);
            needs.put(c, count + 1);
        }

        // 窗口左指针
        int windowStartIndex = 0;
        // 窗口右指针，位于窗口最右边再加一的位置
        int windowEndIndex = 0;

        // 窗口符合需求的个数
        int countCharInWindowMeetsNeeds = 0;

        // 只要窗口右指针，小于候选的位置，就一直循环
        while (windowEndIndex < charsPool.length) {
            // charToWindow 表示从候选中选择的，即将被放入 window 中的字符
            char charToWindow = charsPool[windowEndIndex];
            // 记录了之后，扩大窗口的长度
            windowEndIndex++;

            // 判断刚刚被移动到窗口内的字符，是否存在于 needs 之中
            if (needs.containsKey(charToWindow)) {
                // 如果存在，更新窗口数据：
                // 获取该字符串目前在 window 中出现的个数，如果还没出现，就设置为 0
                Integer countInWindowB4 = window.getOrDefault(charToWindow, 0);
                // 此时，存入该字符，然后将出现的次数加一：这步过后，表示窗口向右移动了
                Integer countInWindowAfter = countInWindowB4 + 1;
                window.put(charToWindow, countInWindowAfter);

                // 如果此时，window 中该字符出现的次数，与需要的次数相等
                Integer countInNeeds = needs.get(charToWindow);
                if (countInWindowAfter.equals(countInNeeds)) {
                    // 说明该字符满足了条件
                    countCharInWindowMeetsNeeds++;
                }
            }

            // 此时，判断是否需要向左收缩窗口
            // 如果当前窗口的长度，大于等于需要找到的个数，说明有缩小窗口的可能性
            // 注：windowEndIndex - windowStartIndex 即为当前窗口的长度
            while (windowEndIndex - windowStartIndex >= charsToBeFound.length) {
                // 窗口中，符合 needs 的个数，已经等于 needs 的总大小了
                if (countCharInWindowMeetsNeeds == needs.size()) {
                    // 那么，说明已经找到了，返回 true
                    return true;
                }
                // 否则，缩小窗口，将窗口左边的元素移出
                // 获取左边窗口位置的字符串
                char charRemoveFromWindow = charsPool[windowStartIndex];
                // 直接向左移动，缩小窗口
                windowStartIndex++;
                // 判断刚刚被移出窗口的字符串，是否在 needs 中
                if (needs.containsKey(charRemoveFromWindow)) {
                    // 获取该字符在 window 和 needs 中的数量
                    Integer countCharInNeeds = needs.get(charRemoveFromWindow);
                    Integer countCharInWindow = window.get(charRemoveFromWindow);
                    // 如果数量相等，说明被记录在 countCharInWindowMeetsNeeds 中
                    if (countCharInNeeds.equals(countCharInWindow)) {
                        // 所以需要减少一
                        countCharInWindowMeetsNeeds--;
                    }
                    // 此时，再从窗口中，移出该字符（减少该字符的数量）
                    window.put(charRemoveFromWindow, countCharInWindow - 1);
                }
            }
        }

        // 如果走完上面的流程，还是无法找到的话，说明不存在，返回 false
        return false;
    }
}