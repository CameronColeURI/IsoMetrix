import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WindowOrganizer extends JPanel implements ComponentListener
{
	private Hero hero;

	private JPanel mainPanel = new JPanel();

	private GamePanel gamePanel;
	private ButtonPanel butPanel;
	private MainMenu mainMenu;
	private HeroCreation heroCreate;
	
	private Dimension dim = new Dimension(800,600);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame window = new JFrame("IsoMetrix");
				window.add(new WindowOrganizer().getMainPanel());
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				window.pack();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
			}
		});
	}

	public WindowOrganizer()
	{
		mainPanel.setLayout(new BorderLayout());
		
		mainMenu = new MainMenu("menu0");	
		mainMenu.addComponentListener(this);
		mainMenu.setPreferredSize(dim);
		
		heroCreate = new HeroCreation("menu1");	
		heroCreate.addComponentListener(this);
		heroCreate.setPreferredSize(dim);
		
		gamePanel = new GamePanel();
		gamePanel.addComponentListener(this);
		gamePanel.setPreferredSize(dim);
		
		butPanel = new ButtonPanel(gamePanel);
		butPanel.addComponentListener(this);
		
		mainPanel.add(mainMenu, BorderLayout.CENTER);
		
		setVisible(true);
	       
	}

	public JPanel getMainPanel()
	{
		return mainPanel;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e)
	{
		if (e.getSource() == mainMenu)
		{
			mainPanel.remove(mainMenu);
			mainPanel.add(heroCreate, BorderLayout.CENTER);
		}
		
		if (e.getSource() == heroCreate)
		{
			hero = heroCreate.getHero();
			System.out.println(hero);
			gamePanel.setHero(hero);
			
			mainPanel.remove(heroCreate);

			System.out.println("HALP");
						
			mainPanel.add(gamePanel, BorderLayout.CENTER);
			mainPanel.add(butPanel, BorderLayout.NORTH);
			
			mainPanel.repaint();
			mainPanel.revalidate();
			
		}
		
		mainPanel.repaint();
		mainPanel.revalidate();
		
	}
}


