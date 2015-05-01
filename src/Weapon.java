
/* ------------------------------------------------------------------												      
|Basic Information: This class extends  from JPanel and implements   
|						  Runnable					                           
|Purpose: 			  The purpose of this class is to create and			
|					  manipulate the weapon the both the user and the  
|					  aliens will be fireing			                 
|--------------------------------------------------------------------*/

import javax.swing.*;

import java.awt.*;
import java.applet.*;

class Weapon extends JPanel implements Runnable{
	
	// Instance variables
        private AudioClip shootingClip;							// Audio clip for shots
	private Image img;											// Beam image. 
	private int pause =20;										// Pause between beam moves (in milliseconds)
	private final int PAUSE_MAX = 100;						// Maximum pause you can set
	private final int PAUSE_MIN = 5;							// Minimum pause you can set
	private int time = 25;										// Pause between beam moves (in milliseconds)
	private final int TIME_MAX = 100;						// Maximum pause you can set
	private final int TIME_MIN = 5;							// minimum pause you can set
	private int power = 1;									// How much damage can be done
	private final int POWER_MAX = 5;							// Defines the maximum power player can have
	private final int POWER_MIN = 1;							// Defines the minimum power player can have
	private boolean moving = false;							// Identifies whether it is moving
	private boolean killThread = false;						// Killthread set to false
	private boolean direction = true;						// true =  moving  up,  false =  moving down
	private String slot = ShipFrame.AMMO_SLOTS[0];		// position  on  the  ship 	
	private Thread thread;										// handles  the  movement  of  the  beam
	private boolean endPosition = false;					// Boolean endPosition set to false
	private final int WIDTH = 10;								// Width  of  the  beam's panel
	private final int HEIGHT = 10;							// Length  of  the  beam's panel
	private final int YBOUND;									// Width of the game's panel
	
	/*-------------------------------------------------
	|	CONSTRUCTOR												   
	|-------------------------------------------------*/
	/* The parameters necessary to initialize this		
	|	constructor are Image,boolean,string,int. 		
	|  The image passed will be drawn to the panel's		
	|	background; the boolean value will set the 		
	|	moving direction of the weapon; the String 		
	|	provides the slot location of the ship that the	
	|	weapon will be placed in; int provides the y 	
	|	boundaries of the game's main panel.				
	|																	
	|	The constructor will also set the panel layout  
	|	to null,focusable,opaque value to false, and 	
	|	visible to false.   										
	|-------------------------------------------------*/
	Weapon(Image i, AudioClip clip, boolean d,String s,int ybound) 
	{
		img = i;														// Sets image to i
		direction = d;                                                                                                  // Sets the direction to the direction passed
		slot = s;													// Sets the number of slots
		YBOUND = ybound;											// Sets the y-boundry
		thread = null;												// Initializes the thread to null
		shootingClip = clip;
                
		setSize(WIDTH,HEIGHT);									// Sets  the  weapons  panel  size
		setOpaque(false);											// Does not draw any background			
	   setLayout(null);											// set layout to none
		setVisible(false);										// visible by default so it will only appear when needed
	}
	
	/*-------------------------------------------------
	|	Weapon's Image Modifier Method						
	|-------------------------------------------------*/
	/*	This method replaces the current image to the   
	|  one being passed.											
	--------------------------------------------------*/
	public void setImage(Image i)
	{
		img = i;
	}

	/*-------------------------------------------------
	|	Weapon's setTime Method									
	|-------------------------------------------------*/
	/*	This method sets the time variable to the       
	|  int parameter that it is passed                 
	--------------------------------------------------*/	
	public void setTime(int t)
	{
		time = t;
	}
	
	/*-------------------------------------------------
	|	Weapon's increasePause Method							
	|-------------------------------------------------*/
	/*	This method increases the current paused time 	
	|	by 5 as long as it has not reached the maximum	
	|	limit.														
	--------------------------------------------------*/
	public void increasePause()
	{
		if(time < TIME_MAX)
		{
			time += 5;
		}
	}
	
	/*-------------------------------------------------
	|	Weapon's decreasePause Method					      
	|-------------------------------------------------*/
	/*	This method decreases the current paused time 	
	|	by 5 as long as it has not reached the minimum	
	|	limit.														
	|-------------------------------------------------*/
	public void decreasePause()
	{
		if(time > TIME_MIN)
		{
			time -= 5;
		}
	}
	
	/*-------------------------------------------------
	|	Weapon's Power++ Method					            
	|-------------------------------------------------*/
	/*	This method increases the current power value 	
	|	by 1 as long as it has not reached the maximum	
	|	limit.														
	|-------------------------------------------------*/
	public void increasePower()
	{
		if(power < POWER_MAX)
		{
			power++;
		}
	}
	
	/*-------------------------------------------------
	|	Weapon's Power-- Method									
	|-------------------------------------------------*/
	/*	This method decreases the current power value 	
	|	by 1 as long as it has not reached the minimum	
	|	limit.														
	|-------------------------------------------------*/
	public void decreasePower()
	{
		if(power < POWER_MIN)
		{
			power--;
		}
	}
	/*-------------------------------------------------
	|	Weapon's Direction Modifier   						
	|-------------------------------------------------*/
	/*	This method defines which direction the panel	
	|	should be moving. If true moves north, if false	
	|	moves south.												
	|-------------------------------------------------*/
	public void setDirection(boolean d)
	{	
		direction = d;
	}
	
