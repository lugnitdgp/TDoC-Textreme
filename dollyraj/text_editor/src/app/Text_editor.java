package app;

//DAY 1
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

//DAY 2
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//DAY 3
import javax.swing.JFileChooser;
import java.util.Scanner;
import java.util.Formatter;
import javax.swing.JOptionPane;

//DAY 4
import javax.swing.text.StyledDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;



public class Text_editor {
	      
	private final String title="Simple Text Editor";
	private JFrame frame;
	private JTextPane textArea;
	private File openFile;
	private JButton bold;
	private JButton italics;
	private JButton undo;
	private JButton redo;
	UndoManager manager=new UndoManager();
	
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
    	newFile.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			newFile();
    		}
    	});
    	file.add(newFile);
    	file.addSeparator();
    
    	JMenuItem open=new JMenuItem("Open");
    	open.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			open();
    		}
    	});

    	file.add(open);
    	file.addSeparator();
    	
    	
    	JMenuItem saveFile=new JMenuItem("Save");
    	saveFile.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			save();
    		}
    	});

    	file.add(saveFile);
    	file.addSeparator();
    	
    	JMenuItem saveasFile=new JMenuItem("Save As");
    	saveasFile.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			saveas();
    		}
    	});

    	file.add(saveasFile);
    	file.addSeparator();
    	
    	JMenuItem closeFile=new JMenuItem("Close");
    	closeFile.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			close();
    		}
    	});

    	file.add(closeFile);
    	file.addSeparator();
    	
    	JMenu edit=new JMenu("Edit");
    	menuBar.add(edit);
    	menuBar.add(new JMenu("|")).setEnabled(false);
    	
    	JMenuItem copyFile=new JMenuItem("Copy");
    	copyFile.addActionListener(e->textArea.copy());
    	edit.add(copyFile);
    	edit.addSeparator();
    	
    	JMenuItem cutFile=new JMenuItem("Cut");
    	cutFile.addActionListener(e->textArea.cut());
    	edit.add(cutFile);
    	edit.addSeparator();
    	
    	JMenuItem pasteFile=new JMenuItem("Paste");
    	pasteFile.addActionListener(e->textArea.paste());
    	edit.add(pasteFile);
    	edit.addSeparator();
    	
    	
    	JMenu exit=new JMenu("Exit");
    	exit.addActionListener(e->System.exit(0));
    	menuBar.add(exit);
    	
    	JPanel panel=new JPanel();
    	panel.setBackground(Color.WHITE);
    	panel.setSize(100,100);
    	frame.getContentPane().add(panel,BorderLayout.SOUTH);
    	
    	bold=new JButton();
    	bold.setText("BOLD");
    	bold.setBackground(Color.GRAY);
    	bold.addActionListener(e->Bold());
    	
    	italics=new JButton();
    	italics.setText("Italics");
    	italics.setBackground(Color.GRAY);
    	italics.addActionListener(e->Italics());
    	
    	undo=new JButton();
    	undo.setText("Undo");
    	undo.setBackground(Color.GRAY);
    	
    	
    	redo=new JButton();
    	redo.setText("Redo");
    	redo.setBackground(Color.GRAY);
    	
    	undo.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			try {
    				  manager.undo();
    			}catch(Exception ex) {
    		}
    		}
    	});
    	
    	redo.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			try {
    				  manager.redo();
    			}catch(Exception ex) {
    				
    			}
    		}
    	});
    	
    	
    	textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
    		public void undoableEditHappened(UndoableEditEvent e) {
    			manager.addEdit(e.getEdit());
    		}
    	});
    	
    	panel.add(undo);
    	panel.add(redo);
    	panel.add(bold);
    	panel.add(italics);
    	
    	
    	
    	
 
    }
    
    private void open() {
    	try {
    		  JFileChooser chooser=new JFileChooser();
    		  chooser.setDialogTitle("Select a file To Open");
    		  chooser.showOpenDialog(null);
    		  
    		  File selectedFile=chooser.getSelectedFile();
    		  if(!selectedFile.exists()) {
    			  //ERROR message
    			  JOptionPane.showMessageDialog(null,"Failed to open a file,file doesn't exist","Error",JOptionPane.ERROR_MESSAGE);
    			  openFile=null;
    			  return;
    		  }
    		  
    		  Scanner reader=new Scanner(selectedFile);
    		  String contents="";
    		  while(reader.hasNextLine()) {
    			  contents+=reader.nextLine()+"\n";
    		  }
    		  
    		  reader.close();
    		  textArea.setText(contents);
    		  openFile=selectedFile;
    		  
    		  frame.setTitle(title+"-"+selectedFile.getName());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    private void saveas() {
    	try {
    		 JFileChooser chooser=new JFileChooser();
    		 chooser.setDialogTitle("Choose location to save");
    		 int save=chooser.showSaveDialog(null);
    		 
    		 if(save==JFileChooser.CANCEL_OPTION) {
    			 System.exit(-1);
    		 }
    		 
    		 openFile=chooser.getSelectedFile();
    		 
    		 save();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    
    private void save() {
    	try {
    		String text=textArea.getText().trim();
    		if(!text.equals("") && openFile==null) {
    			saveas();
    			return;
    		}
    			
    		if(openFile==null && text.equals("")) {
    			//ERROR MESSAGE
    			JOptionPane.showMessageDialog(null, "Can't save,No file is selected","Error",JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		
    		String contents=textArea.getText();
    		Formatter form=new Formatter(openFile);
    		form.format("%s", contents);
    		form.close();
    		
    		frame.setTitle(title+"-"+openFile.getName());
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    private void close() {
    	if(openFile==null) {
    		JOptionPane.showMessageDialog(null,"failed to close the file,No file is selected","Error",JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	try {
    		 int input=JOptionPane.showConfirmDialog(null,"Do you want to save before closing?","Wait!",JOptionPane.YES_NO_OPTION);
    		 if(input==JOptionPane.YES_OPTION)
    			 save();
    	
    	textArea.setText("");
    	openFile=null;
    	frame.setTitle(title);
    }catch(Exception e) {
    	e.printStackTrace();
    }
    	
    } 
    
    
    private void newFile() {
    	if(openFile!=null) {
    		JOptionPane.showMessageDialog(null,"Please close or save the opened file","ERROR",JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	textArea.setText("");
    }
    
    private void Bold() {
    	StyledDocument doc=(StyledDocument) textArea.getDocument();
    	int selectionEnd=textArea.getSelectionEnd();
    	int selectionStart=textArea.getSelectionStart();
    	if(selectionStart==selectionEnd) {
    		return;
    	}
    	
    	Element element=doc.getCharacterElement(selectionStart);
    	AttributeSet as=element.getAttributes();
    	MutableAttributeSet asNew=new SimpleAttributeSet(as.copyAttributes());
    	StyleConstants.setBold(asNew,!StyleConstants.isBold(as));
    	doc.setCharacterAttributes(selectionStart,textArea.getSelectedText().length(),asNew,true);
    	String text=(StyleConstants.isBold(as)?"Cancel Bold":"Bold");
    	bold.setText(text);
    }
    
    private void Italics() {
    	StyledDocument doc=(StyledDocument) textArea.getDocument();
    	int selectionEnd=textArea.getSelectionEnd();
    	int selectionStart=textArea.getSelectionStart();
    	if(selectionStart==selectionEnd) {
    		return;
    	}
    	
    	Element element=doc.getCharacterElement(selectionStart);
    	AttributeSet as=element.getAttributes();
    	MutableAttributeSet asNew=new SimpleAttributeSet(as.copyAttributes());
    	StyleConstants.setItalic(asNew,!StyleConstants.isItalic(as));
    	doc.setCharacterAttributes(selectionStart,textArea.getSelectedText().length(),asNew,true);
    	//String text=(StyleConstants.isBold(as)?"Cancel Bold":"Bold");
    	//bold.setText(text);
    }
}

