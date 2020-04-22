# Chinese Chess built in Java

A two player Chinese Chess Game implemented in JAVA using the swing libraries for COP 3252.

Beyond implementing the rules of standard Chinese Chess, this game includes bonus features such as move timers, theming, exporting move history, a live event log, and a fully fledged gui.

To run the game, simply download the jar file, and use the command java -jar ChineseChess.jar.

## Extra Features
* Theming with an intuitive start menu
* Game Profile (option to change time limit, player color, and player name)
* Player timers (switches between turns)
* Clean user interface
* Live event log
* End Game/Restart Game option
* Chinese and English piece icons (Under View > Language)
* Menu Bar (Including direct link to rules, about page)
* Save Game (Choose file name and path, includes all moves made, player name, time elapsed)
* Open source and object oriented design
* Java Documentation


## How to Play

1. Download the jar file from https://github.com/ChiliPaneer/ChineseChess-JAVA_Swing
2. On a maching with at least Java 8, run the following command: "java jar ChineseChess-JAVA_Swing.jar
3. Configure your theme on the start menu, or just press the begin button to launch the board.
4. Select a piece to highlight it
5. Select an endpoint to attempt to move the piece there   (*note that the bottom player always goes first*)
6. The game will automatically inform you if a checkmate or stalemate has been reached by standard rules.
7. If you wish to end the game early, click on the End Game button.
8. To play again, exit the game and repeast steps 2 through 7.

## Roles
Michael handled most of the GUI stuff in game, Venkat handled most of the game logic stuff, and Andy handled most of the bonus feature stuff. We mostly focused on our individual parts but communicated a lot to make sure everything would be easily compatible. 

##### Michael Yu:
* Created the actual gameboard
* Managed the languages and icon handling
* Created the event box

##### Venkat Pamulapati
* Implemented the game logic including the board, the checking/validation, pieces, edge case handling, etc.
* Created the Start Menu
* Implemented theming

##### Andy Jiang
* Implemented move timers and move logger
* Created the GUI for the timers and asynchronous updating
* Implemented game saving

## In-depth feature overview:

##### Timer and Timer GUI:
The Timer class keeps track of the start and stop time in nanoseconds (System.nanoTime()) to determine the time elapsed
, which is then converted into milliseconds. Nanoseconds was used over milliseconds for a more precise measurement of time elapsed. It is then implemented
into the Player class to create a timer for each individual player. All methods of timer is called through the Player class.
The Player class interacts with the Timer class to get all necessary information about the player's time elapsed during each turn.
Then, it formats it in mm:ss format using SimpleDateFormat and make it accessible for the TurnTimerPanel to get.

The TurnTimerPanel is a GridLayout (2x1) of two timer panel (one for each player). Inside each timer panel, there is a
number panel, centered inside timer panel using BorderLayout, which includes the time label. The time label is inside
a panel to create extra space between the number and border.

In order for the two timer panel to update the time left for each player, threading must be used. Without threads, when
updating the label inside a while loop, the game does not function until while loop is exited. Thus, the time labels
must be updated asynchronously with the game. Players' timer is stopped when their turn is over and started again when
their turn start. Thus, the updating of each label is started and stopped, to create a animation of switching. However,
dead threads can not be restarted, thus a new thread is created each time the label is suppose to update.

