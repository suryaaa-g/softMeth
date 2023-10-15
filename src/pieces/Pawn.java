package pieces;




import board.Board;
import chess.Chess;
/**
 * The Pawn class extends the Piece class and consists of all the moves a Pawn can make
 * The Pawn can move one space vertically unless it is killing 
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0 
 * @since 2022-03-22
 * 
 */
public class Pawn extends Piece{
	
	/**
	 * The doingEnpassante variable lets us know if enpassant is being performed or not 
	 * Originally set to false and will be true once enpassant has been formed
	 */
	boolean doingEnpassante = false;
	
	/**
	 * Default constructor for the Pawn it creates an Instance of the Pawn 
	 * @param type The type of piece it is 
	 * @param team The team the piece belongs to (true for White, false for Black)
	 */
	public Pawn(String type, boolean team) {
		
		super(type, team);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method moves the Pawn and returns true if the Pawn is moved, false otherwise
	 * @param oldPos The original position of the Pawn
	 * @param newPos The end position of the Pawn
	 * @return boolean true if the Pawn moved false otherwise 
	 */
	public boolean move(String oldPos, String newPos) {
		
		
		
		if(isMoveValid(oldPos, newPos)) {
			

			didMove = true;
			
			int[] oldPosConv = Board.convertCords(oldPos);
			
			int[] newPosConv = Board.convertCords(newPos);
			
			if(doingEnpassante) {
				Board.board[newPosConv[1]][newPosConv[0]] = Board.board[oldPosConv[1]][oldPosConv[0]];
				Board.board[oldPosConv[1]][oldPosConv[0]] = null;
				if(team) {
					Board.board[newPosConv[1] + 1][newPosConv[0]] = null;
				}
				if(!team)
				{
					Board.board[newPosConv[1] - 1][newPosConv[0]] = null;
				}


				return true;
			}
			
			
			//System.out.println("White Y = " + newPosConv[0]);
			// White Reaches end of board, promote to queen.
			if(Board.board[oldPosConv[1]][oldPosConv[0]].team == true && newPosConv[1] == 0) {

				Board.board[newPosConv[1]][newPosConv[0]] = new Queen("wQ", true);
				Board.board[oldPosConv[1]][oldPosConv[0]] = null;
				return true;
				
			}
			
			//System.out.println("Black X = " + newPosConv[0]);
			// Black Reaches end of board, promote to queen.
			if(Board.board[oldPosConv[1]][oldPosConv[0]].team == false && newPosConv[1] == 7) {	
				
				Board.board[newPosConv[1]][newPosConv[0]] = new Queen("bQ", false);
				Board.board[oldPosConv[1]][oldPosConv[0]] = null;
				return true;
				
			}
			
			
			Board.board[newPosConv[1]][newPosConv[0]] = Board.board[oldPosConv[1]][oldPosConv[0]];
			
			
			Board.board[oldPosConv[1]][oldPosConv[0]] = null;
			
				
			
			return true;
			
		}
		
		else {
		
			return false;
		}
		
			
	}
	
	
	/**
	 * This method checks if the Pawn is valid for promotion and promotes it if it is
	 * The default promotion is a Queen
	 * @param oldPos The original position of the Pawn
	 * @param newPos The end position of the Pawn
	 * @param piece What piece the user wants to upgrade to
	 * @return boolean true if the Pawn promoted false otherwise
	 */
	public boolean Promotion(String oldPos, String newPos, String piece) {
		
	
		if(isMoveValid(oldPos, newPos)) {
			
			didMove = true;
			
			int[] oldPosConv = Board.convertCords(oldPos);
			
			int[] newPosConv = Board.convertCords(newPos);
			
			// White Reaches end of board, promote to queen.
			if( (Board.board[oldPosConv[1]][oldPosConv[0]].team == true && newPosConv[1] == 0)) {
				
				if(piece.equals("R")) {
				Board.board[newPosConv[1]][newPosConv[0]] = new Rook("wR", true);
				}
				if(piece.equals("N")) {
				Board.board[newPosConv[1]][newPosConv[0]] = new Knight("wN", true);
				}
				if(piece.equals("B")) {
				Board.board[newPosConv[1]][newPosConv[0]] = new Bishop("wB", true);
				}
				if(piece.equals("Q")) {
				Board.board[newPosConv[1]][newPosConv[0]] = new Queen("wQ", true);
				}
			
		
				Board.board[oldPosConv[1]][oldPosConv[0]] = null;
				return true;
			}
			
			// Black Reaches end of board, promote to queen.
			if( (Board.board[oldPosConv[1]][oldPosConv[0]].team == false && newPosConv[1] == 7)) {
				
				if(piece.equals("R")) {
				Board.board[newPosConv[1]][newPosConv[0]] = new Rook("bR", true);
				}
				if(piece.equals("N")) {
				Board.board[newPosConv[1]][newPosConv[0]] = new Knight("bN", true);
				}
				if(piece.equals("B")) {
				Board.board[newPosConv[1]][newPosConv[0]] = new Bishop("bB", true);
				}
				if(piece.equals("Q")) {
				Board.board[newPosConv[1]][newPosConv[0]] = new Queen("bQ", true);
				}
			
		
				Board.board[oldPosConv[1]][oldPosConv[0]] = null;
				return true;
			}
		
		
			return false;
		}
		return false;
	}
	
	/**
	 * This method makes sure if the path of the Pawn is clear or not
	 * @param oldPos The old position of the Pawn
	 * @param newPos The new position of the Pawn
	 * @return boolean true if the path is clear, false otherwise
	 */
	public boolean isPathClear(String oldPos, String newPos) {
		
		char oldRow = oldPos.substring(0,1).charAt(0);
		
		int oldColumn = Integer.parseInt(oldPos.substring(1));
		
		char newRow = newPos.substring(0,1).charAt(0);
		
		int newColumn = Integer.parseInt(newPos.substring(1));
		
		return true;
			
			
	}
	
	/**
	 * This method checks all the valid moves for the Pawn 
	 * @param oldPos The original position of the Pawn
	 * @param newPos The end position of the Pawn
	 * @return boolean true if the Pawn move is valid false otherwise
	 */
		
	public boolean isMoveValid(String oldPos, String newPos) {
		
		char oldRow = oldPos.substring(0,1).charAt(0);
		
		int oldColumn = Integer.parseInt(oldPos.substring(1));
		
		char newRow = newPos.substring(0,1).charAt(0);
		
		int newColumn = Integer.parseInt(newPos.substring(1));
		
		
		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		
		// White Pawn
		if(team) {

			if(!didMove() && oldRow == newRow && newColumn - oldColumn == 2 && Board.board[newPosConv[1] + 1][newPosConv[0]] == null) {
				
				if(!Chess.testMove) {
					en_passantable = true;
					enPassTurn = Chess.turnCount;
				}
				return true;
	
			}
			
			if(oldRow == newRow && newColumn - oldColumn == 1 && (!(Board.board[newPosConv[1]][newPosConv[0]] instanceof Piece))){
				
				en_passantable = false;
				return true;
				
				
			}
			
			if(newColumn - oldColumn > 0 && Board.board[newPosConv[1]][newPosConv[0]] instanceof Piece && newColumn - oldColumn == 1 && (Math.abs(newRow - oldRow)) == 1) {
				
				en_passantable = false;
				return true;
				
			}
			
			//en_passante
			if( newPosConv[1] < 7
					&& oldPosConv[1] == 3
					&&(Board.board[newPosConv[1] + 1][newPosConv[0]] != null
					&&(Board.board[newPosConv[1] + 1][newPosConv[0]].team == false 
					&& Board.board[newPosConv[1] + 1][newPosConv[0]] instanceof Pawn && 
					Board.board[newPosConv[1] + 1][newPosConv[0]].en_passantable == true)
					&& (Math.abs(oldRow - newRow) == 1) && (newColumn - oldColumn == 1))
					&& Math.abs(Chess.turnCount - Board.board[newPosConv[1] + 1][newPosConv[0]].getEnPassTurn()) == 1
					
					) {
				
				doingEnpassante = true;
				return true;
				
			}
			
			
			
			

			
			else {
				
				return false;
				
			}
			
			

		}
		
		// Black Pawn
		if(!team) {
			
			if(Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[newPosConv[1]][newPosConv[0]].team == false) {
				
				
				
				return false;
				
			}
			
			if(!didMove() && oldRow == newRow && (oldColumn - newColumn == 2) && Board.board[newPosConv[1] - 1][newPosConv[0]] == null) {
				
				if(!Chess.testMove) {
					en_passantable = true;
					enPassTurn = Chess.turnCount;
				}
				return true;
				
			}
			
			if(oldRow == newRow && oldColumn - newColumn == 1 && (!(Board.board[newPosConv[1]][newPosConv[0]] instanceof Piece))){
				
				en_passantable = false;
				return true;
				
			}
			
			if(Board.board[newPosConv[1]][newPosConv[0]] instanceof Piece && oldColumn - newColumn == 1 && (Math.abs(newRow - oldRow)) == 1) {
				
				en_passantable = false;
				return true;
				
			}
			
			//en_passante
			if( 	newPosConv[1] > 0
					&& oldPosConv[1] == 4
					&&(Board.board[newPosConv[1] - 1][newPosConv[0]] != null
					&&(Board.board[newPosConv[1] - 1][newPosConv[0]].team == true 
					&& Board.board[newPosConv[1] - 1][newPosConv[0]] instanceof Pawn && 
					Board.board[newPosConv[1] - 1][newPosConv[0]].en_passantable == true)
					&& (Math.abs(oldRow - newRow) == 1) && (oldColumn - newColumn == 1))
					&& Math.abs(Chess.turnCount - Board.board[newPosConv[1] - 1][newPosConv[0]].getEnPassTurn()) == 1
					) {
				
				doingEnpassante = true;
				return true;
				
			}
			
			
			
			
			else {
				
				return false;
				
			}
		}
		
		return false;
		
	}
}