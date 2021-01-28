package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.f.getcW().getBtnConnetti().addActionListener(this);
		this.f.getcW().getRdbtnAuto().addActionListener(this);
		this.f.getcW().getRdbtnManual().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == f.getcW().getBtnConnetti()) {

			if(!f.getcW().getTextFieldNickName().getText().isBlank()) {
				if(!f.getcW().getTextFieldIPServer().getText().isBlank()) {
					if(!f.getcW().getRdbtnManual().isSelected() ) {
						Client c=new Client(f,f.getcW().getTextFieldNickName().getText(),f.getcW().getTextFieldIPServer().getText());
						Thread t=new Thread(c);
						t.start();
					}
					else {
						if(!f.getcW().getTextFieldPortaServer().getText().isBlank()) {
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
				}
				else {
					JOptionPane.showMessageDialog(null, "nessun server inserito","inserire server",JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "nessun nome inserito","inserire nome",JOptionPane.ERROR_MESSAGE);
			}
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
	
}
