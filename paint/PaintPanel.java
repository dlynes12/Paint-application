	package ca.utoronto.utm.paint;
	
	import javafx.event.EventHandler;
	import javafx.scene.canvas.Canvas;
	import javafx.scene.canvas.GraphicsContext;
	import javafx.scene.input.MouseEvent;
	import javafx.scene.layout.StackPane;
	import javafx.scene.paint.Color;

import java.util.ArrayList;
	import java.util.Observable;
	import java.util.Observer;
	
	import ca.utoronto.utm.paint.ShapeChooserPanel;
	
	class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {
	
		private int i = 0;
		private PaintModel model; // slight departure from MVC, because of the way painting works
		private View view; // So we can talk to our parent or other components of the view
	
		private String mode; // modifies how we interpret input (could be better?)

		private Shape shape; // B9
		private ShapeFactory shapeFactory = new ShapeFactory(); // B9 Where to place?
		
		private boolean fill;
	
		private Canvas canvas;

		public PaintPanel(PaintModel model, View view) {
	
			this.canvas = new Canvas(600, 600);
			this.getChildren().add(this.canvas);
			// The canvas is transparent, so the background color of the
			// containing pane serves as the background color of the canvas.
			this.setStyle("-fx-background-color: white");
	
			this.addEventHandler(MouseEvent.ANY, this);
	
			//this.mode = "Circle"; // bad code here?
	
			this.model = model;
			this.model.addObserver(this);
	
			this.view = view;
		}

		public void repaint() {
	
			GraphicsContext g = this.canvas.getGraphicsContext2D();
	
			// Clear the canvas
			g.clearRect(0, 0, this.getWidth(), this.getHeight());
			g.strokeText("i=" + i, -50, -75);
			i = i + 1;
	
			// ============================== DRAW SHAPES ==============================
			
			// Execute all DrawingCommands in PaintModel's commandStack.
			this.model.getPaintCommandStack().operateAll(g);
		}
	
		@Override
		public void update(Observable o, Object arg) {
	
			// Not exactly how MVC works, but similar.
			this.repaint();
		}
	
		/**
		 * Controller aspect of this
		 */
		public void setMode(String mode) {
			this.mode = mode;
		}
		
		public void setFill(boolean fill) {
			this.fill = fill;
		}
		
		public boolean getFill() { //For future use if necessary
			return fill;
		}
		
		// ============================== UNDO/REDO ==============================
		public void undo() {
			this.model.undo();
		}
		
		public void redo() {
			this.model.redo();
		}
		@Override
		public void handle(MouseEvent event) {
	
			if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				mouseDragged(event);
			} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				mousePressed(event);
			} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
				mouseMoved(event);
			} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				mouseClicked(event);
			} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				mouseReleased(event);
			} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				mouseEntered(event);
			} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				mouseExited(event);
			}
		}
	
		private void mouseMoved(MouseEvent e) {
			if (this.mode == "Squiggle") {
	
			} else if (this.mode == "Circle") {
	
			} else if (this.mode == "Polyline") { // ~J
				if (this.shape != null && this.shape instanceof Polyline) {
					Polyline poly = (Polyline) this.shape; // CAST
					
					Point cursor = new Point((int) e.getX(), (int) e.getY());
					poly.getPoints().set(poly.getNumberOfPoints()-1, cursor);
					this.model.notifyChange();
				}
			}
		}
	
		private void mouseDragged(MouseEvent e) {
			if (this.mode == "Squiggle") {
				Squiggle squiggle = (Squiggle) this.shape; // CAST
				squiggle.addPoint(new Point((int) e.getX(), (int) e.getY()));
				
				this.model.notifyChange();
				
			} else if (this.mode == "Circle") { // ~ j
				Circle circle = (Circle) this.shape; // CAST
				
				int radius = (int) Math.sqrt(Math.pow(circle.getCentre().getX() - e.getX(), 2) + Math.pow(circle.getCentre().getY() - e.getY(), 2));				
				circle.setRadius(radius);
				
				this.model.notifyChange();
				
			}else if (this.mode == "Rectangle") {	
				Rect rectangle = (Rect) this.shape; // CAST
				
				int width = (int) (rectangle.getCentre().getX() - e.getX()); // calculate the distance from the first 
				int length = (int) ((rectangle.getCentre().getY()) - e.getY()); // click to when you release to get length and width
				rectangle.setLength(length);
				rectangle.setWidth(width);

				this.model.notifyChange();
				
			}else if (this.mode == "Square") {	
				Square square = (Square) this.shape; // CAST
				int width = (int) (square.getCentre().getX() - e.getX());
				int length = (int) (square.getCentre().getY() - e.getY());
				square.setLength(length);
				square.setWidth(width);
				square.setSides(length);
				square.setDirection(width, length);

				this.model.notifyChange();
			}
		}
	
		private void mouseClicked(MouseEvent e) {
			if (this.mode == "Squiggle") {
	
			} else if (this.mode == "Circle") {
	
			} else if (this.mode == "Polyline") { // ~j
				Polyline polyline = (Polyline) this.shape;
				
				if (e.getClickCount() == 2) {
				    // System.out.println(e.getClickCount()); // for debugging
					// Double-click to set last point and end a polyline
					this.shape = null;
				} else {
					// Click to add new Point to existing Polyline
					if (this.shape == null) {
						this.shape = shapeFactory.createShape(this.mode);
						polyline = (Polyline) this.shape; // CAST
						
						Point first = new Point((int) e.getX(), (int) e.getY());
						polyline.addPoint(first);
						polyline.addPoint(first); // Added twice so Polyline has at least 2 points.
						
						polyline.setColor(ShapeChooserPanel.getColor());
						polyline.setStrokeThickness(ShapeChooserPanel.getThickness());

						// this.model.addShape(polyline);
						this.model.addCommand(new DrawPolyline(polyline));
						
					} else {
						Point newPolyPoint = new Point((int) e.getX(), (int) e.getY());
						polyline.addPoint(newPolyPoint);
						
						this.model.notifyChange();
					}
				}
			}
		}
	
		private void mousePressed(MouseEvent e) {
			
			// Not good to have this here because PaintPanel starts with no mode selected
			// this.shape = shapeFactory.createShape(this.mode); // B9
			
			if (this.mode == "Squiggle") { // ~j
				this.shape = shapeFactory.createShape(mode); // Create and CAST
				Squiggle squiggle = (Squiggle) this.shape;

				squiggle.addPoint(new Point((int) e.getX(), (int) e.getY()));
				
				squiggle.setColor(ShapeChooserPanel.getColor());
				squiggle.setStrokeThickness(ShapeChooserPanel.getThickness());
				
				// this.model.addShape(squiggle);
				this.model.addCommand(new DrawSquiggle(squiggle));
	
			} else if (this.mode == "Circle") {
				this.shape = shapeFactory.createShape(mode); // Create and CAST
				Circle circle = (Circle) this.shape;
				
				// Problematic notion of radius and centre!!
				Point centre = new Point((int) e.getX(), (int) e.getY());
				int radius = 0;
				
				circle.setCentre(centre); // ADDED
				circle.setRadius(radius);
				
				circle.setColor(ShapeChooserPanel.getColor());
				circle.setStrokeThickness(ShapeChooserPanel.getThickness());

				circle.setFilled(this.getFill());

				// this.model.addShape(circle);
				// this.model.getPaintCommandStack().pushCommand(shape);
				this.model.addCommand(new DrawCircle(circle));
				
			} else if (this.mode == "Rectangle") {
				this.shape = shapeFactory.createShape(mode); // Create and CAST
				Rect rectangle = (Rect) this.shape;
				
				rectangle.setCentre(new Point((int) e.getX(), (int) e.getY())); // ADDED
				rectangle.setWidth(0);
				rectangle.setLength(0);

				rectangle.setColor(ShapeChooserPanel.getColor());
				rectangle.setStrokeThickness(ShapeChooserPanel.getThickness());
				
				rectangle.setFilled(this.getFill()); 
				
				// this.model.addShape(rectangle);
				//this.model.getPaintCommandStack().pushCommand(shape);
				this.model.addCommand(new DrawRectangle(rectangle));
				
			} else if (this.mode == "Square") {
				this.shape = shapeFactory.createShape(mode); // Create and CAST
				Square square = (Square) this.shape;
				
				square.setCentre(new Point((int) e.getX(), (int) e.getY())); // ADDED
				square.setWidth(0);
				square.setLength(0);
				
				square.setColor(ShapeChooserPanel.getColor());
				square.setStrokeThickness(ShapeChooserPanel.getThickness());
				
				square.setFilled(this.getFill());
				
				//this.model.getPaintCommandStack().pushCommand(shape);
				// this.model.addShape(square);
				this.model.addCommand(new DrawSquare(square));
			}
		}
	
		private void mouseReleased(MouseEvent e) {
			if (this.mode == "Squiggle") {
				this.shape = null;
					
			} else if (this.mode == "Circle") {
				if (this.shape != null) {
					Circle circle = (Circle) this.shape; // CAST
					int radius = (int) Math.sqrt(Math.pow(circle.getCentre().getX() - e.getX(), 2) + Math.pow(circle.getCentre().getY() - e.getY(), 2));				
					circle.setRadius(radius);
					
					this.shape = null;
				}
				
			} else if (this.mode == "Rectangle") {
				if (this.shape != null) {
					Rect rectangle = (Rect) this.shape;
					
					int width = (int) (rectangle.getCentre().getX() - e.getX()); // calculate the distance from the first 
					int length = (int) ((rectangle.getCentre().getY()) - e.getY()); // click to when you release to get length and width
					rectangle.setLength(length);
					rectangle.setWidth(width);

					this.shape = null;
				}
				
			} else if (this.mode == "Square") {
				if (this.shape != null) {
					Square square = (Square) this.shape;
					
					int sides = (int) (square.getCentre().getX() - e.getX());
					square.setLength(sides);
					square.setWidth(sides);
					
					this.shape = null;
				}		
			}
		}
	
		private void mouseEntered(MouseEvent e) {
			if (this.mode == "Squiggle") {
	
			} else if (this.mode == "Circle") {
	
			}
		}
	
		private void mouseExited(MouseEvent e) {
			if (this.mode == "Squiggle") {
	
			} else if (this.mode == "Circle") {
	
			}
		}
	}
