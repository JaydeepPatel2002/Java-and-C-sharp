package assign3;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * ProgressBar
 * Class used to make a dynamic progress bar for each battle stage
 * Jaydeep Patel, Liam Willis
 */
public class ProgressBar {
	
	// The width of the bar
	private int m_iWidth;
	// The height of the bar
	private int m_iHeight;
	// The X coordinate the bar is placed at
	private int m_iXCoord;
	// The Y coordinate the bar is placed at
	private int m_iYCoord;
	// The rectangle which represents the bar
	Rectangle m_oBar;
	// The length of each segment of the progress bar, calculated based on the number of remaining ships
	private int m_iSegment;
	// The width of pane the bar has to fill
	private double m_dPaneWidth;
	
	// The constructor for the bar. Takes in:
	/*
	 * - Width
	 * - Height
	 * - X coordinate
	 * - Y coordinate
	 * - The list of active Spaceships
	 * - The width of pane left to fill
	 */
	public ProgressBar(int iWidth, int iHeight, int iX, int iY, ArrayList<Spaceship> oShips, double dPWidth)
	{
		// Sets each member field (except segment length)
		this.m_iWidth = iWidth;
		this.m_iHeight = iHeight;
		this.m_iXCoord = iX;
		this.m_iYCoord = iY;
		this.m_dPaneWidth = dPWidth;
		
		// Creates a new Rectangle object
		this.m_oBar = new Rectangle(0,0,0,0);
		// Places it at the coordinates given
		this.m_oBar.setX(this.m_iXCoord);
		this.m_oBar.setY(this.m_iYCoord);
		// Sets the width and height of the bar to the dimensions given
		this.m_oBar.setWidth(this.m_iWidth);
		this.m_oBar.setHeight(this.m_iHeight);
		// Sets the fill colour to green
		this.m_oBar.setFill(Color.GREEN);
		
		// Filters the ship list to only contain non-blockers (anything that doesn't move downwards), and counts how many ships remain
		int iNonBlockers = (int)oShips.stream()
				.filter(s -> s.getYSpeed() != 0)
				.count();
		
		// Sets the segment width to the available pane width divided by the number of hostile ships
		this.m_iSegment = (int)this.m_dPaneWidth / iNonBlockers;
	}
	
	// addSegment
	// Called when a ship is destroyed, adds a segment to the width of the progress bar
	public void addSegment()
	{
		// Adds a segment width to the bar's width
		this.m_oBar.setWidth(this.m_oBar.getWidth() + this.m_iSegment);
	}

}
