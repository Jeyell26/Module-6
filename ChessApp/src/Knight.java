/*
 * Name:
 * Section Leader:
 * File: Knight.java
 * ------------------
 * This class represents the Knight type of chess piece. This piece can move only in 
 * L-shapes. That is it moves two spaces in one direction and one space in an orthogonal
 * direction. It is the only piece in chess that can jump over other pieces. For
 * more information go here: http://en.wikipedia.org/wiki/Knight_(chess)
 */

public class Knight extends ChessPiece{

	/** Constructor for the Knight class */
	public Knight(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}		
	
	/** Method that returns a boolean indicating whether or not the king can legally move
	 *  to the specified location (you need to fill this one in).
	 */
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		// Fill this in with your own code.
		int row = Math.abs(nextRow - this.row);
		int col = Math.abs(nextCol - this.col);
		if((row==2 && col==1) || (row==1 && col==2)){
			if(board.pieceAt(nextRow,nextCol) != null){
				if(board.pieceAt(nextRow,nextCol).getColor() == this.color){
					return false;
				}
			}
			if(this.moveWouldCauseCheck(nextRow,nextCol,board)){
				return false;
			}
			return true;
		}
		else{
			return false;
		}


//		return automagicKnightCanMoveTo(nextRow, nextCol, board);	// Eventually this line should not be here
	}
	
	/** Implementation of getType() method for the Knight class. Provides a way to identify
	 *  the Knight-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() 
	{
		return PieceType.KNIGHT;
	}
	
}
