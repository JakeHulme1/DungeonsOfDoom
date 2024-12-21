/**
 * The enum contains all the directions a Player can go in
 * 
 * Using an enum encourages maintainability as developers can simply add more
 * directions in here, and
 * add these to the move() method in HumanPlayer
 */
public enum Direction {
    /**
     * North direction.
     */
    N,

    /**
     * East direction.
     */
    E,

    /**
     * South direction.
     */
    S,

    /**
     * West direction.
     */
    W
}