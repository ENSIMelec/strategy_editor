import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.text.NumberFormat;
import java.util.Locale;



public class EditorPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	static public DefaultListModel<Robot> stepList = new DefaultListModel<Robot>();//Liste de toute les étapes du robot
	static public JList<Robot> list = new JList<Robot>(stepList);
	JScrollPane listScrollPane = new JScrollPane(list);
	
	public static Robot actualRobot;
	
	JButton addButton = new JButton("Ajouter");
	JButton suppButton = new JButton("Supprimer");
	
	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
	
	//String[] listType = new String[]{"Deplacement","DeplacementX", "DeplacementY", "Angle", "Position", "RecalageX", "RecalageY", "RecalageXY", "DeplacementRelatif", "AngleRelatif"};

	String[] listType = new String[]{"POSITION", "XY_ABSOLU", "THETA"};
	JComboBox strType = new JComboBox(listType);
	
	static JTextField strX = new JTextField("0");
	JLabel labelX = new JLabel("X :");
	
	static JTextField strY= new JTextField("0");
	JLabel labelY = new JLabel("Y :");
	
	static JTextField strAngle = new JTextField("0");
	JLabel labelAngle = new JLabel("Angle :");
	
	JLabel labelDeltaDeplacement = new JLabel("Distance Tolerance : ");
	JTextField strDeltaDeplacement = new JTextField("null");
	
	JLabel labelDeltaAngle= new JLabel("Angle Tolerance : ");
	JTextField strDeltaAngle = new JTextField("null");
	
	JLabel labelVitesse = new JLabel("Speed (mm/sec) :");
	JTextField strVitesse= new JTextField("400");
	
	ButtonGroup strSens = new ButtonGroup();
	JRadioButton strAvant = new JRadioButton("Avant");
	JRadioButton strArriere = new JRadioButton("Arrière");
	
	/*JLabel labelBlocage = new JLabel("Blocage :");
	String[] listBlocage = new String[]{"aucun", "avant", "arriere", "avantG", "avantD", "arriereG", "arriereD"};
	JComboBox strBlocage = new JComboBox(listBlocage);*/
	
	/*JLabel labelCoefCourbe = new JLabel("Coef courbe :");
	JTextField strCoefCourbe = new JTextField("0");
	
	JCheckBox btnLissage = new JCheckBox("Lissage");
	
	JCheckBox btnDerapage = new JCheckBox("Dérapage");
	
	JCheckBox btnAttAction = new JCheckBox("Attente Action");*/
	
	JLabel labelTimeout = new JLabel("Time Out (Attente Action) :");
	JTextField strTimeout= new JTextField("0");

	
	JTextField strAction= new JTextField("null");
	JLabel labelAction = new JLabel("Action :");
	
	JLabel labelCommentaire = new JLabel("Comments :");
	JTextArea commentaireArea = new JTextArea();
	
	public EditorPanel(){
		GridBagLayout gb = new GridBagLayout();
		this.setLayout(gb);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		strSens.add(strAvant);
		strSens.add(strArriere);
		
		gbc.gridx=0;
		gbc.gridwidth=2;
		gb.setConstraints(listScrollPane, gbc);
		gbc.gridwidth=1;
		gb.setConstraints(addButton, gbc);
	
		gbc.gridwidth=2;
		gb.setConstraints(strType, gbc);
		gbc.gridwidth=1;
		gb.setConstraints(labelX, gbc);
		gb.setConstraints(labelY, gbc);
		gb.setConstraints(labelAngle, gbc);
		gb.setConstraints(strAvant, gbc);
		gb.setConstraints(labelDeltaDeplacement, gbc);
		gb.setConstraints(labelDeltaAngle, gbc);
		gb.setConstraints(labelVitesse, gbc);
		//gb.setConstraints(labelBlocage, gbc);
		//gb.setConstraints(labelCoefCourbe, gbc);
		gb.setConstraints(labelTimeout, gbc);
		//gb.setConstraints(btnLissage, gbc);
		gbc.gridwidth=2;
		//gb.setConstraints(btnAttAction, gbc);
		gbc.gridwidth=1;
		gb.setConstraints(labelAction, gbc);
		gbc.gridwidth=2;
		gb.setConstraints(labelCommentaire, gbc);
		gb.setConstraints(commentaireArea, gbc);
		gbc.gridwidth=1;
		
		gbc.gridx=1;
		gb.setConstraints(suppButton, gbc);
		gb.setConstraints(strX,gbc);
		gb.setConstraints(strY, gbc);
		gb.setConstraints(strAngle, gbc);
		gb.setConstraints(strArriere, gbc);
		gb.setConstraints(strDeltaDeplacement, gbc);
		gb.setConstraints(strDeltaAngle, gbc);
		gb.setConstraints(strVitesse, gbc);
		//gb.setConstraints(strBlocage, gbc);
		//gb.setConstraints(strCoefCourbe, gbc);
		gb.setConstraints(strTimeout, gbc);
		//gb.setConstraints(btnDerapage, gbc);
		
		//gb.setConstraints(strBlocage, gbc);
		gb.setConstraints(strAction, gbc);
		
		
		actualRobot = new Robot(0,0, 0);
		stepList.addElement(actualRobot);
		list.setSelectedIndex(0);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFocusable(false);
		this.add(listScrollPane);
		this.add(addButton);
		this.add(suppButton);
		this.add(strType);
		this.add(strX);
		this.add(labelX);
		this.add(labelY);
		this.add(strY);
		this.add(strAngle);
		this.add(labelAngle);
		this.add(strAvant);
		this.add(strArriere);
		this.add(strDeltaAngle);
		this.add(labelDeltaAngle);
		this.add(strDeltaDeplacement);
		this.add(labelDeltaDeplacement);
		this.add(labelVitesse);
		this.add(strVitesse);
//		this.add(labelCoefCourbe);
//		this.add(strCoefCourbe);
//		this.add(labelBlocage);
//		this.add(strBlocage);
//		this.add(btnLissage);
//		this.add(btnDerapage);
//		this.add(btnAttAction);
		this.add(labelTimeout);
		this.add(strTimeout);
		this.add(labelAction);
		this.add(strAction);
		this.add(labelCommentaire);
		this.add(commentaireArea);
		
		
		strAvant.setSelected(true);
//		btnAttAction.setSelected(false);
//		btnDerapage.setSelected(false);
//		btnLissage.setSelected(false);
		strType.setSelectedItem("Position");
		
		strType.addActionListener(typeSelected);
		addButton.addActionListener(addToList);
		suppButton.addActionListener(suppToList);
		list.addListSelectionListener(selected);
		strX.addKeyListener(posChange);
		strY.addKeyListener(posChange);
		strAngle.addKeyListener(posChange);
		strDeltaAngle.addKeyListener(setDeltaAngle);
		strDeltaDeplacement.addKeyListener(setDeltaDeplacement);
		strAvant.addActionListener(avantSelected);
//		btnAttAction.addActionListener(attActionSelected);
//		btnDerapage.addActionListener(derapageSelected);
		strArriere.addActionListener(arriereSelected);
//		strBlocage.addActionListener(blocageSelected);
		strVitesse.addKeyListener(setVitesse);
		strTimeout.addKeyListener(setTimeout);
//		strCoefCourbe.addActionListener(setCoefCourbe);
//		btnLissage.addActionListener(lissageSelected);
		strAction.addKeyListener(setAction);
		commentaireArea.addKeyListener(setCommentaire);
		typeChange(strType.getSelectedItem().toString());
	}
	
	ActionListener addToList = new ActionListener(){//Ajout d'un robot à la liste
		   public void actionPerformed(ActionEvent e) {

		   	  actualRobot = new Robot(actualRobot);
			  //actualRobot.setType("XY_ABSOLU");
			  stepList.add(list.getSelectedIndex()+1, actualRobot);
		      //stepList.addElement(actualRobot);
		      //list.ensureIndexIsVisible(list.getModel().getSize()-1);
		      list.setSelectedIndex(list.getSelectedIndex()+1);
		   }
	};
		
	ActionListener suppToList = new ActionListener(){//Supression d'un robot à la liste
		   public void actionPerformed(ActionEvent e) {
			   if(stepList.size()>1){
				   int removeIndex = list.getSelectedIndex();
				   if(removeIndex==list.getModel().getSize()-1){
					   list.setSelectedIndex(removeIndex-1);
				   }else{
					   list.setSelectedIndex(removeIndex+1);
				   } 
			       stepList.remove(removeIndex);
			       actualRobot = stepList.lastElement();
			   }else{
				   System.out.println("Suppression impossible");
			   }
		   }
	};
	
	KeyAdapter posChange= new KeyAdapter() {
		public void keyReleased(java.awt.event.KeyEvent e)  {
			actualRobot.setX(Integer.parseInt(strX.getText()));
		   	actualRobot.setY(Integer.parseInt(strY.getText()));
		   	actualRobot.setAngle(Integer.parseInt(strAngle.getText()));
		   	updateCoord();
		}
	};
	
	KeyAdapter setTimeout = new KeyAdapter(){
		public void keyReleased(java.awt.event.KeyEvent e)  {					
			actualRobot.setTimeOut(Integer.parseInt(strTimeout.getText()));
		}
	};
	
