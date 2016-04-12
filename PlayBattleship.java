import java.util.*;

/* Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
   Due: 22 April 2016
   Class: CSCI 1101, Section 01

*/

public class PlayBattleship {

   public static void main(String[] args) {
      Scanner kb = new Scanner(System.in);
      //User enters name for human player (their own name)
      System.out.print("Enter your name: ");
      String n1 = kb.nextLine();
      //User enters name for AI player
      System.out.print("Enter a name for your AI opponent: ");
      String n2 = kb.nextLine();
      BattleshipBoard board = new BattleshipBoard(n1, n2, "O", "X", 8, 8);
      
      int choice = -1;
      //Create array of letters (used to check input column coordinates) REMOVE: NEED TO DO GUI
      //String[] check = {"A", "B", "C", "D", "E", "F", "G", "H",
                        //"a", "b", "c", "d", "e", "f", "g", "h"};
      System.out.println("\nWelcome to Battleship!");
      
      //User has option to look at game rules or exit
      System.out.println("\nDo you know how to play battleship?");
      System.out.println("Choose an option: [0] Exit\n"
                         + "\t\t  [1] No, please teach me\n"
                         + "\t\t  [2] Yes, let me play!");
      while (choice < 0 || choice > 2) {
         try {
            System.out.print("Enter your choice: ");
            choice = kb.nextInt();
            if (choice == 0)
               System.exit(0);
            else if (choice == 1)
               tutorial();
            else if (choice < 0 || choice > 2) {
               System.out.println("You must enter and number between 0 and 2");        
               System.out.print("Enter your choice: ");
               choice = kb.nextInt();
            }
         }
         catch (InputMismatchException e) {
            System.out.println("You must input a number.");
         }
      }
      
      //Human sets Ships
      
      //AI sets Ships
      
      //"Coin toss" to choose first player 
      //(if 0 human goes on odd turns (first), if 1 ai goes on odd turns)
      
      //Players make moves until one is defeated
      
          
   }
   
   //Tutorial (player has option to read game rules before starting the game
   public static void tutorial() {
      System.out.println("\nIn the game of Battleship, you are at war!"); 
      System.out.println("You are the commander of the Navy, and to be\n"
                         + "victorious you must sink all of your opponent's\n"
                         + "ships.");
      System.out.println("\nYou have three ships that you must place:\n"
                         + "\tA battleship that sinks after 4 hits\n"
                         + "\tA submarine that sinks after 3 hits\n"
                         + "\tA destroyer that sinks after 2 hits");
      System.out.println("Your opponent has the same types of ships.");
      System.out.println("\nYou will place your ships on the board first\n"
                         + "followed by your opponent. You opponent's ships\n"
                         + "will be hidden from you, and yours from your\n"
                         + "opponent.");
      System.out.println("\nTo fire at your enemy, enter the column letter and\n"
                         + "row number you wish to target. You cannot fire at your\n"
                         + "own ships. If you hit an enemy ship, a red dot will\n" 
                         + "appear\n at the location you targeted. If you miss your\n"
                         + "enemy's ship, a white dot will appear in the location\n"
                         + "you targeted. If you sink an enemy ship, your enemy will\n
                         + "say, \"You sunk my Battleship!\" The first player to sink\n"
                         + "all of their enemy's ships wins the game.";
   }
   
   
} 