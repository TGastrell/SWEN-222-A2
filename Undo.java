import java.util.Stack;

/**
 * Class used to implement undo functionality, This uses a stack to collect previous game states
 * within an individual turn.
 * @author Tim Gastrell
 *
 */
public class Undo {
	
	/**
	 * Stack of previous game states this turn
	 */
	private Stack<GameState> prevStates;
	
	/**
	 * Creates a new Undo object and initialises the stack
	 */
	public Undo() {
		prevStates = new Stack<GameState>();
	}
	
	/**
	 * Adds a game state to the stack
	 * @param state Game state to be added
	 */
	public void addState(GameState state) {
		prevStates.push(state);
	}
	
	/**
	 * Returns the top element on the stack.
	 * 
	 * @return Previous state
	 * @throws InvalidMove
	 */
	public GameState getPreviousState() throws InvalidMove {
		if(prevStates.size() == 0) {throw new InvalidMove("You may only undo moves performed this turn.");}
		return prevStates.pop();
	}
	
	/**
	 * Returns the stack (for testing purposes
	 * @return Stack of game states
	 */
	public Stack<GameState> getStack() {
		return prevStates;
	}
	
}
