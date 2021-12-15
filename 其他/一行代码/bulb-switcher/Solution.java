/*
319. Bulb Switcher:
灯泡开关

There are n bulbs that are initially off.
You first turn on all the bulbs, then you turn off every second bulb.

On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
For the i(th) round, you toggle every i bulb. For the n(th) round, you only toggle the last bulb.

Return the number of bulbs that are on after n rounds.

题目含义：

第 1 轮操作把每 1 盏电灯的开关按一下（全部打开）。

第 2 轮操作把每 2 盏灯的开关按一下（就是按第 2，4，6… 盏灯的开关，它们被关闭）。

第 3 轮操作把每 3 盏灯的开关按一下（就是按第 3，6，9… 盏灯的开关，有的被关闭，比如 3，有的被打开，比如 6）…

如此往复，直到第 n 轮，有几盏灯亮着？

解题思路：

假设有 6 盏灯，需要进行 6 轮操作。
只看第 6 盏灯：在第 1、2、3 和 6 轮，会被按下开关，因为 6 = 1 * 6 = 2 * 3。

也就是说，按下的次数为"约数"（或"因子"）的个数，只要按下奇数次，才会是打开状态。

只有平方数的因子数为奇数。
比如 6 = 1*6 = 2*3，它们的因子总是成对出现的，而 4 = 1*4 = 2*2，
只有平方数的平方根因子会只出现 1 次，所以最终答案等于 n 以内（包括n和1）的平方数数量

假设有 16 盏灯，那么第 16 盏灯会被按 5 次：16 = 1 * 16 = 2 * 8 = 4 * 4（其中 4 只按一次）

假设现在总共有 16 盏灯，我们求 16 的平方根，等于 4，
这就说明最后会有 4 盏灯亮着，分别是第 1*1=1 盏、第 2*2=4 盏、第 3*3=9 盏和第 4*4=16 盏

链接：https://leetcode-cn.com/problems/bulb-switcher
 */
class Solution {

    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
