
public class Block extends Square{
	
	/** Side that the block is heading toward, i.e. the direction that the block 
	 * is going.
	 * @param startSide
	 * @return Direction
	 */
	private Direction getSide(int startSide) {		
		switch (startSide) {
			case 0:	return Direction.UP;
			case 1:	return Direction.DOWN;
			case 2:	return Direction.LEFT;
			case 3:	return Direction.RIGHT;
			default: throw new IllegalArgumentException();
		}
	}
	
	/** Initial coordinates of the block instance
	 * 
	 * @param startSide
	 * @param courtWidth
	 * @param courtHeight
	 * @return coordinates (int[x,y])
	 */
	private final int[] init_coords
					(Direction side, int courtWidth, int courtHeight) {
		switch (side) {
			case UP: {
				int init_x = (int) (Math.random()*courtWidth);
				int init_y = courtHeight;
				int[] coords = { init_x, init_y };
				return coords;
			}
			case DOWN: {
				int init_x = (int) (Math.random()*courtWidth);
				int init_y = 0;
				int[] coords = { init_x, init_y };
				return coords;
			}
			case LEFT: {
				int init_x = courtWidth;
				int init_y = (int) (Math.random()*courtHeight);
				int[] coords = { init_x, init_y };
				return coords;
			}
			case RIGHT: {
				int init_x = 0;
				int init_y = (int) (Math.random()*courtHeight);
				int[] coords = { init_x, init_y };
				return coords;
			}
			default: throw new IllegalArgumentException();
		}
	}
	
	/** Initial velocity of the block instance
	 * 
	 * @param side
	 * @return velocity (int[x,y])
	 */
	private final int[] init_vel (Direction side) {
		switch (side) {
			case UP: {
				int[] vel = { 0, -4 };
				return vel;
			}
			case DOWN: {
				int[] vel = { 0, 4 };
				return vel;
			}
			case LEFT: {
				int[] vel = { -4, 0 };
				return vel;
			}
			case RIGHT: {
				int[] vel = { 4, 0 };
				return vel;
			}
			default: throw new IllegalArgumentException();
		}
	}
	
	private Direction Side = getSide((int) (Math.random()*4));
	
	public Block(int court_width, int court_height) {
		int[] coordinates = init_coords
						(Side, court_width, court_height);
		int[] velocity = init_vel(Side);
		
		this.v_x = velocity[0];
		this.v_y = velocity[1];
		this.pos_x = coordinates[0];
		this.pos_y = coordinates[1];
		this.width = SIZE;
		this.height = SIZE;
		
		// take the width and height into account when setting the 
		// bounds for the upper left corner of the object.
		this.max_x = court_width - width;
		this.max_y = court_height - height;
	}
	
}
