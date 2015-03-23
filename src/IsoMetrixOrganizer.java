import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class IsoMetrixOrganizer extends JFrame

{	
	private int xOffset, yOffset;
	
	private LinkedCollection cellGrid;
	
	private boolean show2D = false, showIso = false;
	
	private Point isoPt, twoDPt;
	
	public IsoMetrixOrganizer(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;

		cellGrid = new LinkedCollection();
		placeGrid();
	}

	private void placeGrid()	//in 2d
	{
		int width = 70, height = 70;
		int xP, yP;
		
		for (int i = 0; i < 9; i++)//cols
		{
			for (int j = 0; j < 9; j++) //rows
			{
				xP = (j * width);
				yP = (i * height);
				
				cellGrid.add(new Cell(xP, yP, width, height, (10 * i) + j, xOffset, yOffset));
			}
		}
		cellGrid.reset(null);
	}	
	
	public void check(Point isoClick)
	{
		boolean found = false;
		
		Cell collision = null;
		
		cellGrid.reset();
		Cell current = cellGrid.next();
		
		Point twoDClick = current.isoTo2D(isoClick, xOffset, yOffset);
		
		twoDPt = twoDClick;
		isoPt = isoClick;
		
		
		while (cellGrid.hasNext() && !found)
		{
			if (current.isInside(new Point(twoDClick.x - xOffset, twoDClick.y - yOffset)))	
			{
				collision = current;
				found = true;
				System.out.println("halp");
			}
			current = cellGrid.next();
					
		}
		cellGrid.reset(collision);
	}
	
	public void paint(Graphics pane)
	{	
		//painting the grid
		if (cellGrid != null)
		{
			cellGrid.paint(pane, show2D, showIso);
			System.out.println("painting");
		}
		
//		if (isoPt != null && twoDPt != null)
//			pane.drawLine(isoPt.x, isoPt.y, twoDPt.x, twoDPt.y);
	}
	

	public boolean getShow2D()
	{
		return this.show2D;
	}

	public void setShow2D(boolean b)
	{
		this.show2D = b;
		
	}		
	
	public boolean getShowIso()
	{
		return this.showIso;
		
	}
	
	public void setShowIso(boolean b)
	{
		this.showIso = b;
	}
}
