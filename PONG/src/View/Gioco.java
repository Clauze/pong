package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Control.Controller;
import Model.Ball;
import Model.Slider;

public class Gioco extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ball pallina;
	private Slider serverPlayer,clientPlayer=null;

	public Gioco(Slider serverPlayer) {
		this.setLayout(null);
		this.setBackground(Color.green);
		pallina=new Ball(100, 100, 4, 4, 20, 1264, 681);
		this.serverPlayer=serverPlayer;
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, this.getSize().width, this.getSize().height);
		//g.drawImage(img,0,0,this.getSize().width,this.getSize().height,this);
		g.setColor(Color.red);
		g.fillRect(serverPlayer.getPosX(), serverPlayer.getPosY(), serverPlayer.getLunghezza(), serverPlayer.getAltezza());
		if(clientPlayer!=null) {
			g.fillRect(clientPlayer.getPosX(), clientPlayer.getPosY(), clientPlayer.getLunghezza(), clientPlayer.getAltezza());
		}
		g.fillOval(pallina.getX(), pallina.getY(), pallina.getRadius(), pallina.getRadius());
		
	}

	public void setClientPlayer(Slider clientPlayer) {
		this.clientPlayer = clientPlayer;
	}

	public Ball getPallina() {
		return pallina;
	}
	
	public void setPallina(Ball pallina) {
		this.pallina = pallina;
	}

	public Slider getserverPlayer() {
		return serverPlayer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.serverPlayer.setD(new Dimension(this.getSize().width, this.getSize().height));
		try {
			System.out.println("localhost:"+InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true)	{
			pallina.move();
			collision();
			serverPlayer.move();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	
	public void collision() {
		if((pallina.getX()+pallina.getRadius()>= serverPlayer.getPosX() && pallina.getX() < serverPlayer.getPosX()+serverPlayer.getLunghezza())&&(pallina.getY()+pallina.getRadius()>= serverPlayer.getPosY() && pallina.getY() < serverPlayer.getPosY()+serverPlayer.getAltezza())){
			pallina.setXv(pallina.getXv()*-1);
			pallina.setX(serverPlayer.getPosX()+serverPlayer.getLunghezza());
		}
		else if((pallina.getX()+pallina.getRadius()>= clientPlayer.getPosX() && pallina.getX() < clientPlayer.getPosX()+clientPlayer.getLunghezza())&&(pallina.getY()+pallina.getRadius()>= clientPlayer.getPosY() && pallina.getY() < clientPlayer.getPosY()+clientPlayer.getAltezza())){
			pallina.setXv(pallina.getXv()*-1);
			pallina.setX(clientPlayer.getPosX()-pallina.getRadius());
		}
	}

}

