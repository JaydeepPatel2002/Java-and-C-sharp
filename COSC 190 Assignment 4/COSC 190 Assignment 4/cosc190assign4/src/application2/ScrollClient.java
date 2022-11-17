package application;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * ScrollClient
 * Client app for Client Requirement 3 in COSC 190 Assignment 4
 * Allows a user to scroll through the quotes in fortunes.txt by requesting quotes from the ScrollServer
 * Jaydeep Patel, Liam Willis
 */
public class ScrollClient extends Application
{
	// Text area for the quote
	private TextArea txtQuote = new TextArea();
	// Text area for the author of the quote (if available)
	private TextArea txtAuthor = new TextArea();
	// Input stream used to receive FortuneEntry objects from the ScrollServer
	private ObjectInputStream oObjStr;
	// Data output stream used to send fortune indexes back to the ScrollServer
	private DataOutputStream oDataStrOut;
	// The fortune received from the ScrollServer
	FortuneEntry oResFortune;
	
	// start
	// Start method for the client window
	public void start(Stage primaryStage)   {
	    
		// Initializes the connection with the ScrollServer (see initServer method below)
		try {
			initServer();
		// Catch any exceptions thrown by the server initialization:
		} catch (Exception e2) {
			// Prints the stack trace of the exception
			e2.printStackTrace();
		}
		
		// Scrollbar used to scroll through quotes
	    ScrollBar oScroll = new ScrollBar();
	    // Sets the scrollbar as a vertical scrollbar
	    oScroll.setOrientation(Orientation.VERTICAL);
	   
	    // HBox which contains the quote text area and label
	    HBox oQuoteBox = new HBox();
	    // Sets the padding of the quote box to be 10px on all sides
	    oQuoteBox.setPadding(new Insets(10,10,10,10));
	    
	    // Label for the quote text field
	    Label lblQuote = new Label("Quote: ");
	    // Adds the quote label and text field to the quote box
	    oQuoteBox.getChildren().addAll(lblQuote, txtQuote);
	    
	    // HBox used to contain the quote text area and label
	    HBox oAuthorBox = new HBox();
	    // Sets the padding of the author box to be 10px on all sides
	    oAuthorBox.setPadding(new Insets(10,10,10,10));
	    
	    // Label for the author text box
	    Label lblAuthor = new Label("Author: ");
	    // Adds the author label and text box to the author box
	    oAuthorBox.getChildren().addAll(lblAuthor, txtAuthor);
	    
	    // VBox which contains the quote and author boxes
	    VBox oSubPane = new VBox();
	    // Adds the quote and author boxes to the VBox
	    oSubPane.getChildren().addAll(oQuoteBox, oAuthorBox);
	   
	    // Main border pane which holds the VBox and scroll bar
	    BorderPane oMainPane = new BorderPane();
	    // Sets the center of the main pane as the VBox
	    oMainPane.setCenter(oSubPane);
	    
	    // Sets the right side of the main pane as the scroll bar
	    oMainPane.setRight(oScroll);
	   
	    // Creates an event listener for the scroll bar's value property (changes when the scrollbar is moved):
	    oScroll.valueProperty().addListener(e -> {
	    	try {
	    		// Reads a fortune from the server with the scrollbar's value cast to an integer (see readQuote method below)
				readQuote((int)(oScroll.getValue()));
			// Catch any exceptions thrown by reading the fortune:
			} catch (Exception e1) {
				// Prints the stack trace of the exception
				e1.printStackTrace();
			}
	    });
	      
	    // Creates a new scene with the main border pane
	    Scene scn = new Scene(oMainPane, 450, 170);
	    // Sets the primary stage to use the new scene
	    primaryStage.setScene(scn); 
	    // Sets the title of the client window
	    primaryStage.setTitle("Scroll Client");
	    // Shows the client window
	    primaryStage.show();
	  }
	
	// initServer
	// Initializes the connection between the ScrollClient and the ScrollServer
	public void initServer() throws Exception
	{
		// Opens a new socket on port 28084
		Socket oClientSocket = new Socket("localhost", 28084);
		// Gets the input stream from the newly opened socket
		oObjStr = new ObjectInputStream(oClientSocket.getInputStream());
		// Gets the output stream from the newly opened socket
		oDataStrOut = new DataOutputStream(oClientSocket.getOutputStream());
	}
	
	// readQuote
	// Reads a quote from the ScrollServer based on the scrollbar's value (provided as a parameter)
	private void readQuote(int toRead)
	{
		try {
			// Writes the integer value of the scroll bar to the output stream
			oDataStrOut.writeInt(toRead);
			Platform.runLater(() -> {
				try {
					// Reads a FortuneEntry object provided by the server based on the integer sent
					oResFortune = (FortuneEntry)oObjStr.readObject();
				// Catch any exceptions thrown by casting the received object into a FortuneEntry, and prints their stack trace
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// Catch any exceptions thrown by reading the FortuneEntry from the server, and prints their stack trace
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Sets the contents of the quote text area to the received fortune's quote
				txtQuote.setText(oResFortune.getQuote());
				
				// If the FortuneEntry has an author:
				if(oResFortune.getAuthor() != null)
				{
					// Sets the text of the author text field to the received fortune's author
					txtAuthor.setText(oResFortune.getAuthor());
				}
			});
		
		// Catch any exceptions thrown by writing the scrollbar value to the server:
		} catch (IOException e) {
			// Prints the exception's stack trace
			e.printStackTrace();
		} 
	}
	  
	  public static void main(String[] args) {
	    launch(args);
	  }
	}