package applicaton; // Package consists of classes and sub-packages
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class TextEditor {
	private final String title = "Simple Text Editor";
	private JFrame frame;
	private JTextPane textArea;
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.GREEN);
		textArea.setCaretColor(Color.GREEN);
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane(textArea);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
	}
	
}