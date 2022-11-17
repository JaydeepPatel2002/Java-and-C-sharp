package assign3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/*
 * BattleRework
 * Main "game" class for COSC 190 Assignment #3
 * Jaydeep Patel, Liam Willis
 */
public class BattleRework extends Application {
	
	// ArrayList of Spaceships, holds the Spaceships in each level
	private ArrayList<Spaceship> oShipList;
	// ArrayList of Shells, holds the player projectiles present in the current level
	private ArrayList<Shell2> oShellList;
	// Constant variable, sets the player's base horizontal movement speed
	private static final int BASE_PLAYER_SPEED = 15;
	// The Pane object used in each battle stage
	private Pane oMainPane = new Pane();
	// The stage used to display each screen
	public Stage primaryStage = new Stage();
	// The level the player is currently on, starts at 1
	private int iCurrentLevel = 1;
	// A hidden text field used to check the state of a battle stage
	// (if the user has won or lost)
	public Text oStageCheck = new Text("default");
	// The string keyword indicating the player won
	public static final String BATTLE_WIN = "Won";
	// The string keyword indicating the player lost
	public static final String BATTLE_LOSE = "Lost";
	// Animation used to repeatedly check the battle stage's state
	private Timeline oStageCheckAni;
	// Flag which decides if they player can shoot or not
	private boolean bCanShoot = true;
	// Progress bar for each battle stage (see ProgressBar class)
	public ProgressBar oProgress;
	// The width of each screen
	public static final int BATTLE_WIDTH = 400;
	// The height of each screen
	public static final int BATTLE_HEIGHT = 400;
	// An ArrayList of Strings which holds the high scores
	// Values from Scores.txt are read to this, and values from this list are written to Scores.txt
	private ArrayList<String> highScores = new ArrayList<String>();
	// The name and score of the 1st score in the list
	private String name1;
	private int score1;
	// The name and score of the 2nd score in the list
	private String name2;
	private int score2;
	// The name and score of the 3rd score in the list
	private String name3;
	private int score3;
	// The name given by the player at the first screen
	private String playerName;
	// An ArrayList of Score objects, used to compare and organize high scores
	private ArrayList<Score> scoreObjList = new ArrayList<Score>();
	// The sound of the player firing a shell
	Media media = new Media("https://quicksounds.com/uploads/tracks/558715509_702002223_1521380462.mp3");
	// The music which plays on the start screen
	Media intro = new Media("https://dm0qx8t0i9gc9.cloudfront.net/watermarks/audio/rsqUhk0trk23etvzn/audioblocks-race-against-the-clock-jon-presstone_HGZ5f_Wkc_WM.mp3");
	// The sound which plays on clearing a level
	Media ending = new Media("https://assets.mixkit.co/sfx/preview/mixkit-medieval-show-fanfare-announcement-226.mp3");
	// MediaPlayer for the intro music
	MediaPlayer mediaPlayer3 = new MediaPlayer(intro);
	// MediaPlayer for the level win sound
	MediaPlayer mediaPlayer4 = new MediaPlayer(ending);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	// start
	// Launches the opening screen of the game
	// Note: The stage in the parameter of this function is never used.
	@Override
	public void start(Stage unused) throws Exception {
		// TODO Auto-generated method stub
		
		// Plays the intro music
		mediaPlayer3.play();
		
		// BorderPane which will contain the UI elements for the intro screen
		BorderPane obPane = new BorderPane();
		// Pane containing the elements at the top of the screen
    	Pane obTop = new Pane();
    	// Horizontal box containing the elements at the bottom of the screen
		HBox obbottom = new HBox();
		// ImageView used as the image for the play button
		ImageView img = new ImageView("file:images/play2.png");
		// Button which will start the game
		Button cmdSpin = new Button("",img);
		// Sets the button's width and height
		img.setFitWidth(75);
		img.setFitHeight(45);
		// Pane used to contain objects in the center of the pane
		Pane obCenter = new Pane();
		// Text which tells the user to enter their name
		Text txtResults = new Text("Enter your name: ");
		txtResults.setId("introtext");
		
		// Adding the sub-panes to the main pane
		obPane.setTop(obTop);
		obPane.setBottom(obbottom);
		obPane.setCenter(obCenter);
		// Setting the position of the intro text
		txtResults.setLayoutY(50);
		txtResults.setLayoutX(60);
		// Adding the intro text to the pane
		obTop.getChildren().add(txtResults);
		// TextField which will get the player's name
		TextField b = new TextField();
		
		obTop.setMaxHeight(10);
		// Animation which checks what the player has entered in the text field
		   Timeline obAni = new Timeline(new KeyFrame(Duration.millis(100),e->{ 
			  
			   // If the player has entered at least 4 characters:
			 if (b.getText().length()>=4) 
			 {
				 // Enables the play button
				 cmdSpin.setDisable(false);
			 }
			 // If the player has not entered at least 4 characters:
			 else if(b.getText().length()<4)
			 {
				 // Disables the play button
				 cmdSpin.setDisable(true);

			 }
		
		  }));
		   
		   // Sets the animation to loop indefinitely, then plays the animation
			obAni.setCycleCount(Timeline.INDEFINITE);
			obAni.play();
			
		// Sets the title of the stage
        primaryStage.setTitle("WELCOME");
  
        
        b.setLayoutX(125);
        b.setLayoutY(59);
        
        // Adds the button to the page
        obCenter.getChildren().add(b);
        
      
        
       obbottom.getChildren().add(cmdSpin);
       // Sets the background color of the button to be fully transparent (only displays the button image)
       cmdSpin.setStyle("-fx-background-color:transparent");
       
       // Disables the play button
       cmdSpin.setDisable(true);
       
       obbottom.setAlignment(Pos.TOP_CENTER);
       obbottom.setMinHeight(100);
 
       // Creates a new Scene for the intro
        Scene sc = new Scene(obPane, 400, 400);
        
        // Links the scene to the CSS stylesheet found in application.css
        sc.getStylesheets().add("file:styles/application.css");
        // Adds the CSS class "introend" to the scene
		obPane.getStyleClass().add("introend");
        
        // Sets the on-click event of the play button to
        cmdSpin.setOnAction(e->{mediaPlayer3.stop(); playerName = b.getText(); battleStart();});
        
        // Sets the scene of the primary stage to the intro scene
        primaryStage.setScene(sc);
        //Displays the primary stage
        primaryStage.show();
	}
	
