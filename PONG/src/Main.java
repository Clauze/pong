import Control.Controller;
import View.Finestra;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Finestra f=new Finestra();
		f.setVisible(true);
		new Controller(f);
		
	}		
}
