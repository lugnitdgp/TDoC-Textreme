package pkg;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 


/*Initial name for the text editor is "Texbelish"*/


class Editor_main 
	extends JFrame 
	implements ActionListener
{
	
	JFrame frame;
    JMenuBar menubar;
    JMenu file,edit,exit;
    JTextArea textArea;
    JScrollPane scroll;
    JMenuItem newFile,open,save,saveas,cut,copy,paste;
    JPanel panel;
    JButton bold,italic;
    //JMenu editMenu;
	public Editor_main()
	{
		
		Color bluegray=new Color(151,188,215);
		frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Texbelish"); 
		frame.setBounds(100, 100, 616, 444);

        menubar = new JMenuBar();
        menubar.setOpaque(true);
        menubar.setMargin(new Insets(0,5,0,0));
        menubar.setBackground(bluegray);
        

        file = new JMenu("File");
        file.setFont(new Font("Cambria", Font.PLAIN, 15));
        
        newFile = new JMenuItem("New");
        file.add(newFile);
        file.addSeparator();
        
        open = new JMenuItem("Open");
        file.add(open);
        file.addSeparator();
        
        save = new JMenuItem("Save");
        file.add(save);
        file.addSeparator();
        
        saveas = new JMenuItem("Save As");
        file.add(saveas);

        
        
        
        edit = new JMenu("Edit");
        edit.setFont(new Font("Cambria", Font.PLAIN, 15));
        
        cut = new JMenuItem("Cut");
        edit.add(cut);
        edit.addSeparator();
        
        copy = new JMenuItem("Copy");
        edit.add(copy);
        edit.addSeparator();
        
        paste = new JMenuItem("Paste");
        edit.add(paste);
        
        exit = new JMenu("Exit");
        exit.setFont(new Font("Cambria", Font.PLAIN, 15));

        menubar.add(file);
        menubar.add(new JMenu("|")).setEnabled(false);
        menubar.add(edit);
        menubar.add(new JMenu("|")).setEnabled(false);
        menubar.add(exit);
        
        textArea = new JTextArea();
		textArea.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		textArea.setLineWrap(true);
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		
		scroll = new JScrollPane(textArea);
		scroll.setLocation(10,210);
		scroll.setSize(500,400);
		
		panel = new JPanel();
		panel.setSize(616,200);
		panel.setBackground(bluegray);
		
		bold = new JButton("Bold");
		bold.setSize(100,20);
		bold.setBackground(Color.BLUE);
		bold.setForeground(Color.WHITE);
		italic = new JButton("Italic");
		italic.setSize(100,20);
		italic.setBackground(Color.BLUE);
		italic.setForeground(Color.WHITE);
		panel.add(bold);
		panel.add(italic);
		
		
        frame.add(scroll,BorderLayout.CENTER);
        frame.add(menubar,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.SOUTH);
        frame.setVisible(true);
		
	}
	public static void main(String args[])throws Exception
	{
		Editor_main window = new Editor_main();
		window.frame.setLocationRelativeTo(null);
		window.frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
	}
}
