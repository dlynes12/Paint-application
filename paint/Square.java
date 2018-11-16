package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Shape {
    private Point centre;
    private int direction;
    private int sides;
    private int length;
    private int width;



    public Square() {}

    public void Square(Point centre, int sides) {
        this.centre = centre;
        this.sides = sides;

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

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    public void setWidth(int width) { this.width = width; }

    public int getWidth() { return width; }

    public void setLength(int length) { this.length = length; }

    public int getLength() { return length;}

    public int getDirection() { return this.direction;}


    public void setDirection(int x, int y) {
        if (x > 0 && y > 0 ) {
            this.direction = 1;
        }else if (x > 0 && y < 0) {
            this.direction = 2;
        }else if (x < 0 && y > 0){
            this.direction = 3;
        }else if (x < 0 && y < 0){
            this.direction = 4;
        }

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
        int sides = this.getSides();
        int width = this.getWidth();
        int length = this. getLength();
        direction = getDirection();

        g.setLineWidth(this.getStrokeThickness());
        g.setStroke(this.getColor());
        g.setFill(this.getColor());
        System.out.println(this.direction);

        if (this.direction == 1) {
            if (this.isFilled() == false) {
                g.strokeRect(x - (sides) ,y - (sides),sides,sides);
                System.out.println(this.direction);

            }else {
                g.fillRect(x - (sides) ,y - (sides),sides,sides);
            }
        }else if (this.direction == 2) {

            if (this.isFilled() == false) {
                g.strokeRect(x + sides, y, -sides, -sides);
                System.out.println(this.direction);

            } else {
                g.fillRect(x + sides, y, -sides, -sides);
            }
        }else if (this.direction == 3) {
            if (this.isFilled() == false) {
                g.strokeRect(x  ,y - sides  ,sides, sides);
                System.out.println(sides);

            }else {
                g.fillRect(x  ,y - sides  ,sides, sides);
            }
        }else if (this.direction == 4) {
            if (this.isFilled() == false) {
                g.strokeRect(x , y , -sides, -sides);
                System.out.println(this.direction);

            }else {
                g.fillRect(x , y , -sides, -sides);
            }
        }
    }
}
