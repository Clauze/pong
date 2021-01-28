package Model;

import java.awt.Dimension;
import java.io.Serializable;

public class Slider implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int posX;
	private int posY;
	private int velX;
	private int velY;
	private int lunghezza;
	private int altezza;
	private Dimension d;

	public Slider(String name,int posX,int posY) {
		this.name=name;
		this.posX = posX;
		this.posY = posY;
		this.velX = 0;
		this.velY = 0;
		this.lunghezza = 30;
		this.altezza = 120;
	}

	public Dimension getD() {
		return d;
	}

	public void setD(Dimension d) {
		this.d = d;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosY(int velY) {
		this.posY += velY;
	}

	public int getPosY() {
		return posY;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getVelY() {
		return velY;
	}	

	public int getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(int lunghezza) {
		this.lunghezza = lunghezza;
	}

	public int getAltezza() {
		return altezza;
	}

	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}
	
	public void move() {
		this.setPosY(velY);
		if(this.posY < 0) {
			this.posY=0;
		}
		else if(this.posY > d.height - this.altezza) {
			this.posY= d.height - this.altezza;
		}
	}
}
