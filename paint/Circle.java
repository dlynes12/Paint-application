package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Shape {
	
	private Point centre;
	private int radius;

	public Circle() {}
	
	public Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
		
		this.color = null;
		this.strokeThickness = 1;
		this.isFilled = false;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Circle knows how to draw itself.
	 * @param g GraphicsContext
	 */
	@Override
	public void execute(GraphicsContext g) {
		int x, y, r;
		x = this.getCentre().getX();
		y = this.getCentre().getY();
		r = this.getRadius();
		
		g.setStroke(this.getColor());
		g.setLineWidth(this.getStrokeThickness());
		
		if (this.isFilled) {
			g.setFill(this.getColor());
			g.fillOval(x - r, y - r, r * 2, r * 2);
		} else
			g.strokeOval(x - r, y - r, r * 2, r * 2);
	}
}
