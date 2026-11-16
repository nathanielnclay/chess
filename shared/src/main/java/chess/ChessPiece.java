package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece piece = (ChessPiece) o;
        return pieceColor == piece.pieceColor && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return new PieceMoves(board, myPosition).getMoves();
    }

    @Override
    public String toString() {
        if (pieceColor == ChessGame.TeamColor.WHITE){
            if (type == ChessPiece.PieceType.KING) {return "K";}
            if (type == ChessPiece.PieceType.QUEEN) {return "Q";}
            if (type == ChessPiece.PieceType.BISHOP) {return "B";}
            if (type == ChessPiece.PieceType.KNIGHT) {return "N";}
            if (type == ChessPiece.PieceType.ROOK) {return "R";}
            if (type == ChessPiece.PieceType.PAWN) {return "P";}
        }
        else {
            if (type == ChessPiece.PieceType.KING) {return "k";}
            if (type == ChessPiece.PieceType.QUEEN) {return "q";}
            if (type == ChessPiece.PieceType.BISHOP) {return "b";}
            if (type == ChessPiece.PieceType.KNIGHT) {return "n";}
            if (type == ChessPiece.PieceType.ROOK) {return "r";}
            if (type == ChessPiece.PieceType.PAWN) {return "p";}
        }
        return null;
    }
}
