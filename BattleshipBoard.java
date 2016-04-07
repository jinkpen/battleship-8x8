import java.util.ArrayList;
/* Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
   Due: 22 April 2016
   Class: CSCI 1101, Section 01

*/
public class BattleshipBoard {

   //Attributes
   private int row;
   private int col;
   private Block[][] board;            //Game board 
   private ArrayList<Block> targeted;  //Holds references to Block objects that have been targeted by either player  
   private Human human;                //Human player
   private AI ai;                      //Computer player
   private int turns;
   
   //Constructor
   public BattleshipBoard() {
      row = 8;
      col = 8;
      board = new Block[row][col];
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) { 
            board[i][j] = new Block();
            board[i][j].setRow(i);
            board[i][j].setCol(j);
         }
      }
      targeted = new ArrayList<Block>();
   }
   
   //Accessor/mutator methods
   public Block[][] getBoard() {return board;}
   public Human getHuman() {return human;}
   public AI getAI() {return ai;}
   public int getTurn() {return turns;}
   public void setBoard(Block[][] b) {board = b;}
   public void setHuman(Human h) {human = h;}
   public void setAI(AI a) {ai = a;}  
   
   //Method that converts input row int to input-1
   //(so it accods with 2D board array)
   
   //Method that calls both human and ai setShip methods
   
   //Method that calls both
}