	// battleStart
	// Sets up and displays a battle stage based on the current level
	public void battleStart()
	{
		// Resets the value of the stage check field to "default" 
		oStageCheck.setText("default");
		
		// Initializes the Shell list
		oShellList = new ArrayList<Shell2>();
		
		// Resets the main battle pane
		oMainPane = new Pane();
		
		// Adds a Rectangle to the main pane, serving as a visual indicator of how far down the ships can go before the player loses
		Rectangle oBoundary = new Rectangle(0,0,0,0);
		// Places the start of the rectangle at 0, 300
		oBoundary.setX(0);
		oBoundary.setY(300);
		// Gives the Rectangle a width of 400 and a height of 3
		oBoundary.setWidth(400);
		oBoundary.setHeight(3);
		// Sends the Rectangle to the back of the pane
		oBoundary.toBack();
		// Sets the rectangle's colour to red
		oBoundary.setFill(Color.RED);
		// Adds the rectangle to the pane
		oMainPane.getChildren().add(oBoundary);
		
		// ImageView which is controlled by the player
		ImageView oPlayer = new ImageView("file:images/Launch.png");
		// Sets the player's ship to the center of the screen, at y = 320
		oPlayer.setX(225 - oPlayer.getImage().getWidth());
		oPlayer.setY(320);
		// Adds the player's ship to the pane
		oMainPane.getChildren().add(oPlayer);
		
		// Initializes the Spaceship list depending on the current level (see initShips method below)
		oShipList = initShips();
		// Adds each Spaceship's image to the pane
		oShipList.stream().forEach(s -> oMainPane.getChildren().add(s.obView));
		
		// Assigns the event handler function to the key press event of the pane (see handleInput method below)
		oMainPane.setOnKeyPressed(e -> handleInput(e, oPlayer, primaryStage));
		
		// Adds the stage check text to the pane
		oMainPane.getChildren().add(oStageCheck);
		
		// Initializes a new animation which checks the stage check text every 60 milliseconds (see checkBattle method below)
		oStageCheckAni = new Timeline(new KeyFrame(Duration.millis(60), e -> checkBattle(oStageCheckAni)));
		oStageCheckAni.setCycleCount(Timeline.INDEFINITE);
		// Plays the stage check animation
		oStageCheckAni.play();
		
		// Sets the progress bar to a new progress bar:
		// - Width: 0
		// - Height: 10
		// - X: 60
		// - Y: 390
		// - The list of ships in the level
		// - The width of the pane it has to fill
		oProgress = new ProgressBar(0, 10, 60, 390, oShipList, 350);
		// Adds the progress bar to the pane
		oMainPane.getChildren().add(oProgress.m_oBar);
		
		// Adds text to the pane beside the progress bar
		Text oProgressText = new Text("Progress: ");
		// If the player is on level or higher:
		if(iCurrentLevel >= 10)
		{
			// Sets the text of the progress bar to "?"
			oProgressText.setText("Progress: ?");
			// Hides the progress bar by setting the fill colour to be completely transparent
			oProgress.m_oBar.setFill(Color.TRANSPARENT);
		}
		
		// Adds the progress text, sets its position, and changes its colour to white
		oMainPane.getChildren().add(oProgressText);
		oProgressText.setX(0);
		oProgressText.setY(397);
		oProgressText.setFill(Color.WHITE);
		
		// Creates a new Scene for the battle with the battle width and height
		Scene oScn = new Scene(oMainPane, BATTLE_WIDTH, BATTLE_HEIGHT);
		// Links the new Scene to the CSS stylesheet "application.css"
		oScn.getStylesheets().add("file:styles/application.css");
		// Sets the ID property of the main pane to "Main"
		oMainPane.setId("Main");
		// Sets the new scene as the scene of the primary stage
		primaryStage.setScene(oScn);
		
		// Sets the title of the current stage to the current level if it is not level 11
		if(iCurrentLevel != 11) primaryStage.setTitle("Level " + iCurrentLevel);
		// Sets the title of the current stage to level 10 if it is level 11
		else primaryStage.setTitle("Level 10");
		// Prevents the player from resizing the window
		primaryStage.setResizable(false);
		// Shows the battle stage
		primaryStage.show();
		
		// Gives the main pane focus so the controls work
		oMainPane.requestFocus();
	}
	
