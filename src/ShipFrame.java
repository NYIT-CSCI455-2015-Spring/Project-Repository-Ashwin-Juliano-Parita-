/* ------------------------------------------------------------------
|Basic Information: This class extends  from JPanel				                  
|Purpose: 			  The purpose of this class is to create and panel 
|						  with an image of a space ship drawn in it	
|--------------------------------------------------------------------*/

import javax.swing.*;

import java.awt.*;

class ShipFrame extends JPanel{
																												//AMMO_SLOTS.
	private AxisLimit xbound;					//Limits x-axis moves
	private AxisLimit ybound;					//Limits y-axis moves
	
	private Image shipImg;						//Holds ship image to be drawn in this panel
	private int life;							//Amount of hits it can take
	private boolean dead = false;				//if false means it is still alive

	private final int WIDTH = 40;				//This panel width
	private final int HEIGHT = 30;				//This panel height
	
	private final int INIT_SHIPLOCATION_X;		//Ship initial X location on the main panel
	private final int INIT_SHIPLOCATION_Y;		//Ship initial Y location on the main panel
	
	private final int MAIN_PANEL_WIDTH;			//hold the main panel dimensions
	private final int MAIN_PANEL_HEIGHT;		//same as above
	
	public final static String[] AMMO_SLOTS = new String[]{"center","left","right"}; //Holds options of weapon locations.
	private Point[] slotLocations;
	
	private Image beamImg;							//Holds weapon image to be passed to the weapon constructor
	public static final int MAX_AMMO = 3;		//Defines the maximum amount of ammo
	private Weapon[] weapon;						//Array of weapons to be added to the ship
	private int ammoIndex;							//Which weapon to be used
	private int ammo;								//current ammo limit
	private boolean direction = true;
	
	private Animation explosion;					//manipulates the explosion animation

	/*--------------------------------------------------
	|	CONSTRUCTOR													 
	|--------------------------------------------------						
	|																	
	|	The constructor will set  the  panel			
	|	layout to null, opaque value to false, and 		
	|	visibility to true.   									
	|--------------------------------------------------*/
	ShipFrame(Image sI, int shipLife, int shipAmmo, boolean d, Image bI, int initialX,int initialY, 
			AxisLimit x, AxisLimit y,int mainPanelWidth, int mainPanelHeight, Image[] explo)
   {
		shipImg = sI;
		beamImg = bI;
		
		xbound = x;
		ybound = y;
		
		MAIN_PANEL_WIDTH = mainPanelWidth;
		MAIN_PANEL_HEIGHT = mainPanelHeight;
		
		INIT_SHIPLOCATION_X = initialX;
		INIT_SHIPLOCATION_Y = initialY;
		
		life = shipLife;
		weapon = new Weapon[MAX_AMMO];
		ammo = shipAmmo;
		direction = d;
		
		setSize(WIDTH,HEIGHT);
		setLocation(INIT_SHIPLOCATION_X,INIT_SHIPLOCATION_Y);
		
		ammoIndex = 0;
		ammo = 3;
		
		explosion = new Animation(explo,20);
		
		slotLocations = new Point[3];
		this.loadWeapon();
		
		setLayout(null);
		setOpaque(false);
		setVisible(true);
	}
	
	/*--------------------------------------------------
	|	Life Incrementer											 
	|--------------------------------------------------*/
	/*	This method increments life by 1.					
	---------------------------------------------------*/
	public void increaseLife()
	{
		life++;
	}
	
	/*--------------------------------------------------
	|	Life Decrementer											 
	|--------------------------------------------------*/
	/*	decreseases life by 1 if life isn't 0, when life
	|	reaches 0, it will set ship  to dead, and play	
	|	animation and audio.										
	---------------------------------------------------*/
	public void decreaseLife()
	{
		if(life > 0)
		{
			life--;
		}
		if(life == 0)
		{
			dead = true;
			this.repaint();
			this.add(explosion);
			explosion.startAnimation();
		}
	}
	
