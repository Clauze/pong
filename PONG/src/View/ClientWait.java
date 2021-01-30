package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ClientWait extends JPanel {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel LabelIP;
	private JLabel Ip;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JTextField textFieldNickName;
	private JLabel lblNewLabel_1;
	private JPanel panel_6;
	private JPanel panel_7;
	private JRadioButton rdbtnManual;
	private JRadioButton rdbtnAuto;
	private JTextField textFieldIPServer;
	private JPanel panel_8;
	private JLabel lblNewLabel_2;
	private JTextField textFieldPortaServer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnConnetti;
	private JLabel lblNewLabel_3;
	private JButton btnIndietro;
	private Font font;
	/**
	 * Create the panel.
	 */
	public ClientWait() {
		setLayout(new BorderLayout(0, 0));
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
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btnConnetti = new JButton("Connetti");
		btnConnetti.setFont(font);
		panel.add(btnConnetti);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(font);
		panel.add(btnIndietro);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		LabelIP = new JLabel("il tuo indirizzo ip: ");
		LabelIP.setFont(font);
		panel_1.add(LabelIP);
		
		Ip = new JLabel("");
		Ip.setFont(font);
		panel_1.add(Ip);
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.EAST);
		
		panel_3 = new JPanel();
		add(panel_3, BorderLayout.CENTER);
		panel_3.setAlignmentX(CENTER_ALIGNMENT);
		panel_3.setAlignmentY(CENTER_ALIGNMENT);
		panel_3.setLayout(new GridLayout(5, 1, 0, 0));
		
		panel_6 = new JPanel();
		panel_3.add(panel_6);
		
		lblNewLabel_3 = new JLabel("Mi connetto al server ...");
		lblNewLabel_3.setFont(font);
		lblNewLabel_3.setVisible(false);
		panel_6.add(lblNewLabel_3);
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("Inserire nickname");
		lblNewLabel.setFont(font);
		panel_4.add(lblNewLabel);
		
		textFieldNickName = new JTextField();
		textFieldNickName.setFont(font);
		textFieldNickName.addKeyListener(new KeyAdapter() {
	    	public void keyTyped(KeyEvent e) {
	    		if (textFieldNickName.getText().length() >= 10 )
	    			e.consume();
	   		}
	    });
		panel_4.add(textFieldNickName);
		textFieldNickName.setColumns(10);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_1 = new JLabel("Inserire ip server");
		lblNewLabel_1.setFont(font);
		panel_5.add(lblNewLabel_1);
		
		textFieldIPServer = new JTextField();
		textFieldIPServer.setFont(font);
		panel_5.add(textFieldIPServer);
		textFieldIPServer.setColumns(10);
		
		panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		lblNewLabel_2 = new JLabel("Inserire porta server");
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setFont(font);
		panel_8.add(lblNewLabel_2);
		
		textFieldPortaServer = new JTextField();
		textFieldPortaServer.setVisible(false);
		textFieldPortaServer.addKeyListener(new KeyAdapter() {
	    	public void keyTyped(KeyEvent e) {
	    		if (textFieldPortaServer.getText().length() >= 5 )
	    			e.consume();
	   		}
	    });
		textFieldPortaServer.setFont(font);
		textFieldPortaServer.setColumns(10);
		panel_8.add(textFieldPortaServer);
		
		panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		rdbtnAuto = new JRadioButton("automatico");
		rdbtnAuto.setFont(font);
		buttonGroup.add(rdbtnAuto);
		panel_7.add(rdbtnAuto);
		
		rdbtnManual = new JRadioButton("manuale");
		rdbtnManual.setFont(font);
		buttonGroup.add(rdbtnManual);
		panel_7.add(rdbtnManual);

	}
	
	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}
	
	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}
	
	public JLabel getIp() {
		return Ip;
	}
	
	public JTextField getTextFieldNickName() {
		return textFieldNickName;
	}
	
	public JRadioButton getRdbtnManual() {
		return rdbtnManual;
	}
	
	public JRadioButton getRdbtnAuto() {
		return rdbtnAuto;
	}
	
	public JTextField getTextFieldIPServer() {
		return textFieldIPServer;
	}
	
	public JTextField getTextFieldPortaServer() {
		return textFieldPortaServer;
	}
	
	public JButton getBtnConnetti() {
		return btnConnetti;
	}

	public JButton getBtnIndietro() {
		return btnIndietro;
	}	
	
	
}
