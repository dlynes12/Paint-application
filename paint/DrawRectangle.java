package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Concrete DrawingCommand containing an instance of Rect.
 * DrawRectangle is able to execute the draw(execute) method in 
 * class Rect.
 * @author diazjaze
 *
 */
public class DrawRectangle implements DrawingCommand {
	private Rect rect;
	/**
	 * Construct a new command that can execute a method
	 * that draws a Rectangle on a canvas.
	 * @param rect
	 */
	public DrawRectangle(Rect rect) {
		this.rect = rect;
	}

	public void execute(GraphicsContext g) {
		this.rect.execute(g);
	}
}
