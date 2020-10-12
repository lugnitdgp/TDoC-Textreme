import java.awt.Color;

public class function_color {
	Text gui;
	
	public function_color(Text gui) {
		this.gui=gui;
	}
	
	public void changeColor(String color) {
		switch(color) {
		case "white":
			gui.frame.getContentPane().setBackground(Color.white);
			gui.textArea.setBackground(Color.white);
			gui.textArea.setForeground(Color.black);
			break;
		case "black":
			gui.frame.getContentPane().setBackground(Color.black);
			gui.textArea.setBackground(Color.black);
			gui.textArea.setForeground(Color.white);
			break;
		case "blue":
			gui.frame.getContentPane().setBackground(Color.blue);
			gui.textArea.setBackground(Color.blue);
			gui.textArea.setForeground(Color.white);
			break;
		
		}
		
	}

}
