/* Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
   Due: 22 April 2016
   Class: CSCI 1101, Section 01
   
   Board Class: Attributes - rows 
                           - columns
                Operations - constructor (takes two int args)
*/

public abstract class Board {
   
   private int rows;    //Number of rows
   private int cols;    //Number of columns
   
   //Constructor (parameters initialize rows and columns)
   public Board(int r, int c) {
      rows = r;
      cols = c;
   }
   
   //Accessor methods
   public int getRows() {return rows;}
   public int getCols() {return cols;}
   
}