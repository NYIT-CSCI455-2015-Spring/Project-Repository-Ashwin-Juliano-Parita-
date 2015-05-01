
/* ------------------------------------------------------------------											      
|Basic Information: This class extends  from JPanel and implements   
|						  Runnable					                           
|Purpose: 			  The purpose of this class is to create the       
|						  main menu of the game					               
|--------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class StartMenuPanel extends JPanel implements Runnable
{
	// Instance variables
	private Thread thread;													// Thread variable
	private final int WIDTH;												// Width of main panel
	private final int HEIGHT;												// Height of main panel
	private StartMenuItem button_container;									// Object for button container
	private StartMenuItem banner;											// Object for game banner
	private StartMenuItem startButton;										// Object for start button
	private StartMenuItem quitButton;										// Object for quit button
	private StartMenuItem scoresButton;										// Object for scored button
	private StartMenuItem levelComplete;									// Object for level complete
	private StartMenuItem gameOver;											// Object for game over 
	private AxisLimit bannerx, bannery;										// x-limit / y-limit of the banner
	private AxisLimit buttoncontainerx, buttoncontainery;					// x-limit / y-limit of the button container
	private int bannerWidth,bannerHeight;									// banner width and height
	private int containerWidth,containerHeight;								// container width and height
	
	/*------------------------------------------------------------
	| Constructor                                                 
	|------------------------------------------------------------
	| The constructor takes a thread; int values for the Height  
	|  and Width; startMenuItems for the button container, the    
	|  game banner, the start and quit button, the score button,  
	|  the level image, the game over image; the height and width 
	|  of the main game panel                                     
	|------------------------------------------------------------*/
	StartMenuPanel(Image start_btn,Image quit_img, Image scores_img, 
			Image container_img, Image banner_img, Image level_img 
			,Image gameover_img,int width, int height,GalaxyBattle main)
	{
		WIDTH           = width;												// Width of the main panel
		HEIGHT          = height;												// Height of the main panel
		bannerWidth     = 800;													// Width of the game banner
		bannerHeight    = 150;													// Height of the banner 
		containerWidth  = 300;													// Width of the container
		containerHeight = 70;													// Height of the container
		
		bannerx = new AxisLimit(-bannerWidth,135);					   			// x-axis limit
		bannery = new AxisLimit(200,100);										// y-axis limit
		buttoncontainerx = new AxisLimit(360,360);
		buttoncontainery = new AxisLimit(HEIGHT+containerHeight,250);
		// BANNER configuration
		banner = new StartMenuItem( banner_img, bannerWidth, bannerHeight, bannerx.getStart(),bannery.getStart());
		// CONTAINER configuration
		button_container = new StartMenuItem( container_img, containerWidth, containerHeight, buttoncontainerx.getStart(), buttoncontainery.getStart());
		// START button configuration
		startButton = new StartMenuItem( start_btn, 150, 45, 80, 15);	
		// SCORES button configuration
		//scoresButton = new StartMenuItem( scores_img, 150, 45, 80, 75);
		levelComplete = new StartMenuItem( level_img, 1000, 150, 290, 100);
		gameOver = new StartMenuItem( gameover_img, 1000, 150, 310, 100);
		
		// Addition of elements to the panel
		button_container.addMouseListener(new MenuMouseListener(main,this));
		add(banner);
		add(button_container);
		button_container.add(startButton);
		//button_container.add(scoresButton);
		add(levelComplete);
		add(gameOver);
		
		// Layout cofigurations
		setLayout(null);
		setOpaque(false);
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		levelComplete.setVisible(false);	
		gameOver.setVisible(false);		
	}
	
   /*------------------------------------------
	|  getStartButton Method				        
	|------------------------------------------	
	| Returns startMenuItem startButton        
	|------------------------------------------*/
	public StartMenuItem getStartButton()
	{
		return startButton;
	}	

   /*------------------------------------------
	|  getQuitButton Method			    	        
	|------------------------------------------	
	| Returns startMenuItem quitButton         
	|------------------------------------------*/
	public StartMenuItem getQuitButton()
	{
		return quitButton;
	}	
	
   /*------------------------------------------
	|  getScoreButton Method					     
	|------------------------------------------	
	| Returns startMenuItem scoredButton       
	|------------------------------------------*/
	public StartMenuItem getScoresButton()
	{
		return scoresButton;
	}	

   /*------------------------------------------
	|  getContainer Method						     
	|------------------------------------------	
	| Returns startMenuItem button container  
	|------------------------------------------*/
	public StartMenuItem getContainer()
	{
		return button_container;
	}
	
   /*------------------------------------------
	|  removeAllItems Method					     
	|------------------------------------------
	| This method removes all objects from     
	|  the start menu									  
	|------------------------------------------*/
	public void removeAllItems()
	{
		// Configures visibility
		startButton.setVisible(false);
		button_container.setVisible(false);
		banner.setVisible(false);
		//scoresButton.setVisible(false);
		levelComplete.setVisible(false);	
		gameOver.setVisible(false);
	}
	
   /*------------------------------------------
	|  levelComplete Method					        
	|------------------------------------------	
	| Increments the x coordinate              
	|------------------------------------------*/
	public void levelComplete()
	{
		// Configures visibility
		levelComplete.setVisible(true);
	}
	
   /*------------------------------------------
	|  gameOver Method						        
	|------------------------------------------	
	| Increments the x coordinate              
	|------------------------------------------*/
	public void gameOver()
	{
		// Configure visibility
		gameOver.setVisible(true);
                //button_container.setVisible(true);
		//startButton.setVisible(true);			  // Component not utilized
	}

   /*------------------------------------------
	|  Override Run Method			   	        
	|------------------------------------------	
	| This method configures the movement      
	|  of the banner and the button container   
	|  across the main panel screen. The speed  
	|  variable determines the sleep duration   
	|  of the thread						           
	|------------------------------------------*/
	public void run()
	{			
		int speed = 20;
		while(banner.getX() < bannerx.getEnd())
		{
			try
			{
				thread.sleep(speed);
			}catch(InterruptedException io){}			
			banner.movex(10);
		}
		while(button_container.getY() > buttoncontainery.getEnd())
		{
			try
			{
				thread.sleep(speed);
			}catch(InterruptedException io){}
			if((banner.getY()+banner.getHeight()) < button_container.getY())
			{
				button_container.movey(-10);
			}
			else
			{
				banner.movey(-10);
				button_container.movey(-10);
			}
		}
	}
	
   /*------------------------------------------
	|  animate Method							        
	|------------------------------------------	
	| The method creates a new thread and      
	|  starts it                                
	|------------------------------------------*/
	public void animate()
	{
		thread = null;
		thread = new Thread(this);
		thread.start();
	}
}