iimport javax.swing.*;

import java.awt.*;
import java.net.*;

import javax.imageio.ImageIO;

import java.io.*;

public class GalaxyBattle extends JApplet {
	
	private Background mainPanel;						//The background panel
	private StartMenuPanel menuPanel;					//The start menu
	private ShipFrame userShip;							//The user ship
	private ShipFrame[] alienShip;						//Array of ShipFrame to place the aliens in
	
	private Image shipImg, galaxyImg, weaponImg,		//Images		
	start_btn, container_img, banner_img, quit_img, 
	scores_img, level_img, gameover_img, scoreImg;
	private Image[]alienImg;							//Array of images for the aliens
	private Image[] explosionImgs;					    //Array of images for the explosion animation
	private AlienSimulation alienSimulation;		//Simulation object to handle the simulation of the aliens

	private Collision collision;						//Collision object to check for the collided objects
    private final int WIDTH = 1000;						//Width of the game panel
    private final int HEIGHT = 500;						//Height of the game panel	
    
    private Level level;								//Level object to represent the level the user is on
    private Player userInfo;							//Player object to keep track of the player scores and info
	private GameStatus statusPanel;						//The status panel that keeps track of everything
	private AnimatedPanel scorePanel;					//The animated panel that animates the panel
	
	/*--------------------------------------------------
	|	init()									        
	|---------------------------------------------------
	| No parameters                                    
	|									                
	|  Loads objects for the game                       
	|--------------------------------------------------*/
	public void init()
	{
		loadMainObjects();
		runMainPanel();	
		runMenuPanel();	
   }
	
	/*--------------------------------------------------
	|	runGame(int level)									    
	|--------------------------------------------------
	|  Starts the game by loading the aliens in their   
	|	starting position. Adds the user to the screen   
	|	along with the aliens. Adds the menu to the 		 
	|	screen and runs the simulation of the game.      
	|--------------------------------------------------*/
	public void runGame(int lev)
	{	
		loadAllienShips(8+level.getDifficulty());
		addUserShip();
		statusPanel.refreshAll();
		statusPanel.refreshLevel();
		runSimulation(lev);
		runCollisionCheck();
		addAliens();
	}
	
	/*-------------------------------------------------
	|	loadMainObjects()								
	|--------------------------------------------------
	| No Parameters                                    
	|									                
	|  Loads all the images, songs, main panel,         
	|	menu panel and the user information			    
	|--------------------------------------------------*/
	public void loadMainObjects()
	{
		loadImages();
		loadMainPanel();
		loadMenuPanel();
		loadUser();
	}
	
	/*--------------------------------------------------
	|	runSimulation(int lev)							       
	|--------------------------------------------------
	| Takes in an int as a parameter                   
	|									                     	 
	|  Sets the simulation no null to kill previous	    
	|	simulation, creates a new simulation and  		 
	|	starts it.                              		    
	|--------------------------------------------------*/
	public void runSimulation(int lev)
	{
		alienSimulation = null;
		alienSimulation = new AlienSimulation(userShip, alienShip, WIDTH,HEIGHT,lev,level);
		alienSimulation.start();
	}
	
	/*--------------------------------------------------
	|	runCollisionCheck()							          
	|--------------------------------------------------
	| No parameter                                     
	|									                     	 
	|  Sets the collision to no null to kill previous	 
	|	collision checks, creates a new collision and  	 
	|	starts it.                              		    
	|--------------------------------------------------*/
	public void runCollisionCheck()
	{
		collision = null;
		collision = new Collision(userInfo,this,statusPanel,userShip,alienShip,level,WIDTH,HEIGHT,menuPanel);
		collision.start();
	}
	
	/*--------------------------------------------------
	|	loadExplosionImages()							       
	|--------------------------------------------------*/
	/* No parameter                                     
	|									                     	 
	|  Loads the images necessary for the explosion    
	|	animation.                              		    
	|--------------------------------------------------*/
	public void loadExplosionImages()
	{
		int nimgs = 16;
		explosionImgs = new Image[nimgs];
		for(int i = 0; i< nimgs; i++)
		{
			String path = "Images/Explosion/exp"+(i+1)+".png";
			explosionImgs[i] = loadImage(path);
		}
	}
	
