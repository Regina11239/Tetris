import java.awt.*;
import java.applet.*;
import java.util.*;

public class Piece3 extends TetrisPiece
{
	Matrix m0;

	int layout0[][] = { {1,1},
					    {1,1} };


	public Piece3(Graphics g)
	{
		super(g);

		m0 = new Matrix(g,layout0);

		orientations.add(m0);

		numOrientations = orientations.size();

		currentOrientation = Grfx.random(0,numOrientations-1);

		column = Grfx.random(0, numColumns - orientations.get(currentOrientation).getWidth());
	}


	public boolean canRotateClockwise(int n)
	{
		return false;
	}

	public boolean canRotateCounterClockwise(int n)
	{
		return false;
	}
}


