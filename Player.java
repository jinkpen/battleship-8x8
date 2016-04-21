import java.util.ArrayList;

/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public class Player {

   private Block[] battleship;         //4-element Block array
   private Block[] submarine;          //3-element Block array
   private Block[] destroyer;          //2-element Block array
   private boolean battleHasBeenSet;   //Set to true once player's battleship is set
   private boolean subHasBeenSet;      //Set to true once player's submarine is set
   private boolean destroyHasBeenSet;  //Set to true once player's destroyer is set
   private boolean battleSunk;         //Set to true if all elements in battleship are "hit"
   private boolean subSunk;            //Set to true if all elements in battleship are "hit"
   private boolean destroySunk;        //Set to true if all elements in battleship are "hit"
   private boolean defeated;           //Set to true if all ships have been sunk

   //Constructor: Creates empty arrays (which represent ships),
   //and sets all flags to false
   public Player() {
      battleship = new Block[4];
      submarine = new Block[3];
      destroyer = new Block[2];
      battleHasBeenSet = false;
      subHasBeenSet = false;
      destroyHasBeenSet = false;
      battleSunk = false;
      subSunk = false;
      destroySunk = false;
      defeated = false;
   }
   
   //Accessor methods
   public Block[] getBattleship() {return battleship;}
   public Block[] getSubmarine() {return submarine;}
   public Block[] getDestroyer() {return destroyer;}
   public boolean getBattleSunk() {return battleSunk;}
   public boolean getSubSunk() {return subSunk;}
   public boolean getDestroySunk() {return destroySunk;}
   public boolean getDefeated() {return defeated;}
   
   //Methods to set HasBeenSet flags
   public void setBattleHasBeenSet(boolean s) {battleHasBeenSet = s;}
   public void setSubHasBeenSet(boolean s) {subHasBeenSet = s;}
   public void setDestroyHasBeenSet(boolean s) {destroyHasBeenSet = s;}
   
   //Methods that return true if ship has been set
   public boolean battleHasBeenSet() {return battleHasBeenSet;}
   public boolean subHasBeenSet() {return subHasBeenSet;}
   public boolean destroyHasBeenSet() {return destroyHasBeenSet;}
   
   //Method to set human battleship
   public void setBattleship(ArrayList<Square> bs) {
      //For each element of battleship array
      for (int i = 0; i < battleship.length; i++) {
         //Add block to array, set occupied to true
         battleship[i] = bs.get(i).getBlock();
         battleship[i].setOccupied(true);
         battleship[i].setShip(4);
         //Set button color to grey (shipColor, defined in Square)
         bs.get(i).setBackground(Square.shipColor);
         bs.get(i).setOpaque(true);           
         bs.get(i).setBorderPainted(true);
         bs.get(i).setEnabled(false);
      }
      //Set battleHasBeenSet to true
      battleHasBeenSet = true;
   }
   
   //Method to set ai battleship
   public void setBattleship(int index, Block block) {
      battleship[index] = block;
      battleship[index].setOccupied(true);
      battleship[index].setShip(4);
   }
   
   //Method to set submarine
   public void setSubmarine(ArrayList<Square> ss) {
      //For each element of submarine array
      for (int i = 0; i < submarine.length; i++) {
         //Add block to array, set occupied to true
         submarine[i] = ss.get(i).getBlock();
         submarine[i].setOccupied(true);
         submarine[i].setShip(3);
         //Set button color to grey (shipColor, defined in Square)
         ss.get(i).setBackground(Square.shipColor);
         ss.get(i).setOpaque(true);           
         ss.get(i).setBorderPainted(true);
         ss.get(i).setEnabled(false);
      }
      //Set subHasBeenSet to true
      subHasBeenSet = true;
   }
   
   //Method to set ai submarine
   public void setSubmarine(int index, Block block) {
      submarine[index] = block;
      submarine[index].setOccupied(true);
      submarine[index].setShip(3);
   }
   
   //Method to set destroyer
   public void setDestroyer(ArrayList<Square> ds) {
      //For each element of destroyer array
      for (int i = 0; i < destroyer.length; i++) {
         //Add block to array, set occupied to true
         destroyer[i] = ds.get(i).getBlock();
         destroyer[i].setOccupied(true);
         destroyer[i].setShip(2);
         //Set button color to grey (shipColor, defined in Square)
         ds.get(i).setBackground(Square.shipColor);
         ds.get(i).setOpaque(true);           
         ds.get(i).setBorderPainted(true);
         ds.get(i).setEnabled(false);
      }
      //Set destroyHasBeenSet to true
      destroyHasBeenSet = true;
   }
   
   //Method to set ai destroyer
   public void setDestroyer(int index, Block block) {
      destroyer[index] = block;
      destroyer[index].setOccupied(true);
      destroyer[index].setShip(2);
   }
   
   //Methods to set sunk and defeated flags
   public void setBattleSunk(boolean bs) {battleSunk = bs;}
   public void setSubSunk(boolean ss) {subSunk = ss;}
   public void setDestroySunk(boolean ds) {destroySunk = ds;}
   public void setDefeated(boolean d) {defeated = d;}
   
   //Method to reset human player's board
   public void reset(Square[][] board) {
      int row = -1;
      int col = -1;
      //If battleship has been set
      if (battleHasBeenSet()) {
         //For each index of battleship array
         for (int i = 0; i < battleship.length; i++) {
            //Set occupied to false
            battleship[i].setOccupied(false);
            row = battleship[i].getRow();
            col = battleship[i].getCol();
            //Change color of square on board to oceanColor
            board[row][col].setBackground(Square.oceanColor);
            //Change element to null
            battleship[i] = (Block)null;
         }
         //Set battleHasBeenSet to false
         setBattleHasBeenSet(false);
      }
      //If submarine has been set
      if (subHasBeenSet()) {
         //For each index of submarine array
         for (int i = 0; i < submarine.length; i++) {
            //Set occupied to false
            submarine[i].setOccupied(false);
            row = submarine[i].getRow();
            col = submarine[i].getCol();
            //Change color of square on board to oceanColor
            board[row][col].setBackground(Square.oceanColor);
            //Change element to null
            submarine[i] = (Block)null;
         }
         //Set subHasBeenSet to false
         setSubHasBeenSet(false);
      }
      //If submarine has been set
      if (destroyHasBeenSet()) {
         //For each index of destroyer array
         for (int i = 0; i < destroyer.length; i++) {
            //Set occupied to false
            destroyer[i].setOccupied(false);
            row = destroyer[i].getRow();
            col = destroyer[i].getCol();
            //Change color of square on board to oceanColor
            board[row][col].setBackground(Square.oceanColor);
            //Change element to null
            destroyer[i] = (Block)null;
         }
         //Set destroyHasBeenSet to false
         setDestroyHasBeenSet(false);
      }
   }
   
   //Method to check if player sunk ship
   public boolean checkShipSunk(Square[][]board, Player player, int ship) {   
      boolean floating = false;
      int row = -1;
      int col = -1;
      if (ship == 4) {
         for (int i = 0; i < 4; i++)
            if (!player.getBattleship()[i].getHit())
               floating = true;
         if (!floating) {
            for (int i = 0; i < 4; i++) {
               player.getBattleship()[i].setSunk(true);
               player.setBattleSunk(true); //"You sunk my ship" message <--------------
               row = player.getBattleship()[i].getRow();
               col = player.getBattleship()[i].getCol();
               board[row][col].setBackground(Square.sunkColor);
            }
            return true;
         }
      }
      else if (ship == 3) {
         for (int i = 0; i < 3; i++)
            if (!player.getSubmarine()[i].getHit())
               floating = true;
         if (!floating) {
            for (int i = 0; i < 3; i++) {
               player.getSubmarine()[i].setSunk(true);
               player.setSubSunk(true); //"You sunk my ship" message <--------------
               row = player.getSubmarine()[i].getRow();
               col = player.getSubmarine()[i].getCol();
               board[row][col].setBackground(Square.sunkColor);
            }
            return true;
         }
      }
      else if (ship == 2) {
         for (int i = 0; i < 2; i++)
            if (!player.getDestroyer()[i].getHit())
               floating = true;
         if (!floating) {
            for (int i = 0; i < 2; i++) {
               player.getDestroyer()[i].setSunk(true);
               player.setDestroySunk(true);  //"You sunk my ship" message <--------------
               row = player.getDestroyer()[i].getRow();
               col = player.getDestroyer()[i].getCol();
               board[row][col].setBackground(Square.sunkColor);
            }
            return true;
         }
      }
      return false;
   }
}