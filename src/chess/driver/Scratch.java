package chess.driver;

import chess.models.Models;
import chess.resources.Bishop;
import chess.resources.Board;
import chess.resources.ChessPiece;
import chess.resources.King;
import chess.resources.Knight;
import chess.resources.Pawn;
import chess.resources.Player;
import chess.resources.Queen;
import chess.resources.Rook;
/**
 * 
 * Scratch - a class that serves as scratch paper
 * 
 * @author arkenibrahim
 *
 */
public class Scratch {

	public Scratch() {
		Board b = new Board(8,8);
		Models.board = b;
		Models.stateRepresentation = new ChessPiece[b.getHeight()][b.getWidth()];
		
		
		int[] newCoords = {0, 4}; 
		King king = new King(Player.PLAYER_NORTH, newCoords);
		Models.stateRepresentation[0][4] = king;
		Models.kingNorth = king;
		
		int[] newCoords1 = {4, 4}; 
		Rook rook = new Rook(Player.PLAYER_SOUTH, newCoords1);
		Models.stateRepresentation[4][4] = rook;
		
		
//		ChessPiece pawn = State.stateRepresentation[1][3];
//		ChessPiece knight = State.stateRepresentation[0][6];
//		ChessPiece king = State.stateRepresentation[0][4];
//		
//
		int[] moveToPosition2 = {2,4};
		rook.attemptMove(moveToPosition2); // move pawn forward
		
		int[] moveToPosition3 = {2,6};
		rook.attemptMove(moveToPosition3);
//		
//		int[] moveToPosition3 = {1,3};
//		queen.attemptMove(moveToPosition3); 
//		
//		int[] moveToPosition4 = {2,7};
//		knight.attemptMove(moveToPosition4); 
//		
//		int[] moveToPosition5 = {0,4};
//		king.attemptMove(moveToPosition5); 
		
//		int[] moveToPositio2n = {4, 1};
//		pawn.attemptMove(moveToPositio2n);
//		int[] moveToPositio3n = {5, 1};
//		pawn.attemptMove(moveToPositio3n);
//		int[] moveToPositwio3n = {6, 1};
//		pawn.attemptMove(moveToPositwio3n);
		
		
		for(int i = 0; i < Models.stateRepresentation.length; i++){
			for(int j = 0; j < Models.stateRepresentation[i].length; j++){
				
				ChessPiece cp = Models.stateRepresentation[i][j];
				
				if(cp == null)
					System.out.print("[ ]");
				if(cp instanceof Pawn) 
					System.out.print("[P]");
				if(cp instanceof Bishop) 
					System.out.print("[B]");
				if(cp instanceof Rook) 
					System.out.print("[R]");
				if(cp instanceof Queen) 
					System.out.print("[Q]");
				if(cp instanceof Knight) 
					System.out.print("[N]");
				if(cp instanceof King) 
					System.out.print("[K]");
			}
			System.out.println();
		}
	}

}
