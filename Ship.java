public class Ship {

/* Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
   Due: 22 April 2016
   Class: CSCI 1101, Section 01

*/

   private boolean hit;    //Set to true if ship object hit 
   private boolean sunk;   //Set to true if all ship object in array are hit
   private int row;        //x coordinate of 
   private int col;        //y coordinate
   
   //Constructor
   public Ship() {
      hit = false;
      sunk = false;
      row = (Integer)null;
      col = (Integer)null;
   }
   
   //Mutator/accessor methods
   public void setHit(boolean h) {hit = h;}
   public void setSunk(boolean s) {sunk = s;}
   public void setRow(int r) {row = r;}
   public void setCol(int c) {col = c;}
   public boolean getHit() {return hit;}
   public boolean getSunk() {return sunk;}
   public int getRow() {return row;}
   public int getCol() {return col;}
}