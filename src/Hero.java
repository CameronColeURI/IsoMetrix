import java.awt.Image;
import java.awt.Point;


public class Hero extends Unit
{
	public Hero(String name, Image img, Point pt)
	{
		this.name = name;
		this.defaultImg = img;
		this.pt = pt;
	}
}
