
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IButton extends JButton
{
	private String fileName;
	
	BufferedImage image;
	
	public IButton(String fileName)
	{
		this.fileName = fileName;
		
		try {
			image = ImageIO.read(getClass().getResource("resources/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);

	}

	@Override
	public Dimension getPreferredSize() {
		 return new Dimension(image.getWidth(), image.getHeight());
	}
}
