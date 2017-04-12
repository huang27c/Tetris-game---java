/**
 * TetrisBoardTextView creates a String view of a TetrisBoard.
 * 
 * @author Ching Ching Huang
 *
 */
public class TetrisBoardTextView {

	private TetrisBoard board;
	private TetrisPiece piece;

	/*
	 * constructor takes in tetris board and calls the board
	 */
	public TetrisBoardTextView(TetrisBoard b) {
		board = b;
	}

	public java.lang.String getBoardString() {
		return null;
	}

	/*
	 * print out the board and the piece
	 */
	public void printBoard() {
		piece = board.getCurrentPiece();//get the current tetris piece
		int rot = piece.getPieceRotation(); //get the current tetris piece's rotation
		int positions[] = board.getCurrentPieceGridPosition();

		//the calculation for converting the piece to the board
		int leftX = positions[0]; //get the left top x location of the piece grid
		int rightX = leftX + 3; //get the right top x location of the piece grid
		int topY = positions[1]; //get the top left y location of the piece grid
		int bottomY = positions[1] + 3; //get the bottom left y location
		
		//print out the instruction 
		System.out.println("Please enter a move (l,r,d,z,x) or type Quit to end");
		//print out how many lines the player has cleared
		System.out.println("Number of lines cleared: "
				+ board.numberOfFormedLines());
		//print out how many times did the player clear 4 lines at a time
		System.out.println("Number of TetrisesScore cleared: " + board.getFourLineScore());
		
		//print out the board and the piece
		for (int i = 0; i < board.blockMatrix.length; i++) {
			for (int j = 0; j < board.blockMatrix[i].length; j++) {
				//check if the board has contain a block, then print out x
				if (board.blockMatrix[i][j] == true) {
					System.out.print("x");
				} else if (i >= leftX && i <= rightX && j >= topY
						&& j <= bottomY) {
					//converting the piece to the board
					//check if i, j are in the piece grid
					if (piece.isFilled(rot, i - leftX, j - topY) == true) {
						//then check the piece grid
						System.out.print("*");
					} else {
						System.out.print(".");
					}
				} else {
					System.out.print(".");
				}
			}
			System.out.println();

		}

	}
}
