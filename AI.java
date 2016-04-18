import java.util.ArrayList;

/**
 * Students: Joanna Bistekos, Jessica Inkpen, Jon MacDonald
 * Due: 22 April 2016
 * Class: CSCI 1101, Section 01
 */

public class AI extends Player {
   
   private ArrayList<Block> targeted;    //Contains block object that AI has targeted
   private ArrayList<Block> hits;        //Holds successfully targeted Blocks (that have not been sunk)
   
   //Constructor
   public AI() {
      super();
      targeted = new ArrayList<Block>();
      hits = new ArrayList<Block>();
   }
   
   //Method for computer to set ships on board
   public void setShips(Square[][] board) {
      setBattleship(board);
      setSubmarine(board);
      setDestroyer(board);
   }
   
   //Method to set battleship (called from setShips)
   public void setBattleship(Square[][] board) {
      int direction = -1;  //0 = horizontal, 1 = vertical
      int row = -1;        //Will be assigned (pseudorandom number (0-7) 
      int col = -1;        //Will be assigned (pseudorandom number (0-7) 
      //While battleship has not been set
      while (!battleHasBeenSet()) {
         ArrayList<Block> temp = new ArrayList<Block>(); 
         //Choose direction (0 or 1)
         direction = (int)(Math.random()*2); 
         //If direction is horizontal
         if (direction == 0) {
            row = (int)(Math.random()*8); //Number between 0 and 7
            col = (int)(Math.random()*5); //Number between 0 and 4
            //Check that blocks that battleship would occupy are not already occupied
            for (int i = col; i < col + 4; i++) {
               //If block is not occupied, add to temp
               if (!board[row][i].getBlock().getOccupied())
                  temp.add(board[row][i].getBlock());
               //Otherwise, break out of for loop
               else
                  break;
            }
            //If temp is correct size, add each block to battleship array
            if (temp.size() == 4) {
               for (int i = 0; i < getBattleship().length; i++) {
                  setBattleship(i, temp.get(i)); 
               }
               //Set battleHasBeenSet to true
               setBattleHasBeenSet(true);
            }
         }
         //If direction is vertical
         else {
            row = (int)(Math.random()*5); //Number between 0 and 4
            col = (int)(Math.random()*8); //Number between 0 and 7
            //Check that blocks that battleship would occupy are not already occupied
            for (int i = row; i < row + 4; i++) {
               //If block is not occupied, add to temp
               if (!board[i][col].getBlock().getOccupied())
                  temp.add(board[i][col].getBlock());
               //Otherwise, break out of for loop
               else
                  break;
            }
            //If temp is correct size, add each block to battleship array
            if (temp.size() == 4) {
               for (int i = 0; i < getBattleship().length; i++) {
                  setBattleship(i, temp.get(i)); 
               }
               //Set battleHasBeenSet to true
               setBattleHasBeenSet(true);
            }
         }
      }
   }
   //Method to set submarine (called from setShips)
   public void setSubmarine(Square[][] board) {
      int direction = -1;  //0 = horizontal, 1 = vertical
      int row = -1;        //Will be assigned (pseudorandom number (0-7) 
      int col = -1;        //Will be assigned (pseudorandom number (0-7) 
      //While submarine has not been set
      while (!subHasBeenSet()) {
         ArrayList<Block> temp = new ArrayList<Block>(); 
         //Choose direction (0 or 1)
         direction = (int)(Math.random()*2); 
         //If direction is horizontal
         if (direction == 0) {
            row = (int)(Math.random()*8); //Number between 0 and 7
            col = (int)(Math.random()*6); //Number between 0 and 5
            //Check that blocks that submarine would occupy are not already occupied
            for (int i = col; i < col + 3; i++) {
               //If block is not occupied, add to temp
               if (!board[row][i].getBlock().getOccupied())
                  temp.add(board[row][i].getBlock());
               //Otherwise, break out of for loop
               else
                  break;
            }
            //If temp is correct size, add each block to submarine array
            if (temp.size() == 3) {
               for (int i = 0; i < getSubmarine().length; i++) {
                  setSubmarine(i, temp.get(i)); 
               }
               //Set subHasBeenSet to true
               setSubHasBeenSet(true);
            }
         }
         //If direction is vertical
         else {
            row = (int)(Math.random()*6); //Number between 0 and 5
            col = (int)(Math.random()*8); //Number between 0 and 7
            //Check that blocks that submarine would occupy are not already occupied
            for (int i = row; i < row + 3; i++) {
               //If block is not occupied, add to temp
               if (!board[i][col].getBlock().getOccupied())
                  temp.add(board[i][col].getBlock());
               //Otherwise, break out of for loop
               else
                  break;
            }
            //If temp is correct size, add each block to submarine array
            if (temp.size() == 3) {
               for (int i = 0; i < getSubmarine().length; i++) {
                  setSubmarine(i, temp.get(i)); 
               }
               //Set subHasBeenSet to true
               setSubHasBeenSet(true);
            }
         }
      }
   }
   //Method to set destroyer (called from setShips)
   public void setDestroyer(Square[][] board) {
      int direction = -1;  //0 = horizontal, 1 = vertical
      int row = -1;        //Will be assigned (pseudorandom number (0-7) 
      int col = -1;        //Will be assigned (pseudorandom number (0-7) 
      //While destroyer has not been set
      while (!destroyHasBeenSet()) {
         ArrayList<Block> temp = new ArrayList<Block>(); 
         //Choose direction (0 or 1)
         direction = (int)(Math.random()*2); 
         //If direction is horizontal
         if (direction == 0) {
            row = (int)(Math.random()*8); //Number between 0 and 7 
            col = (int)(Math.random()*7); //Number between 0 and 6 
            //Check that blocks that destroyer would occupy are not already occupied
            for (int i = col; i < col + 2; i++) {
               //If block is not occupied, add to temp
               if (!board[row][i].getBlock().getOccupied())
                  temp.add(board[row][i].getBlock());
               //Otherwise, break out of for loop
               else
                  break;
            }
            //If temp is correct size, add each block to destroyer array
            if (temp.size() == 2) {
               for (int i = 0; i < getDestroyer().length; i++) {
                  setDestroyer(i, temp.get(i)); 
               }
               //Set destroyHasBeenSet to true
               setDestroyHasBeenSet(true);
            }
         }
         //If direction is vertical
         else {
            row = (int)(Math.random()*7); //Number between 0 and 6
            col = (int)(Math.random()*8); //Number between 0 and 7
            //Check that blocks that destroyer would occupy are not already occupied
            for (int i = row; i < row + 2; i++) {
               //If block is not occupied, add to temp
               if (!board[i][col].getBlock().getOccupied())
                  temp.add(board[i][col].getBlock());
               //Otherwise, break out of for loop
               else
                  break;
            }
            //If temp is correct size, add each block to destroyer array
            if (temp.size() == 2) {
               for (int i = 0; i < getDestroyer().length; i++) {
                  setDestroyer(i, temp.get(i)); 
               }
               //Set destroyHasBeenSet to true
               setDestroyHasBeenSet(true);
            }
         }
      }
   }
   
