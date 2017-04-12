import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * TetrisGameGUIController plays Tetris with keys, printing the
 * game after each move.
 * 
 * @author Ching Ching Huang
 *
 */

public class TetrisGameGUIController extends JPanel implements KeyListener,
		ActionListener {

	private TetrisGame game;
	private static int DEFAULT_DROP_RATE = 2000; //default drop rate
	private JLabel linesLabel, tetrisesLabel, levelLabel; //labels for texts
	private JPanel scorePanel; //panel for the all labels
	private Timer gameTimer; //the timer
	private int dropRate = 2000; //the drop rate

	/**
	 * constructor initialize the tetris game
	 */
	public TetrisGameGUIController() {
		// use a BorderLayout
		super(new BorderLayout());

		addKeyListener(this);

		game = new TetrisGame();
		printScore();// print the score to the frame
		setFocusable(true);
		setupTimer(); // start the animation of the piece
	}

	/**
	 * create a timer for the piece
	 */
	private void setupTimer() {
		gameTimer = new Timer(dropRate, this); 
		gameTimer.setInitialDelay(DEFAULT_DROP_RATE); //set the initial drop rate to default value
		gameTimer.start(); //start the animation
	}

	/**
	 * start the animation of the piece
	 */
	public void actionPerformed(ActionEvent e) {
		game.attemptMove(TetrisGame.DOWN);//move the piece down
		repaint(); //update for each drop
	}

	/**
	 * add the score to frame
	 */
	public void printScore() {
		infoPanel();
		add(scorePanel, BorderLayout.NORTH);
		add(new TetrisBoardGUIView(game.getTetrisBoard()), BorderLayout.CENTER);

	}

	/**
	 * create a panel to print out the score
	 */
	private void infoPanel() {
		// the text of the scores
		String scoreText = ("Number of line cleared: " + game.getScore());
		String tetrisesText = ("Number of Tetrises: " + game.getTetrises());
		String theLevel = ("Level: " + game.getLevel());

		// a panel for three scores
		scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(3, 0));

		// create instructions and put at the top
		levelLabel = new JLabel(theLevel);
		linesLabel = new JLabel(scoreText);
		tetrisesLabel = new JLabel(tetrisesText);

		// add the labels to the panel
		scorePanel.add(levelLabel);
		scorePanel.add(linesLabel);
		scorePanel.add(tetrisesLabel);
	}

	/**
	 * Assigning keys to play the game
	 */
	public void keyReleased(KeyEvent e) {
		// get the input
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_DOWN) {
			game.attemptMove(TetrisGame.DOWN);
		} else if (key == KeyEvent.VK_RIGHT) {
			game.attemptMove(TetrisGame.RIGHT);
		} else if (key == KeyEvent.VK_LEFT) {
			game.attemptMove(TetrisGame.LEFT);
		} else if (key == KeyEvent.VK_Z) {
			game.attemptMove(TetrisGame.CCW);
		} else if (key == KeyEvent.VK_X) {
			game.attemptMove(TetrisGame.CW);
		} else if (key == KeyEvent.VK_SPACE) {
			game.attemptMove(TetrisGame.SPACE_DROP);
		} else if (key == KeyEvent.VK_UP) {
			game.attemptMove(TetrisGame.CW);
		}
		repaint();// update the board
		updateScore(); // update the score
	}

	/**
	 * update the score for each move
	 */
	private void updateScore() {
		levelLabel.setText("Level: " + game.getLevel());
		linesLabel.setText("Number of line cleared: " + game.getScore());
		tetrisesLabel.setText("Number of Tetrises: " + game.getTetrises());
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

	}
}
