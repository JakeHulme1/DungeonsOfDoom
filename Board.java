import java.io.*

public class Board {
    
    //Fields
    private Tile[][] tiles;
    private int width;
    private int height;

    // Constructor
    public Board(int width, int height) {// User will need to knowboard dimensions beforereading it in - need some defensive programming here
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    // Load the map into the tiles object
    public void loadMap(String fileName) {

        // Use try-with-resources when reading files in Java so file is auto closed at end of try block
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Store each line 
            String line; 
            // Counter to ensure amount of lines doesn't exceed the 'height'
            int heightCounter = 0;

            // While the line isn't emptyand we haven't exceeded 'height', populate the 'tiles' object
            while (line = br.readLine() != null && y < height) {
                for (int x = 0; x < line.length() && x < width; x++) {
                    char tileChar = line.charAt(x);
                    tiles[x][y] = createTileFromChar(tileChar);
                }
            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }
    } 

    // Use switch caseshere - find reference
    privateTile createTileFromChar(char tileChar) {

    }

    // Methods
    public void initialiseBoard(String filePath) {}
    public Tile getTile(int xCoordinate, int yCoordinate) {}
    public void updateTile(int xCoordinate, int yCoordinate, Tile tile) {}
    public void displayBoard() {}
}
