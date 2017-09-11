/**
 * Class represents the game board.
 * 
 * @author Tim Gastrell
 *
 */
public class Board {
	
	/**
	 * The board
	 */
	private GridSquare[][] boardGrid;
	
	/**
	 * Board dimensions
	 */
	private int width, height;
	
	/**
	 * Constant positions of player tiles for reference
	 */
	public static final int PLAYER_ONE_POSITION_X = 3, PLAYER_ONE_POSITION_Y = 3,
			PLAYER_TWO_POSITION_X = 24, PLAYER_TWO_POSITION_Y = 24;
	
	/**
	 * Constructs a new board with the specified dimensions
	 * @param x width
	 * @param y height
	 */
	public Board(int x, int y) {
		this.width = x;
		this.height = y;
		boardGrid = new GridSquare[width][height];
	}
	
	/**
	 * Returns a piece on the board with the specified id
	 * @param id Identifying letter
	 * @return Piece with id
	 */
	public PieceTile findPieceWithId(GridSquare.Type id) {
		for(int i  = 0; i < width; i++) {
			for(int j = 0; j < height; j++) { //Search board for id
				GridSquare square = getSquare(i, j);
				if(square.getType() == id) {
					return (PieceTile) square.getTile();
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets a specified square
	 * 
	 * @param x X position
	 * @param y Y position
	 * @return  returns the square at the specifiied position
	 */
	public GridSquare getSquare(int x, int y) {
		if(x <= width && x >= 0 && y <= height && y >= 0) {
			return boardGrid[x][y];
		}
		return new GridSquare(GridSquare.Type.OUT_OF_BOUNDS);
	}
	
	/**
	 * Sets the specified board position to the specified square
	 * @param g Square to be placed
	 * @param x X position
	 * @param y Y position
	 */
	public void setSquare(GridSquare g, int x, int y) {
		boardGrid[x][y] = g;
	}
	
	/**
	 * Function for placing pieces on the board.
	 * 
	 * @param piece Piece to be placed
	 * @param x X pos
	 * @param y Y pos
	 */
	public void setTile(PieceTile piece, int x, int y) {
		//Sets each item and the letter in their correct positions
		setSquare(piece.getItems()[0], x+1,y);
		setSquare(piece.getItems()[1], x+2, y+1);
		setSquare(piece.getItems()[2], x+1, y+2);
		setSquare(piece.getItems()[3], x, y+1);
		setSquare(piece.getId(), x+1, y+1);
		piece.setPos(x, y);
		
	}
	
	/**
	 * Function to refill a recently vacated space with the specified tile type
	 * 
	 * @param type Type to fill space with
	 * @param x X pos
	 * @param y Y pos
	 */
	public void fillSpace(GridSquare.Type type, int x, int y) {
		for(int i = x; i <= x+2; i++) {
			for(int j = y; j <= y+2; j++) {
				setSquare(new GridSquare(type), i, j);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
