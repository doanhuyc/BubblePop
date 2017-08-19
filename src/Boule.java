import java.awt.*;



public class Boule
{
	public static final int RAYON=16; // rayon de la boule
	private int couleur ;
	protected int x; //coordonnee du centre
	protected int y; 
	protected int row;
	protected int col;
	public boolean isMark=false;
	public boolean Effacer=false;
	// les constance de couleur
	private static final int Blue = 1;
	private static final int Green= 2;
	private static final int Yellow = 3;
	private static final int Pink = 4;
	private static final int Red = 5;

	
	public Boule(int row, int col, int couleur,boolean isMark,boolean Effacer){
		this.row=row;;
		this.col=col;
		// retourner une valeur random entre 1 et 5
		this.couleur= couleur;
		this.y=row*32;
		this.x=col*32;
		this.isMark=isMark;
		this.Effacer=Effacer;
	}
	public void deplacer( int dy){
        row=row+dy;
	}
	
	public void afficher(Graphics g)
	{
	
		
		
		//changer les Constance en couleur
		if(!isMark&&!Effacer){
		switch(couleur){
		case Blue : g.setColor(Color.blue);break;
		case Green : g.setColor(Color.green);break;
		case Yellow : g.setColor(Color.yellow);break;
		case Pink : g.setColor(Color.pink);break;
		case Red : g.setColor(Color.red);break;
		}
		g.fillOval((col)*32, (row)*32, RAYON*2, RAYON*2);
		g.setColor(Color.black);
		g.drawOval((col)*32, (row)*32, RAYON*2, RAYON*2);
		}
		
		
		if(isMark&&!Effacer){
			g.setColor(Color.black);
			g.fillRect((col)*32, (row)*32, RAYON*2, RAYON*2);
			switch(couleur){
			case Blue : g.setColor(Color.blue);break;
			case Green : g.setColor(Color.green);break;
			case Yellow : g.setColor(Color.yellow);break;
			case Pink : g.setColor(Color.pink);break;
			case Red : g.setColor(Color.red);break;
			}
			g.fillOval((col)*32, (row)*32, RAYON*2, RAYON*2);
			g.setColor(Color.black);
			g.drawOval((col)*32, (row)*32, RAYON*2, RAYON*2);
			}
		
		if (Effacer&&isMark){
			g.setColor(Color.white);
			g.fillRect((col)*32, (row)*32, RAYON*2, RAYON*2);
		}
	}

	
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getColor(){
		return couleur;
	}
	private int sqr(int x){ return x*x;}

	public boolean contient(int px, int py){
	    //verifier si le point px py est dans la boule
	      return sqr(px-(x+RAYON))+sqr(py-(y+RAYON)) <= (RAYON*RAYON);
	    }
	final public void setMark(boolean what) {
		if (isMark != what) {
			isMark = what;		}
	}
	final public void setEffacer(boolean what) {
		if (Effacer != what) {
			Effacer = what;		}
	}
	
	
	final public void moveTo(int drow, int dcol) {
		this.row =drow;
		this.col =dcol;
		
	}
	final public boolean isMarked() {
		return isMark;
	}

}