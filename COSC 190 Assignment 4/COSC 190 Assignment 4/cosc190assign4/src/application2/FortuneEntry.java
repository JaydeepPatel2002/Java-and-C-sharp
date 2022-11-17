package application;

/*
 * FortuneEntry
 * Serializable object used to contain a quote and its author
 * Jaydeep Patel, Liam Willis
 */
public class FortuneEntry implements java.io.Serializable {
	// The quote, as a string
	private String sQuote;
	// The author of the quote, as a string
	private String sAuthor;
	
	// Constructor for the class which only takes a quote
	public FortuneEntry(String sQuote)
	{
		// Sets the quote of this FortuneEntry
		this.sQuote = sQuote;
	}
	
	// Constructor for the class which takes a quote and an author
	public FortuneEntry(String sQuote, String sAuthor)
	{
		// Sets the quote of this FortuneEntry
		this.sQuote = sQuote;
		// Sets the author of thsi FortuneEntry
		this.sAuthor = sAuthor;
	}
	
	// Get method, returns the quote
	public String getQuote()
	{
		return this.sQuote;
	}
	
	// Get method, returns the author
	public String getAuthor()
	{
		return this.sAuthor;
	}
}
