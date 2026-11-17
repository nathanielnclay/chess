package chess;

// comment made will work on this later time spent thinking

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard Board;
    private int turn;
    public ChessGame() {
        Board = new ChessBoard();
        Board.resetBoard();
        turn = 0;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        if( turn%2 == 0 ){return TeamColor.WHITE;}
        else {return TeamColor.BLACK;}
    }

    /**
     * Sets which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        if (team == TeamColor.WHITE) {turn = 0;}
        else {turn = 1;}
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets all valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        List<ChessMove> movesValid = new ArrayList<>();
        if(Board.getPiece(startPosition) == null){return movesValid;}
        TeamColor team = Board.getPiece(startPosition).getTeamColor();
        Collection<ChessMove> moves = Board.getPiece(startPosition).pieceMoves(Board, startPosition);
        for (ChessMove move : moves){
            ChessBoard copyBoard = Board.clone();
            movePiece(move, copyBoard);
            if(!checkCheck(team, copyBoard)){
                movesValid.add(move);
            }
        }
        return movesValid;
    }

    /**
     * Makes a move in the chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startposition = move.getStartPosition();
        ChessPiece piece = Board.getPiece(startposition);
        if (piece == null){throw new InvalidMoveException("No piece here.");}
        if (piece.getTeamColor() != getTeamTurn()){throw new InvalidMoveException("Not your turn");}
        Collection<ChessMove> movesValid = validMoves(startposition);
        for(ChessMove moveValid : movesValid){
            if(move.equals(moveValid)){
                movePiece(move, Board);
                turn += 1;
                return;
            }
        }
        throw new InvalidMoveException("This move is not legal.");
    }

    public void movePiece(ChessMove move, ChessBoard board){
        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();
        TeamColor teamColor = board.getPiece(start).getTeamColor();
        ChessPiece.PieceType pomote = move.getPromotionPiece();
        if(pomote == null){
            ChessPiece piece = board.getPiece(start);
            board.addPiece(end, piece);
        }
        else {
            ChessPiece piece = new ChessPiece(teamColor, pomote);
            board.addPiece(end, piece);
        }
        board.addPiece(start, null);
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        return checkCheck(teamColor, Board);
    }

    public boolean checkCheck(TeamColor teamColor, ChessBoard board) {
        ChessPosition k_position = null;
        kingloop:
        for (int row = 1; row <= 8; row++){
            for (int col = 1; col <= 8; col++) {
                k_position = new ChessPosition(row, col);
                ChessPiece piece = board.getPiece(k_position);
                if (piece != null) {
                    if (piece.getPieceType() == ChessPiece.PieceType.KING && piece.getTeamColor() == teamColor) {
                        break kingloop;
                    }
                }
            }
        }
        for (int row = 1; row <= 8; row++){
            for (int col = 1; col <= 8; col++){
                ChessPosition e_position = new ChessPosition(row, col);
                ChessPiece piece = board.getPiece(e_position);
                if (piece != null) {
                    if (piece.getTeamColor() != teamColor) {
                        Collection<ChessMove> moves = piece.pieceMoves(board, e_position);
                        for (ChessMove move : moves) {
                            if (move.getEndPosition().equals(k_position)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        return isInCheck(teamColor) && noValidMoves(teamColor);
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        return !isInCheck(teamColor) && noValidMoves(teamColor);
    }

    public boolean noValidMoves(TeamColor teamColor){
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition position = new ChessPosition(row, col);
                ChessPiece piece = Board.getPiece(position);
                if (piece.getTeamColor() == teamColor) {
                    Collection<ChessMove> moves = validMoves(position);
                    if (moves.size() != 0) {return false;}
                }
            }
        }
        return true;
    }
    /**
     * Sets this game's chessboard to a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        Board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {return Board;}


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return turn == chessGame.turn && Objects.equals(getBoard(), chessGame.getBoard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoard(), turn);
    }
}
