package application;

import java.awt.*;
import javax.swing.*;
import java.io.*;


public class TextEditor {
	JFrame frame;
	JScrollPane scroll;
	JTextPane textArea;
	JMenuBar menubar;
	JButton bold;
	JButton italic;
	
	ImageIcon image = new ImageIcon("C:\\Users\\SANKHADEEP GHOSH\\eclipse-workspace\\Text_Editor\\icons\\save.png");
	
	TextEditor(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
			System.out.println("exception in setlookandfeel.");
		}
		
		
		frame = new JFrame("Text Editor");
		frame.setBounds(100, 100, 616, 444);
		
			
		
		textArea = new JTextPane();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setBounds(100, 100, 100, 100);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		
		JScrollPane scroll= new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		Font font = new Font("Bookman Old Style", Font.ITALIC, 18);
		textArea.setFont(font);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 5, 0, 0));
		menuBar.setBackground(Color.WHITE);
		menuBar.setForeground(Color.BLACK);
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
		JMenuItem save = new JMenuItem("Save", image);
		file.add(save);
		file.addSeparator();
		JMenuItem saveas = new JMenuItem("Save As");
		file.add(saveas);
		
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
		
		JMenu exit = new JMenu("Exit");
		menuBar.add(exit);		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setSize(100, 100);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
//		panel.add(new Button("Okay"), BorderLayout.SOUTH);
		
		bold = new JButton();
		bold.setText("BOLD");
		panel.add(bold);
		bold.setBackground(new java.awt.Color(255, 255, 155));
		
		italic = new JButton();
		italic.setText("ITALIC");
		panel.add(italic);
		italic.setBackground(new java.awt.Color(255, 255, 255));
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
	
		TextEditor ob = new TextEditor();
		ob.frame.setLocationRelativeTo(null);
		ob.frame.setVisible(true);
		
	}

}
