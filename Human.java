/* Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
   Due: 22 April 2016
   Class: CSCI 1101, Section 01

*/

public class Human extends Player {

   public Human() {  
      super();
   }
   
   public void setShips(int r, int c) {
      //Player clicks on ship in menu to select which ship to set
      
      //Player clicks on (vertically or horizonally) consecutive blocks  
      //(2, 3, or 4, depending on which ship was selected)
      
      //Player confirms that ship is placed in desired location
      
      //Repeast process for all three ships
   }
   
   public void fire(AI ai, int r, int c) {
      //Player clicks on block
      
      //If block has already been selected, nothing happens (block cannot be clicked twice)
      //Player stays on turn
      
      //If block is occupied with player's own ship, nothing happens 
      //(game will not let player fire on own ship), player stays on turn
      
      //If block is not occupied, block coloured in white
      //Block added to targeted array
      //Message reads, "You missed"
      
      //If block is occupied by enemy ship, 
      //Block coloured in red
      //Enemy ship (object hit) set to hit
      //If all other Ship objects that Ship array are hit,
      //All objects in 
   }
   
}