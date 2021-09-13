/*
 * Name:
 * Section Leader:
 * File: Queen.java
 * ------------------
 * This class represents the Queen type of chess piece. This piece can move and capture
 * in any straight line (diagonally, horizontally, or vertically). For more information go 
 * here: http://en.wikipedia.org/wiki/Queen_(chess)
 */

public class Queen extends ChessPiece{

	/** Constructor for the Queen class */
	public Queen(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}	
	
	/** Method that returns a boolean indicating whether or not the queen can legally move
	 *  to the specified location (you need to fill this one in).
	 */
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board) {
		// Fill this in with your own code.
		// Check if it will bump to a piece
		// ROOK MOVES
		if(nextRow == row || nextCol == col) {
			// if moving horizontally
			if (nextRow == row) {
				for (int i = col + 1; i < nextCol; i++) {
					if (board.pieceAt(row, i) != null) {
						return false;
					}
				}
				for (int i = col - 1; i > nextCol; i--) {
					if (board.pieceAt(row, i) != null) {
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
			if (nextCol == col) {
				for (int i = row + 1; i < nextRow; i++) {
					if (board.pieceAt(i, col) != null) {
						return false;
					}
				}
				for (int i = row - 1; i > nextRow; i--) {
					if (board.pieceAt(i, col) != null) {
						return false;
					}
				}
				if (board.pieceAt(nextRow, nextCol) != null) {
					if (board.pieceAt(nextRow, nextCol).getColor() == this.color) {
						return false;
					}
				}
			}
			return true;
		}
		// BISHOP MOVES
		int row = (nextRow - this.row);
		int col = (nextCol - this.col);

		// Valid diagonal move
		if (Math.abs(row) == Math.abs(col)) {
			// south east movement checker
			if (row > 0 && col > 0) {
				for (int i = 1; i < row; i++) {
					if (board.pieceAt(this.row + i, this.col + i) != null) {
						return false;
					}
				}
			}
			// north west movement checker
			if (row < 0 && col < 0) {
				for (int i = -1; i > row; i--) {
					if (board.pieceAt(this.row + i, this.col + i) != null) {
						return false;
					}
				}
			}
			// north east movement checker
			if (row < 0 && col > 0) {
				for (int i = -1; i > row; i--) {
					if (board.pieceAt(this.row + i, this.col - i) != null) {
						return false;
					}
				}
			}
			// north west movement checker
			if (row > 0 && col < 0) {
				for (int i = 1; i < row; i++) {
					if (board.pieceAt(this.row + i, this.col - i) != null) {
						return false;
					}
				}
			}
			if (board.pieceAt(nextRow, nextCol) != null) {
				if (board.pieceAt(nextRow, nextCol).getColor() == this.color) {
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

	}

	/** Implementation of getType() method for the Pawn class. Provides a way to identify
	 *  the Pawn-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() 
	{
		return PieceType.QUEEN;
	}
	
}
