import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;


public class TetrisGame extends Applet implements KeyListener, FocusListener
{
	private int appletWidth;				
  	private int appletHeight;				
	private Graphics g, gBuffer;			
   private Image virtualMem;     		
	private Background background;

	private TetrisPiece currentPiece, nextPiece;

	private boolean focus;					
	private boolean gameOver;
    private Font scoreFont, gameOverFont, peFont;
    private int score;
    private int image;					
    private DecimalFormat scoreDisplay;
    private boolean dropped;
   
   private Image bgImage, titleImage;

	public void init()
	{
  		appletWidth = getWidth();
		appletHeight = getHeight();

		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();
		background = new Background(gBuffer);

		currentPiece = getNewPiece();
		nextPiece    = getNewPiece();
      
      bgImage = getImage(getDocumentBase(),"bgImage.PNG");
      titleImage = getImage(getDocumentBase(),"titleImage.PNG");

		addKeyListener(this);
		addFocusListener(this);
		focus = false;				
		gameOver = false;
		gameOverFont = new Font("Arial Narrow",Font.BOLD,72);
		score = 0;
		scoreFont = new Font("Calibri",Font.BOLD,48);
		scoreDisplay = new DecimalFormat("0,000,000");
		peFont = new Font("Arial Narrow",Font.BOLD,36);
		dropped  = false;
		image = 0;
	}

	public void paint(Graphics g)
	{
		this.g = g;  
		image++;    

		if (!focus)
			titleScreen();  
		else
		{
			displayEverything(g);
			if (currentPiece.canFall())
			{
				currentPiece.fall();
				dropped = false;
			}
			else
			{
				currentPiece.putInGrid();
				currentPiece = nextPiece;
				nextPiece = getNewPiece();
				dealWithCompleteRows(g);
			}
		}
		g.drawImage(virtualMem,0,0,this);
		checkIfGameIsOver();
		if (!gameOver)
			repaint();
		else
			endGame();
	}

	public void titleScreen()
	{
		gBuffer.drawImage(titleImage, 0, 0, appletWidth, appletHeight, this);
		Font title = new Font("Candara",Font.PLAIN,100);
		Font directions = new Font("Times New Roman",Font.BOLD,36);
		Font keys = new Font("Arial",Font.PLAIN,24);
		gBuffer.setFont(title);
		gBuffer.setColor(new Color(32, 206, 137));
		gBuffer.drawString("TETRIS",338,205);
		gBuffer.setFont(new Font("Arial", Font.ITALIC, 24));
      gBuffer.drawString("FOREST VERSION",650,205);
      gBuffer.setFont(directions);
		gBuffer.drawString("Click in the window to begin/resume play.",188,320);
		gBuffer.setFont(keys);
		gBuffer.drawString("<Right Arrow> = Move Right",250,390);
		gBuffer.drawString("<Left Arrow> = Move Left",250,430);
		gBuffer.drawString("<Down Arrow> = Drop Piece",250,470);
		gBuffer.drawString("<Page Up> = Rotate Piece Clockwise",250,510);
		gBuffer.drawString("<Page Down> = Rotate Piece Counter Clockwise",250,550);
  	}

	public void focusGained(FocusEvent evt) { focus = true; }

	public void focusLost(FocusEvent evt)   { focus = false; }

	public void keyReleased(KeyEvent e)     { }
	public void keyTyped(KeyEvent e)        { }

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
		{
			if (currentPiece.canMoveLeft())
				currentPiece.moveLeft();
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			if (currentPiece.canMoveRight())
				currentPiece.moveRight();
		}

		if (key == KeyEvent.VK_DOWN)
		{
			currentPiece.drop();
			dropped = true;
		}

		if (key == KeyEvent.VK_PAGE_UP)
		{
			if (currentPiece.canRotateClockwise())
				currentPiece.rotateClockwise();
		}

		if (key == KeyEvent.VK_PAGE_DOWN)
		{
			if (currentPiece.canRotateCounterClockwise())
				currentPiece.rotateCounterClockwise();
		}

		if (key == KeyEvent.VK_ESCAPE)
		{
			gameOver = true;
		}

	}

	public void checkIfGameIsOver()
	{
		if (currentPiece.getRow() == 0 && !currentPiece.canFall())
			gameOver = true;
	}

	public void endGame()
	{
		currentPiece.display();
		gBuffer.setFont(gameOverFont);
		gBuffer.setColor(Color.white);
		gBuffer.drawString("Game Over!",610,575);
		g.drawImage (virtualMem,0,0,this);
	}

	public TetrisPiece getNewPiece()
	{
		TetrisPiece newPiece = new Piece1(gBuffer);

		int pieceNumber = Grfx.random(1,7);

		switch (pieceNumber)
		{
			case 2 : newPiece = new Piece2(gBuffer); break;
			case 3 : newPiece = new Piece3(gBuffer); break;
			case 4 : newPiece = new Piece4(gBuffer); break;
			case 5 : newPiece = new Piece5(gBuffer); break;
			case 6 : newPiece = new Piece6(gBuffer); break;
			case 7 : newPiece = new Piece7(gBuffer);
		}

		return newPiece;
	}

	public void dealWithCompleteRows(Graphics g)
	{
		displayEverything(g);
		g.drawImage (virtualMem,0,0,this);

		int numCompletedRows = background.markCompleteRows();
		if (numCompletedRows > 0)
		{
			int pointsEarned = numCompletedRows * numCompletedRows * 150;
			if (dropped)
				pointsEarned *= 2;

			Grfx.delay(100);
			displayEverything(g);
			displayPointsEarned(pointsEarned,Color.gray);
			g.drawImage (virtualMem,0,0,this);
			Grfx.delay(100);

			for (int j = 1; j <= 4; j++)
			{
				background.flashOn();
				displayEverything(g);
				displayPointsEarned(pointsEarned,Color.white);
				g.drawImage (virtualMem,0,0,this);
				Grfx.delay(100);
				background.flashOff();
				displayEverything(g);
				displayPointsEarned(pointsEarned,Color.gray);
				g.drawImage (virtualMem,0,0,this);
				Grfx.delay(100);
			}

			background.deleteRows();
			score += pointsEarned;
			GameThing.makeGameFaster();
		}
	}

	public void displayPointsEarned(int pe, Color peColor)
	{
		gBuffer.setFont(peFont);
		gBuffer.setColor(peColor);
		gBuffer.setFont(peFont);

		if (pe == 150 || pe == 300)
			gBuffer.drawString("Row Completed!",640,541);
		else
			gBuffer.drawString("Multi-Row BONUS!",640,541);

		gBuffer.drawString(pe + " Points Earned!",640,587);
	}

	public void displayEverything(Graphics g)
	{
		gBuffer.drawImage(bgImage, 0, 0, appletWidth, appletHeight, this);
      background.display(image); 
		currentPiece.display();
		nextPiece.displayNext();
		displayScore();
	}

	public void displayScore()
	{
		gBuffer.setFont(scoreFont);
		gBuffer.setColor(Color.white);
		gBuffer.drawString(scoreDisplay.format(score),637,439);
	}

	public void update(Graphics g)
	{
		paint(g);
	}

}