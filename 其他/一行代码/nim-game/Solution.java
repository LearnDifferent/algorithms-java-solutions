/*
292. Nim Game:

You are playing the following Nim Game with your friend:

- Initially, there is a heap of stones on the table.
- You and your friend will alternate taking turns, and you go first.
- On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.
- The one who removes the last stone is the winner.

Given n, the number of stones in the heap,
return true if you can win the game assuming both you and your friend play optimally,
otherwise return false.

链接：https://leetcode-cn.com/problems/nim-game
 */
class Solution {
    public boolean canWinNim(int n) {

        // 只要自己面对 4 的倍数，就一定会输：
        // 因为一次移动 1～3，所以只剩 4 个的时候就会输，
        // 只要自己还剩 5～7 个的话，就一定会赢，
        // 也就是说，当到对手的时候，还剩 8 个，就是我赢。
        // 可以得出结论，只要不是 4 的倍数，自己就稳赢。
        return n % 4 != 0;
    }
}
