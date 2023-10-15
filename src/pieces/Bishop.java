package pieces;



import board.Board;

/**
 * Bishop is a class that extends the Piece Class. It is responsible for the movement and Functionality of the Bishop
 * The Bishop can only move unlimited spaces diagonally 
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0 
 * @since 2022-03-22
 * 
 */
public class Bishop extends Piece {
	
	/**
	 * Default constructor for the Bishop it creates an Instance of the Bishop 
	 * @param type The type of piece it is 
	 * @param team The team the piece belongs to (true for White, false for Black)
	 */
	
	public Bishop(String type ,boolean team) {
		super(type, team);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method moves the Bishop and returns true if the Bishop is moved, false otherwise
	 * @param oldPos The original position of the Bishop
	 * @param newPos The end position of the Bishop
	 * @return boolean true if Bishop moves false otherwise 
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
	 * This method checks if the path for the Bishop is clear
	 * @param oldPos The old position of the Bishop 
	 * @param newPos The new position of the Bishop
	 * @return boolean true if Bishop Path is Clear false otherwise
	 */
	
	public boolean isPathClear(String oldPos, String newPos) {
		
		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		
		boolean right, up;
		
		// checks whether or not we are going right or left
		if(oldPosConv[0] < newPosConv[0]) {
			right = true;
		}
		else {
			right = false;
		}
		
		// checks whether or not we are going up or down
		if(oldPosConv[1] < newPosConv[1]) {
			up = false;
		}
		else {
			up = true;
		}

		int oldRow = oldPosConv[0];
		int newRow = newPosConv[0];
		
		int oldColumn = oldPosConv[1];	
		int newColumn = newPosConv[1];
		

		//Up And Left
		
		if(up && right) {
			
			//System.out.println("Up and Right");
			
			int y = oldColumn -1;
			
			for(int x = oldRow + 1; x <= newRow -1; x++) {
				
				if(Board.board[y][x] instanceof Piece) {
					return false;
				}
				
				y--;
				
			}
		}
		
		//Up and Left
		if(up && !right) {
			
			//System.out.println("Up and Left");
			
			int y = oldColumn -1;
			
			for(int x = oldRow -1; x >= newRow + 1; x--) {
				
				if(Board.board[y][x] instanceof Piece) {
					return false;
				}
				
				y--;
				
			}
		}
		
		//Down and Left
		if(!up && !right) {
			
			//System.out.println("Down and Left");
			
			int y = oldColumn + 1;
			
			for(int x = oldRow - 1; x >= newRow + 1; x--) {
				
				if(Board.board[y][x] instanceof Piece) {
					return false;
				}
				
				y++;
				
			}
		}
		
		//Down and Right
		if(!up && right) {
			
			//System.out.println("Down and Right");
			
			int y = oldColumn + 1;
			
			
			for(int x = oldRow + 1; x <= newRow - 1; x++) {
				
				//System.out.println("X: " + x + " Y: " + y);
				if(Board.board[y][x] instanceof Piece) {
					return false;
				}
				
				y++;
				
			}
		}
		
		
		return true;
	}
	
	/**
	 * This method checks if the move is valid or not 
	 * @param oldPos The old position of the Bishop 
	 * @param newPos the new position of the Bishop
	 * @return boolean true if Bishop moves are valid false otherwise
	 */
	
	public boolean isMoveValid(String oldPos, String newPos) {
		

		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		
		if(Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[newPosConv[1]][newPosConv[0]].getTeam() == Board.board[oldPosConv[1]][oldPosConv[0]].getTeam())
			return false;
		
		int originX = oldPosConv[0];
		int originY = oldPosConv[1];
		
		int destX = newPosConv[0];
		int destY = newPosConv[1];
			


		if((Math.abs(originX - destX) == Math.abs(originY - destY)) && isPathClear(oldPos, newPos)) {
			return true;
		}
		
		
		return false;
	}
	
	

}
