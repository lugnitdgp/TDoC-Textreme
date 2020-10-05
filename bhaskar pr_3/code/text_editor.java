package code;

import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.*;
public class text_editor {
	static JFrame frame;
	static JLabel l;
	static JTextArea textarea;
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
		
		textarea=new JTextArea();
		textarea.setBackground(Color.white);
		textarea.setForeground(Color.black);
		textarea.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
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
		JButton but=new JButton("bold");
		but.setFont(new Font("Monaco", Font.BOLD, 20));
		but.setBackground(new java.awt.Color(0,140,255));
		but.setBounds(0, 0, 250, 150);
		panel.add(but);	
		JButton but1=new JButton("italics");
		but1.setFont(new Font("Monaco", Font.ITALIC, 20));
		but1.setBackground(new java.awt.Color(0,140,255));
		but1.setBounds(0, 0, 250, 150);
		panel.add(but1);	
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
            //textarea.setText(j.getSelectedFile().getAbsolutePath()); 
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
