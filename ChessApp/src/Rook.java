/*
 * Name:
 * Section Leader:
 * File: Rook.java
 * ------------------
 * This class represents the Rook type of chess piece. This piece can move and capture 
 * pieces along rows and columns. It is also known as a castle. For more information visit: 
 * http://en.wikipedia.org/wiki/Rook_(chess)
 */

public class Rook extends ChessPiece{

	/** Constructor for the Rook class */
	public Rook(int initialRow, int initialCol, int pieceColor)
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
		// Check if it will bump to a piece
		// if moving horizontally
		if(nextRow == row){
			for (int i = col+1; i < nextCol; i++) {
				if(board.pieceAt(row,i) != null){
					return false;
				}
			}
			for (int i = col-1; i > nextCol; i--) {
				if(board.pieceAt(row,i) != null){
					return false;
				}
			}
			if (board.pieceAt(nextRow, nextCol) != null) {
				if (board.pieceAt(nextRow, nextCol).getColor() == this.color) {
					return false;
				}
			}
		}
		// if moving vertically
		if(nextCol == col){
			for (int i = row+1; i < nextRow; i++) {
				if(board.pieceAt(i,col) != null){
					return false;
				}
			}
			for (int i = row-1; i > nextRow; i--) {
				if(board.pieceAt(i,col) != null){
					return false;
				}
			}
			if (board.pieceAt(nextRow, nextCol) != null) {
				if (board.pieceAt(nextRow, nextCol).getColor() == this.color) {
					return false;
				}
			}
		}
		if(this.moveWouldCauseCheck(nextRow,nextCol,board)){
			return false;
		}
		return true;
	}

	/** Implementation of getType() method for the Rook class. Provides a way to identify
	 *  the Rook-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() 
	{
		return PieceType.ROOK;
	}

}
