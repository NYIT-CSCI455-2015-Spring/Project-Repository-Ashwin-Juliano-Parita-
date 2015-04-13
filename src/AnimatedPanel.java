/* ------------------------------------------------------------------											      
|Basic Information: This class extends  from JPanel and implements   
|						  Runnable					                           
|Purpose: 			  The purpose of this class is to animate menu     
|						  of the game to appear in an animation insted     
|						  of just popping up		                           
|--------------------------------------------------------------------*/

   import javax.swing.*;
   import java.awt.*;
   class AnimatedPanel extends JPanel implements Runnable
   {
      private Thread thread;         //The thread tha will control the animation
      private Image image;				 //The image of the animation
   
      private int initWidth;			 //The initial width of the animation	
      private int initHeight;			 //The initial height of the animation
   
      private final int WIDTH;
      private final int HEIGHT;
   
   
   /*--------------------------------------------------
   |	CONSTRUCTOR													 
   |--------------------------------------------------
   |	The parameters nessesary to initialize this		
   |	constructor are Image,int,int,int, int       									               
   |	It will initalize the class members with what	
   |	is being passed to it. Set the initial height   
   |   and initial width to 0. set the layout to null	
   |	the set the location, visibility and opaquenes  
   |--------------------------------------------------*/
      AnimatedPanel(Image i,int w, int h,int x, int y)
      {
         thread = null;
         image = i;
         WIDTH = w;
         HEIGHT = h;
      
         initWidth = 0;
         initHeight = 0;
         setLayout(null);	
         setSize(initWidth,initHeight);
         setLocation(x,y);
         setVisible(false);
         setOpaque(false);      	
      }
   
   /*--------------------------------------------------
   |	animate()									             
   |--------------------------------------------------
   | No parameters                                    
   |									                     	 
   |	Create a thread and initialize it       	   	 
   |--------------------------------------------------*/
      public void animate()
      {
         thread = new Thread(this);
         thread.start();
      }
   
   /*--------------------------------------------------
   |	pintComponent(Graphics)								 
   |--------------------------------------------------
   | Takes in a Graphics object                       
   |									                     	 
   |	Create a Graphics2D object and use it              
   |	to draw the image on tho the screen at           
   |	the position specified.        	   	          
   |--------------------------------------------------*/
      public void paintComponent(Graphics g)
      {
         Graphics2D g2d = (Graphics2D)g;
         super.paintComponent(g2d);
         g2d.drawImage(image,0,0,this);
      }
   
      public void run()
      {
      //Do this until the animation reaches the point where the menu should be
         while(WIDTH > initWidth || HEIGHT > initHeight)
         {
            try
            {
               thread.sleep(5);									//Control the speed of the animation
            }
               catch(InterruptedException ie){}
            if(WIDTH > initWidth)						//If the width is greater than the initial width
            {
               initWidth += 5;							//Add 5 to the initial with
            }
            if(HEIGHT > initHeight)						//Do the same with height
            {
               initHeight +=5;
            }
            setSize(initWidth,initHeight);			//Set the size of the panel
            repaint();										//Repaint with the new size.
         }	
      
      }
   }