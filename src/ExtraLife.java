
public class ExtraLife extends Circle {

	public ExtraLife(int courtWidth, int courtHeight) {
		super(courtWidth, courtHeight);		
	}

	@Override
	public Direction hitObj(GameObj other) {
		
		if (this.willIntersect(other)) {
			if (other instanceof Player) {
				((Player) other).gainLife();
			}
			double dx = other.pos_x + other.width /2 - (pos_x + width /2);
			double dy = other.pos_y + other.height/2 - (pos_y + height/2);

			double theta = Math.atan2(dy, dx);
			double diagTheta = Math.atan2(height, width);

			if ( -diagTheta <= theta && theta <= diagTheta ) {
				return Direction.RIGHT;
			} else if ( diagTheta <= theta 
					&& theta <= Math.PI - diagTheta ) {
				return Direction.DOWN;
			} else if ( Math.PI - diagTheta <= theta 
					|| theta <= diagTheta - Math.PI ) {
				return Direction.LEFT;
			} else {
				return Direction.UP;
			}

		} else {
			return null;
		}

	}
	
}
