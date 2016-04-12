import java.util.ArrayList;

/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public abstract class Player {

   //private String name;        //Player's name
   //private String symbol;      //Symbol that represents player on board (X or O)
   private Ship[] battleship;  //4-element Ship array
   private Ship[] submarine;   //3-element Ship array
   private Ship[] destroyer;   //2-element Ship array
   private ArrayList<Block> targeted;  //Holds blocks that player has targetd
   private boolean battleSunk;         //Set to true if all elements in battleship are "hit"
   private boolean subSunk;            //Set to true if all elements in battleship are "hit"
   private boolean destroySunk;        //Set to true if all elements in battleship are "hit"
   private boolean defeated;           //Set to true if all ships have been sunk
   
   //Constructor
   public Player() {
      battleship = new Ship[4];
      for (int i = 0; i < battleship.length; i++)
         battleship[i] = new Ship();
      submarine = new Ship[3];
      for (int i = 0; i < submarine.length; i++)
         submarine[i] = new Ship();
      destroyer = new Ship[2];
      for (int i = 0; i < destroyer.length; i++)
         destroyer[i] = new Ship();
      battleSunk = false;
      subSunk = false;
      destroySunk = false;
      defeated = false;
   }
   
   //Accessor/mutator methods
   //public String getName() {return name;}
   //public String getSymbol() {return symbol;}
   public Ship[] getBattleship() {return battleship;}
   public Ship[] getSubmarine() {return submarine;}
   public Ship[] getDestroyer() {return destroyer;}
   public ArrayList<Block> getTargeted() {return targeted;}
   public boolean getBattleSunk() {return battleSunk;}
   public boolean getSubSunk() {return subSunk;}
   public boolean getDestroySunk() {return destroySunk;}
   public boolean getDefeated() {return defeated;}
   //public void setName(String n) {name = n;}
   //public void set(String s) {symbol = s;}
   public void setBattleship(Ship[] bs) {battleship = bs;}
   public void setSubmarine(Ship[] sm) {submarine = sm;}
   public void setDestroyer(Ship[] d) {destroyer = d;}
   public void setTargeted(ArrayList<Block> t) {targeted = t;}
   public void setBattleSunk(boolean bs) {battleSunk = bs;}
   public void setSubSunk(boolean ss) {subSunk = ss;}
   public void setDestroySunk(boolean ds) {destroySunk = ds;}
   public void setDefeated(boolean d) {defeated = d;}
}