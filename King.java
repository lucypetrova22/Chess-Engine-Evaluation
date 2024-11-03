public class King extends ChessPiece {
    public King(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, ChessPiece[][] board) {
        return Math.abs(newRow - row) <= 1 && Math.abs(newCol - col) <= 1;
    }
}

