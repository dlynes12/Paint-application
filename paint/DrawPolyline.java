package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Concrete DrawingCommand containing an instance of Polyline.
 * DrawPolyline is able to execute the draw(execute) method in 
 * class Polyline.
 * @author diazjaze
 *
 */
public class DrawPolyline implements DrawingCommand {
	private Polyline polyline;
	/**
	 * Construct a new command that can execute a method
	 * that draws a Polyline on a canvas.
	 * @param Polyline
	 */
	public DrawPolyline (Polyline polyline) {
		this.polyline = polyline;
	}

	public void execute(GraphicsContext g) {
		this.polyline.execute(g);
	}
}
