package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
/**
 * A squiggle is a collection ordered points that make a 2D path when connected.
 * @author ellez
 *
 */
public class Squiggle extends Shape {
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public Squiggle() {
		this.color = null;
		this.strokeThickness = 1;
		this.isFilled = false;
	}
	
	public Squiggle(ArrayList<Point> points) {
		this.points = points;
		
		this.color = null;
		this.strokeThickness = 1;
		this.isFilled = false;
	}

	public void addPoint(Point point) {
		this.points.add(point);
	}
	
	/**
	 * Remove a point from Squiggle iff the point being removed
	 * is the first or last point of Squiggle.
	 * @param index - integer 0 or length of points ArrayList minus 1
	 */
	public void removePoint(int index) {
		if (index == 0 || index == this.getSizePoints()-1)
			this.points.remove(index);
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}

	public int getSizePoints() {
		return points.size();
	}
	
	@Override
	public void setFilled(boolean isFilled) {
		// A Squiggle in this Paint program is defined as an open shape that can't be filled.
	}
	
	/**
	 * Squiggle knows how to draw itself.
	 * @param g GraphicsContext
	 */
	@Override
	public void execute(GraphicsContext g) {
		
		for (int i = 0; i < this.getSizePoints() - 1; i++) {
			Point p1 = this.points.get(i);
			Point p2 = this.points.get(i + 1);
			
			g.setStroke(this.getColor());
			g.setLineWidth(this.getStrokeThickness());
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}
}
