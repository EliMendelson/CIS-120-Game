/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * GameCourt
 * 
 * This class holds the primary game logic of how different objects 
 * interact with one another.  Take time to understand how the timer 
 * interacts with the different methods and how it repaints the GUI 
 * on every tick().
 *
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	int time = 0;
	private boolean[] player1Controls = { false, false, false, false };
	private boolean[] player2Controls = { false, false, false, false };
	private ArrayList<Block> blocks = new ArrayList<Block>();
	
	// the state of the game logic
	private Player player1;          // the Black Square, keyboard control
	private Player player2;			 // the Blue Square, keyboard control
	//private Circle snitch;          // the Golden Snitch, bounces
	//private Poison poison;          // the Poison Mushroom, doesn't move
	
	public boolean playing = false;  // whether the game is running
	private JLabel status;       // Current status text (i.e. Running...)

	// Game constants
	public static final int COURT_WIDTH = 700;
	public static final int COURT_HEIGHT = 500;
	public static final int SQUARE_VELOCITY = 4;
	// Update interval for timer in milliseconds 
	public static final int INTERVAL = 35; 
	
	public GameCourt(JLabel status){
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // The timer is an object which triggers an action periodically
        // with the given INTERVAL. One registers an ActionListener with
        // this timer, whose actionPerformed() method will be called 
        // each time the timer triggers. We define a helper method
        // called tick() that actually does everything that should
        // be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tick();
				blockGenerator();
				time += INTERVAL;
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// this key listener allows the square to move as long
		// as an arrow key is pressed, by changing the square's
		// velocity accordingly. (The tick method below actually 
		// moves the square.)
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					player1Controls[0] = true;
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					player1Controls[1] = true;
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					player1Controls[2] = true;
				} else if (e.getKeyCode() == KeyEvent.VK_W) {
					player1Controls[3] = true;
				}
			}
			public void keyReleased(KeyEvent e){
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D
					|| keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_W)
					for (int i = 0; i < player1Controls.length; i++) {
						player1Controls[i] = false;
					}
			}
		});
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player2Controls[0] = true;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player2Controls[1] = true;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player2Controls[2] = true;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player2Controls[3] = true;
				}
			}
			public void keyReleased(KeyEvent e){
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT
					|| keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP)
					for (int i = 0; i < player2Controls.length; i++) {
						player2Controls[i] = false;
					}
			}
		});
		
		this.status = status;
	}

	/** (Re-)set the state of the game to its initial state.
	 */
	public void reset() {

		player1 = new Player(COURT_WIDTH/3, COURT_HEIGHT/2,
								COURT_WIDTH, COURT_HEIGHT);
		player2 = new Player((COURT_WIDTH*2)/3, COURT_HEIGHT/2,
								COURT_WIDTH, COURT_HEIGHT);
		//poison = new Poison(COURT_WIDTH, COURT_HEIGHT);
		//snitch = new Circle(COURT_WIDTH, COURT_HEIGHT);

		playing = true;
		time = 0;
		blocks.clear();
		status.setText("Running...");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	private void playerVelControl() {
		if (player1Controls[0] == true) player1.v_x = -SQUARE_VELOCITY;
		else if (player1Controls[1] == true) player1.v_x = SQUARE_VELOCITY;
		else player1.v_x = 0;
		if (player1Controls[2] == true) player1.v_y = SQUARE_VELOCITY;
		else if (player1Controls[3] == true) player1.v_y = -SQUARE_VELOCITY;
		else player1.v_y = 0;
		
		if (player2Controls[0] == true) player2.v_x = -SQUARE_VELOCITY;
		else if (player2Controls[1] == true) player2.v_x = SQUARE_VELOCITY;
		else player2.v_x = 0;
		if (player2Controls[2] == true) player2.v_y = SQUARE_VELOCITY;
		else if (player2Controls[3] == true) player2.v_y = -SQUARE_VELOCITY;
		else player2.v_y = 0;
	}
	
    /**
     * This method is called every time the timer defined
     * in the constructor triggers.
     */
	void tick(){
		if (playing) {
			
			playerVelControl();
			
			// advance the square and snitch in their
			// current direction.
			player1.move();
			player2.move();
			//snitch.move();

			Iterator<Block> iter = blocks.iterator();
			
			while (iter.hasNext()) {
				Block block = iter.next();
				block.move();
				if (block.hitWall() != null) {
					iter.remove();
				}
			}
			
			// make the snitch bounce off walls...
			//snitch.bounce(snitch.hitWall());
			// ...and the mushroom
			//snitch.bounce(snitch.hitObj(poison));
		
			// check for the game end conditions
			/*if (player1.intersects(poison)) { 
				playing = false;
				status.setText("Player 1 loses!");

			} else*/ /*if (player1.intersects(snitch)) {
				playing = false;
				status.setText("Player 1 wins!");
			}
			
			/*if (player2.intersects(poison)) { 
				playing = false;
				status.setText("Player 2 loses!");

			} else*/ /*if (player2.intersects(snitch)) {
				playing = false;
				status.setText("Player 2 wins!");
			}*/
			
			// update the display
			repaint();
		} 
	}

	private void blockGenerator() {
		if (time % 3500 == 0) {
			time = 0;
			Block block1 = new Block(COURT_WIDTH, COURT_HEIGHT);
			Block block2 = new Block(COURT_WIDTH, COURT_HEIGHT);
			blocks.add(block1);
			blocks.add(block2);
		}
	}
	
	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		player1.draw(g, Color.RED);
		player2.draw(g, Color.BLUE);
		for (Block block : blocks) {
			block.draw(g);
		}
		//poison.draw(g);
		//snitch.draw(g);
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(COURT_WIDTH,COURT_HEIGHT);
	}
}
