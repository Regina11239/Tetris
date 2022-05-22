import java.awt.*;
import java.applet.*;
import java.util.*;

abstract public class TetrisPiece extends GameThing
{
   protected int row, column;
	protected int numOrientations;
	protected int currentOrientation;
	protected ArrayList<Matrix> orientations;
	protected int count;


	public TetrisPiece(Graphics g)
	{
		super(g);
		row = 0;
		orientations = new ArrayList<Matrix>();
		count = 0;
	}

	public void display()
	{
		orientations.get(currentOrientation).display(row, column);
	}

	public void displayNext()
	{
		if (orientations.get(currentOrientation).getWidth() > 2)
			orientations.get(currentOrientation).display(7, 19);
		else
			orientations.get(currentOrientation).display(7, 20);
	}

	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	public void moveLeft()
	{
		column--;
	}

	public void moveRight()
	{
		column++;
	}

	public boolean canMoveLeft()
	{
		if (column <= 0)
			return false;

		boolean spaceAvailable = true;
		for (int r = 0; r < orientations.get(currentOrientation).getHeight(); r++)
			for (int c = 0; c < orientations.get(currentOrientation).getWidth(); c++)
				if (playSpace[row + r][column + c - 1] != 0 && orientations.get(currentOrientation).getLayout()[r][c] != 0)
				spaceAvailable = false;
		return spaceAvailable;
	}

	public boolean canMoveRight()
	{
		if (column + orientations.get(currentOrientation).getWidth() >= numColumns)
			return false;

		boolean spaceAvailable = true;
		for (int r = 0; r < orientations.get(currentOrientation).getHeight(); r++)
			for (int c = 0; c < orientations.get(currentOrientation).getWidth(); c++)
				if (playSpace[row + r][column + c + 1] != 0 && orientations.get(currentOrientation).getLayout()[r][c] != 0)
				spaceAvailable = false;
		return spaceAvailable;
	}

	public void rotateClockwise()
	{
		currentOrientation++;
		if (currentOrientation >= numOrientations)
			currentOrientation = 0;
	}

	public void rotateCounterClockwise()
	{
		currentOrientation--;
		if (currentOrientation < 0)
			currentOrientation = numOrientations - 1;
	}

	public boolean canRotateClockwise()
	{
		int nextOrientation = currentOrientation + 1;
		if (nextOrientation >= numOrientations)
		    nextOrientation = 0;

		int numColsToMoveLeft = 0;
		boolean spaceAvailable;
		do
		{
			spaceAvailable = true;
			for (int r = 0; r < orientations.get(nextOrientation).getHeight(); r++)
				for (int c = 0; c < orientations.get(nextOrientation).getWidth(); c++)
					if (playSpace[row + r][column + c - numColsToMoveLeft] != 0 && orientations.get(nextOrientation).getLayout()[r][c] != 0)
						spaceAvailable = false;
			numColsToMoveLeft++;
		}
		while(!spaceAvailable && numColsToMoveLeft <= 3);
		numColsToMoveLeft--;

		if (spaceAvailable && numColsToMoveLeft > 0)
			column -= numColsToMoveLeft;

		return spaceAvailable;
	}

	public boolean canRotateCounterClockwise()
	{
		int prevOrientation = currentOrientation - 1;
		if (prevOrientation < 0)
		    prevOrientation = numOrientations - 1;

		int numColsToMoveLeft = 0;
		boolean spaceAvailable;
		do
		{
			spaceAvailable = true;
			for (int r = 0; r < orientations.get(prevOrientation).getHeight(); r++)
				for (int c = 0; c < orientations.get(prevOrientation).getWidth(); c++)
					if (playSpace[row + r][column + c - numColsToMoveLeft] != 0 && orientations.get(prevOrientation).getLayout()[r][c] != 0)
						spaceAvailable = false;
			numColsToMoveLeft++;
		}
		while(!spaceAvailable && numColsToMoveLeft <= 3);
		numColsToMoveLeft--;

		if (spaceAvailable && numColsToMoveLeft > 0)
			column -= numColsToMoveLeft;

		return spaceAvailable;
	}

	public void fall()
	{
		if (count < speed)
			count++;
		else
		{
			row++;
			count = 0;
		}
	}

	public boolean canFall()
	{
		boolean spaceAvailable = true;
		for (int r = 0; r < orientations.get(currentOrientation).getHeight(); r++)
			for (int c = 0; c < orientations.get(currentOrientation).getWidth(); c++)
				if (playSpace[row + r + 1][column + c] != 0 && orientations.get(currentOrientation).getLayout()[r][c] != 0)
					spaceAvailable = false;
		return spaceAvailable;
	}

	public void drop()
	{
		while (canFall())
			row++;    		 
	}

	public void putInGrid()
	{
		for (int r = 0; r < orientations.get(currentOrientation).getHeight(); r++)
			for (int c = 0; c < orientations.get(currentOrientation).getWidth(); c++)
				if (orientations.get(currentOrientation).getLayout()[r][c] != 0)
					playSpace[row+r][column+c] = orientations.get(currentOrientation).getSquare(r,c).getColor();
	}
}


