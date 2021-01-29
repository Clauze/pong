package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import View.ClientWait;
import View.Finestra;
import View.ServerWait;

public class ControllerClient implements ActionListener{
	
	public Finestra f;

	public ControllerClient(Finestra f) {
		super();
		this.f = f;
		this.f.changePanel(new ClientWait());
		try {
			this.f.getcW().getIp().setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.f.getcW().getBtnConnetti().addActionListener(this);
		this.f.getcW().getBtnIndietro().addActionListener(this);
		this.f.getcW().getRdbtnAuto().addActionListener(this);
		this.f.getcW().getRdbtnManual().addActionListener(this);
		this.f.getcW().getIp().setText(getMyIp());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == f.getcW().getBtnConnetti()) {

			if(!f.getcW().getTextFieldNickName().getText().isBlank()) {

				if(!f.getcW().getTextFieldIPServer().getText().isBlank() && checkIPAddress(f.getcW().getTextFieldIPServer().getText())) {
					if(!f.getcW().getRdbtnManual().isSelected() ) {
						Client c=new Client(f,f.getcW().getTextFieldNickName().getText(),f.getcW().getTextFieldIPServer().getText());
						Thread t=new Thread(c);
						t.start();
					}
					else {
						if(!f.getcW().getTextFieldPortaServer().getText().isBlank() && checkPort(f.getcW().getTextFieldPortaServer().getText())) {
							int port=0;
							try {
								port=Integer.parseInt(f.getcW().getTextFieldPortaServer().getText());
							}
							catch (Exception e1) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(null, "formato porta non corretto, impostata porta di default 9999","errore porta",JOptionPane.ERROR_MESSAGE);
								port=9999;
							}
							Client c=new Client(f,f.getcW().getTextFieldNickName().getText(),f.getcW().getTextFieldIPServer().getText(),port);
							Thread t=new Thread(c);
							t.start();
						}
						else {
							int port=9999;
							JOptionPane.showMessageDialog(null, "nessuna porta inserita, impostata porta di default 9999","errore porta",JOptionPane.ERROR_MESSAGE);
							Client c=new Client(f,f.getcW().getTextFieldNickName().getText(),f.getcW().getTextFieldIPServer().getText(),port);
							Thread t=new Thread(c);
							t.start();
						}
					}
					f.getcW().getBtnConnetti().setEnabled(false);
					f.getcW().getRdbtnManual().setEnabled(false);
					f.getcW().getRdbtnAuto().setEnabled(false);
					f.getcW().getTextFieldNickName().setEnabled(false);
					f.getcW().getTextFieldPortaServer().setEnabled(false);
					f.getcW().getBtnIndietro().setEnabled(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "nessun server inserito o indirizzo ip non corretto","inserire server",JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "nessun nome inserito","inserire nome",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == f.getcW().getBtnIndietro()) {
			f.changePanel();
		}

		else if(e .getSource() == f.getcW().getRdbtnManual()) {
			f.getcW().getTextFieldPortaServer().setVisible(true);
			f.getcW().getLblNewLabel_2().setVisible(true);
			f.getcW().revalidate();
		}
		else if(e .getSource() == f.getcW().getRdbtnAuto()) {
			f.getcW().getTextFieldPortaServer().setVisible(false);
			f.getcW().getLblNewLabel_2().setVisible(false);
			f.getcW().revalidate();
		}
	}
	
	private String getMyIp() {
		String s=null;

		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("google.com", 80));
			s= socket.getLocalAddress().toString();
			s=s.substring(1);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				s=InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return s;

	}
	
	private boolean checkPort(String str) {  
		  Pattern pPattern = Pattern.compile("\\d{1,4}");  
		  return pPattern.matcher(str).matches();  
	}  
	
	 // - Check IP address type- //
	private boolean checkIPAddress(String str) { 
		if(str.compareTo("localhost")==0) {
			return true;
		}
		else {
		  Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");  
		  return ipPattern.matcher(str).matches();  
		}
	}
}
