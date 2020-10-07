package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.Element;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;
public class text_editor {
	static JFrame frame;
	static JLabel l;
	static JTextPane textarea;
	static JButton but,but1,but2,but3;
	public text_editor()
	{
		getElements();
	}
	public static void main(String args[])
	{	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			text_editor window = new text_editor();
			window.frame.setLocationRelativeTo(null);
			window.frame.setVisible(true);
			}
			catch (Exception e) {
			e.printStackTrace();
			}
			}
			});
	}
	
	public static void getElements() {
		frame=new JFrame("Text Editor");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,100,616,444);
		Container c=frame.getContentPane();
		c.setBackground(Color.white);
		
		textarea=new JTextPane();
		textarea.setBackground(Color.white);
		textarea.setForeground(Color.black);
		textarea.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
		c.add(textarea,BorderLayout.NORTH);
		
		JScrollPane scroll=new JScrollPane(textarea);
		c.add(scroll,BorderLayout.CENTER);
		
		JMenuBar menu = new JMenuBar();
		menu.setMargin(new Insets(0,5,0,0));
		menu.setBackground(Color.WHITE);
		c.add(menu,BorderLayout.NORTH);
		
		JMenu file=new JMenu("File");
		JMenu Edit=new JMenu("Edit");
		JMenu exit=new JMenu("Exit");
		menu.add(file);
		menu.add(new JMenu("|")).setEnabled(false);
		menu.add(Edit);
		menu.add(new JMenu("|")).setEnabled(false);
		menu.add(exit);
		JMenuItem item=new JMenuItem("New");
		JMenuItem item1=new JMenuItem("Open");
		JMenuItem item2=new JMenuItem("Save");
		JMenuItem item3=new JMenuItem("Save As");
		item1.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				open();
			}
		});
		item.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			textarea.setText("");
			}
		});
		item3.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			saveAs();	
			}
		});
		l=new JLabel();
		textarea.add(l);
		JMenuItem item4=new JMenuItem("close");
		file.add(item);
		file.add(item1);
		file.add(item2);
		file.add(item3);
		file.add(item4);
		
		file.addSeparator();
		
		JMenuItem item5=new JMenuItem("cut");
		JMenuItem item6=new JMenuItem("copy");
		JMenuItem item7=new JMenuItem("paste");
		
		Edit.add(item5);
		Edit.add(item6);
		Edit.add(item7);
		
		item5.addActionListener(e->textarea.cut());
		item6.addActionListener(e->textarea.copy());
		item7.addActionListener(paste->textarea.paste());
		
		
		
		
		
		JMenuItem item8=new JMenuItem("Close");
		
		item8.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			System.exit(0);	
			}
		});
		
		exit.add(item8);
		
		JPanel panel=new JPanel();
		panel.setBackground(Color.black);
		panel.setSize(100,100);
		c.add(panel,BorderLayout.SOUTH);
		but=new JButton("bold");
		but.setFont(new Font("Monaco", Font.BOLD, 20));
		but.setBackground(new java.awt.Color(0,140,255));
		but.setBounds(0, 0, 250, 150);
		panel.add(but);	
		but.addActionListener(e ->bold());
		  

		but1=new JButton("italics");
		but1.setFont(new Font("Monaco", Font.ITALIC, 20));
		but1.setBackground(new java.awt.Color(0,140,255));
		but1.setBounds(0, 0, 250, 150);
		panel.add(but1);
		but1.addActionListener(e ->italics());
		
		UndoManager Man=new UndoManager();
		textarea.getDocument().addUndoableEditListener(Man);
		
		but2=new JButton("undo");
		but2.setFont(new Font("Monaco", Font.PLAIN, 20));
		but2.setBackground(new java.awt.Color(0,140,255));
		but2.setBounds(0, 0, 250, 150);
		panel.add(but2);	
		
		but2.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					if(Man.canUndo()) {
					Man.undo();
				}
					}
				catch(Exception ex){
					System.out.print(ex);
					
				}
			}
		});
		
		but3=new JButton("redo");
		but3.setFont(new Font("Monaco", Font.PLAIN, 20));
		but3.setBackground(new java.awt.Color(0,140,255));
		but3.setBounds(0, 0, 250, 150);
		panel.add(but3);
	
		
		but3.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					if(Man.canRedo()) {
					Man.redo();
				}
					}
				catch(Exception ex){
					System.out.print(ex);
					
				}
			}
		});
}
	
	public static void bold()
	{
		 StyledDocument doc = (StyledDocument) textarea.getDocument();
		    int selectionEnd = textarea.getSelectionEnd();
		    int selectionStart = textarea.getSelectionStart();
		    if (selectionStart == selectionEnd) {
		      return;
		    }
		    Element element =  doc.getCharacterElement(selectionStart);
		    AttributeSet as =  element.getAttributes();

		    MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
		    StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
		    doc.setCharacterAttributes(selectionStart, textarea.getSelectedText()
		        .length(), asNew, true);
		    String text = (StyleConstants.isBold(as) ? "Cancel Bold" : "Bold");
		    but.setText(text);
	}
	public static void italics()
	{
		 StyledDocument doc = (StyledDocument) textarea.getDocument();
		    int selectionEnd = textarea.getSelectionEnd();
		    int selectionStart = textarea.getSelectionStart();
		    if (selectionStart == selectionEnd) {
		      return;
		    }
		    Element element =  doc.getCharacterElement(selectionStart);
		    AttributeSet as =  element.getAttributes();

		    MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
		    StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
		    doc.setCharacterAttributes(selectionStart, textarea.getSelectedText()
		        .length(), asNew, true);
		    String text = (StyleConstants.isItalic(as) ? "Cancel Italic" : "Italic");
		    but1.setText(text);
	}
	
	public static void open()
	{	textarea.setText("");
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
		  
        int r = j.showOpenDialog(null); 
 
        if (r == JFileChooser.APPROVE_OPTION) 

        { 
        	try {
        	      File myObj = new File(j.getSelectedFile().getAbsolutePath());
        	      Scanner myReader = new Scanner(myObj);
        	      while (myReader.hasNextLine()) {
        	        String data = myReader.nextLine();
        	        textarea.setText(data);
        	      }
        	      myReader.close();
        	    } catch (FileNotFoundException e) {
        	      System.out.println("An error occurred.");
        	      e.printStackTrace();
        	    } 
          
        } 
	}
	public static void saveAs()
	{
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
		  
        int r = j.showOpenDialog(null); 
		
        try {
		      File myObj = new File(j.getSelectedFile().getAbsolutePath());
		      if (myObj.createNewFile()) {
		    	  FileWriter myWriter = new FileWriter(j.getSelectedFile().getAbsolutePath());
		          myWriter.write(textarea.getText());
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
