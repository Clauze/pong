package Control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import Model.Ball;
import Model.Slider;
import View.ClientWait;
import View.Finestra;
import View.GiocoClient;

public class Client implements Runnable{

	private Socket socket;
	private Finestra f;
	private String ipServer;
	private String userName;
	private GiocoClient gC=null;
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
		f.getcW().getLblNewLabel_3().setVisible(true);
		f.getcW().revalidate();
		gC=new GiocoClient(new Slider(userName, (int)(f.getSize().getWidth() - 79), 0));
		try {
			socket= new Socket(ipServer, porta);
			ObjectOutputStream streamBall= new ObjectOutputStream(socket.getOutputStream());
			streamBall.writeObject(gC.getClientPlayer());
			ObjectInputStream streamPallina= new ObjectInputStream(socket.getInputStream());
			Ball pallina= (Ball) streamPallina.readObject();
			Slider s=(Slider) streamPallina.readObject();
			f.changePanel(gC);
			f.getgC().setServerPlayer(s);
			f.getgC().setPallina(pallina);
			new Thread(gC).start();
			
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
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "server non raggiungibile","errore server",JOptionPane.ERROR_MESSAGE);
			ClientWait cW=new ClientWait();
			f.changePanel(cW);
			new ControllerClient(f);
			if(f.getgC()!=null) {
				f.getgC().setFlag(true);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "errore ricezione dati","errore server",JOptionPane.ERROR_MESSAGE);
			ClientWait cW=new ClientWait();
			f.changePanel(cW);
			new ControllerClient(f);
			if(f.getgC()!=null) {
				f.getG().setFlag(true);
			}
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
		}
	}

}
