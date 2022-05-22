import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.applet.Applet;


public class Grfx extends Applet
{   
	public static int random(int min, int max)
	{
		int range = max - min + 1;
		int number = (int) (range * Math.random() + min);
		return number;
	}
   
	public static void delay(int n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();
	}


	public static void setColor(Graphics g, int colorNum)
	{
		colorNum %= 10;
		switch(colorNum)
		{
         case  1 : g.setColor(new Color(166, 212, 190)); 		break;
			case  2 : g.setColor(new Color(150, 200, 150)); 	break;
			case  3 : g.setColor(new Color(133, 158, 126)); 	break;
			case  4 : g.setColor(new Color(133, 174, 79)); 	break;
			case  5 : g.setColor(new Color(47, 171, 146)); 	break;
			case  6 : g.setColor(new Color(179, 198, 125)); 	break;
			default : g.setColor(Color.WHITE);
		};
	}

   public static void setColor(Graphics g, int red, int green, int blue)
	{
		Color newColor = new Color(red, green, blue);
		g.setColor(newColor);
	}
   
   public static void fillFrame(Graphics g, int x1, int y1, int x2, int y2)
	{
		int temp;
		if (x1 > x2)
			{ temp = x1; x1 = x2; x2 = temp; }
		if (y1 > y2)
			{ temp = y1; y1 = y2; y2 = temp; }
		int width  = x2 - x1 + 1;
		int height = y2 - y1 + 1;
      g.fillRect(x1, y1 - 5, width, 5);
      g.fillRect(x1 - 5, y1 - 5, 5, height - 10);
      g.fillRect(x1 + width, y1 - 5, 5, height - 10);
      g.fillRect(x1, height, width, 5);

	}
}