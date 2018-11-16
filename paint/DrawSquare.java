package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Concrete DrawingCommand containing an instance of Square.
 * DrawSquare is able to execute the draw(execute) method in 
 * class Square.
 * @author diazjaze
 *
 */
public class DrawSquare implements DrawingCommand {
	private Square square;
	/**
	 * Construct a new command that can execute a method
	 * that draws a Square on a canvas.
	 * @param square
	 */
	public DrawSquare (Square square) {
		this.square = square;
	}

	public void execute(GraphicsContext g) {
		this.square.execute(g);
	}
}
