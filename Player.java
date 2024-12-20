public abstract class Player {

    // Fields
    private int xCoordinate;
    private int yCoordinate;
    private int gold = 0;
    private Board board;
    private TileType previousTileType = TileType.EMPTY;

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

    // Methods:
    public abstract void pickUpGold();

    public abstract void move(Direction direction); // Implementation in HumanPlayer and BotPlayer,inherited from here

    public abstract String quit();

    public abstract boolean isGameEnded();
}
