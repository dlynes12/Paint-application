package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
/**
 * A generic drawing command
 * @author ellez
 *
 */
public interface DrawingCommand {
	
	public void execute(GraphicsContext g);

}

/* PaintPanel acts as the client
 * ShapeManipulators creates command and adds to queue in PaintModel
 * Paint Model is invoker
 * DrawingCommand is the Command Interface
 * Draw[ShapeSubclass] is a concrete command containing a shape instance and
 * execute call
 * on receiver with actual knowledge on command
 * Shape Subclasses are receivers and carry the code to be executed
 */
