/**
 * TetrisS shape
 * 
 * @author Ching Ching Huang
 *
 */
public class TetrisS extends TetrisPiece {

	public TetrisS() {
		// 0 degree
		filledSquares[0][0][1] = true;
		filledSquares[0][1][0] = true;
		filledSquares[0][1][1] = true;
		filledSquares[0][2][0] = true;

		// 90 degree
		filledSquares[1][0][0] = true;
		filledSquares[1][0][1] = true;
		filledSquares[1][1][1] = true;
		filledSquares[1][1][2] = true;

		// 180 degree
		filledSquares[2][0][1] = true;
		filledSquares[2][1][0] = true;
		filledSquares[2][1][1] = true;
		filledSquares[2][2][0] = true;

		// 270 degree
		filledSquares[3][0][0] = true;
		filledSquares[3][0][1] = true;
		filledSquares[3][1][1] = true;
		filledSquares[3][1][2] = true;
	}
}
