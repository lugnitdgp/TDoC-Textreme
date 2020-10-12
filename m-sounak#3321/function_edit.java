
public class function_edit {
	
	Text gui;
	
	public function_edit(Text gui) {
		this.gui=gui;
	}
	
	public void undo() {
		gui.manager.undo();
	}
	
	public void redo() {
		gui.manager.redo();
	}

}
