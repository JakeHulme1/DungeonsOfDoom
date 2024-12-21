public abstract class Player {

    // Fields
    private int xCoordinate;
    private int yCoordinate;
    private int gold = 0;
    private Board board;
    private TileType previousTileType = TileType.EMPTY;
    private TileType originalTileType = TileType.EMPTY;

    // Constructor
    // TOD: Implement random placement of HumanPlayer or BotPlayer, ensuring they
    // start on empty square and noton top of each other
    public Player(int startX, int startY, Board board) {
        this.xCoordinate = startX;
        this.yCoordinate = startY;
        this.gold = 0;
        this.board = board;
    }

    // Accessors
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    public int getGold() {
        return this.gold;
    }

    public Board getBoard() {
        return this.board;
    }

    public TileType getPreviousTileType() {
        return this.previousTileType;
    }

    public TileType getOriginalTileType() {
        return this.originalTileType;
    }

    // Mutators
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPreviousTileType(TileType previousTileType) {
        this.previousTileType = previousTileType;
    }

    public void setOriginalTileType(TileType previousTileType) {
        this.originalTileType = previousTileType;
    }

    // Methods:
    public abstract void pickUpGold();

    public abstract void move(Direction direction); // Implementation in HumanPlayer and BotPlayer,inherited from here

    public abstract void quit();

    public abstract boolean isGameEnded();
}
