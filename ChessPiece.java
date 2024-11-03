public abstract class ChessPiece {
    protected int row, col;
    protected boolean isWhite;

    public ChessPiece(int row, int col, boolean isWhite) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(int newRow, int newCol, ChessPiece[][] board);

    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean isWhite() { return isWhite; }
}
