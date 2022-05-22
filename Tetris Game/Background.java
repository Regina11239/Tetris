import java.awt.*;
import java.applet.*;


public class Background extends GameThing
{

	Font bgFont;
  
	public Background(Graphics g)
	{
		super(g);

		playSpace = new int[numRows+5][numColumns+5];

		for (int r = 0; r < numRows+5; r++)
			for (int c = 0; c < numColumns+5; c++)
				if (r < numRows && c < numColumns)
					playSpace[r][c] = 0;
				else
					playSpace[r][c] = -1;

		bgFont = new Font("calibri",Font.BOLD,32);

	}

   
	public void display(int image) 
  	{
 		int x1 = 7 * squareSize;
		int y1 = 20;
		int x2 = x1 + numColumns * squareSize;
		int y2 = y1 + numRows * squareSize;
		g.setColor(new Color(10, 91, 69));
		Grfx.fillFrame(g,x1,y1,x2,y2);

		g.setFont(bgFont);
		g.setColor(Color.white);
		g.drawString("Next",640,150);
		g.drawString("Piece:",710,150);
		g.drawString("Score:",640,385);                                                                       

		drawGrid();
	}

	private	void drawGrid()
	{
		for (int r = 0; r < numRows; r++)
			for (int c = 0; c < numColumns; c++)
				if (playSpace[r][c] != 0)
					Square.display(g,r,c,playSpace[r][c]);
	}

	public int markCompleteRows()
	{
		int numMarked = 0;
		for (int r = numRows-1; r >= 0; r--)
		{
			boolean complete = true;
			for (int c = 0; c < numColumns; c++)
				if (playSpace[r][c] == 0)
					complete = false;

			if (complete)
			{
				numMarked++;
				for (int c = 0; c < numColumns; c++)
					playSpace[r][c] = 7;  
  			}
		}
		return numMarked;
	}

	public void deleteRows()
	{
		for (int r = 0; r < numRows; r++)
			for (int c = 0; c < numColumns; c++)
				if (playSpace[r][c] == 7)
				{
					for (int r2 = r; r2 > 0; r2--)
					    playSpace[r2][c] = playSpace[r2-1][c];
				    playSpace[0][c] = 0;
				}
	}

	public void flashOn()
	{
		for (int r = numRows-1; r >= 0; r--)
			for (int c = 0; c < numColumns; c++)
				if (playSpace[r][c] == 7)  
					playSpace[r][c] = -1;
	}

	public void flashOff()
	{
		for (int r = numRows-1; r >= 0; r--)
			for (int c = 0; c < numColumns; c++)
				if (playSpace[r][c] ==-1)  
					playSpace[r][c] = 7;
	}

}


