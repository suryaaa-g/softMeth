package pieces;



import board.Board;
import chess.Chess;


/**
 * King is a class that extends the Piece Class. It is responsible for the movement and Functionality of the King
 * The King can only move one space in either direction and has the ability to castle  
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0 
 * @since 2022-03-22
 * 
 */
public class King extends Piece {
	
	/**
	 * The canCastle variable checks if castling will be possible or not
	 * It is originally set to false 
	 * It will be true once the conditions for castling have been met
	 */
	boolean canCastle = false;
	
	/**
	 * The castleSide variable checks what side we can castle on 
	 * 0 = no side
	 * 1 = right side
	 * 2 = left side
	 */
	int castleSide = 0;
	
	/**
	 * Default constructor for the King it creates an Instance of the King 
	 * @param type The type of piece it is 
	 * @param team The team the piece belongs to (true for White, false for Black)
	 */
	public King(String type, boolean team) {
		super(type, team);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method moves the King and returns true if the King is moved, false otherwise
	 * @param oldPos The original position of the King
	 * @param newPos The end position of the King
	 * @return boolean true if the King moved false otherwise 
	 */
	public boolean move(String oldPos, String newPos) {
		
		
		castleSide = 0;
		

		

		if(isMoveValid(oldPos, newPos)) {
			int[] oldPosConv = Board.convertCords(oldPos);		
			int[] newPosConv = Board.convertCords(newPos);

			
			if(canCastle) {
				Board.board[newPosConv[1]][newPosConv[0]] = Board.board[oldPosConv[1]][oldPosConv[0]];
				Board.board[oldPosConv[1]][oldPosConv[0]] = null;
				didMove = true;
				
				//Move rook Top Left
				if(newPosConv[1] == 0 && newPosConv[0] == 2) {
					Board.board[0][0].didMove = true;
					Board.board[0][3] = Board.board[0][0];
					Board.board[0][0] = null;
				}
				
				//Move rook Top Right
				if(newPosConv[1] == 0 && newPosConv[0] == 6) {
					Board.board[0][7].didMove = true;
					Board.board[0][5] = Board.board[0][7];
					Board.board[0][7] = null;
				}
				
				//Move rook Bottom Left
				if(newPosConv[1] == 7 && newPosConv[0] == 2) {
					Board.board[7][0].didMove = true;
					Board.board[7][3] = Board.board[7][0];
					Board.board[7][0] = null;
				}
				
				//Move rook Bottom Right
				if(newPosConv[1] == 7 && newPosConv[0] == 6) {
					Board.board[7][7].didMove = true;
					Board.board[7][5] = Board.board[7][7];
					Board.board[7][7] = null;
				}
				return true;
				
			}


			Board.board[newPosConv[1]][newPosConv[0]] = Board.board[oldPosConv[1]][oldPosConv[0]];

			Board.board[oldPosConv[1]][oldPosConv[0]] = null;
			didMove = true;
			return true;



		}
		else {
			return false;
		}

	}
	
	
	
	/**
	 * This method makes sure if the path of the King is clear or not
	 * @param oldPos The old position of the King
	 * @param newPos The new position of the King
	 * @return boolean true if the path is clear, false otherwise
	 */
	public boolean isPathClear(String oldPos, String newPos) {
	
		return true;
	}
	
	
	/**
	 * This method checks all the valid moves for the King 
	 * @param oldPos The original position of the King
	 * @param newPos The end position of the King
	 * @return boolean true if the King move is valid false otherwise
	 */
	public boolean isMoveValid(String oldPos, String newPos) {
		

		
		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		
		int originX = oldPosConv[0];
		int originY = oldPosConv[1];
		
		int destX = newPosConv[0];
		int destY = newPosConv[1];
		
		if(Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[newPosConv[1]][newPosConv[0]].getTeam() == Board.board[oldPosConv[1]][oldPosConv[0]].getTeam())
			return false;
		
		
		
		
		
		// Castling code 
		//System.out.println(Board.board[destY][originY].getType());
		
		if(Math.abs(destX - originX) == 2) {
			if(originY == destY && destX == originX + 2) { // going to the right
				//System.out.println("checking" + originX + " " + originY);

				//System.out.println("destY : " + destY);
				if(didMove == false && Board.board[originY][7] != null  && Board.board[originY][7].didMove == false) {
					//System.out.println("tested : " + destX + " " +  destY);

					if(Board.board[originY][5] == null && Board.board[originY][6] == null) {
						
						if(!Chess.testMove) {
							canCastle = true;
							castleSide = 2;
						}
						//Board.board[destY][7].didMove = true;
						return true;
					}
				}
				else {
					canCastle = false;
					
				}
			}
			if(originY == destY && destX == originX - 2) { // going to left
				if(didMove == false && Board.board[originY][0] != null  && Board.board[originY][0].didMove == false) {
					if(Board.board[originY][1] == null && Board.board[originY][2] == null && Board.board[originY][1] == null && Board.board[originY][2] == null) {
						
						if(!Chess.testMove) {
							canCastle = true;
							castleSide = 1;
						}
						//Board.board[destY][0].didMove = true;
						return true;
					}
				}
			}
			else {
				canCastle = false;
			}
			
		}

		
		if(originX == destX && Math.abs(destY - originY) == 1) { //moving up or down
			return true;
		}
		
		
		
		
		// left right
		
		if(originY == destY && Math.abs(destX - originX) == 1) {
			return true;
		}
		
		
		//diagonal
		if( Math.abs(destX - originX) == 1 && Math.abs(destY - originY) == 1) {
			return true;
		}
		
		

		
		
		
		
		return false;
		
		
		
		
		
		
		
		
		
	}
	

	
	
	
	
	
	
	

}