	/*--------------------------------------------------
	|	Life Accessor											 
	|--------------------------------------------------*/
	/*	Gets the life of the ship									
	---------------------------------------------------*/
	public int getLife()
	{
		return life;
	}
	
	/*--------------------------------------------------
	|	Dead Accessor											 
	|--------------------------------------------------*/
	/*	Checks if ship is dead										
	---------------------------------------------------*/
	public boolean isDead()
	{
		return dead;
	}
	
	/*-------------------------------------------------
	|	Image Modifier								 
	|--------------------------------------------------
	|	This method replace the current image to the one
	|	being passed.												
	---------------------------------------------------*/
	public void setImage(Image i)
	{
		shipImg = i;
	}

	/*-------------------------------------------------
	|  Image Accessor           					 
	|--------------------------------------------------
	| returns current image.							
	---------------------------------------------------*/
	public Image getImage()
	{
		return shipImg;
	}


	/*--------------------------------------------------
	|	SpaceShip's INIT_SHIPLOCATION_X Accessor           					 
	|--------------------------------------------------
	| returns ship x initial location						
	---------------------------------------------------*/
	public int getInit_ShipLocation_X()
	{
		return INIT_SHIPLOCATION_X;
	}
	
	/*--------------------------------------------------
	|	SpaceShip's INIT_SHIPLOCATION_Y Accessor           					 
	|--------------------------------------------------
	| returns ship y initial location						
	---------------------------------------------------*/
	public int getInit_ShipLocation_Y()
	{
		return INIT_SHIPLOCATION_Y;
	}
	
	/*--------------------------------------------------
	|	MAX_AMMO Assessor           				 			 
	|--------------------------------------------------*/
	/* returns maximum ammo value allowed					
	---------------------------------------------------*/
	
	public int getMax_Ammo()
	{
		return MAX_AMMO;
	}
	/*--------------------------------------------------
	|	Weapon Assessor           				 				 
	|--------------------------------------------------*/
	/* Prerequisite: index passed must be less than 	 
	|	MAX_AMMO.													 
	|	returns weapon corresponding to the index passed 
	---------------------------------------------------*/
	public Weapon getWeapon(int index)
	{
		if(index < MAX_AMMO)
		{
			return weapon[index];
		}
		else
		{
			System.out.println("ERROR: weapon index out of boundaries.");
			return null;
		}
	}
	
	/*--------------------------------------------------
	|	Weapon[] Assessor           				 			
	|--------------------------------------------------*/
	/* returns array of weapons      						
	---------------------------------------------------*/
	public Weapon[] getWeapon()
	{
		return weapon;
	}
	
	/*--------------------------------------------------
	|	ammoIndex Assessor           				 			
	|--------------------------------------------------*/
	/* returns ammoIndex			      						
	---------------------------------------------------*/
	public int getAmmoIndex()
	{
		return ammoIndex;
	}
	
	/*--------------------------------------------------
	|	ammo Assessor           				 			
	|--------------------------------------------------*/
	/* returns ammo			      						
	---------------------------------------------------*/
	public int getAmmo()
	{
		return ammo;
	}

	/*--------------------------------------------------
	|	moveLeft Location Method	           		     	
	|---------------------------------------------------
	| Change the ship's X location based on the space 
	|	value being passed. Thus moving ship to the     
	|	left.				      									
	---------------------------------------------------*/
	public void moveLeft(int space)
	{
		if(getX() >= xbound.getStart())
		{
			setLocation(getX()-space,getY());
		}
	}
	
	/*--------------------------------------------------
	|	moveRight  Location Method	           		     	
	|--------------------------------------------------
	| Change the ship's X location based on the space
	|	value being passed. Thus moving ship to the     
	|	right.				      									
	---------------------------------------------------*/
	public void moveRight(int space)
	{
		if(getX() < xbound.getEnd())
		{
			setLocation(getX()+space,getY());
		}
	}
	
