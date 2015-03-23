import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class HeroCreation extends JComponent implements MouseListener
{
	private Hero hero;
	
	private MyButton warrior, mage, archer;
	
	private String fileName;
	
	private static final long serialVersionUID = 1L;
	private Image image = null;
	private int iWidth2;
	private int iHeight2;
	
	public HeroCreation(String fileName)
	{
//		super(new GridBagLayout());
//		GridBagConstraints d = new GridBagConstraints();
		
		this.fileName = fileName;
		
		this.image = readImage(fileName, 800, 600);
		this.iWidth2 = image.getWidth(this)/2;
		this.iHeight2 = image.getHeight(this)/2;
		
		warrior =  new MyButton("", new Color(1,1,1), 320, 150, 127 , 79, "button2");
		mage = new MyButton("", new Color(1,1,1), 320, 250, 128, 79, "button3");
		archer = new MyButton("", new Color(1,1,1), 320, 350, 128, 79, "button4");		
		
		addMouseListener(this);
		
		setVisible(true);
		
	}
	
	private void assignHero(Hero hero)
	{
		this.hero = hero;
		
		hideThisStuff();
	}
	
	public Hero getHero()
	{
		return this.hero;
	}
	
	private void hideThisStuff()
	{
		warrior = null;
		mage = null;
		archer = null;
		
		removeMouseListener(this);
		
		setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//setDoubleBuffered(true);
		
		if (image != null)
		{
			int x = this.getParent().getWidth()/2 - iWidth2;
			int y = this.getParent().getHeight()/2 - iHeight2;
			g.drawImage(image,x,y,this);
		}
		
		if (warrior != null)
			warrior.paint(g);
		if (mage != null)
			mage.paint(g);
		if (archer != null)
			archer.paint(g);

	}
	
	public Image readImage(String fileName, int width, int height)
	{
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("resources/" + fileName + ".png"));
			img = img.getScaledInstance(width, height, Image.SCALE_FAST);
		} catch (IOException e) {
		}

		return img;
	}
	
	private void check()
	{
		if (warrior.isInside(click.x, click.y))
		{
			assignHero(new Warrior("warrior", readImage("blankTest", 90, 156), new Point(220,200)));
		}
		else if (mage.isInside(click.x, click.y))
		{
			assignHero(new Mage("warrior", readImage("testHero", 90, 156), new Point(220,200)));
		}
		else if (archer.isInside(click.x, click.y))
		{
			assignHero(new Archer("warrior", readImage("testHero", 90, 156), new Point(220,200)));
		}
	}
	
	/**
	 * Obvious MouseListener implementations
	 */
	public void mouseClicked(MouseEvent event)
	{
		check();
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
		click.x = event.getX();	
		click.y = event.getY();
		
		//System.out.println(isoClick);
		
		flipWhenInside();	
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
		flipWhenInside();	
	}
	
	private Point click = new Point();
}
