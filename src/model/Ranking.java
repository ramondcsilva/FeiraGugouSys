package model;

import util.TreeAVL;

public class Ranking {
    private TreeAVL palavras;
    private static int l = 0;
    
    public Ranking() {
        palavras = new TreeAVL();
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
            if(this.getPalavras().equals(rank.getPalavras())){
                return true;
            }
        }
        return false;
    }
}