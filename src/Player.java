/* ------------------------------------------------------------------												      
|Basic Information: This class does not extend anything but          
|						  implements MouseListener									
|																							
|Purpose: 			  Create a player and keeps track of its settings  
|--------------------------------------------------------------------*/


class Player
{
	// Instance variables
	private String name;							 // String used for the player name
	private int kills;								 // Integer values for the # of kills
	private int score;								 // Integer value to represent the player score
	private ShipFrame ship;							 // Shipframe object for the player ship
	private Level level;							 // Level object to represent the current game level
	
	/*-----------------------------------------
	|	Constructor										 
	|-----------------------------------------	
	| The parameters needed to initialize the  
    |  constructor are a String for the player 
	|  name, a ShipFrame object and a Level    
	|  object											 
	|-----------------------------------------*/
	Player(String n, ShipFrame s, Level l)
	{
		name  = n;										 // Initialize player name to the value passed
		kills = 0;										 // Initializes kills to value of 0
		score = 0;										 // Initializes score to value of 0		
		ship  = s;										 // Initializes shipframe
		level = l;										 // Initializes level to the value passed
	}
	
   /*------------------------------------------
	|  getName Method							        
	|------------------------------------------	
	| This method returns the player name      
	|------------------------------------------*/
	public String getName()
	{
		return name;									
	}
	
   /*------------------------------------------
	| getKills Method							        
	|------------------------------------------	
	| This method returns the number of kills  
	|------------------------------------------*/
	public int getKills()
	{
		return kills;									 
	}
	
   /*------------------------------------------
	|  getScore Method							     
	|------------------------------------------	
	| This method returns the player score     
	|------------------------------------------*/
	public int getScore()
	{
		return score;									
	}
	
   /*------------------------------------------
	|  getLevel Method							     
	|------------------------------------------	
	| This method returns the level            
	|------------------------------------------*/
	public Level getLeve()
	{
		return level;								
	}
	
   /*------------------------------------------
	|  ShipFrame Method							     
	|------------------------------------------	
	| This method returns the player's         
	|  shipframe object                         
	|------------------------------------------*/
	public ShipFrame getShip()
	{
		return ship;									 
	}
	
   /*-------------------------------------------
	|  increaseKills Method							   
	|-------------------------------------------	
	| Increases the number of kills				   
	|-------------------------------------------*/
	public void increaseKills()
	{
		kills++;											
	}
	
   /*-------------------------------------------
	|  addScore Method							      
	|-------------------------------------------		
	|  This method adds the integer that was     
	|  passed to the score variable              
	|-------------------------------------------*/
	public void addScore(int n)
	{
		score += n;										 
	}
}