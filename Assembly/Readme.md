# MIPS Assembly Programming

MIPS assembly language programming exercises with three different difficulty levels.

## 🖥️ Project Overview

A collection of MIPS assembly language programming tasks demonstrating understanding of assembly programming and computer architecture.

## 🛠️ Tech Stack

- **Language**: MIPS Assembly
- **Simulator**: MARS4.5 (Recommended) / QtSpim
- **Architecture**: MIPS32

## 🎯 Task Implementation

### Q1: String Character Replacement
**Function**: Replaces all occurrences of character 'b' with character 'c' in string 'a'

**Key Features**:
- Input validation (empty input, length limits, non-English characters)
- Character existence check
- Duplicate character detection
- Interactive retry mechanism

**Technical Implementation**:
- String traversal and character comparison
- Memory management for input buffers
- Error handling with specific messages

### Q2: Mathematical Expression Calculator
**Function**: Calculates x³ + 3x²y + 3xy² + 9y³ with overflow protection

**Key Features**:
- Optimized calculation using (x+y)³ + (2y)³
- Comprehensive overflow detection at each step
- Input validation for integers
- Detailed error reporting for overflow scenarios

**Technical Implementation**:
- Multi-step arithmetic operations
- Overflow checking for addition and multiplication
- String-to-integer conversion
- Step-by-step calculation with safety checks

### Q3: String Comparison (strncmp)
**Function**: Implements C language's strncmp function in MIPS assembly

**Key Features**:
- Protocol format parsing (P1:, P2:, P3:)
- String comparison with specified length
- Multiple number format support (decimal, hex, octal, binary, ASCII)
- Escape character handling

**Technical Implementation**:
- Character-by-character string comparison
- Number format conversion and validation
- Stack-based state management
- Complex input parsing with error handling

## 🚀 Quick Start

### Download Simulators
- **MARS4.5**: [Download](https://courses.missouristate.edu/kenvollmar/mars/download.htm) (Recommended)
- **QtSpim**: [Download](http://spimsimulator.sourceforge.net/) (Official)

## 🔧 Technical Features

### MIPS Instruction Set
- Arithmetic instructions
- Logical instructions
- Branch and jump instructions
- Memory access instructions
- System calls

### Programming Skills
- Register usage optimization
- Memory management
- Function calling conventions
- Error handling

---

*This project is for educational purposes only.*

---

# MIPS汇编语言编程

MIPS汇编语言编程练习，包含三个不同难度的编程任务。

## 🖥️ 项目简介

MIPS汇编语言编程任务集合，展示汇编语言编程和计算机架构的理解。

## 🛠️ 技术栈

- **语言**: MIPS汇编语言
- **模拟器**: MARS4.5 (推荐) / QtSpim
- **架构**: MIPS32

## 🎯 任务实现

### Q1: 字符串字符替换
**功能**: 将字符串'a'中所有字符'b'替换为字符'c'

**主要特性**:
- 输入验证（空输入、长度限制、非英文字符）
- 字符存在性检查
- 重复字符检测
- 交互式重试机制

**技术实现**:
- 字符串遍历和字符比较
- 输入缓冲区的内存管理
- 具体错误消息处理

### Q2: 数学表达式计算器
**功能**: 计算x³ + 3x²y + 3xy² + 9y³，包含溢出保护

**主要特性**:
- 使用(x+y)³ + (2y)³优化计算
- 每步的全面溢出检测
- 整数输入验证
- 溢出场景的详细错误报告

**技术实现**:
- 多步算术运算
- 加法和乘法的溢出检查
- 字符串到整数转换
- 带安全检查的逐步计算

### Q3: 字符串比较 (strncmp)
**功能**: 用MIPS汇编实现C语言的strncmp函数

**主要特性**:
- 协议格式解析 (P1:, P2:, P3:)
- 指定长度的字符串比较
- 多种数字格式支持（十进制、十六进制、八进制、二进制、ASCII）
- 转义字符处理

**技术实现**:
- 逐字符字符串比较
- 数字格式转换和验证
- 基于栈的状态管理
- 带错误处理的复杂输入解析

## 🚀 快速开始

### 下载模拟器
- **MARS4.5**: [下载](https://courses.missouristate.edu/kenvollmar/mars/download.htm) (推荐)
- **QtSpim**: [下载](http://spimsimulator.sourceforge.net/) (官方)

## 🔧 技术特点

### MIPS指令集
- 算术运算指令
- 逻辑运算指令
- 分支和跳转指令
- 内存访问指令
- 系统调用

### 编程技巧
- 寄存器使用优化
- 内存管理
- 函数调用约定
- 错误处理

---

*本项目仅用于教育目的。*
