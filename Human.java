/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public class Human extends Player {

   public Human() {  
      super();
   }
   //method takes in Row value, column value, and type of ship value. Then saves the coordinates into 3 different arrays
   public void setShips(int r, int c, int type) {
      //enters here if its the battleship placing phase
      if (type == 3) {
         //sets coordinates of the first ship object in the battleship array
         if (getBattleship()[0].getRow() == -1) {
            getBattleship()[0].setRow(r);
            getBattleship()[0].setCol(c);
         } 
         //sets coordinates of the second ship object in the battleship array
         else if (getBattleship()[0].getRow() != -1 && getBattleship()[1].getRow() == -1) {
            getBattleship()[1].setRow(r);
            getBattleship()[1].setCol(c);
         } 
         //sets coordinates of the third ship object in the battleship array 
         else if (getBattleship()[0].getRow() != -1 && getBattleship()[1].getRow() != -1 && getBattleship()[2].getRow() == -1) {
            getBattleship()[2].setRow(r);
            getBattleship()[2].setCol(c);
         }
         //sets coordinates of the last ship object in the battleship array
         else {
            getBattleship()[3].setRow(r);
            getBattleship()[3].setCol(c);
         }
      } 
      //enters here if its the submarine placing phase  
      else if (type == 2) {
         //sets coordinates of the first ship object in the submarine array 
         if (getSubmarine()[0].getRow() == -1) {
            getSubmarine()[0].setRow(r);
            getSubmarine()[0].setCol(c);
         }
         //sets coordinates of the second ship object in the submarine array
         else if (getSubmarine()[0].getRow() != -1 && getSubmarine()[1].getRow() == -1) {
            getSubmarine()[1].setRow(r);
            getSubmarine()[1].setCol(c);
         }
         //sets coordinates of the last ship object in the submarine array
         else {
            getSubmarine()[2].setRow(r);
            getSubmarine()[2].setCol(c);
         }
      }
      //enters here if its the destroyer placing phase
      else {
         //sets coordinates of the first ship object in the destroyer array
         if (getDestroyer()[0].getRow() == -1) {
            getDestroyer()[0].setRow(r);
            getDestroyer()[0].setCol(c);
         }
         ////sets coordinates of the last ship object in the submarine array
         else {
            getDestroyer()[1].setRow(r);
            getDestroyer()[1].setCol(c);
         }
      }
   }             
   
   //public void fire(AI ai, int r, int c) {
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
   //}
   
}