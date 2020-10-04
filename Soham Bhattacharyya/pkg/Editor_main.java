package pkg;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 


/*Initial name for the text editor is "Texbelish"*/


class Editor_main 
	extends JFrame 
	implements ActionListener
{
	JFrame frame;
	
	JTextArea textArea;
	//JMenuBar menubar;
	JScrollPane scroll;
	public Editor_main()
	{
		
		
		frame = new JFrame();
		frame.setTitle("Texbelish"); 
		frame.setBounds(100, 100, 616, 444);
		
		
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		textArea.setLineWrap(true);
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		
		scroll = new JScrollPane(textArea);
		scroll.setLocation(10,210);
		scroll.setSize(500,400);

		//frame.add(panel0, BorderLayout.NORTH);

		frame.add(scroll,BorderLayout.CENTER);
		//frame.add(menubar,BorderLayout.NORTH);
		
	}
	public static void main(String args[])throws Exception
	{
		String x=UIManager.getSystemLookAndFeelClassName();
		UIManager.setLookAndFeel(x);
		Editor_main window = new Editor_main();
		window.frame.setLocationRelativeTo(null);
		window.frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
	}
}
