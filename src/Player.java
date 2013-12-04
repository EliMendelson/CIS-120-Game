import java.awt.Color;
import java.awt.Graphics;


public class Player extends Square{
	
	public static final int SIZE = 40;
	
	public Player(int INIT_X, int INIT_Y, int court_width, int court_height) {
		
		this.v_x = INIT_VEL_X;
		this.v_y = INIT_VEL_Y;
		this.pos_x = INIT_X;
		this.pos_y = INIT_Y;
		this.width = SIZE;
		this.height = SIZE;
		
		// take the width and height into account when setting the 
		// bounds for the upper left corner of the object.
		this.max_x = court_width - width;
		this.max_y = court_height - height;
	}

	public void draw(Graphics g, Color c) {
    	g.setColor(c);
    	g.fillRect(pos_x, pos_y, width, height);
    }

}
