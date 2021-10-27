import java.util.HashMap;
import java.util.Map;

/**
 * 3. Longest Substring Without Repeating Characters:
 * 无重复字符的最长子串
 *
 * 解决方案：滑动窗口，获取最大的窗口
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
class Solution {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int windowStart = 0;
        int windowEnd = 0;
        int result = 0;

        while (windowEnd < s.length()) {
            char charToAdd = s.charAt(windowEnd);
            windowEnd++;
            // 更新窗口数据：
            // 该字符在窗口中的数量
            Integer charCount = window.getOrDefault(charToAdd, 0);
            // 更新数量
            window.put(charToAdd, charCount + 1);

            // 如果超过了 1，说明该字符已经存在过了，需要收缩左侧窗口
            while (window.get(charToAdd) > 1) {
                char charToRemove = s.charAt(windowStart);
                windowStart++;
                // 更新窗口数据
                Integer count = window.get(charToRemove);
                window.put(charToRemove, count - 1);
            }
            // 如果小于等于 1，说明该字符符合题意，更新结果
            result = Math.max(result, windowEnd - windowStart);
        }

        // 当窗口移动到尾部的时候，跳出循环并返回结果
        return result;
    }
}