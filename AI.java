import java.util.ArrayList;

/* Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
   Due: 22 April 2016
   Class: CSCI 1101, Section 01

*/

public class AI extends Player {
   
   private ArrayList<Block> targeted;
   
   public AI() {
      super();
      targeted = new ArrayList<Block>();
   }
   
   //Method for computer to set ships on board
   public void setShips(Human human) {
      setBattleship(human);
      setSubmarine(human);
      setDestroyer(human);
   }
   
   //Method for computer to fire at enemy ships 
   public void fire(Human human) {
   }
   
   //Method to set battleship (called from setShips)
   public void setBattleship(Human human) {
      int direction;       //Direction that ship wil be placed (0 = horizontal; 1 = vertical)
      int row;             //x coordinate of a block
      int col;             //y coordinate of a block
      
      //Initialize direction with pseudorandom number (0-1)
      direction = (int)(Math.random()*1);
      //Computer picks block for starting point of 
      
   }
   
   public void setSubmarine(Human human) {
   }
   
   public void setDestroyer(Human human) {
   }
   
}   
   
   
   