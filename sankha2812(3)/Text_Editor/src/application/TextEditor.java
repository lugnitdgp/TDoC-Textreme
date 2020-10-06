package application;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;

public class TextEditor implements ActionListener {
	JFrame frame;
	JScrollPane scroll;
	JTextPane textArea;
	JMenuBar menubar;
	JButton bold;
	JButton italic;
	JButton dMode;
	String fname=null;
	
	ImageIcon image = new ImageIcon("C:\\Users\\SANKHADEEP GHOSH\\eclipse-workspace\\Text_Editor\\icons\\save.png");
	
	TextEditor(){
		frame = new JFrame("Text Editor");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {	}
		
		frame.setBounds(100, 100, 616, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea = new JTextPane();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setBounds(100, 100, 100, 100);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		Font font = new Font("Bookman Old Style", Font.ITALIC, 18);
		textArea.setFont(font);
		
		JScrollPane scroll= new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
				
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 5, 0, 0));
		menuBar.setBackground(Color.WHITE);
		menuBar.setForeground(Color.BLACK);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
			JMenuItem newF = new JMenuItem("New");
			file.add(newF);
			file.addSeparator();
			JMenuItem op = new JMenuItem("Open");
			file.add(op);
			file.addSeparator();			
			JMenuItem sv = new JMenuItem("Save", image);
			file.add(sv);
			file.addSeparator();
			JMenuItem svas = new JMenuItem("Save As");
			file.add(svas);
			
		newF.addActionListener(this);
		op.addActionListener(this);
		sv.addActionListener(this);
		svas.addActionListener(this);	
		
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
			JMenuItem copy = new JMenuItem("Copy");
			copy.addActionListener(e -> textArea.copy());
			edit.add(copy);
			edit.addSeparator();
			JMenuItem cut = new JMenuItem("Cut");
			cut.addActionListener(e -> textArea.cut());
			edit.add(cut); 
			edit.addSeparator();
			JMenuItem paste = new JMenuItem("Paste");
			paste.addActionListener(e -> textArea.paste());
			edit.add(paste);
		
		JMenuItem ext = new JMenuItem("Exit");
		menuBar.add(ext);
		ext.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setSize(100, 100);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		bold = new JButton("BOLD");
		panel.add(bold);
		bold.setBackground(new java.awt.Color(255, 255, 155));
		bold.setFont(new Font("Serif", Font.BOLD, 13));
		italic = new JButton("ITALIC");
		panel.add(italic);
		italic.setBackground(new java.awt.Color(255, 255, 255));
		italic.setFont(new Font("Serif", Font.ITALIC, 13));
	}
	
	 public void actionPerformed(ActionEvent e) 
	    { 
	         String s= e.getActionCommand();
	         
	         if(s.equals("New")) {
	        	 if(1==sure())	textArea.setText("");
	        	 if(0==sure())	saveFile(fname);
	         }
	         
	         if(s.equals("Exit")) {
	        	 if(1==sure())	System.exit(0);
	        	 if(0==sure())	{
	        		 saveFile(fname);
	        		 System.exit(0);
	        	 }
	         }
	         
	         if(s.equals("Open")) {
	        	 if(0==sure())	saveFile(fname);
	        	    // Create an object of JFileChooser class 
		            JFileChooser j = new JFileChooser(); 
		            // Invoke the showsOpenDialog function to show the save dialog 
		            int r = j.showOpenDialog(null); 
		            // If the user selects a file 
		            if (r == JFileChooser.APPROVE_OPTION) { 
		                // Set the label to the path of the selected directory 
		                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
		                try { 
		                    String s1 = "", sl = ""; 
		                    BufferedReader br = new BufferedReader(new FileReader(fi)); 
		                    sl = br.readLine(); 
		                    // Take the input from the file 
		                    while ((s1 = br.readLine()) != null) { 
		                        sl = sl + "\n" + s1; 
		                    } 
		                    textArea.setText(sl);
		                    br.close();
		                } 
		                catch (Exception evt) { 
		                    JOptionPane.showMessageDialog(frame, evt.getMessage()); 
		                } 
		            }
	         }
	         
	         if(s.equals("Save"))			saveFile(fname);
	         
	         if(s.equals("Save As"))		saveFile(null);
	         
	    }
	 
	 public int sure() {
		 int resp = JOptionPane.showConfirmDialog(frame, "Do you want to save this file?");
		 return resp;
	 }
	 
	 private void saveFile(String name) 
	  {
		    if (name == null) {  
		    	// get filename from user
		      JFileChooser fc = new JFileChooser();
		      if (fc.showSaveDialog(null) != JFileChooser.CANCEL_OPTION)
		        name = fc.getSelectedFile().getAbsolutePath();
		    }
		    if (name != null) {  
		      try {
		        Formatter out = new Formatter(new File(name));  
		        fname = name;
		        out.format("%s", textArea.getText());
		        out.close();
		        JOptionPane.showMessageDialog(frame, "File successfully Saved.");
		      }
		      catch (FileNotFoundException e) {
		        JOptionPane.showMessageDialog(null,  name +"not flound.",
		          "Error", JOptionPane.ERROR_MESSAGE);
		      }
		    }
	  }
	
	public static void main(String[] args) {	
		TextEditor ob = new TextEditor();
		ob.frame.setLocationRelativeTo(null);
		ob.frame.setVisible(true);	
	}
}