	/*-------------------------------------------------
	|	Weapon's Slot Modifier									
	|-------------------------------------------------*/
	/*	This method changes the slot location of the		
	|	weapon. the passing value has to be of String	
	|	type with value of "center","left", or "right".	
	|-------------------------------------------------*/
	public void setSlot(String s)
	{
		slot = s;
	}
	
	/*-------------------------------------------------
	|	Weapon's Thread Assessor								
	|-------------------------------------------------*/
	/*	This method stops the thread  			         	
	|-------------------------------------------------*/
	public void killThread() 
	{
		if(isAlive())
		{
			thread = null;
			killThread = true;
		}
	}
	/*-------------------------------------------------
	|	Weapon's Thread Assessor							
	|-------------------------------------------------*/
	/*	This method returns the thread is null or not											|
	|-------------------------------------------------*/
	public boolean isAlive() 
	{
		if(thread == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public boolean isEndPosition()
	{
		return endPosition;
	}
	
	/*-------------------------------------------------
	|	Weapon's Speed Assessor           					
	|-------------------------------------------------*/
	/* Returns current pause value in millisecond.		
	|-------------------------------------------------*/
	public int getTime()
	{
		return time;
	}
	
	/*-------------------------------------------------
	|	Weapon's Slot Assessor           					
	|-------------------------------------------------*/
	/*	Returns current slot location(String)				
	|-------------------------------------------------*/
	public String getSlot()
	{
		return slot;
	}
	
	/*-------------------------------------------------
	|	Weapon's Direction Assessor         				
	|-------------------------------------------------*/
	/*	return moving direction of the weapon. True for	
	|	north, and false for south.							
	|-------------------------------------------------*/
	public boolean getDirection()
	{
		return direction;
	}
	
	/*-------------------------------------------------
	|	Weapon's Moving Check Assessor           			
	|-------------------------------------------------*/
	/* Returns if weapon is moving or not.					
	|-------------------------------------------------*/
	public boolean isMoving()
	{
		return moving;
	}
	
	/*-------------------------------------------------
	|	Weapon's Image Assessor           					
	|-------------------------------------------------*/
	/* Returns weapon current image.							
	|-------------------------------------------------*/
	public Image getImage() 
	{
		return img;
	}
	
	/*-------------------------------------------------
	|	Weapon's Move Method             					
	|-------------------------------------------------*/
	/*	Moves weapon by however many pixels it is being	
	|	passed. Passing value has to be int.				
	|-------------------------------------------------*/
	public void move(int space)
	{
		setLocation(getX(),getY()-space);
	}
	
	/*-------------------------------------------------
	|	Weapon's Shoot Method             					
	|-------------------------------------------------*/
	/* Prerequisite: thread has to be null.				
	|	Function: inializes thread, and call its run 	
	|	method, sets moving and visible to true.			
	|-------------------------------------------------*/
	public void shoot()
	{
		if(thread == null) 
		{
			thread = new Thread(this);
			thread.start();
			moving = true;
			setVisible(true);
                        shootingClip.play();
		}
	}
	
	/*-------------------------------------------------
	|	Weapon's Run Method              					
	|-------------------------------------------------*/
	/*	Presequesite: thread must be initialized, and	
	|	direction must be set to either false or true.	
	|																	
	|	Function: When called it will check if direction
	|	is either false or true. If true space will be	
	|	set to 10, else it will be set to -10.				
	|	While direction = true and the y position of	   
	|	this panel is less than this panel width(10), 	
	|	or direction = false, and this panel y position	
	|	is less than this panel width(10)+YBOUND. It 	
	|	will call move() and its parameter value will	
	|	be whatever the variable space is holding.		
	|																	
	|	After breaking out of the while loop,moving     
	|  will be set to false, thread will be set to     
	|  null, and panel visibility will be set	to false 												|
	|-------------------------------------------------*/
	public void run() 
	{
		int space;
		
		if(direction)
		{
			space = 5;
		}
		else
		{
			space = -5;
		}
		while(!killThread &&((direction && this.getY() > (this.getWidth()-(getHeight()+50))) || (!direction && this.getY() < (this.getWidth()+YBOUND))))
		{
			try{thread.sleep(time);}catch(InterruptedException ex){}
			this.move(space);
		}
		try{thread.sleep(pause);}catch(InterruptedException ex){}
		moving = false;
		thread = null;
		endPosition = true;
		setVisible(false);
		killThread = false;
	}
	
	/*--------------------------------------------------
	|	Weapon's Paint Method            					 
	|--------------------------------------------------*/
	/* This method will paint img to the panel.		    
	|--------------------------------------------------*/
	public void paintComponent(Graphics g) 
	{
		g = (Graphics2D) g;
		super.paintComponent(g);
		try{
			g.drawImage(img,0,0,this);
		}catch(NullPointerException ex){
			System.out.println(ex.getCause());
		}
	}	
}