	// battleStart(iPlayerPos)
	// Overload of the above method which places the player at a specific position, used to maintain the player's position between levels 10 and 11
	public void battleStart(int iPlayerPos)
	{
		oStageCheck.setText("default");
		
		oShellList = new ArrayList<Shell2>();
		
		oMainPane = new Pane();
		
		Rectangle oBoundary = new Rectangle(0,0,0,0);
		oBoundary.setX(0);
		oBoundary.setY(340);
		oBoundary.setWidth(400);
		oBoundary.setHeight(3);
		oBoundary.toBack();
		oBoundary.setFill(Color.RED);
		oMainPane.getChildren().add(oBoundary);
		
		ImageView oPlayer = new ImageView("file:images/Launch.png");
		oPlayer.setX(225 - oPlayer.getImage().getWidth());
		oPlayer.setY(320);
		oMainPane.getChildren().add(oPlayer);
		
		oShipList = initShips();
		oShipList.stream().forEach(s -> oMainPane.getChildren().add(s.obView));
		
		oMainPane.setOnKeyPressed(e -> handleInput(e, oPlayer, primaryStage));
		
		oMainPane.getChildren().add(oStageCheck);
		
		oStageCheckAni = new Timeline(new KeyFrame(Duration.millis(60), e -> checkBattle(oStageCheckAni)));
		oStageCheckAni.setCycleCount(Timeline.INDEFINITE);
		oStageCheckAni.play();
		
		oProgress = new ProgressBar(0, 10, 60, 390, oShipList, 350);
		oMainPane.getChildren().add(oProgress.m_oBar);
		
		Text oProgressText = new Text("Progress: ");
		if(iCurrentLevel >= 10)
		{
			oProgressText.setText("Progress: ?");
			oProgress.m_oBar.setFill(Color.TRANSPARENT);
		}
		
		oMainPane.getChildren().add(oProgressText);
		oProgressText.setX(0);
		oProgressText.setY(397);
		oProgressText.setFill(Color.WHITE);
		
		Scene oScn = new Scene(oMainPane, BATTLE_WIDTH, BATTLE_HEIGHT);
		oScn.getStylesheets().add("file:styles/application.css");
		oMainPane.setId("Main");
		primaryStage.setScene(oScn);
		if(iCurrentLevel != 11) primaryStage.setTitle("Level " + iCurrentLevel);
		else primaryStage.setTitle("Level 10");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		oMainPane.requestFocus();
	}
	
