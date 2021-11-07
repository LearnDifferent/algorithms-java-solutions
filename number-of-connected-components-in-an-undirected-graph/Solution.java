/*
 * 323. Number of Connected Components in an Undirected Graph:
 * 无向图中连通分量的数目
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 *
 * Example 1:
 *
 *      0          3
 *      |          |
 *      1 --- 2    4
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 *
 * Example 2:
 *
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 *
 * Note: You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * 解决方案：Union-Find 算法（并查集算法），解决图论中的「动态连通性（Dynamic Connectivity）」问题
 *
 * 链接：https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 */