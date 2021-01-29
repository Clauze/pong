package Control;

import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import View.Finestra;
import View.ServerWait;

public class ControllerServerWait implements ActionListener{
	
	public Finestra f;

	public ControllerServerWait(Finestra f) {
		super();
		this.f = f;
		this.f.changePanel(new ServerWait());
		this.f.getsW().getIp().setText(getMyIp());
		this.f.getsW().getBtnConnetti().addActionListener(this);
		this.f.getsW().getBtnIndietro().addActionListener(this);
		this.f.getsW().getRdbtnAuto().addActionListener(this);
		this.f.getsW().getRdbtnManual().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == f.getsW().getBtnConnetti()) {
			System.out.println("ciao connetti");
			if(!f.getsW().getTextFieldNickName().getText().isBlank()) {
				System.out.println("icoa");
				if(!f.getsW().getRdbtnManual().isSelected() ) {
					System.out.println("creo server");
					Server c=new Server(f,f.getsW().getTextFieldNickName().getText());
					Thread t=new Thread(c);
					t.start();
				}
				else {
					if(!f.getsW().getTextFieldPorta().getText().isBlank()) {
						int port;
						try {
							port=Integer.parseInt(f.getsW().getTextFieldPorta().getText());
						}
						catch (Exception e1) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, "formato porta non corretto, impostata porta di default 9999","errore porta",JOptionPane.ERROR_MESSAGE);
							port=9999;
						}
						Server c=new Server(f,f.getsW().getTextFieldNickName().getText(),port);
						Thread t=new Thread(c);
						t.start();
					}
					else {
						int port=9999;
						JOptionPane.showMessageDialog(null, "nessuna porta inserita, impostata porta di default 9999","errore porta",JOptionPane.ERROR_MESSAGE);
						Server c=new Server(f,f.getsW().getTextFieldNickName().getText(),port);
						Thread t=new Thread(c);
						t.start();
					}
				}
				f.getsW().getBtnConnetti().setEnabled(false);
				f.getsW().getRdbtnManual().setEnabled(false);
				f.getsW().getRdbtnAuto().setEnabled(false);
				f.getsW().getTextFieldNickName().setEnabled(false);
				f.getsW().getTextFieldPorta().setEnabled(false);
				f.getsW().getBtnIndietro().setEnabled(false);
			}
			else {
				JOptionPane.showMessageDialog(null, "nessun nome inserito","inserire nome",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource() == f.getsW().getBtnIndietro()) {
			f.changePanel();
		}
		
		else if(e .getSource() == f.getsW().getRdbtnManual()) {
			f.getsW().getTextFieldPorta().setVisible(true);
			f.getsW().getLblNewLabel_2().setVisible(true);
			f.getsW().revalidate();
		}
		else if(e .getSource() == f.getsW().getRdbtnAuto()) {
			f.getsW().getTextFieldPorta().setVisible(false);
			f.getsW().getLblNewLabel_2().setVisible(false);
			f.getsW().revalidate();
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
	
}
