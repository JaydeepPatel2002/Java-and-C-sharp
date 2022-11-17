package assign3;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

/*
 * Shell (Shell2)
 * The projectiles the player fires, and the code which handles what happens when they hit a ship.
 * Jaydeep Patel, Liam Willis
 */
public class Shell2 {
	// The ImageView which displays the Shell
	private ImageView obView;
	// The animation which makes the Shell travel upwards, and governs hit detection
	private Timeline obAni;
	// A flag indicating whether or not this shell is active (if it is still able to hit ships)
	private boolean bIsActive = true;
	// The speed all shells travel upwards at, set to 6
	private static final int SHELL_SPEED = 6;
	// The sound which plays when a shell hits a ship
	private Media media = new Media("https://quicksounds.com/uploads/tracks/181694553_165965955_1398494391.mp3");
	
	// Constructor for the shell, takes:
	/*
	 * - The X coordinate the shell originates at
	 * - The Y coordinate the shell originates at
	 * - The list of all active ships
	 * - The pane which displays the ships/shells
	 * - The stage check text
	 * - The progress bar for the current stage
	 */
	public Shell2(double x,double y, ArrayList<Spaceship> oShips, Pane oPane, Text oStageCheck, ProgressBar oBar)
	{
		// Sets the ImageView to the shell image
		obView = new ImageView("file:images/shell.png");
		// Sets the X coordinate of the image to the x coordinate given minus half the image's width
		obView.setX(x - obView.getImage().getWidth()/2);
		// Sets the Y coordinate of the image to the Y coordinate given minus the image's height
		obView.setY(y-obView.getFitHeight());
		// Sets the animation of this shell to a new 15 millisecond indefinite animation using
		/*
		 * - the function up()
		 * - the list of ships
		 * - The current pane
		 * - The stage check text
		 * - The progress bar
		 */
		obAni = new Timeline(new KeyFrame(Duration.millis(15),
				e->up(oShips, oPane, oStageCheck, oBar)));
		obAni.setCycleCount(Timeline.INDEFINITE);
	}
	
	// up
	// Void function used in each shell's animation
	// Moves the shell up, checks if the shell has left the screen, and checks if the shell has hit a ship
	private void up(ArrayList<Spaceship> oShips, Pane oPane, Text oStageCheck, ProgressBar oBar)
	{
		// If the current shell is active:
		if(bIsActive)
		{
			// Moves the shell upwards at the speed set earlier
			obView.setY(obView.getY() - SHELL_SPEED);
			
			// If the shell's image leaves the top of the screen:
			if(obView.getY() < 0 - obView.getImage().getHeight())
			{
				// Deactivates this shell
				deactivate(oPane);
			}
			
			// For each ship in the ships list:
			for(int i = 0; i < oShips.size(); i++)
			{
				// If this shell has hit a ship (see isHit in Spaceship class):
				if(oShips.get(i).isHit(obView))
				{
					// Subtracts 1 HP from the ship's health
					oShips.get(i).iHP--;
					
					// Deactivates this shell
					deactivate(oPane);
					
					// If the current ship has run out of HP:
					if(oShips.get(i).iHP == 0)
					{
						// Plays the ship hit sound effect
						MediaPlayer mediaPlayer = new MediaPlayer(media);
						mediaPlayer.play();
						
						// If this ship can move down (is not a blocker or "GOLEFT" ship):
						if(oShips.get(i).getYSpeed() > 0)
						{
							// Adds a segment to the progress bar (see addSegemtn method on ProgressBar class)
							oBar.addSegment();
						}
						
						// Removes the current ship's image from the pane
						oPane.getChildren().remove(oShips.get(i).obView);
						// Stops the current ship's animation
						oShips.get(i).obAni.stop();
						// Removes the current ship from the list of active ships
						oShips.remove(i);
						
						// Gets a count of all the non-blocker ships in the active list
						int iNonBlockers = (int)oShips.stream()
								.filter(s -> s.getYSpeed() != 0)
								.count();
						
						// If there are no non-blocker ships left:
						if(iNonBlockers == 0)
						{
							// Stops the animation of each ship in the list
							oShips.stream()
								.forEach(s -> s.obAni.stop());
							// Clears the ship list
							oShips = new ArrayList<Spaceship>();
							
							// (When all non-blocker ships in a level are destroyed, the 
							// level automatically ends. This is because the player has already won
							// at this point, as ships that move down cannot end the level, so forcing
							// the player to destroy them is pointless.)
						}
						
						// If there are no ships left in the ship list:
						if(oShips.size() == 0)
						{
							// Sets the contents of the stage check text to the "BATTLE_WIN" keyword (see BattleRework class)
							oStageCheck.setText(BattleRework.BATTLE_WIN);
						}
					}
				}
			}
				
		}
	}
	
	// playAnim
	// Plays the animation associated with this shell object
	public void playAnim()
	{
		this.obAni.play();
	}
	
	// getView
	// Gets the ImageView of this Shell object
	public ImageView getView()
	{
		return this.obView;
	}
	
	// deactivate
	// Deactivates this shell object, upon leaving the screen or hitting a spaceship.
	public void deactivate(Pane oPane)
	{
		// Stops this shell's animation
		obAni.stop();
		// Removes this shell from the pane
		oPane.getChildren().remove(obView);
		// Indicates that this shell is no longer active
		bIsActive = false;
		
	}
}
