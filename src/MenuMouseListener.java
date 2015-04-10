/* ------------------------------------------------------------------											      
|Basic Information: This class does not extend anything but          
|						  implements MouseListener									
|																							
|Purpose: 			  Listen for the click of the user on the          
|						  menu buttons                                     
|--------------------------------------------------------------------*/

import java.awt.event.*;

class MenuMouseListener implements MouseListener
{
		// Instance variables
  		private StartMenuPanel menuPanel;					// StartMenuPanel object
		private StartMenuItem startButton;					// StartMenuItem object
		private StartMenuItem scoresButton;					// StartMenuItem object
		private GalaxyBattle main;								// Galaxy object
		private boolean scoreClicked;						// Boolean for button clicked
		
		/*----------------------------------------------
		|  Constructor											   
		|----------------------------------------------
		| The parameters that are necessary for this   
		|  constructor are a Galaxy object and a        
		|	StartMenuPanel. The class configures the     
		|	mouse behavior according the mouse events    
		|	passed.													
		|----------------------------------------------*/
		MenuMouseListener(GalaxyBattle g,StartMenuPanel mPanel)
		{
			menuPanel    = mPanel;								// MenuPanel object
			startButton  = menuPanel.getStartButton();			// Assigns the start button from start panel
			scoresButton = menuPanel.getScoresButton();  		// Assigns the score button from start panel
			main 			 = g;								// Assigns a Galaxy object to main
			scoreClicked = false;								// Initialize score clicked to false 
		}

		/*----------------------------------------------
		|  MousePressed Method								   
		|----------------------------------------------	
		| This method configures what will happen when 
		|  a mouse event is passed					         
		|----------------------------------------------*/		
  		public void mousePressed(MouseEvent e) 
		{	 			 			
			int mx = e.getX();									// Gets x-coordinate
			int my = e.getY();									// Gets y-coordinate
			
			if(mx > startButton.getX() && mx < startButton.getX()+startButton.getWidth() &&
					my > startButton.getY() && my < startButton.getY()+startButton.getHeight() ) 
			{		
				if(scoreClicked)								// Configuration for score clicked event
				{
					main.closeScores();						    // Calls close method
				}
				
				menuPanel.removeAllItems();						// Removes start menu items
				main.statusBar();								// Starts the status bar 
				main.runGame(1);								// Iniitializes the game
			}
			else if(mx > scoresButton.getX() && mx < scoresButton.getX()+scoresButton.getWidth() &&
					my > scoresButton.getY() && my < scoresButton.getY()+scoresButton.getHeight() )
			{
				if(!scoreClicked)									// Configuration for score NOT clicked
				{
					main.openScores(scoreClicked);			
					scoreClicked = true;							// Set score clicked boolean to true status
				}
				else
				{
					main.openScores(scoreClicked);			
					scoreClicked = false;						// Set score clicked boolean to false status
				}
			}
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}		 
		public void mouseExited(MouseEvent e) {}	
		public void mouseReleased(MouseEvent e) {}			  
  }