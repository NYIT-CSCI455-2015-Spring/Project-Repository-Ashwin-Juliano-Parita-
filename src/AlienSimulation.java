

/* ------------------------------------------------------------------											
|Basic Information: This class extends  from Thread and uses an array
|						  of ShipFrame objects.					               
|Purpose: 			  The purpose of this class is to animate the      
|						  aliens in each level and also crate algorimths	
|						  to change how often each alien should shoot.		
|--------------------------------------------------------------------*/

//Import all of the necessary classes
import java.awt.*;
import java.util.Random;

class AlienSimulation extends Thread {
	
	//Declare all of the variables of the class
	private ShipFrame ship = null;					// The ship being controlled by the player
	private Weapon []shipWeapon = null;				// The weapon associated with the ship
	private ShipFrame[] aliens = null;				// The array of aliens to be animated
	private Weapon[] aliensWeapon = null;			// The array of alien wapons
	private int randomIndex =0;						
	private int level = 0;								//The level of the animation
	private Random generator = new Random();
	private final int PANEL_WIDTH;					//The width of the entire game frame
	private final int PANEL_HEIGHT;					//The height of the entire game frame
	private Boolean left = true;						//Boolean to check alien position status
	private Boolean right = true;						//Boolean to check alien position status
	private int difficulty = 0;						//The difficulty of the level
	
	/*--------------------------------------------------
	|	CONSTRUCTOR													 |
	|--------------------------------------------------
	| The parameters nessesary to initialize this		
	|	constructor are ShipFrame,ShipFrame[],int,int, 	
	|  int,Level								               
	|								   
	|--------------------------------------------------*/

	AlienSimulation(ShipFrame s,ShipFrame[] a,int w,int h,int motion, Level dif)
	{
		ship = s;
		shipWeapon = ship.getWeapon();
		aliens = a;
		PANEL_WIDTH = w;
		PANEL_HEIGHT = h;
		level = motion;
		difficulty = dif.getDifficulty();
	}
	
