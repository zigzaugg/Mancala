import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Instructions extends JPanel{
	Font myFont=new Font("Arial", Font.PLAIN,32);//sets it to 32 point font plain text with a font-family of arial
	String text,text1="Turn: ";
	boolean end=false;//holds if the game is done yet
	int winner=0;


	public Instructions(){
	}

	public void paint(Graphics g){
		text=Mancala.getPlayerName();
		if(end){
			if(winner==1||winner==2){
				text1=Mancala.getPlayerName(winner);
				text="Wins";
			}
			else{
				text1="Game Over";
				text="Tie";
			}
		}
		g.setColor(new Color(227,198,156));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setFont(myFont);
		g.setColor(Color.black);
		g.fillRect(0,getHeight()-1,getWidth(),getHeight());

		FontMetrics metrics = g.getFontMetrics(myFont);
		int strWidth = metrics.stringWidth(text1);
		g.drawString(text1,(int)((getWidth()/2)-(strWidth/2)),(int)(getHeight()/3));
		strWidth=metrics.stringWidth(text);
		g.drawString(text,(int)((getWidth()/2)-(strWidth/2)),2*getHeight()/3);
	}

	public void setEnd(int x){
		end=true;
		winner=x;
	}
}
