import javax.swing.JFrame;

/**
 * This class runs the tetris game 
 * Extra credit: I created space drop 
 * when the user press space bar, the piece will drop to the bottom
 *
 * @author Ching Ching Huang
 *
 */
public class TetrisApplication {

	// the window of the game
	private static JFrame frame;

	/*
	 * main method to start the game
	 */
	public static void main(String[] args) {
		frame = new JFrame("Tetris Game");

		frame.setSize(350, 650); // set the size of the frame
		frame.setResizable(false); // unable to resize the frame
		frame.setLocationRelativeTo(null);
		// default to the middle of the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// shut down the game when the window is closed
		frame.add(new TetrisGameGUIController()); // add the game to the frame
		frame.setVisible(true);
	}
}
