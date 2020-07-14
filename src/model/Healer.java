package model;

import java.util.ArrayList;

import com.google.java.contract.*;

public class Healer extends Piece{

	public Healer(int health, Square currentSquare, int team) {
		super(health, currentSquare, team);
	}
	@Requires({"row <=7 && row >= 0", "column <= 5 && column >= 0"})
	@Ensures({"currentRow <=7 && currentRow >= 0", "currentColumn <= 5 && currentColumn >= 0"})
	private boolean moveCheck(int row, int column) {
		int currentRow = this.getCurrentSquare().getRow();
		int currentColumn = this.getCurrentSquare().getColumn();
		if (row == currentRow || row == currentRow + 1 || row == currentRow - 1) {
			if (column == currentColumn || column == currentColumn + 1 || column == currentColumn - 1 ) {
			return true;
			}
		}
		return false;
	}
	@Override
	@Requires({"row <=7 && row >= 0", "column <= 5 && column >= 0"})
	@Ensures("Board.squares[row][column] != null")
	public boolean move(int row, int column) {
		if (moveCheck(row, column)) {
			this.setCurrentSquare(Board.squares[row][column]);
				return true;
		}
		else {
			return false;
		}
	}
	public String toString() {
		return "healer";
		}
	
	@Ensures({"currentRow <=7 && currentRow >= 0", "currentColumn <= 5 && currentColumn >= 0"})
	public ArrayList<Square> prepareSpell() {
		int currentRow = this.getCurrentSquare().getRow();
		int currentColumn = this.getCurrentSquare().getColumn();
		ArrayList<Square> squareCheck = new ArrayList<Square>();
		
		for (int i=0; i<2;i++) {
			if (currentRow+i <= Board.squares.length-1) {
				squareCheck.add(Board.squares[currentRow+i][currentColumn]);
			}
		}
		return squareCheck;
	}
	
	public boolean castSpell(ArrayList<Square> targetSquares) {
		for (Square s: targetSquares) {
			for (Piece j: Board.pieceSet) {
				if (j.getTeam() == this.getTeam() && j.getCurrentSquare() == s) {
					j.increaseHealth(75);
				}
			}
			
		}
	return true;
	}	
}

