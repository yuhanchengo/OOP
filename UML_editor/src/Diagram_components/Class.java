package Diagram_components;
import java.awt.Color;
import java.awt.Graphics;
/*
 * The Class class. An Rectangle object in the UML editor
 */

public class Class extends Basic_object{
protected static int object_width = 120;
protected static int object_height = 170;
protected static int namePosRatio = 5; 
	public Class(int x, int y){
		super(x,y, object_width, object_height, namePosRatio);
	}
	
	@Override
	/*
	 * draw basic structure of Class objects
	 */
	public void draw(Graphics g) {
		// draw rectangle
		g.setColor(Color.WHITE);
		g.fillRect(x_cord, y_cord, object_width, object_height);
		g.setColor(Color.black);
		g.drawRect( x_cord, y_cord, object_width, object_height);
		this.drawAddsOn(g);
		for(Port p: this.ports){
			p.draw(g);
		}
	}
	/*
	 * draw two more lines for Class objects
	 */
	protected void drawAddsOn(Graphics g){
		g.drawLine(this.x_cord, (this.y_cord + object_height / 3), this.x_cord + object_width,
				(this.y_cord + object_height / 3));
		g.drawLine(this.x_cord, this.y_cord + object_height / 3 * 2, this.x_cord + object_width,
				this.y_cord + object_height / 3 * 2);
	}
	@Override
	public int compareTo(Object o) {
//		super.compareTo(o);
		return 0;
	}




	
	
}