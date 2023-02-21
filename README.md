# Group47_Frogger
This is the github repository for CS 2340 Group 47's Frogger project to be implemented with Android Studio.

Sprint 1 
    - Start Screen 
        o A way to start the game 
        o A way to quit the game 
    - Initial Configuration Screen 
        o An input for player name 
            ▪ Name cannot be whitespace-only, empty, or null 
        o A way to choose difficulty of the game 
        o A way to pick a character sprite to represent the player 
        o A way to continue to the game screen 
    - Game Screen 
        o This screen is where the actual game will take place. This is where most of the 
        functionality in later sprints is implemented. For now, the screen will: 
            ▪ Display starting lives 
            ▪ Player name 
            ▪ Display player character sprite depending on the selected character 
Sprint 2 
    - Make the player able to move up, down, left, and right. It may be a good idea to simplify things 
    by using a grid or tile system to represent all possible locations of the player. 
        o The player should not be able to move off the screen. 
    - Generate safe tiles, road tiles, and river tiles. Rivers and roads should extend across the width of 
    the screen. 
        o Roads and rivers should be of varying widths. For example, the player may have to cross 
        a 2-tile river or a 5-tile river, assuming a tile system is used. 
        o Water tile functionality will be implemented in a future sprint. 
    - Implement a goal tile 
        o Should be located at the opposite end of the map with respect to the player. 
        o The functionality associated with the goal tile (for example, going to the win screen) will 
        be implemented in a future sprint. For now, simply have the goal tile display. 
Sprint 3 
    - Implement vehicles 
        o Must have 3 different types which are differentiated by some gameplay behavior (not 
        just aesthetic). Consider different vehicle sizes, different vehicle speeds, etc. Moving in 
        different directions alone does not count. 
    - Implement score 
        o The player should receive points for travelling towards the goal, but NOT sideways or 
        backwards (ex. Passing a road of cars gives 100 points, passing a road of bicycles – which 
        are slower – gives 50 points) 

 
Sprint 4 
    - Implement water tiles 
        o The player should lose a life if they touch a water tile. Losing a life should work the exact 
        same way as the previous sprint. 
    - Collisions + Losing Lives 
        o If a player touches a car, they should lose a life. 
        o Once the player loses all their lives, they should get sent to a game over screen. 
    - Game Over Screen 
        o Should give the player an option to restart the game or quit. 
        o Text displaying the player’s final score should be displayed. 
 
Sprint 5 
    - Continue to implement score 
        o The player should receive points for travelling towards the goal, but NOT sideways or 
        backwards 
    - Implement functionality for the goal tile 
        o If the player touches the goal tile, the winning game screen should show up. 
    - Implement logs 
        o Must have 2 different types which are differentiated by some gameplay behavior. 
    Moving in different directions alone does not count. 
        o A player on top of a log or should move in the same direction and speed of the log. 
            ▪ The player should die if they move off the screen 
    - Win Screen 
        o Should give the player an option to restart the game or quit. 
        o Text displaying the player’s final score should be displayed. 
