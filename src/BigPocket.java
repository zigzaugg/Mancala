import javax.swing.*;
import java.awt.*;

public class BigPocket extends Pocket{

	int marbles;
	int padding=30;
	Color pocketColor=new Color(177,148,106);
	Color backgroundColor=new Color(227,198,156);
	Color textColor=new Color(0,0,0);
	Font myFont=new Font("Arial", Font.PLAIN,32);//sets it to 32 point font plain text with a font-family of arial
	String text;//to be replaced with appropriate number

	public BigPocket(){
		marbles = 0;
	}
	public void paint(Graphics g){
		g.setColor(backgroundColor);//sets color to background
		g.fillRect(0,0,getWidth(),getHeight());//draws the background
		g.setColor(pocketColor);
		g.fillRoundRect((int)(padding/2),(int)(padding/2),getWidth()-padding,getHeight()-padding,50,50);

		g.setColor(textColor);//sets text color
		g.setFont(myFont);//sets the font to myFont

		text=""+getMarbles();
		g.setColor(Color.red);
		drawMarbles(g);
		g.setColor(Color.black);

		
		FontMetrics metrics = g.getFontMetrics(myFont);

		int strHeight = metrics.getHeight();

		int strWidth = metrics.stringWidth(text);

		g.drawString(text,(int)(getWidth()/2-strWidth/2),(int)(getHeight()/2+strHeight/4));//actually draws the number
	}
	public void addMarble(){
		marbles++;
	}
	public void add(int n){
		marbles += n;
	}
	public void setMarbles(int n){
		marbles=n;
	}
	public int getMarbles(){
		return marbles;
	}
}
