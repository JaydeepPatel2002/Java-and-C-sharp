package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/*
 * WriteQuoteServer
 * Server for Serve Requirement 2 for COSC 190 Assignment 4
 * Receives a FortuneEntry object from QuoteClient, then writes the contents of the fortune to fortunes.txt
 * Jaydeep Patel, Liam Willis
 */
public class WriteQuoteServer extends Application {
	// Text area which displays the current quote
	private TextArea txtQuote = new TextArea();
	// Server socket which connects to QuoteClient
	ServerSocket oServSocket;
	// Thread which handles server communications
	Thread thServThread;

	@Override
	// start
	// Start method for the server window
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// Initializes the server and waits for connections (see runServer method below)
		runServer();
		// Creates a new scene with a scrollpane containing the quote text area
		Scene scn = new Scene(new ScrollPane(txtQuote), 450, 200);
		// Sets the primary stage to display the new scene
		primaryStage.setScene(scn);
		// Sets the title of the server window
		primaryStage.setTitle("Write Quote Server");
		// Displays the server window
		primaryStage.show();
		
		// Sets the on close event for the server window:
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
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	// runServer
	// Initializes the server, waits for connections, receives a FortuneEntry object, then writes that entry to fortunes.txt 
	private void runServer()
	{
		// Sets the run method of the server thread:
		thServThread = new Thread(() -> {
			try {
				// Initializes the server socket on port 28083
				oServSocket = new ServerSocket(28083);
				
				// Waits for the QuoteClient to connect
				Socket oSocket = oServSocket.accept();
				
				// Gets the output stream from the QuoteClient connection
				ObjectInputStream oObjStreamIn = new ObjectInputStream(oSocket.getInputStream());
				
				// Reads a FortuneEntry object from the quote client
				FortuneEntry oToWrite = (FortuneEntry)oObjStreamIn.readObject();
				
				// Writes the received quote to fortunes.txt (see writeQuote method below)
				writeQuote(oToWrite);
			
			// Catch any exceptions thrown by receiving the object or writing the fortune to the file:
			} catch (IOException e) {
				// Prints the stack trace of the exception
				e.printStackTrace();
			// Catch any exceptions thrown by casting the received object to a FortuneEntry
			} catch (ClassNotFoundException e) {
				// Prints the stack trace of the exception
				e.printStackTrace();
			}
		});
		
		// Starts the server thread
		thServThread.start();
	}
	
	// writeQuote
	// Writes a provided FortuneEntry to fortunes.txt
	private void writeQuote(FortuneEntry oToWrite) throws IOException
	{
		// Opens the fortunes.txt file
		File oTxt = new File("fortunes.txt");
		// Printwriter used to write the fortune to the text file, set to append mode
		PrintWriter oWriter = new PrintWriter(new FileWriter(oTxt, true));
		
		// Writes a percent sign to mark a new quote, followed by a line break
		oWriter.write("%\n");
		// Writes the quote to the file, followed by a line break
		oWriter.write(oToWrite.getQuote() + "\n");
		
		// If the fortune entry has an author:
		if(oToWrite.getAuthor() != "")
		{
			// Writes a hyphen to the file, followed by the quote's author, and a line break
			oWriter.write("   ―" + oToWrite.getAuthor() + "\n");
		}
		
		// Closes the printwriter
		oWriter.close();
		
	}

}
