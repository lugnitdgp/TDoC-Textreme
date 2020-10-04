package application;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
public class SimpleTextEditor 
{
	JFrame frame;JTextPane txtarea;JScrollPane scroll;
	JMenuBar menu;JMenu file,edit,exit;JMenuItem newfile,openfile,savefile,saveas,copy,cut,paste;
	JPanel panel;JButton bold,italics,underline;
	SimpleTextEditor()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) { }
	
	frame= new JFrame("TEXT EDITOR");
	frame.setBounds(100,100,600,444);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	txtarea= new JTextPane();
	txtarea.setBackground(Color.WHITE);
	txtarea.setForeground(Color.MAGENTA);
	frame.getContentPane().add(txtarea,BorderLayout.NORTH);
	
	scroll= new JScrollPane(txtarea); 
	frame.getContentPane().add(scroll,BorderLayout.CENTER);
	
	menu=new JMenuBar();
	menu.setMargin(new Insets(2,2,0,0));
	menu.setBackground(Color.PINK);
	menu.setForeground(Color.BLACK);
	frame.getContentPane().add(menu,BorderLayout.NORTH);
	
	file=new JMenu("File");edit=new JMenu("Edit");exit=new JMenu("Exit");
	menu.add(file);menu.add(new JMenu("|")).setEnabled(false);
	menu.add(edit);menu.add(new JMenu("|")).setEnabled(false);
	menu.add(exit);
	
	newfile=new JMenuItem("New");openfile=new JMenuItem("Open");savefile=new JMenuItem("Save");
	saveas=new JMenuItem("SaveAs");copy=new JMenuItem("Copy");paste=new JMenuItem("Paste");
	cut=new JMenuItem("Cut");
	file.add(newfile);file.addSeparator();file.add(openfile);file.addSeparator();
	file.add(savefile);file.addSeparator();file.add(saveas);
	edit.add(cut);file.addSeparator();edit.add(copy);file.addSeparator();
	edit.add(paste);file.addSeparator();
	
	panel=new JPanel();
	panel.setBackground(Color.PINK);
	panel.setForeground(Color.WHITE);
	panel.setSize(100,100);
	frame.getContentPane().add(panel,BorderLayout.SOUTH);
	
	bold=new JButton();italics=new JButton();underline=new JButton();
	bold.setText("Bold");italics.setText("Italics");underline.setText("Underline");
	bold.setBackground(new java.awt.Color(0,140,255));
	italics.setBackground(new java.awt.Color(0,140,255));
	underline.setBackground(new java.awt.Color(0,140,255));
	panel.add(bold);panel.add(italics);panel.add(underline);
	}
public static void main(String[] args)
{
	
	SimpleTextEditor window = new SimpleTextEditor();
	window.frame.setLocationRelativeTo(null);
	window.frame.setVisible(true);
	
}
}
