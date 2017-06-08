import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;


public class Display extends JApplet{

	private static final long serialVersionUID = 1L;
	public int height = 700;
	public int width = 700;
	public int x;
	public Board board;
	public JFrame frame;
	Color blue = new Color(0, 204, 255, 150);
	Color red = new Color(255, 80, 80,150);
	Color darkb = new Color(118, 73, 15);
	Color lightb = new Color (235, 173, 92);
	Image background = new ImageIcon(getClass().getResource("/Chess_Pieces/background.png")).getImage();
	Image menu = new ImageIcon(getClass().getResource("/Chess_Pieces/menu.png")).getImage();
	Image endscreenWloses = new ImageIcon(getClass().getResource("/Chess_Pieces/WLoses.png")).getImage();
	Image endscreenBloses = new ImageIcon(getClass().getResource("/Chess_Pieces/BLoses.png")).getImage();
	Image credits = new ImageIcon(getClass().getResource("/Chess_Pieces/credits.png")).getImage();
	Image choose = new ImageIcon(getClass().getResource("/Chess_Pieces/choose.png")).getImage();
	Image iconW = new ImageIcon(getClass().getResource("/Chess_Pieces/IconW.png")).getImage();
	Image iconB = new ImageIcon(getClass().getResource("/Chess_Pieces/IconB.png")).getImage();




	public Display(Board board, JFrame frame) {
		this.board = board;
		this.frame = frame;
		//sets the icon for the program
		frame.setIconImage(iconW);

	}
	public void init() {
		setSize(700, 700);
	}


	public void paint(Graphics g) {
		width = getWidth();
		height = getHeight();

		setSize(height, height);
		
		//checks which screen the program is in
		if(board.screen == "Game"){
			inGameScreen(g);
			
		}
		else if(board.screen.equals("Start")){
			inStartScreen(g);

		}
		else if(board.screen.equals("WLoses")){
			g.drawImage(endscreenWloses,0, 0, null);
		}
		else if(board.screen.equals("BLoses")){
			g.drawImage(endscreenBloses,0, 0, null);
		}
		else if(board.screen.equals("Credits")){
			g.drawImage(credits,0, 0, null);
			
		}
		else if(board.screen.equals("Choose")){
			g.drawImage(background,0, 0, null);
			g.setColor(Color.RED);
			//g.fillRect(100, 270, 500, 100);
			//g.fillRect(100, 400, 500, 100);
			//g.fillRect(100, 530, 500, 100);
			//g.fillRect(100, 125, 500, 100);
			g.drawImage(choose,0, 0, null);
			
		}
		
		if(board.turn == "W"){
			frame.setIconImage(iconW);
		}
		else{
			frame.setIconImage(iconB);
		}


	}

	private void inStartScreen(Graphics g){
		g.drawImage(background,0, 0, null);
		//g.setColor(Color.RED);
		//g.fillRect(100, 270, 500, 100);
		//g.fillRect(100, 400, 500, 100);
		//g.fillRect(100, 530, 500, 100);
		g.drawImage(menu, 0, 0, null);
	}
	
	

	private void inGameScreen(Graphics g){
		drawBoard(g);

		if(board.selected != null) {
			board.selected.movelocs.clear();
			showselected(g, board.selected.getMoveLocs(board));
			board.selected.movelocs.clear();
		}

		updatePieces(g);
	}

	//dra
	private void showselected(Graphics g, ArrayList<int[]> selected) {
		for(int[] loc : selected) {
			if((board.grid[loc[0]][loc[1]] == null || board.grid[loc[0]][loc[1]].side != board.selected.side)){
				if(board.grid[loc[0]][loc[1]] != null && board.grid[loc[0]][loc[1]].side != board.selected.side)
					g.setColor(red);
				else
					g.setColor(blue);

				g.fillRect(loc[0] * height / 8, loc[1] * height / 8, height/8 + 1, height/8 + 1);
				
			}
		}
	}


	private void drawBoard(Graphics g) {
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if((i + j) % 2 == 0) {
					g.setColor(darkb);

				}else {

					g.setColor(lightb);
				}
				g.fillRect(i * height / 8, j * height / 8, height/8+2, height/8+2);
			}
		}

	}
	public void updatePieces(Graphics g) {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.grid[i][j] != null) {

					board.grid[i][j].draw(g, height);

				}
			}
		}

	}
	

}