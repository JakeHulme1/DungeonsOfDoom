public class BotPlayer extends Player {

    // Fileds inherited from Player

    // Constructor
    public BotPlayer(int startX, int startY, Board board) {
        super(startX, startY, board);
    }

    // Methods
    public void move(Direction direction) {
    }

    public String pickUpGold() {
        return "Test";
    }

    public String quit() {
        return "Test";
    }

    public String determineNextMove() {
        return "Test";
    } // This may be a tricky one

    public boolean isGameEnded() {
        return false;
    }
}
