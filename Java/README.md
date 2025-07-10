# Java Programming Project / Java编程项目

Breakout game developed in Java, demonstrating MVC architecture and design patterns.

基于Java开发的打砖块游戏，展示MVC架构和设计模式应用。

## 🎮 Project Overview / 项目简介

A complete Breakout game implementation using Java with MVC architecture and multiple design patterns.

基于Java开发的完整打砖块游戏，使用MVC架构和多种设计模式。

## 🛠️ Tech Stack / 技术栈

- **Language / 语言**: Java 8+
- **Build Tool / 构建工具**: Maven
- **Architecture / 架构**: MVC (Model-View-Controller)
- **Design Patterns / 设计模式**: Singleton, Factory, Observer, Strategy

## 📁 Project Structure / 项目结构

```
Java/
├── src/main/java/com/ning/breakout/  # Game source code / 游戏源代码
│   ├── StartGame.java               # Game entry point / 游戏启动类
│   ├── controller/                  # Controller layer / 控制器层
│   ├── model/                       # Model layer / 模型层
│   └── view/                        # View layer / 视图层
├── COMP2013zhuNing/                 # Documentation & demo / 文档和演示
│   ├── Design.pdf                   # Design document / 设计文档
│   ├── javadoc/                     # API documentation / API文档
│   └── zhuNingDemo.mp4              # Demo video / 演示视频
└── pom.xml                          # Maven configuration / Maven配置
```

## 🚀 Quick Start / 快速开始

```bash
# Compile project / 编译项目
mvn clean install

# Run game / 运行游戏
mvn exec:java -Dexec.mainClass="com.ning.breakout.StartGame"
```

## 🎯 Key Features / 主要特性

- Complete Breakout game logic / 完整的打砖块游戏逻辑
- MVC architecture design / MVC架构设计
- Multiple design patterns implementation / 多种设计模式应用
- Graphical user interface / 图形化用户界面

## 📚 Documentation / 文档

- [Design Document / 设计文档](./COMP2013zhuNing/Design.pdf)
- [Demo Video / 演示视频](./COMP2013zhuNing/zhuNingDemo.mp4)
- [API Documentation / API文档](./COMP2013zhuNing/javadoc/)

---

*This project is for educational purposes only.*  
*本项目仅用于教育目的。*