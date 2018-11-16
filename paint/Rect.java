package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class Rect extends Shape {
	private Point centre;
	private int len;
	private int width;
	
	public Rect() {}
	
	public Rect(Point centre, int len, int width) {
		this.centre = centre;
		this.len = len;
		this.width = width;

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

	public void setLength(int len) {
		this.len = len;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return len;
	}
	
	/**
	 * Rectangle knows how to draw itself.
	 * @param g GraphicsContext
	 */
	@Override
	public void execute(GraphicsContext g) {
		// TO DO
		int x = this.getCentre().getX();
		int y = this.getCentre().getY();
		int width = this.getWidth();
		int len = this.getLength();
		
		g.setLineWidth(this.getStrokeThickness());
		g.setStroke(this.getColor());
		g.setFill(this.getColor());

		if (len > 0 && width > 0 ) {
			if (this.isFilled() == false) {
				g.strokeRect(x - (width) ,y - (len),width,len);
			}else {
				g.fillRect(x - (width) ,y - (len),width,len);
			}
		}else if (len > 0 && width < 0) {
			if (this.isFilled() == false) {
				g.strokeRect(x, y - len, -width, len);
			} else {
				g.fillRect(x, y - len, -width, len);
			}
		}else if (len < 0 && width > 0){
			if (this.isFilled() == false) {
				g.strokeRect(x- width ,y ,width,-len);
			}else {
				g.fillRect(x- (width) ,y ,width,-len);
			}
		}else if (len < 0 && width < 0){
			if (this.isFilled() == false) {
				g.strokeRect(x , y , -width, -len);
			}else {
				g.fillRect(x , y , -width, -len);
			}
		}
	}
}
