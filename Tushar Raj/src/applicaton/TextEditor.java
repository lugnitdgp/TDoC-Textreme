package applicaton; // Package consists of classes and sub-packages
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class TextEditor {
	private final String title = "Simple Text Editor";
	private JFrame frame;
	private JTextPane textArea;
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					TextEditor window = new TextEditor();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TextEditor() {
		initialize();
	}
	
	public void initialize() {
		frame = new JFrame("Editor");
		frame.setAutoRequestFocus(false);
		frame.setTitle(title);
		frame.setBounds(100, 100, 616, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextPane();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setCaretColor(Color.BLACK);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 5, 0, 0));
		menuBar.setBackground(Color.BLACK);
		menuBar.setForeground(Color.WHITE);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		JMenuItem newFile = new JMenuItem("New");
		file.add(newFile);
		file.addSeparator();
		
		JMenuItem open = new JMenuItem("Open");
		file.add(open);
		file.addSeparator();
		
		JMenuItem save = new JMenuItem("Save");
		file.add(save);
		file.addSeparator();
		
		JMenuItem saveAs = new JMenuItem("Save As");
		file.add(saveAs);
		file.addSeparator();
		
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		JMenuItem copy = new JMenuItem("Copy");
		edit.add(copy);
		edit.addSeparator();
		
		JMenuItem cut = new JMenuItem("Cut");
		edit.add(cut);
		edit.addSeparator();
		
		JMenuItem paste = new JMenuItem("Paste");
		edit.add(paste);
		edit.addSeparator();
		
		JMenu exit = new JMenu("Exit");
		menuBar.add(exit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setSize(100, 100);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton bold = new JButton();
		bold.setText("Bold");
		bold.setBackground(new java.awt.Color(0, 140, 255));
		
		JButton italics = new JButton();
		italics.setText("Italics");
		italics.setBackground(new java.awt.Color(0, 140, 255));
		
		panel.add(bold);
		panel.add(italics);
	}
	
}