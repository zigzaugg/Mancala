import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Mancala{

	/*Each of these variables is an array of objects from each of our classes
	  These are what are shown on the screen
	*/
	static Pocket[] pockets=new Pocket[12];//initializes array of 12 pocket objects
	static BigPocket[] bigPockets = new BigPocket[2];//initializes array of 2 big pockets
	static Instructions[] instructions=new Instructions[1];//Instructions at the top

	//these variables hold information about the players and whose turn it is
	static int playerTurn=1;
	static String player1, player2;

	public static void main(String[] args){	
		//Propts the user for both player names so they know whose turn it is
		setPlayerName();
		//initializes all of the pockets
		initPockets();
		//allows everything to show up on the screen
		crazyGuiStuff(pockets, bigPockets, instructions);
	}	

	static void setPlayerName(){
		player1=JOptionPane.showInputDialog("Enter player 1's name:", "Player 1");
		player2=JOptionPane.showInputDialog("Enter player 2's name:", "Player 2");
		//if nothing entered, use a default
		if(player1.equals("")){
			player1="Player 1";
		}
		if(player2.equals("")){
			player2="Player 2";
		}
		//if they're the same, identify them with a suffix
		if(player1.equals(player2)){
			player1+=" (1)";
			player2+=" (2)";
		}
	}

	static void initPockets(){
		//initializes the big pockets
		for(int k = 0; k<2; k++)
			bigPockets[k] = new BigPocket();

		//initializes the normal pockets
		for(int k=0;k<12;k++){
			pockets[k]=new Pocket();
			pockets[k].setNum(k);
			pockets[k].setMarbles(4);
		}

		//initalizes the Instructions
		instructions[0]=new Instructions();
	}

	static void crazyGuiStuff(Pocket[] pockets, BigPocket[] bigPockets, Instructions[] instructions){ 
		JFrame gui=new JFrame();
		gui.setTitle("Mancala");
		int size=165*3;
		gui.setSize((int)(size*7.75),size);//sets size to 3:1 ratio
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = gui.getContentPane();//the window that holds eveything
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();		
		c.fill = c.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		for(int i = 0; i < 12; i++){
			if(i<6){
					c.gridy = 2;
					c.gridx = i + 1;
			}else{			//sets pockets counterclockwise starting bottom left
					c.gridy = 1;
					c.gridx = 12 - i;
			}
			pane.add(pockets[i], c);
		}
		c.gridheight = 2;
		c.gridy = 1;
		c.gridx = 0;
		pane.add(bigPockets[0], c);
		c.gridx = 7;
		pane.add(bigPockets[1], c);
		c.gridheight=1;
		c.gridwidth=8;
		c.gridy=0;
		c.gridx=0;
		pane.add(instructions[0] ,c);
				

				//Background panel=new Background();//class that draws the board
				//pane.add(panel);
		gui.setVisible(true);//lets us see it
	}

	static int getPlayerTurn() {
		return playerTurn;
	}
	
	static void setPlayerTurn(int p) {
		playerTurn = p;
	}
	
	//returns the current player's name
	static String getPlayerName(){
		if(getPlayerTurn()==1)
			return player1;
		else
			return player2;
	}
	
	//returns the specified player name
	static String getPlayerName(int x){
		if(x==1)
			return player1;
		else
			return player2;
	}

	

	//repaint all panels
	static void redo(){
		for(int i=0;i<12;i++){
			pockets[i].repaint();
		}
		for(int i=0;i<2;i++){
			bigPockets[i].repaint();
		}
		instructions[0].repaint();
	}

	//when one of the pockets is clicked, it executes this method to change the other pockets
	static int turn(int index, int marbles, int player){
		pockets[index].setMarbles(0);
		while(marbles>0){ //distributes marbles
			if(player==1&&index==5){
				bigPockets[1].addMarble();
				marbles--;
				if(marbles==0)
					return 1;
			}
			if(player==2&&index==11){
				bigPockets[0].addMarble();
				index=-1;
				marbles--;
				if(marbles==0)
					return 2;
			}
			if(marbles>0){
				index++;
				if(index==12)
					index=0;
				pockets[index].addMarble();
				marbles--;
			}
		}
		int opposite=11-index;
		//if end pocket contains a zero then take all points from opposite pocket and the final one
		//this one is for player 1
		if(player==1&&opposite>5){
			if(pockets[index].getMarbles()==1)//because 1 just got added so it actually was 0 before the move
			{
				if(pockets[opposite].getMarbles()>0){
					bigPockets[1].add(pockets[opposite].getMarbles()+1);//adds to player 1's score
					pockets[opposite].setMarbles(0);
					pockets[index].setMarbles(0);
				}
			}
		}
		//this one is for player 2
		if(player==2&&opposite<6){
			if(pockets[index].getMarbles()==1)//because 1 just got added so it actually was 0 before the move
			{
				if(pockets[opposite].getMarbles()>0){
					bigPockets[0].add(pockets[opposite].getMarbles()+1);//adds to player 2's score
					pockets[opposite].setMarbles(0);
					pockets[index].setMarbles(0);
				}
			}
		}
		if(player==1)
			return 2;
		else
			return 1;
	}

	//assumes there will never be a negative number
	//determines if somone won the game
	static boolean winDetection(){
		int sum1=0, sum2=0;

		for(int x=0;x<6;x++)
			sum1+=pockets[x].getMarbles();
		if(sum1==0)
			return true;
		for(int x=6;x<12;x++)
			sum2+=pockets[x].getMarbles();
		if(sum2==0)
			return true;
		return false;
	}

	//if someone cannot play, the game ends
	static void endGame(){
		for(int x=0;x<6;x++){
			bigPockets[1].add(pockets[x].getMarbles());
			pockets[x].setMarbles(0);
		}
		for(int x=6;x<12;x++){
			bigPockets[0].add(pockets[x].getMarbles());
			pockets[x].setMarbles(0);
		}
		if(bigPockets[0].getMarbles()>bigPockets[1].getMarbles()){
			instructions[0].setEnd(2);
		}
		if(bigPockets[1].getMarbles()>bigPockets[0].getMarbles()){
			instructions[0].setEnd(1);
		}
		if(bigPockets[0].getMarbles()==bigPockets[1].getMarbles()){
			instructions[0].setEnd(3);
		}
		redo();
	}
}
