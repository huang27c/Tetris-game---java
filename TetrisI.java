/**
 * TetrisI shape
 * 
 * @author Ching Ching Huang
 *
 */
public class TetrisI extends TetrisPiece {

	public TetrisI() {
		// 0 degree
		filledSquares[0][0][0] = true;
		filledSquares[0][0][1] = true;
		filledSquares[0][0][2] = true;
		filledSquares[0][0][3] = true;

		// 90 degree
		filledSquares[1][1][0] = true;
		filledSquares[1][2][0] = true;
		filledSquares[1][3][0] = true;
		filledSquares[1][0][0] = true;

		// 180 degree
		filledSquares[2][0][0] = true;
		filledSquares[2][0][1] = true;
		filledSquares[2][0][2] = true;
		filledSquares[2][0][3] = true;

		// 270 degree
		filledSquares[3][1][0] = true;
		filledSquares[3][2][0] = true;
		filledSquares[3][3][0] = true;
		filledSquares[3][0][0] = true;
	}
}
