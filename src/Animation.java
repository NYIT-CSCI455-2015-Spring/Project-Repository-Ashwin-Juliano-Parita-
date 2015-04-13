/* ------------------------------------------------------------------|												      
|Basic Information: This class extends  from JPanel and implements   |
|						  Runnable					                           |
|Purpose: 			  The purpose of this class is to provide methods  |
|						  that will ease the use of animations.            |
|--------------------------------------------------------------------*/

import java.awt.*;
import javax.swing.*;

class Animation extends JPanel implements Runnable
{
	private Image[] image;
	private int index;
	private int delay;
	private boolean paint;
	
	private Thread thread;
	
	private final int WIDTH = 40;
	private final int HEIGHT = 30;
	
	/*--------------------------------------------------|
	|	CONSTRUCTOR													 |
	|--------------------------------------------------*/
	/* The parameters nessesary to initialize this		|
	|	constructor are Image Array,int.       	      |								               
	|	It will initalize the class members with what	|
	|	is being passed to it. Set the index to 0. set	|
	|	paint to false and set the layout, opaque and	|	
	|	visibility.   												|
	|--------------------------------------------------*/
	Animation(Image[] img, int d)
	{
		image = img;
		index = 0;
		delay = d;
		paint = false;
		
		setSize(WIDTH,HEIGHT);
		setLayout(null);
		
		setOpaque(false);
		setVisible(true);
	}
	
	/*--------------------------------------------------|
	|	startAnimation()									       |
	|--------------------------------------------------*/
	/* No parameters                                    |
	|									                     	 |
	|	If the thread is not null. Set paint to true     |
	|	and start the thread.                  	   	 |
	|--------------------------------------------------*/
	public void startAnimation()
	{
		if(thread == null)
		{
			thread = new Thread(this);
			paint = true;
			thread.start();
		}
	}
	
	/*--------------------------------------------------|
	|	stopThread()									          |
	|--------------------------------------------------*/
	/* No parameters                                    |
	|									                     	 |
	|	set the thread to null and the paint to false 	 |
	|	to stop the animation                            |
	|--------------------------------------------------*/
	public void stopThread()
	{
		thread = null;
		paint = false;
	}
	public void run()
	{
		while(index < image.length-1){
			try
			{
				thread.sleep(delay);
				index++;
				repaint();
			}catch(InterruptedException ie){}
		}
		index = 0;
		paint = false;
	}
	
	/*--------------------------------------------------|
	|	pintComponent(Graphics)									 |
	|--------------------------------------------------*/
	/* Takes in a Graphics object                       |
	|									                     	 |
	|	Cast g to a Graphics2D object and use it         |  
	|	to draw the image on tho the screen at           |
	|	the position specified if paint is true.         |
	|--------------------------------------------------*/
	public void paintComponent(Graphics g)
	{
		
		g = (Graphics2D)g;
		super.paintComponent(g);
		if(paint)
		{
			try{
				g.drawImage(image[index],0,0,this);
			}catch(NullPointerException ex){
				System.out.println(ex.getCause());
			}
		}
	}
}