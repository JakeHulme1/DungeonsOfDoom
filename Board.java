import java.io.*;

public class Board {

    // Fields
    private Tile[][] tiles;
    private int width;
    private int height;
    private int goldToWin;

    // Constructor
    public Board(int width, int height) {// User will need to knowboard dimensions beforereading it in - need some
                                         // defensive programming here
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    // Mutators
    public void setGoldToWin(int goldToWin) {
        this.goldToWin = goldToWin;
    }

    // Accessors
    public int getGoldToWin() {
        return this.goldToWin;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Tile getTile(int xCoordinate, int yCoordinate) {
        return tiles[xCoordinate][yCoordinate];
    }

    // Load the map into the tiles object
    public void loadMap(String directoryPath, String fileName) {

        File file = new File(directoryPath, fileName);

        // Use try-with-resources when reading files in Java so file is auto closed at
        // end of try block
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Read the first line
            String line = br.readLine();

            // Read the second line to get amount of gold required to win
            line = br.readLine();
            if (line != null && line.startsWith("win")) {
                // Following the space is the number of gold required to win, use mutator to set
                // goldToWin
                setGoldToWin(Integer.parseInt(line.split(" ")[1]));
            }

            // Read the rest of the map
            // y counts the number of rows
            int y = 0;

            // While the line isn't emptyand we haven't exceeded 'height', populate the
            // 'tiles' object
            while ((line = br.readLine()) != null && y < height) {
                for (int x = 0; x < line.length() && x < width; x++) {
                    char tileChar = line.charAt(x);
                    tiles[x][y] = createTileFromChar(tileChar);
                }
                y++; // Move to next row
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when trying to load the file :/");
        }
    }

    // Creates a tile from the inputted character, linking it to TileType object.
    // Private because it is an internal'helper function' for the public loadMap
    // function - encapsulates functionality
    private Tile createTileFromChar(char tileChar) {

        // Used switch statements as this is much more concise than if statements
        switch (tileChar) {
            case '#':
                return new Tile(TileType.WALL);
            case '.':
                return new Tile(TileType.EMPTY);
            case 'G':
                return new Tile(TileType.GOLD);
            case 'E':
                return new Tile(TileType.EXIT);
            case 'P':
                return new Tile(TileType.PLAYER);
            case 'B':
                return new Tile(TileType.BOT);
            default:
                return new Tile(TileType.EMPTY);
        }
    }

    // Choose the corresponding character for a tile when passed its TileType
    private char getCharFromTileType(TileType type) {
        switch (type) {
            case WALL:
                return '#';
            case EMPTY:
                return '.';
            case GOLD:
                return 'G';
            case EXIT:
                return 'E';
            case PLAYER:
                return 'P';
            case BOT:
                return 'B';
            default:
                return ' ';
        }
    }

    // Print out the board - used in LOOK command
    public void displayBoard(Player player) {

        // Get coordinates of player's current position
        int playerX = player.getXCoordinate();
        int playerY = player.getYCoordinate();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // If on player's current poisition, display 'P'
                if (x == playerX && y == playerY) {
                    System.out.print('P' + " ");
                } else {
                    System.out.print(getCharFromTileType(tiles[x][y].getType()) + " ");
                }
            }
            System.out.println();
        }
    }
}