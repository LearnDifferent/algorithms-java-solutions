/**
 * 438. Find All Anagrams in a String:
 * 找到字符串中所有字母异位词
 * 解决方案：滑动窗口
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 */
class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();

        int windowStart = 0;
        // 结束的位置，在窗口最后一个 index 加一的位置
        int windowEnd = 0;
        // 命中的次数：在 window 中的字符，符合 needs 条件的次数
        // 比如：needs 中需要 3 个 c 字符，window 中找到 3 个 c 字符后，命中次数就加一
        int hits = 0;

        // 记录结果
        List<Integer> result = new ArrayList<>();

        // s 是候选的字符串
        char[] pool = s.toCharArray();
        // p 是需要被符合的字符串，也就是需要被找到的同位词
        char[] beFound = p.toCharArray();

        // 统计需要被找到的字符，及其对应出现的次数，然后放入 needs 中
        for (char c : beFound) {
            Integer count = needs.getOrDefault(c, 0);
            needs.put(c, count + 1);
        }

        // 只要 window 小于候选的长度，就继续循环
        // 下面这个判断也可以写成：windowEnd - 1 < pool.length - 1
        // windowEnd - 1 表示 window 的最后一个 index
        // pool.length - 1 表示 pool 最后一个 index
        // 如果相等的话，说明已经循环到了最后一个位置，再继续循环就会超出位置，所以要退出循环
        while (windowEnd < pool.length) {
            // 窗口向右移动后，获取到的的字符
            char charToWindow = pool[windowEnd];
            // 继续向右移动
            windowEnd++;

            if (needs.containsKey(charToWindow)) {
                // 如果 needs 中需要这个字符，就将其放入 window 中，并更新数据
                Integer countInWindow = window.getOrDefault(charToWindow, 0);
                countInWindow++;
                window.put(charToWindow, countInWindow);

                Integer countInNeed = needs.get(charToWindow);
                if (countInWindow.equals(countInNeed)) {
                    // 此时，window 中该字符，已经符合了 needs 中需要的次数
                    hits++;
                }
            }

            // 判断是否收缩窗口（移动左指针）：
            // 只要 window 没有超过"需要被找到的字符串"的长度，就可以继续收缩
            while (windowEnd - windowStart >= beFound.length) {
                // 注意，这里是 needs.size()，表示需要的个数
                if (hits == needs.size()) {
                    // 如果命中的次数，和需要查找的次数相等，说明找到了
                    // 题目中要求记录起始的 index，所以这里就将其加入
                    result.add(windowStart);
                }
                // 如果没有命中，就继续缩小 window 范围
                char charRemoveFromWindow = pool[windowStart];
                windowStart++;

                // 如果 needs 中有该字符，窗口左边指针向后移动的时候，就要更新数据
                if (needs.containsKey(charRemoveFromWindow)) {
                    Integer countCharInNeeds = needs.get(charRemoveFromWindow);
                    Integer countCharInWindow = window.get(charRemoveFromWindow);
                    if (countCharInNeeds.equals(countCharInWindow)) {
                        // 如果相等，就让命中数减一
                        hits--;
                    }
                    // 从 window 中减一，表示删除了该位置的字符
                    window.put(charRemoveFromWindow, countCharInWindow - 1);
                }
            }
        }

        return result;
    }
}