
public class ExtraLife extends Circle {

	private final int[] init_coords(int courtWidth, int courtHeight) {
		int x = (int) (Math.random()*courtWidth);
		int y = (int) (Math.random()*courtHeight);
		int[] coords = { x, y };
		return coords;
	}
	
	private final int[] init_vel() {
		int x = (int) (Math.random()*8) - 4;
		int y = (int) (Math.random()*8) - 4;
		int[] coords = { x, y };
		return coords;
	}
	
	public ExtraLife(int courtWidth, int courtHeight) {
		int[] coordinates = init_coords(courtWidth, courtHeight);
		int[] velocity = init_vel();
		
		this.v_x = velocity[0];
		this.v_y = velocity[1];
		this.pos_x = coordinates[0];
		this.pos_y = coordinates[1];
		this.width = SIZE;
		this.height = SIZE;
		
		// take the width and height into account when setting the 
		// bounds for the upper left corner of the object.
		this.max_x = courtWidth - width;
		this.max_y = courtHeight - height;
	}
	
	
}
