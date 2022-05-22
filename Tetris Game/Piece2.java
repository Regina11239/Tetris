import java.awt.*;
import java.applet.*;
import java.util.*;

public class Piece2 extends TetrisPiece
{
	Matrix m0, m1;

	int layout0[][] = { {1},
					    {1},
					    {1},
					    {1} };

	int layout1[][] = { {1,1,1,1} };


	public Piece2(Graphics g)
	{
		super(g);

		m0 = new Matrix(g,layout0);
		m1 = new Matrix(g,layout1);

		orientations.add(m0);
		orientations.add(m1);

		numOrientations = orientations.size();

		currentOrientation = Grfx.random(0,numOrientations-1);

		column = Grfx.random(0, numColumns - orientations.get(currentOrientation).getWidth());
		alignColors();
	}

	public void alignColors()
	{
		m1.getSquare(0,0).setColor(m0.getSquare(0,0).getColor());
		m1.getSquare(0,1).setColor(m0.getSquare(1,0).getColor());
		m1.getSquare(0,2).setColor(m0.getSquare(2,0).getColor());
		m1.getSquare(0,3).setColor(m0.getSquare(3,0).getColor());
	}
}


