import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MyButton extends Abutton
{
	private Image img;
	
	public MyButton(String someLabel, Color someColor, int someX, int someY,
			int someWidth, int someHeight, String fileName)
	{
		super(someLabel, someColor, someX, someY, someWidth, someHeight);
		
		this.img = readImage(fileName);
		
	}
	
	public void paint(Graphics pane)
	{
		super.paint(pane);		
		
		System.out.println(new Point(x,y));
		pane.drawImage(img, x + 1, y + 1, null);
	}
	
	public Image readImage(String fileName)
	{
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("resources/" + fileName + ".png"));
			//img = img.getScaledInstance(GUI.WINDOW_W, GUI.WINDOW_H, Image.SCALE_FAST);
		} catch (IOException e) {
		}

		return img;
	}
	
}
