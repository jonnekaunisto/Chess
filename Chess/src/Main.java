//(c) Jonne Kaunisto, Naveen Ram, Nico Diaz-Wahl 2017
//Chess, Extra credit Project

import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main{
	static Board board = new Board(); //stores everything related to what is supposed to be displayed
	static JFrame frame = new JFrame("Chess"); //the actual frame that the JApplet is on
	static Display disp =  new Display(board, frame); //draws everything
	
	public static void main(String[] args) {
		setup();

		//if esc is pressed returns back to start menu
		frame.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0){

				if(board.screen.equals("Start")){}
				else if((int) arg0.getKeyChar() == 27){
					board.screen = "Start";
					disp.repaint();
				}
				
			}
		});
		
		//registers clicks on the board and navigates through the program
		disp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				
				//gets the square that is clicked on
				int x = (int) Math.floor(me.getX() / (disp.height / 8));
				int y = (int) Math.floor(me.getY() / (disp.height / 8));
				System.out.println("Clicked: (" + x + ", " + y + ")");
				
				//figures out which screen the program is on
				if(board.screen.equals("Game")) {
					inGameScreen(me, x, y);
					disp.repaint();
				}
				else if (board.screen.equals("Start")){
					inStartScreen(me);
				}
				else if (board.screen.equals("WLoses")){
					board.screen = "Start";
					disp.repaint();
				}
				else if (board.screen.equals("BLoses")){
					board.screen = "Start";
					disp.repaint();
				}
				else if (board.screen.equals("Credits")){
					board.screen = "Start";
					disp.repaint();
				}
				else if (board.screen.equals("Choose")){
					inChooseScreen(me);
					disp.repaint();
				}
				
			
				
			
			}

		});
	}
	
	
	//sets up the window
	private static void setup(){
		disp.setVisible(true); //makes screen visible
		 //makes a frame called chess
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
		frame.add(disp); //puts the japplet on to a frame
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setSize(678, 700);
		frame.setResizable(false);
		//frame.setIconImage(icon);
	}
	
	//if pawn is promoted then ask which piece the player wants to promote it to
	private static void inChooseScreen(MouseEvent me){
		int x = me.getX();
		int y = me.getY();
		int pawnx = board.promotepawn[0];
		int pawny = board.promotepawn[1];
		String pawnSide = board.grid[pawnx][pawny].side;
		
		
		
		if(x >= 100 && x <= 600 && y >= 270 && y <= 370){
			board.screen = "Game";
			board.grid[pawnx][pawny] = new Knight(pawnx, pawny, pawnSide);
			board.grid[pawnx][pawny].firstTurn = false;
			disp.repaint();
		}
		else if(x >= 100 && x <= 600 && y >= 400 && y <= 500){
			board.screen = "Game";
			board.grid[pawnx][pawny] = new Rook(pawnx, pawny, pawnSide);
			board.grid[pawnx][pawny].firstTurn = false;
			disp.repaint();
		}
		else if(x >= 100 && x <= 600 && y >= 530 && y <= 630){
			board.screen = "Game";
			board.grid[pawnx][pawny] = new Bishop(pawnx, pawny, pawnSide);
			board.grid[pawnx][pawny].firstTurn = false;
			disp.repaint();
		}
		else if(x >= 100 && x <= 600 && y >= 125 && y <= 225){
			board.screen = "Game";
			board.grid[pawnx][pawny] = new Queen(pawnx, pawny, pawnSide);
			board.grid[pawnx][pawny].firstTurn = false;
			disp.repaint();
		}
		
		
		
	}
	
	//goes to the appropriate screen from the menu
	protected static void inStartScreen(MouseEvent me){
		int x = me.getX();
		int y = me.getY();
		if(x >= 100 && x <= 600 && y >= 270 && y <= 370){
			board.screen = "Game";
			board.normalGameSetup();
			//board.testSetup(); //only for testing
			disp.repaint();
		}
		else if(x >= 100 && x <= 600 && y >= 400 && y <= 500){
			if(board.setUp){
			board.screen = "Game";
			disp.repaint();
			
			}
		}
		else if(x >= 100 && x <= 600 && y >= 530 && y <= 630){
			board.screen = "Credits";
			disp.repaint();
		}
		
		
		
		
	}
	
 
	//logic for chess mouse clicks
	protected static void inGameScreen(MouseEvent me, int x, int y) {
		
		//if not clicked on any piece
		if (board.selected == null) {

			//If clicked on piece, then set it as selected
			System.out.println(x + "," + y);
			if (board.grid[x][y] != null && board.grid[x][y].side == board.turn) {
				board.selectPiece(x, y);
				System.out.print("selected: ");
				System.out.println(board.selected.getClass().getName());
			}

		//if a piece has already been clicked
		} else {
			
			//if clicked on selected piece, deselect it
			if (board.grid[x][y] == (board.selected)) {
				board.selected = null;
			
			//check if move location is valid and then move the piece
			} else if (board.selected.validMove(x, y, board) && (board.grid[x][y] == null || board.selected.side != board.grid[x][y].side)) {
				
				System.out.print(board.selected.getClass().getName());
				System.out.println(" Moves: (" + x + ", " + y + ")");
				board.selected.move(x, y, board);
				
				//makes every piece have false enPassant value
				for(Piece[] row : board.grid){
					for(Piece piece : row){
						if(piece != null && piece != board.selected){
							piece.enPassant = false;
						}
					}
				}
				
				//nothing is selected after
				board.selected = null;
				
				//changes the turn
				if (board.turn == "W") {
					board.turn = "B";
				} else if (board.turn == "B") {
					board.turn = "W";
				}
				//nothing is clicked
				
			//if the piece clicked is on same side then select that piece
			} else if (board.selected != null && board.grid[x][y] != null && board.grid[x][y].side == board.selected.side) {
				board.selected = board.grid[x][y];
				
			}
			
		}

		
	}

}