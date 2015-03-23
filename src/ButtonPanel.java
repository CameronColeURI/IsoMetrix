import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class ButtonPanel extends JPanel
{
	GamePanel game;
	
	JButton drawIso, draw2D;	//declares the buttons for the button JPanel
	
	public ButtonPanel(GamePanel game)
	{
		drawIso = new JButton("Draw Iso");
		draw2D = new JButton("Draw 2D");
		
		drawIso.setForeground(Color.blue);
		draw2D.setForeground(Color.red);
		
		this.game = game;
		
		setup();
		setVisible(true);
	}
	
	public void setup()
	{
		drawIso.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				game.iSorganizer.setShowIso(!game.iSorganizer.getShowIso());
				getParent().repaint();
				
			}
		});
		
		draw2D.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				game.iSorganizer.setShow2D(!game.iSorganizer.getShow2D());
				getParent().repaint();
			}
		});
		
		
		this.add(drawIso);
		this.add(draw2D);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}