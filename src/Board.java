import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel
{
	JLabel p=new JLabel("Point : 0");
	JLabel b=new JLabel("Bubble Pops : 0");
	
	
	private Font font;
	//compter les boules marked
	int marked;
	
	private static final int PWIDTH=393;  //dimension du Panel
 	private static final int PHEIGHT=388;
 	
 	// les Colonnes et les lignes dans le jeu
 	private static final int ROWMAX=10;
 	private static final int COLMAX=12;
 	
 	// compter les rows et les colonnes
 	private int cols;
 	private int row;
 	
 	//les Points
 	private int Point=0;
 	private int BubblePops=0;
 	
 	private boolean running =false;
 	private boolean gameOver=false;
 	
 	private Boule[][] playground;
 	String message="Game Over";
 	Graphics g;
 	
 	
 	//Stop Game and GameOver
	public void stopGame() 
	  {  running = false;
	  	 gameOver=true;
	  }
	  
	public int getRow(){
		  return ROWMAX;
	  }
	public int getCol(){
		  return COLMAX;
	  }
	public int getPoint(){
		  return Point;
	  }
	  
	  
  	public int couleur(){
        return (int)(Math.random()*(5-1+1))+1;
    }
 	
 	
  	public Board(){
 		playground=new Boule[ROWMAX][COLMAX];
 		gameInit();
 		setLayout(new BorderLayout());
		add(Point(),BorderLayout.SOUTH);
		addMouseMotionListener(new BouleAdapter());
		addMouseListener(new BouleAdapter());
		
 	}
 	
 	public void paint(Graphics g) {
 	        super.paint(g);
 	        if(running){
 	        	for(int i=0;i<ROWMAX;i++){
 	        		for(int j=0;j<COLMAX;j++){
 	        			playground[i][j].afficher(g);
 	        		}
 	        	}
 	        if(Point>=500){
 	        	gameOverMessage(g);
 	        }
 	        repaint();
 	        }
 	  }
 	public void addNotify() {
        super.addNotify();
        gameInit();
    }
 	
 	
 	//Creer un noveau jeu en creant une tableau de Boule
 	public void gameInit(){
 		running=true;
 		Point =0;
 		BubblePops=0;
 		for(int i=0;i<ROWMAX;i++){
 			for(int j=0;j<COLMAX;j++){
 				playground[i][j]=new Boule(i,j,couleur(),false,false);
 			}
 		}
 	}
 	
 	
 	// Move une Boule a une nouvelle place
 	public void moveTo(int x, int y, int toX, int toY) {
		if (x != toX || y != toY) {
			playground[toX][toY] = playground[x][y];
			if (isEmpty(toX, toY) == false) {
				playground[toX][toY].moveTo(toX, toY);
			}
			playground[x][y] = null;
		}
	}
	// Verifier si une Boule (Row x , colonne y) existe	
	
	public boolean isEmpty(int x, int y) {
		if (x < 0 || y < 0 || x > ROWMAX-1 || y > COLMAX-1) {
			return true;
		}
		return (playground[x][y] == null);
	}
	
	/* C'est le logic du jeu
	 * Utilise recursive methode pour trouver les balls de meme couleur
	 * verifier les ball a gauche, a droit, en haut, en bas,
	 * Puis apeller FindSameMarked(row,col -1);
	 */
	
	public void findsameMarked(int row, int col) {
		if (isEmpty(row, col) == true) {
	
			return;
			
		}
		Groupe g=new Groupe();
		marked++;
		int color = playground[row][col].getColor();

		if(col<COLMAX-1){
		if (playground[row][col+1].getColor() == color && playground[row][col+1].isMarked() == false ) {
			g.add(playground[row][col]);
			g.add(playground[row][col+1]);
			playground[row][col].setMark(true);
			playground[row][col+1].setMark(true);
			findsameMarked(row, col + 1);
		}}
		if(col>=1){
		if (playground[row][col-1].getColor() == color && playground[row][col-1].isMarked() == false) {
			g.add(playground[row][col]);
			g.add(playground[row][col-1]);
			playground[row][col-1].setMark(true);
			playground[row][col].setMark(true);
			findsameMarked(row, col - 1);
			
		}}
		if(row<ROWMAX-1){
		if (playground[row+1][col].getColor() == color && playground[row+1][col].isMarked() == false) {
			g.add(playground[row][col]);
			g.add(playground[row+1][col]);
			playground[row+1][col].setMark(true);
			playground[row][col].setMark(true);
			findsameMarked(row + 1, col);
		}}
		if(row>=1){
		if (playground[row-1][col].getColor() == color && playground[row-1][col].isMarked() == false) {
			g.add(playground[row][col]);
			g.add(playground[row-1][col]);
			playground[row-1][col].setMark(true);
			playground[row][col].setMark(true);
			findsameMarked(row - 1, col);
		}}
	}
	
	
	public boolean isPlaygroundSolvable() {
		int row = ROWMAX-1;
		int col = COLMAX-1;
		while (col >= 0 && isEmpty(row, col) == false) {
			while (row >= 0 && isEmpty(row, col) == false) {
				if (isEmpty(row - 1, col) == false && playground[row][col].getColor() == playground[row-1][col].getColor()) {
					return true;
				}
				if (isEmpty(row, col - 1) == false && playground[row][col].getColor() == playground[row][col-1].getColor()) {
					return true;
				}
				row--;
			}
			col--;
			row = ROWMAX - 1;
		}
		return false;
	}
	
	



	//class JPanel pour afficher les Points
	
	private  JPanel Point(){

		JPanel n = new JPanel();
		n.setLayout(new FlowLayout());
		n.add(p);
		n.add(b);
		n.setBorder(BorderFactory.createLoweredBevelBorder());
		return n;
	}
	  
	//print a Game Over Message on the screen
	private void gameOverMessage(Graphics g)
	  {
	    gameOver=true;
		String msg = "Game Over. Your Score: " + Point;
	    g.setColor(Color.black);
	    g.setFont(font);
		g.drawString(msg, PWIDTH/2, PHEIGHT/2);
	  }  // end of gameOverMessage()
	
	
	  
	  
	
	  
	  class BouleAdapter extends MouseAdapter implements MouseMotionListener{
	     
	  public void mouseMoved(MouseEvent e)
	  {
		
		  UnMarkedAll();
		  int x=e.getX();
		  int y=e.getY();

		for(int i=0;i<ROWMAX;i++){
			  for(int j=0;j<COLMAX;j++){
				  if(playground[i][j].contient(x, y)){
					  findsameMarked(playground[i][j].getRow(),playground[i][j].getCol());
					  BubblePops =marked*(marked-1);
					  b.setText("BubblePops : "+BubblePops);
					  marked=0;
				  }
				 }
			  }
	  }
	 //Quand on click a` une autre boules , on unmarked les autres boules
	  public void UnMarkedAll(){
		  for(int i=0;i<ROWMAX;i++){
			  for(int j=0;j<COLMAX;j++){
				  playground[i][j].setMark(false);
				  //playground[i][j].setEffacer(false);
			  }
		  }
	  }
	 
	  public void mousePressed(MouseEvent e){
		  
		  Point=Point + BubblePops;
		  p.setText("Point : "+Point);
		  int x=e.getX();
		  int y=e.getY();

		for(int i=0;i<ROWMAX;i++){
			  for(int j=0;j<COLMAX;j++){
				  if(playground[i][j].contient(x, y)){
					 playground[i][j].setEffacer(true);
				  }
										  
				  }
				 }
			  }
	  }
	  
	       
	        
}
	  
	 
	  

 	
 	
 	
