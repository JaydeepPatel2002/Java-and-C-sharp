package application;
	
import java.io.BufferedReader;
import java.io.DataInputStream;
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
 * ScrollServer
 * Server for Server Requirement 3 of COSC 190 Assignment 4
 * Receives an integer from the ScrollClient, then returns a FortuneEntry object based on that integer
 * Jaydeep Patel, Liam Willis
 */
public class ScrollServer extends Application {
	
	// Text area which displays outgoing quotes
	private TextArea txtQuote = new TextArea();
	// ArrayList which contains FortuneEntries for each fortune in fortune.txt
	private ArrayList<FortuneEntry> oFortunes = new ArrayList<FortuneEntry>();
	// The FortuneEntry to be sent to the client
	private FortuneEntry oChosenFortune;
	// The server socket for the ScrollServer
	ServerSocket oServSocket;
	// The thread which handles server communications
	Thread thServThread;
	// The integer received from the client
	int iRecievedIndex = 0;
	
	// start
	// Start method for the server window
	@Override
	public void start(Stage primaryStage) {
		// Loads all quotes from fortunes.txt (see loadAllQuotes method below)
		loadAllQuotes();
		// Initializes the server and waits for connections (see runServer method below)
		runServer();
		// Creates a new scene containing a scrollpane with the quote text area
		Scene scn = new Scene(new ScrollPane(txtQuote), 450, 200);
		// Sets the stage to use the new scene
		primaryStage.setScene(scn);
		// Sets the title of the server window
		primaryStage.setTitle("Scroll Bar Message Server");
		// Shows the server window
		primaryStage.show();
		
		// Sets the on close event of the window:
		primaryStage.setOnCloseRequest(e -> {
			try
			{
				// Closes the server socket
				oServSocket.close();
			}
			// Catch any exceptions thrown by closing the server socket:
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
	
	// runServer
	// Initializes the server socket, and communicates with the ScrollClient
	private void runServer()
	{
		// Sets the run method of the server thread:
		thServThread = new Thread(() -> {
			try
			{
				// Opens the server socket on port 28084
				oServSocket = new ServerSocket(28084);
				
				// Waits for the ScrollClient to connect to the server
				Socket oSocket = oServSocket.accept();
				
				// Gets the output stream from the client connection socket
				ObjectOutputStream oObjStrOut = new ObjectOutputStream(oSocket.getOutputStream());
				
				// Gets the input stream from the client connection socket
				DataInputStream oDataStrIn = new DataInputStream(oSocket.getInputStream());
	
				while(true)
				{
					// Reads the scroll bar value from the client (see ScrollClient class)
					iRecievedIndex = oDataStrIn.readInt();
					
					// Gets the decimal equivalent of the received integer by dividing it by 100
					// (The value received from ScrollClient is equivalent to what percentage the current fortune is through the fortune list)
					double dChosenPercent = iRecievedIndex / 100.0;
					// Gets the index of the chosen entry by multiplying the percentage decimal by the size of the list minus 1
					int iChosenInd = (int)(dChosenPercent * (oFortunes.size() - 1));
					// Gets the fortune from the list at the chosen index
					oChosenFortune = oFortunes.get(iChosenInd);
					
					// Runbs when the program is available:
					Platform.runLater(() -> {
						try {
							// Adds the chosen index to the server text area
							txtQuote.appendText(String.valueOf(iChosenInd));
							// Adds the quote of the chosen fortune to the server text area
							txtQuote.appendText(oChosenFortune.getQuote());
							// Sends the chosen fortune to the client
							oObjStrOut.writeObject(oChosenFortune);
						// Catch any exceptions thrown by writing the fortune to the client:
						} catch (IOException e) {
							// Prints the exception's stack trace
							e.printStackTrace();
						}
					});
					
				}
		
			}
			// Catch any exceptions thrown by receiving the integer value from the client:
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
