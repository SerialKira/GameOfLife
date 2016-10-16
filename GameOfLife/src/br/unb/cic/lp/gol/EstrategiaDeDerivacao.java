package br.unb.cic.lp.gol;

/**
 * Created by PedroHenrique on 12/10/2016.
 */
public interface EstrategiaDeDerivacao {

    /* verifica se uma celula deve ser mantida viva */
    boolean shouldKeepAlive(int i, int j, GameEngine engine);

    /* verifica se uma celula deve (re)nascer */
    boolean shouldRevive(int i, int j, GameEngine engine);

}
