import java.util.ArrayList;

public class King extends Piece {
	public int[][] rmoveLocs = 
		{
				{-1, -1},
				{-1, 0},
				{-1, 1},
				{0, 1},
				{0, -1},
				{1, -1},
				{1, 0},
				{1, 1}
		};
	
	public King(int x, int y) {
		super(x, y);


	}

	public King(int x, int y, String side) {
		super(x, y, side);


	}
	
	@Override
	public void move(int i, int j, Board board) {
		
		//for King side castling
		if(firstTurn && i-x == 2){
			board.grid[x+1][y] = board.grid[x+3][y];
			board.grid[x+3][y] = null;
			board.grid[x+1][y].x = x+1;
			board.grid[x+1][y].movelocs.clear();
			board.grid[x+1][y].firstTurn = false;
			
			board.grid[i][j] = board.grid[x][y];
			board.grid[x][y] = null;
			x = i;
			y = j;
			movelocs.clear();
			firstTurn = false;
			
			
			
		}
		//Queen side Castling
		else if(firstTurn && i-x == -2){
			board.grid[x-1][y] = board.grid[0][y];
			board.grid[0][y] = null;
			board.grid[x-1][y].x = x-1;
			board.grid[x-1][y].movelocs.clear();
			board.grid[x-1][y].firstTurn = false;
			
			board.grid[i][j] = board.grid[x][y];
			board.grid[x][y] = null;
			x = i;
			y = j;
			movelocs.clear();
			firstTurn = false;
			
			
			
		}
		//normal move
		else if (validMove(i, j, board)) {
			if(board.grid[i][j] != null) board.grid[i][j].kill(board);
			board.grid[i][j] = board.grid[x][y];
			board.grid[x][y] = null;
			x = i;
			y = j;
			movelocs.clear();
			firstTurn = false;
		}

	}
	
	public ArrayList<int[]> getMoveLocs(Board board) {
		
		//king side castle
		if(canCastleK(board)){
			movelocs.add(new int[] {x+2, y});
		}
		
		if(canCastleQ(board)){
			movelocs.add(new int[] {x-2, y});		
		}
		
		for(int i = 0; i < 8; i++) {
			if(appropriate(board, x + rmoveLocs[i][0], y + rmoveLocs[i][1])) {
			movelocs.add(new int[] {x + rmoveLocs[i][0],  y + rmoveLocs[i][1]});
		}
		}
		return movelocs;
	}

	private boolean canCastleQ(Board board) {
		return (firstTurn && board.grid[0][y] != null && board.grid[0][y].firstTurn && board.grid[x-2][y] == null && board.grid[x-1][y] == null);
		
	}

	private boolean canCastleK(Board board) {
		return (firstTurn && board.grid[x+3][y] != null && board.grid[x+3][y].firstTurn && board.grid[x+2][y] == null && board.grid[x+1][y] == null);
		
	}
	
	@Override
	public void kill(Board board){
		board.screen = side + "Loses";
		board.setUp = false;
		if(side == "W"){
			board.turn = "B";
		}
		
	}
	
}
