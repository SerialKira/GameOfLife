package br.unb.cic.lp.gol;


/**
 * Created by PedroHenrique on 08/10/2016.
 */

public class HighLife implements EstrategiaDeDerivacao{
    public HighLife(){
    }

    public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
        return (engine.isCellAlive(i,j) && (engine.numberOfNeighborhoodAliveCells(i, j) == 2 || engine.numberOfNeighborhoodAliveCells(i, j) == 3));
    }

    public boolean shouldRevive(int i, int j, GameEngine engine) {
        return (!engine.isCellAlive(i,j) && (engine.numberOfNeighborhoodAliveCells(i,j) == 3 || engine.numberOfNeighborhoodAliveCells(i,j) == 6));
    }
}
