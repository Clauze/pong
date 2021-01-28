package View;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import java.awt.Font;

public class ShowMyIp extends JPanel {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel MyIP;
	private JLabel Ip;
	private JButton btnAbbandona;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JTextField textFieldNickName;
	private JLabel lblNewLabel_1;
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel lblNewLabel_2;
	private JTextField textField;
	private JPanel panel_8;
	private JRadioButton rdbtnManual;
	private JRadioButton rdbtnAuto;

	/**
	 * Create the panel.
	 */
	public ShowMyIp() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btnAbbandona = new JButton("Lascia");
		btnAbbandona.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnAbbandona);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		MyIP = new JLabel("il tuo indirizzo ip: ");
		MyIP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(MyIP);
		
		Ip = new JLabel("");
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
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("Inserire nickname");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_4.add(lblNewLabel);
		
		textFieldNickName = new JTextField();
		textFieldNickName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_4.add(textFieldNickName);
		textFieldNickName.setColumns(10);
		
		panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		lblNewLabel_2 = new JLabel("Inserire porta server");
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_7.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		panel_7.add(textField);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_1 = new JLabel("Aspetto un altro giocatore....");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_5.add(lblNewLabel_1);
		
		panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		rdbtnManual = new JRadioButton("manuale");
		panel_8.add(rdbtnManual);
		
		rdbtnAuto = new JRadioButton("automatico");
		panel_8.add(rdbtnAuto);

	}

}