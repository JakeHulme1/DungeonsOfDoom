# DungeonsOfDoom
Java implementation of Dungeons of Doom text-based game

MAP FORMAT:
To ensure the program can read a map, it must be in the following format (same format as example map on Moodle):
- Line 1: Map name
- Line 2: "Win <gold needed to win (integer)>"
- Line 3+ The map data (consisting of '#', '.', 'E' and 'G' tiles only.

HOW TO RUN THE GAME:
1. Navigate to the Game.java file
2. Input the dimensions (width x height) of the map in the 'board' object (line 20)
3. Input the file path to the folder containing the map(s) into the 'directoryPath' variable
4. Input the map name into the 'fileName' variable
5. Run the Game.java file and enter commands in the terminal

Object-oriented design principles used:
- Single responsibility - each class has minimum amount of functionality
- Inheritance - HumanPlayer and BotPlayer inherit from abstract Player class - promotes code reusability
- Encapsulation - all functionality is hidden within classes, with only necessary methods being public
- Clean and readable code - all field and method names have relevant titles, comments used to explain why code is there and when its not obvious what code does.
