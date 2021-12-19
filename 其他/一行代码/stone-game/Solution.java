/*
877. Stone Game:

Alice and Bob play a game with piles of stones.
There are an even number of piles arranged in a row (石头堆的数量为偶数，两人拿走的堆数相同),
and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones.
The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first.
Each turn, a player takes the entire pile of stones
either from the beginning or from the end of the row (只能拿走最左边或者最右边的石头堆).
This continues until there are no more piles left,
at which point the person with the most stones wins.

Assuming Alice and Bob play optimally,
return true if Alice wins the game, or false if Bob wins.

链接：https://leetcode-cn.com/problems/stone-game
 */
class Solution {
    public boolean stoneGame(int[] piles) {
        /*
        因为 Alice starting first，所以 Alice 必赢。
        只要计算好奇数堆和偶数堆的总数，最后不管怎么样，
        先手都能控制自己永远只拿奇数堆或偶数堆。
         */
        return true;
    }
}
