package Model;

import java.io.Serializable;

public class Ball implements Serializable{

	/**
	 * - Ball v1.0
	 */
	
	  ///////////////////
	 // - Variables - //
	///////////////////
	
	private int x;			 // - represents the x coordinate of the ball
	private int y;			 // - represents the y coordinate of the ball
	private double xv;      	 // - x velocity 
	private double yv;      	 // - y velocity
	private int radius;		 // - radius of the ball
	private int   HEIGHT;
	private int   WIDTH;
	private int clientPoints=0;
	private int serverPoints=0;
	
	public Ball(int x, int y, double xv, double yv, int radius, int WIDTH, int HEIGHT) {
		super();
		this.x = x;
		this.y = y;
		this.xv = xv;
		this.yv = yv;
		this.radius = radius;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
 

	public void move(){
		x += xv;
		y += yv;
		if(x > (WIDTH - radius)){ // - Calibrate the screen layer - //
			serverPoints+=1;
			x= (WIDTH-radius); // - set the position of the ball
			xv = xv * -1; // - set the velocity of the ball

		}
		
		else if(x < 0){  // - Calibrate the screen layer - //
			x = 0;
			clientPoints+=1;
			xv = xv *-1;
		}
		
		if(y < 0){ // - Calibrate the screen layer - //
			y = 0;
			yv = yv * -1;
		}
		
		else if(y > (HEIGHT - radius)) // - Calibrate the screen layer - //
		{
			y = (HEIGHT-radius); 
			yv = yv * -1;
			
		}
		

	}
	
	  ///////////////////////////
	 // - Getters & Setters - //
	///////////////////////////
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getXv() {
		return xv;
	}
	public void setXv(double xv) {
		this.xv = xv;
	}
	public double getYv() {
		return yv;
	}
	public void setYv(double yv) {
		this.yv = yv;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getClientPoints() {
		return String.valueOf(clientPoints);
	}

	public String getServerPoints() {
		return String.valueOf(serverPoints);
	}

	
}
