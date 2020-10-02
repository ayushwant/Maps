package guimodule;
import processing.core.*;


public class sun extends PApplet{
	PImage img;
	public void setup()
	{
		
		
		size(400,400);  //set canvas size
		background(255);  //set canvas color
		stroke(0);  //set pen color
		//img = loadImage("https://upload.wikimedia.org/wikipedia/commons/6/64/Gorakhpur_Junction_railway_station.jpg","jpg");
		img = loadImage("C:\\Users\\ayush\\Downloads\\picnic-spot.jpg", "jpg");
		img.resize(0, height); //resize loaded img to full height of canvas
		image(img,0,0);   //display image
	}
	
	public void draw() //gets called over and over
	{
		
		float x = (PI*second())/30;
		//float a = width/4;
		float a = 100;
		//line(width/4, height/5, a*cos(x), a*sin(x) );
		//System.out.print(a*cos(x));
		
		line(100, 80, a*cos(x)+100, a*sin(x)+80 );
		
		int[] color = sunColorSec(second()); //calc color code
		fill(color[0], color[1], color[2]);
		
		//ellipse(width/4,height/5, width/4, height/4 ); //draw sun
		ellipse(100,80, 80, 80 );
		//circle(width/4, height/5, width/4);
	}
	


	public int[] sunColorSec(float seconds)
	{
		int[] rgb = new int[3];
		 
		float d = Math.abs(30-seconds); // d=diffFrom30
		//absolute value
		
		float ratio = d/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
		
	}
	
//	public static void main(String[] args)

}
