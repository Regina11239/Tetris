import java.awt.*;
import java.applet.*;

public class GameThing 
{
	protected final static int appletWidth = 1000; 				 
	protected final static int appletHeight = 650;
    				

	protected final static int squareSize = 25;

	protected final static int numColumns = 15;					  

	protected final static int numRows = appletHeight / squareSize;

	protected static int speed = 100;   

	protected static int[][] playSpace;
   protected Graphics g;				
   



	public GameThing(Graphics g)
	{
		this.g = g;    
		
	}


	public static void makeGameFaster()
	{
		if (speed > 1)
			speed --;
	}
}


