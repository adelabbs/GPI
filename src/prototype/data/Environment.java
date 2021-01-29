package prototype.data;

public class Environment {
	
	private int line;
	private int column;
	private int [][] map;
	
	public Environment(int line, int column) {
		this.line= line;
		this.column=column;
		map = new int[line][column];
		
		for(int i=0; i< this.line; i++) {
			for(int j=0; j< this.column; j++) {
				this.map[i][j]= 0;
			}
		}
		
	}
	
}