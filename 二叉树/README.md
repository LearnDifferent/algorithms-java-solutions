来自：https://labuladong.github.io/algo/2/18/31/

Complete Binary Tree（完全二叉树）：

每一层都是紧凑靠左排列的。Complete Binary Tree 可以完美地使用数组来存储，而不会浪费空间。

比如下面这个：

```
					 [A]
					 / \
				[B]    [C]
				/ \    /
			[D] [E] [F]
```

可以直接用数组表示为：`[ ][A][B][C][D][E][F]`。如果是其他的二叉树，就可能会出现某个位置为空的情况。

![img](https://labuladong.github.io/algo/images/complete_tree/complete.png)

---

Perfect Binary Tree（满二叉树）：

特殊的 Complete Binary Tree，每层都是是满的，像一个稳定的三角形

![img](https://labuladong.github.io/algo/images/complete_tree/perfect.png)

---

Full Binary Tree（也被翻译为"满二叉树"）：

一棵二叉树的所有节点要么没有孩子节点，要么有两个孩子节点

![img](https://labuladong.github.io/algo/images/complete_tree/trees.png)
