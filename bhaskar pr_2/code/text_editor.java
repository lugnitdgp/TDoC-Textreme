package code;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.*;
public class text_editor {
	public static void main(String args[])
	{	
		
		JFrame frame=new JFrame("Text Editor");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,100,616,444);
		Container c=frame.getContentPane();
		c.setBackground(Color.white);
		
		JTextArea textarea=new JTextArea();
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
		JMenuItem item1=new JMenuItem("Save");
		JMenuItem item2=new JMenuItem("Save As");
		JMenuItem item3=new JMenuItem("open");
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
}
