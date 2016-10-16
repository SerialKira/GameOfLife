package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;

/**
 * Classe que atua como um controlador do 
 * padrao MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {

	private GameEngine engine;
	private GameView board;
	private Statistics statistics;
	private CareTaker caretaker;
	private Originator originator;
	
	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
		this.originator = new Originator (engine.getHeight(), engine.getWidth());
		this.caretaker= new CareTaker();
	}
	
	public GameView getBoard() {
		return board;
	}
	
	public void setBoard(GameView board) {
		this.board = board;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public void start() {
		board.update();
	}
	
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		System.out.println("\n \n");
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			board.update();
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void Undo(){
		engine.set_cells(originator.getStateFromMemento(caretaker.get()));
		board.update();
	}
	
	public void nextGeneration() {
		originator.set_revive(engine.returncells());
		caretaker.add(originator.saveStateToMemento());
		engine.nextGeneration();
		board.update();
	}
	
}
