package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import View.Finestra;
import View.Gioco;

public class Controller implements ActionListener,KeyListener{
	
	private Finestra frame;
	
	public Controller(Finestra frame) {
		super();
		this.frame = frame;
		frame.addKeyListener(this);
		frame.getBtnCrea().addActionListener(this);
		frame.getBtnUnisciti().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==frame.getBtnCrea()) {
			new ControllerServerWait(frame);
			
		}
		else {
			new ControllerClient(frame);
			/*Client c=new Client(frame,"localhost");
			Thread t=new Thread(c);
			t.start();*/
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(frame.getgC()==null) {
				frame.getG().getserverPlayer().setVelY(-10);
			}
			else {
				frame.getgC().getClientPlayer().setVelY(-10);
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(frame.getgC()==null) {
				frame.getG().getserverPlayer().setVelY(10);
			}
			else {
				frame.getgC().getClientPlayer().setVelY(10);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_UP) {
			if(frame.getgC()==null) {
				frame.getG().getserverPlayer().setVelY(0);
			}
			else {
				frame.getgC().getClientPlayer().setVelY(0);
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			if(frame.getgC()==null) {
				frame.getG().getserverPlayer().setVelY(0);
			}
			else {
				frame.getgC().getClientPlayer().setVelY(0);
			}
		}
	}

}
