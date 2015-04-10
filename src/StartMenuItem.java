/* ------------------------------------------------------------------											      
|Basic Information: This class extends JPanel						      
|																							
|Purpose: 			  Paint items to the menu at given locations       
|--------------------------------------------------------------------*/



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.IllegalThreadStateException;

class StartMenuItem extends JPanel 
{
	// Instance variables						
	private Image image;						// StartMenuImage image to be drawn on THIS panel			
	private int width;							// Width of StartMenuItem panel			
	private int height;							// Height of StartMenuItem panel	
	private final int X;						// StartMenuItem X-LOCATION on MAIN panel
	private final int Y;						// StartMenuItem Y-LOCATION on MAIN panel
	
	/*-------------------------------------------------------------
	| Constructor 														   	   
	|-------------------------------------------------------------
	| The constructor for this class needs an Image object to     
	|  draw on the background, a width and a height for the panel  
	|  where this object will be drawn, and an X and Y location    
	|  to place the StartMenuItem on the panel                     
   |------------------------------------------------------------ */		
	StartMenuItem( Image image, int width, int height, int X, int Y) 
	{	
		this.image  = image;					// Initialize image to the image passed							
		this.width  = width;				   	// Initialize the width to the value passed
		this.height = height;					// Initialize the height to the value passed
		this.X      = X;						// Initialize the x-value
		this.Y      = Y;						// Initialize the y-value
				
		// Panel Configuration
		setLayout(null);
		setOpaque(false);
		setSize(width, height);
		setLocation(X, Y);
		setVisible(true);
	}

   /*------------------------------------------
	|  movex Method							        
	|------------------------------------------	
	| Increments the x coordinate              
	|------------------------------------------*/
	public void movex(int n)
	{
		setLocation(getX()+n,getY());
	}

   /*------------------------------------------
	|  movey Method							        
	|------------------------------------------	
	| Increments the y coordinate              
	|------------------------------------------*/
	public void movey(int n)
	{
		setLocation(getX(),getY()+n);
	}	
	
   /*------------------------------------------
	|  paintComponent					              
	|------------------------------------------	
	| Overrides paint component                
	|------------------------------------------*/
	public void paintComponent(Graphics g) 
	{
		g = (Graphics2D)g;
		super.paintComponent(g);
		try{
			g.drawImage(image, 0, 0, this);
		}catch(NullPointerException ex)
		{
			System.out.println(ex.getCause());
		}
	}
}