	/*--------------------------------------------------
	|	loadImages()							        
	|---------------------------------------------------
	| No parameter                                     
	|									                
	|  Loads the images necessary to create the game    
	|--------------------------------------------------*/
	public void loadImages()
	{
		scoreImg = loadImage("Images/scoreback.png");
		galaxyImg = loadImage("Images/space.png");
		shipImg = loadImage("Images/spaceship.png");
		weaponImg = loadImage("Images/beam.png");
		alienImg = new Image[]{loadImage("Images/alien.png"),loadImage("Images/alien2.png"),loadImage("Images/alien3.png")};
		
		start_btn = loadImage("Images/start_btn.png");
		quit_img = loadImage("Images/quit_img.png");
		container_img = loadImage("Images/btn_container.png");
		banner_img = loadImage("Images/game_banner.png");
		scores_img = loadImage("Images/scores.png");
		level_img = loadImage("Images/levelup.png");
		gameover_img = loadImage("Images/go.png");
		loadExplosionImages();
	}
	
	/*--------------------------------------------------
	|	loadScoresPanel()									       
	|--------------------------------------------------
	| No Parameters                                    
	|									                     	 
	|  Creates an score panel to display the high       
	|  scores and adds it to the main panel.		       
	|--------------------------------------------------*/
	public void loadScoresPanel()
	{
		if(scorePanel == null)
		{
			scorePanel = new AnimatedPanel(scoreImg,400,400,10,10);
		}
		menuPanel.add(scorePanel);
	}
	
	/*--------------------------------------------------
	|	openScores(boolean clicked)							 
	|--------------------------------------------------
	| Takes in a boolean as theparameter               
	|									                     	 
	|  When the the scores button is cliked it displays 
	|	the high scores of the game. Else closes it		 
	|--------------------------------------------------*/
	public void openScores(boolean clicked)
	{
		if(!clicked)
		{
			loadScoresPanel();
			loadScoresPanel();
			scorePanel.animate();
			scorePanel.setVisible(true);
		}
		else
		{
			closeScores();
		}
	}
	
	/*--------------------------------------------------
	|	closeScores()							                
	|--------------------------------------------------
	| No parameter                                     
	|									                     	 
	|  Sets the visibility of the score panel to false  
	|	and closes it                              		 
	|--------------------------------------------------*/
	public void closeScores()
	{
		scorePanel.setVisible(false);
		scorePanel = null;
	}

	/*--------------------------------------------------
	|	loadMainPanel()							             
	|---------------------------------------------------
	| No parameter                                     
	|									                     	 
	|  Loads the main panel of the game which is the    
	|	the screen were the animations and simulations    
	|	will happen                                      
	|--------------------------------------------------*/
	public void loadMainPanel()
	{
		mainPanel = new Background(galaxyImg, WIDTH,HEIGHT);
	}
	
	/*--------------------------------------------------
	|	loadMenuPanel()							             
	|--------------------------------------------------
	| No parameter                                     
	|									                     	 
	|  Loads the menu prior to the game's beggining     
	|--------------------------------------------------*/
	public void loadMenuPanel()
	{
		menuPanel = new StartMenuPanel(start_btn, quit_img, scores_img, container_img, banner_img,level_img,gameover_img,
												 WIDTH,HEIGHT,this);
	}
	
	/*--------------------------------------------------
	|	loadUser()							                   
	----------------------------------------------------
	| No parameter                                     
	|									                     	 
	|  Loads the ship that the user will have control   
	|	over and sets its settings as well as the settings
	|	of its weapon such as speed of shots and power   
	|--------------------------------------------------*/
	public void loadUser()
	{
		int life = 5;
		int ammo = 1;
		int points = 30;
		AxisLimit x = new AxisLimit(20,WIDTH-70);
		AxisLimit y = new AxisLimit(HEIGHT-100,HEIGHT-50);
		userShip = new ShipFrame(shipImg,life,points,ammo,true,weaponImg,(WIDTH/2),y.getEnd(),x,y,WIDTH,HEIGHT,explosionImgs);
		userShip.getWeapon(0).setTime(12);
		userShip.getWeapon(1).setTime(12);
		userShip.getWeapon(2).setTime(12);				
		level = null;
		level = new Level(alienShip);
		level.setLevel(0);
		
		userInfo = new Player("Java",userShip,level);
	}
	
