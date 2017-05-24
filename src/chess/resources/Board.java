package chess.resources;


/**
 * Board -- a class that is used to represent the board of the chess game. This class is
 * designed to be extensible to accommodate for different board sizes. 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */

public class Board {


	private int boardHeight;
	private int boardWidth;
	private String shape; // The shape of the board we will play with
	
	
	public Board(int height, int width){

		this.boardHeight = height;
		this.boardWidth = width;
		this.shape = "square";
		
	}
	
	public Board(int height, int width, String shape){

		this.boardHeight = height;
		this.boardWidth = width;
		this.shape = shape;
		
	}
	
	public int getHeight() {
		return this.boardHeight;
	}


	public int getWidth() {
		return this.boardWidth;
	}
	
	
	
}
