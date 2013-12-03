import java.awt.Color;
import java.awt.Graphics;


public class Player extends Square{
	
	public Player(int INIT_X, int INIT_Y, int courtWidth, int courtHeight) {
		super(INIT_X, INIT_Y, courtWidth, courtHeight);
	}

	public void draw(Graphics g, Color c) {
    	g.setColor(c);
    	g.fillRect(pos_x, pos_y, width, height);
    }

}
