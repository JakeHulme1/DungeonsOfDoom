/* Used this guide on Java enum:
https://www.geeksforgeeks.org/enum-in-java/?ref=header_outind 
Since we know all tile types at compile-time, an enum is appropriate here */

/**
 * The TileType contains all the types a tile can have
 */
public enum TileType {

    /**
     * Represents a wall tile.
     */
    WALL,

    /**
     * Represents an empty tile.
     */
    EMPTY,

    /**
     * Represents a gold tile.
     */
    GOLD,

    /**
     * Represents an exit tile.
     */
    EXIT,

    /**
     * Represents a player tile.
     */
    PLAYER,

    /**
     * Represents a bot tile.
     */
    BOT
}
