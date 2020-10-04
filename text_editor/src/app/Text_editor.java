package app;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class Text_editor {
	      
	private final String title="Simple Text Editor";
	private JFrame frame;
	private JTextPane textArea;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Text_editor window =new Text_editor();
				    window.frame.setLocationRelativeTo(null);
			        window.frame.setVisible(true);
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});   
	}


    public Text_editor() {
	           initialize();
   }
    
    
    public void initialize() {
    	
    	frame =new JFrame("Editor");
    	frame.setAutoRequestFocus(false);
    	frame.setTitle(title);
    	frame.setBounds(100,100,616,444);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().setLayout(new BorderLayout(0,0));
    	
    	
    	textArea=new JTextPane();
    	textArea.setBackground(Color.GRAY);
    	textArea.setForeground(Color.WHITE);
    	frame.getContentPane().add(textArea,BorderLayout.NORTH);
    	
    	Font textFont=new Font(Font.SANS_SERIF,Font.PLAIN,15);
        textArea.setFont(textFont);
        textArea.setCaretColor(Color.WHITE);
        
    	
    	JScrollPane scroll=new JScrollPane(textArea);
    	frame.getContentPane().add(scroll,BorderLayout.CENTER);
    	
    }

}

