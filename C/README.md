# C Digital Banking System / C语言数字银行系统

Complete banking account management system developed in C, supporting account operations and transactions.

使用C语言开发的完整银行账户管理系统，支持账户操作和交易功能。

## 🏦 Project Overview / 项目简介

A comprehensive digital banking system implementing account management, fund operations, and transaction records using C programming language.

基于C语言开发的综合数字银行系统，实现账户管理、资金操作和交易记录功能。

## 🛠️ Tech Stack / 技术栈

- **Language / 语言**: C
- **Compiler / 编译器**: GCC
- **Data Structures / 数据结构**: Structs, Arrays, File I/O / 结构体、数组、文件I/O
- **Memory Management / 内存管理**: Dynamic Memory Allocation / 动态内存分配

## 📁 Project Structure / 项目结构

```
C/
├── DigitalMiniBank_Ning_Zhu_20215673.c    # Main program / 主程序文件
├── CityBranchCodes_Ning_Zhu_20215673.txt  # City and branch codes / 城市和分行代码
├── COMP1038.PGA-Coursework-02-Specification.pdf  # Project specification / 项目规范
└── README.md                               # Project documentation / 项目文档
```

## 🚀 Quick Start / 快速开始

```bash
# Compile program / 编译程序
gcc -o DigitalMiniBank DigitalMiniBank_Ning_Zhu_20215673.c

# Run program / 运行程序
./DigitalMiniBank
```

## 🎯 Key Features / 主要功能

- Account management / 账户管理
- Fund operations (deposit/withdraw) / 资金操作（存款/取款）
- Money transfer / 转账功能
- Transaction history / 交易记录
- Data persistence / 数据持久化
- Input validation / 输入验证

## 🏗️ Core Data Structures / 核心数据结构

```c
// Account data structure / 账户数据结构
struct datas {
    int flag;           // 0=closed, 1=open, 2=transfer / 0=关闭, 1=开启, 2=转账
    char name[101];     // Customer name / 客户姓名
    char id[16];        // ID number / 身份证号
    char address[201];  // Address / 地址
    char birth[11];     // Birth date / 出生日期
    long long deposit;  // Deposit amount / 存款金额
    char city[4];       // City code / 城市代码
    char branch[3];     // Branch code / 分行代码
    int number;         // Account number / 账户号码
    struct time date;   // Opening date / 开户日期
    struct transfer tran; // Transfer info / 转账信息
};
```

## 🎮 System Menu / 系统菜单

```
1) Show city code and branch code / 显示城市和分行代码
2) Open an account / 开户
3) Show account details / 显示账户详情
4) Show list of accounts / 显示账户列表
5) Deposit in an account / 存款
6) Withdraw from an account / 取款
7) Transfer money / 转账
8) Transaction details / 交易详情
9) Close an account / 关闭账户
10) Quit / 退出
```

## 🔧 Technical Features / 技术特性

- **File I/O Operations / 文件I/O操作**: Binary file read/write / 二进制文件读写
- **Memory Management / 内存管理**: Dynamic allocation and deallocation / 动态分配和释放
- **Error Handling / 错误处理**: Comprehensive input validation / 全面的输入验证
- **Data Persistence / 数据持久化**: Account data stored in files / 账户数据文件存储

## 📚 Documentation / 文档

- [Project Specification / 项目规范](./COMP1038.PGA-Coursework-02-Specification.pdf)

---

*This project is for educational purposes only.*  
*本项目仅用于教育目的。*