import java.util.ArrayList;

public class Queen extends Piece {


	public Queen(int x, int y) {
		super(x, y);
	}

	public Queen(int x, int y, String side) {
		super(x, y, side);
	}

	
	public ArrayList<int[]> getMoveLocs(Board board) {

		for(int ne = 1; ne <= Math.min(super.y, 7 - super.x); ne++){
			movelocs.add(new int[] {super.x + ne, super.y - ne});

			if (board.grid[super.x + ne][super.y - ne] != null) {
				break;
			}

		}
		for(int se = 1; se < Math.min(8- super.y, 8 - super.x); se++){
			movelocs.add(new int[] {super.x + se, super.y + se});

			if (board.grid[super.x + se][super.y + se] != null) {
				break;
			}

		}
		for(int sw = 1; sw <= Math.min(7 - super.y, super.x); sw++){
			movelocs.add(new int[] {super.x - sw, super.y + sw});

			if (board.grid[super.x - sw][super.y + sw] != null) {
				break;
			}

		}

		for(int nw = 1; nw <= Math.min(super.y, super.x); nw++){
			movelocs.add(new int[] {super.x-nw, super.y - nw});

			if (board.grid[super.x-nw][super.y - nw] != null) {
				break;
			}

		}
		
		//vertical/horizontal
		
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
