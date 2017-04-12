import java.util.Random;

/**
 * The TetrisBoard class represents the model: a board on which Tetris is
 * played; it maintains the grid (the block matrix) and the current piece.
 *
 * @author Ching Ching Huang
 *
 */
public class TetrisBoard {

	protected boolean[][] blockMatrix; // the board
	protected TetrisPiece currentPiece; // the current piece
	private int[] currentPiecePosition; // the array contains the current
										// location of the piece
	private static int NUM_COLS = 10; // width of the board
	private static int NUM_ROWS = 18; // height of the board
	private int removedLine = 0; // store how many lines are deleted
	private int TetrisesScore = 0; // store how many four-line is deleted at the
									// same time
	private int level = 0;// the level of the game

	/**
	 * Constructor sets up the board.
	 */
	public TetrisBoard() {
		initBoard();
		addNewPiece();
	}

	/**
	 * Initialize the 2D board array to have all false values
	 */
	public void initBoard() {
		// row major
		// initialize the board. default value if false
		blockMatrix = new boolean[NUM_ROWS][NUM_COLS];
	}

	/**
	 * Initialize an int array of length two to keep track of the grid position
	 * of the current piece (row, col)
	 */
	public void initCurrentPiecePosition() {
		// the default location for the piece
		int defaultRowLocation = 0;
		int defaultColLocation = 3;
		// default position
		currentPiecePosition = new int[2];
		currentPiecePosition[0] = defaultRowLocation;
		currentPiecePosition[1] = defaultColLocation;
		checkLost();
	}

	/**
	 * check whether the player lost the game
	 */
	public void checkLost() {
		int rot = currentPiece.getPieceRotation();
		// if the player can't add a new piece, lost the game
		if (detectCollision(currentPiece, rot, currentPiecePosition[0],
				currentPiecePosition[1])) {
			System.out.println("You Lost!!");
			System.exit(0); // exit the whole game when the player lost the game

		}
	}

	/**
	 * add a random new piece to the board
	 */
	public void addNewPiece() {
		// generate random number to create random peice
		Random random = new Random();
		int randomPiece = random.nextInt(7); // 0-6
		// create random piece
		if (randomPiece == 0) {
			currentPiece = new TetrisI();
		} else if (randomPiece == 1) {
			currentPiece = new TetrisJ();
		} else if (randomPiece == 2) {
			currentPiece = new TetrisL();
		} else if (randomPiece == 3) {
			currentPiece = new TetrisS();
		} else if (randomPiece == 4) {
			currentPiece = new TetrisSquare();
		} else if (randomPiece == 5) {
			currentPiece = new TetrisT();
		} else if (randomPiece == 6) {
			currentPiece = new TetrisZ();
		}

		// add a random new piece to the default position
		initCurrentPiecePosition();
	}

	/**
	 * Check if moving right is valid. If so, move the current piece right.
	 * 
	 * @return when the piece is able to move the right
	 */
	public boolean moveRight() {
		// get the current rotation
		int rot = currentPiece.getPieceRotation();
		if (detectOutOfBound(currentPiece, rot, currentPiecePosition[0],
				currentPiecePosition[1] + 1)) {
			// if it's out of bond, do nothing
			return false;
		} else if (detectCollision(currentPiece, rot, currentPiecePosition[0],
				currentPiecePosition[1] + 1)) {
			// if it collides, do nothing
			return false;
		} else {
			// otherwise, move left
			currentPiecePosition[1] = currentPiecePosition[1] + 1;
			return true;
		}
	}

	/**
	 * Check if moving left is valid. If so, move the current piece left.
	 * 
	 * @return when the piece is able to move the right
	 */
	public boolean moveLeft() {
		// get the current rotation
		int rot = currentPiece.getPieceRotation();
		if (detectOutOfBound(currentPiece, rot, currentPiecePosition[0],
				currentPiecePosition[1] - 1)
				|| detectCollision(currentPiece, rot, currentPiecePosition[0],
						currentPiecePosition[1] - 1)) {
			// if it's out of bond or collides to other piece, do nothing
			return false;
		} else {
			// otherwise, move right
			currentPiecePosition[1] = currentPiecePosition[1] - 1;
			return true;
		}
	}

	/**
	 * Check if moving down is valid. If so, move the current piece down.
	 * 
	 * @return when the piece is able to move the down
	 */
	public boolean moveDown() {
		// get the current rotation
		int rot = currentPiece.getPieceRotation();
		if (detectOutOfBound(currentPiece, rot, currentPiecePosition[0] + 1,
				currentPiecePosition[1])
				|| detectCollision(currentPiece, rot,
						currentPiecePosition[0] + 1, currentPiecePosition[1])) {
			// if it's out of bound, that means it touches the base line or if
			// it collides
			// either statement is true, then land the piece
			landPiece();
			// check if there are lines that can remove from the boar
			checkAndRemove();
			// add a new piece to continue the game
			addNewPiece();
			return false;
		} else {
			// otherwise, move down
			currentPiecePosition[0] = currentPiecePosition[0] + 1;
			return true;
		}
	}

	/**
	 * drop the piece until it collides or on the last row
	 * 
	 */
	public void spaceDrop() {
		// checking each row
		for (int r = 0; r < NUM_ROWS; r++) {
			// if move down is true, move down
			// if it's not, return
			if (moveDown() == false) {
				return;
			}
		}
	}

