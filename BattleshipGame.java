import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
   Due: 22 April 2016
   Class: CSCI 1101, Section 01
*/

public class BattleshipGame extends JFrame implements ActionListener {
   //JPanels
   private JPanel message;
   private JPanel grid;
   private JPanel buttons;

   private JButton[][] board;                //Game board
   private JLabel[] letters;                 //Letter labels [grid panel]
   private JLabel[] numbers;                 //Number labels [grid panel]
   private String[] lets = {"   A", "   B", "   C", "   D", "   E", "   F", "   G", "   H"};
   private String[] nums = {"   1", "   2", "   3", "   4", "   5", "   6", "   7", "   8"};

   //Buttons panel
   private JButton surrender,    //Button to reveal enemy ships
                   reset,         //Button to clear board
                   tutorial,
                   set;

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
                  aiSunk;           //Indicate computer ship sunk
                  //msg; //Label that can be any of the above messages [testing for simplification]

   //private String[] msgs = {"You set the BATTLESHIP!", "You set the SUBMARINE!", "You set the DESTROYER!"};

   private static int turn = 0;

   //Contructor 
   public BattleshipGame() {
      //Create panels
      message = new JPanel(new FlowLayout());
      message.setPreferredSize(new Dimension(this.WIDTH, 100));
      grid = new JPanel(new GridLayout(9, 9, 0, 0));
      buttons = new JPanel(new FlowLayout());
       
      //Create labels for message panel
      battleSet = new JLabel("Battleship set");
      message.add(battleSet);
      battleSet.setVisible(false);
       
      subSet = new JLabel("Submarine set");
      destSet = new JLabel("Destroyer set");
      // or even don't have a message jlabel set and just create a new one every time
      /*msg = new JLabel("");
      message.add(msg);
      msg.setVisible(false);*/
         
      //Create buttons for buttons panel
      surrender = new JButton("Surrender");
      buttons.add(surrender);
      surrender.addActionListener(this);

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
      setTitle("Battleship");
      setSize(450, 600);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
   }

   //determines what happens if a button is clicked
   public void actionPerformed(ActionEvent e) {
      //human that is playing the game
      BattleshipBoard game = new BattleshipBoard(); 
      //check if it is the set ship portion of the game
      //if(person.getBattleship()[0] == null || person.getBattleship()[1] == null || person.getBattleship()[2] == null || person.getBattleship()[3] == null || person.getSubmarine()[0] == null || person.getSubmarine()[1] == null || person.getSubmarine()[2] == null || person.getDestroyer()[0] == null || person.getDestroyer()[1] == null){
      for (int i = 0; i < 8; i++) { 
         for (int j = 0; j < 8; j++) {
            if (e.getSource() == board[i][j]) {
               board[i][j].setBackground(Color.GRAY);
               //use something to keep track of click buttons coordinates(Integer)
             /*else if(e.getSource() == set){
               use setShip method to keep track of coordinates in ship arrays
             }*/
               //showMsg(msgs[1]);
               battleSet.setVisible(true);
               board[i][j].setEnabled(false);
            }
         }
      //}
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
                 + "<li>A destroyer that sinks after 2 hits</li></ul>"
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
            // reset here
            reset();
         } else if (n == JOptionPane.NO_OPTION) {
            // close window and go back
            // it does it by default so probably wont need this
         }
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
            // 
         } else if (n == JOptionPane.NO_OPTION) {
            // close window and go back
            // it does it by default so probably wont need this
         }
      }
   }                                             

   //Resets the board
   public void reset() {
      for (int i = 0; i < 8; i++) { 
         for (int j = 0; j < 8; j++) {
            //Changes each square color back to blue
            board[i][j].setBackground(new Color(51, 153, 255));
            //Enables all the buttons again
            board[i][j].setEnabled(true);
         }
      }
   }

   //Shows message for a minute then disappears
   /*public void showMsg(String m){
      try{
         msg.setText(m);
         msg.setVisible(true);
         Thread.sleep(60000);
         msg.setVisible(false);
      } catch (Exception e){}
   }*/

   //Main function
   public static void main(String[] args) {
      StartMenu menu = new StartMenu();
   }
}              