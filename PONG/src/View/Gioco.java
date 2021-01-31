package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
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
	private boolean flag=false;
	private BufferedImage image;
	private Font font;

	public Gioco(Slider serverPlayer) {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("bin\\PixelMplus10-Regular.ttf")).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setLayout(null);
		this.setBackground(Color.orange);
		pallina=new Ball(100, 100, 4, 4, 20, 1264, 681);
		this.serverPlayer=serverPlayer;
		flag=false;
		try {
			image=ImageIO.read(getClass().getResource("/immagini/sPong.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.clearRect(this.getSize().width/2, 0, this.getSize().width, this.getSize().height);
		g.setColor(Color.white);
		//g.drawImage(image,0,0,this.getSize().width,this.getSize().height,this);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.draw(new Line2D.Float(this.getSize().width/2, 0, this.getSize().width/2, this.getSize().height));
        g.setFont(font);
        g.drawString(serverPlayer.getName(), (this.getSize().width/2)-200, this.getSize().height-50);
        g.drawString(clientPlayer.getName(), (this.getSize().width/2)+100, this.getSize().height-50);
        g.drawString(pallina.getServerPoints(), (this.getSize().width/2)-150, 30);
        g.drawString(pallina.getClientPoints(), (this.getSize().width/2)+100, 30);
		g.fillRect(serverPlayer.getPosX(), serverPlayer.getPosY(), serverPlayer.getLunghezza(), serverPlayer.getAltezza());
		if(clientPlayer!=null) {
			g.fillRect(clientPlayer.getPosX(), clientPlayer.getPosY(), clientPlayer.getLunghezza(), clientPlayer.getAltezza());
		}
		g.fillOval(pallina.getX(), pallina.getY(), pallina.getRadius(), pallina.getRadius());
		setBackground(Color.black);

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
		
		while(!flag)	{
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
			if(pallina.getClientPoints().compareTo("5") == 0 || pallina.getServerPoints().compareTo("5") == 0) {
				flag=true;
			}
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

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
	
}

