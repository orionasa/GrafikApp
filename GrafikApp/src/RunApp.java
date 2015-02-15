import javax.swing.JFrame;


public class RunApp {
	
	public static void main(String Args[]){
		 Grafik_App test = new Grafik_App();
		 test.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		 test.setLocationRelativeTo(null);
		 test.setSize( 400, 550 );
		 test.setResizable(false);
		 test.setVisible( true );
		
	}

}
