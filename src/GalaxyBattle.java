import javax.swing.*;
import java.awt.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.io.*;

public class GalaxyBattle extends JApplet {
	
	private Background mainPanel;						//The background panel
	private ShipFrame userShip;							//The user ship
	
	private Image shipImg, galaxyImg, weaponImg;					//Images

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
		galaxyImg = loadImage("Images/space.png")
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
		int ammo = 1;
		AxisLimit x = new AxisLimit(20,WIDTH-70);
		AxisLimit y = new AxisLimit(HEIGHT-100,HEIGHT-50);
		userShip = new ShipFrame(shipImg,ammo,true,weaponImg,(WIDTH/2),y.getEnd(),x,y,WIDTH,HEIGHT);
		userShip.getWeapon(0).setTime(12);
		userShip.getWeapon(1).setTime(12);
		userShip.getWeapon(2).setTime(12);
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
