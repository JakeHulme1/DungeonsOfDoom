/**
 * The player class represents a player in the game.
 * It provides common functionality for a human and bot player
 * 
 * It is an abstract class which encapsulates common properties of a player like
 * their current position, moving around the board, picking up gold etc.
 */
public abstract class Player {

    /**
     * The x coordinate of a player
     */
    private int xCoordinate;

    /**
     * The y coordinate of a player
     */
    private int yCoordinate;

    /**
     * The amount of gold a player owns
     */
    private int gold;

    /**
     * The board that a player is associated with
     */
    private Board board;

    /**
     * Stores the tile type of a tile before the player moved onto it.
     * This is necessary because when a player moves onto a tile (e.g., a gold
     * tile), the board will display the player instead of the original tile type.
     * Keeping track of the original tile type allows the game to restore the tile's
     * original state when the player moves away. It also allows a player to pick up
     * the gold as the player must move onto that tile before picking it up, so the
     * game needs to know that the tile was originally a gold tile.
     */
    private TileType originalTileType = TileType.EMPTY;

    /**
     * Constructs a new player and assigns them to a board
     * 
     * @param board the board that the player is playing on
     */
    public Player(Board board) {
        this.gold = 0; // PLayer always starts with no gold
        this.board = board;
    }

    /**
     * Gets the x-coordinate of the player
     *
     * @return the x-coordinate of the player
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * Gets the y-coordinate of the player
     *
     * @return the y-coordinate of the player
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    /**
     * Gets the amount of gold the player owns
     *
     * @return the amount of gold the player owns
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Gets the board which the player is playing on
     *
     * @return the board on which the player is playing
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Gets the original tile type before the player moved onto it
     *
     * @return the original tile type beforeplayer moved onto it
     */
    public TileType getOriginalTileType() {
        return this.originalTileType;
    }

    /**
     * Sets the x-coordinate of the player.
     *
     * @param xCoordinate the new x-coordinate of the player
     */
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Sets the y-coordinate of the player.
     *
     * @param yCoordinate the new y-coordinate of the player
     */
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Sets the amount of gold the player has collected.
     *
     * @param gold the new amount of gold the player has collected
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Sets the original tile type the player was on before moving.
     *
     * @param originalTileType the new original tile type the player was on before
     *                         moving
     */
    public void setOriginalTileType(TileType originalTileType) {
        this.originalTileType = originalTileType;
    }

    /**
     * Abstract method for picking up gold.
     * This method is implemented by HumanPlayer and BotPlayer
     */
    public abstract void pickUpGold();

    /**
     * Abstract method for moving the player in a specified direction.
     * This method is implemented by HumanPlayer and BotPlayer
     *
     * @param direction the direction in which to move the player
     */
    public abstract void move(Direction direction);

    /**
     * Abstract method for quitting the game.
     * This method is implemented by HumanPlayer and BotPlayer
     */
    public abstract void quit();

    /**
     * Abstract method to check if the game has ended.
     * This method is implemented by HumanPlayer and BotPlayer
     *
     * @return true if the game has ended, false otherwise
     */
    public abstract boolean isGameEnded();

    /**
     * Determines the starting position of the player randomly
     * 
     * @param board  the board in which the player is playing on
     * @param player the player who is being placed
     */
    public abstract void randomStartingPosition(Board board, Player player);
}
