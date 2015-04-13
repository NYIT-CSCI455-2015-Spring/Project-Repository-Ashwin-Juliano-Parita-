
/* ------------------------------------------------------------------											
|Basic Information: This class extends  from Thread	               
|Purpose: 			  The purpose of this class is implement collision
|						  rules to the game, giving both the machine aliens
|						  and the user ability to kill each other.				
|--------------------------------------------------------------------*/


import java.awt.*;

class Collision extends Thread {
	
	private Player player;						//Holds Player object values
	private GalaxyBattle main;					//holds main frame values
	private ShipFrame userShip = null;			//holds user ship's values
	private Weapon[] userWeapon = null;			//holds user weapon's values
	private ShipFrame[] alienShip = null;		//holds aliens ship's values
	private Weapon[][] alienWeapon = null;		//holds aliens weapon's values
	private final int MAX_AMMO;					//holds maximum possible ammo
	private Level level;						//holds game level information
	private final int PANEL_WIDTH;				//main frame dimensions
	private final int PANEL_HEIGHT;				//main frame dimensions
	private GameStatus statusPanel;				//holds statusPanel values
	private StartMenuPanel lvlCmplt;			//start panel

	/*--------------------------------------------------
	|	Constructor													
	|--------------------------------------------------
	|	This is used to initialize all  the  required	
	|	data needed to  run  the collision thread.		
	---------------------------------------------------*/
	Collision(Player p,GalaxyBattle t,GameStatus gs,ShipFrame s,ShipFrame[] a,Level l,int w,int h,StartMenuPanel item)
	{
		player = p;
		main = t;
		statusPanel = gs;
		userShip = s;
		alienShip = a;
		level = l;
		loadWeapons();
		MAX_AMMO = ShipFrame.MAX_AMMO;
		PANEL_WIDTH = w;
		PANEL_HEIGHT = h;
		lvlCmplt = item;
	}
	
	/*--------------------------------------------------
	|	loadWeapon method											 
	|--------------------------------------------------*/
	/*	This method initialized all weapon values to		
	|	this class members for both user and aliens.		
	---------------------------------------------------*/
	public void loadWeapons()
	{
		userWeapon = userShip.getWeapon();
		alienWeapon = new Weapon[alienShip.length][MAX_AMMO];
		for(int a = 0; a< alienWeapon.length; a++)
		{
				alienWeapon[a] = alienShip[a].getWeapon() ; 
		}
	}
	
	/*--------------------------------------------------
	|	checkUserHit method										 
	|--------------------------------------------------*/
	/*	This method check if user has been by calling 	
	|	the method isUserHit(), if true decreases user's
	|	life refresh user status panel, and reloads 		
	|	alien's weapon.											
	---------------------------------------------------*/
	public void checkUserHit(int alienShipIndex,int alienWeaponIndex)
	{
		if(!userShip.isDead())
		{
			boolean hit = isUserHit(alienShipIndex,alienWeaponIndex);
			
			if(hit)
			{
				alienWeapon[alienShipIndex][alienWeaponIndex].setVisible(false);
				alienWeapon[alienShipIndex][alienWeaponIndex].killThread();
				alienShip[alienShipIndex].loadSlot(alienWeaponIndex);
				userShip.decreaseLife();
				try{sleep(alienWeapon[alienShipIndex][alienWeaponIndex].getTime()*5);}catch(InterruptedException ie){}				
			}
		}
	}	
	
	/*--------------------------------------------------
	|	isUserHit method											 
	|--------------------------------------------------*/
	/*	This method check the position of the  alien 	
	|	index's weapon based on which  weapon and alien 
	|	indexes is being passed, and return true if		
	|  it's weapon index is in range of the user ship,	
	|	else returns false.										
	---------------------------------------------------*/
	public boolean isUserHit(int alienShipIndex,int alienWeaponIndex)
	{
		boolean hit;
		AxisLimit userXLimit = new AxisLimit(userShip.getX(),userShip.getX()+userShip.getWidth());
		AxisLimit userYLimit = new AxisLimit(userShip.getY()-userShip.getHeight(),userShip.getY());
		Point alienWeaponLocation = new Point(alienWeapon[alienShipIndex][alienWeaponIndex].getX()
														,alienWeapon[alienShipIndex][alienWeaponIndex].getY());
		

		if((alienWeaponLocation.getX() >= userXLimit.getStart() && alienWeaponLocation.getX() <= userXLimit.getEnd()) 
			&& (alienWeaponLocation.getY() >= userYLimit.getStart() && alienWeaponLocation.getY() <= userYLimit.getEnd()))
		{
				hit = true;
				if(userShip.getLife()==1)
				{lvlCmplt.gameOver();}
		}
		else
		{
				hit = false;
		}
		
		return hit;
	}
	
