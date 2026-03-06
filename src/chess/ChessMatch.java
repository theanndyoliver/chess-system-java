package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	public ChessPiece [][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //Aqui ele cria uma matriz com o mesmo tamanho do tabuleiro.
		
		for(int i = 0 ; i < board.getRows() ; i++) {
			for(int j = 0 ; j < board.getColumns();j++) {
				mat[i][j]= (ChessPiece)board.piece(i, j); //Downcasting de piece(classe principal) para ChessPiece(subclasse)
				//Esse codigo verifica todas posiçoes do tabuleiro(linha por linha e coluna por coluna) e retorna as peças que tiver nele.
			}
		}
		return mat;
		
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition(); //Converte a posição de xadrez de origem para indice de matriz.
		Position target = targetPosition.toPosition(); //Converte a posição de xadrez de destino para indice de matriz.
		validateSourcePosition(source); // Verifica se tem uma peça naquela posição de origem.
		validateTargetPosition(source,target);
		Piece capturedPiece = makeMove(source,target); //Move a peça de origem para o local de destino capturando a peça no destino se tiver uma.
		return (ChessPiece) capturedPiece; // Faz o downcasting de Piece para ChessPiece(sub-classe) e retorna ela.
		
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //Remove a peça daquela posição e armazena na váriavel P.
		Piece capturedPiece = board.removePiece(target); //Remove a peça capturada e armazena nessa váriavel.
		board.placePiece(p, target); // Coloca a peça da váriavel P na posição indicada pelo Position target.
		return capturedPiece; // Retorna a peça capturada.
	}
	
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem.");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Não existe movimento possível para a peça escolhida!");
		}
	}
	
	private void validateTargetPosition(Position source,Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("A peça escolhida não pode se mover para a posição de destino.");
			
		}
	}
	
	private void placeNewPiece(char column,int row,ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column,row).toPosition());
		
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));

}
}
