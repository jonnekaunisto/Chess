public class Board{
	
	Piece[][] grid = new Piece[8][8];
	Piece selected = null;
	public String screen = "Start";
	public int[] promotepawn = new int[2];
	public boolean setUp = false;
	public String turn = "W";
	

	public Board() {
		//empty on purpose
	}
	
	
	//for testing
	public void testSetup() {
		grid[4][5] = new Pawn(4,5);
		grid[3][5] = new Pawn(3,5, "B");
	}
	
	public void normalGameSetup() {
		
		//clears board
		for(Piece[] row : grid){
			for (int i = 0; i < row.length; i++) {
				row[i] = null;
			}
		}
		
		//white side
		grid[0][7] = new Rook(0,7);
		grid[1][7] = new Knight(1,7);
		grid[2][7] = new Bishop(2,7);
		grid[3][7] = new Queen(3,7);
		grid[4][7] = new King(4,7);
		grid[5][7] = new Bishop(5,7);
		grid[6][7] = new Knight(6,7);
		grid[7][7] = new Rook(7,7);
		
		
		
		//black side
		grid[0][0] = new Rook(0,0, "B");
		grid[1][0] = new Knight(1,0,"B");
		grid[2][0] = new Bishop(2,0, "B");
		grid[3][0] = new Queen(3,0, "B");
		grid[4][0] = new King(4,0, "B");
		grid[5][0] = new Bishop(5,0, "B");
		grid[6][0] = new Knight(6,0,"B");
		grid[7][0] = new Rook(7,0, "B");
		
		//pawns
		for(int i = 0; i < 8; i++) {
			grid[i][1] = new Pawn(i, 1, "B");
			grid[i][6] = new Pawn(i, 6);
		}
		setUp = true;
		turn = "W";

	}
	
	//selects piece
	public void selectPiece(int x, int y) {
		selected = grid[x][y];
		System.out.println(selected);

	}
}
