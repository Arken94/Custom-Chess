package chess.models;

import java.util.Stack;

import chess.resources.Bishop;
import chess.resources.Board;
import chess.resources.ChessPiece;
import chess.resources.Frog;
import chess.resources.King;
import chess.resources.Knight;
import chess.resources.Pawn;
import chess.resources.Player;
import chess.resources.Queen;
import chess.resources.Rook;
import chess.resources.Snail;



/**
 * 
 * StateInitializer - in charge of completely setting up State class for game play. 
 * This class's constructor must be called in the State's classes constructor.
 * 
 * We have decouples the initializer from the state in order to allow for extensible 
 * features to be added to the state. For instance, if we wanted to change the state rep.
 * to be a hexagon, we would only need to change code in here to set up the game differently.
 * 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */

public class ModelsInitializer {

	
	
	
	/**
	 * constructor for StateInitializer that sets up the state.
	 * It delegates many tasks to other methods.
	 * 
	 */
	public ModelsInitializer(Board board) {
		Models.board = board;
		Models.stateRepresentation = new ChessPiece[board.getHeight()][board.getWidth()];
		buildStateRepresentation();
		Models.commands = new Stack<Command>();
	}
	
		
	
	/**
	 * Builds up the game state representation, by placing all required pieces in their 
	 * respective positions. This method should only ever be called in "initializeState()". 
	 * This method is only used to build the state of the game.
	 * 
	 * THIS METHOD CAN/WILL BE EASILY CHANGED TO ACCOMMODATE FOR DIFFERENT BOARD SIZES & SHAPES
	 * 
	 * @return No value returned
	 */
	private static void buildStateRepresentation(){
		buildPawns();
		buildBishops();
		buildRooks();
		buildKnights();
		buildQueens();
		buildKings();
		buildCustom();
		
	}
	
		
	
	
	/**
	 * Initialize Queens in the state representation.
	 * 
	 * @return No value returned
	 */
	public static void buildQueens(){
		
		
		/* Build Rooks - these are hard coded - can be easily extended to fit any board */
		
		Player player = Player.PLAYER_NORTH; // give the NORTH player one queen.
		int[] newPosition = {0,3};
		Queen queen = new Queen(player, newPosition);
		Models.stateRepresentation[0][3] = queen;
		
		
		player = Player.PLAYER_SOUTH;	// give the SOUTH player one queen.
		int boardHeight = Models.board.getHeight();
		int[] newPosition2 = {boardHeight -1, 3};
		queen = new Queen(player, newPosition2);
		Models.stateRepresentation[boardHeight -1][3] = queen;
		

	}
	
	
	/**
	 * Initialize Kings in the state representation.
	 * 
	 * @return No value returned
	 */
	public static void buildKings(){
		
		
		/* Build Rooks - these are hard coded - can be easily extended to fit any board */
		
		Player player = Player.PLAYER_NORTH; // give the NORTH player one queen.
		int[] newPosition = {0,4};
		King king = new King(player, newPosition);
		Models.kingNorth = king;
		Models.stateRepresentation[0][4] = king;
		
		
		player = Player.PLAYER_SOUTH;	// give the SOUTH player one queen.
		int boardHeight = Models.board.getHeight();
		int[] newPosition2 = {boardHeight -1, 4};
		king = new King(player, newPosition2);
		Models.kingSouth = king;
		Models.stateRepresentation[boardHeight -1][4] = king;
		

	}
	
	
	/**
	 * Initialize Knights in the state representation.
	 * 
	 * @return No value returned
	 */
	public static void buildKnights(){
		
		
		/* Build Rooks - these are hard coded - can be easily extended to fit any board */
		
		
		Player player = Player.PLAYER_NORTH;	// give NORTH player two knights
		int[] newPosition = {0,1};
		Knight knight = new Knight(player, newPosition);
		Models.stateRepresentation[0][1] = knight;
		int[] newPosition1 = {0,6};
		knight = new Knight(player, newPosition1);
		Models.stateRepresentation[0][6] = knight;
		
		
		player = Player.PLAYER_SOUTH;		// give SOUTH player two knight
		int boardHeight = Models.board.getHeight();
		int[] newPosition2 = {boardHeight -1, 1};
		knight = new Knight(player, newPosition2);
		Models.stateRepresentation[boardHeight -1][1] = knight;
		int[] newPosition3 = {boardHeight -1, 6};
		knight = new Knight(player, newPosition3);
		Models.stateRepresentation[boardHeight -1][6] = knight;
		
	}
	
	
	
	
	
