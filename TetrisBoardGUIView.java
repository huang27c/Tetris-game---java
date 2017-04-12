import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;
/**
* TetrisBoardGUIView creates a view of a TetrisBoard and pieces.
* 
* @author Ching Ching Huang
*
*/
public class TetrisBoardGUIView extends JComponent {

	private TetrisBoard board;
	private TetrisPiece piece;
	protected JPanel panel;
	private int boardWidth;
	private int boardHeight;

	/**
	 * Constructor calls the board
	 * 
	 * @param TetrisBoard
	 */
	public TetrisBoardGUIView(TetrisBoard b) {
		board = b;
	}

	/**
	 * Paint the game.
	 */
	public void paint(Graphics g) {
		piece = board.getCurrentPiece(); //the board
		int rot = piece.getPieceRotation(); //the piece

		// the calculation for converting the piece to the board
		int positions[] = board.getCurrentPieceGridPosition();
		int leftX = positions[0]; // get the left top x location of the piece grid
		int rightX = leftX + 3; // get the right top x location of the piece grid
		int topY = positions[1]; // get the top left y location of the piece grid
		int bottomY = positions[1] + 3; // get the bottom left y location

		for (int i = 0; i < board.blockMatrix.length; i++) {
			for (int j = 0; j < board.blockMatrix[i].length; j++) {
				if (board.blockMatrix[i][j] == true) {
					// if the board has landed tetris piece, print out a pink
					// outline box
					piantTetris(g, j * computeBlockSize(), i
							* computeBlockSize());
				} else if (i >= leftX && i <= rightX && j >= topY
						&& j <= bottomY) {
					if (piece.isFilled(rot, i - leftX, j - topY) == true) {
						// check the grid of tetris.
						// if it's true, print out a pink box.
						piantTetris(g, j * computeBlockSize(), i
								* computeBlockSize());
					}
				}
			}
		}
		paintBoardOutline(g, computeBlockSize()); //print the outline 
	}

	/**
	 * paint the tetris piece with blocks
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */
	private void piantTetris(Graphics g, int x, int y) {
		g.setColor(Color.WHITE);// set the block to white
		g.fillRect(x, y, computeBlockSize(), computeBlockSize()); // paint the
																	// block
		g.setColor(Color.BLACK);// set the outline color to black
		g.drawRect(x, y, computeBlockSize(), computeBlockSize()); // paint the
																	// outline
	}

	/**
	 * set each piece different this method is not using
	 * 
	 * @param g
	 */
	private void paintPiece(Graphics g) {
		if (board.getCurrentPiece() == new TetrisI()) {
			g.setColor(Color.BLUE);
		} else if (board.getCurrentPiece() == new TetrisJ()) {
			g.setColor(Color.RED);
		} else if (board.getCurrentPiece() == new TetrisL()) {
			g.setColor(Color.YELLOW);
		} else if (board.getCurrentPiece() == new TetrisS()) {
			g.setColor(Color.ORANGE);
		} else if (board.getCurrentPiece() == new TetrisSquare()) {
			g.setColor(Color.MAGENTA);
		} else if (board.getCurrentPiece() == new TetrisT()) {
			g.setColor(Color.GREEN);
		} else if (board.getCurrentPiece() == new TetrisZ()) {
			g.setColor(Color.PINK);
		}
	}

	/**
	 * Compute the best block size for the current width and height.
	 * 
	 * @return the blockSize
	 */
	private int computeBlockSize() {
		int blockSize = 0;
		boardWidth = this.getWidth() * 9 / 10;
		boardHeight = (boardWidth / board.getNumCols()) * board.getNumRows();
		blockSize = boardWidth / board.getNumCols();
		return blockSize;
	}

	/**
	 * paint the outline of the board
	 * 
	 * @param graphics
	 * @param blockSize
	 */
	private void paintBoardOutline(Graphics g, int blockSize) {
		// print out the outer rect
		int boardWidth = computeBlockSize() * 10;
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, boardWidth, boardHeight);
	}

}