package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMoves {
    private final ChessBoard board;
    private final ChessPosition myPosition;

    public PawnMoves(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
    }

    public Collection<ChessMove> getPawnMoves(){
        List<ChessMove> possibleMoves = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);
        int[] firstMove = {2,0};
        int[] forwardMove = {1,0};
        int[][] diagonalMoves = {{1,1}, {1,-1}};
        int startingposition = 2;
        int promotionTime = 7;
        if (piece.getTeamColor().equals(ChessGame.TeamColor.BLACK)){
            firstMove = new int[]{-2, 0};
            forwardMove = new int[]{-1, 0};
            diagonalMoves = new int[][]{{-1, 1}, {-1, -1}};
            startingposition = 7;
            promotionTime = 2;
        }
        ChessPiece.PieceType [] promotions = {
                ChessPiece.PieceType.QUEEN,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.ROOK
        };
        if (myPosition.getRow() == startingposition){
            ChessPosition moveFirst = myPosition.moveAdd(firstMove);
            ChessPosition moveForward = myPosition.moveAdd(forwardMove);
            if (board.emptyspace(moveFirst) && board.emptyspace(moveForward)){
                possibleMoves.add(new ChessMove(myPosition, moveFirst, null));
            }
        }
        ChessPosition moveForward = myPosition.moveAdd(forwardMove);
        if (board.emptyspace(moveForward)){
            if (myPosition.getRow() == promotionTime) {
                for (ChessPiece.PieceType promotion : promotions){
                    possibleMoves.add(new ChessMove(myPosition, moveForward, promotion));
                }
            }
            else {possibleMoves.add(new ChessMove(myPosition, moveForward, null));}
        }
        for (int[] move : diagonalMoves){
            ChessPosition moveDiagonal = myPosition.moveAdd(move);
            if (board.enemyPosition(moveDiagonal, piece)){
                if (myPosition.getRow() == promotionTime) {
                    for (ChessPiece.PieceType promotion : promotions) {
                        possibleMoves.add(new ChessMove(myPosition, moveDiagonal, promotion));
                    }
                }
                else {possibleMoves.add(new ChessMove(myPosition, moveDiagonal, null));}
            }
        }
        return possibleMoves;
    }
}
