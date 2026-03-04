package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	
	
	public ChessPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Erro ao instanciar ChessPosition.Valores válidos de a1 até h8");
		}
		this.column = column;
		this.row = row;
	}


	public char getColumn() {
		return column;
	}


	public int getRow() {
		return row;
	}


	
	protected Position toPosition() {
		return new Position( 8 - row,column - 'a'); // // Converte posição do xadrez (ex: a1) para índices da matriz
		//Exemplo: a1 // 'a' - 'a' = 0  entao Coluna 0    // 8-1 = linha 7
		//matrizes começam a coluna e linha em 0.
	}
	
	protected static ChessPosition fromPosition(Position position) { 
		return new ChessPosition((char)('a' - position.getColumn()),8 - position.getRow()); 
		// Converte índice de matriz para posição de xadrez.
	}
	
	@Override
	public String toString() {
		return "" + column+row;
	}
	
	
	
	

}
