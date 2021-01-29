package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Model.Ball;
import Model.Slider;

public class GiocoClient extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ball pallina;
	private Slider serverPlayer,clientPlayer;
	private boolean flag=false;

	public GiocoClient(Slider clientPlayer) {
		this.setLayout(null);
		this.setBackground(Color.green);
		pallina=new Ball(100, 100, 4, 4, 20, 1264, 681);
		this.clientPlayer=clientPlayer;
		flag=false;
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, this.getSize().width, this.getSize().height);
		//g.drawImage(img,0,0,this.getSize().width,this.getSize().height,this);
		g.setColor(Color.red);
		if(serverPlayer!=null) {
			g.fillRect(serverPlayer.getPosX(), serverPlayer.getPosY(), serverPlayer.getLunghezza(), serverPlayer.getAltezza());
		}
		g.fillRect(clientPlayer.getPosX(), clientPlayer.getPosY(), clientPlayer.getLunghezza(), clientPlayer.getAltezza());
		g.fillOval(pallina.getX(), pallina.getY(), pallina.getRadius(), pallina.getRadius());
		
	}

	public Ball getPallina() {
		return pallina;
	}
	
	public void setPallina(Ball pallina) {
		this.pallina = pallina;
	}


	public Slider getClientPlayer() {
		return clientPlayer;
	}

	public void setServerPlayer(Slider serverPlayer) {
		this.serverPlayer = serverPlayer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.clientPlayer.setD(new Dimension(this.getSize().width, this.getSize().height));
		while(!flag)	{
			clientPlayer.move();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
