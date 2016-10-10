package br.unb.cic.lp.gol;

import com.sun.org.glassfish.external.statistics.Statistic;

/**
 * Created by PedroHenrique on 08/10/2016.
 */
public class Conway extends GameEngine  {
    public Conway(int height, int width, Statistics statistics){
        super(height, width, statistics);
    }

    @Override
    protected boolean shouldKeepAlive(int i, int j) {
        return (cells[i][j].isAlive() && (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3));
    }

    @Override
    protected boolean shouldRevive(int i, int j) {
        return (!cells[i][j].isAlive() && (numberOfNeighborhoodAliveCells(i,j) == 3));
    }
}
