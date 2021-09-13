/*
 * Name:
 * Section Leader:
 * File: Chess.java
 * ------------------
 * This program plays the game Chess.
 */

import java.awt.Color;
import java.awt.event.*;
import java.util.function.Predicate;

/** The main class responsible for managing the chess game */
public class Chess extends GraphicsProgram {

	/**
	 * Object responsible for handling the graphical display on the screen
	 */
	ChessDisplay display;

	/**
	 * Object that keeps track of the locations of all pieces
	 */
	ChessBoard board;

	double squareSideLength;
	double initialX = 39;
	double initialY = 26;
	// White turn = 1
	// Black turn = 0
	int turn = 1;
	int click = 0;
	ChessPiece prevPiece;


	/**
	 * Method called before run responsible for initializing the ChessDisplay and
	 * ChessBoard objects
	 */
	public void init() {
		display = ChessDisplay.getInstance(this);            // This line is required, don't change it
		board = new ChessBoard();
		squareSideLength = (double) display.getHeight() / (double) 8 * 0.9D;

		display.useRealChessLabels(false);            // Use this method to change how the board is labeled
		// on the screen. Passing in true will label the board
		// like an official chessboard; passing in false will
		// label the board like it is indexed in an array and
		// in ChessDisplay.
	}

	/**
	 * The main method that runs the program
	 */
	public void run() {
		// You fill this in.
		addMouseListeners();
		board.initializeBoard();
		display.draw(board);

		println("White turn:");
	}

	public void mousePressed(MouseEvent e) {
		int[] location = display.getLocation(e.getX(), e.getY());
		int x = location[0];
		int y = location[1];
		if (turn == 1) {
			// first click
			if (click == 0) {
				if (board.pieceAt(x, y) != null && board.pieceAt(x, y).getColor() == ChessPiece.WHITE) {
					display.selectSquare(x, y, Color.YELLOW);
					prevPiece = board.pieceAt(x, y);
					display.draw(board);
					click = 1;
					return;
				}
			}
			// second click
			else if (click == 1) {
				if (prevPiece.canMoveTo(x, y, board) && prevPiece != board.pieceAt(x, y)) {
					println(prevPiece.getType() + " from (" + prevPiece.getRow() + "," + prevPiece.getCol() + ") to (" + x + "," + y + ")");
					board.removePiece(prevPiece.getRow(),prevPiece.getCol());
					prevPiece.moveTo(x, y);
					board.addPiece(prevPiece);
					display.unselectAll();
					display.draw(board);
					turn = 0;
					if(isInCheckMate(board,turn)){
						println("Checkmate! White wins");
						turn = 3;
						return;
					}
					if(isInCheck(board,turn)){
						println("Check!");
					}
					if(isInStalemate(board,turn)){
						println("Stalemate! Draw");
						turn = 3;
						return;
					}
					click = 0;
					println("");
					println("Black Turn:");
					return;
				} else {
					display.unselectAll();
					display.draw(board);
					click = 0;
					System.out.println(board.pieceAt(x, y));
					return;
				}

			}
		}
		// black
		if (turn == 0) {
			// first click
			if (click == 0) {
				if (board.pieceAt(x, y) != null && board.pieceAt(x, y).getColor() == ChessPiece.BLACK) {
					display.selectSquare(x, y, Color.YELLOW);
					prevPiece = board.pieceAt(x, y);
					display.draw(board);
					click = 1;
					return;
				}
			}
			// second click
			else if (click == 1) {
				if (prevPiece.canMoveTo(x, y, board) && prevPiece != board.pieceAt(x, y)) {
					println(prevPiece.getType() + " from (" + prevPiece.getRow() + "," + prevPiece.getCol() + ") to (" + x + "," + y + ")");
					board.removePiece(prevPiece.getRow(),prevPiece.getCol());
					prevPiece.moveTo(x, y);
					board.addPiece(prevPiece);
					display.unselectAll();
					display.draw(board);
					turn = 1;
					if(isInCheckMate(board,turn)){
						println("Checkmate! Black wins");
						turn = 3;
						return;
					}
					if(isInCheck(board,turn)){
						println("Check!");
					}
					if(isInStalemate(board,turn)){
						println("Stalemate! Draw");
						return;
					}
					click = 0;
					println("");
					println("Black Turn:");
					return;
				} else {
					display.unselectAll();
					display.draw(board);
					click = 0;
					System.out.println(board.pieceAt(x, y));
					return;
				}

			}
		}
	}
	}


