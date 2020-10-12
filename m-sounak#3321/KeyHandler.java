import java.awt.FileDialog;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class KeyHandler implements KeyListener{
	
	Text gui;
	public KeyHandler(Text gui) {
		this.gui=gui;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_S) {
			FileDialog fd = new FileDialog(gui.frame,"Save",FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile()!=null) 
				gui.frame.setTitle(fd.getFile());
			
  
                try { 
                    // Create a file writer 
                    FileWriter wr = new FileWriter(fd.getDirectory() + fd.getFile()); 
                    gui.l1=fd.getDirectory();
                    gui.l2=fd.getFile();
  
                    // Create buffered writer to write 
                    //BufferedWriter w = new BufferedWriter(wr); 
  
                    // Write 
                    wr.write(gui.textArea.getText()); 
  
                    //w.flush(); 
                    wr.close(); 
                    gui.s=1;
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(gui.frame, evt.getMessage()); 
                } 
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}

