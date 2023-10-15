package pieces;


import board.Board;

/**
 * The Knight class extends the Piece class and consists of all the movements and functionality for the Knight
 * The Knight can only move 4 spaces in any direction and in an L shape consisting of 2 spaces vertically and one space horizontally OR 2 spaces horizontally and one space vertically 
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0 
 * @since 2022-03-22
 */
public class Knight extends Piece{
	
	/**
	 * Default constructor for the Knight it creates an Instance of the Knight 
	 * @param type The type of piece it is 
	 * @param team The team the piece belongs to (true for White, false for Black)
	 */
	public Knight(String type, boolean team) {
		super(type, team);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method moves the Knight and returns true if the Knight is moved, false otherwise
	 * @param oldPos The original position of the Knight
	 * @param newPos The end position of the Knight
	 * @return boolean true if the Knight moved false otherwise
	 */
	public boolean move(String oldPos, String newPos) {
		
		if(isMoveValid(oldPos, newPos)) {
			
			didMove = true;
			
			int[] oldPosConv = Board.convertCords(oldPos);
			
			int[] newPosConv = Board.convertCords(newPos);
			
			
			Board.board[newPosConv[1]][newPosConv[0]] = Board.board[oldPosConv[1]][oldPosConv[0]];
			
			
			Board.board[oldPosConv[1]][oldPosConv[0]] = null;
			
			return true;
			
		}
		
		else {
		
			return false;
			
		}
		
	}
	
	/**
	 * This method makes sure if the path of the Knight is clear or not
	 * @param oldPos The old position of the Knight
	 * @param newPos The new position of the Knight
	 * @return boolean true if the path is clear, false otherwise
	 */
	public boolean isPathClear(String oldPos, String newPos) {
	
		return true;
	}
	
	/**
	 * This method checks all the valid moves for the Knight 
	 * @param oldPos The original position of the Knight
	 * @param newPos The end position of the Knight
	 * @return boolean true if the Knight move is valid false otherwise
	 */
	public boolean isMoveValid(String oldPos, String newPos) {
		
		char oldRow = oldPos.substring(0,1).charAt(0);
		
		int oldColumn = Integer.parseInt(oldPos.substring(1));
		
		char newRow = newPos.substring(0,1).charAt(0);
		
		int newColumn = Integer.parseInt(newPos.substring(1));
		
		
		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		
		if(Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[newPosConv[1]][newPosConv[0]].getTeam() == Board.board[oldPosConv[1]][oldPosConv[0]].getTeam())
			return false;
		
		//Trying to jump to piece occupied by same color team
//		if(Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[oldPosConv[1]][oldPosConv[0]] != null && 
//				Board.board[newPosConv[1]][newPosConv[0]].team == Board.board[oldPosConv[1]][oldPosConv[0]].team) {
//			
//			return false;
//			
//		}
		
		// Left Top/Bottom
		if(oldRow - newRow == 1 && (newColumn - oldColumn == 2 || oldColumn - newColumn == 2)) {
			

			return true;

		}
		
		// Left Left
		if(oldRow - newRow == 2 && (newColumn - oldColumn == 1 || oldColumn - newColumn == 1)) {
			

			return true;

		}
		
		// Right Top/Bottom
		if(newRow - oldRow == 1 && (newColumn - oldColumn == 2 || oldColumn - newColumn == 2)) {
			

			return true;

		}
		
		// Right Right
		if(newRow - oldRow == 2 && (newColumn - oldColumn == 1 || oldColumn - newColumn == 1)) {
			

			return true;

		}
		
		
		return false;
		
	}

}
