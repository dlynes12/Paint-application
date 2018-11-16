package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
/**
 * A Polyline is a collection of ordered points that when drawn,
 * are connected by straight lines.
 * @author ellez
 *
 */
public class Polyline extends Shape {
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public Polyline() {
		this.color = null;
		this.strokeThickness = 1;
		this.isFilled = false;
	}
	
	public Polyline(ArrayList<Point> points) {
		this.points = points;
		
		this.color = null;
		this.strokeThickness = 1;
		this.isFilled = false;
	}
	
	public void addPoint(Point point) {
		this.points.add(point);
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	public int getNumberOfPoints() {
		return this.points.size();
	}
	
	@Override
	public void setFilled(boolean isFilled) {
		// A Polyline in this Paint program is defined as an open shape that can't be filled.
	}
	
	/**
	 * Polyline knows how to draw itself.
	 * @param g GraphicsContext
	 */
	@Override
	public void execute(GraphicsContext g) {
		for (int i = 0; i < this.getNumberOfPoints() - 1; i++) {
			Point p1 = this.points.get(i);
			Point p2 = this.points.get(i + 1);
			
			g.setStroke(this.getColor());
			g.setLineWidth(this.getStrokeThickness());
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}
}
