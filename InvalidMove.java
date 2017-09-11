/**
 * Invalid move exeption thrown when an illegal move 
 * is played.
 * @author Tim Gastrell
 *
 */
@SuppressWarnings("serial")
public class InvalidMove extends Exception {
	public InvalidMove(String e) {
		super(e);
	}
}
