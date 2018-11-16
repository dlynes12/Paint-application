package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Concrete DrawingCommand containing an instance of Squiggle.
 * DrawSquiggle is able to execute the draw(execute) method in 
 * class Squiggle.
 * @author diazjaze
 *
 */
public class DrawSquiggle implements DrawingCommand {
	private Squiggle squiggle;
	/**
	 * Construct a new command that can execute a method
	 * that draws a Squiggle on a canvas.
	 * @param rect
	 */
	public DrawSquiggle (Squiggle squiggle) {
		this.squiggle = squiggle;
	}

	public void execute(GraphicsContext g) {
		this.squiggle.execute(g);
	}
}
