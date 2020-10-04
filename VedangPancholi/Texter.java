package text_editor;
import java.awt.*; 
import javax.swing.*; 
import java.io.*; 
import java.awt.event.*; 
import javax.swing.plaf.metal.*; 
import javax.swing.text.*; 
public class Texter extends JFrame implements ActionListener
{
	JTextArea t;
	 
	JFrame f;
	Texter()
	{
		try { 
		      
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme()); 
        } 
        catch (Exception e) { 
        }
		
			f = new JFrame("Text Editor");
			f.setBounds(100, 100, 616, 444);
			Font font = new Font("Segoe Script", Font.BOLD, 20);
			t= new JTextArea();
			f.getContentPane().add(t,BorderLayout.NORTH);
			JScrollPane s = new JScrollPane(t);
			f.getContentPane().add(s,BorderLayout.CENTER);
			t.setFont(font);
			t.setBounds(100, 100, 100,100);
			
			t.setBackground(Color.WHITE);
			t.setForeground(Color.BLUE);
			
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		
	}
	public static void main(String[]args)
	{
		Texter ob = new Texter();
	}
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
