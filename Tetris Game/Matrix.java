import java.awt.*;
import java.applet.*;
import java.util.*;

public class Matrix extends GameThing
{
	private int height, width;

	private int layout[][];

	private Square squares[][];

	public Matrix(Graphics g, int[][] newLayout)
	{
		super(g);
		height  = newLayout.length;
		width   = newLayout[0].length;
		layout  = newLayout;
		squares = new Square[height][width];

		for (int r = 0; r < height; r++)
			for (int c = 0; c < width; c++)
				if (layout[r][c] == 1)
					squares[r][c] = new Square(g,r,c);
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int[][] getLayout()
	{
		return layout;
	}

	public Square getSquare(int row, int column)
	{
		return squares[row][column];
	}

	public void display(int row, int column)
	{
		for (int r = 0; r < height; r++)
			for (int c = 0; c < width; c++)
				if (layout[r][c] == 1)
					squares[r][c].display(row,column);
	}
}


