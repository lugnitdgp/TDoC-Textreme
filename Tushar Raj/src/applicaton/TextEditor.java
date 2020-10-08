package applicaton; // Package consists of classes and sub-packages
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.io.*;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.*;

public class TextEditor {
	private final String title = "Simple Text Editor";
	static JFrame frame;
	static JTextPane textArea;
	private JMenuBar menuBar;
	private JPanel panel;
	//JFileChooser j = new JFileChooser();
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					TextEditor window = new TextEditor();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TextEditor() {
		initialize();
	}

	public void initialize() {
		
		frame = new JFrame("Editor");
		frame.setAutoRequestFocus(false);
		frame.setTitle(title);
		frame.setBounds(100, 100, 616, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		textArea = new JTextPane();
		textArea.setBackground(new java.awt.Color(39, 41, 43));
		textArea.setForeground(Color.LIGHT_GRAY);
		textArea.setCaretColor(Color.GREEN);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);

		JScrollPane scroll = new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 10, 0, 0));
		menuBar.setBackground(Color.BLACK);
		menuBar.setForeground(Color.WHITE);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu file = new JMenu("File");
		menuBar.add(file);
		menuBar.add(new JMenu("|")).setEnabled(false);
		file.setForeground(Color.CYAN);


		JMenuItem newFile = new JMenuItem("New");
		file.add(newFile);
		file.addSeparator();

		JMenuItem open = new JMenuItem("Open");
		file.add(open);
		file.addSeparator();

		JMenuItem save = new JMenuItem("Save");
		file.add(save);
		file.addSeparator();

		JMenuItem saveAs = new JMenuItem("Save As");
		file.add(saveAs);
		file.addSeparator();

		open.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				open();
			}
		});
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		newFile.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				textArea.setText("");
			}
		});
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		saveAs.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				saveAs();	
			}
		});
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		menuBar.add(new JMenu("|")).setEnabled(false);
		edit.setForeground(Color.CYAN);


		JMenuItem undo = new JMenuItem("Undo");
		edit.add(undo);
		edit.addSeparator();
		
		JMenuItem redo = new JMenuItem("Redo");
		edit.add(redo);
		edit.addSeparator();
		
		JMenuItem copy = new JMenuItem("Copy");
		edit.add(copy);
		edit.addSeparator();

		JMenuItem cut = new JMenuItem("Cut");
		edit.add(cut);
		edit.addSeparator();

		JMenuItem pasteitem = new JMenuItem("Paste");
		edit.add(pasteitem);
		
		UndoManager manager = new UndoManager();
		undo.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				if(manager.canUndo())					
					manager.undo();
			} catch (Exception ex) {
			}
			}
			});
		
		redo.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
			manager.redo();
			} catch (Exception ex) {
			}
			}
			});
		
		
		cut.addActionListener(e->textArea.cut());
		copy.addActionListener(e->textArea.copy());
		pasteitem.addActionListener(paste->textArea.paste());


		JMenu font = new JMenu("Font");
		menuBar.add(font);
		menuBar.add(new JMenu("|")).setEnabled(false);
		JMenuItem decfontsize = new JMenuItem("Decrease font size");
		font.add(decfontsize);
		font.addSeparator();
		JMenuItem incfontsize = new JMenuItem("Increase font size");
		font.add(incfontsize);
		font.addSeparator();
		font.setForeground(Color.CYAN);
		JMenuItem bold = new JMenuItem("Bold");
		font.add(bold);
		font.setForeground(Color.CYAN);
		JMenuItem italics = new JMenuItem("Italics");
		font.add(italics);
		font.setForeground(Color.CYAN);
		JMenuItem underline = new JMenuItem("Underline");
		font.add(underline);
		font.setForeground(Color.CYAN);
		
		decfontsize.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				doSmallerFontSizeAction();	
			}
		});
		decfontsize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, ActionEvent.CTRL_MASK));
		
		incfontsize.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				doLargerFontSizeAction();	
			}
		});	
		incfontsize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, ActionEvent.CTRL_MASK));


		
		JMenuItem quit = new JMenuItem("Exit");
		file.add(quit);
		
		quit.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);	
			}
		});
		

