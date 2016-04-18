import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public class PlayBattleship extends JFrame implements ActionListener {
   
   //Player object (human) and AI (extends Player) object (ai)
   Player human = new Player();
   AI ai = new AI();
   
   //Message content [message panel]
   private String[] msgs = {"You set the BATTLESHIP!",
                            "You set the SUBMARINE!",
                            "You set the DESTROYER!",
                            "Your board has been cleared.",
                            "Could not set ship. Try again",
                            "Battleship has already been set.",
                            "Submarine has already been set.",
                            "Destroyer has already been set.",
                            "It's your turn!",
                            "Your BATTLESHIP was hit!",
                            "Your SUBMARINE was hit!",
                            "Your DESTROYER was hit!",
                            "You hit one of the computer's ships!",
                            "You sunk one of the computer's ships!",
                            "You missed!"};
   private JLabel msg; //Label that can be any of the above messages [testing for simplification]
   
   //Battleship boards [grid panels]
   private Square[][] humanBoard = new Square[8][8];
   private Square[][] aiBoard = new Square[8][8];
   
   //Action buttons [button panel]
   private JButton surrender,     //Button to reveal enemy ships
                   reset,         //Button to clear board
                   tutorial,      //Button to view tutorial
                   play;          //Button to switch game to play mode   

   //JPanels
   private JPanel message;
   private JPanel humanGrid;
   private JPanel aiGrid;
   private JPanel buttons;
   
   //Constructor (for GUI)
   public PlayBattleship(){
      //Create message panel
      message = new JPanel(new GridBagLayout());
      message.setPreferredSize(new Dimension(this.WIDTH, 100));
      msg = new JLabel("Commander! Set your ships!");
      msg.setFont(new Font("Arial", Font.PLAIN, 14));
      message.add(msg);
      msg.setVisible(true);
   
      //Create grid panels  
      humanGrid = createBoard(humanBoard);
      aiGrid = createBoard(aiBoard);           
      
      //Create button panel
      buttons = new JPanel(new FlowLayout());
         
      /*Create buttons for buttons panel. Add text and 
      action listener to buttons, add button to panel*/
      
      //Surrender button (reveals enemy ships)
      surrender = new JButton("Surrender");
      surrender.addActionListener(this);
      surrender.setVisible(false);  //Surrender button remains hidden until game begins
      buttons.add(surrender);
            
      //Reset button (clears boards)
      reset = new JButton("Reset");
      reset.addActionListener(this);
      buttons.add(reset);
      
      //Play button (for beginning game after setting ships) 
      play = new JButton("Play");      
      play.addActionListener(this);  
      buttons.add(play);              
   
      //Tutorial button (displays JOptionPane with game instructions) 
      tutorial = new JButton("Tutorial");
      tutorial.addActionListener(this);
      buttons.add(tutorial);
      
      //Add panels to Frame
      add(message, BorderLayout.CENTER);
      add(aiGrid, BorderLayout.EAST);
      add(humanGrid, BorderLayout.WEST);
      add(buttons, BorderLayout.SOUTH);
   
      //Setting window properties
      setTitle("Battleship!");
      setSize(1000, 400);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
      
      //Add ships to ai board
      ai.setShips(aiBoard);
   }   
   
   //ACTION LISTENERS
   
   Square bow = (Square)null;
   //Action Listener for setting human ships
   ActionListener setShips = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         boolean occupied = false;
         if (bow == null) {
            bow = (Square)e.getSource();
            return; 
         }
         else {
            Square stern = (Square)e.getSource();
            ArrayList<Square> ship = new ArrayList<Square>();
            ship = canSetShip(bow, stern);
            //If mouse clicks were invalid
            if (ship == null) {
               bow = null;
               msg.setText(msgs[4]);  
               return;
             }
             //If user is trying to set a battleship
             else if (ship.size() == 4) {
               //If battleship has already been set
               if (human.battleHasBeenSet()) {
                  bow = null; 
                  msg.setText(msgs[5]);
                  return;
               }
               //Otherwise, set battleship
               else {
                  human.setBattleship(ship);
                  msg.setText(msgs[0]);
               }
            }
            else if (ship.size() == 3) {
            //If submarine has already been set
               if (human.subHasBeenSet()) {
                  bow = null;
                  msg.setText(msgs[6]); 
                  return;
               }
               //Otherwise, set submarine
               else {
                  human.setSubmarine(ship);
                  msg.setText(msgs[1]);
               }
            }
            else if (ship.size() == 2) {
               //If destroyer has already been set
               if (human.destroyHasBeenSet()) {
                  bow = null; 
                  msg.setText(msgs[7]);
                  return;
               }
               //Otherwise, set destroyer
               else {
                  human.setDestroyer(ship);
                  msg.setText(msgs[2]);
               }
            }
            bow = null;
         }
      }
   };     
   
   //Action Listener that allows player to fire
   ActionListener fire = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         Square target = (Square)e.getSource();
         //If the targeted square is occupied
         if (target.getBlock().getOccupied()) {
            target.getBlock().setHit(true);
            target.setBackground(Square.hitColor);
            int ship = target.getBlock().getShip();
            human.checkShipSunk(aiBoard, ai, ship);
         }
         else {
            target.setBackground(Square.missColor);
            target.removeActionListener(fire);
         }
         if (ai.getBattleSunk() && ai.getSubSunk() && ai.getDestroySunk())
            ai.setDefeated(true);   //"You win!" message <-----------
         if (!ai.getDefeated())
            ai.fire(humanBoard, human);
         if (human.getBattleSunk() && human.getSubSunk() && human.getDestroySunk())
            human.setDefeated(true);   //"You lose!" message <----------- 
         }
      };
            
   //Action Listener for buttons panel
   public void actionPerformed(ActionEvent e) {
      //Tutorial button actions
      if (e.getSource() == tutorial) {
         //Pop up box displaying the tutorial
         JOptionPane.showMessageDialog(this, "<html><h1>In the game of Battleship,<br>you are at war!</h1>"
                        + "<br>You are the commander of the Navy, and to<br>be victorious you must sink all"
                        + "of your<br>opponent's ships.<br>"
                        + "<br>You have three ships that you must place:<br><ul>"
                        + "<li>A battleship that sinks after 4 hits</li>"
                        + "<li>A submarine that sinks after 3 hits</li>"
                        + "<li>A destroyer that sinks after 2 hits</li></ul></html>"
                        + "\nYour opponent has the same types of ships."
                        + "\nYou will place your ships on the board first\nfollowed by your opponent."
                        + "\nYour opponent's ships will be hidden from\nyou, and yours from your opponent."
                        + "\n\nTo fire at your enemy, enter the column letter\nand row number you wish to target."
                        + "\nYou cannot fire at your own ships."
                        + "\n\nIf you hit an enemy ship, a red dot will appear\nat the location you targeted."
                        + "\nIf you miss your enemy's ship, a white dot\nwill appear in the location you targeted."
                        + "\nIf you sink an enemy ship, your enemy will say,\n\"You sunk my Battleship!\""
                        + "\n\nThe first player to sink all of their enemy's ships\nwins the game.",
                        "Tutorial", 
                        JOptionPane.INFORMATION_MESSAGE);
      } 
      //If player clicks reset button  
      if (e.getSource() == reset) {
         human.reset(humanBoard);
         msg.setText(msgs[3]);
      }
      //If player clicks surrender button
      if (e.getSource() == surrender) {
         ai.reveal(aiBoard);
         human.setDefeated(true);
      }
      
      if (e.getSource() == play) {
         //If player has set all their ships
         if(human.battleHasBeenSet() && human.subHasBeenSet() && human.destroyHasBeenSet()) {
            //Hide reset button
            reset.setVisible(false);
            //Reveal surrender button
            surrender.setVisible(true);
            for (int i = 0; i < 8; i++) {
               for(int j = 0; j < 8; j++) { 
                  //Remove setShips action listeners from humanBoard
                  humanBoard[i][j].removeActionListener(setShips);
                  //Add fire action listener to aiBoard
                  aiBoard[i][j].addActionListener(fire);
               }
            }
            play.setVisible(false);
            msg.setText(msgs[8]);
         }
      }
   }
   
   
   //Method to create boards for human and ai
   public JPanel createBoard(Square[][] board) {
      JLabel[] letters = new JLabel[8];   //Letter labels 
      JLabel[] numbers = new JLabel[8];   //Number labels 
      //Letters and numbers for labels for player grids 
      String[] lets = {"   A", "   B", "   C", "   D", "   E", "   F", "   G", "   H"};
      String[] nums = {"   1", "   2", "   3", "   4", "   5", "   6", "   7", "   8"};  
      JPanel grid = new JPanel(new GridLayout(9, 9, 0, 0));
      //Create JButtons and add to panel
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
            //If both i and j equal 0, add blank label
            if (i == 0 && j == 0) 
               grid.add(new JLabel(""));
            //If i equals 0, make letter label, add to panel
            else if (i == 0) {
               letters[j-1] = new JLabel(lets[j-1]);
               letters[j-1].setFont(new Font("Arial", Font.BOLD, 14)); 
               grid.add(letters[j-1]);
            }   
            //If j equals 0, make number label, add to panel
            else if (j == 0) {
               numbers[i-1] = new JLabel(nums[i-1]);
               numbers[i-1].setFont(new Font("Arial", Font.BOLD, 14));
               grid.add(numbers[i-1]);
            }
            //Otherwise, make board button, add to panel
            else {
               board[i-1][j-1] = new Square(i-1, j-1);
               //If human grid, add setShips action listener
               if (board[i-1][j-1].getBlock().getID() < 64)
                  board[i-1][j-1].addActionListener(setShips); 
               grid.add(board[i-1][j-1]);   
            }
         }
      }
      return grid;
   }
   
   //Method that returns null if ship cannot be set, otherwise returns ArrayList of squares
   public ArrayList<Square> canSetShip(Square bow, Square stern) {
      ArrayList<Square> temp = new ArrayList<Square>();
      //If either bow or stern are occupied, return 0
      if (bow.getBlock().getOccupied() || stern.getBlock().getOccupied())
         return null;
      //If bow and stern are in the same row
      if (bow.getBlock().getRow() == stern.getBlock().getRow()) {
         //Initialize row variable
         int row = stern.getBlock().getRow();
         //Let length equal distance between bow and stern   
         int length = bow.getBlock().getCol() - stern.getBlock().getCol(); 
         //Let start equal square with lower col value
         int start = (int)Math.min(bow.getBlock().getCol(), stern.getBlock().getCol());
         //Let length equal absolute value of length
         length = Math.abs(length);
         //Check if any squares between bow and stern (inclusive) are occupied
         for (int i = start; i <= length + start; i++) {
            //If yes, return null
            if (humanBoard[row][i].getBlock().getOccupied())
               return null;
            //Otherwise, add square to temporary array list
            else 
               temp.add(humanBoard[row][i]);
         }
         //If temp is larger than 4 or smaller than 2, return null
         if (temp.size() > 4 || temp.size() < 2)
            return null;
         //Otherwise, return temp
         else
            return temp;
      }
      //If bow and stern are in the same column
      else if (bow.getBlock().getCol() == stern.getBlock().getCol()) {
         //Initialize col variable
         int col = stern.getBlock().getCol();
         //Let length equal distance between bow and stern    
         int length = bow.getBlock().getRow() - stern.getBlock().getRow(); 
         //Let start equal square with lower row value
         int start = (int)Math.min(bow.getBlock().getRow(), stern.getBlock().getRow()); 
         //Let length equal absolute value of length
         length = Math.abs(length); 
         //Check if any squares between bow and stern (inclusive) are occupied
         for (int i = start; i <= length + start; i++) {
            //If yes, return null
            if (humanBoard[i][col].getBlock().getOccupied())
               return null;
            //Otherwise, add square to temporary array list
            else 
               temp.add(humanBoard[i][col]);
         }
         //If temp is larger than 4 or smaller than 2, return null
         if (temp.size() > 4 || temp.size() < 2)
            return null;
         //Otherwise, return temp
         else
            return temp; 
      }
      //If bow and stern are not in the same row 
      //or are not in the same column, return null
      else 
         return null;
   }

   //Shows message for 30 seconds then disappears
   public void showMsg(String m) {
       //Set the text and make it visible
      msg.setText(m);
      msg.setVisible(true);
       //After 30 seconds the message disappears
       //Creating a timer that waits 30 seconds to hide the JLabel
      Timer t = new Timer(30000, 
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               msg.setVisible(false);
            }
         });
      t.start();
   }

   //Main function
   public static void main(String[] args) {
      StartMenu menu = new StartMenu();
   }
}