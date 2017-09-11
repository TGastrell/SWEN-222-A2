import java.util.List;
/**
 * Class representing a player's bag on unused pieces 
 * and the board of unused pieces to be displayed.
 * @author Tim Gastrell
 *
 */
public class GameBag {
	
	/**
	 * Board of unused pieces
	 */
	Board board;
	
	/**
	 * List of piece objects
	 */
	List<PieceTile> bag;
	
	/**
	 * Constructs a new bag and iniitalises the unused piece board
	 * @param bag Set of unused tiles
	 */
	public GameBag(List<PieceTile> bag, int x, int y) {
		this.bag = bag;
		board = new Board(x, y);
		initialise();
		updateBoard();
	}
	
	/**
	 * Updates the unused piece board
	 */
	public void updateBoard() {
		for(int i = 0, k = 0; i < board.getWidth(); i+=3) {
			for(int j = 0; j < board.getHeight(); j+=3) {
				//Check that there are pieces to display
				if(k < bag.size()) {
					board.setTile(bag.get(k), i, j);
					k++;
				}
				else {
					board.fillSpace(GridSquare.Type.EMPTY, i, j);
				}
			}
		}
	}
	
	/**
	 * Initialises the board with empty squares
	 */
	private void initialise() {
		for(int i = 0; i < board.getWidth(); i++) {
			for(int j = 0; j < board.getHeight(); j++) {
				board.setSquare(new GridSquare(GridSquare.Type.EMPTY), i, j);	
			}
		}
	}
	
	public List<PieceTile> getBag() {
		return bag;
	}
	
	public void setBag(List<PieceTile> bag) {
		this.bag = bag;
	}
	
	public Board getBoard() {
		return board;
	}
}