//		panel = new JPanel();
//		panel.setBackground(Color.BLACK);
//		panel.setSize(100, 100);
//		frame.getContentPane().add(panel, BorderLayout.SOUTH);
//
//		JButton bold = new JButton();
//		bold.setText("Bold");
//		bold.setBackground(Color.BLACK);
//		bold.setForeground(Color.CYAN);
//
//		JButton italics = new JButton();
//		italics.setText("Italics");
//		italics.setBackground(Color.BLACK);
//		italics.setForeground(Color.CYAN);
//		
//		JButton underline = new JButton();
//		underline.setText("Underline");
//		underline.setBackground(Color.BLACK);
//		underline.setForeground(Color.CYAN);
		
		bold.setFont(new Font("Serif", Font.BOLD, 15));
		italics.setFont(new Font("Serif", Font.ITALIC, 15));
		underline.setFont(new Font("Serif", Font.PLAIN, 15));

//		panel.add(bold);
//		panel.add(italics);
//		panel.add(underline);
		
		bold.addActionListener(new StyledEditorKit.BoldAction());
		bold.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		
		italics.addActionListener(new StyledEditorKit.ItalicAction());
		italics.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		
		underline.addActionListener(new StyledEditorKit.UnderlineAction());
		underline.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		
		
		JToggleButton theme = new JToggleButton();
		theme.setText("Light Mode");
		theme.setBackground(Color.CYAN);
		theme.setForeground(Color.BLACK);
		menuBar.add(theme);

		ItemListener itemListener = new ItemListener() { 
			  
            
            public void itemStateChanged(ItemEvent itemEvent) 
            { 
  
                int state = itemEvent.getStateChange(); 
  
                if (state == ItemEvent.SELECTED) { 
                	textArea.setBackground(Color.WHITE);
    				textArea.setForeground(Color.BLACK);
    				textArea.setCaretColor(Color.BLACK);
    				menuBar.setBackground(Color.DARK_GRAY);
    				menuBar.setForeground(Color.BLACK);
    				theme.setBackground(Color.BLACK);
    				theme.setForeground(Color.CYAN);
                } 
                else { 
                	textArea.setBackground(new java.awt.Color(39, 41, 43));
            		textArea.setForeground(Color.LIGHT_GRAY);
            		textArea.setCaretColor(Color.GREEN);
            		menuBar.setBackground(Color.BLACK);
            		menuBar.setForeground(Color.WHITE);
    				theme.setBackground(Color.CYAN);
    				theme.setForeground(Color.BLACK);  
    			} 
            } 
        }; 
        theme.addItemListener(itemListener);
        
        
        
		


	}

	public static void open()
	{	textArea.setText("");
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

			// Initialize s1 
			sl = br.readLine(); 

			// Take the input from the file 
			while ((s1 = br.readLine()) != null) { 
				sl = sl + "\n" + s1; 
			} 

			// Set the text 
			textArea.setText(sl); 
			br.close();
		} 
		catch (Exception evt) { 
			JOptionPane.showMessageDialog(frame, evt.getMessage()); 
		} 
	} 
	// If the user cancelled the operation 
	else
		JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
	}
	
	
	public static void saveAs()
	{
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 

		int r = j.showOpenDialog(null); 

		try {
			File myObj = new File(j.getSelectedFile().getAbsolutePath());
			if (myObj.createNewFile()) {
				FileWriter myWriter = new FileWriter(j.getSelectedFile().getAbsolutePath());
				myWriter.write(textArea.getText());
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
	
	public void doSmallerFontSizeAction()
	{
	  // get the current font
	  Font f = textArea.getFont();

	  // create a new, smaller font from the current font
	  Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()-1);

	  // set the new font in the editing area
	  textArea.setFont(f2);
	}
	
	public void doLargerFontSizeAction()
	{
	  // get the current font
		

		Font f = textArea.getFont();

		  // create a new, smaller font from the current font
		  Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+1);

		  // set the new font in the editing area
		  textArea.setFont(f2);

	}

}

