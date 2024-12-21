import java.util.Random; // Used for generation of random starting position

/**
 * This class contains all the functionality of a Bot Player which autonomously
 * searches for the human player.
 */
public class BotPlayer extends Player {

    /**
     * Constructs a new BotPlayer with a random starting position on the board.
     *
     * @param board the board which the bot is playing on
     */
    public BotPlayer(Board board) {
        super(board);
        randomStartingPosition(board, this);
    }

    /**
     * Moves the bot in the specified direction.
     *
     * @param direction the direction in which to move the bot
     */
    public void move(Direction direction) {

        // Current coordinates
        int newX = getXCoordinate();
        int newY = getYCoordinate();

        // Calculate new coordinates based on direction
        switch (direction) {
            case N:
                newY -= 1;
                break;
            case E:
                newX += 1;
                break;
            case S:
                newY += 1;
                break;
            case W:
                newX -= 1;
                break;
        }

        // Check the new position is not out of bounds
        if (isValidCoordinate(newX, newY)) {

            // Store info about the newtile using new coordinates
            Tile targetTile = getBoard().getTile(newX, newY);

            // Check the new tile is not a wall
            if (targetTile.getType() != TileType.WALL) {

                // Restore the original tile type of the current position
                Tile currentTile = getBoard().getTile(getXCoordinate(), getYCoordinate());
                currentTile.setType(getOriginalTileType());

                // Update bot's position
                setXCoordinate(newX);
                setYCoordinate(newY);

                // Store the original type of the target tile
                setOriginalTileType(targetTile.getType());

                // Set the new position to BOT
                targetTile.setType(TileType.BOT);

                System.out.println("Bot moved " + direction);
            } else {
                System.out.println("Bot move failed. There's a wall in the way!");
            }
        } else {
            System.out.println("Bot move failed. Out of bounds!");
        }
    }

    public void pickUpGold() {
        // Bot does not pick up gold
    }

    public void quit() {
        // Bot cannot quit game
    }

    /**
     * Determines the direction in which the bot should move to get closer to the
     * humanplayer
     * 
     * @param humanPlayer the human player to search for
     * @return the direction in which the bot should move
     */
    public Direction determineNextMove(Player humanPlayer) {

        int botX = getXCoordinate();
        int botY = getYCoordinate();
        int humanX = humanPlayer.getXCoordinate();
        int humanY = humanPlayer.getYCoordinate();

        // Determine the move to move towards to human player
        if (botX < humanX) {
            return Direction.E;
        } else if (botX > humanX) {
            return Direction.W;
        } else if (botY < humanY) {
            return Direction.S;
        } else if (botY > humanY) {
            return Direction.N;
        }

        // If bot is already at the human player's position, return null
        return null;
    }

    /**
     * The bot can't end the game so 'gameEnded' will always be false
     */
    public boolean isGameEnded() {
        return false;
    }

    /**
     * Randomly places a player on an empty tile on the board.
     *
     * @param board the game board
     * @param bot   the bot to be placed
     */
    public void randomStartingPosition(Board board, Player bot) {

        Random random = new Random();
        boolean validCoordinates = false;

        // Constantly generate random coordinates until its an empty space
        do {
            int randomX = random.nextInt((board.getWidth()));
            int randomY = random.nextInt(board.getHeight());

            // Check if the random coordinatesare on an empty tile
            Tile tile = board.getTile(randomX, randomY);
            if (tile.getType() == TileType.EMPTY) {
                // Place bot on the empty tile
                bot.setXCoordinate(randomX);
                bot.setYCoordinate(randomY);
                board.getTile(randomX, randomY).setType(TileType.BOT);

                // Exit loop
                validCoordinates = true;
            }
        } while (!validCoordinates);
    }

    /**
     * Checks if the coordinates are within range of the height and width and >= 0.
     * It is private because it's an internal helper method.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the coordinates are valid, false otherwise
     */
    private boolean isValidCoordinate(int x, int y) {
        Board board = getBoard();
        return x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight();
    }
}
