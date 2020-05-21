
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

/**
 * Author : Canyon
 * Edited by
 * @Ta0uf19
 *
 * TODO:
 *  - fix up, down key
 */

public class TablePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static int WEIGHT_PADDING_PX = 30;
	private static int HEIGHT_PADDING_PX = 600;

	Image tableImg, robotImg;
	static boolean drawLine;
	
	public TablePanel(){
		try {
			tableImg = ImageIO.read(getClass().getResource("/table.png"));
			robotImg = ImageIO.read(getClass().getResource("/roballs.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addMouseMotionListener(mooved);
		this.addMouseListener(pressed);
		this.addMouseWheelListener(wheelRotate);
		this.addKeyListener(keyPressed);
		drawLine=true;
	}
	
	public void paintComponent(Graphics g){//Affichage de la table et tout les �l�ments dessus
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform at = g2d.getTransform();

		g2d.translate(WEIGHT_PADDING_PX, HEIGHT_PADDING_PX);
		// inverser le plan
		g2d.scale(1, -1);

		g.drawImage(tableImg, 0, 0, this);
		drawRobot(g2d, EditorPanel.actualRobot);
		if(drawLine)drawLine(g2d);
	}

	//Transition
	KeyListener keyPressed = new KeyListener(){
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_UP) EditorPanel.actualRobot.setY(EditorPanel.actualRobot.getY()+1);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) EditorPanel.actualRobot.setY(EditorPanel.actualRobot.getY()-1);
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) EditorPanel.actualRobot.setX(EditorPanel.actualRobot.getX()+1);
			if(e.getKeyCode()==KeyEvent.VK_LEFT) EditorPanel.actualRobot.setX(EditorPanel.actualRobot.getX()-1);
			if(EditorPanel.list.getSelectedIndex()!=0){
				Robot old = EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex()-1);
				Robot actual = EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex());
				int theta = (int) (Math.atan2((double)(actual.getY()-old.getY()),(double)(actual.getX()-old.getX()))*180/Math.PI);
				//int theta=(int) ((int)Math.atan((double)(old.getX()-actual.getX())/(double)(old.getY()-actual.getY()))*180/Math.PI);
				EditorPanel.actualRobot.setAngle(theta);
			}
			EditorPanel.updateCoord();
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {

			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	};

	/**
	 * Rotation du robot avec la molette
	 */
	MouseWheelListener wheelRotate = new MouseWheelListener(){
		public void mouseWheelMoved(MouseWheelEvent e) {
			EditorPanel.actualRobot.setAngle(EditorPanel.actualRobot.getAngle()-e.getWheelRotation()*2);
			EditorPanel.updateCoord();
			repaint();
			
		}
	};
	/**
	 * Déplacement du robot avec la souris
	 */
	MouseMotionListener mooved = new MouseMotionListener(){

		@Override
		public void mouseDragged(MouseEvent e) {
			/*if(EditorPanel.list.getSelectedIndex()!=0){
				if(EditorPanel.actualRobot.getType().equals("DeplacementX")) {
					EditorPanel.actualRobot.setX(Robot.dispXToReal(e.getX()));
					EditorPanel.actualRobot.setY(EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex()-1).getY());
				}else if(EditorPanel.actualRobot.getType().equals("DeplacementY")) {
					EditorPanel.actualRobot.setX(EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex()-1).getX());
					EditorPanel.actualRobot.setY(Robot.dispYToReal(e.getY()));
				}else {
					EditorPanel.actualRobot.setX(Robot.dispXToReal(e.getX()));
					EditorPanel.actualRobot.setY(Robot.dispYToReal(e.getY()));
				}
				EditorPanel.actualRobot.setX(Robot.dispXToReal(e.getX()));
				EditorPanel.actualRobot.setY(Robot.dispYToReal(e.getY()));
			}else {
				EditorPanel.actualRobot.setX(Robot.dispXToReal(e.getX()));
				EditorPanel.actualRobot.setY(Robot.dispYToReal(e.getY()));
			}*/

			EditorPanel.actualRobot.setX(Robot.dispXToReal(e.getX()));
			EditorPanel.actualRobot.setY(Robot.dispYToReal(e.getY()));


			int theta=0;
			if(EditorPanel.list.getSelectedIndex()!=0){
				Robot old = EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex()-1);
				Robot actual = EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex());

				if(EditorPanel.actualRobot.getSens().equals("arriere")) {
					theta=(int) (Math.atan2((double)(actual.getY()-old.getY()),(double)(actual.getX()-old.getX()))*180/Math.PI)-180;
				}else {
					theta=(int) (Math.atan2((double)(actual.getY()-old.getY()),(double)(actual.getX()-old.getX()))*180/Math.PI);
				}
				EditorPanel.actualRobot.setAngle(theta);
			}
			EditorPanel.updateCoord();
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

		};
		
		MouseListener pressed = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				requestFocus();
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				EditorPanel.actualRobot.setX(Robot.dispXToReal(e.getX()));
				EditorPanel.actualRobot.setY(Robot.dispYToReal(e.getY()));
				EditorPanel.updateCoord();
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			
		};
	
	private void drawRobot(Graphics2D g2d, Robot r){
		if(Main.robot.equals("roballs")) {
			try {
				robotImg= ImageIO.read(getClass().getResource("/roballs.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				robotImg= ImageIO.read(getClass().getResource("/bobby.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		AffineTransform at = AffineTransform.getTranslateInstance(r.getDispX(), r.getDispY());
		//System.out.println(at);
		if(Main.robot.equals("roballs")) {
			at.rotate(Math.toRadians(EditorPanel.actualRobot.getRealAngle()),45,60); //0 � changer par l'angle
		}else {
			at.rotate(Math.toRadians(EditorPanel.actualRobot.getRealAngle()),45,60); //0 � changer par l'angle
			//TODO edit le point de pivot
		}

		g2d.drawImage(robotImg,at,this);

		//g2d.drawRect(EditorPanel.actualRobot.getDispX(), EditorPanel.actualRobot.getDispY(), 90, 91);
		g2d.setColor(new Color(255,0,0));
		if(Main.robot.equals("roballs")) {
			g2d.fillOval(EditorPanel.actualRobot.getDispX()+43, EditorPanel.actualRobot.getDispY()+58, 4,4);
		}else {
			g2d.fillOval(EditorPanel.actualRobot.getDispX()+43, EditorPanel.actualRobot.getDispY()+58, 4,4);
			//TODO edit le point de pivot
		}
	}

	/**
	 * Drawline stratégie
	 * @param g
	 */
	public void drawLine(Graphics2D g){
		 ListModel<Robot> liste = EditorPanel.list.getModel();
		 for(int i=0; i<liste.getSize()-1;i++){
			 g.setColor(new Color(51,0, 255));
			 g.setStroke(new BasicStroke(2f));
			 if(Main.robot.equals("roballs")) {
				 g.drawLine(liste.getElementAt(i).getDispX()+45,liste.getElementAt(i).getDispY()+60,liste.getElementAt(i+1).getDispX()+45, liste.getElementAt(i+1).getDispY()+60);
			 }else {
				 g.drawLine(liste.getElementAt(i).getDispX()+45,liste.getElementAt(i).getDispY()+60,liste.getElementAt(i+1).getDispX()+45, liste.getElementAt(i+1).getDispY()+60);
				 //TODO changer point de pivot
			 }
		}
	}
	
}
