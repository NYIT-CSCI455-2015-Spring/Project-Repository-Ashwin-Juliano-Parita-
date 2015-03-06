/* ------------------------------------------------------------------
|Information: This class extends  from JPanel.				                  
|Purpose: 			  The purpose of this class is to create and panel 
|						  with an image of a space ship drawn in it.	
|--------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;

class ShipFrame extends JPanel{
																												//AMMO_SLOTS.
	private AxisLimit xbound;					//Limits x-axis moves
	private AxisLimit ybound;					//Limits y-axis moves
	
	private Image shipImg;						//Holds ship image to be drawn in this panel

	private final int WIDTH = 40;				//This panel width
	private final int HEIGHT = 30;				//This panel height
	
	private final int INIT_SHIPLOCATION_X;		//Ship initial X location on the main panel
	private final int INIT_SHIPLOCATION_Y;		//Ship initial Y location on the main panel
	
	private final int MAIN_PANEL_WIDTH;			//hold the main panel dimensions
	private final int MAIN_PANEL_HEIGHT;		//same as above

	/*--------------------------------------------------
	|	CONSTRUCTOR													 
	|--------------------------------------------------						
	|																	
	|	The constructor will set  the  panel			
	|	layout to null, opaque value to false, and 		
	|	visibility to true.   									
	|--------------------------------------------------*/
	ShipFrame(Image sI, int initialX,int initialY, 
			AxisLimit x, AxisLimit y,int mainPanelWidth, int mainPanelHeight)
   {
		shipImg = sI;
		
		xbound = x;
		ybound = y;
		
		MAIN_PANEL_WIDTH = mainPanelWidth;
		MAIN_PANEL_HEIGHT = mainPanelHeight;
		
		INIT_SHIPLOCATION_X = initialX;
		INIT_SHIPLOCATION_Y = initialY;
		
		setSize(WIDTH,HEIGHT);
		setLocation(INIT_SHIPLOCATION_X,INIT_SHIPLOCATION_Y);
		
		setLayout(null);
		setOpaque(false);
		setVisible(true);
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
	|	moveLeft Location Method	           		     	
	|--------------------------------------------------
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
	
	/*-------------------------------------------------
	|	Paint Method 				         					 
	|--------------------------------------------------
	| This  method  will paint img to the panel.		 
	---------------------------------------------------*/
	public void paintComponent(Graphics g) 
	{
		g = (Graphics2D)g;
		super.paintComponent(g);
			try{
				g.drawImage(shipImg,0,0,this);
			}catch(NullPointerException ex){
				System.out.println(ex.getCause());
			}
	}
}