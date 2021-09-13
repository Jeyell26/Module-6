/*
 * Name:
 * Section Leader:
 * File: ChessBoard.java
 * ------------------
 * This class represents the ChessBoard. Its job is to keep track of where all
 * of the pieces are. Since we just learned about two-dimensional arrays, it 
 * might be a good idea to use one here (just a hint). Currently, it doesn't do 
 * anything, but it does have four methods for you to fill in. These are the only
 * required methods in this class. As long as these work, feel free to do whatever
 * else you want to get this class working.
 */

import acm.graphics.GImage;
import acm.graphics.GObject;

import java.awt.*;

public class ChessBoard extends DrawableObject{

	/** Constant that sets the size of the chess board */
	public static final int BOARD_SIZE = 8;

	// The constructor will need to initialize whatever data structure you’re going
	// to use to store the ChessPieces and their locations.

	private ChessPiece board[][];
	// The job of the constructor would be to initialize this Array to all nulls,
	// as initially, there aren't any pieces on the board.
	
	/** Constructor for the ChessBoard class (do whatever you want with this) */
	public ChessBoard() {
		// You fill this in.
		// Note: In Java, each class variable, instance variable, or array component is
		//initialized with a default value when it is created
		//For all reference types, the default value is null.
		board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = null;
			}
		}
	}

	public void initializeBoard() {
		ChessPiece temp;
		int x;
		for (int i = 0; i < 32; i++) {
			if (i < 16) {
				if (i == 0 || i == 7) {
					temp = new Rook(7, i, ChessPiece.WHITE);
				} else if (i == 1 || i == 6) {
					temp = new Knight(7, i, ChessPiece.WHITE);
				} else if (i == 2 || i == 5) {
					temp = new Bishop(7, i, ChessPiece.WHITE);
				} else if (i == 3) {
					temp = new Queen(7, i, ChessPiece.WHITE);
				} else if (i == 4) {
					temp = new King(7, i, ChessPiece.WHITE);
				} else {
					temp = new Pawn(6, i - 8, ChessPiece.WHITE);
				}
			} else {
				x = i - 16;
				if (x == 0 || x == 7) {
					temp = new Rook(0, x, ChessPiece.BLACK);
				} else if (x == 1 || x == 6) {
					temp = new Knight(0, x, ChessPiece.BLACK);
				} else if (x == 2 || x == 5) {
					temp = new Bishop(0, x, ChessPiece.BLACK);
				} else if (x == 3) {
					temp = new Queen(0, x, ChessPiece.BLACK);
				} else if (x == 4) {
					temp = new King(0, x, ChessPiece.BLACK);
				} else {
					temp = new Pawn(1, x - 8, ChessPiece.BLACK);
				}
			}
			addPiece(temp);
		}
	}

	/** Returns the ChessPiece currently residing at the specified row and 
	 * column. If no piece exists at the specified location, should return 
	 * null.
	 */
	public ChessPiece pieceAt(int row, int col)
	{
		return board[row][col];
	}
	
	/** Adds the specified ChessPiece to the ChessBoard (hint: ChessPieces know their
	 * own rows and columns. You can use this to figure out where to place the piece)
	 */
	public void addPiece(ChessPiece piece)
	{
		board[piece.getRow()][piece.getCol()] = piece;
		// If the user attempts to add a piece to a location where one already exists,
		// addPiece should overwrite the old piece with the new one.

	}
	
	/** Removes the piece at the specified location from the board.
	 */
	public void removePiece(int row, int col)
	{
		board[row][col] = null;
	}


	
}


