package text_editor;
import java.awt.*; 
import javax.swing.*; 
import java.io.*; 
import java.awt.event.*; 
import javax.swing.plaf.metal.*; 
import javax.swing.text.*; 
import java.util.*;
public class Texter extends JFrame implements ActionListener
{
	JTextArea t;
	File opFile;
	JFrame f;
	Texter()
	{
		try { 
		      
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme()); 
        } 
        catch (Exception e) { 
        }
			
			f = new JFrame("Text Editor");
			f.setBounds(100, 100, 616, 444);
			Font font = new Font("Segoe Script", Font.BOLD, 20);
			t= new JTextArea();
			f.getContentPane().add(t,BorderLayout.NORTH);
			JScrollPane s = new JScrollPane(t);
			f.getContentPane().add(s,BorderLayout.CENTER);
			t.setFont(font);
			t.setBounds(100, 100, 100,100);
			
			t.setBackground(Color.WHITE);
			t.setForeground(new java.awt.Color(160,137,255));
			
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setSize(100, 100);
			f.getContentPane().add(panel, BorderLayout.SOUTH);
			JButton bold = new JButton();
			bold.setText("Bold");
			bold.setBackground(new java.awt.Color(247,197,197));
			panel.add(bold);
			JButton italic = new JButton();
			italic.setText("Italics");
			italic.setBackground(new java.awt.Color(247,197,197));
			panel.add(italic);
			
			
			
			JMenuBar mb = new JMenuBar();
			mb.setMargin(new Insets(0, 5, 0, 0));
			mb.setBackground(Color.BLACK);
			mb.setForeground(Color.WHITE);
			f.getContentPane().add(mb, BorderLayout.NORTH);
			JMenu file = new JMenu("File");
			
			JMenu edit = new JMenu("Edit");
			
			JMenu exit = new JMenu("Exit");
			
			mb.add(file);
			mb.add(new JMenu("|")).setEnabled(false);
			mb.add(edit);
			mb.add(new JMenu("|")).setEnabled(false);
			
			
			exit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e1)
				{
	                System.exit(0);
				}
			});
			mb.add(exit);
			
			
			JMenuItem newFile = new JMenuItem("New");
			newFile.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					newfile();
				}
			});
			file.add(newFile);
			file.addSeparator();
			
			
			JMenuItem openFile = new JMenuItem("Open");
			openFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					open();
				}
			});
			file.add(openFile);
			file.addSeparator();
			
			
			JMenuItem saveFile = new JMenuItem("Save");
			file.add(saveFile);
			file.addSeparator();
			
			saveFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
					try{
					String text = t.getText().trim();
					 if(!text.equals("") && opFile == null) {
					 saveas();
					return ;
					 }
					 if(opFile == null && text.equals("")) {
					 JOptionPane.showMessageDialog(null, "Can't Save , No file is selected", "Error", JOptionPane.ERROR_MESSAGE);
					 return ;
					 }
					 String contents = t.getText();
					 Formatter form = new Formatter(opFile);
					 form.format("%s", contents);
					 form.close();
					}
					catch(Exception e3)
					{
						
					}
				}
				
			});
			JMenuItem saveasFile = new JMenuItem("Save As");
			file.add(saveasFile);
			file.addSeparator();
			saveasFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
					 saveas();
				}
			});
			
			JMenuItem closeFile = new JMenuItem("Close");
			file.add(closeFile);
			
			closeFile.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e4)
				{
					close();
				}
			});
			JMenuItem copyEdit = new JMenuItem("Copy");
			copyEdit.addActionListener(e -> t.copy());
			edit.add(copyEdit);
			edit.addSeparator();
			
			JMenuItem cutEdit = new JMenuItem("Cut");
			cutEdit.addActionListener(e -> t.cut());
			edit.add(cutEdit);
			edit.addSeparator();
			
			JMenuItem pasteEdit = new JMenuItem("Paste");
			pasteEdit.addActionListener(e -> t.paste());
			edit.add(pasteEdit);
		
			
	}
	void newfile()
	{
		 String text = t.getText().trim();
		 if(!text.equals("") && opFile == null)
		 {
			 
			 int result = JOptionPane.showConfirmDialog(f,"Do you want to save the document?", "Caution",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION)
            {
           	 saveas();
            }
            else if (result == JOptionPane.NO_OPTION)
            {
           	 	t.setText("");
            }
	 
		 }  
	}
	void open()
	{
		
		JFileChooser fc = new JFileChooser("f:"); 
        int r = fc.showOpenDialog(null); 
        if (r == JFileChooser.APPROVE_OPTION) 
        { 

            File fi = new File(fc.getSelectedFile().getAbsolutePath());
            try 
            {
            	String s1 = "",s2 = "";  
                FileReader fr = new FileReader(fi); 
                BufferedReader br = new BufferedReader(fr); 
                s2 = br.readLine(); 
                while ((s1 = br.readLine()) != null) 
                { 
                    s2 = s2 + "\n" + s1; 
                } 
                fr.close();
                t.setText(s2);
                opFile = fc.getSelectedFile();
            }
            catch(Exception ev)
            {
	
			}
        }
		
	}
	void saveas()
	{
		JFileChooser fs = new JFileChooser(new File("c:\\"));
		 fs.setDialogTitle("Save A File");
		 int result = fs.showSaveDialog(null);
		 if(result==JFileChooser.APPROVE_OPTION) {
			 String content = t.getText();
			 File fi=fs.getSelectedFile();
			 try
			 {
				 FileWriter fw = new FileWriter(fi.getPath());
				 fw.write(content);
				 fw.flush();
				 fw.close();
			 }
			 catch(Exception e2)
			 {
				 
			 }
		 }
	}
	void close()
	{
		String text = t.getText().trim();
		 if(!text.equals("") && opFile == null)
		 {
			 
			 int result = JOptionPane.showConfirmDialog(f,"Do you want to save the document?", "Caution",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
             if(result == JOptionPane.YES_OPTION)
             {
            	 saveas();
             }
             else if (result == JOptionPane.NO_OPTION)
             {
            	 System.exit(0);
             }
	 
		 }     
	}
	
	public static void main(String[]args)
	{
		Texter ob = new Texter();
	}
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
