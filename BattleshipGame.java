import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public class BattleshipGame extends JFrame implements ActionListener {

   //JPanels
   private JPanel message;
   private JPanel grid;
   private JPanel buttons;

   private int type = 3;                                         //integer to distinguish which ship should be placed
   BattleshipBoard game = new BattleshipBoard();                 //Object of type BattleshipBoard, which is used to access all other classes
   ArrayList<Integer> coorRowPHolder = new ArrayList<Integer>(); //ArrrayList that is a placeholder for the coordinates for the humans rows for their ships
   ArrayList<Integer> coorColPHolder = new ArrayList<Integer>(); //ArrrayList that is a placeholder for the coordinates for the humans columns for their ships

   private JButton[][] board;                //Game board
   private JLabel[] letters;                 //Letter labels [grid panel]
   private JLabel[] numbers;                 //Number labels [grid panel]
   private String[] lets = {"   A", "   B", "   C", "   D", "   E", "   F", "   G", "   H"};
   private String[] nums = {"   1", "   2", "   3", "   4", "   5", "   6", "   7", "   8"};

   //Buttons panel
   private JButton surrender,     //Button to reveal enemy ships
                   reset,         //Button to clear board
                   tutorial,      //Button to view tutorial
                   set;           //Button to set ships

   // private JLabel a, b, c, d, e, f, g, h,    //Labels for around the game board (grid panel)
   //                one, two, three, four,
   //                five, six, seven, eight,
   //                blank;

   //Message panel
   private JLabel battleSet,        //Indicates human sets battleship 
                  subSet,           //Indicates human sets submarine
                  destSet,          //Indicates human sets destroyer
                  aiTurn,           //Indicates computer turn
                  battleHit,        //Indicates human battleship hit
                  subHit,           //Indicates human submarine hit
                  destHit,          //Indicates human destroyer hit
                  humanTurn,        //Indicates human turn
                  aiHit,            //Indicates computer ship hit
                  aiSunk,           //Indicate computer ship sunk
                  msg; //Label that can be any of the above messages [testing for simplification]

   private String[] msgs = {"You set the BATTLESHIP!",
                           "You set the SUBMARINE!",
                           "You set the DESTROYER!",
                           "It's the computer's turn!",
                           "Your BATTLESHIP was hit!",
                           "Your SUBMARINE was hit!",
                           "Your DESTROYER was hit!",
                           "It's your turn!",
                           "You hit one of the computer's ships!",
                           "You sunk one of the computer's ships!",
                           "You missed!"};

   private static int turn = 0;

   //Contructor 
   public BattleshipGame() {
      //Create panels
      message = new JPanel(new GridBagLayout());
      message.setPreferredSize(new Dimension(this.WIDTH, 100));
      grid = new JPanel(new GridLayout(9, 9, 0, 0));
      buttons = new JPanel(new FlowLayout());
      
      //I'm using a different method, not sure how ya'll prefer it tho
      //Create labels for message panel
      /*battleSet = new JLabel("Battleship set");
      message.add(battleSet);
      battleSet.setVisible(false);
       
      subSet = new JLabel("Submarine set");
      destSet = new JLabel("Destroyer set");*/

      //Label changes when certain events occur
      msg = new JLabel("Commander! Set your ships!");
      msg.setFont(new Font("Arial", Font.PLAIN, 18));
      message.add(msg);
      msg.setVisible(true);
         
      //Create buttons for buttons panel
      surrender = new JButton("Surrender");
      buttons.add(surrender);
      surrender.addActionListener(this);
      //Hiding the surrender button because it is unnecessary until game starts
      surrender.setVisible(false);

      set = new JButton("Set");
      buttons.add(set);
      set.addActionListener(this);

      reset = new JButton("Reset");
      buttons.add(reset);
      reset.addActionListener(this);

      tutorial = new JButton("Tutorial");
      buttons.add(tutorial);
      tutorial.addActionListener(this);
       
      //Create letter array (JLabel) for grid panel
      letters = new JLabel[8];
      //Create numbers array (JLabel) for grid panel
      numbers = new JLabel[8];
      //Create board array (JButton) for grid panel
      board = new JButton[8][8];
       
      //Create JButtons and add to panel
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
            //If both i and j equal 0, add blank label
            if (i == 0 && j == 0) 
               grid.add(new JLabel(""));
            //If i equals 0, make letter label, add to panel
            else if (i == 0) {
               letters[j-1] = new JLabel(lets[j-1]);
               letters[j-1].setFont(new Font("Arial", Font.BOLD, 18)); 
               grid.add(letters[j-1]);
            }   
            //If j equals 0, make number label, add to panel
            else if (j == 0) {
               numbers[i-1] = new JLabel(nums[i-1]);
               numbers[i-1].setFont(new Font("Arial", Font.BOLD, 18));
               grid.add(numbers[i-1]);
            }
            //Otherwise, make board button, add to panel
            else {
               board[i-1][j-1] = new JButton();
               board[i-1][j-1].setBackground(new Color(51, 153, 255));  //This colour should be button colour
               board[i-1][j-1].setOpaque(true);
               board[i-1][j-1].setBorderPainted(true);
               board[i-1][j-1].addActionListener(this);
               grid.add(board[i-1][j-1]);   
            }
         }
      }

      //Add panels to Frame
      add(message, BorderLayout.NORTH);
      add(grid, BorderLayout.CENTER);
      add(buttons, BorderLayout.SOUTH);

      //Setting window properties
      setTitle("Battleship!");
      setSize(450, 600);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
   }

   //Determines what happens if a button is clicked
   public void actionPerformed(ActionEvent e) { 
      //checks to see if it is the set ship portion of the game
      if (type != 0) {
         //checks all board buttons
         for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               //enters if button clicked was one of the 64 game buttons, and its occupied status is set to false
               if (e.getSource() == board[i][j] && !game.getBoard()[i][j].getOccupied()) {
                  board[i][j].setBackground(Color.GRAY);
                  coorRowPHolder.add(i);
                  coorColPHolder.add(j);
                  game.getBoard()[i][j].setOccupied(true);
               } 
            }
         }
      }
      //enters if it is the attack portion of the game 
      else if (type == 0) {
         for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               //If a board button is pressed 
               if (e.getSource() == board[i][j]) {
                  //Changes game board button to red if it hits enemy ship
                  game.getHuman().fire(game.getAI(), i, j);
                  //If the AI's ship is hit
                  if (game.getAI().getBattleship()[0].getHit() || game.getAI().getBattleship()[1].getHit() || game.getAI().getBattleship()[2].getHit() || game.getAI().getBattleship()[3].getHit() ||
                      game.getAI().getSubmarine()[0].getHit() || game.getAI().getSubmarine()[1].getHit() || game.getAI().getSubmarine()[2].getHit() ||
                      game.getAI().getDestroyer()[0].getHit() || game.getAI().getDestroyer()[1].getHit()) {
                     //Change color to red
                     board[i][j].setBackground(Color.RED);
                     showMsg(msgs[8]);
                  }
                  //Changes game board button to white if it hits no enemy ship
                  else {
                     board[i][j].setBackground(Color.WHITE);
                     game.getBoard()[i][j].setMiss(true);
                     showMsg(msgs[10]);
                  }
               }
            }
         }
      }
      
      //enters if the set button is clicked      
      if (e.getSource() == set) {
         //enters if it is the setting of the battleship phase, and placeholder ArrayLists are of size 4   
         if (type == 3 && coorRowPHolder.size() == 4 && coorColPHolder.size() == 4) {
            //checks to see if board buttons click are adjacent and in a straight line, if so sets the ship coordinates
            if (((coorRowPHolder.get(0) - coorRowPHolder.get(1)) == 1 && (coorRowPHolder.get(1) - coorRowPHolder.get(2)) == 1 && (coorRowPHolder.get(2) - coorRowPHolder.get(3)) == 1 && (coorColPHolder.get(0) == coorColPHolder.get(1)) && (coorColPHolder.get(1) == coorColPHolder.get(2)) && (coorColPHolder.get(2) == coorColPHolder.get(3))) || ((coorRowPHolder.get(0) - coorRowPHolder.get(1)) == -1 && (coorRowPHolder.get(1) - coorRowPHolder.get(2)) == -1 && (coorRowPHolder.get(2) - coorRowPHolder.get(3)) == -1 && (coorColPHolder.get(0) == coorColPHolder.get(1)) && (coorColPHolder.get(1) == coorColPHolder.get(2)) && (coorColPHolder.get(2) == coorColPHolder.get(3))) || ((coorColPHolder.get(0) - coorColPHolder.get(1)) == 1 && (coorColPHolder.get(1) - coorColPHolder.get(2)) == 1 && (coorColPHolder.get(2) - coorColPHolder.get(3)) == 1 && (coorRowPHolder.get(0) == coorRowPHolder.get(1)) && (coorRowPHolder.get(1) == coorRowPHolder.get(2)) && (coorRowPHolder.get(2) == coorRowPHolder.get(3))) || ((coorColPHolder.get(0) - coorColPHolder.get(1)) == -1 && (coorColPHolder.get(1) - coorColPHolder.get(2)) == -1 && (coorColPHolder.get(2) - coorColPHolder.get(3)) == -1 && (coorRowPHolder.get(0) == coorRowPHolder.get(1)) && (coorRowPHolder.get(1) == coorRowPHolder.get(2)) && (coorRowPHolder.get(2) == coorRowPHolder.get(3)))) {
               for (int i = 0; i <= type; i++) {
                  game.getHuman().setShips(coorRowPHolder.get(i),coorColPHolder.get(i),type);
                  board[coorRowPHolder.get(i)][coorColPHolder.get(i)].setEnabled(false);
               }   
               //decrements type by 1 so we can move on the the setting of the submarine   
               type--;
               //clears placeholder ArrayLists for further use
               coorRowPHolder.clear();
               coorColPHolder.clear();
               //Printing a message that tells the user that the battleship was set
               showMsg(msgs[0]);
            } 
            //enters if above conditions are not met     
            else {
               //tells user that they are setting the ships wrong, and asks them if they understand
               int n = JOptionPane.showConfirmDialog(this, "When selecting where to place ships, they must be placed adjacent to each other",
                                             "Got it?",
                                             JOptionPane.WARNING_MESSAGE,
                                             JOptionPane.YES_NO_OPTION);
               //sets occupied status of the buttons clicked to false, and resets the color the the buttons to the standard blue                             
               if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION) {
                  for (int i = 0; i < coorRowPHolder.size(); i++) {
                     game.getBoard()[coorRowPHolder.get(i)][coorColPHolder.get(i)].setOccupied(false);
                     board[coorRowPHolder.get(i)][coorColPHolder.get(i)].setBackground(new Color(51, 153, 255));
                  }
                  //clears placeholder ArrayLists for use       
                  coorRowPHolder.clear();
                  coorColPHolder.clear();
                  //resets board
                  reset();
                  for (int r = 0; r < 8; r++) {
                     for (int c = 0; c < 8; c++) {
                        //unoccupies the buttons
                        game.getBoard()[r][c].setOccupied(false);
                     }
                  }
               }
            }
         }
         //enters if it is the setting of the submarine phase, and if placeholder ArrayLists are of size 3         
         else if (type == 2 && coorRowPHolder.size() == 3 && coorColPHolder.size() == 3){
            //checks to see if board buttons click are adjacent and in a straight line, if so sets the ship coordinates
            if (((coorRowPHolder.get(0) - coorRowPHolder.get(1)) == 1 && (coorRowPHolder.get(1) - coorRowPHolder.get(2)) == 1 && (coorColPHolder.get(0) == coorColPHolder.get(1)) && (coorColPHolder.get(1) == coorColPHolder.get(2))) || ((coorRowPHolder.get(0) - coorRowPHolder.get(1)) == -1 && (coorRowPHolder.get(1) - coorRowPHolder.get(2)) == -1 && (coorColPHolder.get(0) == coorColPHolder.get(1)) && (coorColPHolder.get(1) == coorColPHolder.get(2))) || ((coorColPHolder.get(0) - coorColPHolder.get(1)) == 1 && (coorColPHolder.get(1) - coorColPHolder.get(2)) == 1 && (coorRowPHolder.get(0) == coorRowPHolder.get(1)) && (coorRowPHolder.get(1) == coorRowPHolder.get(2))) || ((coorColPHolder.get(0) - coorColPHolder.get(1)) == -1 && (coorColPHolder.get(1) - coorColPHolder.get(2)) == -1 && (coorRowPHolder.get(0) == coorRowPHolder.get(1)) && (coorRowPHolder.get(1) == coorRowPHolder.get(2)))){
               for (int i = 0; i <= type; i++) {
                  game.getHuman().setShips(coorRowPHolder.get(i),coorColPHolder.get(i),type);
                  board[coorRowPHolder.get(i)][coorColPHolder.get(i)].setEnabled(false);
               }
               //decrements type by 1 so we can move on the the setting of the destroyer      
               type--;
               //clears placeholder ArrayLists for further use
               coorRowPHolder.clear();
               coorColPHolder.clear();
               //Printing a message that tells the user that the submarine was set
               showMsg(msgs[1]);
            }
            //enters if above conditions are not met
            else {
               //tells user that they are setting the ships wrong, and asks them if they understand
               int n = JOptionPane.showConfirmDialog(this, "When selecting where to place ships you must place them adjacent to eachother",
                                             "Got it?",
                                             JOptionPane.WARNING_MESSAGE,
                                             JOptionPane.YES_NO_OPTION);
               //sets occupied status of the buttons clicked to false, and resets the color the the buttons to the standard blue                              
               if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION) {
                  for (int i = 0;i < coorRowPHolder.size();i++) {
                     game.getBoard()[coorRowPHolder.get(i)][coorColPHolder.get(i)].setOccupied(false);
                     board[coorRowPHolder.get(i)][coorColPHolder.get(i)].setBackground(new Color(51, 153, 255));
                  }
                  //clears placeholder ArrayLists for use       
                  coorRowPHolder.clear();
                  coorColPHolder.clear();
                  //resets board
                  reset();
                  for (int r = 0; r < 8; r++) {
                     for (int c = 0; c < 8; c++) {
                        //unoccupies the buttons
                        game.getBoard()[r][c].setOccupied(false);
                     }
                  }
               }
            }    
         }
         //enters if it is the setting of the destroyer phase, and if placeholder ArrayLists are of size 2                  
         else if (type == 1 && coorRowPHolder.size() == 2 && coorColPHolder.size() == 2){
            //checks to see if board buttons click are adjacent and in a straight line, if so sets the ship coordinates
            if (((coorRowPHolder.get(0) - coorRowPHolder.get(1)) == 1 && (coorColPHolder.get(0) == coorColPHolder.get(1))) || ((coorRowPHolder.get(0) - coorRowPHolder.get(1)) == -1 && (coorColPHolder.get(0) == coorColPHolder.get(1))) || ((coorColPHolder.get(0) - coorColPHolder.get(1)) == 1  && (coorRowPHolder.get(0) == coorRowPHolder.get(1))) || ((coorColPHolder.get(0) - coorColPHolder.get(1)) == -1  && (coorRowPHolder.get(0) == coorRowPHolder.get(1)))){
               for (int i = 0; i <= type; i++) {
                  game.getHuman().setShips(coorRowPHolder.get(i),coorColPHolder.get(i),type);
                  board[coorRowPHolder.get(i)][coorColPHolder.get(i)].setEnabled(false);
               }
               //clears placeholder ArrayLists for use
               coorRowPHolder.clear();
               coorColPHolder.clear();
               //decrements by 1 to let us know that the set ship phase is done
               type--;
               //hides the set button as the set up phase is over
               set.setVisible(false);
               //shows the surrender button as the game has started
               surrender.setVisible(true);
               //Printing a message that tells the user that the destroyer was set
               showMsg("<html><center>"+msgs[2]+"<br>Get ready Commander! Enemy ships incoming!</html>");
            }
            //enters if above conditions are not met
            else {
               //tells user that they are setting the ships wrong, and asks them if they understand
               int n = JOptionPane.showConfirmDialog(this, "When selecting where to place ships, they must be placed adjacent to each other",
                                             "Got it?",
                                             JOptionPane.WARNING_MESSAGE,
                                             JOptionPane.YES_NO_OPTION);
               //sets occupied status of the buttons clicked to false, and resets the color the the buttons to the standard blue                              
               if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION) {
                  for(int i = 0;i < coorRowPHolder.size();i++){
                     game.getBoard()[coorRowPHolder.get(i)][coorColPHolder.get(i)].setOccupied(false);
                     board[coorRowPHolder.get(i)][coorColPHolder.get(i)].setBackground(new Color(51, 153, 255));
                  }
                  //resets board
                  reset();
                  for (int r = 0; r < 8; r++) {
                     for (int c = 0; c < 8; c++) {
                        //unoccupies the buttons
                        game.getBoard()[r][c].setOccupied(false);
                     }
                  }
               }  
            }
         }
         //enters if the user has click to few/much buttons during the set ship phase      
         else{
            //tells user that they have clicked to little/much buttons when placing a ship
            int n = JOptionPane.showConfirmDialog(this, "You must click the appropriate number of buttons (4 - battleship, 3 - submarine, 2 - destroyer)\n",
                                             "Got it?",
                                             JOptionPane.WARNING_MESSAGE,
                                             JOptionPane.YES_NO_OPTION);
            //sets occupied status of the buttons clicked to false, and resets the color the the buttons to the standard blue                                 
            if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION) {
                  for(int i = 0; i < coorRowPHolder.size(); i++){
                     game.getBoard()[coorRowPHolder.get(i)][coorColPHolder.get(i)].setOccupied(false);
                     board[coorRowPHolder.get(i)][coorColPHolder.get(i)].setBackground(new Color(51, 153, 255));
                   } 
               //resets placeholder ArrayLists         
               coorRowPHolder.clear();
               coorColPHolder.clear();
               //resets board
               reset();
               for (int r = 0; r < 8; r++) {
                  for (int c = 0; c < 8; c++) {
                     //unoccupies the buttons
                     game.getBoard()[r][c].setOccupied(false);
                  }
               }
            }  
         }                                          
      }

      //If player presses the tutorial button
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

      //If player presses the reset button
      else if (e.getSource() == reset) {
      int n = JOptionPane.showConfirmDialog(this, "Are you sure you want to reset the board?\n",
                                          "Reset?",
                                          JOptionPane.WARNING_MESSAGE,
                                          JOptionPane.YES_NO_OPTION);
         if (n == JOptionPane.YES_OPTION) {
            //Resets board
            reset();
            game.getHuman().resetShips();
            for (int r = 0; r < 8; r++) {
               for (int c = 0; c < 8; c++) {
                  //unoccupies the buttons
                  game.getBoard()[r][c].setOccupied(false);
               }
            }
         }
         //If the no option was chosen does it closes the window and continues by default
      }

      //If player presses the surrender button
      else if (e.getSource() == surrender) {
         int n = JOptionPane.showConfirmDialog(this, "Are you sure you want to surrender?\n",
                                          "Surrender?",
                                          JOptionPane.WARNING_MESSAGE,
                                          JOptionPane.YES_NO_OPTION);
         if (n == JOptionPane.YES_OPTION) {
            //Surrender button shows all the spots where the enemy ships were
            // so a for loop going through the board to see where the enemy ships were
            //TODO: write an isAIShip() function
            // then setting the square as orange or something to indicate
            // board[AI x][AI y].setBackground(new Color(255, 115, 0));
         }
         //If the no option was chosen does it closes the window and continues by default
      }
   }                                             

   //Resets the board (externally)
   public void reset() {
      //clearing the array for further use
      coorRowPHolder.clear();
      coorColPHolder.clear();
      for (int i = 0; i < 8; i++) { 
         for (int j = 0; j < 8; j++) {
            //Changes each square color back to blue
            board[i][j].setBackground(new Color(51, 153, 255));
            //Enables all the buttons again
            board[i][j].setEnabled(true);
         }
      }
      //type resets back to 3
      type = 3;
      //any message that appeared disappears
      msg.setVisible(false);
      //incase the set button is hidden and the surrender button is showing
      set.setVisible(true);
      surrender.setVisible(false);
   }

   //Shows message for 30 seconds then disappears
   public void showMsg(String m) {
      //Set the text and make it visible
      msg.setText(m);
      msg.setVisible(true);
      //After 30 seconds the message disappears
      //Creating a timer that waits 30 seconds to hide the JLabel
      Timer t = new Timer(30000, new ActionListener() {
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