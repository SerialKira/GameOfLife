package br.unb.cic.lp.gol;

import java.util.List;

/**
 * Created by PedroHenrique on 12/10/2016.
 */
public class ListaEstrategias {
    private List<EstrategiaDeDerivacao> estrategias;

    public ListaEstrategias(){

    }

    public List<EstrategiaDeDerivacao> getRegras() {
        return estrategias;
    }

    public void setRegras(List<EstrategiaDeDerivacao> estrategias) {
        this.estrategias = estrategias;
    }
}
