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
   Player human;
   AI ai;
   
   private JLabel msg; //Label that can be any of the above messages
   
   //Battleship boards [grid panels]
   private Square[][] humanBoard;
   private Square[][] aiBoard;
   
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
   
      //Create human and AI players
      human = new Player();
      ai = new AI();
      
      //Create boards
      humanBoard = new Square[8][8];
      aiBoard = new Square[8][8];

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
               showMsg("Could not set ship. Try again");
               return;
             }
             //If user is trying to set a battleship
             else if (ship.size() == 4) {
               //If battleship has already been set
               if (human.battleHasBeenSet()) {
                  bow = null; 
                  showMsg("<html><center>Commander, your <b>BATTLESHIP</b> has already been set!<br><br>Reset if you wish to change its position</html>");
                  return;
               }
               //Otherwise, set battleship
               else {
                  human.setBattleship(ship);
                  showMsg("<html><center>Commander, your <b>BATTLESHIP</b> has just been set!</html>");
               }
            }
            else if (ship.size() == 3) {
            //If submarine has already been set
               if (human.subHasBeenSet()) {
                  bow = null;
                  showMsg("<html><center>Commander, your <b>SUBMARINE</b> has already been set!<br><br>Reset if you wish to change its position</html>");
                  return;
               }
               //Otherwise, set submarine
               else {
                  human.setSubmarine(ship);
                  showMsg("<html><center>Commander, your <b>SUBMARINE</b> has just been set!</html>");
               }
            }
            else if (ship.size() == 2) {
               //If destroyer has already been set
               if (human.destroyHasBeenSet()) {
                  bow = null; 
                  showMsg("<html><center>Commander, your <b>DESTROYER</b> has already been set!<br><br>Reset if you wish to change its position</html>");
                  return;
               }
               //Otherwise, set destroyer
               else {
                  human.setDestroyer(ship);
                  showMsg("<html><center>Commander, your <b>DESTROYER</b> has just been set!</html>");
               }
            }
            bow = null;
            //If all ships have been set display message
            if (human.battleHasBeenSet() && human.subHasBeenSet() && human.destroyHasBeenSet()) {
               showMsg("<html><center>Commander! All of your ships have been set!<br>Are you ready?</html>");
            }
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
            target.setEnabled(false);
            showMsg("<html><center>Commander! You hit one of the enemy ships!</html>");
            //If an enemy ship has been sunk
            if (human.checkShipSunk(aiBoard, ai, target.getBlock().getShip()))
               showMsg("<html><center>Commander! You sunk one of the enemy ships!</html>");
         }
         else {
            target.setBackground(Square.missColor);
            target.removeActionListener(fire);
            target.setEnabled(false);
            showMsg("<html><center>You missed!</html>");
         }
         if (ai.getBattleSunk() && ai.getSubSunk() && ai.getDestroySunk()) {
            showMsg("");
            ai.setDefeated(true);   
            //"You win!" message
            winLoseMsg();
         }
         if (!ai.getDefeated()) {
            target = ai.fire(humanBoard, human);
            //showMsg("<html><center>Commander, the enemy has made their shot.<br>How will you retaliate?</html>");
            if (ai.checkShipSunk(humanBoard, human, target.getBlock().getShip()))
               showMsg("<html><center>Commander! One of our ships have been sunk!</html>");
         }
         if (human.getBattleSunk() && human.getSubSunk() && human.getDestroySunk()) {
            showMsg("");
            human.setDefeated(true);
            //"You lose!" message 
            winLoseMsg();
         }
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
                        + "\nYou will place each ship on the board by click on \nthe position of the bow and stern."
                        + "\nYour opponent's ships will be hidden from\nyou, and yours from your opponent."
                        + "\n\nTo fire at your enemy, click the square on their \nboard you want to target."
                        + "\nYou cannot fire at your own ships."
                        + "\n\nIf you hit an enemy ship, the targeted \nsquare will turn red."
                        + "\nIf you miss your enemy's ship, the targeted \nsquare will turn white."
                        + "\nIf you sink an enemy ship, the sunken \nship will turn black."
                        + "\n\nThe first player to sink all of their enemy's ships\nwins the game.",
                        "Tutorial", 
                        JOptionPane.INFORMATION_MESSAGE);
      } 
      //If player clicks reset button  
      if (e.getSource() == reset) {
         human.reset(humanBoard);
         for (int i = 0; i < 8; i++) 
            for (int j = 0; j < 8; j++) 
               humanBoard[i][j].setEnabled(true);
         showMsg("<html><center>Commander, your ship board has been reset!</html>");
      }
      //If player clicks surrender button
      if (e.getSource() == surrender) {
         showMsg("");
         ai.reveal(aiBoard);
         human.setDefeated(true);
         winLoseMsg();
      }
      
      if (e.getSource() == play) {
         //If player has set all their ships
         if (human.battleHasBeenSet() && human.subHasBeenSet() && human.destroyHasBeenSet()) {
            //Hide reset button
            reset.setVisible(false);
            //Reveal surrender button
            surrender.setVisible(true);
            for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 8; j++) { 
                  //Remove setShips action listeners from humanBoard
                  humanBoard[i][j].removeActionListener(setShips);
                  //Add fire action listener to aiBoard
                  aiBoard[i][j].addActionListener(fire);
               }
            }
            play.setVisible(false);
            showMsg("<html><center>Commander! Where should we fire?</html>");
         }
         //If a ship was not set properly let the player know 
         else {
            showMsg("<html><center>Commander! You didn't set all your ships!</html>");
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
      grid.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
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
      //Timer t = new Timer(30000, 
         //new ActionListener() {
            //@Override
            //public void actionPerformed(ActionEvent e) {
              // msg.setVisible(false);
            //}
        // });
      //t.start();
   }

   //JOptionPane that appears when you win or lose (message displayed depends)
   public void winLoseMsg() {
      //If the player won (AI was defeated) print out the win message and ask to play again
      if (ai.getDefeated()) {
         int n = JOptionPane.showConfirmDialog(this, "Would you like to play again?",
                                             "YOU WON!",
                                             JOptionPane.YES_NO_OPTION);
         //If the user wishes to play again                            
         if (n == JOptionPane.YES_OPTION) {
            //Restart the game
            restart();
         //If not, close the game
         } else if (n == JOptionPane.NO_OPTION) {
            System.exit(0);
         }
      //If the player lost print out the lose message and ask to play again
      } else if (human.getDefeated()) {
         int n = JOptionPane.showConfirmDialog(this, "Would you like to play again?",
                                             "YOU LOST!",
                                             JOptionPane.YES_NO_OPTION);
         //If the user wishes to play again                            
         if (n == JOptionPane.YES_OPTION) {
            //Restart the game
            restart();
         //If not, close the game
         } else if (n == JOptionPane.NO_OPTION) {
            System.exit(0);
         }
      }
   }
   
   //Resets the game
   public void restart() {
      //Disposing the old PlayBattleship object
      setVisible(false);
      dispose();
      //Resetting the count so the human board is created with setShip ActionListeners
      Block.setCount(0);
      //Creating a new PlayBattleship object
      PlayBattleship game = new PlayBattleship();
   }

   //Main function
   public static void main(String[] args) {
      StartMenu menu = new StartMenu();
   }
}