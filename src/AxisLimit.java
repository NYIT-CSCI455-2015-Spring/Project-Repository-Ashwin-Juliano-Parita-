/* ------------------------------------------------------------------												      
|Basic Information: This class does not extand or implent anything   
|Purpose: 			  The purpose of this class is to provide methods  
|						  to set and get the axis limits for the objects	
|						  in the game                                      
|--------------------------------------------------------------------*/
class AxisLimit
{
	private int start;
	private int end;
	
	/*--------------------------------------------------
	|	CONSTRUCTOR													 
	|---------------------------------------------------
	| The parameters necessary to initialize this		
	|	constructor are int,int.       	               								               
	|	It will initialize the class members with what	
	|	is being passed to it.        						
	|--------------------------------------------------*/
	AxisLimit(int s, int e)
	{
		start = s;
		end = e;
	}
	
	/*--------------------------------------------------
	|	setStart()	Modifier Method							             
	|--------------------------------------------------
	|  takes int as parameter                         
	|									                     	 
	|  Set the start axis to s                  	   	 
	|--------------------------------------------------*/
	public void setStart(int s)
	{
		start = s;
	}
	
	/*--------------------------------------------------
	|	setEnd() Modifier Method								                
	|--------------------------------------------------
	| takes int as parameter                         
	|									                     	 
	|  Set the end axis to e                  	   	 
	|--------------------------------------------------*/
	public void setEnd(int e)
	{
		end = e;
	}
	
	/*--------------------------------------------------
	|	getStart() Accessor Method								             
	|--------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Returns the start axis value                  	 
	|--------------------------------------------------*/
	public int getStart()
	{
		return start;
	}
	
	/*--------------------------------------------------
	|	getEnd() Accessor Method	 								                
	|---------------------------------------------------
	| No parameters                                    
	|									                     	 
	|	Returns the end axis value                  	    
	|--------------------------------------------------*/
	public int getEnd()
	{
		return end;
	}
}