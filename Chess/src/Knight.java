import java.util.ArrayList;

public class Knight extends Piece {
	
	public int[][] rmoveLocs = 
		{
				{1, 2},
				{2, 1},
				{2, -1},
				{1, -2},
				{-1, -2},
				{-2, -1},
				{-2, 1},
				{-1, 2}
		};
	
	public Knight(int x, int y) {
		super(x, y);


	}

	public Knight(int x, int y, String side) {
		super(x, y, side);


	}
	public ArrayList<int[]> getMoveLocs(Board board) {
		
		for(int i = 0; i < 8; i++) {
			if(inGrid(x + rmoveLocs[i][0], y + rmoveLocs[i][1])) {
				movelocs.add(new int[] {x + rmoveLocs[i][0],  y + rmoveLocs[i][1]});
		}
		}
		return movelocs;
	}

}