//// white turn
//		if(turn == 1){
//				// first click
//				if(click == 0){
//				if(board.pieceAt(x,y) != null && board.pieceAt(x,y).getColor() == ChessPiece.WHITE){
//				display.selectSquare(x,y,Color.YELLOW);
//				prevPiece = board.pieceAt(x,y);
//				display.draw(board);
//				click = 1;
//				return;
//				}
//				}
//				// second click
//				else if(click == 1){
//				if(prevPiece.canMoveTo(x,y,board) && prevPiece != board.pieceAt(x,y)){
//				println(prevPiece.getType() + " from (" + prevPiece.getRow() + "," +prevPiece.getCol()+ ") to (" + x + "," + y + ")");
//				prevPiece.moveTo(x,y);
//				board.addPiece(prevPiece);
//				display.unselectAll();
//				display.draw(board);
//				click = 0;
//				turn = 0;
//				// Check if move causes check // checkmate // stalemate
////					if(isInCheckMate(board,turn)){
////						println("Checkmate! White wins");
////						return;
////					}
////					if(isInCheck(board,turn)){
////						println("Check!");
////					}
////					if(isInStalemate(board,turn)){
////						println("Stalemate! Draw");
////						return;
////					}
//				println("");
//				println("Black Turn:");
//				return;
//				}
//				else{
//				display.unselectAll();
//				display.draw(board);
//				click = 0;
//				System.out.println(board.pieceAt(x,y));
//				return;
//				}
//				}
//				}
//				// black turn
//				if(turn == 0){
//				// first click
//				if(click == 0){
//				if(board.pieceAt(x,y) != null && board.pieceAt(x,y).getColor() == ChessPiece.BLACK){
//				display.selectSquare(x,y,Color.YELLOW);
//				prevPiece = board.pieceAt(x,y);
//				display.draw(board);
//				click = 1;
//				}
//				}
//				// second click
//				else if(click == 1){
//				if(prevPiece.canMoveTo(x,y,board) && prevPiece != board.pieceAt(x,y)){
//				println(prevPiece.getType() + " from (" + prevPiece.getRow() + "," +prevPiece.getCol()+ ") to (" + x + "," + y + ")");
//				board.removePiece(x,y);
//				prevPiece.moveTo(x,y);
//				board.addPiece(prevPiece);
//				display.unselectAll();
//				display.draw(board);
//				click = 0;
//				turn = 1;
//				// Check if move causes check // checkmate // stalemate
////					if(isInCheckMate(board,turn)){
////						println("Checkmate! White wins");
////						return;
////					}
////					if(isInCheck(board,turn)){
////						println("Check!");
////					}
////					if(isInStalemate(board,turn)){
////						println("Stalemate! Draw");
////						return;
////					}
//				println("");
//				println("White Turn:");
//				return;
//				}
//				else{
//				display.unselectAll();
//				display.draw(board);
//				click = 0;
//				}
//				}
//				}



//	public void mousePressed(MouseEvent e)
//	{
//		Figure out which player’s turn it is
//		call getLocation(e.getX(), e.getY()) to convert the mouse click location in pixels to a row and column on the ChessBoard
//		if(this is the first click of their turn)
//		{
//			if(they click on a piece of their own color)
//			{
//				highlight spot where the player clicked with selectSquare
//				store the piece at the location the user clicked so you can move it on the next click
//				draw the board
//			}
//		}
//else
//		{
//			check to see if the piece selected on the previous click can move to the spot selected on this click with the canMoveTo method.
//			if(the chosen piece can move to the selected spot AND the selected spot is not the spot the piece already occupies)
//			{
//				remove the piece from the board with the method removePiece
//				update the ChessPiece’s location with the method moveTo
//				add the updated ChessPiece back to the board addPiece
//				clear all highlighted squares with the method unselectAll
//				draw the board
//				check for Checkmates or Stalemates with isInCheckmate or isinStalemate and print appropriate message
//				advance to next player’s turn
//			}
//else
//			{
//				clear all highlighted squares
//				draw the board
//				remain the same player’s turn
//			}
//		}
//	}


