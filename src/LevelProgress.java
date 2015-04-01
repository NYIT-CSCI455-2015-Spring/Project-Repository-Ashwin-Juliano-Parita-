/* ------------------------------------------------------------------											      
|Basic Information: This class does not extend or implement anything 
|																							
|Purpose: 			  Keep track of the users progress                 
|--------------------------------------------------------------------*/

class LevelProgress
{
	private ShipFrame[] aliens;
	private int counter;
	
	/*--------------------------------------------------
	|	CONSTRUCTOR													 
	|--------------------------------------------------
	| The parameters nessesary to initialize this		
	|	constructor are ShipFrame[]       	            								               
	|	It will initalize the class members with what	
	|	is being passed to it. Sets the counter to 0	   
	|--------------------------------------------------*/
	LevelProgress(ShipFrame[]a)
	{
		aliens = a;
		counter = 0;
	}
	
	/*--------------------------------------------------
	|	increaseCounter()									       
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Increments the counter by 1                      
	|--------------------------------------------------*/
	public void increaseCounter()
	{
		counter++;
	}	
	
	/*--------------------------------------------------
	|	resetCounter()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Resets the counter to initial value              
	|--------------------------------------------------*/
	public void resetCounter()
	{
		counter = 0;
	}
	
	/*--------------------------------------------------
	|	getCounter()									          
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Returns the counter                              
	|--------------------------------------------------*/
	public int getCounter()
	{
		return counter;
	}

}