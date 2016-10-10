package br.unb.cic.lp.gol;

/**
 * Created by PedroHenrique on 08/10/2016.
 */
public class DayAndNight extends GameEngine{
    public DayAndNight(int height, int width, Statistics statistics){
        super(height, width, statistics);
    }

    @Override
    protected boolean shouldKeepAlive(int i, int j) {
        int numalive = numberOfNeighborhoodAliveCells(i, j);
        return (cells[i][j].isAlive() && (numalive == 2 || numalive == 3 || numalive == 7 || numalive == 8));
    }

    @Override
    protected boolean shouldRevive(int i, int j) {
        return (!cells[i][j].isAlive() && (numberOfNeighborhoodAliveCells(i,j) == 3));
    }
}
