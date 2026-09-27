package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KnightMoves {
    private final ChessBoard board;
    private final ChessPosition myPosition;

    public KnightMoves(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
    }

    public Collection<ChessMove> getKnightMoves(){
        List<ChessMove> possibleMoves = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);
        int[][] moves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] move : moves){
            ChessPosition moveTo = myPosition.moveAdd(move);
            if (board.positionAvailable(moveTo, piece)){
                possibleMoves.add(new ChessMove(myPosition, moveTo, null));
            }
        }
        return possibleMoves;}
}
