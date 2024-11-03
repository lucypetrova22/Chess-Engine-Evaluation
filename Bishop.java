public class Bishop extends ChessPiece {
    public Bishop(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, ChessPiece[][] board) {
        if (Math.abs(newRow - row) == Math.abs(newCol - col)) {
            int rowDirection = Integer.signum(newRow - row);
            int colDirection = Integer.signum(newCol - col);
            int currentRow = row + rowDirection, currentCol = col + colDirection;
            while (currentRow != newRow || currentCol != newCol) {
                if (board[currentRow][currentCol] != null) {
                    return false;
                }
                currentRow += rowDirection;
                currentCol += colDirection;
            }
            return true;
        }
        return false;
    }
}

