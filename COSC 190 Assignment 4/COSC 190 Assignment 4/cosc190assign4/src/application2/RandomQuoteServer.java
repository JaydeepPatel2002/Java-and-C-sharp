package application;
	
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/*
 * Random Quote Server
 * Server program for Server Requirement 1 of COSC 190 Assignment 4
 * Sends a random quote from fortune.txt to the QuoteClient in the form of an object
 * Jaydeep Patel, Liam Willis
 */
public class RandomQuoteServer extends Application {
	
	// TextArea used to display the sent quote
	private TextArea txtQuote = new TextArea();
	// A list of FortuneEntry objects containing each quote
	private ArrayList<FortuneEntry> oFortunes = new ArrayList<FortuneEntry>();
	// The FortuneEntry randomly selected to be sent to the client
	private FortuneEntry oChosenFortune;
	// The socket the server uses to communicate with the QuoteClient
	ServerSocket oServSocket;
	// The thread the server uses to run its communication functions
	Thread thServThread;
	
	// Start
	// Start method for the server's JavaFX stage
	@Override
	public void start(Stage primaryStage) {
		// Loads all quotes into the oFortunes ArrayList (see loadAllQuotes method below)
		loadAllQuotes();
		// Picks a random fortune from the ArrayList (see displayRandomFortune method below)
		oChosenFortune = displayRandomFortune();
		// Initializes the server socket, and waits for a connection (see runServer method below)
		runServer();
		// Creates a simple scene with a scroll pane which contains the quote text area
		Scene scn = new Scene(new ScrollPane(txtQuote), 450, 200);
		// Sets the primary stage to display that scene
		primaryStage.setScene(scn);
		// Changes the title of the application window
		primaryStage.setTitle("Random Message Server");
		// displays the application window
		primaryStage.show();
		
		// Sets the action of the program when the application window is closed:
		primaryStage.setOnCloseRequest(e -> {
			try
			{
				// Tries to close the server socket
				oServSocket.close();
			}
			// Catch any exceptions thrown by closing the socket:
			catch(IOException excep)
			{
				// Print the exception's stack trace
				excep.printStackTrace();
			}
		});
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	// loadAllQuotes
	// Reads all quotes from fortunes.txt, converts them to FortuneEntry objects, then adds them to the oFortunes ArrayList
	private void loadAllQuotes()
	{
		// A String of all of the quotes combined into a single string
		String quotesCombined = "";
		
		// Using a BufferedReader looking at fortunes.txt:
		try(BufferedReader oBr = new BufferedReader(new FileReader("fortunes.txt"))) 
		{
			// Collects each line from fortunes.txt and joins them into a single string, with line breaks where they are found in the document
			quotesCombined = oBr.lines().collect(Collectors.joining("\n"));		
		}
		// Catch any IOExceptions thrown by reading the text file:
		catch(IOException e)
		{
			// Print the stack trace of the exception
			e.printStackTrace();
		}
		
		// An array of quote strings, with each % sign acting as the start of a new quote
		String[] split = quotesCombined.split("%");
		
		// For each quote string:
		for(String s: split)
		{
			// Removes any quotation marks in the string
			s.replace("\"", "");
			// Removes any extra space from the string, converts it into a FortuneEntry object, then adds it to the oFortunes ArrayList
			// (see toFortune method below)
			oFortunes.add(toFortune(s.trim()));
		}
	}
	
	// toFortune
	// Converts a provided fortune string into a FortuneEntry object, with an optional author
	private FortuneEntry toFortune(String sOrig)
	{
		// The quote from the fortune
		String sQuote = "";
		// The author of the quote (if present)
		String sAuthor = "";
		// The FortuneEntry which will be returned
		FortuneEntry oNewFort;
		
		// If an author is present (indicated by the presence of three spaces and a hyphen):
		if(sOrig.indexOf("   ―") != -1)
		{
			// Gets the quote (the substring starting at position 0 and ending at the position of the last hyphen)
			sQuote = sOrig.substring(0, sOrig.lastIndexOf("―"));
			
			// Gets the quote's author (the substring starting at the position of the last hyphen)
			sAuthor = sOrig.substring(sOrig.lastIndexOf("―"));
			
			// Initializes the return fortune with the quote and author
			oNewFort = new FortuneEntry(sQuote, sAuthor);
		}
		
		// If no author is present:
		else
		{
			// Initializes the return fortune with the quote (the whole string)
			oNewFort = new FortuneEntry(sOrig);
		}
		
		// Returns the converted fortune
		return oNewFort;
	}
	
	// displayRandomFortune
	// Chooses a random fortune from the fortune ArrayList, displays it in the server text area, then returns it to be sent to the QuoteClient.
	private FortuneEntry displayRandomFortune()
	{
		// Random number generator used to get the index of the random fortune
		Random oRng = new Random();
		
		// Gets the index of the random fortune by generating a random integer between 0 and the ArrayList's size minus 1
		int iRandInd = oRng.nextInt(oFortunes.size());
		
		// Gets the quote from the random fortune
		String sChosen = oFortunes.get(iRandInd).getQuote();
		
		// Sets the text area to contain the quote
		txtQuote.setText(sChosen);
		
		// If an author is present:
		if(oFortunes.get(iRandInd).getAuthor() != null)
		{
			// Adds the author string to the text area
			txtQuote.appendText(oFortunes.get(iRandInd).getAuthor());
		}
		
		// Returns the randomly selected fortune
		return oFortunes.get(iRandInd);
	}
	
	// runServer
	// Initializes the server socket, waits for a connection, and writes the randomly chosen FortuneEntry to the client.
	private void runServer()
	{
		// Sets the run method of the server thread:
		thServThread = new Thread(() -> {
			try
			{
				// Sets the server socket to run on port 28082
				oServSocket = new ServerSocket(28082);
				
				// Opens a new socket if any client program connects to the server
				Socket oSocket = oServSocket.accept();
				
				// Gets the output stream of the client socket as an Object Output stream
				ObjectOutputStream oObjStr = new ObjectOutputStream(oSocket.getOutputStream());
				
				// While a connection is made:
				while(true)
				{
					// Writes the random fortune object to the client
					oObjStr.writeObject(oChosenFortune);
				}
		
			}
			// Catches any IOExceptions: 
			catch(IOException e)
			{
				// Prints the stack trace of the exception 
				e.printStackTrace();
			}
		});
		
		// Starts the server thread
		thServThread.start();
	}
}
