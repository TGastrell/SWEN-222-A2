import java.util.Scanner;

/**
 * View class for sword and shield game
 * @author Tim Gastrell
 *
 */
public class TextInterface {
	
	/**
	 * Textual representation of game board 
	 */
	private static String[][] textBoard;
	
	/**
	 * Function to add a specific square to the text board
	 * 
	 * @param square Square to be added to text board
	 * @param x X pos
	 * @param y Y pos
	 */
	private static void printSquare(GridSquare square, int x, int y) {
		GridSquare.Type type = square.getType();
		String character = "";
		
		if(type.toString().length() != 1) {
			switch(type) {
			case EMPTY :
				character = " ";
				break;
			case VERTICAL_SWORD :
				character = "|";
				break;
			case HORIZONTAL_SWORD :
				character = "-";
				break;
			case SHIELD :
				character = "#";
				break;
			case CREATION_TILE :
				character = "_";
				break;
			case OUT_OF_BOUNDS :
				character = "X";
				break;
			case PLAYER_ONE_TILE :
				character = "0";
				break;
			case PLAYER_TWO_TILE :
				character = "1";
				break;
			}
		}
		else {
			character = type.toString();
		}
		textBoard[x][y] = character;
	}
	
	/**
	 * Function  to print the board in it's current state
	 * 
	 * @param game Current game instance
	 */
	private static void printBoard(SwordAndShieldGame game) {
		//print baord
		for(int i = 0; i < game.getBoard().getWidth(); i++) {
			for(int j = 0; j < game.getBoard().getHeight(); j++) {
				printSquare(game.getBoard().getSquare(i, j), i, j);		
			}
		}
		
		//print bag one
		for(int i = game.getBoard().getWidth(), a = 0; i < game.getBoard().getWidth()+15; i++, a++) {
			for(int j = 0 , b = 0; j < 15; j++, b++) {
				printSquare(game.getBag1().getBoard().getSquare(a, b), i, j);	
			}
		}
		
		//print bag two
		for(int i = game.getBoard().getWidth(), a = 0; i < game.getBoard().getWidth()+15; i++, a++) {
			for(int j = 15, b = 0; j < game.getBoard().getHeight(); j++, b++) {
				printSquare(game.getBag2().getBoard().getSquare(a, b), i, j);	
			}
		}
		
		
		for(int i = 0; i < game.getBoard().getWidth()+2; i++) {
			System.out.print("==");
		}
		System.out.print("= AVAILABLE TILES  p1(a) p2(A) =");
		
		System.out.println("");
		
		for(int i = 0; i < game.getBoard().getHeight(); i++) {
			System.out.print("||");
			for(int j = 0; j < game.getBoard().getWidth(); j++) {
				System.out.printf("%s ", textBoard[j][i]);
			}
			System.out.print("||");
			for(int k = 0; k < 15; k++) {
				System.out.printf("%s ", textBoard[game.getBoard().getWidth() + k][i]);
			}
			System.out.print("||");
			System.out.println("");
		}
		
		for(int i = 0; i < game.getBoard().getWidth()+18; i++) {
			System.out.print("==");
		}
		System.out.println("");
	
	}
	
	/**
	 * Function to process the modifying stage of a player's turn.
	 * Players may move, rotate(unimplemented), undo(not successfully implemented)
	 * or pass to end thier turn
	 * 
	 * @param player Player who'se turn it is
	 * @param game Current instance of the game
	 */
	private static void modificationPhase(int player, SwordAndShieldGame game) {
		//Instructions
		System.out.println("\nPlayer "+player+": You may move or rotate any number of your pieces once"
				+ "\nType pass to end your turn");
		
		Scanner scan = new Scanner(System.in);
		String command = scan.next();
		
		//Check for Pass
		if(command.equalsIgnoreCase("pass")) {
			printBoard(game);
			return;
		}
		
		//Check for undo
		if(command.equalsIgnoreCase("undo")) {
			try {
				game.doUndo();
			} catch (InvalidMove e) {
				System.out.println("Invalid move, please try again");
			}
			printBoard(game);
			modificationPhase(player, game);
			return;
		}
		
		//Proceed with modification
		String letter = scan.next();
		String argument = scan.next(); //Direction
		
		//Correct mis-casing if neseccary 
		if(player == 1) {letter = letter.toLowerCase();}
		if(player == 2 ) {letter = letter.toUpperCase();}
		
		if(command.equalsIgnoreCase("move")) {
			//Check piece has not already been modified
			if(game.getPiecesUsed().contains(GridSquare.Type.valueOf(letter))) {
				System.out.println("You may only modify each piece once per turn");
				modificationPhase(player, game);
				return;
			}
			//Process modification
			try {
				game.move(GridSquare.Type.valueOf(letter), SwordAndShieldGame.Dir.valueOf(argument.toUpperCase()));
			} catch (InvalidMove | IllegalArgumentException e) {
				System.out.println("Invalid move, please try again");
				modificationPhase(player, game);
			}
		}
		else {
			System.out.println("Unrecognised input: Please try again.");
			modificationPhase(player, game);
		}
		
		printBoard(game);
		//allow player to modify another piece
		modificationPhase(player, game);
	}
	
	
	/**
	 * Function for the creation stage of a player's turn.
	 * Players may create one piece or pass to skip this stage.
	 * Players may also undo (not successfully implemented)
	 * 
	 * @param player Player whos turn it is
	 * @param game Current instance of game
	 */
	private static void creationPhase(int player, SwordAndShieldGame game) {
		//Instructions
		System.out.println("\nPlayer "+player+": You may create a piece "
				+ "\nAlternatively you may pass to skip the creation phase.");
		
		Scanner scan = new Scanner(System.in);
		String command = scan.next();
		
		//Check Pass
		if(command.equalsIgnoreCase("pass")) {
			printBoard(game);
			return;
		}
		
		//Check undo
		if(command.equalsIgnoreCase("undo")) {
			try {
				game.doUndo();
			} catch (InvalidMove e) {
				System.out.println("Invalid move, please try again");
			}
			printBoard(game);
			creationPhase(player, game);
			return;
		}
		
		//Proceed with creation
		String letter = scan.next();
		//correct mis-casing
		if(player == 1) {letter = letter.toLowerCase();}
		if(player == 2 ) {letter = letter.toUpperCase();}
		
		//Process creation
		if(command.equalsIgnoreCase("create")) {
			try {
				game.create(GridSquare.Type.valueOf(letter), player);
			} catch (InvalidMove | IllegalArgumentException e) {
				System.out.println("Invalid move, please try again");
				creationPhase(player, game);
			}
		}
		else {
			System.out.println("Unrecognised input: Please try again.");
			creationPhase(player, game);
		}
		printBoard(game);
	}
	
	
	
	/**
	 * Function to run the gameplay itself. 
	 *  
	 * @param game Current instance of game
	 */
	private static void playGame(SwordAndShieldGame game) {
		textBoard = new String[game.getBoard().getWidth()+30][game.getBoard().getHeight()];
		int playerTurn = 1;
		printBoard(game);
		
		//Temporary victory condition until reactions are implemented
		while(game.checkForWinner() == 0) {
			
			game.newTurn();
			creationPhase(playerTurn, game);
			modificationPhase(playerTurn, game);
			
			if(playerTurn == 1) {
				playerTurn = 2;
			}
			else if(playerTurn == 2) {
				playerTurn = 1;
			}

		}
		System.out.println("Player "+game.checkForWinner()+" wins!");
	}
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		playGame(new SwordAndShieldGame(30,30));
	}
}
