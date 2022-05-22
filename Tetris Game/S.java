import java.awt.*;
import java.applet.*;
import java.util.*;

public class S extends TetrisPiece
{
	Matrix m0;

	int layout0[][] = { {1,0,1,1,1},
					    {1,0,1,0,1},
					    {1,1,1,0,1} };

	public S(Graphics g)
	{
		super(g);
		m0 = new Matrix(g,layout0,true);
		orientations.add(m0);
		numOrientations = orientations.size();
		currentOrientation = 0;
		column = -6;
		row = 1;
	}
}


