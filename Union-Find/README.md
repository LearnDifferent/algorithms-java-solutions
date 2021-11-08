# Union-Find 算法（并查集算法）

大部分内容摘抄自 [labuladong的文章](https://labuladong.gitee.io/algo/2/19/36/) ，小部分内容参考了[菜鸟教程](https://www.runoob.com/data-structures/union-find-compress.html#:~:text=%E5%AF%B9%E4%BA%8E%E4%B8%80%E4%B8%AA%E9%9B%86%E5%90%88%E6%A0%91%E6%9D%A5,%E8%BF%87%E7%A8%8B%E5%B0%B1%E5%8F%AB%E5%81%9A%E8%B7%AF%E5%BE%84%E5%8E%8B%E7%BC%A9%E3%80%82)和简书上的[这篇文章](https://www.jianshu.com/p/44541a3fe8b3)。

## Dynamic Connectivity（动态连通性）

动态连通性其实可以抽象成给一幅图连线。比如下面这幅图，总共有 10 个节点，他们互不相连，分别用 0~9 标记：

![0 到 9，共 10 个不相连的节点](https://labuladong.gitee.io/algo/images/unionfind/1.jpg)

 Union-Find 算法主要需要实现的 API：

```java
class UF {
    /* 将 p 和 q 连接 */
    public void union(int p, int q);
    /* 判断 p 和 q 是否连通 */
    public boolean connected(int p, int q);
    /* 返回图中有多少个连通分量 */
    public int count();
}
```

Given a set of N objects.

- Union command: connect two objects.
- Find/connected query: is there a path connecting the two objects?

「连通（is connected to）」是一种等价关系，具有如下三个性质：

1、自反性（Reflexive）：节点 `p` 和 `p` 是连通的。

2、对称性（Symmetric）：如果节点 `p` 和 `q` 连通，那么 `q` 和 `p` 也连通。

3、传递性（Transitive）：如果节点 `p` 和 `q` 连通，`q` 和 `r` 连通，那么 `p` 和 `r` 也连通。

>两个数相连时，它们属于同一个等价类（Connected Components）。
>等价类，就是所有相连的数的集合，这个集合必须包含所有相连的数。

比如说之前那幅图，0～9 任意两个**不同**的点都不连通，调用 `connected` 都会返回 false，连通分量为 10 个。

如果现在调用 `union(0, 1)`，那么 0 和 1 被连通，连通分量降为 9 个。

再调用 `union(1, 2)`，这时 0,1,2 都被连通，调用 `connected(0, 2)` 也会返回 true，连通分量变为 8 个。

![0、1和2节点被连通了](https://labuladong.gitee.io/algo/images/unionfind/2.jpg)

判断这种「等价关系」非常实用，比如说编译器判断同一个变量的不同引用，比如社交网络中的朋友圈计算等等。

Union-Find 算法的关键就在于 `union` 和 `connected` 函数的效率。

## 用什么模型来表示一幅图的连通状态？用什么数据结构来实现代码？

> 模型：使用森林（若干棵树）来表示图的动态连通性
>
> 数据结构：用数组来具体实现这个森林

使用森林来表示 Dynamic Connectivity（动态连通性）：

- 设定树的每个节点有一个指针指向其父节点，如果是根节点的话，这个指针指向自己

比如说刚才那幅 10 个节点的图，一开始的时候没有相互连通，就是这样：

![互不相连的时候，每个节点都指向自己](https://labuladong.gitee.io/algo/images/unionfind/3.jpg)

```java
class UF {
    // 记录连通分量
    private int count;
    // 节点 x 的父节点是 parent[x]
  	// 比如，节点 3 的父节点是 4，那么 parent[3] = 4;
    private int[] parent;

    /* 构造函数，n 为图的节点总数 */
    public UF(int n) {
        // 一开始互不连通，素以连通分量为就是节点数
        this.count = n;
        // 父节点指针初始指向自己，
      	// 这里初始化所有节点的父节点为自己
        parent = new int[n];
        for (int i = 0; i < n; i++)
          	// 比如，节点 3 的父节点为节点 3，
          	// 表示自己和自己连通
            parent[i] = i;
    }

    /* 其他函数 */
}
```

**如果某两个节点被连通，则让其中的（任意）一个节点的根节点接到另一个节点的根节点上**（因为是双向的连接，所以 A 节点连接 B 节点，和 B 节点连接 A 节点是一样的）：

![](https://labuladong.gitee.io/algo/images/unionfind/4.jpg)

```java
public void union(int p, int q) {
  	// 获取编号为 p 和 q 的根节点
    int rootP = find(p);
    int rootQ = find(q);
  
    if (rootP == rootQ) {
      	// 如果根节点相同，说明 p 和 q 已经连通了
        return;
    }
    // 将两棵树合并为一棵：连通 p 的跟节点和 q 的跟节点
    parent[rootP] = rootQ;
    // parent[rootQ] = rootP 也一样
  
    // 两个分量合为一个了，所以这里需要 -1
  	count--;
}

/* 返回某个节点 x 的根节点 */
private int find(int x) {
    // 根节点的 parent[x] == x
	  // 因为连接两个节点的时候，不是直接相连两个节点的，
  	// 而是将两个节点的根节点相互连接，
  	// 假设 A 是需要连接的 p 节点的根节点，B 是 q 节点的根节点
  	// 我们不是直接连接 p 和 q，而将 A 的父节点，设置为 B，
  	// 此时 A 有了父节点 B，而 B 还是一个根节点（B 也可以连接 A）
    while (parent[x] != x) {
      	// 不停地获取父节点，直到获取到根节点
        x = parent[x];
    }
    return x;
}

/* 返回当前的连通分量个数 */
public int count() { 
    return count;
}
```

**这样，如果节点 `p` 和 `q` 连通的话，它们一定拥有相同的根节点**：

![img](https://labuladong.gitee.io/algo/images/unionfind/5.jpg)

```java
public boolean connected(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    return rootP == rootQ;
}
```

这个算法的主要 API `connected` 和 `union` 中的复杂度和 `find` 一样。`find` 主要功能就是从某个节点向上遍历到树根，其时间复杂度就是树的高度。

我们可能习惯性地认为树的高度就是 `logN`，但这并不一定。`logN` 的高度只存在于平衡二叉树，对于一般的树可能出现极端不平衡的情况，使得「树」几乎退化成「链表」，树的高度最坏情况下可能变成 `N`。

![img](https://labuladong.gitee.io/algo/images/unionfind/6.jpg)

所以说上面这种解法，`find` , `union` , `connected` 的时间复杂度都是 O(N)。这个复杂度很不理想的，你想图论解决的都是诸如社交网络这样数据规模巨大的问题，对于 `union` 和 `connected` 的调用非常频繁，每次调用需要线性时间完全不可忍受。

**问题的关键在于，如何想办法避免树的不平衡呢**？

## 平衡性优化

我们要知道哪种情况下可能出现不平衡现象，关键在于 `union` 过程：

```java
public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) {
        return;
    }
    // 将两棵树合并为一棵
    parent[rootP] = rootQ;
    // parent[rootQ] = rootP 也可以
    count--;
}  
```

我们一开始就是简单粗暴的把 `p` 所在的树接到 `q` 所在的树的根节点下面，那么这里就可能出现「头重脚轻」的不平衡状况，比如下面这种局面：

[![img](https://labuladong.gitee.io/algo/images/unionfind/7.jpg)](https://labuladong.gitee.io/algo/images/unionfind/7.jpg)

长此以往，树可能生长得很不平衡。**我们其实是希望，小一些的树接到大一些的树下面，这样就能避免头重脚轻，更平衡一些**。解决方法是**额外使用一个 `size` 数组，记录每棵树包含的节点数**，我们不妨称为「重量」：

```java
class UF {
    private int count;
    private int[] parent;
    // 新增一个数组记录树的“重量”
  	// 比如说，节点 2 没有和其他节点连接，只有它自己
  	// 那么 size[2] = 1;
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        // 最初每棵树只有一个节点
        // 重量应该初始化 1
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    /* 其他函数 */
}
```

比如说 `size[3] = 5` 表示，以节点 `3` 为根的那棵树，总共有 `5` 个节点。这样我们可以修改一下 `union` 方法：

```java
public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) {
        return;
    }
    
    // 小树接到大树下面，较平衡
    if (size[rootP] > size[rootQ]) {
				// 如果 rootP 的 size 比较大
      	// 就设定 rootQ 的父节点为 rootP
        parent[rootQ] = rootP;
      	// rootP 的 size 也要相应增加
        size[rootP] += size[rootQ];
    } else {
        parent[rootP] = rootQ;
        size[rootQ] += size[rootP];
    }
    count--;
}
```

这样，通过比较树的重量，就可以保证树的生长相对平衡，树的高度大致在 `logN` 这个数量级，极大提升执行效率。

此时，`find` , `union` , `connected` 的时间复杂度都下降为 O(logN)，即便数据规模上亿，所需时间也非常少。



## 路径压缩

我们可以把最初的树压缩成下图所示，层数是最少的。所有 API 的复杂度全部下降为 O(1)：

![img](https://www.runoob.com/wp-content/uploads/2020/10/conpress-04.png)

要做到这一点，非常简单，只需要在 `find` 中将代码改为：

```java
private int find(int x) {
  	if (x != parent[x]) {
      // 递归查找 x 节点的 父节点 的根节点，
      // 在递归查找的过程中，会不断将父节点设置为根节点
      int rootX = find(parent[x]);
      // 让 x 的父节点，直接连接根节点
      parent[x] = rootX;
    }
  	// 此时，x 的父节点就是根节点，直接返回父节点即可
    return parent[x];
}
```



## 总结

```java
class UF {
    // 连通分量个数
    private int count;
    // 存储一棵树
    private int[] parent;
    // 记录树的「重量」
    private int[] size;

    // n 为图中节点的个数
    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    // 将节点 p 和节点 q 连通
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;
        
        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        // 两个连通分量合并成一个连通分量
        count--;
    }

    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    // 返回节点 x 的连通分量根节点
    private int find(int x) {
        // 查找的过程中，递归压缩，让 x 的父节点就是根节点
      	// 只要 x 不是根节点，就不断递归，让父节点连接根节点
      	if (x != parent[x]) {
          parent[x] = find(parent[x]);
        }
      	// 此时，父节点即为根节点
      	return parent[x];
    }

    // 返回图中的连通分量个数
    public int count() {
        return count;
    }
}
```

Union-Find 算法的复杂度可以这样分析：构造函数初始化数据结构需要 O(N) 的时间和空间复杂度；连通两个节点 `union`、判断两个节点的连通性 `connected`、计算连通分量 `count` 所需的时间复杂度均为 O(1)。

