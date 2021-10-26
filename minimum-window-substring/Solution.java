/**
 * 76. Minimum Window Substring:
 * 最小覆盖子串
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 */
class Solution {

    public String minWindow(String s, String t) {

        // need 用于存储 t（也就是需要查找的字符串）中的所有的字符及其出现的次数
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            // 获取该字符在 need 中，已经出现过的次数；如果还没出现，就设置为 0
            int times = need.getOrDefault(c, 0);
            // 放入的时候，将次数加一
            need.put(c, times + 1);
        }

        // window 是滑动窗口，用于选取
        Map<Character, Integer> window = new HashMap<>();

        // 初始化窗口的左右指针
        // 窗口大小：right - left
        // left 表示左指针，是窗口的起始点
        int left = 0;
        // right 是右指针，位于超过窗口一个位置
        // 可以理解为窗口的 length
        int right = 0;

        // valid 表示 window 窗口中满足 need 条件的字符个数
        int valid = 0;

        // 最小覆盖子串的起始 index
        int minWindowStartIndex = 0;
        // 最小覆盖子串的最小长度
        int minWindowLen = Integer.MAX_VALUE;

        // s 是被搜索的字符串，stringToSearchLength 表示它的长度
        int stringToSearchLength = s.length();
        // 当窗口符合条件，也就是小于被搜索的字符串的长度的时候
        while (right < stringToSearchLength) {
            // 移动右指针，寻找可行的方案
            // charToWindow 是将要移入窗口的字符
            char charToWindow = s.charAt(right);
            // 移动窗口
            right++;

            // 如果在 need 中，找到该字符
            if (need.containsKey(charToWindow)) {
                // 加入到 window 中：
                // 获取出现的次数，没有的话设置为 0
                Integer times = window.getOrDefault(charToWindow, 0);
                // 让出现的次数加 1
                window.put(charToWindow, times + 1);

                // 此时，查看一下 window 中该字符出现的次数
                // 和 need 中该字符出现的次数是否相等
                Integer countInWindow = window.get(charToWindow);
                Integer countInNeed = need.get(charToWindow);
                if (countInWindow.equals(countInNeed)) {
                    // valid 是 window 中，满足 need 的条件的个数
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            // 如果 valid 和 need.size 的大小相同，则说明 window 已完全覆盖 need 的条件
            // 所以，继续向左移动，直到不满足条件为止
            int needSize = need.size();
            while (needSize == valid) {

                int currentWindowSize = right - left;
                if (currentWindowSize < minWindowLen) {
                    // 如果当前窗口长度，小于之前记录的最小窗口的长度：
                    // 更新最小长度
                    minWindowLen = currentWindowSize;
                    // 更新最小覆盖子串的起始 index
                    minWindowStartIndex = left;
                }

                // charRemoveFromWindow 表示将要从窗口中移除的字符
                char charRemoveFromWindow = s.charAt(left);
                // 向左移动窗口
                left++;

                if (need.containsKey(charRemoveFromWindow)) {
                    Integer countWindow = window.get(charRemoveFromWindow);
                    Integer countNeed = need.get(charRemoveFromWindow);

                    if (countNeed.equals(countWindow)) {
                        // valid 表示 window 窗口中满足 need 条件的字符个数
                        // 此时 countNeed 和 countWindow 相等，说明在此之前刚好满足
                        // 而下一步是移除该字符，所以这之后就不满足了，所以需要减一
                        valid--;
                    }
                    // 让该字符串在 window 中出现的次数减少一
                    window.put(charRemoveFromWindow, countWindow - 1);
                }
            }
        }

        // 返回
        if (minWindowLen == Integer.MAX_VALUE) {
            // 如果最小窗口长度没有更新，说明不存在
            return "";
        } else {
            return s.substring(minWindowStartIndex, minWindowStartIndex + minWindowLen);
        }
    }
}