import java.util.ArrayList;

/**
 * Model for Sword and Shield Game.
 * @author Tim Gastrell
 *
 */
public class SwordAndShieldGame {
	
	/**
	 * List of dead tiles
	 */
	private GameBag cemetery = new GameBag(new ArrayList<PieceTile>(), 15, 30);
	
	/**
	 * Objects representing the pieces available to each player
	 */
	private GameBag bag1, bag2;
	
	/**
	 * Object representing the game board
	 */
	private Board board;
	
	/**
	 * 
	 */
	private ArrayList<PieceTile> piecesInPlay = new ArrayList<PieceTile>();
	
	/**
	 * Object facilitating the undo fucntion
	 */
	private Undo undo;
	
	/**
	 * List of pieces modified/used in a turn, renewed every turn
	 */
	private ArrayList<GridSquare.Type> piecesUsed = new ArrayList<GridSquare.Type>();
	
	/**
	 * Directions of movement
	 * 
	 * @author Tim Gastrell
	 *
	 */
	public enum Dir {
		UP,
		DOWN,
		LEFT,
		RIGHT;
	}
	
	/**
	 * Initialise game
	 * @param x board width
	 * @param y board height
	 */
	public SwordAndShieldGame(int x, int y) {
		GameInitialiser newGame = new GameInitialiser(new Board(x, y), new ArrayList<PieceTile>(), new ArrayList<PieceTile>());
		this.bag1 = new GameBag(newGame.getBag1(), 15, 15);
		this.bag2 = new GameBag(newGame.getBag2(), 15, 15);
		this.board = newGame.getBoard();
		this.undo = new Undo();
		cemetery.updateBoard();
	}
	
	/**
	 * Fucntion to move a piece that is deployed on the board.
	 * Moves piece one position in the specified direction.
	 * @param id piece to move
	 * @param dir direction to move piece
	 * @throws InvalidMove
	 */
	public void move(GridSquare.Type id, Dir dir) throws InvalidMove {
		PieceTile piece = board.findPieceWithId(id);
		if(piece == null) {
			throw new InvalidMove("");
		}
		GridSquare.Type adjacent = null;
		int x = piece.getX(), y = piece.getY();
		int newX = x, newY = y;
		undo.addState(new GameState(this));
		
		//Determine the new coordinates for the piece
		switch(dir) {
		case UP :
			adjacent = board.getSquare(piece.getX()+1, piece.getY()-2).getType();
			newY -= 3;
			break;
		case DOWN :
			adjacent = board.getSquare(piece.getX()+1, piece.getY()+4).getType(); 
			newY += 3;
			break;
		case LEFT :
			adjacent = board.getSquare(piece.getX()-2, piece.getY()+1).getType();
			newX -= 3;
			break;
		case RIGHT :
			adjacent = board.getSquare(piece.getX()+4, piece.getY()+1).getType();
			newX += 3;
			break;
		}
		
		//Case: Piece moves out of bounds
		if(adjacent == GridSquare.Type.OUT_OF_BOUNDS || adjacent == GridSquare.Type.PLAYER_ONE_TILE || adjacent == GridSquare.Type.PLAYER_TWO_TILE) {
			cemetery.getBag().add(piece);
			cemetery.updateBoard();
			piecesInPlay.remove(piece);
			piece.setPos(-1, -1);
			board.fillSpace(GridSquare.Type.EMPTY, x, y);
		}
		else if(adjacent == GridSquare.Type.EMPTY || adjacent == GridSquare.Type.CREATION_TILE) { //Case: Piece moves to an empty or creation tile
			
			board.setTile(piece, newX, newY);
			//remove tile data from old position
			if(x == Board.PLAYER_ONE_POSITION_X+3 && y == Board.PLAYER_ONE_POSITION_Y+3) {
				board.fillSpace(GridSquare.Type.CREATION_TILE, x, y); //for moving off the creation tiles
			}
			else if(x == Board.PLAYER_TWO_POSITION_X-3 && y == Board.PLAYER_TWO_POSITION_Y-3) {
				board.fillSpace(GridSquare.Type.CREATION_TILE, x, y);
			}
			else {
				board.fillSpace(GridSquare.Type.EMPTY, x, y);
			}
			piecesUsed.add(id);
		}
		else { //Case: Piece moves into an already occupied area.
			//move piece in the way
			move(adjacent, dir);
			//re-move piece
			move(id, dir);
		}
	}
	