	/*-------------------------------------------------
	|	moveUp  Location Method	           		     	
	|--------------------------------------------------
	| Change the ship's Y location based on the space 
	|	value being passed. Thus moving ship to the     
	|	up.				      									
	---------------------------------------------------*/
	public void moveUp(int space)
	{
		if(getY() > ybound.getStart())
		{
			setLocation(getX(),getY()-space);
		}
	}
	/*--------------------------------------------------
	|	moveDown  Location Method	           		     	
	|--------------------------------------------------
	| Change the ship's X location based on the space 
	|	value being passed. Thus moving ship to the     
	|	down.				      									
	---------------------------------------------------*/
	public void moveDown(int space)
	{
		if(getY() < ybound.getEnd())
		{
			setLocation(getX(),getY()+space);
		}	
	}
	
	/*--------------------------------------------------
	|	loadWeapon Method	           		     				
	|--------------------------------------------------*/
	/* when called initialize this class's array of 	
	|	weapons.	      											
	---------------------------------------------------*/
	private void loadWeapon() {
		for(int i =0; i<weapon.length;i++)
		{
			weapon[i] = new Weapon(beamImg,direction,AMMO_SLOTS[i%AMMO_SLOTS.length],MAIN_PANEL_HEIGHT);
		}
	}
	
	/*--------------------------------------------------
	|	increaseAmmo Method	           		     			 
	|--------------------------------------------------*/
	/*	Prerequisite: ammo must be less than MAX_AMMO	
	|	when called ammo will increase by 1 if limit	
	|	has not been met yet.										      											|
	---------------------------------------------------*/
	public void increaseAmmo()
	{
		if(ammo < MAX_AMMO)
		{
			 ammo++;
		}
	}
	
	/*--------------------------------------------------
	|	decreaseAmmo Method	           		     			 
	|--------------------------------------------------*/
	/*	Prerequisite: ammo must be less than MAX_AMMO	
	|	when called ammo will increase by 1 if limit	
	|	has not been met yet.										
	---------------------------------------------------*/
	public void decreaseAmmo()
	{
		if(ammo > 0)
		{
			ammo--;
		}
	}
	
	/*--------------------------------------------------
	|	loadSlot Method	           		     			 
	|--------------------------------------------------*/
	/*	  This function will move the weapon panel north 
	|	or south depending on the direction value.                   									|	
	---------------------------------------------------*/
	public void loadSlot(int weaponIndex)
	{
		slotLocations = new Point[]{new Point(getX()+18,getY()-1),
		     new Point(getX()+4,getY()-1),new Point(getX()+30,getY()-1)};
			  
		weapon[weaponIndex].setLocation(slotLocations[findSlot(slotLocations,weaponIndex)]);
			  
	}
	
	/*--------------------------------------------------
	|	findSlot Method	  	         		     			 
	|--------------------------------------------------*/
	/*	  This function will return which slot position	
	|	the weapon index is supposed  to be positioned	
	|	on the ship											           									|	
	---------------------------------------------------*/
	public int findSlot(Point[] slotL,int wI)
	{
		int slotIndex = 0;
		
		for(int i =0; i<slotL.length;i++)
		{
				if((weapon[wI].getSlot()).equals(AMMO_SLOTS[i]))
				{
					slotIndex = i;
				}
		}
		return slotIndex;
	}
	
	/*--------------------------------------------------
	|	shootWeapon Method										 
	|--------------------------------------------------*/
	/*	This method fires the designated weapon index   
	---------------------------------------------------*/
	public void shootWeapon()
	{	
		if(ammoIndex == ammo)
		{
			ammoIndex = 0;
		}
		
		ammoIndex = ammoIndex%ammo;
		if(!weapon[ammoIndex].isAlive())
		{
			loadSlot(ammoIndex);
			weapon[ammoIndex].shoot();
			ammoIndex++;
		}
	}
	
	/*-------------------------------------------------
	|	Paint Method 				         					 
	|--------------------------------------------------
	| This  method  will paint img to the panel.		 
	---------------------------------------------------*/
	public void paintComponent(Graphics g) 
	{
		g = (Graphics2D)g;
		super.paintComponent(g);
		if(!dead)
		{
			try{
				g.drawImage(shipImg,0,0,this);
			}catch(NullPointerException ex){
				System.out.println(ex.getCause());
			}
		}
	}
}