package gen_alg_impl;

import gen_alg.Allele;
import gen_alg.Specimen;

import java.util.ArrayList;
import java.util.Arrays;

public class SudokuRozw extends Specimen {
    ArrayList<Integer> staleKraty;
    Pole templ=new Pole();

    public SudokuRozw(Allele template, ArrayList<Integer> stalePola) {
        super(template);
        staleKraty=stalePola;
    }

    @Override
    public Double evaluateGenomeValue() {
        OverwriteOnSetFields();
        Double liczbaKonfliktow=0.0;
        liczbaKonfliktow+=sprawdzKonfliktyPion(getAlleles());
        liczbaKonfliktow+=sprawdzKonfliktyPoziom(getAlleles());
        liczbaKonfliktow+=sprawdzKonfliktyKrata(getAlleles());
        super.score=liczbaKonfliktow;
        return liczbaKonfliktow;
    }

    @Override
    public boolean heuCheck() {
        return super.score == 0;
    }

    @Override
    public Specimen generateNewSpecimen() throws Exception {
        SudokuRozw noweRozwiazanie = new SudokuRozw(templ,staleKraty);
        noweRozwiazanie.initiateGenetics(81);
        noweRozwiazanie.OverwriteOnSetFields();
        return noweRozwiazanie;
    }

    @Override
    public int compareTo(Specimen specimen) {
        return this.score.compareTo(specimen.score);
    }

    public void OverwriteOnSetFields(){
        for(int i=0;i<81;i++){
            if(staleKraty.get(i)!=0){
                Pole pole = new Pole();
                pole.setNewVal(staleKraty.get(i));
                getAlleles().set(i,pole);
            }
        }
    }

    int sprawdzKonfliktyPion(ArrayList<Allele> allele){
        int liczbaKonfliktow=0;
        int[] checkList =new int[9];
        Arrays.fill(checkList,0);
        for(int i=0;i<9;i++){
            int mainSkl;
            for(int j=0;j<9;j++){
                mainSkl=i+(j*9);
                checkList[((Pole)getAlleles().get(mainSkl)).getVal()-1]+=1;
            }
            for(int j=0;j<9;j++){
                if(checkList[j]>1){
                    liczbaKonfliktow+=checkList[j]-1;
                }
            }
            Arrays.fill(checkList,0);
        }
        return liczbaKonfliktow;
    }

    int sprawdzKonfliktyPoziom(ArrayList<Allele> allele){
        int liczbaKonfliktow=0;
        int[] checkList =new int[9];
        Arrays.fill(checkList,0);
        for(int i=0;i<9;i++){
            int mainSkl=i*9;
            for(int j=0;j<9;j++){
                int index =mainSkl+j;
                checkList[((Pole)getAlleles().get(index)).getVal()-1]+=1;
            }
            for(int j=0;j<9;j++){
                if(checkList[j]>1){
                    liczbaKonfliktow+=checkList[j]-1;
                }
            }
            Arrays.fill(checkList,0);
        }
        return liczbaKonfliktow;
    }

    int sprawdzKonfliktyKrata(ArrayList<Allele> allele){
        int liczbaKonfliktow=0;
        int[] tabWierzch ={0,3,6,27,30,33,54,57,60};
        int[] checkList =new int[9];
        Arrays.fill(checkList,0);
        for(int i=0;i<9;i++){
            checkList[((Pole)allele.get(tabWierzch[i])).getVal()-1]+=1;
            checkList[((Pole)allele.get(tabWierzch[i]+1)).getVal()-1]+=1;
            checkList[((Pole)allele.get(tabWierzch[i]+2)).getVal()-1]+=1;
            checkList[((Pole)allele.get(tabWierzch[i]+9)).getVal()-1]+=1;
            checkList[((Pole)allele.get(tabWierzch[i]+10)).getVal()-1]+=1;
            checkList[((Pole)allele.get(tabWierzch[i]+11)).getVal()-1]+=1;
            checkList[((Pole)allele.get(tabWierzch[i]+18)).getVal()-1]+=1;
            checkList[((Pole)allele.get(tabWierzch[i]+19)).getVal()-1]+=1;
            checkList[((Pole)allele.get(tabWierzch[i]+20)).getVal()-1]+=1;
            for(int j=0;j<9;j++){
                if(checkList[j]>1){
                    liczbaKonfliktow+=checkList[j]-1;
                }
            }
            Arrays.fill(checkList,0);

        }

        return liczbaKonfliktow;
    }

    public ArrayList<? extends Allele> getGenome(){return getGenetics().getAlleles();}
}
