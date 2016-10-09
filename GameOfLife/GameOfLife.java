import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameOfLife extends JFrame{
	private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 640);
    private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(600, 600);

    static JMenuBar MenuBar = new JMenuBar();

	public static void main(String[] args) {
		JFrame game = new GameOfLife();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setTitle("Game of Life");
		game.setSize(DEFAULT_WINDOW_SIZE);
        game.setMinimumSize(MINIMUM_WINDOW_SIZE);
        game.setVisible(true);
        game.setJMenuBar(MenuBar);

        JMenu Regras = new JMenu("Regra");
        JMenu Opcoes = new JMenu("Opcoes");

        JMenuItem Conway = new JMenuItem("Conway");
        JMenuItem HighLife = new JMenuItem("High Life");
        JMenuItem DayAndNight = new JMenuItem("Day And Night");

        JMenuItem Pausar = new JMenuItem("Pausar");
        JMenuItem Continuar = new JMenuItem("Continuar");
        JMenuItem Proximo = new JMenuItem("Proxima geracao");
        JMenuItem Anterior = new JMenuItem("Geracao anterior");
        JMenuItem Adicionar = new JMenuItem("Adicionar celula");
        JMenuItem Remover = new JMenuItem("Remover celula");
        JMenuItem Limpar = new JMenuItem("Limpar campo");
        JCheckBoxMenuItem Automatico = new JCheckBoxMenuItem("Automatico");
        JCheckBoxMenuItem Infinito = new JCheckBoxMenuItem("Grade infinita");


        MenuBar.add(Regras);
        Regras.add(Conway);
        Regras.add(HighLife);
        Regras.add(DayAndNight);

        MenuBar.add(Opcoes);
        Opcoes.add(Proximo);
        Opcoes.add(Anterior);
        Opcoes.add(Limpar);
        Opcoes.add(Adicionar);
        Opcoes.add(Remover);
        Opcoes.add(Infinito);
        Opcoes.add(Automatico);
        Opcoes.add(Pausar);
        Opcoes.add(Continuar);

	}

}
