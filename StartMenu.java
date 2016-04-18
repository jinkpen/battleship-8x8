/**
 * 'Old School United Stencil' font retrieved from http://www.dafont.com/old-school-united-stencil.font
 * Created by Frédéric Rich (http://www.fthafm.com/)
 *
 * Background image retrieved from http://www.griffingamesltd.com/wp-content/uploads/2014/03/with-angle-BB-thumbnail.jpg
 * Created by Aloysius Patrimonio (http://apatrimonio.com/)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 *  Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public class StartMenu extends JFrame implements ActionListener {
   //Instance variables
   private JButton deploy,
                   credits;

   //Constructor
   public StartMenu() {
      //setting the window properties
      setTitle("Battleship!");
      setSize(600, 400);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);

      //Setting the background image and scaling it down to size
      setContentPane(new JLabel(new ImageIcon(new ImageIcon("startmenu_background.png").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT))));
      //Using absolute positioning instead of a layout manager
      setLayout(null);
      
      //Creating the buttons
      deploy = new JButton("DEPLOY SHIPS");
      credits = new JButton("CREDITS");

      //Adding the buttons to the frame
      add(deploy);
      deploy.addActionListener(this);
      add(credits);
      credits.addActionListener(this);

      //Setting the coordinates and sizes of the buttons
      deploy.setBounds(40, 230, 260, 35);
      credits.setBounds(40, 290, 260, 35);

      //Refreshing the frame so the buttons come to the front
      setSize(599, 399);
      setSize(600, 400);
   }

   public void actionPerformed(ActionEvent e) {
      //If deploy button is pressed
      if (e.getSource() == deploy) {
         //Hides the start menu
         setVisible(false);
         //Create a Battleship game object
         PlayBattleship play = new PlayBattleship();
      }
      //If credits button is pressed
      else if(e.getSource() == credits) {
         //Pop up box displaying who coded this!
         JOptionPane.showMessageDialog(this, "Coded by: Joanna Bistekos, Jessica Inkpen and Jon MacDonald",
                 "Credits", 
                 JOptionPane.INFORMATION_MESSAGE);
      }
   }

   public static void main(String args[]) {
      StartMenu menu = new StartMenu();
   }
}