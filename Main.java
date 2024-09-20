import java.util.Scanner;

public class Main {
    // Main method to start the game
   
    public static void main(String[] args) {
        // Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(10, 10, 10);

        // Game loop
        while (!game.getGameOver()) {
            
            game.displayBoard();
            System.out.println(); 
            // Get player input for row, col, and action (reveal or flag)
            // For now, just simulate a move (to be replaced with real player input logic)
            // Scanner input = new Scanner(System.in);

            for(int x = 0; x < 10; x++){
                for (int y = 0; y< 10; y++){
                    game.playerMove(x,y,"reveal");
                    if (game.checkWin()) {
                        System.out.println("Congratulations! You've won the game.");
                        break;
                    }
                    if (game.checkLoss(x, y)) {
                        System.out.println("Game Over! You lost");
                        game.setGameOver(true);
                        break;
                    }

            // Check for win or loss conditions
            if (game.checkWin()) {
                System.out.println("Congratulations! You've won the game.");
                break;
            }
            if (game.checkLoss(x, y)) {
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
                break;
            }
        }
        }
        }
    }
}