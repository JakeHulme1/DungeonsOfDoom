/* Used this guide on Java enum as we know all possible variations of a tile at compile time:
https://www.geeksforgeeks.org/enum-in-java/?ref=header_outind */

public enum TileType {
    // All public so they can be accessed by other functions
    WALL,EMPTY, GOLD, EXIT, PLAYER, BOT
}
