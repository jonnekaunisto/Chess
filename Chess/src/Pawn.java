import java.util.ArrayList;

public class Pawn extends Piece {
	
	public Pawn(int x, int y) {
		super(x, y);
	
	}
	
	public Pawn(int x, int y, String side) {
		super(x, y, side);
		
	}
	
	@Override
	public void move(int i, int j, Board board) {

		if (Math.abs(i-x) == 1 && board.grid[i][j] == null){
			board.grid[i][j] = board.grid[x][y];
			board.grid[x][y] = null;
			x = i;
			y = j;
			movelocs.clear();
			firstTurn = false;
			
			board.grid[i][j-heading] = null;
		}
		
		//when pawn reaches the end
		else if(j == 7 || j == 0){
			if(board.grid[i][j] != null) board.grid[i][j].kill(board);
			board.grid[i][j] = board.grid[x][y];
			board.grid[x][y] = null;
			
			board.screen = "Choose";
			board.promotepawn = new int[] {i , j};
		}
		else if (validMove(i, j, board)) {
			
			if(Math.abs(y - j) == 2){
				enPassant = true;
			}
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
		boolean ahead = true;
		
		if(inGrid(super.x - 1,super.y + heading) && board.grid[super.x - 1][super.y + heading] != null && board.grid[super.x - 1][super.y + heading].side != side) {
			movelocs.add(new int[] {super.x-1, super.y +heading});
			ahead = false;
		}
		if(inGrid(super.x + 1,super.y + heading) && board.grid[super.x + 1][super.y + heading] != null && board.grid[super.x + 1][super.y + heading].side != side) {
			movelocs.add(new int[] {super.x + 1, super.y + heading});
			ahead = false;
		}
		if(ahead && inGrid(super.x,super.y + heading) && board.grid[super.x][super.y + heading] == null) {
			movelocs.add(new int[] {super.x, super.y + heading});
		}
		if(ahead && firstTurn && board.grid[super.x][super.y + heading] == null && board.grid[super.x][super.y + 2* heading] == null) {
			movelocs.add(new int[] {super.x, super.y + heading * 2});
		}
		
		if(inGrid(x-1, y) && board.grid[x-1][y] != null && board.grid[x-1][y].enPassant == true){
			movelocs.add(new int[] {x -1, y + heading});
		}
		if(inGrid(x+1, y) && board.grid[x+1][y] != null && board.grid[x+1][y].enPassant == true){
			movelocs.add(new int[] {x + 1, y + heading});
		}
		
		
		return movelocs;
	}

}
