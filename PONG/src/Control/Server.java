package Control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import javax.swing.JOptionPane;

import Model.Slider;
import View.ClientWait;
import View.Finestra;
import View.Gioco;
import View.ServerWait;

public class Server implements Runnable{
	
	private ServerSocket serverSocket;
	private Socket socket;
	private Finestra f;
	private String userName;
	private int porta=9999;
	private boolean flag=true;
	
	public Server(Finestra f,String userName) {
		super();
		this.f=f;
		this.userName=userName;
		porta=9999;
		flag=true;
	}

	public Server(Finestra f,String userName,int porta) {
		super();
		this.f=f;
		this.userName=userName;
		this.porta=porta;
		flag=true;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			if(flag) {
			f.getsW().getLblNewLabel_1().setVisible(true);
			f.getsW().revalidate();
			serverSocket = new ServerSocket(porta);
			System.out.println("connesso");
			Gioco g=new Gioco(new Slider(userName, 30, 0));
				socket=serverSocket.accept();
				ObjectInputStream streamClient= new ObjectInputStream(socket.getInputStream());
				Slider s=(Slider) streamClient.readObject();
				ObjectOutputStream streamBall= new ObjectOutputStream(socket.getOutputStream());				
				streamBall.writeObject(g.getPallina());
				streamBall.writeObject(g.getserverPlayer());
				f.changePanel(g);
				f.getG().setClientPlayer(s);
				new Thread(g).start();

				if(socket.isConnected()) {
					while(flag) {
						streamBall= new ObjectOutputStream(socket.getOutputStream());
						streamBall.writeObject(f.getG().getPallina());
						streamBall.writeObject(f.getG().getserverPlayer());
						streamClient= new ObjectInputStream(socket.getInputStream());
						s=(Slider) streamClient.readObject();
						f.getG().setClientPlayer(s);
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				
				
				socket.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			flag=false;
			System.out.println("cleint non raggiungibile");
			JOptionPane.showMessageDialog(null, "client non raggiungibile","errore client",JOptionPane.ERROR_MESSAGE);
			ServerWait sW=new ServerWait();
			f.changePanel(sW);
			new ControllerServerWait(f);
			if(f.getG()!=null) {
				System.out.println("gioco finiro");
				f.getG().setFlag(true);
			}
			try {
				serverSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			flag=false;
			JOptionPane.showMessageDialog(null, "errore ricezione dati","errore client",JOptionPane.ERROR_MESSAGE);
			ServerWait sW=new ServerWait();
			f.changePanel(sW);
			new ControllerServerWait(f);
			if(f.getG()!=null) {
				f.getG().setFlag(true);
			}
			try {
				serverSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		System.out.println("esco");

	}
}
