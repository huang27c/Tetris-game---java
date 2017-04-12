import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TetrisGameTextController plays Tetris from the command line, printing the
 * game after each move.
 * 
 * @author Ching Ching Huang
 *
 */
public class TetrisGameTextController {

	private TetrisGame game;
	private TetrisBoardTextView view;

	/*
	 * constructor initializes the tetris game and text view also updates the
	 * board
	 */
	public TetrisGameTextController() {
		game = new TetrisGame();
		view = new TetrisBoardTextView(game.getTetrisBoard());
		view.printBoard();

		playGame();
	}

	/*
	 * Receiving the player's key input then update the board
	 */
	private void playGame() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String key = " "; // the input of the player
		try {
			// Try to read a line
			key = in.readLine();

			// Loop until the user types "quit"
			while (!key.toLowerCase().equals("quit")) {

				// If the user types "quit", exit the game
				if (key.toLowerCase().equals("quit")) {
					System.exit(0);

				} else {
					// Otherwise, check to make sure player enter valid keys
					try {
						char input = key.charAt(0);// only read the first
													// character
						if (input == 'l') {
							// left
							game.attemptMove(game.LEFT);
							view.printBoard();
						} else if (input == 'r') {
							// right
							game.attemptMove(game.RIGHT);
							view.printBoard();
						} else if (input == 'd') {
							// down
							game.attemptMove(game.DOWN);
							view.printBoard();
						} else if (input == 'z') {
							// rotate CW
							game.attemptMove(game.CW);
							view.printBoard();
						} else if (input == 'x') {
							// rotate CCW
							game.attemptMove(game.CCW);
							view.printBoard();
						} else {
							// otherwise, tell the player that's a wrong input
							System.out.println("Wrong!!!");
						}
					} catch (StringIndexOutOfBoundsException stringOutOfBound) {
						System.out.println("Plz enter something");
					}
				}
				// Ask for the next input
				key = in.readLine();
			}
		}
		// catch I/O exception
		catch (IOException ioenfe) {
			// inform user of problem
			System.out.println("Error: IOException. ");
		}

	}

}