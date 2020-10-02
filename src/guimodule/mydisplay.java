package guimodule;

import processing.core.PApplet;

public class mydisplay extends PApplet {
	public void setup()
	{
		size(400,400);
		background(200,200,200);
		
	}
	public void draw()
	{
		fill(255,255,0);
		ellipse(200,200,300,300);
		fill(0,0,0);
		ellipse(120,150,50,70);
		ellipse(280,150,50,70);
		noFill();
		arc(200,250,75,75,0,PI);
		
		
	}

}
