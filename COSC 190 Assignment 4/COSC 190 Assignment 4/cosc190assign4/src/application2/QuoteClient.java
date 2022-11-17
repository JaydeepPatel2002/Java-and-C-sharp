package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * QuoteClient
 * Client program for Client Requirements 1 and 2 in COSC 190 Assignment 4
 * Can contact the RandomQuoteServer to obtain a random quote
 * Can also contact the WriteQuoteServer to write a user-defined quote to fortune.txt
 * Jaydeep Patel, Liam Willis
 */
public class QuoteClient extends Application {
	
	// TextArea which contains the random quote
	private TextArea txtQuote = new TextArea();
	// TextArea which returns the random quote's author, if applicable
	private TextArea txtAuthor = new TextArea();
	// Labels for the quote and author text areas
	private Label lblQuote = new Label("Quote: ");
	private Label lblAuthor = new Label("Author: ");
	// The input stream currently in use by the client
	private ObjectInputStream oObjStrFrom;
	// The output stream currently in use by the client
	private ObjectOutputStream oObjStrTo;
	// The socket used by the client to connect to the RandomQuoteServer
	private Socket oClientSocketRead;
	// The socket used by the client to connect to the WriteQuoteServer
	private Socket oClientSocketWrite;

	// start
	// Start method for the client, displays the main client window
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// HBox containing the quote text area and label
		HBox oQuoteBox = new HBox();
		// Sets the padding of the quote box to 10px on all sides
		oQuoteBox.setPadding(new Insets(10,10,10,10));
		// Adds the quote area and its label to the quote box
		oQuoteBox.getChildren().add(lblQuote);
		oQuoteBox.getChildren().add(txtQuote);
		// Sets the height and width of the quote text area
		txtQuote.setMaxHeight(50);
		txtQuote.setMaxWidth(340);
		
		// HBox containing the author text area and label
		HBox oAuthBox = new HBox();
		// Sets the padding of the author box to 10px on all sides
		oAuthBox.setPadding(new Insets(10,10,10,10));
		// Adds the author area and its label to the quote box
		oAuthBox.getChildren().add(lblAuthor);
		oAuthBox.getChildren().add(txtAuthor);
		// Sets the height and width of the author text area
		txtAuthor.setMaxHeight(25);
		txtAuthor.setMaxWidth(320);
		// Sets the author text area and label to be hidden initially
		lblAuthor.setVisible(false);
		txtAuthor.setVisible(false);
		
		// VBox which holds both text area boxes (quote and author)
		VBox oCenter = new VBox();
		// Adds both text area HBoxes to the center box
		oCenter.getChildren().add(oQuoteBox);
		oCenter.getChildren().add(oAuthBox);
		
		// BorderPane which holds the window's contents
		BorderPane oPane = new BorderPane();
		// Sets the center VBox to the center of the borderpane
		oPane.setCenter(oCenter);
		
		// Menu object which contains the Exit option, labeled "File"
		Menu oFile = new Menu("File");
		// Menu item which will exit the program if clicked
		MenuItem oExit = new MenuItem("Exit");
		// Adds the exit item to the file menu
		oFile.getItems().add(oExit);
		// Sets the on click action of the exit item:
		oExit.setOnAction(e -> {
			// Closes the application window
			primaryStage.close();
			// Exits the system with an error code of 0 (no errors)
			System.exit(0);
		});
		
		// Menu object which contains the Read Fortune and Write Fortune options, labeled "View"
		Menu oView = new Menu("View");
		// Menu item which will read a random fortune from RandomQuoteServer if clicked
		MenuItem oRead = new MenuItem("Read Fortune");
		// Menu item which will open a prompt window for a new fortune if clicked
		MenuItem oWrite = new MenuItem("Write Fortune");
		// Adds both menu items to the View menu
		oView.getItems().addAll(oRead, oWrite);
		// Sets the on click action of the read item:
		oRead.setOnAction(e -> {
			try {
				// Read the random quote from the server
				readQuote();
			// Catch any exceptions:
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// Prints the stack trace of the exception
				e1.printStackTrace();
			}
		});
		
		// Sets the on click action of the Write item to open the new quote window (see displayWriteWindow method below)
		oWrite.setOnAction(e -> displayWriteWindow());
		
		// MenuBar which will contain each of the Menu objects
		MenuBar oMenu = new MenuBar();
		// Adds the File and View menus to the MenuBar
		oMenu.getMenus().addAll(oFile, oView);
		// Sets the MenuBar at the top of the display pane
		oPane.setTop(oMenu);
		