   //Method for computer to fire at enemy ships 
   public void fire(Square[][] board, Player player) {
      Square target = (Square)null;
      //If there are no blocks in the targeted array
      if (hits.isEmpty()) {
         target = randomShot(board);
      }
      else {
         target = nonRandomShot(board);
      }
      takeShot(board, target, player);    
   }
   
   //Method that generates a random shot (called if hits array list is empty)
   public Square randomShot(Square[][] board) {
      //Generate random row and column coordinates (0-7) 
      int row = (int)(Math.random()*8);
      int col = (int)(Math.random()*8);
      //While block with row/col has been targeted, generate new row/col
      while (targeted.contains(board[row][col].getBlock())) {
         row = (int)(Math.random()*8);
         col = (int)(Math.random()*8);
      }
      return board[row][col];
   }
   
   //Method that generates shot based on previous successful hits (called from fire)
   public Square nonRandomShot(Square[][] board) {
      int row;
      int col;
      if (hits.size() == 1) {
         row = hits.get(hits.size()-1).getRow();
         col = hits.get(hits.size()-1).getCol();
         if (row == 0) {
            if (col == 0) {
               if (!targeted.contains(board[row+1][col].getBlock()))
                  return board[row+1][col];
               else 
                  return board[row][col+1];
            }
            else if (col == 7) {
               if (!targeted.contains(board[row+1][col].getBlock()))
                  return board[row+1][col];
               else 
                  return board[row][col-1];
            }
            else {
               if (!targeted.contains(board[row+1][col].getBlock())) 
                  return board[row+1][col];   
               else if (!targeted.contains(board[row][col+1].getBlock()))
                  return board[row][col+1];
               else if (!targeted.contains(board[row][col-1].getBlock()))
                  return board[row][col-1];
            }
         }
         else if (row == 7) {
            if (col == 0) {
               if (!targeted.contains(board[row-1][col].getBlock()))
                  return board[row-1][col];
               else 
                  return board[row][col+1];
            }
            else if (col == 7) {
               if (!targeted.contains(board[row-1][col].getBlock()))
                  return board[row-1][col];
               else 
                  return board[row][col-1];
            }
            else {
               if (!targeted.contains(board[row-1][col].getBlock())) 
                  return board[row-1][col];   
               else if (!targeted.contains(board[row][col+1].getBlock()))
                  return board[row][col+1];
               else if (!targeted.contains(board[row][col-1].getBlock()))
                  return board[row][col-1];
            }         
         }
         else if (col == 0) {
            if (!targeted.contains(board[row][col+1].getBlock())) 
               return board[row][col+1];   
            else if (!targeted.contains(board[row+1][col].getBlock()))
               return board[row+1][col];
            else if (!targeted.contains(board[row-1][col].getBlock()))
               return board[row-1][col];
         }  
         else if (col == 7) {
            if (!targeted.contains(board[row][col-1].getBlock())) 
               return board[row][col-1];   
            else if (!targeted.contains(board[row+1][col].getBlock()))
               return board[row+1][col];
            else if (!targeted.contains(board[row-1][col].getBlock()))
               return board[row-1][col];
         }
         else {
            if (!targeted.contains(board[row][col-1].getBlock())) 
               return board[row][col-1];
            else if (!targeted.contains(board[row][col+1].getBlock())) 
               return board[row][col+1]; 
            else if (!targeted.contains(board[row+1][col].getBlock()))
               return board[row+1][col];
            else if (!targeted.contains(board[row-1][col].getBlock()))
               return board[row-1][col];
         }
      }
      else {
         if (hits.get(hits.size()-1).getRow() == hits.get(hits.size()-2).getRow()) { 
            int col1;
            int col2;
            row = hits.get(hits.size()-1).getRow();
            if (hits.get(hits.size()-1).getCol() < hits.get(hits.size()-2).getCol()) {
               col1 = hits.get(hits.size()-1).getCol();
               col2 = hits.get(hits.size()-2).getCol();
            }
            else {
               col1 = hits.get(hits.size()-2).getCol();
               col2 = hits.get(hits.size()-1).getCol();
            }
            while (targeted.contains(board[row][col1].getBlock()) && 
                   !board[row][col1].getBlock().getMiss()&& col1 - 1 >= 0) 
               col1 -= 1;
            if (!targeted.contains(board[row][col1].getBlock()))
               return board[row][col1];
            else {
               while (targeted.contains(board[row][col2].getBlock()) && 
                      !board[row][col2].getBlock().getMiss() && col2 + 1 <= 7)
                  col2 += 1;
               if (!targeted.contains(board[row][col2].getBlock()))
                  return board[row][col2];
            }
         }
         else if (hits.get(hits.size()-1).getCol() == hits.get(hits.size()-2).getCol()) {
            int row1;
            int row2;
            col = hits.get(hits.size()-1).getCol();
            if (hits.get(hits.size()-1).getRow() < hits.get(hits.size()-2).getRow()) {
               row1 = hits.get(hits.size()-1).getRow();
               row2 = hits.get(hits.size()-2).getRow();
            }
            else {
               row1 = hits.get(hits.size()-2).getRow();
               row2 = hits.get(hits.size()-1).getRow();
            }
            while (targeted.contains(board[row1][col].getBlock()) && 
                   !board[row1][col].getBlock().getMiss() && row1 - 1 >= 0) 
               row1 -= 1;
            if (!targeted.contains(board[row1][col].getBlock()))
               return board[row1][col];
            else {
               while (targeted.contains(board[row2][col].getBlock()) && 
                      !board[row2][col].getBlock().getMiss() && row2 + 1 <= 7)
                  row2 += 1;
               if (!targeted.contains(board[row2][col].getBlock()))
                  return board[row2][col];
            }
         }
      }
      return randomShot(board);
   }
   
