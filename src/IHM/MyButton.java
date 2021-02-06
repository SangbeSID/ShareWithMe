package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;



import javax.swing.JButton;


public class MyButton extends JButton {
	private Shape shape;	
	
	public MyButton(String text) {
		super(text);
		this.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.setBackground(new Color(100, 149, 237));
		this.setForeground(Color.WHITE);
		this.setContentAreaFilled(false);
		//this.initForme(shape);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		 // if the button is pressed and ready to be released
	      if (getModel().isArmed()) {
	         g.setColor(Color.lightGray);
	      } else {
	         g.setColor(getBackground());
	      }
	  
	      g.fillOval(0, 0, getSize().width-1, getSize().height-1);
	  
	      super.paintComponent(g);
	}
	

	// paint a round border as opposed to a rectangular one
	public void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, 0, getSize().width-1, getSize().height-1);
	}

	// only clicks within the round shape should be accepted
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
}