	/*--------------------------------------------------
	|	isAlienHit method											 
	|--------------------------------------------------*/
	/*	This method check the position of the  user	 	
	|	index's and return true if it's weapon index 	
	|	is in range of the alienShipIndex being passed,	
	|	else return false.										
	---------------------------------------------------*/
	public boolean isAlienHit(int alienShipIndex, int userWeaponIndex)
	{
		boolean hit;
		AxisLimit alienXLimit = new AxisLimit(alienShip[alienShipIndex].getX(),alienShip[alienShipIndex].getX()+alienShip[alienShipIndex].getWidth());
	
		AxisLimit alienYLimit = new AxisLimit(alienShip[alienShipIndex].getY(),alienShip[alienShipIndex].getY()+alienShip[alienShipIndex].getHeight());
		Point weaponLocation = new Point(userWeapon[userWeaponIndex].getX(),userWeapon[userWeaponIndex].getY());

		if((weaponLocation.getX() >= alienXLimit.getStart() && weaponLocation.getX() <= alienXLimit.getEnd()) 
			&& (weaponLocation.getY() >= alienYLimit.getStart() && weaponLocation.getY() <= alienYLimit.getEnd()))
		{
				hit = true; 
		}
		else
		{
				hit = false;
		}
		
		return hit;
	}
	
	/*--------------------------------------------------
	|	checkAlienHit method										 
	|--------------------------------------------------*/
	/*	This method check if the alien index being 		
	|	passed have been hit by	the weapon index being	
	|	passed by using the method isAlienHit().
	---------------------------------------------------*/
  public void checkAlienHit(int alienIndex,int shipWeaponIndex)
	{
		if(!userShip.isDead() && !alienShip[alienIndex].isDead())
		{
			boolean hit = isAlienHit(alienIndex,shipWeaponIndex);
			
			if(hit)
			{
				System.out.println("Hit");
				player.addScore(alienShip[alienIndex].getPoints());
				userWeapon[shipWeaponIndex].setVisible(false);
				userWeapon[shipWeaponIndex].killThread();
				userShip.loadSlot(shipWeaponIndex);
				alienShip[alienIndex].decreaseLife();
				if(alienShip[alienIndex].getLife() == 0)
				{
					level.getProgress().increaseCounter();
				}
				try{sleep(userWeapon[shipWeaponIndex].getTime()*5);}catch(InterruptedException ie){}
			}
		}
	}
  
	/*--------------------------------------------------
	|	run method													 
	|--------------------------------------------------
	|	This method uses all the prior methods in this	
	|	class in a while loop  to create a run time		
	|	collision check by checking every weapon that	
	|	has been shoot, alien's and user's location		
	|	at every step. Loop will only end if player has	
	|	killed every alien or died.													
	---------------------------------------------------*/	
  public void run()
	{
	  System.out.println("Here");
		while(level.getProgress().getCounter() != alienShip.length)
		{	
		
			for(int wIndex = 0;wIndex<userWeapon.length; wIndex++)
			{
				for(int aIndex = 0;aIndex < alienShip.length; aIndex++)
				{
					checkAlienHit(aIndex,wIndex);
				}
			}
			for(int aIndex = 0; aIndex< alienShip.length;aIndex++)
			{
				for(int wIndex = 0; wIndex < alienWeapon[aIndex].length; wIndex++)
				{
					checkUserHit(aIndex,wIndex);
				}
			}
			
		}
		level.advance();
		if(level.getLevel()==3)
		{level.setLevel(0);}
		if(level.getDifficulty()>=0)
		{lvlCmplt.levelComplete();
		try
			{
				sleep(2000);
			}catch(InterruptedException ex){}
		}
		lvlCmplt.removeAllItems();
		main.runGame(level.getLevel());
	}
}