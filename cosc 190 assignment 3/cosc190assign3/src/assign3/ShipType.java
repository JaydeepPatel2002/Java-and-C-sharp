package assign3;

/*
 * ShipType
 * Enumeration used to denote the type, HP, and behaviours of a ship.
 * Jaydeep Patel, Liam Willis
 */
public enum ShipType {
	// Moves down slowly
	STANDARD,
	// Moves down at a fast rate
	STANDARDFAST,
	// Moves down at a faster rate
	STANDARDFASTER,
	// Moves down at an insanely fast rate
	STANDARDINSANE,
	// Moves to the left and down (slightly slower than standard)
	DIAGONALLEFT,
	// Moves to the right and down (slightly slower than standard)
	DIAGONALRIGHT,
	// Moves either to the left or the right and down (slightly slower than standard)
	DIAGONALRANDOMDIR,
	// Doesn't move, blocks 2 shots
	BLOCKER,
	// Doesn't move, blocks 10000 shots
	BLOCKERINVULN,
	// Moves left, blocks 2 shots
	BLOCKERSTRAFELEFT,
	// Moves right, blocks 2 shots
	BLOCKERSTRAFERIGHT,
	// Moves left, blocks 10000 shots
	BLOCKERINVULNSTRAFELEFT,
	// Moves right, blocks 10000 shots
	BLOCKERINVULNSTRAFERIGHT,
	// Moves to the left, blocks 190 shots (used in level 11)
	GOLEFT;
}
