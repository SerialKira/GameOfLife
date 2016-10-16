package br.unb.cic.lp.gol;

public class Memento  {
	private Cell[][] state;
	private int height;
	private int width;
	
	public Memento(Cell[][] state, int  height, int width){
		this.state= new Cell[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.state[i][j]= new Cell();
						if(state[i][j].isAlive()){
							this.state[i][j].revive();
					}
				}
		}
		
	}
	public Cell[][] getState(){
		return state;
	}
}
