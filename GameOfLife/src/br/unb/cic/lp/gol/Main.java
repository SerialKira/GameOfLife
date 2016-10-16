package br.unb.cic.lp.gol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String args[]) {
		GameController controller = new GameController();
		
		Statistics statistics = new Statistics();
		ApplicationContext context = new ClassPathXmlApplicationContext("br/unb/cic/lp/gol/IDSpring.xml");
		EstrategiaDeDerivacao init = (EstrategiaDeDerivacao) context.getBean("conway");

		GameEngine engine = new GameEngine(10, 10);
		engine.setStatistics(statistics);
		engine.setStrategy(init);

		GameView board = new GameView(controller, engine);
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		controller.start();
	}
}
