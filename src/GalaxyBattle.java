import javax.swing.*;

import java.awt.*;
import java.net.*;

import javax.imageio.ImageIO;

import java.io.*;

public class GalaxyBattle extends JApplet {
	
	private Background mainPanel;						//The background panel
	private ShipFrame userShip;							//The user ship
	private ShipFrame[] alienShip;						//Array of ShipFrame to place the aliens in
	
	private Image shipImg, galaxyImg, weaponImg;		//Images
	private Image[]alienImg;							//Array of images for the aliens
	private Image[] explosionImgs;					    //Array of images for the explosion animation

	private Collision collision;						//Collision object to check for the collided objects
    private final int WIDTH = 1000;						//Width of the game panel
    private final int HEIGHT = 500;						//Height of the game panel		
   
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
		addUserShip();
		runMainPanel();	
		runGame();
   }
	
	/*--------------------------------------------------
	|	runGame()									    
	|--------------------------------------------------*/
	/*  Starts the game by loading the aliens in their   
	|	starting position. Adds the user to the screen   
	|	along with the aliens. Adds the menu to the 		 
	|	screen and runs the simulation of the game.      
	|--------------------------------------------------*/
	public void runGame()
	{		 
		loadAllienShips(5);
		addUserShip();	
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
		loadUser();
	}
	
	/*--------------------------------------------------
	|	runCollisionCheck()							          
	|--------------------------------------------------*/
	/* No parameter                                     
	|									                     	 
	|  Sets the collision to no null to kill previous	 
	|	collision checks, creates a new collision and  	 
	|	starts it.                              		    
	|--------------------------------------------------*/
	public void runCollisionCheck()
	{
		collision = null;
		collision = new Collision(this,userShip,alienShip,WIDTH,HEIGHT);
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
		shipImg = loadImage("Images/spaceship.png");
		weaponImg = loadImage("Images/beam.png");
		galaxyImg = loadImage("Images/space.png");
		alienImg = new Image[]{loadImage("Images/alien.png")};
		loadExplosionImages();
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
		AxisLimit x = new AxisLimit(20,WIDTH-70);
		AxisLimit y = new AxisLimit(HEIGHT-100,HEIGHT-50);
		userShip = new ShipFrame(shipImg,life,ammo,true,weaponImg,(WIDTH/2),y.getEnd(),x,y,WIDTH,HEIGHT,explosionImgs);
		userShip.getWeapon(0).setTime(12);
		userShip.getWeapon(1).setTime(12);
		userShip.getWeapon(2).setTime(12);
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
		int ammo = 1;
		int init_x = 400;
		int init_y = 100;
		AxisLimit x = new AxisLimit(0,1200);
		AxisLimit y = new AxisLimit(0,1200);
		
		for(int i = 0; i< alienShip.length;i++)
		{
			alienShip[i] = new ShipFrame(alienImg[0],life,ammo,false,weaponImg,init_x,init_y,x,y,WIDTH,HEIGHT,explosionImgs);
			init_x = init_x + 60;
		}
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
