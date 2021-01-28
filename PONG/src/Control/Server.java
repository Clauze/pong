package Control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Model.Slider;
import View.Finestra;
import View.Gioco;

public class Server implements Runnable{
	
	private ServerSocket serverSocket;
	private Socket socket;
	private Finestra f;
	
	public Server(Finestra f) {
		super();
		this.f=f;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			serverSocket = new ServerSocket(9999);
			while(true) {
				Gioco g=new Gioco(new Slider("marco", 30, 0));
				socket=serverSocket.accept();
				ObjectInputStream streamClient= new ObjectInputStream(socket.getInputStream());
				Slider s=(Slider) streamClient.readObject();
				System.out.println("ho letto");
				ObjectOutputStream streamBall= new ObjectOutputStream(socket.getOutputStream());				
				streamBall.writeObject(g.getPallina());
				streamBall.writeObject(g.getserverPlayer());
				f.changePanel(g);
				f.getG().setClientPlayer(s);
				new Thread(g).start();

				if(socket.isConnected()) {
					while(true) {
						streamBall= new ObjectOutputStream(socket.getOutputStream());
						streamBall.writeObject(f.getG().getPallina());
						streamBall.writeObject(f.getG().getserverPlayer());
						streamClient= new ObjectInputStream(socket.getInputStream());
						s=(Slider) streamClient.readObject();
						f.getG().setClientPlayer(s);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
				
				socket.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
