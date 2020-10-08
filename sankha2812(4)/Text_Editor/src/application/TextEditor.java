package application;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.event.*;


class BackgroundMenuBar extends JMenuBar {
    Color bgColor=Color.WHITE;

    public void setColor(Color color) {
        bgColor=color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }
}

public class TextEditor implements ActionListener {
	JFrame frame;
	JScrollPane scroll;
	JTextPane textArea;
	BackgroundMenuBar menuBar;
	JButton bold;
	JButton italic;
	JButton undo;
	JButton redo;
	UndoManager Man;
	String fname=null;
	int clicked=0;
	Color colt = new Color(38,38,38);
	Color colp = new Color(54,47,45);
	Color colf=new Color(231,219,116);
	Color colmb=new Color(109,110,106);
	
	ImageIcon image = new ImageIcon("C:\\Users\\SANKHADEEP GHOSH\\eclipse-workspace\\Text_Editor\\icons\\save.png");
	
	TextEditor(){
		frame = new JFrame("Text Editor");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {	}
		
		frame.setBounds(100, 100, 616, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea = new JTextPane();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.black);
		textArea.setBounds(100, 100, 100, 100);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		Font font = new Font("Bookman Old Style", Font.PLAIN, 18);
		textArea.setFont(font);
		
		JScrollPane scroll= new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
				
//		menuBar = new JMenuBar();
		menuBar= new BackgroundMenuBar();
		menuBar.setMargin(new Insets(0, 5, 0, 0));
		menuBar.setBackground(Color.WHITE);
		menuBar.setForeground(Color.red);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		menuBar.add(new JMenu("|")).setEnabled(false);
		file.setMnemonic(KeyEvent.VK_F);
		
			JMenuItem newF = new JMenuItem("New");
			file.add(newF);
			file.addSeparator();
			newF.setMnemonic(KeyEvent.VK_N);
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
		
		JPanel panel = new JPanel();
		panel.setBackground(colp);
		panel.setSize(100, 100);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		bold = new JButton("BOLD");
		panel.add(bold);
		bold.setBackground(new java.awt.Color(255, 255, 155));
		bold.setFont(new Font("Serif", Font.BOLD, 13));
		bold.addActionListener(this);
		italic = new JButton("ITALIC");
		panel.add(italic);
		italic.setBackground(new java.awt.Color(255, 255, 255));
		italic.setFont(new Font("Serif", Font.ITALIC, 13));
		italic.addActionListener(this);
		undo =new JButton("UNDO");
		panel.add(undo);
		undo.setBackground(new java.awt.Color(255, 255, 155));
		undo.setFont(new Font("Serif", Font.PLAIN, 13));
		undo.addActionListener(this);
		redo = new JButton("REDO");
		panel.add(redo);
		redo.setBackground(new java.awt.Color(255, 255, 155));
		redo.setFont(new Font("Serif", Font.PLAIN, 13));
		redo.addActionListener(this);
				
		Man=new UndoManager();
		textArea.getDocument().addUndoableEditListener(Man);
	
		JMenu vw = new JMenu("View");
		menuBar.add(vw);
		menuBar.add(new JMenu("|")).setEnabled(false);
			
			JMenuItem vwPnl = new JMenuItem("View Panel");
			vwPnl.addActionListener(e -> panel.setVisible(true));
			vw.add(vwPnl);
			vw.addSeparator();
			JMenuItem hdPnl = new JMenuItem("Hide Panel");
			hdPnl.addActionListener(e -> panel.setVisible(false));
			vw.add(hdPnl);
			vw.addSeparator();
			JMenuItem dMd = new JMenuItem("Toggle Dark Mode");
			vw.add(dMd);
			dMd.addActionListener(this);	
		
		JMenuItem ext = new JMenuItem("Exit");
		menuBar.add(ext);
		ext.addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) 
	    { 
	         String s= e.getActionCommand();
	         
	         if(s.equals("New")) {
	        	 if(0==sure())	saveFile(fname);
	        	 textArea.setText("");
	         }
	         
	         if(s.equals("Exit")) {
	        	int temp=sure();
	        	if(temp==0)		 saveFile(fname);
	        	if(temp==0 ||  temp==1)	System.exit(0);
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
	         
	         if(s.equals("Toggle Dark Mode")) {
//	        	 JOptionPane.showOptionDialog(frame,"Change appearance?","Toggle Dark Mode",1,3,null, null, null);
	        	 clicked++;
	        	 if(clicked%2==1) {
		        	 menuBar.setColor(colmb);
		        	 textArea.setBackground(colt);
		     		 textArea.setForeground(colf);
	        	 }
	        	 else {
	        		 menuBar.setColor(Color.WHITE);
		        	 textArea.setBackground(Color.white);
		     		 textArea.setForeground(Color.black);
	        	 }
	         }
	         if(s.equals("BOLD"))
	 		{
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
	 			doc.setCharacterAttributes(selectionStart, textArea.getSelectedText().length(), asNew, true);

	 		}
	        if(s.equals("ITALIC"))
	 		{
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
	 			doc.setCharacterAttributes(selectionStart, textArea.getSelectedText().length(), asNew, true);

	 		}
	        
	        if(s.equals("UNDO")) {
	        	try {
					if(Man.canUndo()) {
					Man.undo();
				}
					}
				catch(Exception ex){
					System.out.print(ex);

				}
	        }
	        if(s.equals("REDO")) {
	        	try {
					if(Man.canRedo()) {
					Man.redo();
				}
					}
				catch(Exception ex){
					System.out.print(ex);

				}
	        }        
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
