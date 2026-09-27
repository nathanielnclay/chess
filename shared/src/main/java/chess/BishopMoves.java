package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMoves {

    private final ChessBoard board;
    private final ChessPosition myPosition;

    public BishopMoves(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
    }

    public Collection<ChessMove> getBishopMoves(){
        List<ChessMove> possibleMoves = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);
        int[][] moves = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};
        for (int[] move : moves){
            ChessPosition currentPosition = myPosition;
            for (int i = 1; i <= 8; i++){
                ChessPosition moveTo = currentPosition.moveAdd(move);
                if (board.positionAvailable(moveTo, piece)){
                    possibleMoves.add(new ChessMove(myPosition, moveTo, null));
                }else {break;}
                if (board.enemyPosition(moveTo,piece)){break;}
                currentPosition = moveTo;
            }
        }
        return possibleMoves;}
}
