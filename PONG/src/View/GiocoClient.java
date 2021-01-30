package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
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
	private JButton lascia;
	private Font font;

	public GiocoClient(Slider clientPlayer) {
		this.setLayout(null);
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
		pallina=new Ball(100, 100, 4, 4, 20, 1264, 681);
		lascia=new JButton("lascia");
		this.clientPlayer=clientPlayer;
		flag=false;
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.clearRect(0, 0, this.getSize().width, this.getSize().height);
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
		
        if(serverPlayer!=null) {
			g.fillRect(serverPlayer.getPosX(), serverPlayer.getPosY(), serverPlayer.getLunghezza(), serverPlayer.getAltezza());
		}
		g.fillRect(clientPlayer.getPosX(), clientPlayer.getPosY(), clientPlayer.getLunghezza(), clientPlayer.getAltezza());
		g.fillOval(pallina.getX(), pallina.getY(), pallina.getRadius(), pallina.getRadius());
		setBackground(Color.black);
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
		lascia.setBounds(this.getSize().width/2,20,60,30);
		this.add(lascia);
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
