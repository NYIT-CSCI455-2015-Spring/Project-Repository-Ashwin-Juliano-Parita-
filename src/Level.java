
/* ------------------------------------------------------------------										      
|Basic Information: This class does not extend or implement anything 
|																							
|Purpose: 			  The purpose of this class is to keep track of the
|						  level so the game will know the difficulty and   
|						  animations it should execute.                    
|--------------------------------------------------------------------*/

import java.applet.*;

class Level 
{
	private int level;
	private int difficulty;
	private int scoreBase;
	private LevelProgress progress;
	
	private ShipFrame[] aliens;
	private AudioClip[]levelSongs;
	/*--------------------------------------------------
	|	CONSTRUCTOR													 
	|--------------------------------------------------
	| The parameters nessesary to initialize this		
	|	constructor are ShipFrame[],AudioClip[]       									               
	|	It will initalize the class members with what	
	|	is being passed to it. Initialize the level to  
	|	0, set the difficulty to 1 and the scoreBase to 
	|	0.															   
	|--------------------------------------------------*/
	Level(ShipFrame[] a,AudioClip[] audio)
	{
		level = 0;
		difficulty = 1;
		scoreBase = 0;
		progress = new LevelProgress(a);
		
		aliens = a;
                
                levelSongs = audio;
	}
	
	/*--------------------------------------------------
	|	getLevel()									             
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Returns the current level                        
	|--------------------------------------------------*/
	public int getLevel()
	{
		return level;
	}
	
	/*--------------------------------------------------
	|	setLevel()									             
	|--------------------------------------------------
	| Takes an int as a parameter                      
	|									                     	 
	|	Sets the level to what is being passed to it     
	|--------------------------------------------------*/
	public void setLevel(int l)
	{
		level = l;
	}
	
	/*--------------------------------------------------
	|	getDifficulty()									       
	|--------------------------------------------------
	|No parameters                                   
	|									                     	 
	|	Returns the current difficulty                   
	|--------------------------------------------------*/
	public int getDifficulty()
	{
		return difficulty;
	}
	
	/*--------------------------------------------------
	|	getScoreBase()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Returns the current score base                   
	|--------------------------------------------------*/
	public int getScoreBase()
	{
		return scoreBase;
	}
	
	/*--------------------------------------------------
	|	getLevelProgress()									    
	|--------------------------------------------------
	|No parameters                                    
	|									                     	 
	|	Returns the current level progress               
	|--------------------------------------------------*/
	public LevelProgress getProgress()
	{
		return progress;
	}
	
	/*--------------------------------------------------
	|	advance()									             
	|--------------------------------------------------
	|No parameters                                    
	|									                     	 
	|	Inncrements the level and the difficulty. It also
	|  increases the score base by 5							 	
	|--------------------------------------------------*/
	public void advance()
	{
		level++;
		if(difficulty<50)
		{difficulty++;}
		progress.resetCounter();
		scoreBase +=5;
	}
}