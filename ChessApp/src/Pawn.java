/*
 * Name:
 * Section Leader:
 * File: Pawn.java
 * ------------------
 * This class represents the Pawn type of chess piece. This piece can move only straight 
 * forward (away from your side toward the other side). In can normally move only one space
 * at a time, except on the first move, when it has the option of moving two spaces. The pawn,
 * although it moves only forward, captures only diagonally forward. Turns out, this makes it
 * the most difficult to implement in code (fair warning). For more information go 
 * here: http://en.wikipedia.org/wiki/Pawn_(chess)
 */

public class Pawn extends ChessPiece{

	/** Constructor for the Pawn class */
	public Pawn(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}	

	/** Method that returns a boolean indicating whether or not the pawn can legally move
	 *  to the specified location (you need to fill this one in).
	 */
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		int row = (nextRow - this.row);
		int col = (nextCol - this.col);
		Boolean eatable = false;
		Boolean willEat = false;
		Boolean willMove = false;

		// Fill this in with your own code.
		// Go to ally piece not allowed
		if(board.pieceAt(nextRow,nextCol) != null){
			if(board.pieceAt(nextRow,nextCol).getColor() == this.color){
				return false;
			}
			else{
				eatable = true;
			}
		}
		// Move back not allowed
		if(this.color == ChessPiece.WHITE){
			if(this.row < nextRow){

				return false;
			}
			if(eatable == true && Math.abs(row) + Math.abs(col) == 2){
				willEat = true;
			}
		}

		if(this.color == ChessPiece.BLACK){
			if(this.row > nextRow){
				return false;
			}
			if(eatable == true && Math.abs(row) + Math.abs(col)  == 2){
				willEat = true;
			}
		}

		if(this.col == nextCol){
			if(board.pieceAt(nextRow,nextCol) != null){
				return false;
			}
			if(this.color == ChessPiece.WHITE && this.row == 6 && row>=-2 && row<0){
				willMove = true;
				if(board.pieceAt(5,this.col) != null){
					return false;
				}
			}
			if(this.color == ChessPiece.BLACK && this.row == 1 && row<=2 && row>0){
				willMove = true;
				if(board.pieceAt(2,this.col) != null){
					return false;
				}
			}
			if(Math.abs(row) == 1){
				willMove = true;
			}

		}

		if(willMove || willEat){
			if(this.moveWouldCauseCheck(nextRow,nextCol,board)){
				return false;
			}
			return true;
		}
		return false;
	}

	/** Implementation of getType() method for the Pawn class. Provides a way to identify
	 *  the Pawn-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() 
	{
		return PieceType.PAWN;
	}
	
}
