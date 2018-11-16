package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
/**
 * A stack collection of DrawingCommands for the Paint program.
 * @author diazjaze
 *
 */
public class PaintCommandStack {
	
	private ArrayList<DrawingCommand> commandStack;
	
	public PaintCommandStack() {
		commandStack = new ArrayList<DrawingCommand>();
	}
	
	/**
	 * Add a new command to the end of commandStack.
	 * @param command
	 */
	public void pushCommand(DrawingCommand command) {
		this.commandStack.add(command);
	}
	
	/**
	 * Remove and return the command at the end of the 
	 * commandStack.
	 * @return
	 */
	public DrawingCommand popCommand() {
		DrawingCommand lastCommand = this.commandStack.remove(this.getStackSize()-1);
		return lastCommand;
	}
	
	/**
	 * Return the command at the end of this commandStack.
	 * @return
	 */
	public DrawingCommand peekCommand() {
		DrawingCommand topCommand = this.commandStack.remove(this.getStackSize()-1);
		this.pushCommand(topCommand);
		return topCommand;
		
	}
	/**
	 * Return the size of commandStack.
	 * @return
	 */
	public int getStackSize() {
		return this.commandStack.size();
	}
	
	
	/**
	 * @return true iff commandStack is empty,
	 * otherwise false.
	 */
	public boolean isEmpty() {
		return commandStack.isEmpty();
	}
	
	/**
	 * Remove all DrawingCommands in commandStack.
	 */
	public void clearStack() {
		commandStack.clear();
	}
	
	/**
	 * Execute every command in 
	 * @param g
	 */
	public void operateAll(GraphicsContext g) {
		
		for (DrawingCommand command: this.commandStack) {
			command.execute(g);
		}
		// commandQueue.clear();
	}
}
