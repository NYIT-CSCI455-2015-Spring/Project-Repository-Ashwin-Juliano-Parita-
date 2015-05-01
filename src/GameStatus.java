

/* ------------------------------------------------------------------												      
|Basic Information: This class extends  from JPanel                  
|                                             								
|Purpose: 			  The purpose of this class is to set a a bar which
|						  will show the score, lives, number of enemies    
|						  killed and the lives of the player all real      
|						  time as the game is going on		               
|--------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;

class GameStatus extends JPanel
{
	private Player player;
	private Level lev;
	private StatusItem name,life,score,kills,level;
	private int x,y;
	private final int WIDTH,HEIGHT;
	private final int ITEM_WIDTH,ITEM_HEIGHT,ITEM_NUMBER;
	private int space_Items;
	
	/*--------------------------------------------------
	|	CONSTRUCTOR													
	|--------------------------------------------------
	|   The parameters nessesary to initialize this		 
	|	constructor are Level,Player,int,int.          	 								               
	|	It will initalize the class members with what	 
	|	is being passed to it. Set the height and width  
	|	of the item and adds them all to the panel       
	|--------------------------------------------------*/
	GameStatus(Level l,Player p,int xP,int yP)
	{
		player = p;
		lev=l;
		x = xP;
		y = yP;
		
		ITEM_WIDTH = 100;
		ITEM_HEIGHT = 20;
		ITEM_NUMBER = 4;
		space_Items = 20;
		
		WIDTH = (ITEM_WIDTH+space_Items)*ITEM_NUMBER;
		HEIGHT = ITEM_HEIGHT+2;
		
		
		life = new StatusItem("Life",String.valueOf(player.getShip().getLife()),ITEM_WIDTH,ITEM_HEIGHT,10,1);
		score = new StatusItem("Score",String.valueOf(player.getScore()),ITEM_WIDTH,ITEM_HEIGHT,50,1);
		kills = new StatusItem("Kills",String.valueOf(player.getKills()),ITEM_WIDTH,ITEM_HEIGHT,130,1);
		level = new StatusItem("Level",String.valueOf(lev.getDifficulty()),ITEM_WIDTH,ITEM_HEIGHT,200,1);
		
		
		add(life);
		add(score);
		add(kills);
		add(level);
		setLayout(null);
		setOpaque(false);
		setSize(WIDTH, HEIGHT);
		setLocation(x,y);
		setVisible(true);
	}
	
	/*--------------------------------------------------
	|	getPlayer()									             
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Returns the player that is associated with the   
	| 	status bar       	   	                         
	|--------------------------------------------------*/
	public Player getPlayer()
	{
		return player;
	}
	
	
	/*--------------------------------------------------
	|	refreshName()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Upadates the name of the player in the status    
	|	bar      	   	                               
	|--------------------------------------------------*/
	public void refreshName()
	{
		name.setData(player.getName());
	}
	
	/*--------------------------------------------------
	|	refreshLife()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Upadates the life of of the player in the status 
	|	bar      	   	                               
	|--------------------------------------------------*/
	public void refreshLife()
	{
		life.setData(String.valueOf(player.getShip().getLife()));
	}
	
	/*--------------------------------------------------
	|	refreshScore()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Upadates the score of the player in the status   
	|	bar      	   	                               
	|--------------------------------------------------*/
	public void refreshScore()
	{
		score.setData(String.valueOf(player.getScore()));
	}
	
	/*--------------------------------------------------
	|	refreshKills()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Upadates the number of kills the player has      
	|	in the status bar      	   	                   
	|--------------------------------------------------*/
	public void refreshKills()
	{
		kills.setData(String.valueOf(player.getKills()));
	}
	
	/*--------------------------------------------------
	|	refreshLevel()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Upadates the level of the player in the status   
	|	bar      	   	                               
	|--------------------------------------------------*/
	public void refreshLevel()
	{
		level.setData(String.valueOf(lev.getDifficulty()));
	}

   /*--------------------------------------------------
	|	refreshAll()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Updates all of the items in the status bar       
	|--------------------------------------------------*/
	public void refreshAll()
	{
		life.setData(String.valueOf(player.getShip().getLife()));
		score.setData(String.valueOf(player.getScore()));
		kills.setData(String.valueOf(player.getKills()));
		level.setData(String.valueOf(lev.getDifficulty()));
	}
}