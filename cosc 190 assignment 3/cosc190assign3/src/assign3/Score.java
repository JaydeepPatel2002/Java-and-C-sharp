package assign3;

/*
 * Score
 * Class used to store and organize high scores
 * Jaydeep Patel, Liam Willis
 */
public class Score
{
	// The name of who achieved the high score
	private String m_sName;
	// The score they got
	private int m_iScore;
	
	// Constructor for the Score object, takes in and sets the name and score
	public Score(String name, int score)
	{
		this.m_sName = name;
		this.m_iScore = score;
	}
	
	// Get method for the name
	public String getName()
	{
		return this.m_sName;
	}
	
	// Get method for the score
	public int getScore()
	{
		return this.m_iScore;
	}
	
	// Set method for the score
	public void setScore(int newScore)
	{
		this.m_iScore = newScore;
	}
	
	// Set method for the name
	public void setName(String newName)
	{
		this.m_sName = newName;
	}
	
	// toString
	// Returns a string in format "[name],[score]" to be written to Scores.txt
	@Override
	public String toString()
	{
		return this.m_sName + "," + this.m_iScore;
	}

}
