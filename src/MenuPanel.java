import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class MenuPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu file, option;
	JMenuItem menuSave, menuLoad;
	JMenuItem menuInverse;
	JCheckBoxMenuItem menuDrawAll;
	JCheckBoxMenuItem menuDrawLine;
	
	ButtonGroup robot_grp;
	JRadioButton roballs;
	JRadioButton bobby;
	JFrame frame;
	
	public MenuPanel(){
		this.setLayout(new GridLayout(1, 8));
		file = new JMenu("Fichier");
		option = new JMenu("Option");
		menuBar.add(file);
		menuBar.add(option);
		
		menuSave = new JMenuItem("Enregistrer", new ImageIcon(getClass().getResource("/save.png")));
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuSave.addActionListener(saveFile);
		menuLoad = new JMenuItem("Importer", new ImageIcon(getClass().getResource("/load.png")));
		menuLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		menuLoad.addActionListener(loadFile);
		

		file.add(menuLoad);
		file.add(menuSave);
		
		menuDrawAll = new JCheckBoxMenuItem("Afficher tous les robots");
		menuDrawAll.setEnabled(false);
		//menuDrawAll.addActionListener(drawAll);
		menuDrawLine = new JCheckBoxMenuItem("Afficher le parcours", true);
		menuDrawLine.addActionListener(drawLine);		
		menuInverse = new JMenuItem("Inverser la stratégie");
		menuInverse.addActionListener(inverseStrat);
		//menuInverse.setEnabled(false);
		
		 roballs = new JRadioButton("Krabs");
		 roballs.setSelected(true);
		 bobby = new JRadioButton("Bobby 5K");
		 bobby.setEnabled(false);
		 robot_grp = new ButtonGroup();
		 robot_grp.add(roballs);
		 robot_grp.add(bobby);
		
		option.add(menuDrawLine);
		option.add(menuDrawAll);
		option.add(menuInverse);
		
		option.addSeparator();
		option.add(roballs);
		option.add(bobby);
		
		this.add(menuBar);
		
		roballs.addActionListener(roballsSelected);
		bobby.addActionListener(bobbySelected);
	}
	
	ActionListener roballsSelected = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Main.robot = "roballs";
		}
	};
	
	ActionListener bobbySelected = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Main.robot = "bobby";
		}
	};
	
	ActionListener saveFile = new ActionListener(){ //Sauvegarde en fichier XML
		
		public void actionPerformed(ActionEvent e) {
			FileDialog fDialog = new FileDialog(frame, "Sauvegarde", FileDialog.SAVE);
			fDialog.setFile(".json");
			fDialog.setVisible(true);
			String path = fDialog.getDirectory() + fDialog.getFile();
			ListModel<Robot> liste = EditorPanel.list.getModel();
			JSONArray strat = new JSONArray();
			//strat.put("nbPoints", String.valueOf(liste.getSize()));
			int oldX=0, oldY=0;
			int oldAngle=0;
			for(int i=0; i<liste.getSize(); i++) {
				JSONOrderedObject point = new JSONOrderedObject();
				Robot r = liste.getElementAt(i);
				switch(r.getType()) {
					case "THETA":{
						point.put("comments", r.getCommentaire());
						point.put("type", r.getType());
						point.put("THETA", r.getAngle());
						point.put("speed", r.getVitesse());
						point.put("timeout", r.getTimeOut());
						point.put("action", r.getAction());
						point.put("angle_tolerance", r.getDeltaAngle());
						break;
					}
					
					case "XY_ABSOLU":{
						point.put("comments", r.getCommentaire());
						point.put("type", r.getType());
						point.put("X", r.getX());
						point.put("Y", r.getY());
						//point.put("THETA", r.getAngle());
						point.put("speed", r.getVitesse());
						point.put("timeout", r.getTimeOut());
						point.put("action", r.getAction());
						point.put("distance_tolerance", r.getDeltaDeplacement());
						point.put("angle_tolerance", r.getDeltaAngle());
						point.put("slipping", r.getDerapage());
						//point.put("attAction", r.getAttAction());
						break;
					}
					case "POSITION":{
						point.put("comments", r.getCommentaire());
						point.put("type", r.getType());
						point.put("X", r.getX());
						point.put("Y", r.getY());
						point.put("THETA", r.getAngle());
						/*point.put("deltaDeplacement", r.getDeltaDeplacement());
						point.put("deltaAngle", r.getDeltaAngle());
						point.put("vitesse", r.getVitesse());
						point.put("blocage", r.getBlocage());
						point.put("timeOut", r.getTimeOut());
						point.put("action", r.getAction());
						point.put("attAction", r.getAttAction());*/
						break;
					}

					case "CALIB_X":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("X", r.getX());
						point.put("THETA", r.getAngle());
						break;
					}
					case "CALIB_Y":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("Y", r.getY());
						point.put("THETA", r.getAngle());
						break;
					}
					case "CALIB_XY":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("X", r.getX());
						point.put("Y", r.getY());
						point.put("THETA", r.getAngle());
						break;
					}
					/*case "DeplacementX":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("X", r.getX());
						point.put("deltaDeplacement", r.getDeltaDeplacement());
						point.put("deltaAngle", r.getDeltaAngle());
						point.put("vitesse", r.getVitesse());
						point.put("sens", r.getSens());
						point.put("blocage", r.getBlocage());
						point.put("coefCourbe", r.getCoefCourbe());
						point.put("lissage", r.getLissage());
						point.put("derapage", r.getDerapage());
						point.put("timeOut", r.getTimeOut());
						point.put("action", r.getAction());
						point.put("attAction", r.getAttAction());
						break;
					}
					
					case "DeplacementY":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("Y", r.getY());
						point.put("deltaDeplacement", r.getDeltaDeplacement());
						point.put("deltaAngle", r.getDeltaAngle());
						point.put("vitesse", r.getVitesse());
						point.put("sens", r.getSens());
						point.put("blocage", r.getBlocage());
						point.put("coefCourbe", r.getCoefCourbe());
						point.put("lissage", r.getLissage());
						point.put("derapage", r.getDerapage());
						point.put("timeOut", r.getTimeOut());
						point.put("action", r.getAction());
						point.put("attAction", r.getAttAction());
						break;
					}
					

					
					case "RecalageX":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("X", r.getX());
						point.put("angle", r.getAngle());
						break;
					}
					case "RecalageY":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("Y", r.getY());
						point.put("angle", r.getAngle());
						break;
					}
					case "RecalageXY":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("X", r.getX());
						point.put("Y", r.getY());
						point.put("angle", r.getAngle());
						break;
					}
					case "DeplacementRelatif":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("X", r.getX()-oldX);
						point.put("Y", r.getY()-oldY);
						point.put("deltaDeplacement", r.getDeltaDeplacement());
						point.put("deltaAngle", r.getDeltaAngle());
						point.put("vitesse", r.getVitesse());
						point.put("sens", r.getSens());
						point.put("blocage", r.getBlocage());
						point.put("coefCourbe", r.getCoefCourbe());
						point.put("lissage", r.getLissage());
						point.put("derapage", r.getDerapage());
						point.put("timeOut", r.getTimeOut());
						point.put("action", r.getAction());
						point.put("attAction", r.getAttAction());
						break;
					}
					
					case "AngleRelatif":{
						point.put("commentaire", r.getCommentaire());
						point.put("type", r.getType());
						point.put("angle", r.getAngle()-oldAngle);
						point.put("deltaAngle", r.getDeltaAngle());
						point.put("vitesse", r.getVitesse());
						point.put("blocage", r.getBlocage());
						point.put("timeOut", r.getTimeOut());
						point.put("action", r.getAction());
						point.put("attAction", r.getAttAction());
						break;
					}*/
				}
				oldX=r.getX();
				oldY=r.getY();
				oldAngle = r.getAngle();
				strat.add(point);
				//strat.add("Point"+(i+1), point);
			}
			JSONObject root = new JSONObject();
			root.put("Points", strat);
			File file =new File(path);
		       FileWriter filew;
			try {
				filew = new FileWriter(file);
				filew.write(root.toJSONString());
				filew.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Sauvergarde terminé !");
		       
		       
		   }
	};
	
	ActionListener loadFile = new ActionListener(){//Importation fichier XML
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			FileDialog fDialog = new FileDialog(frame, "Importation", FileDialog.LOAD);
	        fDialog.setFile(".json");
	        fDialog.setVisible(true);
	        String path = fDialog.getDirectory() + fDialog.getFile();
	        File file =new File(path);
        	int nbElements= EditorPanel.stepList.size();
			try {
				JSONParser parser = new JSONParser();
	            Object obj = parser.parse(new FileReader(file));
	            JSONObject strat = (JSONObject) obj;
	            JSONArray points = (JSONArray) strat.get("Points");
	            Iterator<JSONObject> point = points.iterator();
	            
	            Robot r = new Robot();
				int oldX=0, oldY=0;
				int oldAngle=0; 
	            while(point.hasNext()) {
	            	r = new Robot(r);
	            	JSONObject params = point.next();
	            	r.setType((String)params.get("type"));
	            	switch(r.getType()){
		            	case "THETA":{
		            		r.setCommentaire((String)params.get("comments"));
							r.setAngle(Math.toIntExact((Long)params.get("THETA")));
							r.setDeltaAngle((Double)params.get("angle_tolerance"));
							r.setVitesse(Math.toIntExact((Long)params.get("speed")));
							//r.setBlocage((String)params.get("blocage"));
							r.setTimeOut(Math.toIntExact((Long)params.get("timeout")));
							r.setAction((String)params.get("action"));
							//r.setAttAction((boolean)params.get("attAction"));
							break;
						}
						
						case "XY_ABSOLU":{
							r.setCommentaire((String)params.get("comments"));
							r.setX(Math.toIntExact((Long)params.get("X")));
							r.setY(Math.toIntExact((Long)params.get("Y")));
							//r.setAngle(Math.toIntExact((Long)params.get("THETA")));
							r.setDeltaDeplacement(Math.toIntExact((Long)params.get("distance_tolerance")));
							r.setDeltaAngle((Double)params.get("angle_tolerance"));
							r.setVitesse(Math.toIntExact((Long)params.get("speed")));
							//r.setSens((String)params.get("sens"));
							//r.setBlocage((String)params.get("blocage"));
							//r.setCoefCourbe((Double)params.get("coefCourbe"));
							//r.setLissage((boolean)params.get("lissage"));
							if(params.get("slipping") == null) {
								r.setDerapage(false);
							}
							else {
								r.setDerapage((boolean)params.get("slipping"));
							}
							r.setTimeOut(Math.toIntExact((Long)params.get("timeout")));
							r.setAction((String)params.get("action"));
							//r.setAttAction((boolean)params.get("attAction"));
							break;
						}
						case "POSITION":{
							r.setCommentaire((String)params.get("comments"));
							r.setX(Math.toIntExact((Long)params.get("X")));
							r.setY(Math.toIntExact((Long)params.get("Y")));
							r.setAngle(Math.toIntExact((Long)params.get("THETA")));
							break;
						}
						case "CALIB_X":{
							r.setCommentaire((String)params.get("comments"));
							r.setX(Math.toIntExact((Long)params.get("X")));
							r.setAngle(Math.toIntExact((Long)params.get("THETA")));

							break;
						}
						case "CALIB_Y":{
							r.setCommentaire((String)params.get("comments"));
							r.setY(Math.toIntExact((Long)params.get("Y")));
							r.setAngle(Math.toIntExact((Long)params.get("THETA")));
							break;
						}
						case "CALIB_XY":{
							r.setCommentaire((String)params.get("comments"));
							r.setX(Math.toIntExact((Long)params.get("X")));
							r.setY(Math.toIntExact((Long)params.get("Y")));
							r.setAngle(Math.toIntExact((Long)params.get("angle")));
							break;
						}
						/*case "DeplacementX":{
							r.setCommentaire((String)params.get("commentaire"));
							r.setX(Math.toIntExact((Long)params.get("X")));
							r.setDeltaDeplacement(Math.toIntExact((Long)params.get("deltaDeplacement")));
							r.setDeltaAngle((Double)params.get("deltaAngle"));
							r.setVitesse(Math.toIntExact((Long)params.get("vitesse")));
							r.setSens((String)params.get("sens"));
							r.setBlocage((String)params.get("blocage"));
							r.setCoefCourbe((Double)params.get("coefCourbe"));
							r.setLissage((boolean)params.get("lissage"));
							r.setDerapage((boolean)params.get("derapage"));
							r.setTimeOut(Math.toIntExact((Long)params.get("timeOut")));
							r.setAction((String)params.get("action"));
							r.setAttAction((boolean)params.get("attAction"));
							break;
						}
						
						case "DeplacementY":{
							r.setCommentaire((String)params.get("commentaire"));
							r.setY(Math.toIntExact((Long)params.get("Y")));
							r.setDeltaDeplacement(Math.toIntExact((Long)params.get("deltaDeplacement")));
							r.setDeltaAngle((Double)params.get("deltaAngle"));
							r.setVitesse(Math.toIntExact((Long)params.get("vitesse")));
							r.setSens((String)params.get("sens"));
							r.setBlocage((String)params.get("blocage"));
							r.setCoefCourbe((Double)params.get("coefCourbe"));
							r.setLissage((boolean)params.get("lissage"));
							r.setDerapage((boolean)params.get("derapage"));
							r.setTimeOut(Math.toIntExact((Long)params.get("timeOut")));
							r.setAction((String)params.get("action"));
							r.setAttAction((boolean)params.get("attAction"));
							break;
						}
						
						case "Position":{
							r.setCommentaire((String)params.get("commentaire"));
							r.setX(Math.toIntExact((Long)params.get("X")));
							r.setY(Math.toIntExact((Long)params.get("Y")));
							r.setAngle(Math.toIntExact((Long)params.get("angle")));
							r.setDeltaAngle((Double)params.get("deltaAngle"));
							r.setDeltaDeplacement(Math.toIntExact((Long)params.get("deltaDeplacement")));
							r.setVitesse(Math.toIntExact((Long)params.get("vitesse")));
							r.setBlocage((String)params.get("blocage"));
							r.setTimeOut(Math.toIntExact((Long)params.get("timeOut")));
							r.setAction((String)params.get("action"));
							r.setAttAction((boolean)params.get("attAction"));		
							break;
						}
						
						case "RecalageX":{
							r.setCommentaire((String)params.get("commentaire"));
							r.setX(Math.toIntExact((Long)params.get("X")));
							r.setAngle(Math.toIntExact((Long)params.get("angle")));
				
							break;
						}
						case "RecalageY":{
							r.setCommentaire((String)params.get("commentaire"));
							r.setY(Math.toIntExact((Long)params.get("Y")));
							r.setAngle(Math.toIntExact((Long)params.get("angle")));
		
				
							break;
						}
						case "RecalageXY":{
							r.setCommentaire((String)params.get("commentaire"));
							r.setX(Math.toIntExact((Long)params.get("X")));
							r.setY(Math.toIntExact((Long)params.get("Y")));
							r.setAngle(Math.toIntExact((Long)params.get("angle")));
							break;
						}
						case "DeplacementRelatif":{
							r.setCommentaire((String)params.get("commentaire"));
							r.setX(oldX+Math.toIntExact((Long)params.get("X")));
							r.setY(oldY+Math.toIntExact((Long)params.get("Y")));
							r.setDeltaDeplacement(Math.toIntExact((Long)params.get("deltaDeplacement")));
							r.setVitesse(Math.toIntExact((Long)params.get("vitesse")));
							r.setSens((String)params.get("sens"));
							r.setBlocage((String)params.get("blocage"));
							r.setCoefCourbe((Double)params.get("coefCourbe"));
							r.setLissage((boolean)params.get("lissage"));
							r.setDerapage((boolean)params.get("derapage"));
							r.setTimeOut(Math.toIntExact((Long)params.get("timeOut")));
							r.setAction((String)params.get("action"));
							r.setAttAction((boolean)params.get("attAction"));
							break;
						}
						
						case "AngleRelatif":{
							r.setCommentaire((String)params.get("commentaire"));
							r.setAngle(oldAngle+Math.toIntExact((Long)params.get("angle")));
							r.setDeltaAngle(Math.toIntExact((Long)params.get("deltaAngle")));
							r.setVitesse(Math.toIntExact((Long)params.get("vitesse")));
							r.setBlocage((String)params.get("blocage"));
							r.setTimeOut(Math.toIntExact((Long)params.get("timeOut")));
							r.setAction((String)params.get("action"));
							r.setAttAction((boolean)params.get("attAction"));
							break;
						}*/
	            	}
					oldX=r.getX();
					oldY=r.getY();
					oldAngle = r.getAngle();
	            	EditorPanel.stepList.addElement(r);
	            }
	            //int nbPoints = Integer.valueOf((String)strat.get("nbPoints"));
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}			
			EditorPanel.list.setSelectedIndex(nbElements);
			for(int i=0;i<nbElements;i++)EditorPanel.stepList.remove(0);
			System.out.println("Importation terminé !");
		}
	};
	
	ActionListener drawLine = new ActionListener(){//Affiche la trajectoire du robot
		   public void actionPerformed(ActionEvent e) {
			   	TablePanel.drawLine = !TablePanel.drawLine;
		   }
	};
	
	ActionListener inverseStrat = new ActionListener(){//Inverse la strategie
		   public void actionPerformed(ActionEvent e) {
				 ListModel<Robot> liste = EditorPanel.list.getModel();
				 EditorPanel.updateCoord();
				 for(int i=0; i<liste.getSize();i++){
					 liste.getElementAt(i).setX(3000-liste.getElementAt(i).getX());
					 liste.getElementAt(i).setY(liste.getElementAt(i).getY());
					 liste.getElementAt(i).setAngle((180-liste.getElementAt(i).getAngle()) % 360);
				 }
		   }
	};
}



