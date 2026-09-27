package chess;

import java.util.Collection;
import java.util.List;

public class PieceMoves {
    private final ChessBoard board;
    private final ChessPosition myPosition;

    public PieceMoves(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
    }

    public Collection<ChessMove> getMoves(){
        ChessPiece piece = board.getPiece(myPosition);
        if (piece.getPieceType() == ChessPiece.PieceType.KING) {
            return new KingMoves(board, myPosition).getKingMoves();
        }
        if (piece.getPieceType() == ChessPiece.PieceType.QUEEN) {
            return new QueenMoves(board, myPosition).getQueenMoves();
        }
        if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
            return new BishopMoves(board, myPosition).getBishopMoves();
        }
        if (piece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
            return new KnightMoves(board, myPosition).getKnightMoves();
        }
        if (piece.getPieceType() == ChessPiece.PieceType.ROOK) {
            return new RookMoves(board, myPosition).getRookMoves();
        }
        if (piece.getPieceType() == ChessPiece.PieceType.PAWN) {
            return new PawnMoves(board, myPosition).getPawnMoves();
        }
        return null;
    }
}
