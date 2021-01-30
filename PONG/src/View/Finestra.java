package View;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightContrastIJTheme;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIDefaults;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
*/
public class Finestra extends JFrame {
	

	private JPanel contentPane;
	private JLabel lblPong;
	private JButton btnCrea;
	private JButton btnUnisciti;
	private Gioco g=null;
	private GiocoClient gC=null;
	private ServerWait sW=null;
	private ClientWait cW=null;
	private Image img;
	private Font font;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;

	public Finestra() {

		try {
			UIManager.setLookAndFeel(new FlatArcDarkContrastIJTheme());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("bin\\PixelMplus10-Regular.ttf")).deriveFont(60f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedImage icon;
		try {
			icon = ImageIO.read(getClass().getResource("/immagini/pong-logo.jpg"));
			this.setIconImage(icon);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.setFocusable(true);
		contentPane.setLayout(new GridLayout(5, 1, 0, 0));
		
		panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		panel = new JPanel();
		contentPane.add(panel);
		lblPong = new JLabel("PONG");
		panel.add(lblPong);
		lblPong.setFont(font);
		lblPong.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		btnCrea = new JButton("Crea");
		panel_1.add(btnCrea);
		btnCrea.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		
		panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		btnUnisciti = new JButton("Unisciti");
		panel_2.add(btnUnisciti);
		btnUnisciti.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		
		panel_4 = new JPanel();
		contentPane.add(panel_4);
	}


	public void changePanel() {
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.contentPane);
		this.revalidate();
		gC=null;
		sW=null;
		cW=null;
	}
	
	public void changePanel(Gioco g) {
		this.g=g;
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.g);
		this.revalidate();
		gC=null;
		sW=null;
		cW=null;
	}
	
	public void changePanel(ClientWait cW) {
		this.cW=cW;
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.cW);
		this.revalidate();
		sW=null;
		g=null;
		gC=null;
	}
	
	public void changePanel(ServerWait sW) {
		this.sW=sW;
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.sW);
		this.revalidate();
		gC=null;
		g=null;
		cW=null;
	}
	
	public void changePanel(GiocoClient gC) {
		this.gC=gC;
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.gC);
		this.revalidate();
		/*g=null;
		sW=null;
		cW=null;*/
	}

	public JButton getBtnCrea() {
		return btnCrea;
	}

	public void setBtnCrea(JButton btnCrea) {
		this.btnCrea = btnCrea;
	}

	public JButton getBtnUnisciti() {
		return btnUnisciti;
	}

	public void setBtnUnisciti(JButton btnUnisciti) {
		this.btnUnisciti = btnUnisciti;
	}

	public Gioco getG() {
		return g;
	}

	public GiocoClient getgC() {
		return gC;
	}

	public ServerWait getsW() {
		return sW;
	}

	public ClientWait getcW() {
		return cW;
	}
	
	
}