	/*--------------------------------------------------
	|	loadAllienShips(int length)							 
	|--------------------------------------------------*/
	/* Takes in an int as a parameter                   
	|									                     	 
	|  Loads all of the alien ships with their settings 
	|	such as life, how many points they are worth,    
	|	their ammo and initial position.					    
	|--------------------------------------------------*/
	public void loadAllienShips(int length)
	{
		alienShip = null;
		alienShip = new ShipFrame[length];
		int life = 1;
		int points = 30;
		int ammo = 1;
		int init_x = 400 + level.getLevel()*10;
		int init_y = 100 + level.getLevel()*10;
		AxisLimit x = new AxisLimit(0,1200);
		AxisLimit y = new AxisLimit(0,1200);
		
		for(int i = 0; i< alienShip.length;i++)
		{
			alienShip[i] = new ShipFrame(alienImg[0],life,points,ammo,false,weaponImg,init_x,init_y,x,y,WIDTH,HEIGHT,explosionImgs);
			init_x = init_x + 60;
		}
		
		System.out.println("Here");
	}
	
	/*--------------------------------------------------
	|	addUserShip()							                
	|--------------------------------------------------
	| No parameter                                     
	|									                     	 
	|  Adds the user ship to the screen                 
	|--------------------------------------------------*/
	public void addUserShip()
	{
		mainPanel.add(userShip);
		userShip.moveLeft(1);
		for(int i = 0; i<(userShip.getWeapon()).length;i++)
		{
			mainPanel.add(userShip.getWeapon()[i]);
		}
	}
	
	/*--------------------------------------------------
	|	addAliens()							                
	|--------------------------------------------------*/
	/* Adds all the aliens to the screen                
	|--------------------------------------------------*/
	public void addAliens()
	{ 		
		System.out.println("Adding Aliens");
		for(int i = 0; i<alienShip.length;i++)
		{
			for(int b = 0;b<alienShip[i].getWeapon().length;b++)
			{
				mainPanel.add(alienShip[i]);
				mainPanel.add(alienShip[i].getWeapon()[b]);
			}
		}         
	}
	
	/*--------------------------------------------------
	|	statusBar()									             
	|--------------------------------------------------
	| No Parameters                                    
	|									                     	 
	|  Creates a statusPanel and adds it to the main	 	
	|	panel			      										 
	|--------------------------------------------------*/
	public void statusBar()
	{
		statusPanel = new GameStatus(level,userInfo,10,10);
		mainPanel.add(statusPanel);
	}
	
	/*--------------------------------------------------
	|	runMainPanel()							                
	|--------------------------------------------------
	| No parameter                                     
	|									                     	 
	|  Adds the key listener to the game so the user	 
	|	can control the ship. Sets the size of the 		 
	|	games screen.             
	|--------------------------------------------------*/
	public void runMainPanel()
	{
		mainPanel.addKeyListener(new MyKeyListener(userShip));
        setSize(WIDTH,HEIGHT);
        setLayout(new BorderLayout());
		getContentPane().add(mainPanel,BorderLayout.CENTER);
	}
	
	/*--------------------------------------------------
	|	runMenuPanel()							                
	|--------------------------------------------------
	| No parameter                                     
	|									                     	 
	|  Adds the menu to the screen in an animated       
	|  fashion                                          
	|--------------------------------------------------*/
	public void runMenuPanel()
	{
		mainPanel.add(menuPanel);
		menuPanel.animate();
	}
	
	
	/*--------------------------------------------------
	|	loadImage(string p)							         
	|--------------------------------------------------
	| Takes in a string p                              
	|									                     	 
	|  Loads the images to be used in an applet by      
	|	getting the code base.                           
	|--------------------------------------------------*/
	public Image loadImage(String p) 
	{
       Image i = null;
       try{
	       i = ImageIO.read(new URL(getCodeBase(),p));
       }
       catch(MalformedURLException ex){
 	      System.out.print(ex.getCause());
       }
       catch(IOException ex){
               System.out.print(ex.getCause());
       }
       finally {
           System.out.println("Image Path: "+p+" - Loaded.");
       }
       return i;
  }
	
}
