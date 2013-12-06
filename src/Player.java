import java.awt.Color;
import java.awt.Graphics;


public class Player extends Square{
	
	public static final int SIZE = 40;
	private Direction activeSide = getSide((int) (Math.random()*4));
	
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

	private Direction getSide(int startSide) {		
		switch (startSide) {
			case 0:	return Direction.UP;
			case 1:	return Direction.DOWN;
			case 2:	return Direction.LEFT;
			case 3:	return Direction.RIGHT;
			default: throw new IllegalArgumentException();
		}
	}
	
	// Method to figure out which side to light up on each player.
	public void attachSide() {
		activeSide = getSide((int) (Math.random()*4));
	}
	
	public void draw(Graphics g, Color c) {
    	g.setColor(c);
    	g.fillRect(pos_x, pos_y, width, height);
    	g.setColor(Color.GREEN);
    	switch (activeSide) {
    		case UP:
    			g.fillRect(pos_x, pos_y, width, 4);
    			break;
    		case DOWN:
    			g.fillRect(pos_x, pos_y + height - 4, width, 4);
    			break;
    		case LEFT:
    			g.fillRect(pos_x, pos_y, 4, height);
    			break;
    		case RIGHT:
    			g.fillRect(pos_x + width - 4, pos_y, 4, height);
    			break;
    		default: throw new IllegalArgumentException();
    	}
    }

}
