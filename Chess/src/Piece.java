import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public abstract class Piece {
	public String side;
	public int x;
	public int y;
	public ArrayList < int[] > movelocs = new ArrayList < int[] > ();
	public Image buffered;
	public boolean firstTurn = true;
	public int heading; //which way a piece is going
	public boolean enPassant = false;
	File sound = new File("/Chess_Pieces/chessound.WAV");

	public Piece(int x, int y) {
		this.x = x;
		this.y = y;
		side = "W";
		setImage();
		heading = -1;
	}
	
	
	public Piece(int x, int y, String side) {
		this.x = x;
		this.y = y;
		this.side = side;
		setImage();
		if(side == "W") {
			heading = -1;
		}
		else{
			heading = 1;
		}
	}
	
	//loads the correct image for piece
	private void setImage() {
		buffered = new ImageIcon(getClass().getResource("/Chess_Pieces/" + this.getClass().getSimpleName() + "_" + this.side + ".png")).getImage();

	}
	
	//draws the image at the current location of piece
	public void draw(Graphics g, int height) {
		g.drawImage(buffered, (x * height / 8) + ((height / 8) - buffered.getWidth(null)) / 2, (y * height / 8) + ((height / 8) - buffered.getHeight(null)) / 2, null);

	}

	//moves piece
	public void move(int i, int j, Board board) {

		if (validMove(i, j, board)) {
			if(board.grid[i][j] != null) board.grid[i][j].kill(board);
			
//			board.grid[i][j] = board.grid[x][y];
//			board.grid[x][y] = null;
//			x = i;
//			y = j;
			movePiece(x, y , i, j, board);
			movelocs.clear();
			firstTurn = false;
			
			playSound();
			
		}

	}
	
	//checks if move is valid
	public boolean validMove(int i, int j, Board board) {

		getMoveLocs(board);

		for (int[] a: movelocs) {
			if (a[0] == i && a[1] == j) {

				return true;
			}
		}

		return false;
	}
	
	
	//does not work
	public void playSound(){
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			//Thread.sleep(clip.getMicrosecondLength()/1000);
		}catch(Exception e){
			//System.out.println("crash");
		}
	}

	
	//defined in each subclass
	public abstract ArrayList < int[] > getMoveLocs(Board board);


	public boolean inGrid(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			return false;
		}
		return true;
	}
	
	
	public boolean notOnMyPiece(Board board, int x, int y) {
		if (board.grid[x][y] != null && board.grid[x][y].side == side) {
			return false;
		}
		return true;
	}
	
	public void kill(Board board){
		//only implemented for King class, to end the game
	}
	
	protected void movePiece(int currentx, int currenty, int x, int y, Board board){
		board.grid[x][y] = board.grid[currentx][currenty];
		board.grid[currentx][currenty] = null;
		this.x = x;
		this.y = y;
	}

	public boolean appropriate(Board board, int x, int y) {
		return inGrid(x, y) && notOnMyPiece(board, x, y);
	}

	public String toString() {
		return "( " + x + ", " + y + ")";
	}

}