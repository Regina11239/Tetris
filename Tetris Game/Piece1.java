import java.awt.*;
import java.applet.*;
import java.util.*;

public class Piece1 extends TetrisPiece
{
	Matrix m0, m1, m2, m3;

	int layout0[][] = { {1,0},
					    {1,1},
					    {1,0} };

	int layout1[][] = { {1,1,1},
					    {0,1,0} };

	int layout2[][] = { {0,1},
					    {1,1},
					    {0,1} };

	int layout3[][] = { {0,1,0},
					    {1,1,1} };

	public Piece1(Graphics g)
	{
		super(g);

		m0 = new Matrix(g,layout0);
		m1 = new Matrix(g,layout1);
		m2 = new Matrix(g,layout2);
		m3 = new Matrix(g,layout3);

		orientations.add(m0);
		orientations.add(m1);
		orientations.add(m2);
		orientations.add(m3);

		numOrientations = orientations.size();

		currentOrientation = Grfx.random(0,numOrientations-1);

		column = Grfx.random(0, numColumns - orientations.get(currentOrientation).getWidth());
		alignColors();
	}

	public void alignColors()
	{
		m1.getSquare(0,0).copyColor(m0.getSquare(2,0));
		m1.getSquare(0,1).copyColor(m0.getSquare(1,0));
		m1.getSquare(0,2).copyColor(m0.getSquare(0,0));
		m1.getSquare(1,1).copyColor(m0.getSquare(1,1));

		m2.getSquare(0,1).copyColor(m0.getSquare(2,0));
		m2.getSquare(1,0).copyColor(m0.getSquare(1,1));
		m2.getSquare(1,1).copyColor(m0.getSquare(1,0));
		m2.getSquare(2,1).copyColor(m0.getSquare(0,0));

		m3.getSquare(1,0).copyColor(m0.getSquare(0,0));
		m3.getSquare(1,1).copyColor(m0.getSquare(1,0));
		m3.getSquare(1,2).copyColor(m0.getSquare(2,0));
		m3.getSquare(0,1).copyColor(m0.getSquare(1,1));
	}

}


