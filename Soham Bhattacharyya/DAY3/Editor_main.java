package pkg;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.io.*;
import java.util.*;
import javax.swing.filechooser.*;

/*Initial name for the text editor is "Texbelish"*/


class Editor_main 
	extends JFrame 
	implements ActionListener
{
	String filename;
	JFrame frame;
    JMenuBar menubar;
    JMenu file,edit,exit;
    JTextArea textArea;
    JScrollPane scroll;
    JMenuItem newFile,open,save,saveas,cut,copy,paste,savenexit;
    JPanel panel;
    JButton bold,italic;
    
    String selected;
    //JMenu editMenu;
	public Editor_main()
	{
		filename="";
		selected="";
		
		Color bluegray=new Color(151,188,215);
		frame = new JFrame();

		frame.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent we) {
		        int result = JOptionPane.showConfirmDialog(frame,
		            "Do you want to Save before closing?", "Exit Confirmation",
		            JOptionPane.YES_NO_OPTION);
		        if (result == JOptionPane.YES_OPTION)
		        {
		        	try {
						save();
					}
					catch(IOException ex){
						System.err.println("Caught IOException: " +  ex.getMessage());
					}	
		        }
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      }
		    });
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
        newFile.addActionListener(this);
        
        open = new JMenuItem("Open");
        file.add(open);
        file.addSeparator();
        open.addActionListener(this);
        
        save = new JMenuItem("Save");
        file.add(save);
        file.addSeparator();
        save.addActionListener(this);
        
        saveas = new JMenuItem("Save As");
        file.add(saveas);
        saveas.addActionListener(this);

        
        
        
        edit = new JMenu("Edit");
        edit.setFont(new Font("Cambria", Font.PLAIN, 15));
        
        cut = new JMenuItem("Cut");
        edit.add(cut);
        edit.addSeparator();
        cut.addActionListener(this);
        
        copy = new JMenuItem("Copy");
        edit.add(copy);
        edit.addSeparator();
        copy.addActionListener(this);
        
        paste = new JMenuItem("Paste");
        edit.add(paste);
        paste.addActionListener(this);
        
        exit = new JMenu("Exit");
        exit.setFont(new Font("Cambria", Font.PLAIN, 15));
        savenexit = new JMenuItem("Save and exit");
        savenexit.addActionListener(this);
        exit.add(savenexit);

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
	
	void save() throws IOException
	{
		if(filename.equals(""))
			saveas();
		else 
		{
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			
			String question="Previous contents of this file will be overwritten. Proceed?";
			int result = JOptionPane.showConfirmDialog(frame,question, "Confirm Save",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
	        
			if(result == JOptionPane.YES_OPTION)
			{
				File f= new File(filename);
				
				FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                
                String contents= textArea.getText();
                
                pw.print(contents);
                bw.close();
                fw.close();
			}
			
	    }
	}	
	
	void newFile()throws IOException
	{
		String question="Save current file?";
		int result = JOptionPane.showConfirmDialog(frame,question, "Confirm New File",
	               JOptionPane.YES_NO_OPTION,
	               JOptionPane.QUESTION_MESSAGE);
		if(result == JOptionPane.YES_OPTION)
			save();
		textArea.setText("");
		filename="";
		frame.setTitle("Texbelish");
	}
	
	void saveas() throws IOException
	{
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		int r = j.showSaveDialog(null); 
        String file;
        File f;
        
        if (r == JFileChooser.APPROVE_OPTION) 
        { 
            file= j.getSelectedFile().getAbsolutePath(); 
            int z=file.lastIndexOf(".");           
            
            if((file.substring(z+1,file.length()).equals("txt")))
    		{
            	int x= file.lastIndexOf("\\");
                String f0=file.substring(x+1,file.length());
                f0=f0.concat(" - Texbelish");
                filename=file;
                frame.setTitle(f0);
                f=new File(file);
                
                if (f.createNewFile()) {
                    System.out.println("File created: " + f.getName());
                  } 
                else 
                	JOptionPane.showMessageDialog(null, "File already exists");
                    
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                
                String contents= textArea.getText();
                
                pw.print(contents);
                bw.close();
                fw.close();
    		}
            else
            {
            	System.out.println("User cancelled it");
            }
        }
	}
        
	void open() throws IOException
	{
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
		  
        int r = j.showOpenDialog(null); 
        String file;
        File f;
        // if the user selects a file 
        if (r == JFileChooser.APPROVE_OPTION) 

        { 
            file= j.getSelectedFile().getAbsolutePath(); 
            int z=file.lastIndexOf(".");
            
            
            if((file.substring(z+1,file.length()).equals("txt")))
    		{
            	int x= file.lastIndexOf("\\");
                String f0=file.substring(x+1,file.length());
                filename=file;
                f0=f0.concat(" - Texbelish");
                frame.setTitle(f0);
                f=new File(file);
                
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String contents="",buffer;
                while((buffer=br.readLine())!=null) {
                	contents=contents.concat(buffer);
                	contents=contents.concat("\n");
                }
                br.close();
                fr.close();
                textArea.setText(contents);
    		}
            
            else
            {
            	JOptionPane.showMessageDialog(null, "No files supported except .txt files");
            }
        } 
	}
	public static void main(String args[])throws Exception
	{
		Editor_main window = new Editor_main();
		window.frame.setLocationRelativeTo(null);
		window.frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==open)
		{
			try {
				open();
			}
			catch(IOException ex){
				System.err.println("Caught IOException: " +  ex.getMessage());
			}
		}
		else if(e.getSource()==saveas)
		{
			try {
				saveas();
			}
			catch(IOException ex){
				System.err.println("Caught IOException: " +  ex.getMessage());
			}
		}
		else if(e.getSource()==save)
		{
			try {
				save();
			}
			catch(IOException ex){
				System.err.println("Caught IOException: " +  ex.getMessage());
			}
		}
		else if(e.getSource()==newFile)
		{
			try {
				newFile();
			}
			catch(IOException ex){
				System.err.println("Caught IOException: " +  ex.getMessage());
			}
		}
		else if(e.getSource()==savenexit)
		{
			int result = JOptionPane.showConfirmDialog(frame,
		            "Do you want to Save before closing?", "Exit Confirmation",
		            JOptionPane.YES_NO_OPTION);
	        if (result == JOptionPane.YES_OPTION)
	        {
	        	try {
					save();
				}
				catch(IOException ex){
					System.err.println("Caught IOException: " +  ex.getMessage());
				}	
	        }
	        System.exit(0);
		}
		else if(e.getSource()==paste)
		{
			if(!selected.equals(""))
			{
				if(textArea.getSelectedText()==null)
					textArea.insert(selected, textArea.getCaretPosition());
				else
					textArea.replaceSelection(selected);
			}
			else
				JOptionPane.showMessageDialog(null, "Nothing was selected");
		}
		else if(e.getSource()==copy)
		{
			selected = textArea.getSelectedText();
		}
		else if(e.getSource()==cut)
		{
			selected = textArea.getSelectedText();
			textArea.replaceSelection("");
		}
	}
}
