package Control;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Model.Ball;
import Model.Slider;
import View.Finestra;
import View.Gioco;
import View.GiocoClient;

public class Client implements Runnable{

	private Socket socket;
	private Finestra f;
	private String ipServer;
	private String userName;
	private int porta;
	
	public Client(Finestra f, String userName, String ipServer) {
		this.f=f;
		this.ipServer=ipServer;
		this.userName=userName;
		porta=9999;
	}
	
	public Client(Finestra f, String userName, String ipServer,int porta) {
		this.f=f;
		this.userName=userName;
		this.ipServer=ipServer;
		this.porta=porta;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			GiocoClient g=new GiocoClient(new Slider(userName, (int)(f.getSize().getWidth() - 79), 0));
			socket= new Socket(ipServer, porta);
			ObjectOutputStream streamBall= new ObjectOutputStream(socket.getOutputStream());
			streamBall.writeObject(g.getClientPlayer());
			System.out.println("ho spedito");
			ObjectInputStream streamPallina= new ObjectInputStream(socket.getInputStream());
			Ball pallina= (Ball) streamPallina.readObject();
			Slider s=(Slider) streamPallina.readObject();
			f.changePanel(g);
			f.getgC().setServerPlayer(s);
			f.getgC().setPallina(pallina);
			new Thread(g).start();
			
			if(socket.isConnected()) {
				while(true) {
					
					streamPallina= new ObjectInputStream(socket.getInputStream());
					pallina= (Ball) streamPallina.readObject();
					s=(Slider) streamPallina.readObject();
					f.getgC().setServerPlayer(s);
					f.getgC().setPallina(pallina);
					streamBall= new ObjectOutputStream(socket.getOutputStream());
					streamBall.writeObject(f.getgC().getClientPlayer());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
