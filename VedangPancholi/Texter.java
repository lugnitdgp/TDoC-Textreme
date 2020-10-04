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
			t.setForeground(new java.awt.Color(160,137,255));
			
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			JMenuBar mb = new JMenuBar();
			mb.setMargin(new Insets(0, 5, 0, 0));
			mb.setBackground(Color.BLACK);
			mb.setForeground(Color.WHITE);
			f.getContentPane().add(mb, BorderLayout.NORTH);
			JMenu file = new JMenu("File");
			
			JMenu edit = new JMenu("Edit");
			
			JMenu exit = new JMenu("Exit");
			
			mb.add(file);
			mb.add(new JMenu("|")).setEnabled(false);
			mb.add(edit);
			mb.add(new JMenu("|")).setEnabled(false);
			mb.add(exit);
			JMenuItem newFile = new JMenuItem("New");
			file.add(newFile);
			file.addSeparator();
			JMenuItem openFile = new JMenuItem("Open");
			file.add(openFile);
			file.addSeparator();
			JMenuItem saveFile = new JMenuItem("Save");
			file.add(saveFile);
			file.addSeparator();
			JMenuItem saveasFile = new JMenuItem("Save As");
			file.add(saveasFile);
			file.addSeparator();
			JMenuItem closeFile = new JMenuItem("Close");
			file.add(closeFile);
			JMenuItem copyEdit = new JMenuItem("Copy");
			edit.add(copyEdit);
			edit.addSeparator();
			JMenuItem cutEdit = new JMenuItem("Cut");
			edit.add(cutEdit);
			edit.addSeparator();
			JMenuItem pasteEdit = new JMenuItem("Paste");
			edit.add(pasteEdit);
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setSize(100, 100);
			f.getContentPane().add(panel, BorderLayout.SOUTH);
			JButton bold = new JButton();
			bold.setText("Bold");
			bold.setBackground(new java.awt.Color(247,197,197));
			panel.add(bold);
			JButton italic = new JButton();
			italic.setText("Italics");
			italic.setBackground(new java.awt.Color(247,197,197));
			panel.add(italic);
	}
	public static void main(String[]args)
	{
		Texter ob = new Texter();
	}
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
