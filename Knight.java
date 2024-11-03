public class Knight extends ChessPiece {
    public Knight(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, ChessPiece[][] board) {
        int rowDiff = Math.abs(newRow - row);
        int colDiff = Math.abs(newCol - col);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}