	/**
	 * if the piece landed, add the piece to be part of the board
	 */
	public void landPiece() {
		// get the current rotation
		int rot = currentPiece.getPieceRotation();
		// go through piece grid
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (currentPiece.isFilled(rot, r, c) == true) {
					// add the piece to the board
					blockMatrix[currentPiecePosition[0] + r][currentPiecePosition[1]
							+ c] = true;
				}
			}

		}
	}

	/**
	 * check the lines and remove the lines
	 */
	public void checkAndRemove() {
		// counter to check if the player can remove four lines at the same time
		int fourLine = 0;
		for (int r = 0; r < 4; r++) {
			if (currentPiecePosition[0] + r > NUM_ROWS - 1) {
				// if current piece reaches the bottom
				break;// leave the for loop and do nothing
			}
			if (fullLine(currentPiecePosition[0] + r)) {
				// if the line is full
				removeLine(currentPiecePosition[0] + r);
				// remove that line
				removedLine++;// add 1 to the total of removed line
				fourLine++; // keep track to check if the player can remove four
							// lines

				if (fourLine == 4) {
					// if the player removed four line at the same time
					TetrisesScore++; // add 1 to the total of fourLine special
										// score
					fourLine = 0;// reset counter
				}
				// calculate the level
				level = removedLine / 10;
			}
		}
	}

	/**
	 * Check if rotating clockwise is valid. If so, rotate the current piece
	 * clockwise by 90 degrees.
	 * 
	 * @return when the piece is able to rotate clock wise
	 */
	public boolean rotateCW() {
		// rotate the piece first
		currentPiece.rotateCW();
		// get the new rotation
		int rot = currentPiece.getPieceRotation();
		if (detectOutOfBound(currentPiece, rot, currentPiecePosition[0],
				currentPiecePosition[1])
				|| detectCollision(currentPiece, rot, currentPiecePosition[0],
						currentPiecePosition[1])) {
			// if it's out of bound, rotate it back
			// if it collides, rotate it back
			currentPiece.rotateCCW();
			return false;
		} else {
			// otherwise, just let the piece rotate
			return true;
		}

	}

	/**
	 * Check if rotating clockwise is valid. If so, rotate the current piece
	 * clockwise by 90 degrees.
	 * 
	 * @return when the piece is able to rotate clock wise
	 */
	public boolean rotateCCW() {
		// first rotate the piece counter clockwise
		currentPiece.rotateCCW();
		// get the new rotation
		int rot = currentPiece.getPieceRotation();
		if (detectOutOfBound(currentPiece, rot, currentPiecePosition[0],
				currentPiecePosition[1])
				|| detectCollision(currentPiece, rot, currentPiecePosition[0],
						currentPiecePosition[1])) {
			// if it's out of bound, rotate it back
			currentPiece.rotateCW();
			return false;
		} else {
			// otherwise, just let the piece rotate
			return true;
		}
	}

	/**
	 * Checks if placing the piece at grid position (row, col) with the rotation
	 * rot (values can be 0, 90, 180, 270) would cause a collision
	 * 
	 * @return when the piece doesn't collide to other pieces
	 */
	public boolean detectCollision(TetrisPiece piece, int rot, int gridRow,
			int gridCol) {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (currentPiece.isFilled(rot, r, c) == true
						&& (blockMatrix[r + gridRow][c + gridCol] == true)) {
					// return true when it collides
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if placing the piece at grid position (row, col) with the rotation
	 * rot (values can be 0, 90, 180, 270) would cause an out of bounds
	 * condition
	 * 
	 * @return when the piece is not out of bound
	 */
	public boolean detectOutOfBound(TetrisPiece piece, int rot,
			int rowPosition, int colPosition) {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (currentPiece.isFilled(rot, r, c) == true
						&& (rowPosition + r >= NUM_ROWS || colPosition + c < 0 || colPosition
								+ c >= NUM_COLS)) {
					// return true when it's out of bound
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * check whether the row is full
	 * @param row
	 * @return when the row is full
	 */
	private boolean fullLine(int row) {
		//check every columns in the row
		for (int c = 0; c < NUM_COLS; c++) {
			if (blockMatrix[row][c] == false) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Check if there is a full line at the row.
	 */
	private void removeLine(int row) {
		// check from the bottom to the top
		for (int r = row; r > 0; r--) {
			for (int c = 0; c < NUM_COLS; c++) {
				// move every index one unit down
				blockMatrix[r][c] = blockMatrix[r - 1][c];
			}
		}

	}

	// the total of lines that are removed
	public int numberOfFormedLines() {
		return removedLine;
	}

	// the total number of removing four-line at the same time
	public int getFourLineScore() {
		return TetrisesScore;
	}

	// get the level
	public int getLevel() {
		return level;
	}

	// the current movable piece
	public TetrisPiece getCurrentPiece() {
		return currentPiece;

	}

	// the current movable piece's row col location
	public int[] getCurrentPieceGridPosition() {
		return currentPiecePosition;

	}

	// the width of the board
	public int getNumCols() {
		return NUM_COLS;
	}

	// the height of the board
	public int getNumRows() {
		return NUM_ROWS;
	}

	// get the board
	public boolean[][] getBlockMatrix() {
		return blockMatrix;
	}
}