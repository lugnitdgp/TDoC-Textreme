package applicaton; // Package consists of classes and sub-packages
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.io.*;

import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;


public class TextEditor {
	private final String title = "Simple Text Editor";
	static JFrame frame;
	static JTextPane textArea;
	private JMenuBar menuBar;
	private JPanel panel;
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		textArea.setBackground(new java.awt.Color(39, 41, 43));
		textArea.setForeground(Color.LIGHT_GRAY);
		textArea.setCaretColor(Color.GREEN);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);

		JScrollPane scroll = new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 10, 0, 0));
		menuBar.setBackground(Color.BLACK);
		menuBar.setForeground(Color.WHITE);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu file = new JMenu("File");
		menuBar.add(file);
		menuBar.add(new JMenu("|")).setEnabled(false);
		file.setForeground(Color.CYAN);


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

		open.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				open();
			}
		});
		newFile.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				textArea.setText("");
			}
		});
		saveAs.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				saveAs();	
			}
		});

		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		edit.setForeground(Color.CYAN);


		JMenuItem copy = new JMenuItem("Copy");
		edit.add(copy);
		edit.addSeparator();

		JMenuItem cut = new JMenuItem("Cut");
		edit.add(cut);
		edit.addSeparator();

		JMenuItem pasteitem = new JMenuItem("Paste");
		edit.add(pasteitem);
		edit.addSeparator();

		cut.addActionListener(e->textArea.cut());
		copy.addActionListener(e->textArea.copy());
		pasteitem.addActionListener(paste->textArea.paste());


		JMenu exit = new JMenu("Exit");
		menuBar.add(exit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		JMenuItem quit = new JMenuItem("Quit without Saving");
		exit.add(quit);
		exit.setForeground(Color.CYAN);

		quit.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);	
			}
		});


		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setSize(100, 100);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		JButton bold = new JButton();
		bold.setText("Bold");
		bold.setBackground(Color.BLACK);
		bold.setForeground(Color.CYAN);

		JButton italics = new JButton();
		italics.setText("Italics");
		italics.setBackground(Color.BLACK);
		italics.setForeground(Color.CYAN);

		panel.add(bold);
		panel.add(italics);


	}

	public static void open()
	{	textArea.setText("");
	JFileChooser j = new JFileChooser("f:"); 

	// Invoke the showsOpenDialog function to show the save dialog 
	int r = j.showOpenDialog(null); 

	// If the user selects a file 
	if (r == JFileChooser.APPROVE_OPTION) { 
		// Set the label to the path of the selected directory 
		File fi = new File(j.getSelectedFile().getAbsolutePath()); 

		try { 
			// String 
			String s1 = "", sl = ""; 

			// File reader 
			FileReader fr = new FileReader(fi); 

			// Buffered reader 
			BufferedReader br = new BufferedReader(fr); 

			// Initialize s1 
			sl = br.readLine(); 

			// Take the input from the file 
			while ((s1 = br.readLine()) != null) { 
				sl = sl + "\n" + s1; 
			} 

			// Set the text 
			textArea.setText(sl); 
		} 
		catch (Exception evt) { 
			JOptionPane.showMessageDialog(frame, evt.getMessage()); 
		} 
	} 
	// If the user cancelled the operation 
	else
		JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
	}

	public static void saveAs()
	{
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 

		int r = j.showOpenDialog(null); 

		try {
			File myObj = new File(j.getSelectedFile().getAbsolutePath());
			if (myObj.createNewFile()) {
				FileWriter myWriter = new FileWriter(j.getSelectedFile().getAbsolutePath());
				myWriter.write(textArea.getText());
				myWriter.close(); 
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
