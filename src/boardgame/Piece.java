package boardgame;

public class Piece {
	
	protected Position position; //Protected Significa que so pode ser compartilhado entre o pacote "boardgame" e as subclasses.
	private Board board;
	
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}


	protected Board getBoard() {
		return board;
	}	

}
