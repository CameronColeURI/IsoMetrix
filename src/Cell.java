import java.awt.*;


public class Cell
{
	private int x, y;
	
	Point[] twoDPts, isoPts;
	
	private int width, height;
	
	private int num;
	
	private boolean highlight = false;
	
	private int xOffset, yOffset;
	
	public Cell(int x, int y, int width, int height, int num, int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		this.width = width;
		this.height = height;
		
		this.x = x;	//2d pts
		this.y = y;
		
		twoDPts = new Point[]{
				new Point(x + xOffset,y + yOffset),	//top left corner
				new Point(x + xOffset + width, y + yOffset), //top right corner
				new Point(x + xOffset + width, y + height + yOffset), //bot right corner
				new Point(x + xOffset, y + height + yOffset) //bot left corner			
		};
		
		isoPts = new Point[]{
				twoDToIso(twoDPts[0], xOffset, yOffset),
				twoDToIso(twoDPts[1], xOffset, yOffset),
				twoDToIso(twoDPts[2], xOffset, yOffset),
				twoDToIso(twoDPts[3], xOffset, yOffset)
		};
			
		this.num = num;
	}
	
	public Point twoDToIso(Point pt, int xOffset, int yOffset)
	{
		Point tempPt = new Point(x,y);
		
		pt.x -= xOffset;
		pt.y -= yOffset;

		tempPt.x = (pt.x - pt.y);
		tempPt.y = (pt.x + pt.y)/2;
		
		tempPt.x += xOffset; 
		tempPt.y += yOffset;
		
		pt.x += xOffset;
		pt.y += yOffset;

		return tempPt;
	}
	
	public Point isoTo2D(Point pt, int xOffset, int yOffset)
	{
		Point tempPt = new Point(x,y);
		
		pt.x -= xOffset;
		pt.y -= yOffset;
		
		tempPt.x = (2 * pt.y + pt.x)/2;
		tempPt.y = (2 * pt.y - pt.x)/2;
		
		pt.x += xOffset;
		pt.y += yOffset;
		
		tempPt.x += xOffset;
		tempPt.y += yOffset;
		//System.out.println(tempPt);
		
		return tempPt;
	}
	
	public boolean isInside(Point pt)
	{
		boolean inside = false;
		
		if ((pt.x > x) && (pt.x < (x + width)))
			if ((pt.y > y) && (pt.y < (y + height)))
				inside = true;
		
		return inside;
	}
	
	public void paint(Graphics pane, boolean draw2D, boolean drawIso)
	{
		if (draw2D)
		{
			int[] xP = new int[]{twoDPts[0].x, twoDPts[1].x, twoDPts[2].x,
					twoDPts[3].x};

			int[] yP = new int[]{twoDPts[0].y, twoDPts[1].y,
					twoDPts[2].y, twoDPts[3].y};

			pane.setColor(Color.red);
			pane.drawPolygon(xP, yP, 4);
			
			if (highlight)
				pane.fillPolygon(xP, yP, 4);
			
			Font f = new Font("Text", Font.BOLD, 20);
			pane.setFont(f);
//			pane.drawString("" + num, x + width/2, y + height/2);
		}

		if (drawIso)
		{		
			pane.setColor(Color.blue);

			int[] iX = new int[]{isoPts[0].x, isoPts[1].x, isoPts[2].x,
					isoPts[3].x};

			int[] iY = new int[]{isoPts[0].y, isoPts[1].y,
					isoPts[2].y, isoPts[3].y};

			pane.drawPolygon(iX, iY, 4);
			
			if (highlight)
				pane.fillPolygon(iX, iY, 4);
			
		}

		Font d = new Font("Text", Font.PLAIN, 12);
		pane.setFont(d);
		
	}
	
	
	public void setHighlight(boolean h)
	{
		this.highlight = h;
	}
	
	
}
