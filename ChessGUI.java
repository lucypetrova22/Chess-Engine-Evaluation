import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChessGUI extends Application {
    private ChessBoard chessBoard;
    private Button[][] boardButtons;
    private Label statusLabel;
    private int selectedRow = -1, selectedCol = -1;

    @Override
    public void start(Stage primaryStage) {
        chessBoard = new ChessBoard();
        boardButtons = new Button[8][8];
        GridPane gridPane = new GridPane();

        statusLabel = new Label("White's turn");

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button button = new Button();
                button.setMinSize(60, 60);
                updateButton(row, col, button);

                final int r = row;
                final int c = col;
                button.setOnAction(e -> handleBoardClick(r, c));
                boardButtons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }

        VBox vbox = new VBox(10, gridPane, statusLabel);
        Scene scene = new Scene(vbox, 480, 520);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess Game");
        primaryStage.show();
    }

    private void handleBoardClick(int row, int col) {
        if (selectedRow == -1 && selectedCol == -1) {
            ChessPiece selectedPiece = chessBoard.getPieceAt(row, col);
            if (selectedPiece != null && selectedPiece.isWhite() == chessBoard.isWhiteTurn()) {
                selectedRow = row;
                selectedCol = col;
                highlightLegalMoves(selectedPiece);
            }
        } else {
            boolean moved = chessBoard.movePiece(selectedRow, selectedCol, row, col);
            if (moved) {
                refreshBoard();
                if (chessBoard.isCheck(!chessBoard.isWhiteTurn())) {
                    statusLabel.setText("Check!");
                } else {
                    statusLabel.setText(chessBoard.isWhiteTurn() ? "White's turn" : "Black's turn");
                }

                String gameState = chessBoard.checkGameState();
                if (!gameState.equals("In Progress")) {
                    statusLabel.setText(gameState);
                }
            }
            selectedRow = -1;
            selectedCol = -1;
            clearHighlights();
        }
    }

    private void updateButton(int row, int col, Button button) {
        ChessPiece piece = chessBoard.getPieceAt(row, col);
        if (piece != null) {
            button.setText(piece.isWhite() ? "W" + piece.getClass().getSimpleName().charAt(0)
                    : "B" + piece.getClass().getSimpleName().charAt(0));
        } else {
            button.setText("");
        }
    }

    private void highlightLegalMoves(ChessPiece piece) {
        clearHighlights();
        for (int newRow = 0; newRow < 8; newRow++) {
            for (int newCol = 0; newCol < 8; newCol++) {
                if (piece.isValidMove(newRow, newCol, chessBoard.getBoard())) {
                    boardButtons[newRow][newCol].setStyle("-fx-background-color: yellow;");
                }

            }
        }
    }

    private void clearHighlights() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boardButtons[row][col].setStyle("");
            }
        }
    }

    private void refreshBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                updateButton(row, col, boardButtons[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
