package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMoves {
    private final ChessBoard board;
    private final ChessPosition myPosition;

    public KingMoves(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
    }

    public Collection<ChessMove> getKingMoves(){
        List<ChessMove> possibleMoves = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);
        int[][] moves = {{1,1}, {1,-1}, {-1,1}, {-1,-1}, {1,0}, {0,1}, {-1,0}, {0,-1}};
        for (int[] move : moves){
            ChessPosition moveTo = myPosition.moveAdd(move);
            if (board.positionAvailable(moveTo, piece)){
                possibleMoves.add(new ChessMove(myPosition, moveTo, null));
            }
        }
        return possibleMoves;}
}
