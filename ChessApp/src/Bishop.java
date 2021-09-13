/*
 * Name:
 * Section Leader:
 * File: Bishop.java
 * ------------------
 * This class represents the Bishop type of chess piece. This piece can move and capture 
 * pieces along diagonals. For more information visit: http://en.wikipedia.org/wiki/Bishop_(chess)
 */

public class Bishop extends ChessPiece{

	/** Constructor for the Bishop class */
	public Bishop(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}	
	
	/** Method that returns a boolean indicating whether or not the bishop can legally move
	 *  to the specified location (you need to fill this one in).
	 */
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		// Fill this in with your own code.
		int row = (nextRow - this.row);
		int col = (nextCol - this.col);

		// Valid diagonal move
		if(Math.abs(row) == Math.abs(col)){

			// south east movement checker
			if(row > 0 && col > 0){
				for (int i = 1; i < row; i++) {
					if(board.pieceAt(this.row + i, this.col + i) != null){
						return false;
					}
				}
			}
			// north west movement checker
			if(row < 0 && col < 0){
				for (int i = -1; i > row; i--) {
					if(board.pieceAt(this.row + i, this.col + i) != null){
						return false;
					}
				}
			}
			// north east movement checker
			if(row < 0 && col > 0){
				for (int i = -1; i > row; i--) {
					if(board.pieceAt(this.row + i, this.col - i) != null){
						return false;
					}
				}
			}
			// south west movement checker
			if(row > 0 && col < 0){
				for (int i = 1; i < row; i++) {
					if(board.pieceAt(this.row + i, this.col - i) != null){
						return false;
					}
				}
			}
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



//		return automagicBishopCanMoveTo(nextRow, nextCol, board);	// Eventually this line should not be here
	}
	
	/** Implementation of getType() method for the Bishop class. Provides a way to identify
	 *  the Bishop-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() 
	{
		return PieceType.BISHOP;
	}
}
