import java.awt.*;


public class Unit
{
	protected String name;
	
	protected Image defaultImg;
	protected Image[] images;
	
	protected IsoMetrixOrganizer iSorg;
	
	protected int hp, ar, dx, dy;
	
	protected Point pt;	//pos
	
	public Unit(){} //def constructor
	
	//quick constructor
	public Unit(String name, Image img, Point pt)
	{
		this.name = name;
		this.defaultImg = img;
		this.pt = pt;
	}
	
	//full constructor
	public Unit(IsoMetrixOrganizer iSorg, String name, Image img, Point pt,
			int hp, int ar, int dx, int dy)
	{
		this.iSorg = iSorg;
		
		this.name = name;
		this.defaultImg = img;
		this.pt = pt;
		this.hp = hp;
		this.ar = ar;
		this.dx = dx;
		this.dy = dy;
	}
	
	private boolean isDead()
	{
		return (hp < 0);
	}
	
	private void attack(Unit u)
	{
		
	}
	
	private void displayHp(Color c)
	{
		
	}
	
	private void findImages()
	{
		
	}
	

	public Image getDefaultImg()
	{
		return this.defaultImg;
	}
	
	public void setDefaultImg(Image i)
	{
		this.defaultImg = i;
	}
	
	public int getX()
	{
		return pt.x;
	}
	
	public void setX(int x)
	{
		pt.x = x;
	}
	
	public int getY()
	{
		return pt.y;
	}
	
	public void setY(int y)
	{
		pt.y = y;
	}
	
	public void setLocation(int x, int y)
	{
		pt.x = x;
		pt.y = y;
	}
}
