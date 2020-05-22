import javax.swing.*;

public class Main {
	public static String robot="roballs";
	public static void main(String[] args) {

		// run thread
		UDPServer udpServer = new UDPServer();
		Thread t = new Thread(udpServer);
		t.start();

		JFrame fenetre = new EditorFrame();


	}

}
