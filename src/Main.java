import javax.swing.JFrame;
import java.awt.Color;

public class Main extends JFrame
{
	public Main()
	{
		add(new Board());
		setTitle("Bubble Breaker");
		setLocation(100,100);
	    setSize(393,388);
	    setVisible(true);
	    setIgnoreRepaint(true);
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBackground(Color.white);
	}
	public static void main(String[]args){
		new Main();
		}
	}