package View;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
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


	public Finestra() {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setFont(new Font("Cambria", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		/*contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(332dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
				new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(16dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(214dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(16dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(38dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		*/
		this.setFocusable(true);
		lblPong = new JLabel("PONG");
		lblPong.setFont(new Font("Verdana Pro", Font.PLAIN, 14));
		lblPong.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPong, "16, 4");
		
		btnCrea = new JButton("Crea");
		btnCrea.setFont(new Font("Verdana Pro", Font.PLAIN, 11));
		contentPane.add(btnCrea, "16, 10");
		
		btnUnisciti = new JButton("Unisciti");
		btnUnisciti.setFont(new Font("Verdana Pro", Font.PLAIN, 11));
		contentPane.add(btnUnisciti, "16, 14");
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
