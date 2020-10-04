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
	//JPanel panel0,panel,panel2;
	//JLabel l,l2;
	JTextArea textArea;
	JScrollPane scroll;
	public Editor_main()
	{
		frame = new JFrame();
		frame.setTitle("Texbelish"); 
		frame.setBounds(100, 100, 616, 444);
		
		
		/*
		l = new JLabel("TestPanel1");
		l2 = new JLabel("TestPanel2");
		panel= new JPanel();
		panel.setSize(616,50);
		panel.add(l);
		panel2 = new JPanel();
		panel2.setSize(616,50);
		panel2.add(l2);
		panel0 = new JPanel();
		panel0.setSize(616,300);
		panel0.setLayout(new BorderLayout());
		panel0.add(panel,BorderLayout.NORTH);
		panel0.add(panel2,BorderLayout.SOUTH);*/
		
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
