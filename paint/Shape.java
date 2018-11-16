package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * A Shape in this Paint program is an open or enclosed 2D figure.
 * @author diazjaze
 *
 */
public abstract class Shape implements DrawingCommand {
	
	protected Color color;
	protected int strokeThickness;
	protected boolean isFilled;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getStrokeThickness() {
		return strokeThickness;
	}
	public void setStrokeThickness(int strokeBorder) {
		this.strokeThickness = strokeBorder;
	}
	public boolean isFilled() {
		return isFilled;
	}
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
	/**
	 * Shape knows how to draw itself.
	 * @param g GraphicsContext
	 */
	public void execute(GraphicsContext g) {
	}
}