	/*--------------------------------------------------
	|	simulationOne(int,int)									 |
	|--------------------------------------------------
	| The parameters for this function are int, int.   
	|									                     	 |
	|	The First int type data determines the speed in  
	|	which the alien will move                        
	|	The seccond int type date will determine the     
	|	frequency in which the alien will shoot.	   	 
	|--------------------------------------------------*/
	public void simulationOne(int alienSpeed, int shootFrequency)
	{
		//For all the aliens in the array
		for(int i = 0;i<aliens.length;i++)
		{
			//If the alien furtherst in the right is within 100 pixels from the right end of the panel
			if((aliens[aliens.length-1].getX()<=1000 && aliens[aliens.length-1].getX()>=900))
			{
				left=true; 																
				right=false;
			}
			else if(aliens[0].getX()>=0 && aliens[0].getX()<=100)
			{
				left=false;
				right=true;
			}
			else
			{/* The bolleans remain the same so aliens will keep moving */}
			
			//If left is true and right is false call the moveLeft() function 
			//for each alien in the array so they will all move together.
			if(left==true && right==false)
			{
				int a=0;
				do{
					aliens[a].moveLeft(alienSpeed); //Move at speed passed to the function
					a++;
				}while(a<=aliens.length-1);
			}
			
			//If left is false and right is true call the moveRight() function 
			//for each alien in the array so they will all move together.
			else if(left==false && right==true)
			{
				int t=0;
				do{
					aliens[t].moveRight(alienSpeed); //Move at speed passed to the function
					t++;
				}while(t<=aliens.length-1);
			}
			
			//When the game starts there are both true so start moving to the right
			else if(left==true && right==true)
			{
				int t=0;
				do{
					aliens[t].moveRight(alienSpeed); //Move at speed passed to the function
					t++;
				}while(t<=aliens.length-1);
			}
			
			/*If the alien is alive and the alien is visible to the player and it is within the
			shootFrequency distace from the ship. The alien at the current index can shoot.*/
			if(aliens[i].getLife()!=0 && aliens[i].getX()-ship.getX() <= shootFrequency
												  && aliens[i].getX()-ship.getX() >= -shootFrequency
												  && aliens[randomIndex].getX()<1000)
			{
				aliens[i].shootWeapon(); //Shoot weapon
			}

			
			try
			{
				sleep(100);
			}catch(InterruptedException ex){}
		}
	}
	
	
	/*--------------------------------------------------
	|	initialPosition(int)									    |
	|--------------------------------------------------
	| The parameters for this function are int.        
	|									                     	 |
	|	The int type data is the simulation the the		 
	|  the alien should have prior to the beginnig      
   |	of the level.	   	 
	|--------------------------------------------------*/
	public void initialPosition(int option)
	{
		

		//If the option is 0.
		
		if(option == 0)
		{
			for(int i = 0; i< aliens.length;i++)
			{
				if(i>=0 && i<3)
				{aliens[i].setLocation(aliens[i].getX(),aliens[i].getY()+(i%3*(50)));}
				else if(i>=3 && i<6)
				{aliens[i].setLocation(aliens[i].getX()+50,aliens[i].getY()+(i%3*(50)));}
				else if(i>=6 && i<9)
				{aliens[i].setLocation(aliens[i].getX()+100,aliens[i].getY()+(i%3*(50)));}
				else if(i>=9 && i<12)
				{aliens[i].setLocation(aliens[i].getX()+150,aliens[i].getY()+(i%3*(50)));}
				else if(i>=12 && i<15)
				{aliens[i].setLocation(aliens[i].getX()+200,aliens[i].getY()+(i%3*(50)));}
				else if(i>=15 && i<18)
				{aliens[i].setLocation(aliens[i].getX()+250,aliens[i].getY()+(i%3*(50)));}
				else if(i>=18 && i<21)
				{aliens[i].setLocation(aliens[i].getX()+300,aliens[i].getY()+(i%3*(50)));}
				else if(i>=21 && i<24)
				{aliens[i].setLocation(aliens[i].getX()+350,aliens[i].getY()+(i%3*(50)));}
				else if(i>=24 && i<27)
				{aliens[i].setLocation(aliens[i].getX()+400,aliens[i].getY()+(i%3*(50)));}
				else if(i>=27 && i<30)
				{aliens[i].setLocation(aliens[i].getX()+450,aliens[i].getY()+(i%3*(50)));}
				else if(i>=30 && i<33)
				{aliens[i].setLocation(aliens[i].getX()+500,aliens[i].getY()+(i%3*(50)));}
				else if(i>=33 && i<36)
				{aliens[i].setLocation(aliens[i].getX()+550,aliens[i].getY()+(i%3*(50)));}
				else if(i>=24 && i<27)
				{aliens[i].setLocation(aliens[i].getX()+600,aliens[i].getY()+(i%3*(50)));}
				else if(i>=27 && i<30)
				{aliens[i].setLocation(aliens[i].getX()+650,aliens[i].getY()+(i%3*(50)));}
				else if(i>=30 && i<33)
				{aliens[i].setLocation(aliens[i].getX()+700,aliens[i].getY()+(i%3*(50)));}
				else if(i>=33 && i<36)
				{aliens[i].setLocation(aliens[i].getX()+750,aliens[i].getY()+(i%3*(50)));}

			}
		}
		
	
	}

	//The task given to the thread when it is invoked with the start method.
	//Depending of the level passed to the object call the animation that 
	//corresponds to the level with its speed and shootFrequency.
	public void run()
	{
		System.out.println(aliens.length);
		initialPosition(0);            //Load the aliens to have the correct initail position for the simulation of the level
		
		while(true)
		{

			simulationOne(1+difficulty,50+difficulty*2);  //Difficulty increases every level.
		}

	}
}