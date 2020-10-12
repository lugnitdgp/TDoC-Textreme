import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class Text {
	private final String title = "Simple Text Editor";
	JFrame frame;
	JTextPane textArea;
	private JMenuBar menuBar;
	private JMenu file,edit,color;
	private JMenuItem newFile,open,save,saveas,close,cut,copy,paste,exit,c1,c2,c3;
	private JPanel panel;
	private JButton bold,italics,undo,redo,sure,ex;
	
	int s=0;
	String l1;
	String l2;
	
	UndoManager manager = new UndoManager();
	function_edit ed = new function_edit(this);
	function_color col=new function_color(this);
	KeyHandler key=new KeyHandler(this);
	
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
		textArea.addKeyListener(key);
		//textArea.setForeground(Color.BLACK);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		textArea.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						manager.addEdit(e.getEdit());
					}
				});
		

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
				frame.setTitle(title);
				s=0;
				
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
				if (s==0) {
					FileDialog fd = new FileDialog(frame,"Save",FileDialog.SAVE);
					fd.setVisible(true);
					if(fd.getFile()!=null) 
						frame.setTitle(fd.getFile());
					
		  
		                try { 
		                    // Create a file writer 
		                    FileWriter wr = new FileWriter(fd.getDirectory() + fd.getFile()); 
		                    l1=fd.getDirectory();
		                    l2=fd.getFile();
		  
		                    // Create buffered writer to write 
		                    //BufferedWriter w = new BufferedWriter(wr); 
		  
		                    // Write 
		                    wr.write(textArea.getText()); 
		  
		                    //w.flush(); 
		                    wr.close(); 
		                    s=1;
		                } 
		                catch (Exception evt) { 
		                    JOptionPane.showMessageDialog(frame, evt.getMessage()); 
		                } 
		             
				}
				else {
					try {
						FileWriter wr = new FileWriter(l1+l2); 
						  
	                    // Create buffered writer to write 
	                    //BufferedWriter w = new BufferedWriter(wr); 
	  
	                    // Write 
	                    wr.write(textArea.getText()); 
	  
	                    //w.flush(); 
	                    wr.close(); 
					}catch(Exception ee) {
						System.out.println("Error");
					}
				}
			}
			
			});
		file.add(save);
		file.addSeparator();
		saveas = new JMenuItem("Save As");
		saveas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame,"Save",FileDialog.SAVE);
				fd.setVisible(true);
				if(fd.getFile()!=null) 
					frame.setTitle(fd.getFile());
				
	  
	                try { 
	                    // Create a file writer 
	                    FileWriter wr = new FileWriter(fd.getDirectory() + fd.getFile()); 
	                    l1=fd.getDirectory();
	                    l2=fd.getFile();
	  
	                    // Create buffered writer to write 
	                    //BufferedWriter w = new BufferedWriter(wr); 
	  
	                    // Write 
	                    wr.write(textArea.getText()); 
	  
	                    //w.flush(); 
	                    wr.close(); 
	                    s=1;
	                } 
	                catch (Exception evt) { 
	                    JOptionPane.showMessageDialog(frame, evt.getMessage()); 
	                } 
	            } 
	            // If the user cancelled the operation 
	            //else
	              //  JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
			
			});
		file.add(saveas);
		file.addSeparator();
		close = new JMenuItem("Close");
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (s==1) {
					frame.setVisible(false);
					System.exit(0);
				}
				else {
					
						int ret=JOptionPane.showConfirmDialog(frame, "Save file before exitting?", "Save This File?", JOptionPane.YES_NO_CANCEL_OPTION);
						
						        if(ret==JOptionPane.CANCEL_OPTION) {
						
						            return;
						
						        }
						
						        else if(ret==JOptionPane.YES_OPTION) {
						
						        	FileDialog fd = new FileDialog(frame,"Save",FileDialog.SAVE);
									fd.setVisible(true);
									if(fd.getFile()!=null) 
										frame.setTitle(fd.getFile());
									
						  
						                try { 
						                    // Create a file writer 
						                    FileWriter wr = new FileWriter(fd.getDirectory() + fd.getFile()); 
						                    l1=fd.getDirectory();
						                    l2=fd.getFile();
						  
						                    // Create buffered writer to write 
						                    //BufferedWriter w = new BufferedWriter(wr); 
						  
						                    // Write 
						                    wr.write(textArea.getText()); 
						  
						                    //w.flush(); 
						                    wr.close(); 
						                    s=1;
						                } 
						                catch (Exception evt) { 
						                    JOptionPane.showMessageDialog(frame, evt.getMessage()); 
						                } 

						            frame.setVisible(false);
						
						            System.exit(0);
						
						        }
						
						        else if(ret==JOptionPane.NO_OPTION) {
						
						        	frame.setVisible(false);
						
						            System.exit(0);
						
						        }

					
			
				}
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
		
		
		color = new JMenu("Color");
		menuBar.add(color);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		c1 = new JMenuItem("White");
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					col.changeColor("white");
					
				
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(frame, "Error");
				}
			}
			});
		color.add(c1);
		color.addSeparator();
		
		c2 = new JMenuItem("Black");
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					col.changeColor("black");
					
				
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(frame, "Error");
				}
			}
			});
		color.add(c2);
		color.addSeparator();
		
		c3 = new JMenuItem("BLue");
		c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					col.changeColor("blue");
					
				
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(frame, "Error in undo");
				}
			}
			});
		color.add(c3);
		color.addSeparator();
		
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				
			}
			});
		menuBar.add(exit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setSize(100, 100);
		panel.setVisible(true);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		bold = new JButton();
		bold.setText("Bold");
		bold.setBackground(new java.awt.Color(0, 140, 255));
		bold.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StyledDocument doc = (StyledDocument) textArea.getDocument();
				int selectionEnd = textArea.getSelectionEnd();
				int selectionStart = textArea.getSelectionStart();
				if (selectionStart == selectionEnd) {
				return;
				}
				Element element = doc.getCharacterElement(selectionStart);
				AttributeSet as = element.getAttributes();
				MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
				StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
				doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
				.length(), asNew, true);
				String text = (StyleConstants.isBold(as) ? "Cancel Bold" : "Bold");
				bold.setText(text);
				
			}
			});
		panel.add(bold);
		
		
		italics = new JButton();
		italics.setText("Italics");
		italics.setBackground(new java.awt.Color(0, 140, 255));
		italics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StyledDocument doc = (StyledDocument) textArea.getDocument();
				int selectionEnd = textArea.getSelectionEnd();
				int selectionStart = textArea.getSelectionStart();
				if (selectionStart == selectionEnd) {
				return;
				}
				Element element = doc.getCharacterElement(selectionStart);
				AttributeSet as = element.getAttributes();
				MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
				StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
				doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
				.length(), asNew, true);
				String text = (StyleConstants.isItalic(as) ? "Cancel Italic" : "Italic");
				italics.setText(text);
				
			}
			});
		panel.add(italics);
		
		
		undo = new JButton();
		undo.setText("Undo");
		undo.setBackground(new java.awt.Color(0, 140, 255));
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			try {

				ed.undo();
				
			
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(frame, "Error in undo");
			}
			}
			});
		panel.add(undo);
		
		redo = new JButton();
		redo.setText("Redo");
		redo.setBackground(new java.awt.Color(0, 140, 255));
		redo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			try {
			ed.redo();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error in redo");
			}
			}
			});
		panel.add(redo);
		
	}
	
	
	
}
	

