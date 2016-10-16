package br.unb.cic.lp.gol;

/**
 * Created by PedroHenrique on 08/10/2016.
 */

public class DayAndNight implements EstrategiaDeDerivacao{
    public DayAndNight(){
    }

    public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
        int numalive = engine.numberOfNeighborhoodAliveCells(i, j);
        return (engine.isCellAlive(i,j) && (numalive == 3 || numalive == 4 || numalive == 6 || numalive == 7 || numalive == 8));
    }

    public boolean shouldRevive(int i, int j, GameEngine engine) {
        int numalive = engine.numberOfNeighborhoodAliveCells(i, j);
        return (!engine.isCellAlive(i,j) && (numalive == 3 || numalive == 6 || numalive == 7 || numalive == 8));
    }
}
