package assign3;

import java.util.ArrayList;
import java.util.Random;

/*
 * ShipStats
 * Stores the speed and HP values for each ShipType, and "assigns" them by return an ArrayList of Doubles.
 * Jaydeep Patel, Liam Willis
 */
public class ShipStats {
	// The X and Y speeds, and HP of STANDARD ships
	private static final double STANDARD_X = 1.0;
	private static final double STANDARD_Y = 1.0;
	private static final double STANDARD_HP = 1.0;
	// The Y speed of STANDARDFAST ships
	private static final double STANDARD_F_Y = 3.0;
	// The Y speed of STANDARDFASTER ships
	private static final double STANDARD_F2_Y = 5.0;
	// The Y speed of STANDARDINSANE ships
	private static final double STANDARD_I_Y = 7.0;
	
	// The X and Y speeds, and HP of DIAGONALLEFT, DIAGONALRIGHT, and DIAGONALRANDOMDIR ships
	private static final double DIAG_X = 3.0;
	private static final double DIAG_Y = 0.75;
	private static final double DIAG_HP = 1.0;
	
	// The X and Y speeds, and Hp of BLOCKER ships
	private static final double BLOCK_X = 0.0;
	private static final double BLOCK_Y = 0.0;
	private static final double BLOCK_HP = 2.0;
	// The HP of BLOCKERINVULN ships
	private static final double BLOCK_INV_HP = 10000.0;
	// The X speed of BLOCKERSTRAFELEFT, BLOCKERSTRAFERIGHT, BLOCKERINVLUNSTRAFELEFT, and BLOCKERINVULNSTRAFERIGHT ships
	private static final double BLOCK_STR_X = 6.0;
	
	// The X and Y speed, and HP of GOLEFT ships
	private static final double GOLEFT_X = -5.0;
	private static final double GOLEFT_Y = 0.0;
	private static final double GOLEFT_HP = 190.0;
	
	// assignStats
	// Returns an ArrayList of Doubles based on the ShipType requested for the ship's speeds and HP
	// NOTE: HP is returned as a double, but casted into an int. Only use whole numbers for HP values.
	public static ArrayList<Double> assignStats(ShipType eType)
	{
		// X Speed, Y Speed, HP
		
		// The ArrayList containing each set of values
		ArrayList<Double> dStats = new ArrayList<Double>();
		
		// STANDARD values
		if(eType == ShipType.STANDARD)
		{
			dStats.add(STANDARD_X);
			dStats.add(STANDARD_Y);
			dStats.add(STANDARD_HP);
		}
		
		// STANDARDFAST values
		else if(eType == ShipType.STANDARDFAST)
		{
			dStats.add(STANDARD_X);
			dStats.add(STANDARD_F_Y);
			dStats.add(STANDARD_HP);
		}
		
		// STANDARDFASTER values
		else if(eType == ShipType.STANDARDFASTER)
		{
			dStats.add(STANDARD_X);
			dStats.add(STANDARD_F2_Y);
			dStats.add(STANDARD_HP);
		}
		
		// STANDARDINSANE values
		else if(eType == ShipType.STANDARDINSANE)
		{
			dStats.add(STANDARD_X);
			dStats.add(STANDARD_I_Y);
			dStats.add(STANDARD_HP);
		}
		
		// DIAGONALLEFT values
		else if(eType == ShipType.DIAGONALLEFT)
		{
			dStats.add(-1 * DIAG_X);
			dStats.add(DIAG_Y);
			dStats.add(DIAG_HP);
		}
		
		// DIAGONALRIGHT values
		else if(eType == ShipType.DIAGONALRIGHT)
		{
			dStats.add(DIAG_X);
			dStats.add(DIAG_Y);
			dStats.add(DIAG_HP);
		}
		
		// DIAGONALRANDOMDIR values
		else if(eType == ShipType.DIAGONALRANDOMDIR)
		{
			// Random number generator which picks a direction
			Random oRng = new Random();
			// A random number, either 0 and 1
			int iDir = oRng.nextInt(2);
			
			switch(iDir)
			{
				// If the number is 0, the ship will travel to the left
				case 0: // Left
					dStats.add(-1 * DIAG_X);
					dStats.add(DIAG_Y);
					break;
					
				// If the number is 1, the ship will travel to the right
				case 1: // Right
					dStats.add(DIAG_X);
					dStats.add(DIAG_Y);
					break;
			}
			
			dStats.add(DIAG_HP);
		}
		
		// BLOCKER values
		else if(eType == ShipType.BLOCKER)
		{
			dStats.add(BLOCK_X);
			dStats.add(BLOCK_Y);
			dStats.add(BLOCK_HP);
		}
		
		// BLOCKERINVLUN values
		else if(eType == ShipType.BLOCKERINVULN)
		{
			dStats.add(BLOCK_X);
			dStats.add(BLOCK_Y);
			dStats.add(BLOCK_INV_HP);
		}
		
		// BLOCKERSTRAFELEFT values
		else if(eType == ShipType.BLOCKERSTRAFELEFT)
		{
			dStats.add(-1 * BLOCK_STR_X);
			dStats.add(BLOCK_Y);
			dStats.add(BLOCK_HP);
		}
		
		// BLOCKERSTRAFERIGHT values
		else if(eType == ShipType.BLOCKERSTRAFERIGHT)
		{
			dStats.add(BLOCK_STR_X);
			dStats.add(BLOCK_Y);
			dStats.add(BLOCK_HP);
		}
		
		// BLOCKERINVULNSTRAFELEFT values
		else if(eType == ShipType.BLOCKERINVULNSTRAFELEFT)
		{
			dStats.add(-1 * BLOCK_STR_X);
			dStats.add(BLOCK_Y);
			dStats.add(BLOCK_INV_HP);
		}
		
		// BLOCKERINVULNSTRAFERIGHT values
		else if(eType == ShipType.BLOCKERINVULNSTRAFERIGHT)
		{
			dStats.add(BLOCK_STR_X);
			dStats.add(BLOCK_Y);
			dStats.add(BLOCK_INV_HP);
		}
		
		// GOLEFT values
		else if(eType == ShipType.GOLEFT)
		{
			dStats.add(GOLEFT_X);
			dStats.add(GOLEFT_Y);
			dStats.add(GOLEFT_HP);
		}
		
		// Returns the assigned stats
		return dStats;
	}
}
