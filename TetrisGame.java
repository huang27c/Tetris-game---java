/**
 * This class in connecting the board and the controller
 * 
 * @author Ching Ching Huang
 *
 */
public class TetrisGame {

	//all the movings 
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int DOWN = 3;
	public static final int CW = 4;
	public static final int CCW = 5;
	public static final int SPACE_DROP = 6;
	//the tetris board
	private TetrisBoard tetrisBoard;
	
	/*
	 * constructor initializes the board
	 */
	public TetrisGame(){		
		tetrisBoard = new TetrisBoard();
	}
	
	public void attemptMove(int moveType){
		//pairing each number with each move
		if(moveType == RIGHT){
			tetrisBoard.moveRight();
		}else if(moveType == LEFT){
			tetrisBoard.moveLeft();			
		}else if(moveType == DOWN){
			tetrisBoard.moveDown();
		}else if(moveType == CW){
			tetrisBoard.rotateCW();
		}else if(moveType == CCW){
			tetrisBoard.rotateCCW();
		}else if(moveType == SPACE_DROP){
			tetrisBoard.spaceDrop();
		}
	}
	
	/*
	 * getter method for the board
	 */
	public TetrisBoard getTetrisBoard(){
		return tetrisBoard;
	}
	
	public int getTetrises(){
		return tetrisBoard.getFourLineScore();
	}
	
	public int getScore(){
		return tetrisBoard.numberOfFormedLines();
	}
	
	public int getLevel(){
		return tetrisBoard.getLevel();
	}
}
