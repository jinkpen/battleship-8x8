/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public class Block {

   private int row;           //x coordinate of block
   private int col;           //y coordinate of block
   private boolean miss;      //Initialized as false, set to true if block has been targeted, but missed
   private boolean hit;       //Initialized as false, set to true if block has been hit [player's ship hit]
   private boolean sunk;      //Initialized as false, set to true if block is part of a ship that has been sunk
   private boolean occupied;  //Initialized as false, set to true if ship object exists that matches coordinates of block
   
   //Constructor (initializes miss, hit, and sunk as false)
   public Block() {
      miss = false;
      hit = false;
      sunk = false;
      occupied = false;
   }
   
   //Accessor/mutator methods
   public void setRow(int r) {row = r;}
   public void setCol(int c) {col = c;}
   public void setMiss(boolean m) {miss = m;}
   public void setHit(boolean h){hit = h;}
   public void setSunk(boolean s) {sunk = s;}
   public void setOccupied(boolean o) {occupied = o;}
   public int getRow() {return row;}
   public int getCol() {return col;}
   public boolean getMiss() {return miss;}
   public boolean getHit(){return hit;}
   public boolean getSunk() {return sunk;}
   public boolean getOccupied() {return occupied;}
}