	// initShips
	// Returns an ArrayList of Spaceships constructed for each level layout
	private ArrayList<Spaceship> initShips()
	{
		// Creates a new ArrayList to hold the level's Spaceships
		ArrayList<Spaceship> oShips = new ArrayList<Spaceship>();
		
		// Changes which ships are assigned based on the current level:
		switch(iCurrentLevel)
		{
			// Level 1: 6 Standard ships
			case 1:
				oShips.add(new Spaceship(10,0, oMainPane, ShipType.STANDARD, oStageCheck)); 
				oShips.add(new Spaceship(110,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(250,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,0, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(60,-50, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(305,-50, oMainPane, ShipType.STANDARD, oStageCheck));
				break;
			
			// Level 2: Standard and Diagonal ships
			case 2:
				oShips.add(new Spaceship(120,0, oMainPane, ShipType.DIAGONALLEFT, oStageCheck)); 
				oShips.add(new Spaceship(placeLeftOfHalf(),0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(240,0, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				
				oShips.add(new Spaceship(10,-125, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,-125, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(200,-150, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(100,-150, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				break;
			
			// Level 3: Introduces Blockers
			case 3:
				oShips.add(new Spaceship(placeLeftOfHalf() - 40,100, oMainPane, ShipType.BLOCKER, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf(),100, oMainPane, ShipType.BLOCKER, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),100, oMainPane, ShipType.BLOCKER, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf() + 40,100, oMainPane, ShipType.BLOCKER, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf(),0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),0, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(10,-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf(),-150, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-150, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(20,-225, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(350,-225, oMainPane, ShipType.STANDARD, oStageCheck));
				break;
				
			// Level 4: Introduces moving blockers
			case 4:
				oShips.add(new Spaceship(placeLeftOfHalf() - 40,120, oMainPane, ShipType.BLOCKERSTRAFELEFT, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf() + 40,120, oMainPane, ShipType.BLOCKERSTRAFERIGHT, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() - 120,160, oMainPane, ShipType.BLOCKERSTRAFELEFT, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf() + 120,160, oMainPane, ShipType.BLOCKERSTRAFERIGHT, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf() + 20,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf() + 40,0, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() - 40,0, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf(),-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() - 40,-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf() + 40,-150, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(10,-200, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,-200, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(10,-210, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(40,-210, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(360,-210, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(320,-210, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				
				oShips.add(new Spaceship(40,-300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(320,-300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() - 10,-300, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf() + 10,-300, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				break;
			
			// Level 5: Introduces Invulnerable Blockers
			case 5:
				oShips.add(new Spaceship(10,120, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(50,120, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(320,120, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(360,120, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(90,120, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(280,120, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				
				oShips.add(new Spaceship(10,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20,0, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(10,-150, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(360,-150, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf(),-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(10,-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,-150, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf() - 40,-250, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf() + 40,-250, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf(),-250, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-250, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf(),-300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(10,-300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,-300, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(10,-350, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck)); 
				oShips.add(new Spaceship(110,-350, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(250,-350, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(360,-350, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf() + 20,-2300, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				break;
				
			// Level 6: Introduces Fast ships
			case 6:
				oShips.add(new Spaceship(placeLeftOfHalf(),-100, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-100, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				
				oShips.add(new Spaceship(10,-100, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,-100, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(50,-400, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(320,-400, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf(),-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(10,-700, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(360,-700, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(50,-900, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(320,-900, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() - 40,-1200, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf() + 40,-1200, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				break;
			
			// Level 7: Fast, Diagonal (Random), and Blockers
			case 7:
				oShips.add(new Spaceship(10,150, oMainPane, ShipType.BLOCKERINVULNSTRAFERIGHT, oStageCheck));
				oShips.add(new Spaceship(360,150, oMainPane, ShipType.BLOCKERINVULNSTRAFELEFT, oStageCheck));
				
				oShips.add(new Spaceship(360,0, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(10,0, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				
				oShips.add(new Spaceship(360,-100, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(50,-100, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(280,-100, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(10,-200, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(320,-200, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(90,-200, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf(),-250, oMainPane, ShipType.DIAGONALRANDOMDIR, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-250, oMainPane, ShipType.DIAGONALRANDOMDIR, oStageCheck));
				oShips.add(new Spaceship(10,-1050, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(360,-1050, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				
				oShips.add(new Spaceship(360,-500, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(10,-500, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,-530, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(10,-530, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf(),-550, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-550, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf(),-580, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(),-580, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(10,-2550, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(360,-2550, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(10,-2700, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(360,-2700, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				break;
				
			// Level 8: Walls of Standard ships	
			case 8:
				oShips.add(new Spaceship(0,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(40,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(80,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(120,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(160,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(200,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(240,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(280,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(320,0, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360,0, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(200, -150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(240, -150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(280, -150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(320, -150, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(360, -150, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(0, -300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(40, -300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(80, -300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(120, -300, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(160, -300, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(40, -600, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(80, -600, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(120, -600, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(160, -600, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(200, -600, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(240, -600, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(280, -600, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(320, -600, oMainPane, ShipType.STANDARD, oStageCheck));
				oShips.add(new Spaceship(80, -1900, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(280, -2000, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(240, -2100, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				break;
			
			// Level 9: Insane speed ships
			case 9:
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -200, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, 150, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				
				oShips.add(new Spaceship(40, -300, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(240, -350, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -1000, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(360, -450, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(50, -1525, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -1450, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				
				oShips.add(new Spaceship(90, -1750, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(280, -1750, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(130, -2050, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(240, -2050, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				break;
			
			// Level 10: Final level, a combination of everything
			case 10:
				oShips.add(new Spaceship(0,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(40,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(80,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(280,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(320,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(360,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -75, oMainPane, ShipType.STANDARD, oStageCheck));
				
				oShips.add(new Spaceship(10, -500, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(10, -600, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(360, -800, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(360, -900, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf(), -700, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(placeRightOfHalf(), -700, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -1500, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(20, -850, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				oShips.add(new Spaceship(320, -850, oMainPane, ShipType.STANDARDFAST, oStageCheck));
				
				oShips.add(new Spaceship(10, -300, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(360, -300, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(50, -325, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(320, -325, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(90, -350, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(280, -350, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(130, -375, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(240, -375, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(170, -400, oMainPane, ShipType.DIAGONALLEFT, oStageCheck));
				oShips.add(new Spaceship(200, -400, oMainPane, ShipType.DIAGONALRIGHT, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -3000, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -3500, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -4000, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -4500, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -5000, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				
				oShips.add(new Spaceship(0, -6500, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(40, -6700, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(80, -6900, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(120, -7100, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(160, -7300, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(200, -7500, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(240, -7700, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(280, -7900, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(320, -8100, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				oShips.add(new Spaceship(360, -8200, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf(), -6150, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -6100, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 40, -6150, oMainPane, ShipType.STANDARDFASTER, oStageCheck));
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -9000, oMainPane, ShipType.STANDARDINSANE, oStageCheck));
				break;
			
			// "Level 11"
			// Isn't a real level, occurs right after level 10 with no transition screen
			// Has harmless ships which drift by and spell out "COSC", followed by 1 standard ship.
			case 11:
				oShips.add(new Spaceship(0,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(40,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(80,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(280,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(320,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				oShips.add(new Spaceship(360,50, oMainPane, ShipType.BLOCKERINVULN, oStageCheck));
				
				oShips.add(new Spaceship(180 + 500,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(210 + 500,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 500,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 500,130, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 500,160, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 500,190, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(180 + 500,190, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(210 + 500,190, oMainPane, ShipType.GOLEFT, oStageCheck));
			
				oShips.add(new Spaceship(180 + 750,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(210 + 750,130, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 750,130, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(210 + 750,160, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 750,160, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(180 + 750,190, oMainPane, ShipType.GOLEFT, oStageCheck));
				
				oShips.add(new Spaceship(220 + 1000,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(190 + 1000,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(180 + 1000,130, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(180 + 1000,160, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(170 + 1000,190, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(140 + 1000,190, oMainPane, ShipType.GOLEFT, oStageCheck));
				
				oShips.add(new Spaceship(180 + 1250,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(210 + 1250,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 1250,100, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 1250,130, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 1250,160, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(150 + 1250,190, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(180 + 1250,190, oMainPane, ShipType.GOLEFT, oStageCheck));
				oShips.add(new Spaceship(210 + 1250,190, oMainPane, ShipType.GOLEFT, oStageCheck));
				
				oShips.add(new Spaceship(placeLeftOfHalf() + 20, -275, oMainPane, ShipType.STANDARD, oStageCheck));
				break;
		}
		
		// Returns the ship list
		return oShips;
	}
	
	// handleInput
	// Input handler for the player's key presses
	// Requires the KeyEvent the player triggered, the player's imageview, and the current stage
	private void handleInput(KeyEvent e, ImageView oPlayer, Stage oCurrent)
	{
		// If the ship list is not empty:
		if(oShipList.size() > 0)
		{
			// If the player pushed the left arrow key:
			if(e.getCode() == KeyCode.LEFT)
			{
				// If the player is not already at the left edge of the screen:
				if(oPlayer.getX() > 0)
				{
					// Subtracts the player's speed from their X position
					oPlayer.setX(oPlayer.getX() - BASE_PLAYER_SPEED);
				}
			}
			
			// If the player pushed the right arrow key:
			else if (e.getCode() == KeyCode.RIGHT)
			{
				// If the player has not reached the right side of the screen:
				if(oPlayer.getX() < 400 - oPlayer.getImage().getWidth())
				{
					// Adds the player's speed to their X position
					oPlayer.setX(oPlayer.getX() + BASE_PLAYER_SPEED);
				}
			}
			
			// If the player pushed the up arrow key:
			else if(e.getCode() == KeyCode.UP)
			{
				// If the player can currently shoot:
				if(bCanShoot)
				{
					// Creates a new MediaPlayer for the player shoot sound effect
					MediaPlayer mediaPlayer = new MediaPlayer(media);
					// Plays the sound effect
					mediaPlayer.play();
					
					// If there are already 20 shells in-game (off-screen shells are still kept in the list)
					if(oShellList.size() == 20)
					{
						// Deactivates the first shell in the list
						oShellList.get(0).deactivate(oMainPane);
						// Removes the first shell in the list
						oShellList.remove(0);
						// Adds a new shell to the list/game (see addShell method below)
						addShell(oPlayer, oCurrent);
					}
					
					// If there are less than 20 shells in play
					else
					{
						// Adds a new shell to the list/game (see addShell method below)
						addShell(oPlayer, oCurrent);
					}
				}
				
			}
		}
	}
	
	// addShell
	// Adds a new Shell object to the game
	// Requires the player's imageview, and the current stage as parameters
	private void addShell(ImageView oPlayer, Stage oCurrent)
	{
		// Creates a new Shell object with:
		/*
		 * The X coordinates of the center of the player image
		 * The Y coordinates of the top of the player's image
		 * The list of ships in the level
		 * The main pane
		 * The stage check text
		 * The progress bar
		 */
		Shell2 oShell = new Shell2(oPlayer.getX() + oPlayer.getImage().getWidth() / 2, oPlayer.getY(), oShipList, oMainPane, oStageCheck, oProgress);
		
		// Adds the Shell's image to the main pane
		oMainPane.getChildren().add(oShell.getView());
		// Plays the Shell's animation (see Shell2.up)
		oShell.playAnim();
		// Adds the new shell to the shell list
		oShellList.add(oShell);
		
		// Creates a new animation which sets the "can shoot" flag to false every 5 milliseconds
		Timeline oShellDelay = new Timeline(new KeyFrame(Duration.millis(5), e -> bCanShoot = false));
		// Repeats the animation 200 times to add a 1 second delay to when the player is able to fire
		oShellDelay.setCycleCount(200);
		// Sets the animation to set the "can shoot" flag to true when it is finished
		oShellDelay.setOnFinished(e -> bCanShoot = true);
		// Plays the shell delay animation
		oShellDelay.play();
		
	}
	
	// showWinScreen
	// Constructs and displays the win screen for the user
	// Will display either a transition screen between 2 levels or the game win screen
	private void showWinScreen()
	{
		// Plays the sound effect for winning a level
		mediaPlayer4.play();
		// Creates a new pane to store the UI elements
		Pane obPane2 = new Pane();
		
		// If the current level is 11 (the player has won the whole game):
		if(iCurrentLevel == 11)
		{
			// Sets header text informing the player that they won
			Text txtWin = new Text("You have won the game!");
			// Gives the header an ID of "introtext" for CSS styling
			txtWin.setId("introtext");
			// Adds the header text to the pane
			obPane2.getChildren().add(txtWin);
			// Sets the header text's position
			txtWin.setX(0);
			txtWin.setY(30);
			
			// Adds a sub-header text which sits above the high scores
			Text oScoreHeader = new Text ("High Scores: ");
			// Sets the subheader's class to "txtHighScore" for CSS styling
			oScoreHeader.getStyleClass().add("txtHighScore");
			// Adds the subheader to the pane
			obPane2.getChildren().add(oScoreHeader);
			// Positions the subheader
			oScoreHeader.setLayoutX(0);
			oScoreHeader.setLayoutY(70);
			
			// Sorts the high scores (see sortScores method below)
			sortScores();
			
			// Text object containing the first high score, as read from Scores.txt
			Text scoreText1 = new Text("1: " + scoreObjList.get(0).getName() + ", " + scoreObjList.get(0).getScore());
			
			// Adds the 1st score text to the pane
			obPane2.getChildren().add(scoreText1);
			
			// Positions the first score text
			scoreText1.setX(0);
			scoreText1.setY(100);
			
			// Same as above, but for the 2nd high score
			Text scoreText2 = new Text("2: " + scoreObjList.get(1).getName() + ", " + scoreObjList.get(1).getScore());
			
			obPane2.getChildren().add(scoreText2);
			
			scoreText2.setX(0);
			scoreText2.setY(125);
			
			// Same as above, but for the 3rd high score
			Text scoreText3 = new Text("3: " + scoreObjList.get(2).getName() + ", " + scoreObjList.get(2).getScore());
			
			obPane2.getChildren().add(scoreText3);
			
			scoreText3.setX(0);
			scoreText3.setY(150);
			
			// Gives each high score text a class of "txtHighScore" for CSS styling
			scoreText1.getStyleClass().add("txtHighScore");
			scoreText2.getStyleClass().add("txtHighScore");
			scoreText3.getStyleClass().add("txtHighScore");
			primaryStage.setTitle("Results");
		}
		
		// If the current level is not level 11 (the player has only cleared one of the other stages):
		else
		{
			// Text element which will display between-level hints
			Text txtResults = new Text("level complete");
			// Heading text element which informs the player that they completed the current stage
			Text textheading = new Text("LEVEL " + iCurrentLevel + " COMPLETE");
			// ImageView used in the "continue" button
			ImageView img = new ImageView("file:images/next.png");
			// ImageView used in the "quit" button
			ImageView imgQuit = new ImageView("file:images/exit.jpg");
			// The button which continues to the next level, and the buttton which quits the game, respectively.
			Button again = new Button("",img);
			Button quitBtn = new Button("", imgQuit);
			
			// Adds the continue button to the pane
			obPane2.getChildren().add(again);
			// Sets the continue button to:
			// - Stop the win sound player
			// - Increment the current level
			// - Run battleStart() for the next level
			again.setOnAction(e-> {mediaPlayer4.stop(); iCurrentLevel++; battleStart();});
			
			// Adds the quit button to the pane
			obPane2.getChildren().add(quitBtn);
			// Sets the continue button to:
			// - Stop the win sound player
			// - Show the game over screen
			quitBtn.setOnAction(e -> {mediaPlayer4.stop(); showLoseScreen();});
			
			// Positioning the continue button
			again.setTranslateX(100 - again.getWidth());
			again.setTranslateY(200);
			
			// Sets the background color of each button to be fully transparent (only the images are visible)
			again.setStyle("-fx-background-color:transparent");
			quitBtn.setStyle("-fx-background-color:transparent");
			
			// Positions the heading text, then gives it an ID of "introtext" for CSS formatting
			textheading.setLayoutX(0);
			textheading.setLayoutY(35);
			textheading.setId("introtext");
			
			// Changes the contents of the hint text depending on the current level:
			switch (iCurrentLevel) {
			// Level 1
			case 1:
			{
				txtResults = new Text( "=======================================================\n In next Level you will see spaceship \nthat will move diagonal( left and right while \n moving down), be sure to lead your shot ");
				break;
				
			}
			// Level 2
			case 2:
			{
				txtResults = new Text("=======================================================\n In level 3 you will face blockers \nthat will not move but they will block your shots");
				break;	
			}
			// Level 3
			case 3:
			{
				txtResults = new Text("=======================================================\n Some blockers can also move left and right.\n Aim your shots well!");
				break;
				
			}
			// Level 4
			case 4:
			{
				txtResults = new Text("=======================================================\n Silver blockers are invincible, and cannot\n be destroyed. Aim around them, or \nwait for ships to pass through.");
				break;
			}
			// Level 5
			case 5:
			{
				
				txtResults = new Text("=======================================================\n You're only halfway to the end!\n Be careful, they get faster from here!");
				break;
			}
			// Level 6
			case 6:
			{
				txtResults = new Text("=======================================================\n Try to prioritize the faster ships \nover the slower ones.");
				break;
				
			}
			// Level 7
			case 7:
			{
				txtResults = new Text("The next level contains mostly standard ships. Should be easy, right?");
				break;
			}
			// Level 8
			case 8:
			{
				txtResults = new Text("These next spaceships are the fastest \nones yet. Be ready to think fast.");
				break;
			}
			// Level 9
			case 9:
			{
				txtResults = new Text("Final level up next!\n Best of luck!");
				break;
				
			}
			
			}
		
		// Adds the heading text and the hint text to the pane
		obPane2.getChildren().add(txtResults);
		obPane2.getChildren().add(textheading);
		// Gives the hint text and ID of "description" for CSS formatting
		txtResults.setId("description");
		// Sets the fit width and height of the images used by the continue (img) and quit (imgQuit) buttons
		img.setFitWidth(75);
		img.setFitHeight(45);
		imgQuit.setFitWidth(75);
		imgQuit.setFitHeight(45);
		// Sets the position of the hint text
		txtResults.setLayoutY(50);
		txtResults.setLayoutX(7);
		// Sets the position of the quit button
		quitBtn.setTranslateX(210);
		quitBtn.setTranslateY(200);
		
		// Sets the title of the stage to "Stage Complete!"
		primaryStage.setTitle("Stage Complete!");
		}
			
		// Creates a new scene to hold the pane
		Scene oScn = new Scene(obPane2, 400, 400);
		// Links the new scene to the "application.css" stylesheet
		oScn.getStylesheets().add("file:styles/application.css");
		// Gives the pane a class of introend for background image formatting
		obPane2.getStyleClass().add("introend");
		// Sets the stage to the new scene
		primaryStage.setScene(oScn);
		// Displays the new scene
		primaryStage.show();
		
	}
	
	// showLoseScreen
	// Constructs and displays a "game over" screen
	// Called when the player loses, or quits after finishing a level
	private void showLoseScreen()
	{
		// Pane to hold the UI elements
		Pane oLosePane = new Pane();
		
		// Heading text informing the user that the game is over
		Text oResults = new Text("GAME OVER");
		// Gives the heading text an id of "introtext" for CSS formatting
		oResults.setId("introtext");
		// Adds the heading text to the pane
		oLosePane.getChildren().add(oResults);
		// Positions the heading text
		oResults.setLayoutY(50);
		oResults.setLayoutX(85);
		
		// Sub-header text which sits above the high scores
		Text oScoreHeader = new Text ("High Scores: ");
		// Gives the subheader text a class of "txtHighScore" for CSS formatting
		oScoreHeader.getStyleClass().add("txtHighScore");
		// Adds the subheader to the pane
		oLosePane.getChildren().add(oScoreHeader);
		// Positions the subheader
		oScoreHeader.setLayoutX(0);
		oScoreHeader.setLayoutY(70);
		
		// Sorts the high scores (see sortScores method below)
		sortScores();
					
		// Text object containing the first high score, as read from Scores.txt
		Text scoreText1 = new Text("1: " + scoreObjList.get(0).getName() + ", " + scoreObjList.get(0).getScore());
					
		// Adds the 1st score text to the pane
		oLosePane.getChildren().add(scoreText1);
					
		// Positions the first score text
		scoreText1.setX(0);
		scoreText1.setY(100);
					
		// Same as above, but for the 2nd high score
		Text scoreText2 = new Text("2: " + scoreObjList.get(1).getName() + ", " + scoreObjList.get(1).getScore());
					
		oLosePane.getChildren().add(scoreText2);
					
		scoreText2.setX(0);
		scoreText2.setY(125);
					
		// Same as above, but for the 3rd high score
		Text scoreText3 = new Text("3: " + scoreObjList.get(2).getName() + ", " + scoreObjList.get(2).getScore());
					
		oLosePane.getChildren().add(scoreText3);
					
		scoreText3.setX(0);
		scoreText3.setY(150);
					
		// Gives each high score text a class of "txtHighScore" for CSS styling
		scoreText1.getStyleClass().add("txtHighScore");
		scoreText2.getStyleClass().add("txtHighScore");
		scoreText3.getStyleClass().add("txtHighScore");
		primaryStage.setTitle("Results");
		
		// Creates a new scene to hold the pane
		Scene oScn = new Scene(oLosePane, 400, 400);
		// Links the new scene to the "application.css" stylesheet
		oScn.getStylesheets().add("file:styles/application.css");
		// Gives the pane a class of introend for background image formatting
		oLosePane.getStyleClass().add("introend");
		// Sets the stage to the new scene
		primaryStage.setScene(oScn);
		// Displays the new scene
		primaryStage.show();
		
		
	}
	
	// checkBattle
	// Void function used in the stage check animation
	// Checks the contents of the stage check text box, and sets the state of the game accordingly
	// Requires the animation which uses it to be passed in as a parameter
	private void checkBattle(Timeline oAni)
	{
		// If the stage check text indicates the player won the stage
		if(oStageCheck.getText().equals(BATTLE_WIN))
		{
			// Stops the stage check animation
			oAni.stop();
			
			// If the current level is not level 10
			if(iCurrentLevel != 10)
			{
				// Shows the level win screen
				showWinScreen();
			}
			
			// If the current level is level 10
			else
			{
				// Advances to the next battle level without showing a transition screen
				iCurrentLevel++;
				battleStart();
			}
			
		}
		
		// If the stage check text indicates the player lost the stage
		else if(oStageCheck.getText().equals(BATTLE_LOSE))
		{
			// Stops the stage check animation
			oAni.stop();
			// Stops the animations of each ship in the list
			oShipList.stream().forEach(s -> s.obAni.stop());
			// Clears the ship list
			oShipList = new ArrayList<Spaceship>();
			// Shows the game over screen
			showLoseScreen();
		}
		
	}
	
	// placeLeftOfHalf
	// Returns the X coordinate which places a spaceship just to the left of half of the screen's width
	private int placeLeftOfHalf()
	{
		return (BATTLE_WIDTH / 2) - 40;
	}
	
	// placeRightOfHalf
	// Returns the X coordinate which places a spaceship just to the right of half of the screen's width
	private int placeRightOfHalf()
	{
		return (BATTLE_WIDTH / 2);
	}
	
	// readScores
	// Uses a BufferedReader to read the high scores from Scores.txt
	// Returns an ArrayList of Strings with each line in the file
	private ArrayList<String> readScores() throws FileNotFoundException, IOException
	{
		// Using a BufferedReader looking at Scores.txt:
		try(BufferedReader oBr = new BufferedReader(new FileReader("Scores.txt")))
		{
			// Reads all lines in the file, stores them in an ArrayList of Strings
			return (ArrayList<String>) oBr.lines().collect(Collectors.toList());
		}
	}
	
	// writeScores
	// Uses a PrintWriter to write new high scores to Scores.txt
	private void writeScores() throws FileNotFoundException
	{
		// Using a PrintWriter looking at Scores.txt:
		try(PrintWriter writer = new PrintWriter(new File("Scores.txt")))
		{
			// For each score in the high scores ArrayList:
			for(String score: highScores)
			{
				// Writes each score string, followed by a line break
				writer.write(score + "\n");
			}
		}
	}
	
	// sortScores
	// Reads high scores from Scores.txt, compares them to the player's score, sorts them, then writes them to Scores.txt
	private void sortScores()
	{
		try
		{
			// Reads the score strings form Scores.txt into the high scores String ArrayList
			highScores = readScores();
		}
		// If an exception is thrown:
		catch(Exception e)
		{
			// Prints the stack trace of the exception
			e.printStackTrace();
		}
		
		// For each score string in the list:
		for(String line: highScores)
		{
			// Splits each string into an array of strings:
			// - Position 0: Name
			// - Position 1: Score
			String[] current = line.split(",");
			
			// Adds a new Score object to the score object ArrayList with the current line's name and score
			scoreObjList.add(new Score(current[0], Integer.valueOf(current[1])));
		}
		
		// The player the level actually reached
		int levelReached = 0;
		
		// If the player is on level 11, set their score to level 10 (level 11 is not a real level, but a continuation of level 10)
		if(iCurrentLevel == 11) levelReached = 10;
		
		// If the player did not reach level 11, set their score to the current level
		else levelReached = iCurrentLevel;
		
		// Creates a new score object for the player's name and score
		Score playerScore = new Score(playerName, levelReached);
		// Flag used to check if the player tied another high score
		boolean tied = false;
		
		// For each Score in the score object ArrayList:
		for(int i = 0; i < scoreObjList.size(); i++)
		{
			// If the player's score is equal to the current score:
			if(scoreObjList.get(i).getScore() == playerScore.getScore())
			{
				// Sets the name of the current Score object to the player's name
				scoreObjList.add(i, playerScore);
				// Sets the tied flag to true
				tied = true;
				break;
				
			}
		}
		
		// If the player did not tie with another high score, add their score object to the object list
		if(!tied) scoreObjList.add(playerScore);
		
		// Adjusts the score object ArrayList using stream:
		scoreObjList = (ArrayList<Score>)scoreObjList.stream()
				// Sorts the objects by score in descending order
				.sorted((s1, s2) -> s2.getScore() - s1.getScore())
				// Limits the list to 3 entries (removes the lowest score in the list if the player beat someone)
				.limit(3)
				// Collects the objects into a list, which is casted to an ArrayList above
				.collect(Collectors.toList());
		
		// Sets the score string ArrayList using a stream of the score object list:
		highScores = (ArrayList<String>)scoreObjList.stream()
				// Maps each object to the toString value returned by each object ("[name],[score]")
				.map(s -> s.toString())
				// Collects each string to a list, which is cast to an ArrayList above
				.collect(Collectors.toList());
		
		try
		{
			// Writes the score strings to Scores.txt
			writeScores();
		}
		catch(Exception e)
		{
			// Prints the stack trace of any exceptions encountered
			e.printStackTrace();
		}
		
	}
	
	

}
