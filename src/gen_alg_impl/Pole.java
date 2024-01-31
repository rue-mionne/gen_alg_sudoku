package gen_alg_impl;

import gen_alg.Allele;

public class Pole extends Allele {
    int wartosc;
    @Override
    public Double evaluateAlleleValue() {
        return null;
    }

    @Override
    protected Allele generateNewAllele() throws Exception {
        Pole pole = new Pole();
        pole.setNewVal(1+(int)(Math.random()*9));
        return pole;
    }

    public void setNewVal(int val){
        wartosc=val;
    }

    public int getVal(){
        return wartosc;
    }
}