   //Method to take shot (called from fire)
   public void takeShot(Square[][] board, Square target, Player player) {
      targeted.add(target.getBlock());
      if (target.getBlock().getOccupied()) {
         hits.add(target.getBlock());
         target.getBlock().setHit(true);
         target.setBackground(Square.hitColor);
         int ship = target.getBlock().getShip();
         if (player.checkShipSunk(board, player, ship))
            for (int i = hits.size()-1; i >= 0 ; i--)
               if (hits.get(i).getShip() == ship)
                  hits.remove(i);
      }
      else {
         target.setBackground(Square.missColor);
         target.getBlock().setMiss(true);
      }
   }
      
   //Method to reveal ai ships if human player surrenders
   public void reveal(Square[][] board) {
      int row = -1;
      int col = -1;
      for (int i = 0; i < getBattleship().length; i++) {
         row = getBattleship()[i].getRow();
         col = getBattleship()[i].getCol();
         board[row][col].setBackground(Square.shipColor);
      }
      for (int i = 0; i < getSubmarine().length; i++) {
         row = getSubmarine()[i].getRow();
         col = getSubmarine()[i].getCol();
         board[row][col].setBackground(Square.shipColor);
      }
      for (int i = 0; i < getDestroyer().length; i++) {
         row = getDestroyer()[i].getRow();
         col = getDestroyer()[i].getCol();
         board[row][col].setBackground(Square.shipColor);
      }
   }
         
}