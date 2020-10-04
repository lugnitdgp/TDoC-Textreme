package application;

import java.awt.*;
import javax.swing.*;
import java.io.*;


public class TextEditor {
	JFrame frame;
	JScrollPane scroll;
	JTextPane textArea;
	
	
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
		
		JScrollPane scroll= new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		textArea = new JTextPane();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
//		textArea.setBounds(100, 100, 0, 0);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		Font font = new Font("Serif", Font.ITALIC, 18);
		textArea.setFont(font);
	}
	
	public static void main(String[] args) {
	
		TextEditor ob = new TextEditor();
		ob.frame.setLocationRelativeTo(null);
		ob.frame.setVisible(true);
	}

}


//getLookAndFeelDefaults()