		// Creates a new scene with the border pane
		Scene scn = new Scene(oPane, 400, 250);
		// Sets the primary stage to use the new scene
		primaryStage.setScene(scn);
		// Sets the title of the client window
		primaryStage.setTitle("Quote Client");
		// Displays the client window
		primaryStage.show();
		
	}	
	
	// readQuote
	// Reads a random quote provided by the RandomQuoteServer.
	public void readQuote() throws Exception
	{
		// If the client read socket has not been initialized yet:
		if(oClientSocketRead == null)
		{
			// Opens the read socket on port 28082
			oClientSocketRead = new Socket("localhost", 28082);
			// Grabs the read socket's input stream
			oObjStrFrom = new ObjectInputStream(oClientSocketRead.getInputStream());
		}
		
		try {
			// Reads the random fortune from the server as a FortuneEntry object
			FortuneEntry oFortune = (FortuneEntry)oObjStrFrom.readObject();
			
			// Sets the text of the quote text area to the quote from the random fortune
			txtQuote.setText(oFortune.getQuote());
			
			// If the FortuneEntry's author isn't null (an author was included):
			if(oFortune.getAuthor() != null)
			{
				// Sets the text of the author text area to the provided author name
				txtAuthor.setText(oFortune.getAuthor());
				// Makes the author label
				lblAuthor.setVisible(true);
				txtAuthor.setVisible(true);
			}
		// Catch any exceptions thrown by casting the object received from the input stream to a FortuneEntry:
		} catch (ClassNotFoundException e) {
			// Print the stack trace of the exception
			e.printStackTrace();
		// Catch any exceptions thrown by reading the object from the server:
		} catch (IOException e) {
			// Prints the stack trace of the exception
			e.printStackTrace();
		}
		
	}
	
	// displayWriteWindow
	// Displays a window with prompts asking the user for a new quote
	public void displayWriteWindow()
	{
		// Stage which will display the new window
		Stage oSecondaryStage = new Stage();
		// VBox which will hold the window's contents
		VBox oButtonBox = new VBox();
		
		// HBox which holds the text field for the quote and its label
		HBox oQuoteBox = new HBox();
		// Sets the padding of the quote box to 10px on all sides
		oQuoteBox.setPadding(new Insets(10,10,10,10));
		
		// Text field which contains the user's quote
		TextField txtQuoteField = new TextField();
		// Label for the quote textfield asking the user to enter a quote
		Label lblQuoteNew = new Label("Enter a quote: ");
		// Adds both the quote field and its label to the quote box
		oQuoteBox.getChildren().addAll(lblQuoteNew, txtQuoteField);
		
		// HBox which holds the text field for the author and its label
		HBox oAuthBox = new HBox();
		// Sets the padding of the author box to 10px on all sides
		oAuthBox.setPadding(new Insets(10,10,10,10));
		
		// Text field which contains the author of user's quote (if applicable)
		TextField txtAuthorField = new TextField();
		// Label for the author textfield asking the user to enter the author's name
		Label lblAuthorNew = new Label("(Optional) Enter an author: ");
		// Adds both the author field and its label to the author box
		oAuthBox.getChildren().addAll(lblAuthorNew, txtAuthorField);
		
		// Submit button which will write the user's quote to fortunes.txt when clicked
		Button btnSubmit = new Button("Write Quote");
		// Sets the padding of the submit button to be 10px on all sides
		btnSubmit.setPadding(new Insets(10,10,10,10));
		// Sets the on click action of the submit button:
		btnSubmit.setOnAction(e -> {
			try {
				// Writes a FortuneEntry object to the WriteQuoteServer with the contents of the quote and author text box
				writeFortune(txtQuoteField.getText(), txtAuthorField.getText());
				// Closes the write window
				oSecondaryStage.close();
			// Catches any exceptions thrown by the write method, and prints out their stack trace
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}		});
		
		// Adds the quote box, the author box, and the submit button to the main VBox
		oButtonBox.getChildren().addAll(oQuoteBox, oAuthBox, btnSubmit);
		
		// Creates a new scene using the main VBox
		Scene scn2 = new Scene(oButtonBox, 400, 150);
		// Sets the new stage to display the new scene
		oSecondaryStage.setScene(scn2);
		// Changes the title of the write window
		oSecondaryStage.setTitle("Write a Quote");
		// Shows the new stage
		oSecondaryStage.show();
	
	}
	
	// writeFortune
	// Writes a FortuneEntry object to the WriteQuoteServer based on user input
	private void writeFortune(String sQuote, String sAuthor) throws UnknownHostException, IOException
	{
		// If the write socket hasn't been initialized yet:
		if(oClientSocketWrite == null)
		{
			// Opens the write socket on port 28083
			oClientSocketWrite = new Socket("localhost", 28083);
			// Gets the output stream of the newly opened write socket
			oObjStrTo = new ObjectOutputStream(oClientSocketWrite.getOutputStream());
		}
		
		try
		{
			// Creates a new FortuneEntry object from the user's quote and author
			FortuneEntry oOut = new FortuneEntry(sQuote, sAuthor);
			// Writes the FortuneEntry to the WriteQuoteServer
			oObjStrTo.writeObject(oOut);
		}
		// Catches any exception thrown by writing the new object, then prints its stack trace.
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
