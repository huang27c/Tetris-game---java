/**
 * TetrisL shape
 * 
 * @author Ching Ching Huang
 *
 */
public class TetrisL extends TetrisPiece {

	public TetrisL() {

		// 0 degree
		filledSquares[0][0][0] = true;
		filledSquares[0][1][0] = true;
		filledSquares[0][2][0] = true;
		filledSquares[0][2][1] = true;

		// 90 degree
		filledSquares[1][0][0] = true;
		filledSquares[1][0][1] = true;
		filledSquares[1][0][2] = true;
		filledSquares[1][1][0] = true;

		// 180 degree
		filledSquares[2][0][0] = true;
		filledSquares[2][0][1] = true;
		filledSquares[2][1][1] = true;
		filledSquares[2][2][1] = true;

		// 270 degree
		filledSquares[3][0][2] = true;
		filledSquares[3][1][0] = true;
		filledSquares[3][1][1] = true;
		filledSquares[3][1][2] = true;

	}
}
