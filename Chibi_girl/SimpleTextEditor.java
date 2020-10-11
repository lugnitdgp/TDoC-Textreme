package application;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;

import javax.swing.AbstractAction;
import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

class SimpleTextEditor implements ActionListener
{
	JFrame frame;JTextPane txtarea;JScrollPane scroll;
	JMenuBar menu;JMenu file,edit,exit,view,preferences;
	JMenuItem newfile,openfile,savefile,undo,redo,saveas,toggle,font,copy,cut,paste,view_panel,hide_panel,close,printfile;
	JPanel panel,controlPanel,buttonpanel;JButton bold,italics,underline,ok,cancel;JOptionPane dopen,dquit;
	JLabel sampleText;JDialog dialog;JComboBox fontComboBox,sizeComboBox;JCheckBox boldCheck, italCheck;
	String filename=null;String[] fonts;Font f;
	Integer[] sizes = { 7, 8, 9, 10, 11, 12, 14, 18, 20, 22, 24, 36 };
	UndoManager undomanager=new UndoManager();
	
	SimpleTextEditor()
	{
		frame= new JFrame("Untitled Document");
		try
		{
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 
		}
		catch(Exception e) { }
	
	
	frame.setBounds(100,100,616,444);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	txtarea= new JTextPane();
	txtarea.setFont(new Font("Chilanka Regular", Font.PLAIN, 20));

	frame.getContentPane().add(txtarea,BorderLayout.NORTH);
	txtarea.setBackground(Color.WHITE);
	txtarea.setForeground(Color.MAGENTA);
	txtarea.setBounds(200,100,200,100);
	
	
	scroll= new JScrollPane(txtarea); 
	frame.getContentPane().add(scroll,BorderLayout.CENTER);
	
	menu=new JMenuBar();
	menu.setMargin(new Insets(5,10,5,10));
	menu.setBackground(new java.awt.Color(120,180,255));
	
	frame.getContentPane().add(menu,BorderLayout.NORTH);
	
	file=new JMenu("File");edit=new JMenu("Edit");view=new JMenu("Bottom Panel");
	preferences=new JMenu("Preferences");
	menu.add(file);menu.add(new JMenu("|")).setEnabled(false);
	menu.add(edit);menu.add(new JMenu("|")).setEnabled(false);
	menu.add(preferences);menu.add(new JMenu ("|")).setEnabled(false);
	undomanager=new UndoManager();
	newfile=new JMenuItem("New");openfile=new JMenuItem("Open");savefile=new JMenuItem("Save");
	saveas=new JMenuItem("SaveAs");printfile=new JMenuItem("Print");
	view_panel=new JMenuItem("View");hide_panel=new JMenuItem("Hide");
	copy=new JMenuItem("Copy");paste=new JMenuItem("Paste");close=new JMenuItem("Exit");
	cut=new JMenuItem("Cut");toggle=new JMenuItem("Toggle view mode");font=new JMenuItem("Fonts And Colors");
	
	newfile.addActionListener(this);openfile.addActionListener(this);printfile.addActionListener(this);
	savefile.addActionListener(this);saveas.addActionListener(this);
	copy.addActionListener(this);paste.addActionListener(this);cut.addActionListener(this);
	view_panel.addActionListener(this);hide_panel.addActionListener(this);
	close.addActionListener(this);toggle.addActionListener(this);font.addActionListener(this);
	
	undo=new JMenuItem("Undo");redo=new JMenuItem("Redo");
	undo.addActionListener((new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				undomanager.undo();
			} catch (Exception ex) {
			}
		}
	}));
	redo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				undomanager.redo();
			} catch (Exception ex) {
			}
		}
	});
	txtarea.getDocument().addUndoableEditListener(new UndoableEditListener() {
		public void undoableEditHappened(UndoableEditEvent e) {
			undomanager.addEdit(e.getEdit());
		}
	});
	   txtarea.getActionMap().put("Undo",new AbstractAction("Undo") {
	           public void actionPerformed(ActionEvent e) {
	               try {
	                   if (undomanager.canUndo()) {
	                       undomanager.undo();
	                   }
	               } catch (CannotUndoException ex) {
	               }
	           }
	      });
	 
	   txtarea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
	   txtarea.getActionMap().put("Redo",new AbstractAction("Redo") {
	           public void actionPerformed(ActionEvent e) {
	               try {
	                   if (undomanager.canRedo()) {
	                       undomanager.redo();
	                   }
	               } catch (CannotRedoException ex) {
	               }
	           }
	       });
	   txtarea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
	
	
	menu.add(close);
	file.add(newfile);file.addSeparator();file.add(openfile);
	file.addSeparator();file.add(printfile);file.addSeparator();file.add(savefile);
	file.addSeparator();view.add(view_panel);view.addSeparator();view.add(hide_panel);
	preferences.add(view);preferences.addSeparator();file.add(saveas);edit.add(cut);edit.addSeparator();
	edit.add(copy);edit.addSeparator();edit.add(paste);edit.addSeparator();edit.add(undo);
	edit.addSeparator();edit.add(redo);preferences.add(toggle);preferences.addSeparator();
	preferences.add(font);
	
	panel=new JPanel();
	panel.setBackground(Color.PINK);
	panel.setSize(100,100);
	frame.getContentPane().add(panel,BorderLayout.SOUTH);
	
	bold=new JButton("Bold");italics=new JButton("Italics");underline=new JButton("Underline");
	bold.setBackground(new java.awt.Color(100,140,255));
	bold.addActionListener(this);
	italics.setBackground(new java.awt.Color(100,140,255));
	underline.setBackground(new java.awt.Color(100,140,255));
	bold.setFont(new Font("Serif", Font.BOLD, 15));
	italics.setFont(new Font("Serif", Font.ITALIC, 15));
	underline.setFont(new Font("Serif", Font.PLAIN, 15));
	Font font = underline.getFont();
	Map attributes = font.getAttributes();
	attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	underline.setFont(font.deriveFont(attributes));
	
 
	bold.addActionListener(new StyledEditorKit.BoldAction());
	italics.addActionListener(new StyledEditorKit.ItalicAction());
	underline.addActionListener(new StyledEditorKit.UnderlineAction());
	
	panel.add(bold);panel.add(italics);panel.add(underline);
	
	sampleText= new JLabel("      Sample Text");
	dialog = new JDialog(frame,"Select Font");
	dialog.add(sampleText, BorderLayout.CENTER);
	
	FontListener fl = new FontListener();

	GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
	fonts = g.getAvailableFontFamilyNames();

	controlPanel = new JPanel(); buttonpanel=new JPanel();
	fontComboBox = new JComboBox(fonts);sizeComboBox = new JComboBox(sizes);
	boldCheck = new JCheckBox("Bold");italCheck = new JCheckBox("Italics");
	ok= new JButton("Select"); cancel=new JButton ("Cancel");
	
	boldCheck.addActionListener(fl);fontComboBox.addActionListener(fl);ok.addActionListener(this);
	italCheck.addActionListener(fl);sizeComboBox.addActionListener(fl);cancel.addActionListener(this);
	
	controlPanel.add(new JLabel("Family: "));controlPanel.add(new JLabel("Size: "));
    sizeComboBox.setSelectedIndex(5);
    
    controlPanel.add(fontComboBox);controlPanel.add(sizeComboBox);buttonpanel.add(ok);
    controlPanel.add(boldCheck);controlPanel.add(italCheck);buttonpanel.add(cancel);

    dialog.add(controlPanel, BorderLayout.SOUTH);
    dialog.add(buttonpanel,BorderLayout.NORTH);
	}
	
	 public void actionPerformed(ActionEvent e) 
	 { 
	        String s = e.getActionCommand(); 
	  
	        if (s.equals("Cut")) { 
	            txtarea.cut(); 
	        } 
	        else if (s.equals("Copy")) { 
	            txtarea.copy(); 
	        } 
	        else if (s.equals("Paste")) { 
	            txtarea.paste(); 
	        } 
	        else if (s.equals("Toggle view mode")){
	        	if(txtarea.getBackground()==Color.WHITE)
	        	{
	        		txtarea.setBackground(Color.DARK_GRAY);
	        		txtarea.setForeground(Color.CYAN);
	        		panel.setBackground(Color.LIGHT_GRAY);
	        		menu.setBackground(Color.LIGHT_GRAY);
	        	}
	        	else
	        	{
	        		txtarea.setBackground(Color.WHITE);
	        		txtarea.setForeground(Color.MAGENTA);
	        		panel.setBackground(Color.PINK);
	        		menu.setBackground(new java.awt.Color(120,180,255));
	        	}
	        }
	        else if (s.contentEquals("Fonts And Colors")) {
	        	 dialog.setSize(620, 200);
	        	 dialog.setLocationRelativeTo(null);
	        	 dialog.setVisible(true);
	        }
	        else if (s.contentEquals("Select"))
	        {
	        	txtarea.setFont(f);
	        	dialog.setVisible(false);
	        }
	        else if (s.contentEquals("Cancel")) {
	        	dialog.setVisible(false);
	        }
	        else if (s.equals("Exit")) { 
	        	Object stringArray[] = { "Sure", "No. I'd like to change/save" };
	        	 int response=JOptionPane.showOptionDialog(frame, "You're about to exit. Continue?", "Select an Option",
	        	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, stringArray,stringArray[0]);
	        
	        	 if (response == JOptionPane.YES_OPTION) {
	        		 frame.setVisible(false);
	        	    } 
	        	
	        } 
	        else if (s.equals("New")) {
	        	 Object stringArray[] = { "Sure", "No. I'd like to change/save" };
	        	 int response=JOptionPane.showOptionDialog(frame, "You're about to delete this text. Continue?", "Select an Option",
	        	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, stringArray,stringArray[0]);
	        
	        	 if (response == JOptionPane.YES_OPTION) {
	        		 txtarea.setText(""); 
	        		 filename=null;
	        		 frame.setTitle("Untitled Document");
	        	    } 
	        }
	        
	        else if (s.equals("View")) {
	        		panel.setVisible(true);
	        }
	        else if (s.equals("Hide")) {
        		panel.setVisible(false);
            }
	        
	        else if (s.equals("Open")) 
	        {  
	            JFileChooser j = new JFileChooser("/home/aishwarya"); 
	            int r = j.showOpenDialog(null); 
	            // If the user selects a file 
	            if (r == JFileChooser.APPROVE_OPTION) { 
	                // Set the label to the path of the selected directory 
	            	
	                filename=j.getSelectedFile().getAbsolutePath();
	                String name=j.getSelectedFile().getName();
	                File fi = new File(filename); 
	                try { 
	                    String s1 = "", sl = ""; 
	                    BufferedReader br = new BufferedReader(new FileReader(fi)); 
	                    sl = br.readLine(); 
	                    while ((s1 = br.readLine()) != null) { 
	                        sl = sl + "\n" + s1; 
	                    } 
	                    txtarea.setText(sl); 
	                    frame.setTitle(name);
	                } 
	                catch (Exception evt) { 
	                    JOptionPane.showMessageDialog(frame, evt.getMessage()); 
	                } 
	            }
	        } 
	        else if (s.equals("SaveAs")) { 
	            saveFile(null); 
	        } 
	        else if(s.equals("Save")) {
            	saveFile(filename);
            }
	        else if (s.equals("Print")) { 
	            try { 
	                txtarea.print(); 
	            } 
	            catch (Exception evt) { 
	                JOptionPane.showMessageDialog(frame, evt.getMessage()); 
	            } 
	        } 
	        
	  }
	 
	 
	 private void saveFile(String name) 
	 {
		    if (name == null) {  
		    	// get filename from user
		      JFileChooser fc = new JFileChooser();
		      if (fc.showSaveDialog(null) != JFileChooser.CANCEL_OPTION)
		        name = fc.getSelectedFile().getAbsolutePath();
		      String setname=fc.getSelectedFile().getName();
		      	frame.setTitle(setname);
		    }
		    if (name != null) {  
		      try {
		        Formatter out = new Formatter(new File(name));  
		        filename = name;
		        out.format("%s", txtarea.getText());
		        out.close();
		        JOptionPane.showMessageDialog(null, "Saved to " + filename,
		          "Save File", JOptionPane.PLAIN_MESSAGE);
		      }
		      catch (FileNotFoundException e) {
		        JOptionPane.showMessageDialog(null, "Cannot write to file: " + name,
		          "Error", JOptionPane.ERROR_MESSAGE);
		      }
		    }
	 }
	 
	 
	 private class FontListener implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		      updateText();
		    }
	 
	 
	 public void updateText() 
	 {
	      String name = (String) fontComboBox.getSelectedItem();

	      Integer size = (Integer) sizeComboBox.getSelectedItem();

	      int style;
	      if (boldCheck.isSelected() && italCheck.isSelected())
	        style = Font.BOLD | Font.ITALIC;
	      else if (boldCheck.isSelected())
	        style = Font.BOLD;
	      else if (italCheck.isSelected())
	        style = Font.ITALIC;
	      else
	        style = Font.PLAIN;

	      f = new Font(name, style, size.intValue());
	      sampleText.setFont(f);
	      
	    }
	 }
	 
	 
public static void main(String[] args)
{
	
	SimpleTextEditor window = new SimpleTextEditor();
	window.frame.setLocationRelativeTo(null);
	window.frame.setVisible(true);
}
}





