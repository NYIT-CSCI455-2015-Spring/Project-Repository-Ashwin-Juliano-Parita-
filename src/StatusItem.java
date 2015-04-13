/* ------------------------------------------------------------------											      
|Basic Information: This class extends JPanel						      
|																							
|Purpose: 			  Paint items to the status bar at given locations 
|--------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;

class StatusItem extends JPanel
{
	// Instance variables
	private String description;											// String type description
	private String data;												// String type data 
	private final int WIDTH,HEIGHT;										// Height and width of the panel
	private int x,y;													// Position on the panel
	
	/*----------------------------------------------------------
	| Constructor									                     
	|----------------------------------------------------------
	| This constructor takes two string variables to represent 
	|  the status item; a width and height of the main panel;	
	|  an x and y coordinate for the placement of the object    
	|----------------------------------------------------------*/
	StatusItem(String de, String da, int w, int h,int xP, int yP)
	{
		description = de;														// Description of the status item
		data = da;																// Desctiption of the data in the item		
		WIDTH = w;																// Width of the main panel
		HEIGHT = h;																// Height of the main panel
		x = xP;																	// x-coordinate placement of the object
		y = yP;																	// y-coordiante placement of the object
		
		// Layout configurations
		setLayout(null);
		setOpaque(false);
		setSize(WIDTH,HEIGHT);
		setLocation(x,y);
		setVisible(true);		
	}
	
   /*------------------------------------------
	|  paintComponent Method				        
	|------------------------------------------	
	| Overrides paint component                
	|------------------------------------------*/
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		super.paintComponent(g);
		String str = description+": "+data;
		g2D.setPaint(Color.orange);
		g2D.drawString(str,10,10);
	}

   /*------------------------------------------
	|  setDescription Method				        
	|------------------------------------------	
	| Sets description to the value passed     
	|  and then repaints the screen             
	|------------------------------------------*/
	public void setDescription(String d)
	{
		description = d;
		repaint();
	}
	
   /*------------------------------------------
	|  setData Method				                 
	|------------------------------------------	
	| Sets data to the value that is passed    
	|  and the repaints the screen              
	|------------------------------------------*/
	public void setData(String d)
	{
		data = d;
		repaint();
	}
}