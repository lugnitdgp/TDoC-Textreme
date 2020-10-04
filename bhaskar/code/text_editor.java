package code;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.color.*;
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
		textarea.setBounds(100,100,0,0);
		c.add(textarea,BorderLayout.NORTH);
		
		JScrollPane scroll=new JScrollPane(textarea);
		c.add(scroll,BorderLayout.CENTER);
		
			
}
}
