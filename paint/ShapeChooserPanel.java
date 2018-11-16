package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {
	
	//Loading the Images
	Image circle = new Image("file:icons/circle.png");
	Image rectangle = new Image("file:icons/rectangle.png");
	Image square = new Image("file:icons/square.png");
	Image squiggle = new Image("file:icons/squiggle.png");
	Image polyline = new Image("file:icons/polyline.png");
			
	//Creating ImageViews
	ImageView Circle = new ImageView(circle);
	ImageView Rectangle = new ImageView(rectangle);
	ImageView Square = new ImageView(square);
	ImageView Squiggle = new ImageView(squiggle);
	ImageView Polyline = new ImageView(polyline);

	private View view; // So we can talk to our parent or other components of the view
	private String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline"};
	private ImageView[] buttonIcons = { Circle, Rectangle, Square, Squiggle, Polyline };
	private ToggleGroup group = new ToggleGroup();
	private static TextField thickness_text = new TextField("1");
	final Text line_text = new Text("Line Thickness : ");
	
	//Creating Color Chooser
	private static ColorPicker colorPicker = new ColorPicker();
	

//			colorPicker.setOnAction(new EventHandler()  {
//	            public void handle(Event t) {
//	            	view.setStroke(colorPicker);
//	            }
//	        });

	public ShapeChooserPanel(View view) {

		this.view = view;
		
		//Creating Color Picker
		this.add(colorPicker, 0, 6);
		
		//Creating Thickness Chooser
		thickness_text.setMaxSize(50, USE_COMPUTED_SIZE);
		this.add(line_text, 0, 5);
		this.add(thickness_text, 1, 5);
        
		//Creating Icons
		int row = 0;
		for (int i = 0 ; i < buttonLabels.length; i++) {
			ToggleButton button = new ToggleButton();
			button.setId(buttonLabels[i]);
			button.setToggleGroup(group);
			button.setMinWidth(50);
			button.setGraphic(buttonIcons[i]);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
			
		}
	}
	
	public static int getThickness() {
		return Integer.valueOf(thickness_text.getText());
	}
	
	public static Color getColor() {
		return colorPicker.getValue();
	}
	
	@Override
	public void handle(ActionEvent event) {
		String command = ((ToggleButton) event.getSource()).getId();
		this.view.getPaintPanel().setMode(command);
		System.out.println(command);
	}
}
