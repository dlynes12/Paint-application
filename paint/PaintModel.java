package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.canvas.GraphicsContext;

public class PaintModel extends Observable {

	// DO NOT NEED private ArrayList<Shape> shapes = new ArrayList<Shape>(); // An ArrayList of shapes
	private PaintCommandStack paintCommandStack = new PaintCommandStack();
	private PaintCommandStack undoStack = new PaintCommandStack();
	
	// Need a stack of paint commands maintained by paintmodel
	
	// =============================== SHAPE ================================
	
/*	public void addShape(Shape shape) {
		this.shapes.add(shape);
		this.notifyChange();
		System.out.println(this.shapes.size()); // debugging
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	*/
	public void notifyChange() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public PaintCommandStack getPaintCommandStack() {
		return paintCommandStack;
	}
	
	// ============================== UNDO/REDO ==============================
	
	/**
	 * Add a new DrawingCommand to the paintCommandStack.
	 * Performing a new command clears the undoStack.
	 * The user should not be able to redo if they have performed a new command
	 * after undoing.
	 * @param newCommand
	 */
	public void addCommand(DrawingCommand newCommand) {
		if (!this.undoStack.isEmpty()) {
			this.paintCommandStack.pushCommand(newCommand);
			this.undoStack.clearStack();
		} else 
			this.paintCommandStack.pushCommand(newCommand);
	}
	
	/**
	 * Undo the last DrawingCommand performed.
	 */
	public void undo() {
		if (!this.paintCommandStack.isEmpty()) {
			DrawingCommand undoneCommand = this.paintCommandStack.popCommand();
			this.undoStack.pushCommand(undoneCommand);
			this.notifyChange();
		}
	}
	
	/**
	 * Redo a previously undone DrawingCommand.
	 */
	public void redo() {
		if (!this.undoStack.isEmpty()) {
			DrawingCommand undoneCommand = this.undoStack.popCommand();
			this.paintCommandStack.pushCommand(undoneCommand);
			this.notifyChange();
		}
	}
	
/*	public void DrawAll(GraphicsContext g) {
		
		for (Shape s : this.shapes) {
			s.execute(g);
		}
	}*/
}