//	ActionListener attActionSelected = new ActionListener(){
//		public void actionPerformed(ActionEvent e)  {
//			actualRobot.setAttAction(btnAttAction.isSelected());
//		}
//	};
	
//	ActionListener derapageSelected = new ActionListener(){
//		public void actionPerformed(ActionEvent e)  {
//			actualRobot.setDerapage(btnDerapage.isSelected());
//		}
//	};
	
//	ActionListener setCoefCourbe = new ActionListener(){
//		public void actionPerformed(ActionEvent e)  {
//			actualRobot.setCoefCourbe(Double.parseDouble(strCoefCourbe.getText()));
//		}
//	};
	
//	ActionListener lissageSelected = new ActionListener(){
//		public void actionPerformed(ActionEvent e)  {
//			actualRobot.setLissage(btnLissage.isSelected());
//		}
//	};
	
	KeyAdapter setAction = new KeyAdapter(){
		public void keyReleased(java.awt.event.KeyEvent e)  {					
			actualRobot.setAction(strAction.getText());
		}
	};
	
	KeyAdapter setCommentaire = new KeyAdapter(){
		public void keyReleased(java.awt.event.KeyEvent e)  {					
			actualRobot.setCommentaire(commentaireArea.getText());
		}
	};
	
	ActionListener avantSelected = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			actualRobot.setSens("avant");
		}
	};
	ActionListener arriereSelected = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			actualRobot.setSens("arriere");
		}
	};
	
