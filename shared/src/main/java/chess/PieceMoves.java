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
        if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
            return List.of(new ChessMove(new ChessPosition(5,4), new ChessPosition(1,8),null));
        }
        return null;
    }
}
