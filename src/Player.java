import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class Player extends Square{
	
	public static final int SIZE = 40;
	private Direction activeSide = getSide((int) (Math.random()*4));
	private int area = SIZE*SIZE;
	private int lives = 3;
	private ArrayList<Block> activeBlocks
									= new ArrayList<Block>();
	
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

	public void addBlock(Block block) {
		updateArea();
		block.xDiff = block.pos_x - this.pos_x;
		block.yDiff = block.pos_y - this.pos_y;
		activeBlocks.add(block);
	}
	
	public void updateXVelocity(int velocity) {
		this.v_x = velocity;
		for (Block block : activeBlocks) {
			block.v_x = velocity;
		}
	}
	public void updateYVelocity(int velocity) {
		this.v_y = velocity;
		for (Block block : activeBlocks) {
			block.v_y = velocity;
		}
	}
	
	@Override
	public void move(){
		int old_x = pos_x;
		int old_y = pos_y;
		
		pos_x += v_x;
		pos_y += v_y;

		clip();
		for (Block block : activeBlocks) {
			block.pos_x += pos_x - old_x;
			block.pos_y += pos_y - old_y;
			
		}
	}
	
	public int livesLeft() {
		return lives;
	}
	
	public void loseLife() {
		lives--;
		for (Block block : activeBlocks) {
			block.pos_x = this.pos_x + block.xDiff;
			block.pos_y = this.pos_y + block.yDiff;
		}
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
	
	public Direction getActiveSide() {
		return activeSide;
	}
	
	private void updateArea() {
		area += (Block.SIZE * Block.SIZE);
	}
	
	public void draw(Graphics g, Color c) {
    	g.setColor(c);
    	g.fillRect(pos_x, pos_y, width, height);
    	g.setColor(Color.GREEN);
    	switch (activeSide) {
    		case UP:
    			g.fillRect(pos_x, pos_y, width, 4);
    			for (Block block : activeBlocks) {
    				block.draw(g);
    				g.fillRect(block.pos_x, block.pos_y, block.width, 4);
    			}
    			break;
    		case DOWN:
    			g.fillRect(pos_x, pos_y + height - 4, width, 4);
    			for (Block block : activeBlocks) {
    				block.draw(g);
    				g.fillRect(block.pos_x, block.pos_y + block.height - 4,
    														block.width, 4);
    			}
    			break;
    		case LEFT:
    			g.fillRect(pos_x, pos_y, 4, height);
    			for (Block block : activeBlocks) {
    				block.draw(g);
    				g.fillRect(block.pos_x, block.pos_y, 4, block.height);
    			}
    			break;
    		case RIGHT:
    			g.fillRect(pos_x + width - 4, pos_y, 4, height);
    			for (Block block : activeBlocks) {
    				block.draw(g);
    				g.fillRect(block.pos_x + block.width - 4, block.pos_y, 4,
    															block.height);
    			}
    			break;
    		default: throw new IllegalArgumentException();
    	}
    }

}