	/**
	 * Function to draw a piece from the available pieces and place it on the creation tile.
	 * Places the specified piece on the creation tile of the specified player.
	 * @param id Piece to be created
	 * @param player Player creating the piece
	 * @throws InvalidMove	
	 */
	public void create(GridSquare.Type id, int player) throws InvalidMove {	
		undo.addState(new GameState(this));
		PieceTile piece = getTileFromBag(id, player);
		
		if(piece == null) {
			 throw new InvalidMove("You cannot have duplicate pieces in play or use another players piece.");
		}
		
		if(player == 1) {
			if(board.getSquare(Board.PLAYER_ONE_POSITION_X+4, Board.PLAYER_ONE_POSITION_Y+4).getType() == GridSquare.Type.CREATION_TILE) { //Player one creation, only if creation tile is free
				board.setTile(piece, Board.PLAYER_ONE_POSITION_X+3, Board.PLAYER_ONE_POSITION_Y+3);
				piecesInPlay.add(piece);
			}
			else {
				throw new InvalidMove("You must vacate the creation tile before creating a new piece.");
			}
		}
		else {
			if(board.getSquare(Board.PLAYER_TWO_POSITION_X-2, Board.PLAYER_TWO_POSITION_Y-2).getType() == GridSquare.Type.CREATION_TILE) { //Player two creation, only if creation tile is free
				board.setTile(piece, Board.PLAYER_TWO_POSITION_X-3, Board.PLAYER_TWO_POSITION_Y-3);
				piecesInPlay.add(piece);
			}
			else {
				throw new InvalidMove("You must vacate the creation tile before creating a new piece.");
			}
		}
	}
	
	/**
	 * Victory condition for game pre-reaction implementation. 
	 * Checks for a sword ajacent to either player tile.
	 * @return the winning player number, else 0
	 */
	public int checkForWinner() {
		if(board.getSquare(Board.PLAYER_ONE_POSITION_X+3, Board.PLAYER_ONE_POSITION_Y+1).getType() == GridSquare.Type.HORIZONTAL_SWORD) {
			return 2;
		}
		else if(board.getSquare(Board.PLAYER_ONE_POSITION_X+1, Board.PLAYER_ONE_POSITION_Y+3).getType() == GridSquare.Type.VERTICAL_SWORD) {
			return 2;
		}
		else if(board.getSquare(Board.PLAYER_TWO_POSITION_X-1, Board.PLAYER_TWO_POSITION_Y+1).getType() == GridSquare.Type.HORIZONTAL_SWORD) {
			return 1;
		}
		else if(board.getSquare(Board.PLAYER_TWO_POSITION_X+1, Board.PLAYER_TWO_POSITION_Y-1).getType() == GridSquare.Type.VERTICAL_SWORD) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * Undo function.
	 * Resets the game state to the previous game state by changing the board, bags, pieces used, and cemetery.
	 * @throws InvalidMove
	 */
	public void doUndo() throws InvalidMove {
		GameState oldState = undo.getPreviousState();
		board = oldState.getBoard();
		bag1 = oldState.getBag1();
		bag2 = oldState.getBag2();
		cemetery = oldState.getCemetery();
		piecesUsed = oldState.getPiecesUsed();
	}

	/**
	 * Retreieves the specified piece from the specified player's bag.
	 * @param id Piece to retreive 
	 * @param player Player retreiving piece
	 * @return specified piece, if piece not in bag returns null.
	 */
	private PieceTile getTileFromBag(GridSquare.Type id, int player) {
		if(player == 1) {
			for(PieceTile p : bag1.getBag()) { //search player ones bag
				if(p.getId().getType() == id) {
					bag1.getBag().remove(p);
					bag1.updateBoard();
					return p;
				}
			}
		} else if (player == 2){
				for(PieceTile p : bag2.getBag()) { //player twos
					if(p.getId().getType() == id) {
						bag2.getBag().remove(p);
						bag2.updateBoard();
						return p;
					}
			}
		}
		return null;
	}
	
	/**
	 * Resets Undo and PiecesUsed 
	 */
	public void newTurn() {
		setPiecesUsed(new ArrayList<GridSquare.Type>());
		undo = new Undo();
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	public Board getBoard() {
		return board;
	}
	
	public GameBag getCemetery() {
		return cemetery;
	}
	
	public GameBag getBag1() {
		return bag1;
	}

	public GameBag getBag2() {
		return bag2;
	}

	public Undo getUndo() {
		return undo;
	}

	public ArrayList<GridSquare.Type> getPiecesUsed() {
		return piecesUsed;
	}

	public void setPiecesUsed(ArrayList<GridSquare.Type> piecesUsed) {
		this.piecesUsed = piecesUsed;
	}
	
	public ArrayList<PieceTile> getPiecesInPlay(){
		return piecesInPlay;
	}
	
	
}
