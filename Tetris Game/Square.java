import java.awt.*;
import java.applet.*;

public class Square extends GameThing
{
	private int x, y;
	protected int colorNum;

	public Square(Graphics g, int r, int c)
	{
		super(g);
		y = r * squareSize;
		x = (c + 7) * squareSize;
		colorNum = Grfx.random(1,6);
	}

	public void display(int row, int column)
	{
		Grfx.setColor(g,colorNum);
		int x0 = x + column * squareSize;
		int y0 = y + row * squareSize;;
		g.fillRect(x0,y0,squareSize,squareSize);
		int x1 = x0 + 2;
		int y1 = y0 + 2;
		int x2 = x1 + squareSize - 4;
		int y2 = y1 + squareSize - 4;

		int q = 5;
		g.setColor(Color.black);
		g.drawLine(x1,y1,x2,y1);
		g.drawLine(x1,y1,x1,y2);
	}

	public static void display(Graphics g, int row, int column, int color)
	{
		Grfx.setColor(g,color);
		int x0 = (column + 7) * squareSize;
		int y0 = row * squareSize;
		g.fillRect(x0,y0,squareSize,squareSize);
		int x1 = x0 + 2;
		int y1 = y0 + 2;
		int x2 = x1 + squareSize - 4;
		int y2 = y1 + squareSize - 4;

		int q = 5;
      int x = row;
      int y = column;
		g.setColor(Color.black);
		g.drawLine(x1,y1,x2,y1);
		g.drawLine(x1,y1,x1,y2);
  	}

	public int getColor()
	{
		return colorNum;
	}

	public void setColor(int newColor)
	{
		colorNum = newColor;
	}

	public void copyColor(Square original)
	{
		setColor(original.getColor());
	}

	public Square getSquare()
	{
		return this;
	}

}


