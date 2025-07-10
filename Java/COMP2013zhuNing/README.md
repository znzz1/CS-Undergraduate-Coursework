# Report for Breakout Game

## Author

    Ning ZHU  
    Student ID: 20215673
    Contact me: scynz1@nottingham.ac.uk

## Getting Started
First, download the source code from the `project` folder, unzip it and open it in `Intellij`.

Then, to start the game, you can:
  + Type `mvn clean javafx:run` command in the terminal (need `maven` installed on your computer, check your `JAVA_HOME` if it doesn't work, `JDK 17` is used)
  + Run the saved configuration `StartGame`
  + Open the class StartGame and run the `main` method manually

> `mvn clean javafx:run` is the `maven command` which helps to build and run the program

You can find the [Javadoc documentation](./javadoc) of the source code in the `javadoc` folder which resides in the `COMP2013zhuNing` folder.

## Design Patterns Used
  + [MVC design pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
  + [Factory design pattern](https://en.wikipedia.org/wiki/Factory_method_pattern)
  + [Observer design pattern](https://en.wikipedia.org/wiki/Observer_pattern)

## Major Changes
  + **Translation from Swing to JavaFX**
  + **More scenes with more functions**
  + **More precise algorithms used to detect and handle impact**

## Features Successfully Implemented

Feature name | Feature description 
--|:--:
Translation from Swing to JavaFX | Rewrites the whole project using JavaFX
Start scene| Start scene with buttons to move to other scenes
Preferences scene | Choose the color theme of the game
Guidance scene | Introduce how to play the game
Debug console and pause menu| Rewrite using JavaFX
Game info visualization| Visualize the ball count, brick count and level of the game
Scoring system | Calculate the score player gets in one round of game
Username form | Collect the player's name
Permanent high score list | Show the top 5 records in history 
Game object pictures | Use images to show different game object
More brick types and additional levels | More kinds of bricks and additional playable levels
Game Sounds | Add game audio and background music
More precise impact detection system | Modify the algorithm used to detect and handle impact

## Features Not Implemented

Feature | Implementation state | Reason for not implemented
--|:--:|:--:
Binds the size of the game object with the window | Tried but failed to fix bugs | It complicates the game and is not so useful. 
Special scene for users to define levels by themselves | Haven't tried | It will take a lot of time to implement and the original method to create levels is good enough
Saves the game | Haven't tried | Do not have time to implement it

## New Classes Introduced
Classes introduced | Feature implemented
--|:--:
*GameSceneController* 
*NormalSceneController*
MainMenuController | Start scene
PreferencesController, GameBoardFactory | Preferences scene
GuidanceController | Guidance scene
GameEndMenuController | Show the score
UsernameFormController | Username form
RankingListController, RankingListModel | Permanent high score list
GoldenBrick | More brick types and additional levels
GameSoundFactory | Game sounds
GameStatusBar | Game info visualization

## Classes Modified From Original Code
Modified classes | Original classes
--|:--:
BallModel, *Ball* | *Ball*
RubberBall | Ball1
PaddleModel, *Paddle*, RubberPaddle | Paddle
BrickModel, *Brick*, *CrackedBrick* | *Brick*
CementBrick | Brick1
ClayBrick | Brick2
SteelBrick | Brick3
DebugConsoleController | DebugConsole, DebugPanel
GameBoardManager, BrickFactory | Wall
KeyInputListener, PauseMenuController, GameBoard | GameBoard, GameFrame
StartGame | StartGame