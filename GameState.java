import java.util.ArrayList;
import java.util.Set;
/**
 * Object storing a Board, two bags, the cemetery and pieces used from a specific game state
 * Utilised in undo functionality (not successfully implemented)
 * @author Tim Gastrell
 *
 */
public class GameState {
	
	/**
	 * Game board
	 */
	private Board board;
	
	/**
	 * Players bags of unused pieces
	 */
	private GameBag bag1, bag2;
	
	/**
	 * Cemetery of dead pieces
	 */
	private GameBag cemetery;
	
	/**
	 * Pieces used in an individual turn
	 */
	private ArrayList<GridSquare.Type> piecesUsed;
	
	/**
	 * Constructs new GameState Object
	 * @param game current game state
	 */
	public GameState(SwordAndShieldGame game) {
		this.board = game.getBoard();
		this.bag1 = game.getBag1();
		this.bag2 = game.getBag2();
		this.cemetery = game.getCemetery();
		this.piecesUsed = game.getPiecesUsed();
	}

	public Board getBoard() {
		return board;
	}

	public GameBag getBag1() {
		return bag1;
	}

	public GameBag getBag2() {
		return bag2;
	}

	public GameBag getCemetery() {
		return cemetery;
	}
	
	public ArrayList<GridSquare.Type> getPiecesUsed() {
		return piecesUsed;
	}
	
}
