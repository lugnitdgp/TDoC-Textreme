package app;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class Text_editor {
	      
	private final String title="Simple Text Editor";
	private JFrame frame;
	private JTextPane textArea;
	//private JMenuBar menuBar;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
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
    	
    	
    	JMenuBar menuBar =new JMenuBar();
    	menuBar.setMargin(new Insets(0,5,0,0));
    	menuBar.setBackground(Color.WHITE);
    	menuBar.setForeground(Color.BLACK);
    	frame.getContentPane().add(menuBar,BorderLayout.NORTH);
    	
    	
    	JMenu file=new JMenu("File");
    	menuBar.add(file);
    	menuBar.add(new JMenu("|")).setEnabled(false);
    	
    	JMenuItem newFile=new JMenuItem("New");
    	file.add(newFile);
    	file.addSeparator();
    	
    	JMenuItem openFile=new JMenuItem("Open");
    	file.add(openFile);
    	file.addSeparator();
    	
    	JMenuItem saveFile=new JMenuItem("Save");
    	file.add(saveFile);
    	file.addSeparator();
    	
    	JMenuItem saveasFile=new JMenuItem("Save As");
    	file.add(saveasFile);
    	file.addSeparator();
    	
    	JMenuItem closeFile=new JMenuItem("Close");
    	file.add(closeFile);
    	file.addSeparator();
    	
    	JMenu edit=new JMenu("Edit");
    	menuBar.add(edit);
    	menuBar.add(new JMenu("|")).setEnabled(false);
    	
    	JMenuItem copyFile=new JMenuItem("Copy");
    	edit.add(copyFile);
    	edit.addSeparator();
    	
    	JMenuItem cutFile=new JMenuItem("Cut");
    	edit.add(cutFile);
    	edit.addSeparator();
    	
    	JMenuItem pasteFile=new JMenuItem("Paste");
    	edit.add(pasteFile);
    	edit.addSeparator();
    	
    	
    	JMenu exit=new JMenu("Exit");
    	menuBar.add(exit);
    	
    	JPanel panel=new JPanel();
    	panel.setBackground(Color.WHITE);
    	panel.setSize(100,100);
    	frame.getContentPane().add(panel,BorderLayout.SOUTH);
    	
    	JButton bold=new JButton();
    	bold.setText("BOLD");
    	bold.setBackground(Color.GRAY);
    	
    	JButton italics=new JButton();
    	italics.setText("Italics");
    	italics.setBackground(Color.GRAY);
    	
    	panel.add(bold);
    	panel.add(italics);
    	
    }

}

