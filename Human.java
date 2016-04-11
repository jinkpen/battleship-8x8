/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public class Human extends Player {

   //Constructor
   public Human() {
      super();
   }
   //Method takes in row value, column value, and type of ship value. Then saves the coordinates into 3 different arrays
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
   //method that determines if one of the AI's subsections are hit
   public void fire(AI ai, int r, int c) {
      //checks if any section of the battleship, submarine, or destroyer is hit. Sets variable boolean "hit" to true if it is hit
      if (r == ai.getBattleship()[0].getRow() && c == ai.getBattleship()[0].getCol())
         ai.getBattleship()[0].setHit(true);
      else if (r == ai.getBattleship()[1].getRow() && c == ai.getBattleship()[1].getCol())
         ai.getBattleship()[1].setHit(true);
      else if (r == ai.getBattleship()[2].getRow() && c == ai.getBattleship()[2].getCol())
         ai.getBattleship()[2].setHit(true);
      else if (r == ai.getBattleship()[3].getRow() && c == ai.getBattleship()[3].getCol())
         ai.getBattleship()[3].setHit(true);
      else if (r == ai.getSubmarine()[0].getRow() && c == ai.getSubmarine()[0].getCol())
         ai.getSubmarine()[0].setHit(true);
      else if (r == ai.getSubmarine()[1].getRow() && c == ai.getSubmarine()[1].getCol())
         ai.getSubmarine()[1].setHit(true);
      else if (r == ai.getSubmarine()[2].getRow() && c == ai.getSubmarine()[2].getCol())
         ai.getSubmarine()[2].setHit(true);
      else if (r == ai.getDestroyer()[0].getRow() && c == ai.getSubmarine()[0].getCol())
         ai.getDestroyer()[0].setHit(true);
      else if (r == ai.getDestroyer()[1].getRow() && c == ai.getSubmarine()[1].getCol())
         ai.getDestroyer()[1].setHit(true);

      //checks if all of the AI's 4 sections of the battleship are hit, sets battleship's variable boolean "sunk" to true if they are all hit
      if (ai.getBattleship()[0].getHit() && ai.getBattleship()[1].getHit() && ai.getBattleship()[2].getHit() && ai.getBattleship()[3].getHit())
         ai.setBattleSunk(true);
      //checks if all of the AI's 3 sections of the submarine are hit, sets submarine's variable boolean "sunk" to true if they are all hit
      else if (ai.getSubmarine()[0].getHit() && ai.getSubmarine()[1].getHit() && ai.getSubmarine()[2].getHit())
         ai.setSubSunk(true);
      //checks if both of the AI's sections of the destroyer are hit, sets destroyer's variable boolean "sunk" to true if they are both hit
      else if (ai.getDestroyer()[0].getHit() && ai.getDestroyer()[1].getHit())
         ai.setDestroySunk(true);
      //check if the all three of the AI's ships are sunk, if so AI's defeated variable is set to true
      if (ai.getBattleSunk() && ai.getSubSunk() && ai.getDestroySunk())
         ai.setDefeated(true);
   }
   //Resets the ships so none are set or occupied
   public void resetShips(){
      // resetting the battleships
      for(int i = 0; i < 4; i++){
         getBattleship()[i].setRow(-1);
         getBattleship()[i].setCol(-1);
      }
      // resetting the submarine
      for(int i = 0; i < 3; i++){
         getSubmarine()[i].setRow(-1);
         getSubmarine()[i].setCol(-1);
      }
      // resetting the destroyer
      for(int i = 0; i < 2; i++){
         getDestroyer()[i].setRow(-1);
         getDestroyer()[i].setCol(-1);
      }
   }
}