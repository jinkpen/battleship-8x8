import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */
 
 public class Square extends JButton{
   
   private Block block;
   
   //Colour definitions
   public static Color oceanColor = new Color(51, 153, 255);
   public static Color shipColor = new Color(181, 177, 177);
   public static Color missColor = new Color(255, 255, 255);
   public static Color hitColor = new Color(255, 1, 1);
   public static Color sunkColor = new Color(0, 0, 0);
   
   //Constructor
   public Square(int r, int c) {
      super();
      block =  new Block(r, c);
      setBackground(oceanColor);
      setOpaque(true);
      setBorderPainted(true);
   }
   
   public void setBlock(Block b) {block = b;}
   public Block getBlock() {return block;}
  
   
}