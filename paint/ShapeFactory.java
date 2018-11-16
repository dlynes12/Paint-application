package ca.utoronto.utm.paint;
/**
 * A factory that creates and returns a concrete shape 
 * given the specific Shape subclass.
 * @author ellez
 *
 */
public class ShapeFactory {
	
	public Shape createShape(String shape) {
		
		// Easy Instantiation
		if (shape.equals("Circle"))
			return new Circle();
		if (shape.equals("Rectangle"))
			return new Rect();
		if (shape.equals("Square"))
			return new Square();
		if (shape.equals("Squiggle"))
			return new Squiggle();
		if (shape.equals("Polyline"))
			return new Polyline();
		return null;
	} 
}
