/* ------------------------------------------------------------------											      
|Basic Information: This class extends MyKeyListener						
|																							
|Purpose: 			  Listen for the key presses of the user to		   
|						  command the spaceship             
|--------------------------------------------------------------------*/

import java.awt.event.*;

public class MyKeyListener extends KeyAdapter 
{
	// Instance variables
	private ShipFrame ship; 									// Holds spaceship object
	private boolean pressed;									// Boolean value to determine if key is pressed
	
	/*-------------------------------------------------
	|	Constructor											      
	|--------------------------------------------------
	|   Sets the spaceship object passed in.												
	|-------------------------------------------------*/
	MyKeyListener(ShipFrame s) 
	{
		ship = s;											   // Initializes ship to the parameter passed
	}
	
	/*-------------------------------------------------
	|	keyPressed Method									      
	|-------------------------------------------------
	|	Handles arrows and space events sent from the 	
	|   user's keyboard. It also makes sure that the		
	|	SpaceShip object's location does not go out of	
	|	boundaries.													
	--------------------------------------------------*/
	public void keyPressed(KeyEvent ke) 
	{				
		int keyCode = ke.getKeyCode();						    // Retrieve keyCode
		int space = 30;											// Distance that the ship will move
		pressed = true;											// Assigns the true value to pressed		
		
		try{
			if(keyCode == KeyEvent.VK_LEFT)					    // Configures ship movement to the LEFT
			{
				ship.moveLeft(space);							// Ship moves left
			}
			if(keyCode == KeyEvent.VK_RIGHT)					// Configures ship movement to the RIGHT
			{
				ship.moveRight(space);							// Ship moves right
			}
			if(keyCode == KeyEvent.VK_UP)						// Configures ship movement UP
			{
				ship.moveUp(space);								// Ship moves up
			}
			if(keyCode == KeyEvent.VK_DOWN)					    // Configures ship movement DOWN 
			{ 
				ship.moveDown(space);							// Ship moves down
			}
			if(keyCode == KeyEvent.VK_SPACE) 					// Shoot the ship weapon
			{
				ship.shootWeapon();								// Ship shoots beam
			}
		}catch(NullPointerException ex){System.out.println(ex.getLocalizedMessage());}
	}	
}