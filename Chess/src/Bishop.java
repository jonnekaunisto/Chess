import java.util.ArrayList;

public class Bishop extends Piece {


	public Bishop(int x, int y) {
		super(x, y);
	}

	public Bishop(int x, int y, String side) {
		super(x, y, side);
	}
	
	//gets the move locations
	public ArrayList<int[]> getMoveLocs(Board board){

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

		return movelocs;
	}

}
