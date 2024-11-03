public class ChessBoard {
    private ChessPiece[][] board; 
    private boolean isWhiteTurn; 

    public ChessBoard() {
        board = new ChessPiece[8][8];
        isWhiteTurn = true; 
        setupBoard();
    }
    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
    
    private void setupBoard() {
        
        board[0][0] = new Rook(0, 0, true);
        board[0][1] = new Knight(0, 1, true);
        board[0][2] = new Bishop(0, 2, true);
        board[0][3] = new Queen(0, 3, true);
        board[0][4] = new King(0, 4, true);
        board[0][5] = new Bishop(0, 5, true);
        board[0][6] = new Knight(0, 6, true);
        board[0][7] = new Rook(0, 7, true);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(1, i, true);
        }

       
        board[7][0] = new Rook(7, 0, false);
        board[7][1] = new Knight(7, 1, false);
        board[7][2] = new Bishop(7, 2, false);
        board[7][3] = new Queen(7, 3, false);
        board[7][4] = new King(7, 4, false);
        board[7][5] = new Bishop(7, 5, false);
        board[7][6] = new Knight(7, 6, false);
        board[7][7] = new Rook(7, 7, false);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(6, i, false);
        }
    }

 
    public ChessPiece getPieceAt(int row, int col) {
        return board[row][col];
    }

    
    public ChessPiece[][] getBoard() {
        return board;
    }

    
    public boolean validateMove(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece piece = board[startRow][startCol];
        if (piece == null || piece.isWhite() != isWhiteTurn) {
            return false;
        }
        return piece.isValidMove(endRow, endCol, board);
    }

    // Execute a move if it is valid
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (validateMove(startRow, startCol, endRow, endCol)) {
            ChessPiece piece = board[startRow][startCol];
            board[endRow][endCol] = piece;
            board[startRow][startCol] = null;
            piece.row = endRow;
            piece.col = endCol;

            // Toggle turn
            isWhiteTurn = !isWhiteTurn;
            return true;
        }
        return false;
    }

   
    public boolean isCheck(boolean whiteKing) {
        int kingRow = -1, kingCol = -1;
   
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                ChessPiece piece = board[r][c];
                if (piece instanceof King && piece.isWhite() == whiteKing) {
                    kingRow = r;
                    kingCol = c;
                    break;
                }
            }
        }

        
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                ChessPiece piece = board[r][c];
                if (piece != null && piece.isWhite() != whiteKing && piece.isValidMove(kingRow, kingCol, board)) {
                    return true; 
                }
            }
        }
        return false;
    }

    // Check if the game is over by determining checkmate or stalemate
    public String checkGameState() {
        boolean inCheck = isCheck(isWhiteTurn);

        // Check if there are any legal moves for the current player
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board[row][col];
                if (piece != null && piece.isWhite() == isWhiteTurn) {
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            if (piece.isValidMove(newRow, newCol, board)) {
                                ChessPiece originalTarget = board[newRow][newCol];
                                // Temporarily make the move
                                board[newRow][newCol] = piece;
                                board[row][col] = null;

                                boolean stillInCheck = isCheck(isWhiteTurn);

                                // Undo the move
                                board[row][col] = piece;
                                board[newRow][newCol] = originalTarget;

                                if (!stillInCheck) {
                                    return "In Progress"; 
                                }
                            }
                        }
                    }
                }
            }
        }

        
        return inCheck ? "Checkmate" : "Stalemate";
    }

  
    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board[row][col];
                if (piece == null) {
                    System.out.print("- ");
                } else if (piece.isWhite()) {
                    System.out.print(piece.getClass().getSimpleName().charAt(0) + " ");
                } else {
                    System.out.print(piece.getClass().getSimpleName().charAt(0) + " ");
                }
            }
            System.out.println();
        }
    }
}
