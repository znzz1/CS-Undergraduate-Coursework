# Haskell Functional Programming Project / Haskell函数式编程项目

Pathfinding algorithm implementation in Haskell, demonstrating functional programming concepts.

基于Haskell实现的路径查找算法，展示函数式编程概念。

## 🧮 Project Overview / 项目简介

A pathfinding game implementation using Haskell functional programming, featuring recursive algorithms and pure functions.

基于Haskell函数式编程的路径查找游戏实现，包含递归算法和纯函数。

## 🛠️ Tech Stack / 技术栈

- **Language / 语言**: Haskell
- **Compiler / 编译器**: GHC (Glasgow Haskell Compiler)
- **Paradigm / 编程范式**: Functional Programming / 函数式编程
- **Algorithms / 算法**: Pathfinding, Recursion / 路径查找、递归

## 📁 Project Structure / 项目结构

```
Haskell/
├── 20215673.hs                           # Main program / 主程序文件
├── PGP 2021 CW1 Haskell Imp instructions.pdf  # Project specification / 项目规范
└── README.md                             # Project documentation / 项目文档
```

## 🚀 Quick Start / 快速开始

```bash
# Install GHC / 安装GHC
# Ubuntu/Debian: sudo apt-get install ghc
# macOS: brew install ghc
# Windows: Download Haskell Platform

# Start GHCi / 启动GHCi
ghci 20215673.hs

# Test functions / 测试函数
ghci> play map1 10
ghci> play map2 15
ghci> play map3 20
```

## 🎯 Key Features / 主要特性

- Pathfinding algorithm / 路径查找算法
- Recursive depth-first search / 递归深度优先搜索
- Pure functional programming / 纯函数式编程
- Pattern matching / 模式匹配
- Higher-order functions / 高阶函数

## 🎮 Game Rules / 游戏规则

### Map Symbols / 地图符号
- `A`: Start position / 起始位置
- `Z`: Target position / 目标位置
- `1`, `2`, `3`: Gems with different values / 不同价值的宝石
- `#`: Wall, impassable / 墙壁，不可通过
- `.`: Empty space, movable / 空地，可以移动

### Movement Rules / 移动规则
- Move up(N), down(S), left(W), right(E) / 可上下左右移动
- Each move consumes 1 fuel unit / 每次移动消耗1单位燃料
- Collect gems to increase total value / 收集宝石增加总价值
- Reach target Z to win / 到达目标Z获胜

## 📊 Test Maps / 测试地图

```haskell
-- Test map 1 / 测试地图1
map1 = ["Z.2","1#.","A.."]

-- Test map 2 / 测试地图2
map2 = ["Z.1..","####.","1.3..",".#.#.","A..2."]

-- Test map 3 / 测试地图3
map3 = ["..2..Z",".####.","..3...","1#..#.",".#.##1","A..1.."]
```

## 🔧 Core Functions / 核心函数

```haskell
-- Main game function / 主游戏函数
play :: [[Char]] -> Int -> ([Char],Int)

-- Find best path / 找到最佳路径
bestPath :: [[Char]] -> (Int,Int) -> Int -> ([Char],Int)

-- List all possible paths / 列出所有可能路径
listAll :: [[Char]] -> Int -> ((Int,Int),[Char],Int,Int) -> [((Int,Int),[Char],Int,Int)]

-- Quick sort implementation / 快速排序实现
quickSort :: [([Char],Int)] -> [([Char],Int)]
```

## 📚 Documentation / 文档

- [Project Specification / 项目规范](./PGP%202021%20CW1%20Haskell%20Imp%20instructions.pdf)

---

*This project is for educational purposes only.*  
*本项目仅用于教育目的。*