import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


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
		newFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textArea.setText("");
			}
			});
		file.add(newFile);
		file.addSeparator();
		open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	  
	                    // Initilize sl 
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
			});
		file.add(open);
		file.addSeparator();
		save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent source = (JComponent)e.getSource();
			    JFileChooser j = new JFileChooser("f:"); 
				  
	            // Invoke the showsSaveDialog function to show the save dialog 
	            int r = j.showSaveDialog(null); 
	  
	            if (r == JFileChooser.APPROVE_OPTION) { 
	  
	                // Set the label to the path of the selected directory 
	                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
	  
	                try { 
	                    // Create a file writer 
	                    FileWriter wr = new FileWriter(fi, false); 
	  
	                    // Create buffered writer to write 
	                    BufferedWriter w = new BufferedWriter(wr); 
	  
	                    // Write 
	                    w.write(textArea.getText()); 
	  
	                    w.flush(); 
	                    w.close(); 
	                } 
	                catch (Exception evt) { 
	                    JOptionPane.showMessageDialog(frame, evt.getMessage()); 
	                } 
	            } 
	            // If the user cancelled the operation 
	            else
	                JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
			}
			
			});
		file.add(save);
		file.addSeparator();
		saveas = new JMenuItem("Save As");
		saveas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser("f:"); 
				  
	            // Invoke the showsSaveDialog function to show the save dialog 
	            int r = j.showSaveDialog(null); 
	  
	            if (r == JFileChooser.APPROVE_OPTION) { 
	  
	                // Set the label to the path of the selected directory 
	                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
	  
	                try { 
	                    // Create a file writer 
	                    FileWriter wr = new FileWriter(fi, false); 
	  
	                    // Create buffered writer to write 
	                    BufferedWriter w = new BufferedWriter(wr); 
	  
	                    // Write 
	                    w.write(textArea.getText()); 
	  
	                    w.flush(); 
	                    w.close(); 
	                } 
	                catch (Exception evt) { 
	                    JOptionPane.showMessageDialog(frame, evt.getMessage()); 
	                } 
	            } 
	            // If the user cancelled the operation 
	            else
	                JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
			}
			});
		file.add(saveas);
		file.addSeparator();
		close = new JMenuItem("Close");
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				
			}
				
			});
		file.add(close);
		file.addSeparator();
		
		edit = new JMenu("Edit");
		menuBar.add(edit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		cut = new JMenuItem("Cut");
		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
			});
		edit.add(cut);
		edit.addSeparator();
		copy = new JMenuItem("Copy");
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
			});
		edit.add(copy);
		edit.addSeparator();
		paste = new JMenuItem("Paste");
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
			});
		edit.add(paste);
		edit.addSeparator();
		
		exit = new JMenu("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setVisible(false);
			}
			});
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
	