//	ActionListener blocageSelected = new ActionListener(){
//		public void actionPerformed(ActionEvent e) {
//			actualRobot.setBlocage(strBlocage.getSelectedItem().toString());
//		}
//	};
	
	KeyAdapter setVitesse = new KeyAdapter(){
		public void keyReleased(java.awt.event.KeyEvent e) {					
			actualRobot.setVitesse(Integer.parseInt(strVitesse.getText()));
		}
	};
	
	KeyAdapter setDeltaDeplacement = new KeyAdapter(){
		public void keyReleased(java.awt.event.KeyEvent e) {					
			actualRobot.setDeltaDeplacement(Integer.parseInt(strDeltaDeplacement.getText()));
		}
	};
	
	KeyAdapter setDeltaAngle = new KeyAdapter(){
		public void keyReleased(java.awt.event.KeyEvent e) {					
			actualRobot.setDeltaAngle(Double.parseDouble(strDeltaAngle.getText()));
		}
	};
	
	ActionListener typeSelected = new ActionListener(){
		public void actionPerformed(ActionEvent e) {					
			typeChange(strType.getSelectedItem().toString());

		}
	};
	
	private void typeChange(String type) {
		actualRobot.setType(type);
		strX.setEnabled(true);
		strY.setEnabled(true);
		strAngle.setEnabled(true);
		strAvant.setEnabled(true);
		strArriere.setEnabled(true);
		strDeltaAngle.setEnabled(true);
		strDeltaDeplacement.setEnabled(true);
		strVitesse.setEnabled(true);
        strAction.setEnabled(true);
        strTimeout.setEnabled(true);
        
//		strCoefCourbe.setEnabled(true);
//		strBlocage.setEnabled(true);
//		btnLissage.setEnabled(true);
//		btnDerapage.setEnabled(true);
//		btnAttAction.setEnabled(true);
		switch(type) {
			case "THETA":{
				strX.setEnabled(false);
				strY.setEnabled(false);
				strAvant.setEnabled(false);
				strArriere.setEnabled(false);
				strDeltaDeplacement.setEnabled(false);
//				strCoefCourbe.setEnabled(false);
//				btnLissage.setEnabled(false);
//				btnDerapage.setEnabled(false);
				break;
			}
			
			case "XY_ABSOLU":{
				strAngle.setEnabled(false);
				break;
			}
			case "POSITION":{
				strVitesse.setEnabled(false);
				strAction.setEnabled(false);
				break;
			}
			
//			case "DeplacementX":{
//				strY.setEnabled(false);
//				strAngle.setEnabled(false);
//				break;
//			}
//
//			case "DeplacementY":{
//				strX.setEnabled(false);
//				strAngle.setEnabled(false);
//				break;
//			}
//
//			case "Position":{
////				strAvant.setEnabled(false);
////				strArriere.setEnabled(false);
//				strVitesse.setEnabled(false);
////				strBlocage.setEnabled(false);
//				break;
//			}
//
//			case "RecalageX":{
//				strY.setEnabled(false);
////				strAvant.setEnabled(false);
////				strArriere.setEnabled(false);
//				strDeltaAngle.setEnabled(false);
//				strDeltaDeplacement.setEnabled(false);
//				strVitesse.setEnabled(false);
////				strCoefCourbe.setEnabled(false);
////				strBlocage.setEnabled(false);
////				btnLissage.setEnabled(false);
////				btnDerapage.setEnabled(false);
////				btnAttAction.setEnabled(false);
//				break;
//			}
//			case "RecalageY":{
//				strX.setEnabled(false);
////				strAvant.setEnabled(false);
////				strArriere.setEnabled(false);
//				strDeltaAngle.setEnabled(false);
//				strDeltaDeplacement.setEnabled(false);
//				strVitesse.setEnabled(false);
////				strCoefCourbe.setEnabled(false);
////				strBlocage.setEnabled(false);
////				btnLissage.setEnabled(false);
////				btnDerapage.setEnabled(false);
////				btnAttAction.setEnabled(false);
//				break;
//			}
//			case "RecalageXY":{
//				strAvant.setEnabled(false);
//				strArriere.setEnabled(false);
//				strDeltaAngle.setEnabled(false);
//				strDeltaDeplacement.setEnabled(false);
//				strVitesse.setEnabled(false);
//				strCoefCourbe.setEnabled(false);
//				strBlocage.setEnabled(false);
//				btnLissage.setEnabled(false);
//				btnDerapage.setEnabled(false);
//				btnAttAction.setEnabled(false);
//				break;
//			}
//			case "DeplacementRelatif":{
//				strAngle.setEnabled(false);
//				break;
//			}
//
//			case "AngleRelatif":{
//				strX.setEnabled(false);
//				strY.setEnabled(false);
//				strAvant.setEnabled(false);
//				strArriere.setEnabled(false);
//				strDeltaDeplacement.setEnabled(false);
//				strCoefCourbe.setEnabled(false);
//				btnLissage.setEnabled(false);
//				btnDerapage.setEnabled(false);
//				btnAttAction.setEnabled(false);
//				break;
//			}
		}	
	}
	
	ListSelectionListener selected = new ListSelectionListener(){

        public void valueChanged(ListSelectionEvent arg0) {
        	actualRobot = list.getSelectedValue();
        	strType.setSelectedItem(actualRobot.getType());
    		strX.setText(String.valueOf(actualRobot.getX()));
    		strY.setText(String.valueOf(actualRobot.getY()));
    		strAngle.setText(String.valueOf(actualRobot.getAngle()));
    		if(actualRobot.getSens().equals("avant")) {
    			strAvant.setSelected(true);
    			strArriere.setSelected(false);
    		}else {
    			strAvant.setSelected(false);
    			strArriere.setSelected(true);
    		}
    		strDeltaAngle.setText(String.valueOf(actualRobot.getDeltaAngle()));
    		strDeltaDeplacement.setText(String.valueOf(actualRobot.getDeltaDeplacement()));
    		strVitesse.setText(String.valueOf(actualRobot.getVitesse()));
//    		strCoefCourbe.setText(String.valueOf(actualRobot.getCoefCourbe()));
//    		strBlocage.setSelectedItem(actualRobot.getBlocage());
//    		btnLissage.setSelected(actualRobot.getLissage());
//    		btnDerapage.setSelected(actualRobot.getDerapage());
//    		btnAttAction.setSelected(actualRobot.getAttAction());
    		strTimeout.setText(String.valueOf(actualRobot.getTimeOut()));
    		strAction.setText(String.valueOf(actualRobot.getAction()));
    		commentaireArea.setText(actualRobot.getCommentaire());
    		typeChange(actualRobot.getType());
    		
    		int theta=0;
			if(EditorPanel.list.getSelectedIndex()!=0 ){
				if(EditorPanel.actualRobot.getType().equals("THETA")) {
					EditorPanel.actualRobot.setX(EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex()-1).getX());
					EditorPanel.actualRobot.setY(EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex()-1).getY());
				}
				if(EditorPanel.actualRobot.getType().equals("XY_ABSOLU") || EditorPanel.actualRobot.getType().equals("DeplacementRelatif")) {
					Robot old = EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex()-1);
					Robot actual = EditorPanel.stepList.getElementAt(EditorPanel.list.getSelectedIndex());

					if(EditorPanel.actualRobot.getSens().equals("arriere")) {
						theta=(int) (Math.atan2((double)(actual.getY()-old.getY()),(double)(actual.getX()-old.getX()))*180/Math.PI)-180;
					}else {
						theta=(int) (Math.atan2((double)(actual.getY()-old.getY()),(double)(actual.getX()-old.getX()))*180/Math.PI);
					}
					EditorPanel.actualRobot.setAngle(-theta);
				}
			}
			EditorFrame.update();
        }
	 };
	 
	 private void setAnglePrec(){
		 int index= list.getSelectedIndex();
		 if(index!=0){
			 actualRobot.setAngle(stepList.getElementAt(index-1).getAngle()); 
		 } 
	 }
	 
	 private void setCoordPrec(){
		 int index= list.getSelectedIndex();
		 if(index!=0){
			 actualRobot.setX(stepList.getElementAt(list.getSelectedIndex()-1).getX()); 
			 actualRobot.setY(stepList.getElementAt(list.getSelectedIndex()-1).getY()); 
		 }
	 }
	 
	public static void updateCoord(){//Mise à jour des coordonnée pour ne pas pouvoir sortir de la table
		if(actualRobot.getAngle()<-180) actualRobot.setAngle(360-Math.abs(actualRobot.getAngle()));
		if(actualRobot.getAngle()>180) actualRobot.setAngle(Math.abs(actualRobot.getAngle()));

    	strX.setText(String.valueOf(actualRobot.getX()));
    	strY.setText(String.valueOf(actualRobot.getY()));
    	strAngle.setText(String.valueOf(actualRobot.getAngle()));

	}
	
}
