import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pocket extends JPanel{

	int marbles;//number of marbles
	int num;//location on the board, corresponds to array in main class
	int padding=30;//space between pockets
	int marbleWidth=20;
	int marblePoint=marbleWidth/2;//used to center the marbles
	//establish colors for everything
	Color marbleColor=new Color(230,0,0);
	Color pocketColor=new Color(177,148,106);
	Color backgroundColor=new Color(227,198,156);
	Color textColor=new Color(0,0,0,100);
	//sets the font and text centered on the pocket
	Font myFont=new Font("Arial", Font.PLAIN,32);
	String text;

	public Pocket(){
		marbles=4;
		addMouseListener(new PanelListener());//adds listener to the pockets for click events
	}

	public void paint(Graphics g){
		//draw the background and pockets
		g.setColor(backgroundColor);
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(pocketColor);
		g.fillOval((int)(padding/2),(int)(padding/2),getWidth()-padding,getHeight()-padding);
		g.setColor(marbleColor);
		//draw the marbles
		drawMarbles(g);		

		//draw the numbers centered on the pocket
		g.setColor(textColor);
		g.setFont(myFont);
		text=""+getMarbles();
		// get metrics from the graphics
		FontMetrics metrics = g.getFontMetrics(myFont);
		//get the height of the text
		int strHeight = metrics.getHeight();
		//get the width of the text
		int strWidth = metrics.stringWidth(text);
		g.drawString(text,(int)(getWidth()/2-strWidth/2),(int)(getHeight()/2+strHeight/2.5));//actually draws the number
	}

	public int getMarbles(){
		return marbles;
	}
	public int getNum(){
		return num;
	}
	public void setMarbles(int n){
		marbles=n;
	}
	public void setNum(int n){
		num = n;
	}
	public void addMarble(){
		marbles++;
	}
	public int takeMarbles(){
		int m = marbles;
		marbles = 0;
		return m;
	}

	public void drawMarbles(Graphics g){
		switch(getMarbles()){
			case 1:one(g);break;
			case 2:two(g);break;
			case 3:three(g);break;
			case 4:four(g);break;
			case 5:five(g);break;
			case 6:six(g);break;
			case 7:seven(g);break;
			case 8:eight(g);break;
			case 9:nine(g);break;
			// case 10:ten(g);break;
			// case 11:eleven(g);break;
			// case 12:tweleve(g);break;
			// case 13:thirteen(g);break;
			// case 14:forteen(g);break;
			// case 15:fifteen(g);break;
		}
		if(getMarbles()>9){
			nine(g);
		}
	}

	//these methods draw the marbles onto the screen
	public void one(Graphics g){
		g.fillOval(getWidth()/2-marblePoint,getHeight()/2-marblePoint,marbleWidth,marbleWidth);
	}
	public void two(Graphics g){
		g.fillOval(getWidth()/3-marblePoint,getHeight()/2-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()*2/3-marblePoint,getHeight()/2-marblePoint,marbleWidth,marbleWidth);
	}
	public void three(Graphics g){
		g.fillOval(getWidth()/3-marblePoint,getHeight()*2/3-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()*2/3-marblePoint,getHeight()*2/3-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()/2-marblePoint,getHeight()/3-marblePoint,marbleWidth,marbleWidth);
	}
	public void four(Graphics g){
		g.fillOval(getWidth()/3-marblePoint,getHeight()*2/3-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()*2/3-marblePoint,getHeight()*2/3-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()/3-marblePoint,getHeight()/3-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()*2/3-marblePoint,getHeight()/3-marblePoint,marbleWidth,marbleWidth);
	}
	public void five(Graphics g){
		four(g);
		one(g);
	}
	public void six(Graphics g){
		two(g);
		g.fillOval(getWidth()/3-marblePoint,getHeight()/3-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()*2/3-marblePoint,getHeight()/3-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()/3-marblePoint,getHeight()*2/3-marblePoint,marbleWidth,marbleWidth);
		g.fillOval(getWidth()*2/3-marblePoint,getHeight()*2/3-marblePoint,marbleWidth,marbleWidth);
	}
	public void seven(Graphics g){
		six(g);
		one(g);
	}
	public void eight(Graphics g){
		six(g);
		three(g);
		g.fillOval(getWidth()/2-marblePoint,getHeight()*2/3-marblePoint,marbleWidth,marbleWidth);
	}
	public void nine(Graphics g){
		eight(g);
		one(g);
		
	}


	private class PanelListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			//execute code that changes the numbers in the pockets
			//int y = Mancala.getPlayerTurn();
			int p = Mancala.getPlayerTurn();
			if(p == 1 && getNum() < 6 || p == 2 && getNum() > 5)
			{
				if(getMarbles()!=0){
					int temp = Mancala.turn(getNum(),getMarbles(), p);
					Mancala.setPlayerTurn(temp);
					Mancala.redo();
				}
			}
			if(Mancala.winDetection()){
				Mancala.endGame();
			}
		}
	}
}
