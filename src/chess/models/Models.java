package chess.models;

import java.util.Stack;

import chess.resources.*;


/**
 * State - One might think that the 'board' of a chess game should be responsible
 * for saving the state of the entire game 
 * (where all the pieces are currently placed, etc..). However, the board is just another
 * physical piece that is used to play the game, and in reality is not aware of the 
 * state of the game whatsoever. Therefore this 'State' class was designed and created to 
 * represent the state of the game. It saves information regarding where the chess 
 * pieces are located. This state is modified by chess piece moves and will catch for 
 * illegal moves and will also perform proper logic for kills. 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Models {

	
	public static Board board;
	public static King kingNorth;
	public static King kingSouth;
	public static boolean isRunning = true;
	public static ChessPiece[][] stateRepresentation; 
	public static Stack<Command> commands = new Stack<Command>();
	
	// game loop data 
	public static Player turn = Player.PLAYER_NORTH;
	public static int playerNorthScore = 0;
	public static int playerSouthScore = 0;
	public static String player1Name;
	public static String player2Name;
	
	
	/**
	 * Completely delegates all initializing steps to the initializer class.
	 * 
	 * @param board - board object that we pass into the initializer.
	 */
	public static void initializeState(){	
		
		Board board = new Board(8,8);
		new ModelsInitializer(board);
	}
	
	
	
	/**
	 * 
	 * Responsible for simply moving a piece on the state representation. Rule-checking
	 * is implemented on this level. All rule-checking is implemented on the piece level.
	 * 
	 * @param currentPosition - the current position of the piece we are moving
	 * @param moveToPosition  - the prospective position to where me are moving the piece to.
	 * 
	 * @return a string indicating to the caller the result of the method call. "success" signifies
	 * that the piece was moved without any special accommodations. "killed" signifies that the piece was moved
	 * but also killed an opponents piece in the process. 
	 * 
	 */
	public static String changePiecePosition (int[] currentPosition, int[] moveToPosition){
		
		ChessPiece movePiece = stateRepresentation[currentPosition[0]] [currentPosition[1]]; // the piece we are moving
		boolean isMovePositionOccupied = isPositionOccupied(moveToPosition);
	
		/*simply move the piece to the requested position */
		stateRepresentation[currentPosition[0]] [currentPosition[1]] = null; 
		stateRepresentation[moveToPosition[0]] [moveToPosition[1]] = movePiece;
		
		//return killed or success depending on whether the position is occupied
		return (isMovePositionOccupied) ? "killed" : "success"; 

	}
	
	
	
	/**
	 * Determines whether a position is currently occupied.
	 * 
	 * @param position - the spot on the representation we are checking for occupancy
	 * 
	 * @return boolean - true if the @param position is currently occupied.
	 */
	public static boolean isPositionOccupied(int[] position){
		return (stateRepresentation[position[0]][position[1]] != null);
	}
	

	/**
	 * returns the piece currently at @param position.
	 * 
	 * @param position - the spot on the representation we are checking.
	 * 
	 * @return ChessPiece - the object at that position. null means no piece is present.
	 */
	public static ChessPiece getPieceAtPosition(int[] position){
		return (stateRepresentation[position[0]][position[1]]);
	}
	
	
	
	/**
	 * Switches the turn to the next player.
	 * 
	 * @param None
	 * 
	 * @return None
	 */
	public static void switchTurn(){
		if( turn == Player.PLAYER_NORTH ) turn = Player.PLAYER_SOUTH;
		else turn = Player.PLAYER_NORTH;
	}
	
	
	/**
	 * Increments the score for the given player
	 * 
	 * @param winner - the player that you want to increment the score for. 
	 */
	public static void incrementScore(Player winner){
	
		if(winner == Player.PLAYER_NORTH) playerNorthScore++;
		else playerSouthScore++;
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
