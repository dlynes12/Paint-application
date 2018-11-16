package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Concrete DrawingCommand containing an instance of Circle.
 * DrawCircle is able to execute the draw(execute) method in 
 * class Circle.
 * @author diazjaze
 *
 */
public class DrawCircle implements DrawingCommand {
	private Circle circle;
	/**
	 * Construct a new command that can execute a method
	 * that draws a Circle on a canvas.
	 * @param rect
	 */
	public DrawCircle(Circle circle) {
		this.circle = circle;
	}

	public void execute(GraphicsContext g) {
		this.circle.execute(g);
	}
}