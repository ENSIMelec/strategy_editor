
public class Robot {

	private String type;
	private int x;
	private int y;
	private int angle;
	private int deltaDeplacement;
	private double deltaAngle;
	private int vitesse;
	private String sens;
	private String blocage;
	private double coefCourbe;
	private boolean lissage;
	private boolean derapage;
	private int timeOut;
	private String action;
	private boolean attAction;
	private String commentaire;
	
	
	
	
	public Robot(){
		type = "POSITION";
		x=144;
		y=824;
		angle=0;
		deltaDeplacement=10;
		deltaAngle=1;
		vitesse=400;
		sens="avant";
		blocage="aucun";
		coefCourbe=0;
		lissage=false;
		derapage=false;
		timeOut=0;
		action="null";
		attAction=false;
		commentaire="";
	}
	public Robot(int x, int y, int r){
		type = "POSITION";
		this.x=x;
		this.y=y;
		angle=r;
		deltaDeplacement=10;
		deltaAngle=1;
		vitesse=400;
		sens="avant";
		blocage="aucun";
		coefCourbe=0;
		lissage=false;
		derapage=false;
		timeOut=0;
		action="null";
		attAction=false;
		commentaire="";
	}
	
	public Robot(Robot r) {
		type= r.type;
		x= r.x;
		y= r.y;
		angle= r.angle;
		deltaDeplacement= r.deltaDeplacement;
		deltaAngle= r.deltaAngle;
		vitesse= r.vitesse;
		sens= r.sens;
		blocage= r.blocage;
		coefCourbe= r.coefCourbe;
		lissage= r.lissage;
		derapage= r.derapage;
		timeOut= r.timeOut;
		action= "null";
		attAction= false;
		commentaire="";
	}
	
	int getX(){
		return x;
	}
	void setX(int x){
		this.x = x;
	}
	
	/*int getDispX(){
		return (getX()*3/10)+405;
	}
	int getDispY(){
		return  541-(getY()*3/10);
	}*/

	int getDispX(){
		return ((getX()*3/10)-46);
	}
	int getDispY(){
		return  ((getY()*3/10)-59);
	}
	
	/*static int dispXToReal(int x){
		if(Main.robot.equals("roballs")) {
			return (x*10/3)-1500;
		}else {
			return 0;
			//TODO coord X bobby
		}
	}

	static int dispYToReal(int y){
		if(Main.robot.equals("roballs")) {
			return 2000-(y*10/3);
		}else {
			return 0;
			//TODO coord Y bobby
		}
	}*/
	static int dispXToReal(int x){

		if(Main.robot.equals("roballs")) {
			return (x*10/3);
		}else {
			return 0;
			//TODO coord X bobby
		}
	}

	static int dispYToReal(int y){
		if(Main.robot.equals("roballs")) {
			return (y*10/3)-2000;
		}else {
			return 0;
			//TODO coord Y bobby
		}
	}
	
	int getY(){
		return -y;
	}
	void setY(int y){
		this.y = y;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public void setAngle(int a) {
		angle = a;
	}

	public int getRealAngle() {
		return (angle+90);
	}
	/*public int getRealAngle() {
		return (angle);
	}*/
	
	String getType(){
		return type;
	}
	
	void setType(String c){
		type = c;
	}
	
	int getVitesse(){
		return vitesse;
	}
	void setVitesse(int v){
		vitesse = v;
	}
	
	
	String getSens(){
		return sens;
	}	
	
	void setSens(String s){
		sens = s;
	}	
	
	boolean getAttAction(){
		return attAction;
	}
	
	void setAttAction(boolean a){
		attAction = a;
	}	
	
	boolean getDerapage(){
		return derapage;
	}
	
	void setDerapage(boolean b){
		derapage=b;
	}
	
	
	boolean getLissage(){
		return lissage;
	}
	
	void setLissage(boolean b){
		lissage = b;
	}
	
	double getCoefCourbe(){
		return coefCourbe;
	}
	
	void setCoefCourbe(double d){
		coefCourbe = d;
	}
	
	int getDeltaDeplacement(){
		return deltaDeplacement;
	}
	
	void setDeltaDeplacement(int d){
		deltaDeplacement = d;
	}
	
	double getDeltaAngle(){
		return deltaAngle;
	}
	
	void setDeltaAngle(double d){
		deltaAngle = d;
	}
	
	void setTimeOut(int i) {
		timeOut = i;
	}
	
	int getTimeOut() {
		return timeOut;
	}
	
	String getAction() {
		return action;
	}
	
	void setAction(String str) {
		action = str;
	}
	
	String getCommentaire() {
		return commentaire;
	}
	
	void setCommentaire(String str) {
		commentaire = str;
	}
	
	String getBlocage() {
		return blocage;
	}
	
	void setBlocage(String str) {
		blocage = str;
	}

	@Override
	public String toString() {
		return "Robot{" +
				"type='" + type + '\'' +
				'}';
	}
}
