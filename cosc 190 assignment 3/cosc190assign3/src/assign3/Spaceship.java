package assign3;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/*
 * Spaceship
 * Enemy spaceships which the player must shoot down to win each level.
 * If one makes it to the player, the player loses.
 * Jaydeep Patel, Liam Willis
 */
public class Spaceship {
	// The number of images in each Spaceship's wobble animation
	public static final int NUM_IMAGES = 6;
	// The fit width and fit height of each image.
	public static final int FIT_SIZE = 30;
	// An array of images. The spaceship cycles through these to create a wobble effect
	Image[] images = new Image[NUM_IMAGES];
	// The displayed ImageView of the Spaceship
	ImageView obView;
	// The current Spaceship's animation
	Timeline obAni;
	// The index of current image the spaceship is displaying
	int nCurrentImage = 0;
	// If this spaceship has been destroyed
	boolean bIsGone = false;
	// The spaceship's horizontal speed
	private double dXSpeed;
	// The spaceship's vertical speed
	private double dYSpeed;
	// The spaceship's assigned type (see ShipType.java)
	private ShipType eType;
	// The ship's HP, 1 by default
	int iHP = 1;
	// Debug variable, makes the ships stand still
	private static boolean DEBUG_NOMOVE = false;
	
	// Constructor for the Spaceship, takes in:
	/*
	 * X coordinate
	 * Y coordinate
	 * The current pane
	 * The ShipType
	 * The stage check text
	 */
	public Spaceship(int x, int y, Pane obPane, ShipType eType, Text oStageCheck)
	{
		// Sets the NOMOVE debug field to true
		// Used for testing, should be commented out
		//DEBUG_NOMOVE = true;
	
		// For each space in the images array:
		for(int i =0;i<NUM_IMAGES;i++)
		{
			// Assigns the space to its corresponding image in the animation
			this.images[i] = new Image("file:images/ufo_"+i+".png");
		}
		// Sets the imageview to show the first image in the animation.
		obView = new ImageView(images[0]);
		// Sets the fit width and height of the imageview to the constants specified above
		obView.setFitWidth(FIT_SIZE);
		obView.setFitHeight(FIT_SIZE);
		// Sets the X and Y coordinates of the imageview
		obView.setX(x);
		obView.setY(y);
		// Sets the ship's type to the type stated in the constructor
		this.eType = eType;
		
		// Gets an ArrayList of the ship's stats from the assignStats method (see ShipStats class)
		ArrayList<Double> dStats = ShipStats.assignStats(eType);
		// Sets the horizontal speed of this ship to the one provided
		this.dXSpeed = dStats.get(0);
		// Sets the vertical speed of this ship to the one provided
		this.dYSpeed = dStats.get(1);
		// Sets the HP of this ship to the one provided (rounded to an integer)
		this.iHP = (int) Math.round(dStats.get(2));
		
		// If DEBUG_NOMOVE is enabled:
		if(DEBUG_NOMOVE)
		{
			// Sets both speeds to an effectively empty amount
			// (setting all ship speeds to 0 ends the stage, so it must be above 0)
			this.dXSpeed = 0.0000001;
			this.dYSpeed = 0.0000001;
		}
		
		if(this.dYSpeed == 0 && this.eType != ShipType.GOLEFT)
		{
			if(this.iHP < 10000)
			{
				this.obView.setImage(new Image("file:images/redshield.png"));
			}
			
			else 
			{
				this.obView.setImage(new Image("file:images/shield.png"));
			}
			
		}
		
		// Creates a new animation which will run the Drop() function every 50 milliseconds
		obAni = new Timeline(new KeyFrame(Duration.millis(50),e->Drop(obPane, oStageCheck)));
		obAni.setCycleCount(Timeline.INDEFINITE);
		// Plays the animation
		obAni.play();
	}
	
	// Drop
	// An animation which moves the spaceship. Varies depending on ShipType.
	private void Drop(Pane oPane, Text oCheck)
	{
		// If this is a Standard ship of some variety:
		if(this.eType == ShipType.STANDARD || this.eType == ShipType.STANDARDFAST || this.eType == ShipType.STANDARDFASTER || this.eType == ShipType.STANDARDINSANE)
		{
			// Moves the ship down
			obView.setY(obView.getY() + dYSpeed);
			// Cycles through the images array to make the ship look like it's wobbling
			obView.setImage(images[++nCurrentImage % this.NUM_IMAGES]);
		}
		
		// If this is a Diagonal ship:
		else if(this.eType == ShipType.DIAGONALLEFT || this.eType == ShipType.DIAGONALRIGHT || this.eType == ShipType.DIAGONALRANDOMDIR)
		{
			// If this ship hits the left or right wall:
			if(obView.getX() < 0 || obView.getX() + obView.getFitWidth() > oPane.getWidth())
			{
				// Reverses the direction the ship is traveling in (left to right, right to left)
				this.dXSpeed *= -1;
			}
			
			// Moves the spaceship down and to the left/right
			obView.setX(obView.getX() + dXSpeed);
			obView.setY(obView.getY() + dYSpeed);
			// Cycles through the images array to make the ship look like it's wobbling
			obView.setImage(images[++nCurrentImage % this.NUM_IMAGES]);
		}
		
		// If the spaceship is a moving Blocker:
		else if(this.eType == ShipType.BLOCKERSTRAFELEFT || this.eType == ShipType.BLOCKERSTRAFERIGHT || this.eType == ShipType.BLOCKERINVULNSTRAFELEFT || this.eType == ShipType.BLOCKERINVULNSTRAFERIGHT)
		{
			// If this ship hits the left or right wall:
			if(obView.getX() < 0 || obView.getX() + obView.getFitWidth() > oPane.getWidth())
			{
				// Reverses the direction the ship is traveling in (left to right, right to left)
				this.dXSpeed *= -1;
			}
			
			// Moves the ship to the left/right
			obView.setX(obView.getX() + dXSpeed);
		}
		
		// If this ship is a GOLEFT ship:
		else if(this.eType == ShipType.GOLEFT)
		{
			// Moves this ship to the left
			obView.setX(obView.getX() + dXSpeed);
			// Cycles through the images array to make the ship look like it's wobbling
			obView.setImage(images[++nCurrentImage % this.NUM_IMAGES]);
		}
		
		// If the ship has crossed the lose boundary (see battleStart in BattleRework class)
		if(obView.getY() >= 300)
		{
			// Sets the stage check text to the "BATTLE_LOSE" keyword
			oCheck.setText(BattleRework.BATTLE_LOSE);
		}
		
	}
	
	public boolean isHit(ImageView shell)
	{
		// Checks to see if the image of the provided shell has intersected with this ship's image
		return this.obView.getX()<shell.getX()+shell.getFitWidth()
		&& obView.getX()+obView.getFitWidth()>shell.getX()
		&& obView.getY()<shell.getY()+shell.getFitHeight()
		&& obView.getY()+obView.getFitHeight()>shell.getY();
		
	}
	
	// Returns this ship's ShipType
	public ShipType getType()
	{
		return this.eType;
	}
	
	// Returns this ship's Y speed
	public double getYSpeed()
	{
		return this.dYSpeed;
	}
	
}
