public class Queen extends ChessPiece {
    public Queen(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, ChessPiece[][] board) {
        Rook rook = new Rook(row, col, isWhite);
        Bishop bishop = new Bishop(row, col, isWhite);
        return rook.isValidMove(newRow, newCol, board) || bishop.isValidMove(newRow, newCol, board);
    }
}

