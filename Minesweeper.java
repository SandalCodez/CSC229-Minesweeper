import java.util.Random;
public class Minesweeper {

    // Data members
    private char[][] board;   // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over
    private Random rand;
    private int[][] numbers;

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.numbers = new int[rows][cols];
        this.gameOver = false;
        this.initializeBoard();
        this.placeMines();
        this.calculateNumbers();

        // Call methods to initialize the board and place mines
    }
    public boolean getGameOver(){
        return this.gameOver;
    }
    public void setGameOver(boolean status)
    {
        this.gameOver = status;

    }
    // Method to initialize the game board with empty values
    private void initializeBoard() {
        // TODO: Implement this method
        board = new char[rows][cols];       //creates a board of dashes
        for(int i =0; i <= rows-1; i++){
            for(int j = 0; j <= cols-1; j++){
                board[i][j] = '-';
                
            }
        }
        revealed = new boolean[rows][cols];     //creates a board of flase revealeds
        for(int i = 0; i < rows-1 ; i++){
            for (int j = 0; j <= cols -1; j++){
                revealed[i][j]= false;
            }
        }
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        // TODO: Implement this method              //places mines every within the board that is not on a border
        for(int i = 0; i < numMines; i++){
        rand = new Random();
        int randomRow = rand.nextInt(1,rows-1);
        int randomCol = rand.nextInt(1,cols-1);
        mines[randomRow][randomCol] = true;
        }
    }

    // Method to calculate numbers on the board for non-mine cells
    private void calculateNumbers() {
        // TODO: Implement this method
      
        for(int i = 0; i <= rows-1; i++){
            for(int j = 0; j <= cols - 1; j++){
                                                                //creates an array of zeros on the numbers board
                numbers[i][j]= 0;
            }
        }for(int i =0; i <= rows-1; i++){
            for(int j = 0; j <= cols - 1; j++){
                                                                //if a mine is found, increment every cell around the mine by 1 in the numbers board
                if(mines[i][j]==true){              
                    numbers[i-1][j] ++;
                    numbers[i-1][j-1] ++;
                    numbers[i-1][j+1]++;
                    numbers[i][j+1] ++;
                    numbers[i][j-1] ++ ;
                    numbers[i+1][j+1] ++ ;
                    numbers[i+1][j] ++ ;
                    numbers[i+1][j-1] ++ ;
                }
            }
    
        }for(int i =0; i <= rows-1; i++){
            for (int j = 0; j <= cols-1; j++){
                                                                //This takes the numbers array and prints the same value in the charracter board 
                if(numbers[i][j]==1){
                board[i][j]='1';
                }if(numbers[i][j]==2){
                    board[i][j]='2';
                }if(numbers[i][j]==3){
                    board[i][j]='3';
                }
            }
        }
    }
       
    

    // Method to display the current state of the board
    public void displayBoard() {
        // TODO: Implement this method

            for(int i = 0; i <= rows-1; i++){
                System.out.println();
                    for(int j = 0; j <= cols-1; j++){
                        System.out.print(board[i][j] + " ");
            }
        }
    }

    // Method to handle a player's move (reveal a cell or place a flag)
    public void playerMove(int row, int col, String action) {
        // TODO: Implement this method
        
        if(action=="flag"){
            this.flagCell(row,col);
        }
        if(action=="unflag"){
            this.unflagCell(row, col);
        }
        if((action=="reveal")&(mines[row][col] == true)){
            this.setGameOver(true);
        }if((action=="reveal")&(mines[row][col] != true)){
            this.revealCell(row,col);
           
        }
    }
    

    // Method to check if the player has won the game
    public boolean checkWin() {
        // TODO: Implement this method
        int count =0;
        for(int i =0; i <= rows-1; i++){
            for(int j = 0; j<=cols-1; j++){
                if((mines[i][j] != true & revealed[i][j]==true))
                    count++;
                if(mines[i][j] == true & board[i][j]=='F')
                    count++;
            }
        }if (count==(rows*cols)-numMines){
            return true;
        }
        return false;
    }

    // Method to check if the player has lost the game
    public boolean checkLoss(int row, int col) {
        // TODO: Implement this method
        if(mines[row][col]==true){
            setGameOver(true);
            return true;
        }
        return false;
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    private void revealCell(int row, int col) {
        //If a mine is found and all cells are revealed
        if(mines[row][col]==true){
            setGameOver(true);
            for(int i =0; i < rows-1;i++){
                for(int j = 0 ; j <= cols-1; j++){
                    revealed[i][j]=true;
                }
            }
            
        }for(int i =0; i <= rows-1; i++){
            for(int j =0; j<=cols-1;j++){
                if(!mines[row][col]){
                    while(numbers[i][j]<1){
                        revealed[i][j]=true;
                        break;
                    }
        
                }
            }
        }
        
    }
   

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) {
        // TODO: Implement this method
        board[row][col]='F';
        revealCell(row, col);
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) {
        // TODO: Implement this method
        board[row][col]='-';
    }
}
