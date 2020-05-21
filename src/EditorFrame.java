import javax.swing.*;
import java.awt.*;

public class EditorFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	static TablePanel TPanel= new TablePanel();
	public EditorFrame(){
		super("ENSIM'ELEC Strategy Editor");
		this.setSize(1300, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(TPanel, BorderLayout.CENTER);
		this.add(new MenuPanel(), BorderLayout.NORTH);
		this.add(new EditorPanel(), BorderLayout.EAST);
		this.setVisible(true);
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	static public void update(){
		TPanel.repaint();
	}
	
}
