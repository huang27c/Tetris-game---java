/**
 * The TetrisPiece abstract class represents a piece made of 4 TetrisBlocks. It
 * maintains 4 rotations (0 degrees, 90 degrees, 180 degrees and 270 degrees),
 * with each being a 4x4 grid with certain filled squares.
 * 
 * @author Ching Ching Huang
 *
 */
public abstract class TetrisPiece {

	/**
	 * First dimension is 4 rotations. Second and third create 4x4 grid.
	 */
	protected boolean[][][] filledSquares;
	protected int pieceRotation;// maintain current rotation

	/**
	 * Constructor does nothing.
	 */
	public TetrisPiece() {
		pieceRotation = 0;
		filledSquares = new boolean[4][4][4];
	}

	// Rotate the piece clockwise by 90 degrees.
	public void rotateCW() {
		pieceRotation = (pieceRotation + 1) % 4;

	}

	// Rotate the piece counter clockwise by 90 degrees.
	public void rotateCCW() {
		pieceRotation = (pieceRotation - 1) % 4;
		if (pieceRotation < 0) {
			pieceRotation = 3;
		}
	}

	// Get the rotation of this piece.
	public int getPieceRotation() {
		return pieceRotation;
	}

	// Checks if there is a TetrisBlock at the (row, col) position for the
	// rotation rot,
	public boolean isFilled(int rot, int row, int col) {
		// return true if there is a block in the position for that rotation
		if (filledSquares[rot][row][col] == true) {
			return true;
		} else {
			return false;
		}
	}
}