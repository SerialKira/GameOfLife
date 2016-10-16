package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * Essa implementacao eh nao inifinita, ou seja, nem todas as celulas possuem
 * oito celulas vizinhas. Por exemplo, a celula de coordenada (0,0) possui
 * apenas tres celulas vizinhas, (0,1), (1,0) e (1,1).
 * 
 * Um ambiente eh representado como um array bidimensional de celulas, com
 * altura (height) e comprimento (width).
 * 
 * @author rbonifacio
 */
public class GameEngine {
	private int height;
	private int width;
	private Cell[][] cells;
	private Statistics statistics;
	private EstrategiaDeDerivacao strategy;
	private Cell[][] newstate;

	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimentsao horizontal do ambiente
	 */
	public GameEngine(int height, int width, Cell[][] newcell) {
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}
		set_cells(newcell);
	}
	
	public GameEngine(int height, int width) {
		this.height = height;
		this.width = width;
		cells = new Cell[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}
	}
	
	public void setStrategy(EstrategiaDeDerivacao strategy){
		this.strategy = strategy;
	}

	public EstrategiaDeDerivacao getStrategy(){
		return strategy;
	}

	public void setStatistics(Statistics statistics){
		this.statistics = statistics;
	}

	public Statistics getStatistics(){
		return statistics;
	}

	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
	 * algoritmo do Conway, ou seja:
	 * 
	 * a) uma celula morta com exatamente tres celulas vizinhas vivas se torna
	 * uma celula viva.
	 * 
	 * b) uma celula viva com duas ou tres celulas vizinhas vivas permanece
	 * viva.
	 * 
	 * c) em todos os outros casos a celula morre ou continua morta.
	 */
	public final void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (strategy.shouldRevive(i, j,this)) {
					mustRevive.add(this.cells[i][j]);
				} 
				else if ((!strategy.shouldKeepAlive(i, j, this)) && cells[i][j].isAlive()) {
					mustKill.add(this.cells[i][j]);
				}
			}
		}
		
		for (Cell cell : mustRevive) {
			cell.revive();
			statistics.recordRevive();
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
			statistics.recordKill();
		}
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].revive();
			statistics.recordRevive();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			return cells[i][j].isAlive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	public int numberOfNeighborhoodAliveCells(int i, int j) { //i= altura, j=largura
		int alive = 0;
		if(i==0 && j==0){
			if((cells[i+1][j].isAlive())){
				alive++;
				}
			if((cells[i+1][j+1].isAlive())){
				alive++;
				}
			if((cells[i][j+1].isAlive())){
				alive++;
				}
			if((cells[height-1][j].isAlive())){
				alive++;
				}
			if((cells[height-1][j+1].isAlive())){
				alive++;
				}
			if((cells[i][width-1].isAlive())){
				alive++;
				}
			if((cells[i+1][width-1].isAlive())){
				alive++;
				}
			if((cells[height-1][width-1].isAlive())){
				alive++;
				}
		}
			if(i==(height-1) && j==0){
				if((cells[i-1][j].isAlive())){
					alive++;
					}
				if((cells[i][j+1].isAlive())){
					alive++;
					}
				if((cells[i-1][j+1].isAlive())){
					alive++;
					}
				if((cells[height-1][width-1].isAlive())){
					alive++;
					}
				if((cells[height-1][width-2].isAlive())){
					alive++;
					}
				if((cells[0][width-1].isAlive())){
					alive++;
					}
				if((cells[0][0].isAlive())){
					alive++;
					}
				if((cells[0][1].isAlive())){
					alive++;
					}
			}
				if(i==(height-1) && j==(width-1)){
					if((cells[i-1][j].isAlive())){
						alive++;
						}
					if((cells[i-1][j-1].isAlive())){
						alive++;
						}
					if((cells[i][j-1].isAlive())){
						alive++;
						}
					if((cells[0][width-1].isAlive())){
						alive++;
						}
					if((cells[0][width-2].isAlive())){
						alive++;
						}
					if((cells[0][0].isAlive())){
						alive++;
						}
					if((cells[height-1][0].isAlive())){
						alive++;
						}
					if((cells[height-2][0].isAlive())){
						alive++;
						}
				}
					if(i==(0) && j==(width-1)){
						if((cells[i][j-1].isAlive())){
							alive++;
							}
						if((cells[i+1][j].isAlive())){
							alive++;
							}
						if((cells[i+1][j-1].isAlive())){
							alive++;
							}
						if((cells[i][0].isAlive())){
							alive++;
							}
						if((cells[i+1][0].isAlive())){
							alive++;
							}
						if((cells[height-1][0].isAlive())){
							alive++;
							}
						if((cells[height-1][width-1].isAlive())){
							alive++;
							}
						if((cells[height-1][width-2].isAlive())){
							alive++;
							}
					}
		
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
					alive++;
				}
				
			
			}
		}
		
		
		return alive;
	}

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	public boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}

	/* Metodos de acesso as propriedades height e width */
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public Cell[][] returncells(){
		return cells;
	}
	
	public void set_cells (Cell[][] newstate){
		this.newstate= newstate;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(newstate[i][j].isAlive()){
					cells[i][j].revive();
				}
				else{
					cells[i][j].kill();
				}
			}
		}
	}
}
