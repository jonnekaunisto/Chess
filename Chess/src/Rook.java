import java.util.ArrayList;

public class Rook extends Piece {


	public Rook(int x, int y) {
		super(x, y);
	}

	public Rook(int x, int y, String side) {
		super(x, y, side);
	}
	
	
	public ArrayList<int[]> getMoveLocs(Board board) {

		for(int n = 1; n <= super.y; n++) {
			movelocs.add(new int[] {super.x, super.y - n});

			if (board.grid[super.x][super.y - n] != null) {
				break;
			}

		}
		for(int e = 1; e < 8-super.x; e++) {
			movelocs.add(new int[] {super.x + e, super.y});

			if (board.grid[super.x+e][super.y] != null) {
				break;
			}

		}
		for(int s = 1; s < 8 - super.y; s++) {
			movelocs.add(new int[] {super.x, super.y+s});

			if (board.grid[super.x][super.y+s] != null) {
				break;
			}

		}

		for(int w = 1; w <= super.x; w++) {
			movelocs.add(new int[] {super.x-w, super.y});

			if (board.grid[super.x-w][super.y] != null) {
				break;
			}

		}

		return movelocs;

	}

	





}
