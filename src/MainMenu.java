import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu extends JPanel
{
	private JButton start, quit;
	
	private String fileName;
	
	private static final long serialVersionUID = 1L;
	private Image image = null;
	private int iWidth2;
	private int iHeight2;
	
	MainMenu(String fileName)
	{
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.fileName = fileName;
		
		this.image = readImage(fileName);
		this.iWidth2 = image.getWidth(this)/2;
		this.iHeight2 = image.getHeight(this)/2;
		
		start =  new IButton("button0.png");
		quit = new IButton("button1.png");
		
		c.insets = new Insets(0, 150, 300, 0);
		c.weightx = .5;
		c.gridx = 2;
		c.gridy = 0;
		add(start, c);
		
		c.insets = new Insets(400, 0, 0, 200);
		c.weightx = .7;
		c.gridx = 3;
		c.gridx = 4;
		
		add(quit, c);
		
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				hideThisStuff();
				
			}
		});
		
		quit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		setVisible(true);
	}
	
	private void hideThisStuff()
	{
		setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setDoubleBuffered(true);
		
		if (image != null)
		{
			int x = this.getParent().getWidth()/2 - iWidth2;
			int y = this.getParent().getHeight()/2 - iHeight2;
			g.drawImage(image,x,y,this);
		}

	}
	
	public Image readImage(String fileName)
	{
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("resources/" + fileName + ".png"));
			img = img.getScaledInstance(800, 600, Image.SCALE_FAST);
		} catch (IOException e) {
		}

		return img;
	}
}
