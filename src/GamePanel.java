import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GamePanel extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	private Image image = null;
	private int iWidth2;
	private int iHeight2;
	
	private Hero hero;
	
	public IsoMetrixOrganizer iSorganizer;

	public GamePanel()
	{
		this.image = readImage("blankTest", 800, 600);
		this.iWidth2 = image.getWidth(this)/2;
		this.iHeight2 = image.getHeight(this)/2;
		
		addMouseListener(this);
			
		iSorganizer = new IsoMetrixOrganizer(iWidth2, -50);
		
		setVisible(true);
	}


	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setDoubleBuffered(true);
		
		//System.out.println("didk");
		
		if (image != null)
		{
			//System.out.println("didk");
			
			int x = this.getParent().getWidth()/2 - iWidth2;
			int y = this.getParent().getHeight()/2 - iHeight2;
			g.drawImage(image,x,y,this);
		}
		
		iSorganizer.paint(g);
		
		if (hero != null)
			g.drawImage(hero.getDefaultImg(), hero.getX(), hero.getY(), this);
	}
	
	public Image readImage(String fileName, int width, int height)
	{
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("resources/" + fileName + ".png"));
			img = img.getScaledInstance(width,height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
		}

		return img;
	}
	
	public void setHero(Hero h)
	{
		this.hero = h;
	}
	
	/**
	 * Obvious MouseListener implementations
	 */
	public void mouseClicked(MouseEvent event)
	{
		iSorganizer.check(isoClick);	
		repaint();
	}

	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}

	
	private void flipWhenInside()
	{

		repaint();
	}

	@Override
	public void mousePressed(MouseEvent event)
	{
		isoClick.x = event.getX();	
		isoClick.y = event.getY();
		
		//System.out.println(isoClick);
		
		flipWhenInside();	
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
		flipWhenInside();	
	}
	
	private Point isoClick = new Point();
}
