package model;

import java.util.Arrays;
import util.TreeAVL;

public class Ranking {
    private Palavra[] maisBuscadas;
    private Palavra[] menosBuscadas;
    private Pagina[] maisVisitadas;
    private Pagina[] menosVisitadas;
    private Pagina[] maisRelevantes;
    private TreeAVL palavras;
    private static int l = 0;
    
    public Ranking() {
        this.maisBuscadas = new Palavra[3];
        this.menosBuscadas = new Palavra[3];
        this.maisVisitadas = new Pagina[3];
        this.menosVisitadas = new Pagina[3];
        this.maisRelevantes = new Pagina[3];
        palavras = new TreeAVL();
    }

    public Palavra[] getMaisBuscadas() {
        return maisBuscadas;
    }
    
    public Palavra getMaisBuscadas(int index) {
        return this.maisBuscadas[index];
    }
    
    public void setMaisBuscadas(Palavra[] maisBuscadas) {
        this.maisBuscadas = maisBuscadas;
    }

    public Palavra[] getMenosBuscadas() {
        return menosBuscadas;
    }
    
    public Palavra getMenosBuscadas(int index) {
        return this.menosBuscadas[index];
    }
    
    public void setMenosBuscadas(Palavra[] menosBuscadas) {
        this.menosBuscadas = menosBuscadas;
    }

    public Pagina[] getMaisVisitadas() {
        return maisVisitadas;
    }

    public Pagina getMaisVisitadas(int index) {
        return this.maisVisitadas[index];
    }
    
    public void setMaisVisitadas(Pagina[] maisVisitadas) {
        this.maisVisitadas = maisVisitadas;
    }

    public Pagina[] getMenosVisitadas() {
        return menosVisitadas;
    }

    public Pagina getMenosVisitadas(int index) {
        return this.menosVisitadas[index];
    }
    
    public void setMenosVisitadas(Pagina[] menosVisitadas) {
        this.menosVisitadas = menosVisitadas;
    }

    public Pagina[] getMaisRelevantes() {
        return maisRelevantes;
    }
    
    public Pagina getMaisRelevantes(int index) {
        return this.maisRelevantes[index];
    }
    
    public void setMaisRelevantes(Pagina[] maisRelevantes) {
        this.maisRelevantes = maisRelevantes;
    }

    public TreeAVL getPalavras() {
        return palavras;
    }

    public void setPalavras(TreeAVL palavras) {
        this.palavras = palavras;
    }
    
    public void addPalavras(Palavra p){
        if(p != null){    
            palavras.insert((Comparable)p);
        }
    }
    
    public boolean equals(Object obj){
        if(obj instanceof Ranking){
            Ranking rank = (Ranking) obj;
            if(Arrays.equals(this.getMaisBuscadas(), rank.getMaisBuscadas())){
                return true;
            }
            if(Arrays.equals(this.getMenosBuscadas(), rank.getMenosBuscadas())){
                return true;
            }
            if(Arrays.equals(this.getMaisRelevantes(), rank.getMaisRelevantes())){
                return true;
            }
            if(Arrays.equals(this.getMaisVisitadas(), rank.getMaisVisitadas())){
                return true;
            }
            if(Arrays.equals(this.getMenosVisitadas(), rank.getMenosVisitadas())){
                return true;
            }
            if(this.getPalavras().equals(rank.getPalavras())){
                return true;
            }
        }
        return false;
    }
}