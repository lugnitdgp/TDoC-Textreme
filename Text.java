import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.*;

public class Text {
	private final String title = "Simple Text Editor";
	private JFrame frame;
	private JTextPane textArea;
	private JMenuBar menuBar;
	private JMenu file,edit,exit;
	private JMenuItem newFile,open,save,saveas,close,cut,copy,paste;
	private JPanel panel;
	private JButton bold;
	
	public static void main(String args[]) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {

						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						Text window = new Text();
						window.frame.setLocationRelativeTo(null);
						window.frame.setVisible(true);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
	public Text() {
		initialize();
	}
	
	public void initialize() {
		frame = new JFrame("Editor");
		frame.setAutoRequestFocus(false);
		frame.setTitle(title);
		frame.setBounds(100,100,616,444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0,0));
		
		textArea = new JTextPane();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		

		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 5, 0, 0));
		menuBar.setBackground(Color.BLACK);
		menuBar.setForeground(Color.WHITE);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		file = new JMenu("File");
		menuBar.add(file);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		newFile = new JMenuItem("New");
		file.add(newFile);
		file.addSeparator();
		open = new JMenuItem("Open");
		file.add(open);
		file.addSeparator();
		save = new JMenuItem("Save");
		file.add(save);
		file.addSeparator();
		saveas = new JMenuItem("Save As");
		file.add(saveas);
		file.addSeparator();
		close = new JMenuItem("Close");
		file.add(close);
		file.addSeparator();
		
		edit = new JMenu("Edit");
		menuBar.add(edit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		cut = new JMenuItem("Cut");
		file.add(cut);
		file.addSeparator();
		copy = new JMenuItem("Copy");
		file.add(copy);
		file.addSeparator();
		paste = new JMenuItem("Paste");
		file.add(paste);
		file.addSeparator();
		
		exit = new JMenu("Exit");
		menuBar.add(exit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setSize(100, 100);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		bold = new JButton();
		bold.setText("Bold");
		bold.setBackground(new java.awt.Color(0, 140, 255));
		
		
	}
	
}
						