	/**
	 * Initialize Rooks in the state representation.
	 * 
	 * @return No value returned
	 */
	public static void buildRooks(){
		
		
		/* Build Rooks - these are hard coded - can be easily extended to fit any board */
		
		
		Player player = Player.PLAYER_NORTH;	// give NORTH player two Rooks
		int[] newPosition = {0,0};
		Rook rook = new Rook(player, newPosition);
		Models.stateRepresentation[0][0] = rook;
		int[] newPosition1 = {0,7};
		rook = new Rook(player, newPosition1);
		Models.stateRepresentation[0][7] = rook;
		
		
		player = Player.PLAYER_SOUTH;		// give SOUTH player two Rooks
		int boardHeight = Models.board.getHeight();
		int[] newPosition2 = {boardHeight -1, 0};
		rook = new Rook(player, newPosition2);
		Models.stateRepresentation[boardHeight -1][0] = rook;
		int[] newPosition3 = {boardHeight -1, 7};
		rook = new Rook(player, newPosition3);
		Models.stateRepresentation[boardHeight -1][7] = rook;
		
	}
	
	
	
	/**
	 * Initialize bishops in the state representation.
	 * 
	 * @return No value returned
	 */
	public static void buildBishops(){
		
		/* Build bishops */
		
		Player player = Player.PLAYER_NORTH;
		
		int[] newPosition = {0,2};
		Bishop bishop = new Bishop(player, newPosition);
		Models.stateRepresentation[0][2] = bishop;
		
		int[] newPosition1 = {0,5};
		bishop = new Bishop(player, newPosition1);
		Models.stateRepresentation[0][5] = bishop;
		
		player = Player.PLAYER_SOUTH;
		int boardHeight = Models.board.getHeight();
		
		int[] newPosition2 = {boardHeight -1, 2};
		bishop = new Bishop(player, newPosition2);
		Models.stateRepresentation[boardHeight -1][2] = bishop;
		
		int[] newPosition3 = {boardHeight -1, 5};
		bishop = new Bishop(player, newPosition3);
		Models.stateRepresentation[boardHeight -1][5] = bishop;
		
	}
	
	
	
	/**
	 * Initialize pawns in the state representation.
	 * 
	 * @return No value returned
	 */
	public static void buildPawns(){
		/* Build pawns */
		for(int pawnCounter = 0; pawnCounter < 16; pawnCounter++){
			
			/* give each player 8 pawns to start off with */
			Player player;
			int addPawnsYCoordinate; // these are the coordinates to which we add the pawns
			int addPawnsXCoordinate;
			if(pawnCounter < 8){
				addPawnsXCoordinate = pawnCounter;
				player = Player.PLAYER_NORTH;
				addPawnsYCoordinate = 0 + 1;
			} else{
				addPawnsXCoordinate = pawnCounter - 8;
				player = Player.PLAYER_SOUTH;
				addPawnsYCoordinate = (Models.board.getHeight() - 1) - 1; // yCoordinate to add pawns for SOUTH player.
			}
			
			/* create new Pawn objects and set State.stateRepresentation to reflect it */
			int[] newPawnCoordinates = {addPawnsYCoordinate, addPawnsXCoordinate};
			Pawn pawn = new Pawn(player, newPawnCoordinates);
			Models.stateRepresentation[addPawnsYCoordinate][addPawnsXCoordinate] = pawn;
			
		}
	}
	
	
	/**
	 * Initialize custom pieces in the state representation.
	 * 
	 * @return No value returned
	 */
	public static void buildCustom(){

		/* Build custom pieces */
		
		Player player = Player.PLAYER_NORTH;
		
		int[] newPosition = {1,0};
		Frog frog = new Frog(player, newPosition);
		Models.stateRepresentation[1][0] = frog;
		
		int[] newPosition1 = {1,7};
		Snail snail = new Snail(player, newPosition1);
		Models.stateRepresentation[1][7] = snail;
		
		player = Player.PLAYER_SOUTH;
		int boardHeight = Models.board.getHeight();
		
		int[] newPosition2 = {6, 0};
		frog = new Frog(player, newPosition2);
		Models.stateRepresentation[6][0] = frog;
		
		int[] newPosition3 = {6, 7};
		snail = new Snail(player, newPosition3);
		Models.stateRepresentation[6][7] = snail;
	}
	
	
	
	

}
